package ph.edu.pup.ascii.thelis

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Author)
class AuthorSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "save should save author with name and course"() {
        given:
            def author = new Author(name: 'kana', course: 'BSCS')

        when:
            author.save()

        then:
            null != Author.get(author.id)
    }

    @Unroll
    void "save should not save if #condition"() {
        given:
            def author = new Author(name: givenName, course: givenCourse)

        when:
            author.save()

        then:
            null == Author.get(author.id)

        where:
            condition           | givenName   | givenCourse
            'name is null'      | null        | 'BSCS'
            'course is null'    | 'kana'      | null
            'course is invalid' | 'kana'      | 'drug-dealer'
    }
}
