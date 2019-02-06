package com.tavisca.travel.core.hotelrecordfetch.model;

public class HotelRecordRequestParam {

	private Integer limit;
	private Integer offset;
	private String sortBy;
	private String amenityFilterBy;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(final Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(final Integer offset) {
		this.offset = offset;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(final String sortBy) {
		this.sortBy = sortBy;
	}

	public String getAmenityFilterBy() {
		return amenityFilterBy;
	}

	public void setAmenityFilterBy(final String amenityFilterBy) {
		this.amenityFilterBy = amenityFilterBy;
	}

}
