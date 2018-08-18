#!/usr/bin/env bash


INPUTDIR=$1
INPUTFORMAT="txt"

OUTPUTDIR=$2
OUTPUTFORMAT="json"

MINIEMODE=$3
if [ -z "$3" ]
then
    echo "MinIE mode non specified."
    return
fi


echo "Running SalIE with input $INPUTDIR and output $OUTPUTDIR with mode $MINIEMODE..."

PARAMS="--inputdir $INPUTDIR --inputformat $INPUTFORMAT "
PARAMS+="--outputdir $OUTPUTDIR  --outputformat $OUTPUTFORMAT "
PARAMS+="--pipeline salie --batch 500 --miniemode $MINIEMODE "
PARAMS+="--graphstructure clique  --weighting embedding  --weightingmodel data/embeddings/$MINIEMODE-wikipedia.glove.500.bin "
PARAMS+="--rankingprior extractionOrder  --alpha 0.1 --iterations 2"

sbt clean compile
sbt "run-main de.mpg.mpi.runners.RunIOPipeline $PARAMS"


echo "SalIE process completed."
