from gtts import gTTS
import os
import librosa
import numpy as np
import urllib3
import csv

BASE_DIR = os.getcwd()
FilePath = BASE_DIR + '/Audio/test/'
urllib3.disable_warnings()
maxvalue = []
for line in open('word.csv'):
    lines = [line.rstrip('\n') ]
    result = '\n'.join(lines)
    print(result)
    tts = gTTS(result)
    tts.save(FilePath + result +'.wav')
    y, sr = librosa.load(FilePath + result + '.wav')
    pitches, magnitudes = librosa.piptrack(y=y, sr=sr)
    maxvalue = np.max(pitches)
    print(maxvalue)
    os.remove(FilePath + result + ".wav")
