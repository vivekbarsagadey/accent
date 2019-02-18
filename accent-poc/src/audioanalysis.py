from pyAudioAnalysis import audioBasicIO
from pyAudioAnalysis import audioFeatureExtraction
import numpy as np
import os
import pandas as pd
import librosa
from pyAudioAnalysis import audioTrainTest as aT
import matplotlib.pyplot as plt

BASE_DIR = os.getcwd()
FilePath = BASE_DIR + '/Audio/'
mfccPath = BASE_DIR + '/csvFiles/'
[Fs, x] = audioBasicIO.readAudioFile(FilePath + "speaker1.wav")
F, f_names = audioFeatureExtraction.stFeatureExtraction(x, Fs, 0.050*Fs, 0.025*Fs)
print('energy_entropy',F[3,:])
print('MFCC',F[9,:])
mfcc = F[9,:]
print(mfcc.shape)
df = pd.DataFrame(F)
df.to_csv(mfccPath  +'all_features.csv',index=False)
print('Chroma Deviation',F[2,:])
print(F.shape)


#aT.featureAndTrain(["C:/project/accent/accent-poc/src/Audio/"], 1.0, 1.0, aT.shortTermWindow, aT.shortTermStep, "knn", "knnclassifier", False)
#classification, pro, classname = aT.fileClassification("speaker1.wav", "knnclassifier","knn")
#print(classification)
#print(classname)


plt.subplot(2,1,1)
plt.plot(F[34,:])
plt.xlabel('Frame no')
plt.ylabel(f_names[34])
plt.subplot(2,1,2)
plt.plot(F[9,21:])
plt.xlabel('Frame no')
plt.ylabel(f_names[9])
plt.show()


