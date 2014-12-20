package ph.edu.pup.ascii.thelis

import grails.transaction.Transactional

@Transactional
class AuthorService {

    public Author fetchAuthorByName(String name) {
        def author = Author.findByName(name)

        return author ?: new Author(name: name).save()
    }

    public List<Author> findAuthors(String name) {
        return Author.findAllByNameIlike("%$name%")
    }
}
