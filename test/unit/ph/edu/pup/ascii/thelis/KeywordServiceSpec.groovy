package ph.edu.pup.ascii.thelis

import grails.buildtestdata.mixin.Build
import grails.test.mixin.*
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(KeywordService)
@Build([Keyword])
class KeywordServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "fetchKeywordByValue should fectch a keyword object from db"() {
        given:
            def value = 'astrophysics'
            Keyword.build(value: value)

        when:
            def kword = service.fetchKeywordByValue(value)

        then:
            value == kword.value
    }

    void "fetchKeywordByValue should create a keyword entity if the value does not exist"() {
        expect:
            0 == Keyword.findAll().size()

        when:
            def value = 'acoustics'
            def kword = service.fetchKeywordByValue(value)

        then:
            1 == Keyword.findAll().size()
            value == kword.value
    }
}
