package ph.edu.pup.ascii.thelis

import grails.transaction.Transactional

@Transactional
class KeywordService {

    public Keyword fetchKeywordByValue(String value) {
        def keyword = Keyword.findByValue(value)

        return keyword ?: new Keyword(value: value).save()
    }

    public List<Keyword> findKeywords(String value) {
        return Keyword.findAllByValueIlike("%$value%")
    }
}
