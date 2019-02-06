package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;

public class SuppliersMappedIds implements Serializable {

	private static final long serialVersionUID = 1L;

	private String supplierFamily;
	private String supplierHotelId;

	public String getSupplierFamily() {
		return supplierFamily;
	}

	public void setSupplierFamily(final String supplierFamily) {
		this.supplierFamily = supplierFamily;
	}

	public String getSupplierHotelId() {
		return supplierHotelId;
	}

	public void setSupplierHotelId(final String supplierHotelId) {
		this.supplierHotelId = supplierHotelId;
	}

}
