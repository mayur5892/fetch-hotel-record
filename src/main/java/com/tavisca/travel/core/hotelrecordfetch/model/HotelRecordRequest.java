package com.tavisca.travel.core.hotelrecordfetch.model;

import java.util.ArrayList;
import java.util.List;

public class HotelRecordRequest {

	private GeoRegion geoRegion;
	private List<String> supplierFamilies = new ArrayList<>();

	public List<String> getSupplierFamilies() {
		return supplierFamilies;
	}

	public void setSupplierFamilies(final List<String> supplierFamilies) {
		this.supplierFamilies = supplierFamilies;
	}

	public GeoRegion getGeoRegion() {
		return geoRegion;
	}

	public void setGeoRegion(final GeoRegion geoRegion) {
		this.geoRegion = geoRegion;
	}

}
