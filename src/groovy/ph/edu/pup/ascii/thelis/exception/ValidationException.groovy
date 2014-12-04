package ph.edu.pup.ascii.thelis.exception

class ValidationException extends RuntimeException {

	Object invalidObject

	ValidationException(String message, Object invalidObject) {
		super(message)
		this.invalidObject = invalidObject
	}
}
