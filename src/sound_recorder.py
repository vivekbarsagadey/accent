import pyaudio
import wave
import  struct
import numpy as np
from scipy import signal
import matplotlib.pyplot as plt


FORMAT = pyaudio.paInt16
CHANNELS = 2
RATE = 44100
CHUNK = 1024
RECORD_SECONDS =5
WAVE_OUTPUT_FILENAME = "file.wav"
THRESHOLD = 40

audio = pyaudio.PyAudio()
plot_counter = 0

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
    print(data_int)
    #samples = array(data_int)
    frames.append(data)
print("finished recording")

# stop Recording
stream.stop_stream()
stream.close()
audio.terminate()

amplitude = np.sqrt(np.mean(np.square(data_int)))
#spectrogram of data

f, t, Sxx = signal.spectrogram(data_int, RATE)
plt.pcolormesh(t, f, Sxx)
plt.ylabel('Frequency [Hz]')
plt.xlabel('Time [sec]')
plt.show()

#write audio into the file

waveFile = wave.open(WAVE_OUTPUT_FILENAME, 'wb')
waveFile.setnchannels(CHANNELS)
waveFile.setsampwidth(audio.get_sample_size(FORMAT))
waveFile.setframerate(RATE)
waveFile.writeframes(b''.join(frames))
waveFile.close()