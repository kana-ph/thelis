class UrlMappings {

	static mappings = {

        "/"(view:"/index")
		"404"(controller: "error", action: "handle404")
		"500"(controller: "error", action: "handle500")

		"/api/v1/thesis" (controller: "thesis") {
			action = [POST: "save"]
		}
	}
}
