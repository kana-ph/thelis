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
}
