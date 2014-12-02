package ph.edu.pup.ascii.thelis

import ph.edu.pup.ascii.thelis.type.Course

class Thesis {

    String title
    String thesisAbstract = ''

    String publishDate
    Course course

    static hasMany = [
        authors: Author,
        keywords: Keyword
    ]

    static constraints = {
        thesisAbstract blank: true
        authors nullable: false, minSize: 1
    }

    static mapping = {
        version false

        thesisAbstract type: 'text'
    }
}
