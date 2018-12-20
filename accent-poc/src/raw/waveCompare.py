import os
from scipy.io import wavfile
import matplotlib.pyplot as plt
import numpy as np
from scipy.fftpack import fft

audioPath ='C:/project/accent/accent-poc/src/raw/'

def custom_fft(y, fs):
    T = 1.0 / fs
    N = y.shape[0]
    yf = fft(y)
    xf = np.linspace(0.0, 1.0/(2.0*T), N//2)
    vals = 2.0/N * np.abs(yf[0:N//2])  # FFT is simmetrical, so we take just the first half
    # FFT is also complex, to we take just the real part (abs)
    return xf, vals


dirs = [f for f in os.listdir(audioPath)]
recordings = []

for direct in dirs:
    if direct.endswith('.wav'):
       recordings.append(direct)

for filename in recordings:
    sample_rate, samples = wavfile.read(str(audioPath) + filename)
    xf, vals = custom_fft(samples, sample_rate)
    plt.figure(figsize=(12, 4))
    plt.title('FFT of File '  + filename)
    plt.plot(xf, vals)
    plt.xlabel('Frequency')
    plt.grid()
    plt.show()