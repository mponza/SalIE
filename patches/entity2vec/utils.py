import os
import sys

from gensim.models.keyedvectors import KeyedVectors

def load_word2vec_model(filename, mmap=None):
    if filename.endswith('.bin'):
        model = KeyedVectors.load_word2vec_format(filename, binary=True)
    else:
        model = KeyedVectors.load_word2vec_format(filename)

    return model
