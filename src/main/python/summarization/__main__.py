import baker
import logging
import json

from evaluation import OpenFactRougeEvaluation


@baker.command
def evaluate(openfacts_json_dir, abstract_dir, performance_outfilename=None):
    rouge_eval = OpenFactRougeEvaluation(openfacts_json_dir, abstract_dir)
    rouge_scores = rouge_eval.evaluate()

    logging.info(json.dumps(rouge_scores))

    if performance_outfilename is not None:
        with open(performance_outfilename, 'w') as fout:
            json.dump(performance_outfilename, fout)


if __name__ == "__main__":
    logging.basicConfig(format='%(asctime)s : %(levelname)s : %(message)s', level=logging.INFO)
    baker.run()