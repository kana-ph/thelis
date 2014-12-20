package ph.edu.pup.ascii.thelis

import ph.edu.pup.ascii.thelis.common.ThelisService
import ph.edu.pup.ascii.thelis.type.Course

import grails.transaction.Transactional

@Transactional
class ThesisService extends ThelisService {

    AuthorService authorService
    KeywordService keywordService

    public Thesis save(Thesis thesis) {
        return validateAndSave(thesis)
    }

    public Set<Thesis> fetchAll() {
        return Thesis.findAll()
    }

    public Set<Thesis> filterTheses(Map filter) {
        Set<Thesis> result = [] as Set

        if (filter.title) {
            result << Thesis.findAllByTitleIlike("%${filter.title}%")
        }
        if (filter.course) {
            Course course = Course.findByCode(filter.course)
            result << Thesis.findAllByCourse(course)
        }
        if (filter.date) {
            result << Thesis.findAllByPublishDateIlike("%${filter.date}%")
        }
        if (filter.author) {
            List<Author> authorList = authorService.findAuthors(filter.author)

            def thesisCriteria = Thesis.createCriteria()
            def matchedAuthors = thesisCriteria.listDistinct {
                authors { 'in'('id', authorList*.id) }
            }

            result << matchedAuthors
        }
        if (filter.keyword) {
            List<Keyword> keywordList = keywordService.findKeywords(filter.keyword)

            def thesisCriteria = Thesis.createCriteria()
            def matchedKeywords = thesisCriteria.listDistinct {
                keywords { 'in'('id', keywordList*.id) }
            }

            result << matchedKeywords
        }

        return result.flatten()
    }

    public Thesis fetchById(long id) {
        return Thesis.get(id)
    }
}
