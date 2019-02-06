package com.tavisca.travel.core.hotelrecordfetch.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tavisca.travel.core.hotelrecordfetch.model.FilterHotelRecordRequest;
import com.tavisca.travel.core.hotelrecordfetch.model.HotelRecord;
import com.tavisca.travel.core.hotelrecordfetch.model.RetrieveHotelRecordResponse;

@RestController()
public interface HotelRecordApi {

	@GetMapping(path = "/hotelrecords", produces = MediaType.APPLICATION_JSON_VALUE)
	RetrieveHotelRecordResponse fetchHotelRecordsBylocation(@RequestParam(value = "location") final String location,
			                                                          @RequestParam(value = "limit", required = false) final Integer limit,
			                                                          @RequestParam(value = "offset", required = false) final Integer offset,
			                                                          @RequestParam(value = "sortBy", required = false) final String sortBy,
			@RequestParam(value = "amenityFilterBy", required = false) final String amenityFilterBy);
	
	@PostMapping(path = "/hotelrecords", produces = MediaType.APPLICATION_JSON_VALUE)
	RetrieveHotelRecordResponse fetchFilteredHotelRecordBylocation(@RequestBody FilterHotelRecordRequest filterRequest,@RequestParam(value = "location") final String location,
            @RequestParam(value = "limit", required = false) final Integer limit,
            @RequestParam(value = "offset", required = false) final Integer offset,
			@RequestParam(value = "sortBy", required = false) final String sortBy);

	@GetMapping(path = "/hotelrecord/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	HotelRecord getHotelRecordById(@PathVariable String id);

}
