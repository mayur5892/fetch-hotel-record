package com.tavisca.travel.core.hotelrecordfetch.exception;

import java.util.Date;

public class HotelRecordExceptionResponse {

	private Date timestamp;
	private String message;
	private String details;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(final String details) {
		this.details = details;
	}

	public HotelRecordExceptionResponse(final Date timestamp, final String message, final String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

}
