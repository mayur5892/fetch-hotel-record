package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;

public class Description implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String value;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

}
