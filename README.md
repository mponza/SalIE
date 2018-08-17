![SalIE](http://pages.di.unipi.it/ponza/public/images/salie/logo.png)

Welcome to SalIE - Salient Information Extraction - a framework that addressed the extraction of salient open facts from arbitrary text.



Setting Up
-----------

Download the pre-crafted embeddings from [link/to/embeddings](http://link/to/embeddings) into
`src/main/resources/embeddings` and then compile the code with:

    sbt compile


Running
-------

Given a data collection stored in `path/to/input/data` folder, where each element is a text file, you can extract its
salient open facts with SalIE by typing:

    src/main/bash/salient-extraction.sh path/to/input/data path/to/output/data
    
where `path/to/output/data` is the folder on which the salient open facts (in JSON format) will be stored.

For a working example just run `src/main/bash/example.sh`.




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




Evaluation
----------

**Setting Up.** Set up your [virtualenv](https://docs.python-guide.org/dev/virtualenvs/) environment and then install the Python requirements:

    pip install -r src/main/python/requirements.txt


Then, you have to configure [pyrouge](https://pypi.org/project/pyrouge/) by creating the file `~/.pyrouge/settings.ini`
with the content:

    [pyrouge settings]
    home_dir = path/to/src/main/python/summarization/tools/ROUGE-1.5.5/
    
    
**Evaluation.** For evaluating a set of extracted facts with respect to documents' abstracts you need to run:

    python src/main/python/summarization evaluate path/to/open/facts/dir path/to/abstracts path/to/output/rouge.json
    
where `path/to/open/facts/dir` is the path to a directory of a set of documents, each one with the following JSON format:


    {
       'docID':        string      id of the document
       'text':         string      content of the document
       'abstract':     string      abstract of the document

       'openfacts':    list        list of open facts

                   [
                       {
                           'text':     string      text of the open fact
                           'score':    float       salience score
                       }
                   ]
    }
    
and the `path/to/abstracts` is the path to a directory of a set of documents, each one containing the document's abstract.




**Known Error (and How to Fix).** Running ROUGE can raise the "Cannot open exception db file for reading" exception. For fix it, just type:

    bash src/main/fixROUGE.sh
    
and then re-run the evaluation script.



Citation and Further Reading
----------------------------

If you find any resource (code or data) of this repository useful, please cite our paper:

> [Marco Ponza](http://pages.di.unipi.it/ponza), [Luciano Del Corro](https://people.mpi-inf.mpg.de/~corrogg/), [Gerhard Waikum](http://people.mpi-inf.mpg.de/~weikum/)
> Facts That Matter
> *In Proceedings of the 2018 Conference of Empirical Methods in Natural Language Processing (EMNLP 2018)


License
-------
The code in this repository has been released under Apache License 2.0.


