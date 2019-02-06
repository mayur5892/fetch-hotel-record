package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;
import java.util.List;

public class CheckinOutPolicy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String inTime;
	private String outTime;
	private List<String> days;

	public String getInTime() {
		return inTime;
	}

	public void setInTime(final String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(final String outTime) {
		this.outTime = outTime;
	}

	public List<String> getDays() {
		return days;
	}

	public void setDays(final List<String> days) {
		this.days = days;
	}

}
