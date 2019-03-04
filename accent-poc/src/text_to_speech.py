from gtts import gTTS
import os
import librosa
import numpy as np
import urllib3

BASE_DIR = os.getcwd()
FilePath = BASE_DIR + '/Audio/test/'
final = []
for line in open('word.csv'):
    lines = [line.rstrip('\n') ]
    result = '\n'.join(lines)
    print(result)
    urllib3.disable_warnings()
    tts = gTTS(result)
    tts.save(FilePath + result +'.wav')
    y, sr = librosa.load(FilePath + result + '.wav')
    pitches, magnitudes = librosa.piptrack(y=y, sr=sr)
    maxvalue = np.min(pitches)
    final.append(maxvalue)
    os.remove(FilePath + result + ".wav")
print(final)

with open('people.csv', 'w') as f:
    for item in final:
        f.write("%s\n" % item)
