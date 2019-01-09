import matplotlib.pyplot as plt
from scipy.io import wavfile
import os
from os import path
import numpy as np
from scipy.fftpack import fft
from pocketsphinx import get_model_path, get_data_path
from pocketsphinx import DefaultConfig, Decoder


def custom_fft(y, fs):
    T = 1.0 / fs
    N = y.shape[0]
    yf = fft(y)
    xf = np.linspace(0.0, 1.0 / (2.0 * T), N // 2)
    vals = 2.0 / N * np.abs(yf[0:N // 2])  # FFT is symmetrical, so we take just the first half
    # FFT is also complex, to we take just the real part (abs)
    return xf, vals


FilePath = 'C:/project/accent/accent-poc/src/Audio/'
model_path = get_model_path()
data_path = get_data_path()
dirs = [f for f in os.listdir(FilePath)]
recordings = []
for direct in dirs:
    if direct.endswith('.wav'):
        recordings.append(direct)

# Create a decoder with a certain model
config = DefaultConfig()
config.set_string('-hmm', os.path.join(model_path, 'en-us'))
config.set_string('-allphone', path.join(model_path, 'en-us/en-us-phone.lm.dmp'))
config.set_string('-lm', os.path.join(model_path, 'en-us.lm.bin'))
#config.set_string('-dict', os.path.join(model_path, 'cmudict-en-us.dict'))
config.set_float('-lw', 2.0)
config.set_float('-beam', 1e-10)
config.set_float('-pbeam', 1e-10)
decoder = Decoder(config)

# Decode streaming data
buf = bytearray(1024)
with open(path.join(FilePath, 'pronunciation.wav'), 'rb') as f:
    decoder.start_utt()
    while f.readinto(buf):
        decoder.process_raw(buf, False, False)
    decoder.end_utt()
#hypothesis = decoder.hyp()
#print(hypothesis)
print('Phonemes: ', [seg.word for seg in decoder.seg()])

# plot the graph
fig = plt.figure(figsize=(12, 5))
plt.subplots_adjust(hspace=0.5)
for index, filename in enumerate(recordings, start=1):
    sample_rate, samples = wavfile.read(str(FilePath) + filename)
    xf, vals = custom_fft(samples, sample_rate)
    plt.subplot(3, 2, index)
    plt.title('FFT of File ' + filename)
    plt.plot(xf, vals)
    plt.grid()
plt.xlabel('Frequency')
plt.savefig('plot.png')
plt.show()
