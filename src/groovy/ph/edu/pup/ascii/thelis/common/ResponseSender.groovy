package ph.edu.pup.ascii.thelis.common

import ph.edu.pup.ascii.thelis.type.ApiError

import org.springframework.http.HttpStatus


trait ResponseSender {

	public void send404Response() {
		render status: HttpStatus.NOT_FOUND.value()
	}

	public void send204Response() {
		render status: HttpStatus.NO_CONTENT.value()
	}

	public void sendResponse(HttpStatus status, Object responseObject) {
		response.status = status.value()
		respond responseObject
	}

	public void sendResponse(HttpStatus status, ApiError apiError) {
		response.status = status.value()
		respond apiError.asMap
	}
}
