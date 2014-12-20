package ph.edu.pup.ascii.thelis.api.v1

import ph.edu.pup.ascii.thelis.*
import ph.edu.pup.ascii.thelis.common.ThelisController
import ph.edu.pup.ascii.thelis.type.ApiError

import org.springframework.http.HttpStatus

class ThesisController extends ThelisController {

    AuthorService authorService
    KeywordService keywordService
    ThesisService thesisService

    def show(String id) {

        if(isNumber(id)) {
            def thesis = thesisService.fetchById(id as Long)

            if (thesis) {
                sendResponse(HttpStatus.OK, thesis)
            } else {
                send404Response()
            }
        } else {
            sendResponse(HttpStatus.BAD_REQUEST, ApiError.NUMBER_EXPECTED)
        }
    }

    def search() {
        Set<Thesis> result = [] as Set

        Map filter = fetchUrlParams()
        if (filter) {
            result = thesisService.filterTheses(filter)
        } else {
            result = thesisService.fetchAll()
        }

        sendResponse(result)
    }

    def save() {
        def thesis = createThesisFromJson(request.JSON)

        thesis = thesisService.save(thesis)
        sendResponse(HttpStatus.CREATED, thesis)
    }

    def update(String id) {

        if(isNumber(id)) {
            def thesis = thesisService.fetchById(id as Long)

            if (thesis) {
                updateThesisFromJson(thesis, request.JSON)
                thesisService.save(thesis)

                sendResponse(HttpStatus.OK, thesis)
            } else {
                send404Response()
            }
        } else {
            sendResponse(HttpStatus.BAD_REQUEST, ApiError.NUMBER_EXPECTED)
        }
    }

    private Thesis createThesisFromJson(Map json) {
        def thesis = new Thesis(
            title: json.title,
            publishDate: json.publishDate,
            course: json.course,
            authors: createAuthorSet(json.authors)
        )

        return addOptionalValuesFromJson(thesis, json)
    }

    private Thesis updateThesisFromJson(Thesis thesis, Map json) {
        if (json.containsKey('title')) {
            thesis.title = json.title
        }
        if (json.containsKey('publishDate')) {
            thesis.publishDate = json.publishDate
        }
        if (json.containsKey('course')) {
            thesis.course = json.course
        }
        if (json.containsKey('authors')) {
            thesis.authors = createAuthorSet(json.authors)
        }
        return addOptionalValuesFromJson(thesis, json)
    }

    private Thesis addOptionalValuesFromJson(Thesis thesis, Map json) {
        if (json.containsKey('thesisAbstract')) {
            thesis.thesisAbstract = json.thesisAbstract
        }
        if (json.containsKey('keywords')) {
            thesis.keywords = createKeywordSet(json.keywords)
        }
        return thesis
    }

    private Set<Author> createAuthorSet(List jsonAuthor) {
        Set authors = []

        jsonAuthor.each { authorName ->
            authors << authorService.fetchAuthorByName(authorName)
        }

        return authors
    }

    private Set<Author> createKeywordSet(List jsonKeyword) {
        Set keywords = []

        jsonKeyword.each { keywordValue ->
            keywords << keywordService.fetchKeywordByValue(keywordValue)
        }
        
        return keywords
    }

    private boolean isNumber(String id) {
        def numberPattern = /\d+/

        return (id ==~ numberPattern)
    }

    private Map fetchUrlParams() {
        return params.findAll { !(it.key in ['action', 'controller']) }
    }
}
