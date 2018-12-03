import speech_recognition as sr
import pyaudio


# Record Audio
r = sr.Recognizer()
r.energy_threshold = 100
with sr.Microphone(chunk_size=1024, sample_rate=4100) as source:
    print("Say something!")
    r.adjust_for_ambient_noise(source, duration=3)
    audio = r.record(source,duration=10)

# Speech recognition using Google Speech Recognition
try:
    # for testing purposes, we're just using the default API key
    # to use another API key, use `r.recognize_google(audio, key="GOOGLE_SPEECH_RECOGNITION_API_KEY")`
    # instead of `r.recognize_google(audio)`fff
    print("You said: " + r.recognize_google(audio,language='en-GB'))
except sr.RequestError as e:
    print("Could not request results from Google Speech Recognition service; {0}".format(e))
except sr.UnknownValueError:
    print("Google Speech Recognition could not understand audio")