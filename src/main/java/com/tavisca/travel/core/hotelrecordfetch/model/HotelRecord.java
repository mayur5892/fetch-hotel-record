package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class HotelRecord extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String supplierFamily;
	private String name;
	private Double rating;
	private String supplierHotelId;
	private String culture;
	private String hotelCurrencyCode;
	private Geocode geoCode;
	private Contact contact;
	private HotelChain hotelChain;
	private List<SuppliersMappedIds> suppliersMappedIds;
	private List<Image> images;
	private List<Amenity> amenities;
	private List<CheckinOutPolicy> checkinCheckoutPolicy;
	private List<Description> descriptions;

	public List<SuppliersMappedIds> getSuppliersMappedIds() {
		return suppliersMappedIds;
	}

	public void setSuppliersMappedIds(final List<SuppliersMappedIds> suppliersMappedIds) {
		this.suppliersMappedIds = suppliersMappedIds;
	}

	public String getHotelCurrencyCode() {
		return hotelCurrencyCode;
	}

	public void setHotelCurrencyCode(final String hotelCurrencyCode) {
		this.hotelCurrencyCode = hotelCurrencyCode;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(final String culture) {
		this.culture = culture;
	}

	public List<Description> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(final List<Description> descriptions) {
		this.descriptions = descriptions;
	}

	public List<Image> getImages() {
		return images;
	}

	public List<CheckinOutPolicy> getCheckinCheckoutPolicy() {
		return checkinCheckoutPolicy;
	}

	public void setCheckinCheckoutPolicy(final List<CheckinOutPolicy> checkinCheckoutPolicy) {
		this.checkinCheckoutPolicy = checkinCheckoutPolicy;
	}

	public void setImages(final List<Image> images) {
		this.images = images;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(final Contact contact) {
		this.contact = contact;
	}




	public List<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(final List<Amenity> amenities) {
		this.amenities = amenities;
	}

	public Geocode getGeoCode() {
		return geoCode;
	}

	public void setGeoCode(final Geocode geoCode) {
		this.geoCode = geoCode;
	}



	public String getHotelId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getSupplierFamily() {
		return supplierFamily;
	}

	public void setSupplierFamily(final String supplierFamily) {
		this.supplierFamily = supplierFamily;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(final Double rating) {
		this.rating = rating;
	}

	public String getSupplierHotelId() {
		return supplierHotelId;
	}

	public void setSupplierHotelId(final String supplierHotelId) {
		this.supplierHotelId = supplierHotelId;
	}

	public HotelChain getHotelChain() {
		return hotelChain;
	}

	public void setHotelChain(final HotelChain hotelChain) {
		this.hotelChain = hotelChain;
	}

}
