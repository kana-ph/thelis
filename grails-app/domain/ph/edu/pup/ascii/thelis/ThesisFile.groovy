package ph.edu.pup.ascii.thelis

class ThesisFile {

    String filePath
    boolean downloadable = true

    static belongsTo = [thesis: Thesis]

    static constraints = { }
}
