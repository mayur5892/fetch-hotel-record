package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Geocode implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonAlias("lat")
	private Double latitude;
	@JsonAlias("long")
	private Double longitude;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(final Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(final Double longitude) {
		this.longitude = longitude;
	}




}
