from gtts import gTTS
import os
line = input('Enter a sentence:')
language = 'en'
myobj = gTTS(text=line, lang=language, slow=False)
myobj.save("file1.wav")
os.system("file1.wav")
