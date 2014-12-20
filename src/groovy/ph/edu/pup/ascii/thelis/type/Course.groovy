package ph.edu.pup.ascii.thelis.type

enum Course {
	BSCS('BS in Computer Science'),
	BSIT('BS in Information Technology'),
	BSCOE('BS in Computer Engineering'),
	BSECE('BS in Electronics and Communications Engineering');
	// TODO add more, add all

	String fullName

	public Course(String fullName) {
		this.fullName = fullName
	}

	public String getCode() {
		return this.name()
	}

	public static Course findByCode(String code) {
		return Course.values().find { code.toUpperCase() == it.name() }
	}
}
