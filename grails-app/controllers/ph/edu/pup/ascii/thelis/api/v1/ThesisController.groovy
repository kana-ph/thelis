package ph.edu.pup.ascii.thelis.api.v1

import ph.edu.pup.ascii.thelis.*
import ph.edu.pup.ascii.thelis.common.ThelisController

import org.springframework.http.HttpStatus

class ThesisController extends ThelisController {

    AuthorService authorService
    ThesisService thesisService

    def save() {
        def thesis = createThesisFromJson(request.JSON)

        thesis = thesisService.save(thesis)
        sendResponse(HttpStatus.CREATED, thesis)
    }

    private Thesis createThesisFromJson(Map json) {
        return new Thesis(
            title: json.title,
            publishDate: json.publishDate,
            course: json.course,
            authors: createAuthorSet(json.authors)
        )
    }

    private Set<Author> createAuthorSet(List jsonAuthor) {
        Set authors = []

        jsonAuthor.each { authorName ->
            authors << authorService.fetchAuthorByName(authorName)
        }

        return authors
    }
}
