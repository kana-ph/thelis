package ph.edu.pup.ascii.thelis.marshalling.marshaller

import ph.edu.pup.ascii.thelis.Thesis

import grails.converters.JSON

class ThesisMarshaller {

	void register() {
		JSON.registerObjectMarshaller(Thesis) { Thesis thesis ->
			return [
				id: thesis.id,
				title: thesis.title,
				thesisAbstract: thesis.thesisAbstract,
				publishDate: thesis.publishDate,
				course: thesis.course.code,
				authors: thesis.authors*.name,
				keywords: thesis.keywords*.value
			]
		}
	}
}
