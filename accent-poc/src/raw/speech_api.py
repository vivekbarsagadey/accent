import speech_recognition as sr
import pyaudio


# Record Audio
r = sr.Recognizer()  # initialize recognizer
with sr.Microphone() as source:  # mention source it will be either Microphone or audio files.
    print("Speak Anything :")
    audio = r.listen(source)  # listen to the source
    try:
        text = r.recognize_google(audio)  # use recognizer to convert our audio into text part.
        type(audio)
        print("You said : {}".format(text))
    except:
        print("Sorry could not recognize your voice")