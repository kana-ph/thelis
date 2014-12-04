package ph.edu.pup.ascii.thelis

import grails.buildtestdata.mixin.Build
import grails.test.mixin.*
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(AuthorService)
@Build([Author])
class AuthorServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "fetchAuthorByName should fetch an author object from the db"() {
        given:
            def name = 'doge'
            Author.build(name: name)

        when:
            def author = service.fetchAuthorByName(name)

        then:
            name == author.name
    }

    void "fetchAuthorByName should create an author object if the author's name does not exist in the db"() {
        expect:
            0 == Author.findAll().size()

        when:
            def name = 'cate'
            def author = service.fetchAuthorByName(name)

        then:
            1 == Author.findAll().size()
            name == author.name
    }
}
