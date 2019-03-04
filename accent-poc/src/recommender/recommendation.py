import os
import pandas as pd
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import CountVectorizer

filepath = os.getcwd()
df = pd.read_csv(filepath + '/AccentGURU.csv')

df = df[['words', 'category', 'phase']]
print(df.dtypes)
df['phase'] = df['phase'].map(lambda x: x.lower())
df['words'] = df['words'].map(lambda x: x.lower())

df.set_index('words', inplace=True)

df['bag_of_words'] = ''
columns = df.columns
for index, row in df.iterrows():
    words = ''
    for col in columns:
        words = words + row[col] + ' '
    row['bag_of_words'] = words

df.drop(columns=[col for col in df.columns if col != 'bag_of_words'], inplace=True)

# print(df.head())

count = CountVectorizer()
count_matrix = count.fit_transform(df['bag_of_words'])
#print(count.get_feature_names())
#print(count_matrix.toarray())
indices = pd.Series(df.index)
#print(indices)
# print(indices[:5])

cosine_sim = cosine_similarity(count_matrix, count_matrix)


# print(cosine_sim)


def recommendations(words, cosine_sim=cosine_sim):
    recommended_words = []

    # gettin the index of the words that matches the word
    idx = indices[indices == words].index[0]

    # creating a Series with the similarity scores in descending order
    score_series = pd.Series(cosine_sim[idx]).sort_values(ascending=False)
    #print(score_series)
    # getting the indexes of the 10 most similar words
    top_10_indexes = list(  score_series.iloc[2:6].index)

    # populating the list with the words of the best matching words
    for i in top_10_indexes:
        recommended_words.append(list(df.index)[i])

    return print(recommended_words)


if __name__ == '__main__':
    name = input('Enter the word  to obtain the suggestion: ')
    recommendations(name)
