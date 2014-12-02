package ph.edu.pup.ascii.thelis.common

import org.springframework.http.HttpStatus

abstract class ThelisController {

	static responseFormats = ['json']

	protected void sendResponse(HttpStatus status, Object responseObject) {
		response.status = status.value()
		respond responseObject
	}
}
