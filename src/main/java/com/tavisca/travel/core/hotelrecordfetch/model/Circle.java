package com.tavisca.travel.core.hotelrecordfetch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Circle {

	private Center center;

	private double raidus;

	public Center getCenter() {
		return center;
	}

	public void setCenter(final Center center) {
		this.center = center;
	}

	@JsonProperty("radiusKm")
	public double getRaidus() {
		return raidus;
	}

	public void setRaidus(final double raidus) {
		this.raidus = raidus;
	}

}
