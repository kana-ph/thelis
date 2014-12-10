package ph.edu.pup.ascii.thelis

import ph.edu.pup.ascii.thelis.common.ThelisService

import grails.transaction.Transactional

@Transactional
class ThesisService extends ThelisService {

    public Thesis save(Thesis thesis) {
        return validateAndSave(thesis)
    }

    public Thesis fetchById(long id) {
        return Thesis.get(id)
    }
}
