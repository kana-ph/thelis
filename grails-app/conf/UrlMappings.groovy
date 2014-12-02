class UrlMappings {

	static mappings = {

        "/"(view:"/index")

		"/api/v1/thesis" (controller: "thesis") {
			action = [POST: "save"]
		}
	}
}
