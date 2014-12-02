package ph.edu.pup.ascii.thelis

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Keyword)
class KeywordSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "save should save a keyword given with a value"() {
        given:
            def kword = new Keyword(value: 'AI')

        when:
            kword.save()

        then:
            null != Keyword.get(kword.id)
    }

    void "save should not save a keyword if the value is not unique"() {
        given:
            def commonValue = 'neuro-linker'
            def firstKeyword = new Keyword(value: commonValue).save(flush: true)

            def secondKeyword = new Keyword(value: commonValue)

        when:
            secondKeyword.save()

        then:
            null == Keyword.get(secondKeyword.id)
    }

    @Unroll
    void "save should not save a keyword if the value is #what"() {
        given:
            def kword = new Keyword(value: value)

        when:
            kword.save()

        then:
            null == Keyword.get(kword.id)

        where:
            what    | value
            'null'  | null
            'blank' | ''
    }
}
