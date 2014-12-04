package ph.edu.pup.ascii.thelis.common

import ph.edu.pup.ascii.thelis.exception.*

import org.springframework.http.HttpStatus

trait ExceptionHandler {

	def handleValidationException(ValidationException e) {
		response.status = HttpStatus.UNPROCESSABLE_ENTITY.value()
		respond e.asMap
	}

}
