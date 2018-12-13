import matplotlib.pyplot as plt
from matplotlib import style
from pydub import AudioSegment
from scipy.io.wavfile import write
from scipy.io import wavfile as wav
from scipy.fftpack import fft,fftfreq
import numpy as np
from scipy.signal import hilbert
import librosa
import soundfile as sf

sample_rate =100
rate, data = wav.read('file.wav')
sound = AudioSegment.from_file('file.wav', format="wav")
print('How fast to play the data(framerate):',rate)
print('printing the sound  as list of values(raw data)',data)

fft_out =np.fft.fft(data)


slice = np.abs(fft_out)

#AMPLITUDE EXTRACTION FROM FOURIER DATA
analytic_signal = hilbert(data)
amplitude_envelope = np.abs(analytic_signal)



#FREQUENCY EXTRACTION FROM DATA
ferq = np.fft.fftfreq(len(fft_out))
psd = np.abs(fft_out)
print(ferq)
print(psd)
print(len(fft_out))
#idx = np.argmax(np.abs(fft_out))
#freqs = ferq[idx]
freq_in_hertz = abs(ferq * sample_rate)
#print("Frequency in hertz:",freq_in_hertz)
#print("Diamensionless frequency point",ferq)
#print("Printing the array of frequency diamensional:",fft_out)
#plt.plot(freq_in_hertz,'c',label = "freq_hz",linewidth =5)
#plt.title("sound value")

#Modification in pitch
octaves = 0.5
#new_sample_rate = int(sound.frame_rate * (2.0 ** octaves))
y_shifted = librosa.effects.pitch_shift(ferq, rate, n_steps=0)
print(y_shifted)
#hipitchSound = sound._spwan(sound.raw_data,overrides={'frame_rate': new_sample_rate})
#hipitchSound = hipitchSound.set_frame_rate(44100)
#scaled = np.int16(freq_in_hertz/np.max(np.abs(slice)) * 32767)
sf.write('test.wav',y_shifted,rate)

#plot the graph

plt.subplot(2,2,1)
plt.plot(analytic_signal,'r',label = "dat_value")
plt.subplot(2,2,2)
plt.plot(amplitude_envelope,'g',label = "dat_value")
plt.subplot(2,2,3)
plt.plot(ferq,psd,'c',label = "freq_comp")
plt.subplot(2,2,4)
plt.plot(y_shifted,'c',label = "freq_comp")
#plt.xlabel("samples")
#plt.ylabel("frequency")
plt.show()
total = np.sum(data)
print(total)