package ph.edu.pup.ascii.thelis.exception

import ph.edu.pup.ascii.thelis.type.ApiError

class ValidationException extends RuntimeException {

	Object invalidObject

	ValidationException(String message, Object invalidObject) {
		super(message)
		this.invalidObject = invalidObject
	}

	public Map getAsMap() {
		Map map = ApiError.VALIDATION_ERROR.asMap

		List fieldErrors = []

		invalidObject.errors.fieldErrors.each {
			fieldErrors << [
				field: it.field,
				code: it.code,
				rejectedValue: it.rejectedValue
			]
		}

		map.fieldErrors = fieldErrors
		return map
	}
}
