package com.tavisca.travel.core.hotelrecordfetch.model;

import java.util.ArrayList;
import java.util.List;

public class FilterHotelRecordRequest {


	private Integer minimumRating;
	private List<String> amenities =new ArrayList<String>();
	private List<String> hotelChain=new ArrayList<String>();

	public List<String> getHotelChain() {
		return hotelChain;
	}

	public Integer getMinimumRating() {
		return minimumRating;
	}

	public void setMinimumRating(final Integer minimumRating) {
		this.minimumRating = minimumRating;
	}

	public List<String> getAmenities() {
		return amenities;
	}

}
