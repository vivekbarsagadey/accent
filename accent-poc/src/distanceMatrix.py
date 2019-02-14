import numpy as np
from scipy.spatial.distance import euclidean
from fastdtw import fastdtw
import librosa
import pandas as pd
import matplotlib.pyplot as plt
x = np.array([[1,1], [2,2], [3,3], [4,4], [5,5]])
z = np.array([[2,2], [3,3], [4,4]])
distance, path = fastdtw(x, z, dist=euclidean)
#print(distance)
#print(path)

FilePath = 'C:/project/accent/accent-poc/src/Audio/'
mfccPath = 'C:/project/accent/accent-poc/src/csvFiles/'

y, sr = librosa.load(FilePath+'speaker2.wav')
temp = librosa.feature.mfcc(y=y, sr=sr ,n_mfcc=12)
mfcc = temp.T        #Here it did transpose because mfcc is 2 dimensional
print('mfcc array',mfcc)
df = pd.DataFrame(mfcc)
df.to_csv(mfccPath + 'mfcc_amol.csv')
print('mfcc diamension',mfcc.shape)
plt.figure(figsize=(10, 4))
plt.plot(mfcc)
plt.title('MFCC')
plt.show()
