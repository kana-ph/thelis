package ph.edu.pup.ascii.thelis

import ph.edu.pup.ascii.thelis.exception.ValidationException

import grails.buildtestdata.mixin.Build
import grails.test.mixin.*
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ThesisService)
@Build([Author, Keyword, Thesis])
class ThesisServiceSpec extends Specification {

    AuthorService authorService = Mock()
    KeywordService keywordService = Mock()

    def setup() {
        service.authorService = authorService
        service.keywordService = keywordService
    }

    def cleanup() {
    }

    void "save should save a valid thesis object"() {
        given:
            def thesis = new Thesis(course: 'BSIT', title: 'chula', publishDate: 'Dec 2014', authors: [Author.build()])

        expect:
            0 == Thesis.findAll().size()

        when:
            service.save(thesis)

        then:
            1 == Thesis.findAll().size()
    }

    void "save should throw a ValidationException if the thesis obect is invalid"() {
        given:
            def thesis = new Thesis(course: null, title: null, publishDate: null, authors: null)

        when:
            service.save(thesis)

        then:
            thrown ValidationException
    }

    void "save should throw ValidationException if the title is not unique"() {
        given:
            Thesis.build(title: "omg")
            def thesis = new Thesis(title: "omg", course: "BSCS", publishDate: "Jan 2015", authors: [Author.build()])

        expect:
            Thesis.findByTitle("omg")

        when:
            service.save(thesis)

        then:
            thrown ValidationException
    }

    void "fetchById should return an instance of thesis"() {
        given:
            def saved = Thesis.build( course: 'BSIT', title: 'meow', publishDate: 'Dec 2014', authors: [Author.build(name: 'goose')])

        when:
            def thesis = service.fetchById(saved.id)

        then:
            null != thesis
    }

    void "fetchById should return null if the id did not match any saved instance"() {
        when:
            def thesis = service.fetchById(999999L)

        then:
            null == thesis
    }

    void "fetchAll should return a set of all the thesis objects"() {
        given:
            for (i in 1..100) { Thesis.build() }

        when:
            Set allTheses = service.fetchAll()

        then:
            100 == allTheses.size()
    }

    void "filterTheses should return a set of theses with partially matched title if filter has title"() {
        given:
            def filter = [title: 'game']
            def expected = Thesis.build(title: 'the game')

        when:
            def result = service.filterTheses(filter)

        then:
            expected in result
    }

    void "filterTheses should return a set of theses with partially matched date if filter has date"() {
        given:
            def filter = [date: '2014']
            def expected = Thesis.build(publishDate: 'Dec 2014')

        when:
            def result = service.filterTheses(filter)

        then:
            expected in result
    }

    void "filterTheses should return a set of theses that matched a course from filter"() {
        given:
            def filter = [course: 'BSCS']
            def expected = Thesis.build(course: 'BSCS')

        when:
            def result = service.filterTheses(filter)

        then:
            expected in result
    }

    void "filterTheses should return a set of theses that matched at least a name of one of its authors"() {
        given:
            def filter = [author: 'bond']
            def author = Author.build(name: 'James Bond')
            def expected = Thesis.build(authors: [author])

        when:
            authorService.findAuthors(filter.author) >> [author]

            def result = service.filterTheses(filter)

        then:
            expected in result
    }

    void "filterTheses should return a set of theses that matched at least a value of one of its keywords"() {
        given:
            def filter = [keyword: 'roll']
            def kword = Keyword.build(value: 'rickroll')
            def expected = Thesis.build(keywords: [kword])

        when:
            keywordService.findKeywords(filter.keyword) >> [kword]

            def result = service.filterTheses(filter)

        then:
            expected in result
    }
}
