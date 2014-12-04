package ph.edu.pup.ascii.thelis.common

import ph.edu.pup.ascii.thelis.*
import ph.edu.pup.ascii.thelis.common.ThelisController
import ph.edu.pup.ascii.thelis.type.ApiError

import org.springframework.http.HttpStatus

class ErrorController extends ThelisController {

	def handle404() {
		sendResponse(HttpStatus.NOT_FOUND, ApiError.URL_NOT_FOUND)
	}

	def handle500() {
		sendResponse(HttpStatus.INTERNAL_SERVER_ERROR, ApiError.INTERNAL_SERVER_ERROR)
	}

}
