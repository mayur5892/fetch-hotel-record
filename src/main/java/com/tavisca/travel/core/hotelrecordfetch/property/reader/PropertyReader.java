package com.tavisca.travel.core.hotelrecordfetch.property.reader;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "hotel.record.fetch.api")
@PropertySource("classpath:hotelconfig.properties")
public class PropertyReader {

	private Integer limit;
	private String amenityfilterby;
	private String sortby;
	private Double radius;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(final Integer limit) {
		this.limit = limit;
	}

	public String getAmenityfilterby() {
		return amenityfilterby;
	}

	public void setAmenityfilterby(final String amenityfilterby) {
		this.amenityfilterby = amenityfilterby;
	}

	public String getSortby() {
		return sortby;
	}

	public void setSortby(final String sortby) {
		this.sortby = sortby;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(final Double radius) {
		this.radius = radius;
	}
}
