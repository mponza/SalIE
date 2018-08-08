import os
import string
import baker
import codecs
import gensim
import logging
import progressbar

from wikipedia import WikipediaExtraction



@baker.command
def flat_wikipedia_facts(json_wikipedia, outfilename):
    """
    Transforms json_wikipedia into a textual documents with only facts.

    :param json_wikipedia: filename of Wikipedia extracted facts in json format
    :param outfilename:  filename of raw textual Wikipedia facts
    """


    print json_wikipedia
    print outfilename
    print "====="

    #
    # Utils

    def is_number(s):
        try:
            float(s)
            return True
        except ValueError:
            pass
        try:
            import unicodedata
            unicodedata.numeric(s)
            return True
        except (TypeError, ValueError):
            pass

        return False

    def clean_text(text):
        words = []
        for w in text.split():
            w = w.encode('utf-8').translate(None, string.punctuation)
            w = gensim.utils.to_unicode( w.lower() )
            if w == u'' or is_number(w):
                continue
            words.append(w)

        return u' '.join(words)


    #
    # Main

    logging.info('Wikipedia facts into single flat textual document...')

    outdir = os.path.dirname(outfilename)
    if not os.path.isdir(outdir):
        os.makedirs(outdir)

    wikipedia = WikipediaExtraction(json_wikipedia)
    with codecs.open(outfilename, 'w', encoding='utf-8') as fout:

        bar = progressbar.ProgressBar()
        for wikidoc in bar( wikipedia.wikidocs ):

            for fact in wikidoc.facts:
                text = clean_text(fact.get_flat_text())
                fout.write( text + u'\n' )



if __name__ == "__main__":
    logging.basicConfig(format='%(asctime)s : %(levelname)s : %(message)s', level=logging.INFO)
    baker.run()