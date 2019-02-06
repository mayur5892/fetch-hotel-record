package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;

public class Amenity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String desc;
	private String groupName;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(final String desc) {
		this.desc = desc;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

}
