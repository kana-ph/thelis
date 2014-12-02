package ph.edu.pup.ascii.thelis

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ThesisFile)
@Build([Thesis])
class ThesisFileSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "save should save a file with path, type, and the thesis it belongs to"() {
        given:
            def thesis = Thesis.build()
            def file = new ThesisFile(
                filePath: 'http://example.com/thelis-storage/myfile.pdf',
                fileType: 'pdf',
                thesis: thesis
            )

        when:
            file.save()

        then:
            null != ThesisFile.get(file.id)
    }
}
