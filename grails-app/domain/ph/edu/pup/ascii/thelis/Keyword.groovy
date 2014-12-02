package ph.edu.pup.ascii.thelis

class Keyword {

    String value

    static constraints = {
        value blank: false, unique: true
    }
}
