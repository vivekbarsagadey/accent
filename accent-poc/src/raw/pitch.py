import librosa
import numpy as np
import matplotlib.pyplot as plt
import wave
from scipy.fftpack import fft
from scipy.signal import argrelextrema
FilePath = 'C:/project/accent/accent-poc/src/Audio/'

chunk = 2048
wf = wave.open(FilePath+'speaker1.wav','r')
rate = wf.getframerate()
swidth = wf.getsampwidth()
values=[]
timestep = 1.0/rate
while True:
    signal = wf.readframes(chunk)
    if len(signal) < chunk * swidth:
        break
    else:
        signal = np.fromstring(signal, 'Int16')
        n = signal.size
        # we apply the FFT, extract the frequencies and take their absolute value
        freqs = fft(signal)
        freq = np.fft.fftfreq(n, d=timestep)
        data = np.abs(freqs)
        # we find the frequency with maximum intensity
        max = [x for x in argrelextrema(data, np.greater)[0] if x < 10000]
        maxInt = data[max]
        absMaxInt = np.max(maxInt)
        absMax = np.where(maxInt == absMaxInt)[0][0]
        number = max[absMax] * rate / chunk
        values.append(number)
print(values)


y, sr = librosa.load(FilePath+'speaker1.wav')
pitches, magnitudes = librosa.piptrack(y=y, sr=sr)
plt.subplot(211)
plt.plot(y)
plt.title('wave')

plt.subplot(212)
#plt.imshow(pitches[:, :], aspect="auto", interpolation="nearest", origin="bottom")
plt.plot(pitches)
plt.title('pitches')
plt.grid()
plt.show()
print(pitches.shape)