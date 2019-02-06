import numpy as np
from scipy.spatial.distance import euclidean
from fastdtw import fastdtw
import librosa
import matplotlib.pyplot as plt
x = np.array([[1,1], [2,2], [3,3], [4,4], [5,5]])
y = np.array([[2,2], [3,3], [4,4]])
distance, path = fastdtw(x, y, dist=euclidean)
#print(distance)
#print(path)

FilePath = 'C:/project/accent/accent-poc/src/Audio/'
y, sr = librosa.load(FilePath+'sat.wav')
temp = librosa.feature.mfcc(y=y, sr=sr ,n_mfcc=12)
mfcc = temp.T        #Here it did transpose because mfcc is 2 dimensional
print('mfcc array',mfcc)
np.savetxt("mfcc_sat.csv", mfcc, delimiter=",")
print('mfcc diamension',mfcc.shape)
plt.figure(figsize=(10, 4))
plt.plot(temp)
plt.title('MFCC')
plt.show()
