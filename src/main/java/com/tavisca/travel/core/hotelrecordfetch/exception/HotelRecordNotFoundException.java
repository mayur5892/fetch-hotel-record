package com.tavisca.travel.core.hotelrecordfetch.exception;

public class HotelRecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HotelRecordNotFoundException(final String message) {
		super(message);
	}

}
