package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;
import java.util.List;

public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;

	public Address getAddress() {
		return address;
	}

	public Contact setAddress(final Address address) {
		this.address = address;
		return this;
	}

	public List<Telephone> getPhones() {
		return phones;
	}

	public void setPhones(final List<Telephone> phones) {
		this.phones = phones;
	}
	private Address address;
	private List<Telephone> phones;

}
