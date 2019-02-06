from pocketsphinx import AudioFile

# Frames per Second
fps = 100
FilePath = 'C:/project/accent/accent-poc/src/Audio/'
for phrase in AudioFile(frate=fps):  # frate (default=100)
    print('-' * 28)
    print('| %5s |  %3s  |   %4s   |' % ('start', 'end', 'word'))
    print('-' * 28)
    for s in phrase.seg():
        print('| %4ss | %4ss | %8s |' % (s.start_frame / fps, s.end_frame / fps, s.word))
    print('-' * 28)