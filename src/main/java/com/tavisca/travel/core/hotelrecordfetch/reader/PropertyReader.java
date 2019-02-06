package com.tavisca.travel.core.hotelrecordfetch.reader;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "hotel.content.api")
@PropertySource("classpath:hotelconfig.properties")
public class PropertyReader {

	private String url;

	private List<String> city;

	public List<String> getCity() {
		return city;
	}

	public void setCity(final List<String> city) {
		this.city = city;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

}
