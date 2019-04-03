# import fuzzy
# from pymongo import MongoClient
# import argparse
#
# parser = argparse.ArgumentParser(description='Load names into the database')
# parser.add_argument('name', nargs='+')
# args = parser.parse_args()
#
# client = MongoClient()
# db = client.phonetic_search
# dmetaphone = fuzzy.DMetaphone()
# soundex = fuzzy.Soundex(4)
#
# for n in args.name:
#     # Compute the hashes. Save soundex
#     # and nysiis as lists to be consistent
#     # with dmetaphone return type.
#     values = {'_id':n,
#               'name':n,
#               'soundex':[soundex(n)],
#               'nysiis':[fuzzy.nysiis(n)],
#               'dmetaphone':dmetaphone(n),
#               }
#     print ('Loading %s: %s, %s, %s' % (n, values['soundex'][0], values['nysiis'][0],values['dmetaphone']))
#     db.people.update({'_id':n}, values,True, False)




import pyttsx3
from python_speech_features import mfcc
import os
import librosa
import pandas as pd

# BASE_DIR = os.getcwd()
# FilePath = BASE_DIR + '/Audio/compare/'
# y, sr = librosa.load(FilePath+'shan-collaboration.wav')
# mfcc_featurs = mfcc(y,sr)
# df = pd.DataFrame(mfcc_featurs)
# df.to_csv('shan.csv',index=False)
engine = pyttsx3.init()
rate = engine.getProperty('rate')
engine.setProperty('rate', rate-50)
engine.say('The quick brown fox jumped over the lazy dog.')
engine.runAndWait()