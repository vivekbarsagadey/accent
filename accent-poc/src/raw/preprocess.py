import matplotlib.pyplot as plt
from scipy.io import wavfile
import os
from os import path
import numpy as np
from scipy.fftpack import fft
from pocketsphinx import get_model_path, get_data_path
from pocketsphinx import DefaultConfig, Decoder

fps = 50
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

# Create a decoder with a hmm model
config = DefaultConfig()
config.set_string('-hmm', os.path.join(model_path, 'en-us'))
config.set_string('-allphone', os.path.join(model_path, 'en-us-phone.lm.bin'))
config.set_string('-lm', os.path.join(model_path, 'en-us.lm.bin'))
config.set_string('-dict', os.path.join(model_path, 'cmudict-en-us.dict'))
config.set_float('-lw', 2.0)
config.set_float('-beam', 1e-10)
config.set_float('-pbeam', 1e-10)
decoder = Decoder(config)

# Decode streaming data
buf = bytearray(1024)
with open(path.join(FilePath, 'Recording.wav'), 'rb') as f:
    decoder.start_utt()
    while f.readinto(buf):
        decoder.process_raw(buf, False, False)
    decoder.end_utt()
    print('Phonemes: ', [seg.word for seg in decoder.seg()])
    print('-' * 28)
    print('| %5s |  %3s  |   %4s   |' % ('start', 'end', 'word'))
    print('-' * 28)
    for s in decoder.seg():
        print('| %4ss | %4ss | %8s |' % (s.start_frame / fps, s.end_frame / fps, s.word))
    print('-' * 28)
#hypothesis = decoder.hyp()
#print(hypothesis)


# plot the graph
'''fig = plt.figure(figsize=(12, 6))
plt.subplots_adjust(hspace=0.5)
for index, filename in enumerate(recordings, start=1):
    sample_rate, samples = wavfile.read(str(FilePath) + filename)
    xf, vals = custom_fft(samples, sample_rate)
    plt.subplot(3, 3, index)
    plt.title('Frequency of BIRD word of ' + filename)
    plt.plot(xf, vals)
    plt.grid()
plt.xlabel('Frequency')
plt.savefig('plot.png')
plt.show()'''
