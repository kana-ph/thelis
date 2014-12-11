package ph.edu.pup.ascii.thelis.type

enum ApiError {
	URL_NOT_FOUND('URL not found'),
	INTERNAL_SERVER_ERROR('Something went wrong at the server.'),
	VALIDATION_ERROR('Failed to save entity due to validation error');

	private String message

	ApiError(String message) {
		this.message = message
	}

	public Map getAsMap() {
		return [
			code: name(),
			message: message
		]
	}
}
