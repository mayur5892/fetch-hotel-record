package com.tavisca.travel.core.hotelrecordfetch.model;

public class HotelRecordRequestParam {

	private Integer limit;
	private Integer offset;
	private String sortBy;
	private String amenityFilterBy;
	private Double radius;

	public HotelRecordRequestParam(final Integer limit, final Integer offset, final String sortBy, final String amenityFilterBy) {
		this.limit = limit;
		this.offset = offset;
		this.sortBy = sortBy;
		this.amenityFilterBy = amenityFilterBy;
	}

	public void setRadius(final Double radius) {
		this.radius = radius;
	}

	public Integer getLimit() {
		return limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public String getSortBy() {
		return sortBy;
	}

	public String getAmenityFilterBy() {
		return amenityFilterBy;
	}

	public Double getRadius() {
		return radius;
	}
}
