package ph.edu.pup.ascii.thelis.common

import ph.edu.pup.ascii.thelis.exception.ValidationException

abstract class ThelisService {

	public Object validateAndSave(Object object, Map saveArgs = [:]) {
		if (!object.validate()) {
			throw new ValidationException("Invalid $object", object)
		} else {
			return object.save(saveArgs)
		}
	}
}
