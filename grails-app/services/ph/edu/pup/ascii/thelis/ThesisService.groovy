package ph.edu.pup.ascii.thelis

import ph.edu.pup.ascii.thelis.common.ThelisService

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
            result << Thesis.findAllByCourse(filter.course)
        }
        if (filter.date) {
            result << Thesis.findAllByPublishDate(filter.date)
        }
        if (filter.author) {
            List<Author> authors = authorService.findAuthors(filter.author)
            authors.each { author ->
                result << Thesis.findAll { author in it.authors }
            }
        }
        if (filter.keyword) {
            List<Keyword> keywords = keywordService.findKeywords(filter.keyword)
            keywords.each { keyword ->
                result << Thesis.findAll { keyword in it.keywords }
            }
        }

        return result.flatten()
    }

    public Thesis fetchById(long id) {
        return Thesis.get(id)
    }
}
