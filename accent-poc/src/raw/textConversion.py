from gtts import gTTS
import librosa
import numpy as np
import glob
from python_speech_features import mfcc
from scipy.cluster.vq import kmeans2, whiten, vq
from hmmlearn import hmm
import scipy.io.wavfile as wav
#tts = gTTS('hello iam amol.')
#tts.save('hello.wav')



FilePath = 'C:/project/accent/accent-poc/src/Audio/'
raw_samples, sample_rate = librosa.load(FilePath+'speaker2.wav')
mfcc_features = librosa.feature.mfcc(y=raw_samples, sr=sample_rate)
print(mfcc_features)
print('Wave duration is %4.2f seconds' % (len(raw_samples) / float(sample_rate)))

# Create the network's input training data, X
# mfcc is organized (feature, sample) but the net needs (sample, feature)
# X is mfcc reorganized to (sample, feature)
X = np.moveaxis(mfcc_features, 1, 0)
print('mfcc.shape:', mfcc_features.shape)
print('X.shape:   ', X.shape)

mfcc_samples = mfcc_features.shape[1]
mfcc_span = 512/float(sample_rate)
print ('MFCC calculated duration is %4.2f seconds' % (mfcc_span*mfcc_samples))
print(mfcc_samples)
print(mfcc_span)
# MFCC Features. Each row corresponds to MFCC for a frame
mfcc_feats = None
mfcc_person = mfcc(raw_samples.astype(np.float64), sample_rate,nfft=1220)

if mfcc_feats is None:
    mfcc_feats = mfcc_person
else:
    mfcc_feats = np.concatenate((mfcc_feats, mfcc_person), axis=0)

# Normalize the features
whitened = whiten(mfcc_feats)
print(whitened.shape)

