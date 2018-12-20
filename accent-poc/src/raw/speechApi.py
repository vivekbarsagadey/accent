import speech_recognition as sr
from os import path


AUDIO_FILE_EN = path.join(path.dirname(path.realpath(__file__)), "ukEnglish.wav")

# use the audio file as the audio source
r = sr.Recognizer()
with sr.AudioFile(AUDIO_FILE_EN) as source:
    audio_en = r.record(source)
try:
    print('Audio File Said:',r.recognize_sphinx(audio_en))
except sr.UnknownValueError:
    print("Sphinx could not understand audio")
except sr.RequestError as e:
    print("Sphinx error; {0}".format(e))
with sr.Microphone() as source:
    r.adjust_for_ambient_noise(source)
    print("Say something!")
    audio = r.listen(source)

# recognize speech using Google
try:
    print("You said " + r.recognize_google(audio))
except sr.UnknownValueError:
    print("Google could not understand audio")
except sr.RequestError as e:
    print("Google error; {0}".format(e))

with open("microphone-results.wav", "wb") as f:
    f.write(audio.get_wav_data())