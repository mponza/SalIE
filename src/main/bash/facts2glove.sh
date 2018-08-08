#!/usr/bin/env bash

GLOVE_DIR="somewhere/glovedir"
TMP_DIR="somewhere/data/tmp" # flat wikipedia json, vocabulary & other stuff
SIZE=400

WIKI_FILENAME="somewhere/agg-wikipedia.json"
EMB_FILENAME="somewhere/embfilename.txt"



# WikipediaFilename


source /var/tmp/mponza/Developer/Ambiverse/entity-linking/venv/bin/activate


function merge_wikifacts {
    DIR=$1
    CMD="python src/main/python/wikipedia/minie merge $DIR"
    eval $CMD
}


function flat_wikifacts {
    CMD="python src/main/python/wikipedia/minie flat_minie_wikipedia $1 $2"
    eval $CMD
}



for MODALITY  in "${MODALITIES[@]}"
do

    #
    # Standard GloVe

    WIKIFACTS="$WIKIMINIE/$MODALITY-wikipedia.json"
    if [ ! -f "$WIKIFACTS" ]; then
        echo "Merging different Wikipedia documents into unique file..."
        WIKIDOCS="$WIKIMINIE/$MODALITY"
        merge_wikifacts $WIKIDOCS
    fi

    FLAT_WIKIFACTS="$WIKIMINIE/flat-$MODALITY-wikipedia.txt"
    if [ ! -f "$FLAT_WIKIFACTS" ]; then
        echo "Flattering Wikipedia into unique file of facts..."
        flat_wikifacts $WIKIFACTS $FLAT_WIKIFACTS
    fi


    cd "$GLOVEDIR"

    CORPUS="$FLAT_WIKIFACTS"
    VOCAB_FILE=vocab.txt
    COOCCURRENCE_FILE=cooccurrence.bin
    COOCCURRENCE_SHUF_FILE=cooccurrence.shuf.bin
    BUILDDIR=build
    SAVE_FILE=vectors
    VERBOSE=2
    MEMORY=300.0  # increase me
    VOCAB_MIN_COUNT=5
    MAX_ITER=15
    WINDOW_SIZE=15
    BINARY=2
    NUM_THREADS=30
    X_MAX=10

    echo "$ $BUILDDIR/vocab_count -min-count $VOCAB_MIN_COUNT -verbose $VERBOSE < $CORPUS > $VOCAB_FILE"
    $BUILDDIR/vocab_count -min-count $VOCAB_MIN_COUNT -verbose $VERBOSE < $CORPUS > $VOCAB_FILE
    echo "$ $BUILDDIR/cooccur -memory $MEMORY -vocab-file $VOCAB_FILE -verbose $VERBOSE -window-size $WINDOW_SIZE < $CORPUS > $COOCCURRENCE_FILE"
    $BUILDDIR/cooccur -memory $MEMORY -vocab-file $VOCAB_FILE -verbose $VERBOSE -window-size $WINDOW_SIZE < $CORPUS > $COOCCURRENCE_FILE
    echo "$ $BUILDDIR/shuffle -memory $MEMORY -verbose $VERBOSE < $COOCCURRENCE_FILE > $COOCCURRENCE_SHUF_FILE"
    $BUILDDIR/shuffle -memory $MEMORY -verbose $VERBOSE < $COOCCURRENCE_FILE > $COOCCURRENCE_SHUF_FILE


    for VECTOR_SIZE in "${SIZES[@]}"
        do

            if [ ! -f "$VECTORDIR" ]; then
                mkdir -p $VECTORDIR
            fi

            SAVE_FILE="$VECTORDIR/w2v.glove.$MODALITY.default.$VECTOR_SIZE"

            echo "$ $BUILDDIR/glove -save-file $SAVE_FILE -threads $NUM_THREADS -input-file $COOCCURRENCE_SHUF_FILE -x-max $X_MAX -iter $MAX_ITER -vector-size $VECTOR_SIZE -binary $BINARY -vocab-file $VOCAB_FILE -verbose $VERBOSE"
            $BUILDDIR/glove -save-file $SAVE_FILE -threads $NUM_THREADS -input-file $COOCCURRENCE_SHUF_FILE -x-max $X_MAX -iter $MAX_ITER -vector-size $VECTOR_SIZE -binary $BINARY -vocab-file $VOCAB_FILE -verbose $VERBOSE

            cd $SRC

            echo "Adding extra information to file"
            CMD="python src/main/python/wikipedia/minie add_vector_information $SAVE_FILE.txt"
            eval $CMD

            cd "$GLOVEDIR"

    done

    cd "$SRC"





    #
    # Co-occurrence customization of GloVe

    for ALGO in "${ALGOS[@]}"
    do

        # cooccurrence matrix generation

        WIKIFACTS="$WIKIMINIE/$MODALITY-wikipedia.json"  # unique wikipedia file

