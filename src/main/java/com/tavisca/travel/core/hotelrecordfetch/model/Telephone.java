package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;

public class Telephone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String num;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getNum() {
		return num;
	}

	public void setNum(final String num) {
		this.num = num;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(final String ext) {
		this.ext = ext;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(final String areaCode) {
		this.areaCode = areaCode;
	}
	private String countryCode;
	private String ext;
	private String areaCode;

}
