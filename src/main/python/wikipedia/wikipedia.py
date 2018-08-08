import json
import gensim
import logging


class WikiConstituent:

    __slots__ = ('text', 'head')

    def __init__(self, constituent):
        self.text = constituent['text']
        self.head = constituent['head']

    def __str__(self):
        return 'text: {0}, head: {1}'.format(self.text, self.head)


class WikiFact:

    __slots__ = ('subject', 'relation', 'object')

    def __init__(self, fact):
        self.subject = WikiConstituent(fact['subject'])
        self.relation = WikiConstituent(fact['relation'])
        self.object = WikiConstituent(fact['object'])

    def __str__(self):
        return 'subject: {0}, relation: {1}, object: {2}'.format(self.subject, self.relation, self.object)

    def get_flat_text(self):
        return u'{0} {1} {2}'.format(self.subject.text, self.relation.text, self.object.text)


class WikipediaDocument:

    __slots__ = ('id', 'facts')

    def __init__(self, wikidoc):
        # wikidoc is a string
        json_doc = json.loads(wikidoc)

        self.id = json_doc['wikiID']

        self.facts = []
        for sent in json_doc['sentences']:
            for fact in sent['openFacts']:
                self.facts.append( WikiFact( fact ) )


class WikipediaExtraction:

    def __init__(self, filename):
        self.filename = filename

        n = 1
        logging.info('Loading Wikipedia extraction...')
        self.wikidocs = []
        with gensim.utils.smart_open(filename, encoding='utf-8') as fin:
            for line in fin:
                self.wikidocs.append(WikipediaDocument(line))

                if n % 100000 == 0:
                    logging.info('{0} documents loaded.'.format(n))

                n += 1


class WikipediaExtractionStreaming:

    def __init__(self, filename):
        self.filename = filename


    def stream_n_elements(self, n):
        i = 1
        wikidocs = []
        with gensim.utils.smart_open(self.filename, encoding='utf-8') as fin:
            for line in fin:
                wikidocs.append( WikipediaDocument(line) )


                if i % n == 0:
                    yield wikidocs
                    wikidocs = []

                if i % 10000 == 0:
                    logging.info('{0} documents loaded.'.format(i))

                i += 1

        yield wikidocs



class WikipediaExtractionIterator:

    def __init__(self, filename):
        self.filename = filename

    def __iter__(self):
        n = 1
        with gensim.utils.smart_open(self.filename, encoding='utf-8') as fin:
            for line in fin:
                yield WikipediaDocument(line)
                if n % 10000 == 0:
                    logging.info('{0} documents loaded.'.format(n))

                n += 1
