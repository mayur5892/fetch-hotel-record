package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;

public class Attributes implements Serializable {

	private static final long serialVersionUID = 1L;

	private String attributeName;

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(final String attributeName) {
		this.attributeName = attributeName;
	}

}
