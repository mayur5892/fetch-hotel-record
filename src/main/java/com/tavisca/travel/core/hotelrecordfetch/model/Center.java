package com.tavisca.travel.core.hotelrecordfetch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Center {

	private double latitude;

	private double longitude;

	@JsonProperty("lat")
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(final double latitude) {
		this.latitude = latitude;
	}

	@JsonProperty("long")
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(final double longitude) {
		this.longitude = longitude;
	}

}
