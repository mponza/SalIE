#!/usr/bin/env bash

# If you have the exception "Cannot open exception db file for reading: ..." just run this script for fixing.

cd src/main/python/summarization/tools/ROUGE-1.5.5/data/WordNet-2.0-Exceptions
rm WordNet-2.0.exc.db
./buildExeptionDB.pl . exc WordNet-2.0.exc.db
cd ..
rm WordNet-2.0.exc.db
ln -s WordNet-2.0-Exceptions/WordNet-2.0.exc.db WordNet-2.0.exc.db