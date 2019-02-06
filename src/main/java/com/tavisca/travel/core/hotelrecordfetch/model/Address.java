package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String line1;
	private String line2;
	private CodeDetails city;

	public String getLine1() {
		return line1;
	}

	public void setLine1(final String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(final String line2) {
		this.line2 = line2;
	}

	public CodeDetails getCity() {
		return city;
	}

	public Address setCity(final CodeDetails city) {
		this.city = city;
		return this;
	}

	public CodeDetails getState() {
		return state;
	}

	public void setState(final CodeDetails state) {
		this.state = state;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}

	private CodeDetails state;
	private String countryCode;
	private String postalCode;

}
