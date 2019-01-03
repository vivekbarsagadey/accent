import matplotlib.pyplot as plot
import numpy
from scipy.io import wavfile

# Read the wav file (mono)
pre_emphasis = 0.97
frame_size = 0.025
frame_stride = 0.01
NFFT = 256
nfilt = 40
samplingFrequency, signalData = wavfile.read('file.wav')


print(signalData)
print(signalData.shape)

#convert the shape
processData = signalData[:,0]
print(processData.shape)

#apply Pre-amphsis

emphasized_signal = numpy.append(signalData[0], signalData[1:] - pre_emphasis * signalData[:-1])

# Convert from seconds to samples
frame_length, frame_step = frame_size * samplingFrequency, frame_stride * samplingFrequency
signal_length = len(emphasized_signal)
frame_length = int(round(frame_length))
frame_step = int(round(frame_step))
num_frames = int(numpy.ceil(float(numpy.abs(signal_length - frame_length)) / frame_step))
pad_signal_length = num_frames * frame_step + frame_length
z = numpy.zeros((pad_signal_length - signal_length))
pad_signal = numpy.append(emphasized_signal, z)
indices = numpy.tile(numpy.arange(0, frame_length), (num_frames, 1)) + numpy.tile(numpy.arange(0, num_frames * frame_step, frame_step), (frame_length, 1)).T
frames = pad_signal[indices.astype(numpy.int32, copy=False)]
frames *= numpy.hamming(frame_length)
mag_frames = numpy.absolute(numpy.fft.rfft(frames, NFFT))  # Magnitude of the FFT
pow_frames = ((1.0 / NFFT) * ((mag_frames) ** 2))  # Power Spectrum
#mfcc Spectrum
low_freq_mel = 0
high_freq_mel = (2595 * numpy.log10(1 + (samplingFrequency / 2) / 700))  # Convert Hz to Mel
mel_points = numpy.linspace(low_freq_mel, high_freq_mel, nfilt + 2)  # Equally spaced in Mel scale
hz_points = (700 * (10**(mel_points / 2595) - 1))  # Convert Mel to Hz
bin = numpy.floor((NFFT + 1) * hz_points / samplingFrequency)
fbank = numpy.zeros((nfilt, int(numpy.floor(NFFT / 2 + 1))))
for m in range(1, nfilt + 1):
    f_m_minus = int(bin[m - 1])   # left
    f_m = int(bin[m])             # center
    f_m_plus = int(bin[m + 1])    # right

    for k in range(f_m_minus, f_m):
        fbank[m - 1, k] = (k - bin[m - 1]) / (bin[m] - bin[m - 1])
    for k in range(f_m, f_m_plus):
        fbank[m - 1, k] = (bin[m + 1] - k) / (bin[m + 1] - bin[m])
filter_banks = numpy.dot(pow_frames, fbank.T)
filter_banks = numpy.where(filter_banks == 0, numpy.finfo(float).eps, filter_banks)  # Numerical Stability
filter_banks = 20 * numpy.log10(filter_banks)  # dB
print('these are the filter banks',filter_banks)
print('these are pow_frames',pow_frames)

filter_shape = filter_banks[:,0]
pow_shape = pow_frames[:,0]
print(filter_shape.shape)
print(pow_shape.shape)
# Plot the signal read from wav file
plot.subplot(211)

plot.title('Spectrogram of a wav file recorded with pyudio')

plot.plot(signalData)

plot.xlabel('Sample')

plot.ylabel('Amplitude')

plot.subplot(212)

plot.specgram(filter_shape,NFFT,Fs=samplingFrequency)

plot.xlabel('Time')

plot.ylabel('Frequency')

plot.show()

