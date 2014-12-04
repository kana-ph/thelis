import ph.edu.pup.ascii.thelis.marshalling.CustomObjectMarshallers
import ph.edu.pup.ascii.thelis.marshalling.marshaller.*

beans = {
	customObjectMarshallers(CustomObjectMarshallers) {
		marshallers = [
			new ThesisMarshaller()
		]
	}
}
