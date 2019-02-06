package com.tavisca.travel.core.hotelrecordfetch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
public class HotelRecordFetchApplication {

	public static void main(final String[] args) {
		SpringApplication.run(HotelRecordFetchApplication.class, args);
	}

}

