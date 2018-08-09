SalIE
=====


Setting Up
----------




Embeddings Wikipedia Open Facts via GloVe and Data Compression
---------------------------------------------------------------

**Setting Up.** After you have clone this repository with `--recursive` option you have to install and create a 
[virtualenv](https://docs.python-guide.org/dev/virtualenvs/) environment in the `venv` directory:

    virtualenv venv
    
and install the Python requirements:

    source venv/bin/activate
    pip install -r src/main/python/requirements.txt



**Embeddings Generation & Compression.** Given a file of open facts in JSON format (e.g. `path/to/safe-wikipedia.json`) the embeddings file
(e.g. `path/to/glove.safe.embeddings` with no extension) can be generated from scratch with:

    bash src/main/bash/facts2glove.sh path/to/safe-wikipedia.json path/to/glove.safe.embeddings

For setting up different GloVe parameters check `src/main/bash/facts2glove.sh`.