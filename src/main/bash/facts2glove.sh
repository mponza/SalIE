#!/usr/bin/env bash



# Generates embeddings from WIKI_JSON_FILENAME to EMBEDDING_DIR



#
# Configurable Parameters

GLOVE_THREADS=8
SIZE=400
GLOVE_MEMORY=2.0



#
# Directories

GLOVE_DIR="ext/GloVe"

WIKI_JSON_FILENAME="$1"     # e.g. "data/wikipedia/agg-wikipedia.json"          change me with another filename, if needed
EMBEDDING_FILENAME="$2"     # e.g. "data/embeddings/agg-wikipedia.glove.400"    (no extension)

TMP_DIR="./data/tmp"                  # wikipedia txt, vocabulary & other temporary data



#
# Support Functions

function flat_wikipedia_facts {
    CMD="python src/main/python/wikipedia flat_wikipedia_facts $1 $2"
    eval $CMD
}



#
# Setting up

source venv/bin/activate
# pip install -r src/main/python/requirements.txt
mkdir -p $TMP_DIR



#
# Flattering json Wikipedia into a textual file of facts

WIKI_TXT_FILENAME="$TMP_DIR/$(echo "$WIKI_JSON_FILENAME" | sed "s/.*\///").txt"

if [ ! -f "$WIKI_TXT_FILENAME" ]; then
    echo "Flattering Wikipedia json into unique file of textual facts..."
    echo "$WIKI_JSON_FILENAME -> $WIKI_TXT_FILENAME"
    flat_wikipedia_facts $WIKI_JSON_FILENAME $WIKI_TXT_FILENAME
fi



#
# Creation of GloVe embeddings

#make -C $GLOVE_DIR

CORPUS="$WIKI_TXT_FILENAME"
VOCAB_FILE="$TMP_DIR/vocab.txt"
COOCCURRENCE_FILE="$TMP_DIR/cooccurrence.bin"
COOCCURRENCE_SHUF_FILE="$TMP_DIR/cooccurrence.shuf.bin"
BUILDDIR="$GLOVE_DIR/build"
VERBOSE=2
MEMORY=$GLOVE_MEMORY
VOCAB_MIN_COUNT=5
MAX_ITER=15
WINDOW_SIZE=15
BINARY=0
NUM_THREADS=$GLOVE_THREADS
X_MAX=10

$BUILDDIR/vocab_count -min-count $VOCAB_MIN_COUNT -verbose $VERBOSE < $CORPUS > $VOCAB_FILE
$BUILDDIR/cooccur -memory $MEMORY -vocab-file $VOCAB_FILE -verbose $VERBOSE -window-size $WINDOW_SIZE < $CORPUS > $COOCCURRENCE_FILE
$BUILDDIR/shuffle -memory $MEMORY -verbose $VERBOSE < $COOCCURRENCE_FILE > $COOCCURRENCE_SHUF_FILE

EMBEDDING_DIR=$(dirname "$EMBEDDING_FILENAME")
if [ ! -f "$EMBEDDING_DIR" ]; then
    mkdir -p $EMBEDDING_DIR
fi

$BUILDDIR/glove -save-file $EMBEDDING_FILENAME -threads $NUM_THREADS -input-file $COOCCURRENCE_SHUF_FILE -x-max $X_MAX -iter $MAX_ITER -vector-size $SIZE -binary $BINARY -vocab-file $VOCAB_FILE -verbose $VERBOSE



#
# Gensim format, so it can be easily compressed

python -m gensim.scripts.glove2word2vec --input "$EMBEDDING_FILENAME.txt" --output "$EMBEDDING_FILENAME.txt.tmp"
rm "$EMBEDDING_FILENAME.txt"
mv "$EMBEDDING_FILENAME.txt.tmp" "$EMBEDDING_FILENAME.txt"



#
# Compressing embeddings

# bash src/main/patch.sh    # be sure that *.py files in entity2vec have been patched

./ext/entity2vec/model_quantization.py quant "$EMBEDDING_FILENAME.txt"  $EMBEDDING_FILENAME

sbt clean
sbt compile
sbt "run-main it.cnr.isti.hpc.Word2VecCompress $EMBEDDING_FILENAME.e0.100.tr.txt $EMBEDDING_FILENAME.bin"

rm "$EMBEDDING_FILENAME".e0.100.*  # tr.model tr.model.syn0.npy tr.txt
rm "$EMBEDDING_FILENAME".txt       # uncompressed embedding file



#
# Clean temporary files

# rm -rf data/tmp