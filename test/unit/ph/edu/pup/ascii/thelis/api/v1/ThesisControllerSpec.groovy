package ph.edu.pup.ascii.thelis.api.v1

import ph.edu.pup.ascii.thelis.*
import ph.edu.pup.ascii.thelis.exception.ValidationException

import grails.buildtestdata.mixin.Build
import grails.test.mixin.*
import org.springframework.http.HttpStatus
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ThesisController)
@Build([Thesis])
class ThesisControllerSpec extends Specification {

    AuthorService authorService = Mock()
    ThesisService thesisService = Mock()

    def setup() {
        controller.authorService = authorService
        controller.thesisService = thesisService
    }

    def cleanup() {
    }

    void "save should save a thesis using a service method"() {
        when:
            controller.save()

        then:
            1 * thesisService.save(_)
    }

    void "save should respond with CREATED if the request is valid"() {
        given:
            thesisService.save(_) >> Thesis.build()

        when:
            controller.save()

        then:
            HttpStatus.CREATED.value() == response.status
    }

    void "save should respond with UNPROCESSABLE_ENTITY if the request is invalid"() {
        given:
            thesisService.save(_) >> { throw new ValidationException('Fake error', Thesis.build()) }

        when:
            controller.save()

        then:
            HttpStatus.UNPROCESSABLE_ENTITY.value() == response.status
    }

    void "show should respond with OK and the thesis object if an entity matched the id"() {
        given:
            def thesis = Thesis.build()
            thesisService.fetchById(thesis.id) >> thesis

        when:
            controller.show("${thesis.id}")

        then:
            HttpStatus.OK.value() == response.status
            thesis.id == response.json.id
    }

    void "show should respond with 404 if there is no thesis matched the id"() {
        given:
            thesisService.fetchById(_) >> null
        when:
            controller.show("42")

        then:
            HttpStatus.NOT_FOUND.value() == response.status
    }
}
