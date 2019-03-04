import Levenshtein
from scipy.spatial.distance import euclidean
from fastdtw import fastdtw
import numpy as np
import difflib
import csv

data = Levenshtein.ratio('sally', 'sdssally')
print('Matching ratio: ', data)



# x = np.array([[1, 1], [2, 2], [3, 3], [4, 4], [5, 5]])
# y = np.array([[2, 2], [3, 3], [4, 4]])
# distance, path = fastdtw(x, y, dist=euclidean)
# print(distance)
# print(path)

#method 1
data = Levenshtein.ratio('sally', 'sally')
print('Matching ratio: ',data)

#method 2
a = 'abcd'
b = 'Ab123'
seq = difflib.SequenceMatcher()
seq.set_seqs(a.lower(), b.lower())
data = seq.ratio()*100
print(data)


words = []
for line in open('word.csv'):
    lines = [line.rstrip('\n') ]
    result = '\n'.join(lines)
    words.append(result)
#print(words)

#method 3
match = difflib.get_close_matches('bove', words)
print('Possible Matching Words:', match)
