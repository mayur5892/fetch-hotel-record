package com.tavisca.travel.core.hotelrecordfetch.model;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonResponse {

	private JsonNode node;

	public JsonNode getNode() {
		return node;
	}

	public void setNode(final JsonNode node) {
		this.node = node;
	}

}
