import librosa
import pandas as pd
import os
import matplotlib.pyplot as plt
from python_speech_features import mfcc

BASE_DIR = os.getcwd()
FilePath = BASE_DIR + '/Audio/compare/'
mfccPath = BASE_DIR + '/csvFiles/'
dirs = [f for f in os.listdir(FilePath)]
recordings = []
for audio in dirs:
    if audio.endswith('.wav'):
        recordings.append(audio)
print(recordings)


fig = plt.figure(figsize=(12, 6))
plt.subplots_adjust(hspace=0.5)
for index, filename in enumerate(recordings, start=1):
    y, sr = librosa.load(str(FilePath) + filename)
    temp = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=12)
    #temp = mfcc(y,sr)
    mfcc = temp.T  # Here it did transpose because mfcc is 2 dimensional
    #print('mfcc array', mfcc)
    file = filename.split('.')
    df = pd.DataFrame(mfcc)
    print(df)
    df.to_csv(mfccPath + file[0] +'.csv',index=False)
    print('mfcc diamension', mfcc.shape)
    plt.subplot(2,2, index)
    plt.title('MFCC Features of ' + filename)
    plt.plot(mfcc)
    plt.grid()
plt.show()