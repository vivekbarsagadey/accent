import pyaudio
import wave
import  struct
import numpy as np
from scipy import signal
from hmmlearn.hmm import GaussianHMM
import matplotlib.pyplot as plt
import librosa.display
FORMAT = pyaudio.paInt16
CHANNELS = 2
RATE = 44100
CHUNK = 1024
RECORD_SECONDS =2
WAVE_OUTPUT_FILENAME = "file.wav"

audio = pyaudio.PyAudio()
# start Recording
stream = audio.open(format=FORMAT, channels=CHANNELS,
                    rate=RATE, input=True,
                    frames_per_buffer=CHUNK)
print ("recording...")
frames = []

for i in range(0, int(RATE / CHUNK * RECORD_SECONDS)):
    data = stream.read(CHUNK)
    #print(data)
    data_int = np.array(struct.unpack(str(4 * CHUNK) + 'B', data))
    #samples = array(data_int)
    frames.append(data_int)
    print(data_int)
print("finished recording")
print(data_int)
# stop Recording
stream.stop_stream()
stream.close()
audio.terminate()

amplitude = np.sqrt(np.mean(np.square(data_int)))
#print(amplitude)
#print(len(data_int))
#print(data_int.shape)
newValue = data_int.reshape((64,64))
#print(newValue)

observation = np.array((0.6 * np.random.random_sample((40,2)) - 0.3), dtype=np.double)
print(observation)
#initating hmm model
nComp = 5  # number of states
nMix = 2   # number of mixtures
covarianceType = 'full'    # covariance type
n_iter = 10    # number of iterations
startprobPrior = None
transmatPrior = None
bakisLevel = 2
model = GaussianHMM(n_components = nComp, \
                           transmat_prior = transmatPrior, startprob_prior = startprobPrior, \
                           covariance_type = covarianceType, n_iter = n_iter)

model.fit(observation,40)
hidden_states = model.predict(observation)
print("Transition matrix")
print(model.transmat_)
print()

print("Means and vars of each hidden state")
for i in range(model.n_components):
    print("{0}th hidden state".format(i))
    print("mean = ", model.means_[i])
    print("var = ", np.diag(model.covars_[i]))
    print()

#write audio into the file
waveFile = wave.open(WAVE_OUTPUT_FILENAME, 'wb')
waveFile.setnchannels(CHANNELS)
waveFile.setsampwidth(audio.get_sample_size(FORMAT))
waveFile.setframerate(RATE)
waveFile.writeframes(b''.join(frames))
waveFile.close()

#spectrogram of data
y, sr = librosa.load(librosa.util.example_audio_file())
#sr = 22050 #default sampling rate
D = librosa.amplitude_to_db(librosa.stft(y), ref=np.max)

# yaxis
n = D.shape[0]
yout = librosa.fft_frequencies(sr=sr, n_fft=1+(2 * (n - 1)) )
print (yout, yout.min(), yout.max())

#xaxis
m = D.shape[1]
hop_length=512
xout = librosa.frames_to_time(np.arange(m+1), sr=sr, hop_length=hop_length)
print (xout, xout.min(), xout.max())
f, t, Sxx = signal.spectrogram(data_int, RATE)

#dBS = 5 * np.log10(Sxx)
plt.pcolormesh(t, f,Sxx)
plt.colorbar(format='%+2.0f dB')
plt.ylabel('Frequency [Hz]')
plt.xlabel('Time [sec]')
plt.show()
#slicing of spectrogram

plt.pcolormesh(t, f, Sxx)
plt.colorbar(format='%+2.0f dB')
plt.ylabel('Frequency [Hz]')
plt.xlabel('Time [sec]')
plt.imshow(Sxx)
ax=plt.gca()
ax.invert_yaxis()
plt.show()