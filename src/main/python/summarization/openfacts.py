import os
import codecs
import json
import logging
import progressbar


class OpenFactData:

    def __init__(self, openfacts_json_dir, abstract_dir):

        # A document in self.docs is a JSON object in the format:
        #
        #   {
        #       'docID':        string      id of the document
        #       'text':         string      content of the document
        #       'abstract':     string      abstract of the document
        #
        #       'openfacts':    list        list of open facts
        #
        #                       [
        #                           {
        #                               'text':     string      text of the open fact
        #                               'score':    float       salience score
        #                           }
        #                       ]
        #   }


        logging.info('Loading documents from {0}...'.format(openfacts_json_dir))
        bar = progressbar.ProgressBar()

        self.docs = {}      # dictionary of docID -> document

        for filename in bar( os.listdir(openfacts_json_dir) ):
            filename = os.path.join(openfacts_json_dir, filename)

            doc = None
            with codecs.open(filename, 'r', encoding='utf-8') as fin:
                doc = json.load(fin)
                doc['openfacts'] = sorted(doc['openfacts'], key=lambda fact: -fact['score']) # from most to less salient

            self.docs[doc['docID']] = doc


        logging.info('Loading human abstract from {0}...'.format(abstract_dir))
        bar = progressbar.ProgressBar()

        for filename in bar( os.listdir(abstract_dir) ):
            doc_id = os.path.splitext(os.path.basename(filename))[0]

            if doc_id not in self.docs:
                continue

            filename = os.path.join(abstract_dir, filename)
            with codecs.open(filename, 'r', encoding='utf-8') as fin:
                self.docs[doc_id]['abstract'] = fin.read()


        # all documents must have an abstract
        assert len([doc_id for doc_id in self.docs if 'abstract' in self.docs[doc_id] ]) == len(self.docs)

        logging.info('Loaded a total of {0} documents'.format(len(self.docs)))



    def get_docs_from_n_facts(self, n):
        """
        Returns a list of documents where only the top-n facts are reported.
        Each document in the list has the following format:

            {
                'docID':        string      id of the document
                'abstract':     string      abstract of the document
                'summary':      string      summary generated with the top-n open facts
            }

        """

        top_fact_docs = []
        for doc_id in self.docs:

            openfacts = self.docs[doc_id]['openfacts']
            top_openfacts = openfacts[0:n]
            summary = '\n'.join([f['text'] for f in top_openfacts])

            top_fact_docs.append({'docID': doc_id, 'abstract': self.docs[doc_id]['abstract'], 'summary': summary})

        return top_fact_docs