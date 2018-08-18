#!/usr/bin/env bash


PIPELINE="pipeline-salie"

INPUTDIR="/home/ponza/Developer/datasets/factsalience/NYT50/bin"
INPUTFORMAT="bin"

OUTPUTDIR="/home/ponza/Developer/datasets/factsalience/NYT50/$PIPELINE-json"
OUTPUTFORMAT="json"

MINIEMODE="agg"


# runs pipeline

PARAMS="--inputdir $INPUTDIR --inputformat $INPUTFORMAT "
PARAMS+="--outputdir $OUTPUTDIR  --outputformat $OUTPUTFORMAT "
PARAMS+="--pipeline $PIPELINE --batch 500 --miniemode $MINIEMODE "

PARAMS+="--graphstructure clique  --weighting embedding  --weightingmodel data/embeddings/agg-wikipedia.glove.500.bin "
PARAMS+="--rankingprior extractionOrder  --alpha 0.1 --iterations 2"


sbt clean compile
sbt "run-main de.mpg.mpi.runners.RunIOPipeline $PARAMS"


# runs evaluation

source ~/venv/bin/activate
ABSTRACTDIR="/home/ponza/Developer/datasets/factsalience/NYT50/abstracts"

bash src/main/bash/fixROUGE.sh

CMD="python src/main/python/summarization evaluate $OUTPUTDIR $ABSTRACTDIR $PIPELINE-rouge.json"
eval $CMD