package ph.edu.pup.ascii.thelis

import ph.edu.pup.ascii.thelis.exception.ValidationException

import grails.buildtestdata.mixin.Build
import grails.test.mixin.*
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ThesisService)
@Build([Author, Thesis])
class ThesisServiceSpec extends Specification {

    def setup() {
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
}
