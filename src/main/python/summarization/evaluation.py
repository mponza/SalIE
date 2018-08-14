import os
import codecs
import tempfile
import logging
import shutil

from pyrouge import Rouge155

from openfacts import OpenFactData


N_FACTS = [1, 2, 3, 4, 5] # [1, 2, 3, 4, 5]           # computes rouge scores with top-N_FACTS
TMP_ROUGE_DIR = './data/tmp/rouge'  # for tmp rouge files


class OpenFactRougeEvaluation:

    def __init__(self, openfact_json_dir, abstract_dir):
        self.data = OpenFactData(openfact_json_dir, abstract_dir)

        self.tmp_dir = self._set_rogue_tmp_dir()    # it will contain machine and abstract dirs


    def _set_rogue_tmp_dir(self):
        global TMP_ROUGE_DIR

        if not os.path.exists( TMP_ROUGE_DIR ):
            os.makedirs( TMP_ROUGE_DIR )

        tempfile.tempdir = TMP_ROUGE_DIR
        return TMP_ROUGE_DIR


    def evaluate(self):
        global N_FACTS

        rouge_scores = []

        for n in N_FACTS:
            logging.info('Evaluating open facts summaries with {0} facts...'.format(n))
            docs = self.data.get_docs_from_n_facts(n)
            scores = self._compute_rouge_scores(docs)
            rouge_scores.append( {'n_facts': n, 'rouge-scores': scores} )

        return rouge_scores


    def _compute_rouge_scores(self, docs):
        logging.info('Setting up ROUGE evaluation package...')

        r = Rouge155()

        logging.info('Cleaning old temporary directories...')

        if os.path.exists(self.tmp_dir):
            shutil.rmtree(self.tmp_dir)
            os.makedirs(self.tmp_dir)

        # configuration rouge system_dir
        sysdir = os.path.join(self.tmp_dir, 'machine')
        if not os.path.exists(sysdir):
            os.makedirs(sysdir)
        r.system_dir = sysdir

        # configuration rouge model_dir
        modeldir = os.path.join( self.tmp_dir, 'abstract')
        if not os.path.exists(modeldir):
            os.makedirs(modeldir)
        r.model_dir = modeldir


        logging.info('Writing summaries and abstracts for evaluation...')

        # write summaries and abstracts into sysdir and modeldir, respectively
        for doc in docs:
            with codecs.open( os.path.join(sysdir, doc['docID'] + '.txt'), 'w', encoding='utf-8' ) as fout:
                fout.write(doc['summary'])

            with codecs.open( os.path.join(modeldir, doc['docID'] + '.txt'), 'w', encoding='utf-8' ) as fout:
                fout.write(doc['abstract'])


        logging.info('Computing ROUGE scores...')

        r.system_filename_pattern = '(\d+).txt'
        r.model_filename_pattern = '#ID#.txt'

        stemmed_nostopwords = "-e {0} -c 95 -2 -1 -U -n 2 -a -s -w 1.2".format(r._data_dir)
        output = r.convert_and_evaluate(rouge_args=stemmed_nostopwords)
        d = r.output_to_dict(output)

        return {
            '1-rouge-f1': d['rouge_1_f_score'],
            '1-rouge-prec': d['rouge_1_precision'],
            '1-rouge-rec': d['rouge_1_recall'],

            '2-rouge-f1': d['rouge_2_f_score'],
            '2-rouge-prec': d['rouge_2_precision'],
            '2-rouge-rec': d['rouge_2_recall'],

            'l-rouge-f1': d['rouge_l_f_score'],
            'l-rouge-prec': d['rouge_l_precision'],
            'l-rouge-rec': d['rouge_l_recall'],

            'w_1.2-rouge-f1': d['rouge_w_1.2_f_score'],
            'w_1.2-rouge-prec': d['rouge_w_1.2_precision'],
            'w_1.2-rouge-rec': d['rouge_w_1.2_recall'],

            's-rouge-f1': d['rouge_s*_f_score'],
            's-rouge-prec': d['rouge_s*_precision'],
            's-rouge-rec': d['rouge_s*_recall'],

            'su-rouge-f1': d['rouge_su*_f_score'],
            'su-rouge-prec': d['rouge_su*_precision'],
            'su-rouge-rec': d['rouge_su*_recall']
        }