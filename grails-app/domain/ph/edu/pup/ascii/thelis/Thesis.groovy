package ph.edu.pup.ascii.thelis

class Thesis {

    String title
    String thesisAbstract = ''

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
