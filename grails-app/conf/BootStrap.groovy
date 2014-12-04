import ph.edu.pup.ascii.thelis.marshalling.CustomObjectMarshallers

class BootStrap {

    CustomObjectMarshallers customObjectMarshallers

    def init = { servletContext ->

        customObjectMarshallers.register()
    }

    def destroy = { }
}
