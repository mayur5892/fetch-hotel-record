package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;

public class CodeDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public CodeDetails setName(final String name) {
		this.name = name;
		return this;
	}

}
