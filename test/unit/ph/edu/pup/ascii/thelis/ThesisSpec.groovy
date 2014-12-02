package ph.edu.pup.ascii.thelis

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Thesis)
@Build([Author])
class ThesisSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "save should save a thesis with at least title, 1 author, course, and publish date"() {
        given:
            def author = Author.build(name: 'lalat')

            def thesis = new Thesis(title: 'very title', authors: [author] as Set, course: 'BSCS', publishDate: 'Dec 2014')

        when:
            thesis.save()

        then:
            null != Thesis.get(thesis.id)
    }

    void "new thesis object must start with blank abstract"() {
        when:
            def author = Author.build()
            def thesis = new Thesis(title: 'wakawaka', authors: [author] as Set)

        then:
            '' == thesis.thesisAbstract
    }

    @Unroll
    void "save should not save if there is no #field"() {
        given:
            def thesis = new Thesis(title: title, author: author, course: course, publishDate: date)

        when:
            thesis.save()

        then:
            null == Thesis.get(thesis.id)

        where:
            field        || title  | author                      | course | date
            'title'      || null   | [Author.build(name: 'kana')]| 'BSCS' | 'Dec 2014'
            'author'     || 'meow' | null                        | 'BSIT' | 'Jan 2015'
            'course'     || 'wubb' | [Author.build(name: 'kana')]|  null  | 'Feb 2014'
            'publishDate'|| 'deyt' | [Author.build(name: 'kana')]| 'BSCOE'| null
    }
}
