package com.tavisca.travel.core.hotelrecordfetch.model;

import java.util.List;

public class HotelRecordResponse {

	private List<HotelRecord> hotels;

	public List<HotelRecord> getHotels() {
		return hotels;
	}

	public void setHotels(final List<HotelRecord> hotels) {
		this.hotels = hotels;
	}

}
