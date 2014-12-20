package ph.edu.pup.ascii.thelis.api.v1

import ph.edu.pup.ascii.thelis.*
import ph.edu.pup.ascii.thelis.exception.ValidationException

import grails.buildtestdata.mixin.Build
import grails.test.mixin.*
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll

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

    void "show should respond with NOT_FOUND if there is no thesis matched the id"() {
        given:
            def id = 42
            thesisService.fetchById(id) >> null
        when:
            controller.show("$id")

        then:
            HttpStatus.NOT_FOUND.value() == response.status
    }

    void "show should respond with BAD_REQUEST if the id is not a number"() {
        when:
            controller.show("woof-woof")
        then:
            HttpStatus.BAD_REQUEST.value() == response.status
    }

    void "update should respond with NOT_FOUND if there is no thesis matched the id"() {
        given:
            def id = 13
            thesisService.fetchById(id) >> null
        when:
            controller.update("$id")

        then:
            HttpStatus.NOT_FOUND.value() == response.status
    }

    void "update should respond with OK if the thesis is updated"() {
        given:
            def thesis = Thesis.build()
            thesisService.fetchById(thesis.id) >> thesis
            thesisService.save(thesis) >> thesis

        when:
            controller.update("${thesis.id}")

        then:
            HttpStatus.OK.value() == response.status
    }

    void "update should respond with BAD_REQUEST if the id is not a number"() {
        when:
            controller.update("meow meow")
        then:
            HttpStatus.BAD_REQUEST.value() == response.status
    }

    void "update should respond with UNPROCESSABLE_ENTITY if the thesis is invalid"() {
        given:
            def thesis = Thesis.build()
            thesisService.fetchById(thesis.id) >> thesis
            thesisService.save(thesis) >> { throw new ValidationException('Fake error', thesis) }

        when:
            controller.update("${thesis.id}")

        then:
            HttpStatus.UNPROCESSABLE_ENTITY.value() == response.status
    }

    void "search should invoke fetchAll() if there is no url params"() {
        given:
            params << [:] as Map

        when:
            controller.search()

        then:
            1 * thesisService.fetchAll()
    }

    void "search should invoke filterTheses() if there are url params"() {
        given:
            Map urlParams = [title: 'the', keyword: 'game']
            params << urlParams

        when:
            controller.search()

        then:
            1 * thesisService.filterTheses(_)
    }
}
