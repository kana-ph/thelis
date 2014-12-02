package ph.edu.pup.ascii.thelis

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Author)
class AuthorSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "save should save author with name"() {
        given:
            def author = new Author(name: 'kana')

        when:
            author.save()

        then:
            null != Author.get(author.id)
    }

    void "save should not save if name is null"() {
        given:
            def author = new Author()

        when:
            author.save()

        then:
            null == Author.get(author.id)
    }
}
