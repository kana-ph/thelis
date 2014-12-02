package ph.edu.pup.ascii.thelis

import ph.edu.pup.ascii.thelis.common.ThelisController

import org.springframework.http.HttpStatus

class ThesisController extends ThelisController {

    def save() {
        // def thesis = createThesisFromJson(request.JSON)
        //
        // thesis = thesisService.save(thesis)
        sendResponse(HttpStatus.OK, request.JSON)
    }
}
