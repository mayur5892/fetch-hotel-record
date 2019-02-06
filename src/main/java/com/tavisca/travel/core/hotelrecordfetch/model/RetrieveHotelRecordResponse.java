package com.tavisca.travel.core.hotelrecordfetch.model;

import java.util.List;
import java.util.Map;

import org.springframework.hateoas.ResourceSupport;

public class RetrieveHotelRecordResponse extends ResourceSupport {

	private List<HotelRecord> hotels;
	private Map<String, Map<String, Integer>> filters;
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(final int count) {
		this.count = count;
	}

	public List<HotelRecord> getHotels() {
		return hotels;
	}

	public void setHotels(final List<HotelRecord> hotels) {
		this.hotels = hotels;
	}

	public Map<String, Map<String, Integer>> getFilters() {
		return filters;
	}

	public void setFilters(final Map<String, Map<String, Integer>> filters) {
		this.filters = filters;
	}




}
