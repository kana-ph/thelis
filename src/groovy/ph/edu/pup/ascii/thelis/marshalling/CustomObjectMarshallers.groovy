package ph.edu.pup.ascii.thelis.marshalling

class CustomObjectMarshallers {
	
	List marshallers = []

	def register() {
		marshallers.each{ it.register() }
	}
}