#        if [ ! -f "$WIKIFACTS" ]; then
#            echo "Merging different Wikipedia documents into unique file..."
#            WIKIDOCS="$WIKIMINIE/$MODALITY"
#            merge_wikifacts $WIKIDOCS
#        fi


        echo "Generating GloVe resources..."
        VOCABDIR="$VOCAB/$MODALITY/$ALGO"  # where save vocabulary and coocurrence matrix of facts
        if [ ! -f "$VOCABDIR" ]; then
            CMD="python src/main/python/wikipedia/minie generate_words_vocabulary $WIKIFACTS $VOCABDIR $ALGO"
            eval $CMD
        fi


        # running glove

        cd "$GLOVEDIR"

        VOCAB_FILE="$VOCAB/$MODALITY/$ALGO/vocabulary.txt"
        COOCCURRENCE_FILE="$VOCAB/$MODALITY/$ALGO/cooccurrence.bin"
        COOCCURRENCE_SHUF_FILE="$VOCAB/$MODALITY/$ALGO/cooccurrence.shuf.bin"
        BUILDDIR=build
        VERBOSE=2
        MEMORY=300.0  # increase me
        VOCAB_MIN_COUNT=5
        MAX_ITER=15
        WINDOW_SIZE=15
        BINARY=2
        NUM_THREADS=30
        X_MAX=10


        echo "$ $BUILDDIR/shuffle -memory $MEMORY -verbose $VERBOSE < $COOCCURRENCE_FILE > $COOCCURRENCE_SHUF_FILE"
        $BUILDDIR/shuffle -memory $MEMORY -verbose $VERBOSE < $COOCCURRENCE_FILE > $COOCCURRENCE_SHUF_FILE

        for VECTOR_SIZE in "${SIZES[@]}"
        do

            if [ ! -f "$VECTORDIR" ]; then
                mkdir -p $VECTORDIR
            fi

            SAVE_FILE="$VECTORDIR/w2v.glove.$MODALITY.$ALGO.$VECTOR_SIZE"
            echo "$ $BUILDDIR/glove -save-file $SAVE_FILE -threads $NUM_THREADS -input-file $COOCCURRENCE_SHUF_FILE -x-max $X_MAX -iter $MAX_ITER -vector-size $VECTOR_SIZE -binary $BINARY -vocab-file $VOCAB_FILE -verbose $VERBOSE"
            $BUILDDIR/glove -save-file $SAVE_FILE -threads $NUM_THREADS -input-file $COOCCURRENCE_SHUF_FILE -x-max $X_MAX -iter $MAX_ITER -vector-size $VECTOR_SIZE -binary $BINARY -vocab-file $VOCAB_FILE -verbose $VERBOSE

            cd $SRC

            echo "Adding extra information to file"
            CMD="python src/main/python/wikipedia/minie add_vector_information $SAVE_FILE.txt"
            eval $CMD

            cd "$GLOVEDIR"

        done

        cd "$SRC"

    done

done