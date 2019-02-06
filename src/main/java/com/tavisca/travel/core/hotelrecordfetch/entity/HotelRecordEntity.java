package com.tavisca.travel.core.hotelrecordfetch.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "HOTEL_RECORD")
public class HotelRecordEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "HOTEL_ID")
	private String hotelId;

	@Column(name = "HOTEL_LOCATION")
	private String hotelLocation;

	@Column(name = "HOTEL_RATING")
	private double hotelRating;

	@Column(name = "CREATION_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();


	@Column(name = "HOTEL_DATA")
	@Type(type = "JsonUserType")
	private com.tavisca.travel.core.hotelrecordfetch.model.HotelRecord hotelRecord;

	@Column(name = "IS_LATEST")
	private boolean isLatest;

	public com.tavisca.travel.core.hotelrecordfetch.model.HotelRecord getHotelRecord() {
		return hotelRecord;
	}

	public String getHotelId() {
		return hotelId;
	}

	public boolean isIsLatest() {
		return isLatest;
	}

	public double getHotelRating() {
		return hotelRating;
	}

	public String getHotelLocation() {
		return hotelLocation;
	}

	public void setIsLatest(final boolean isLatest) {
		this.isLatest = isLatest;
	}

	public HotelRecordEntity() {

	}

	public Date getCreationDate() {
		return creationDate;
	}





	public Integer getId() {
		return id;
	}


	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof HotelRecordEntity)) {
			return false;
		}
		HotelRecordEntity other = (HotelRecordEntity) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}




}
