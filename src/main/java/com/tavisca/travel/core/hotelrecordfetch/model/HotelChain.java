package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;

public class HotelChain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HotelChain() {

	}

	private String name;
	private String logoUrl;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public HotelChain(final String name, final String logoUrl) {
		super();
		this.name = name;
		this.logoUrl = logoUrl;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(final String logoUrl) {
		this.logoUrl = logoUrl;
	}

}
