SalIE
=====


Setting Up
----------




Compressed Open Facts Embeddings via GloVe
------------------------------------------

**Setting Up.** After you have clone this repository with `--recursive` option you have to install and create a 
[virtualenv](https://docs.python-guide.org/dev/virtualenvs/) environment in the local directory `venv`:

    virtualenv venv
    
and install the Python requirements:

    source venv/bin/activate
    pip install -r src/main/python/requirements.txt
    pip install -r ext/entity2vec/



**Embedding Generation.** Given a file of open facts in Json format (e.g. `path/to/safe-wikipedia.json`) the embeddings file
(e.g. `path/to/glove.safe.embeddings.bin`) can be generated from scratch with:

    bash src/main/bash/facts2glove.sh path/to/safe-wikipedia.json path/to/glove.safe.embeddings.bin

For setting up a different set of parameters just check `src/main/bash/facts2glove.sh`.