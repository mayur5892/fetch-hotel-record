package com.tavisca.travel.core.hotelrecordfetch.impl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tavisca.travel.core.hotelrecordfetch.HotelRecordApi;
import com.tavisca.travel.core.hotelrecordfetch.exception.HotelRecordNotFoundException;
import com.tavisca.travel.core.hotelrecordfetch.model.FilterHotelRecordRequest;
import com.tavisca.travel.core.hotelrecordfetch.model.HotelRecord;
import com.tavisca.travel.core.hotelrecordfetch.model.HotelRecordRequestParam;
import com.tavisca.travel.core.hotelrecordfetch.model.RetrieveHotelRecordResponse;
import com.tavisca.travel.core.hotelrecordfetch.property.reader.PropertyReader;
import com.tavisca.travel.core.hotelrecordfetch.service.HotelRecordService;

@RestController
public class HotelRecordApiImpl implements HotelRecordApi {

	@Autowired
	private HotelRecordService service;

	@Autowired
	private PropertyReader propertyReader;

	@Override
	public RetrieveHotelRecordResponse fetchHotelRecordsBylocation(@RequestParam(value = "location") final String location,
			                                                       @RequestParam(value = "limit",required = false) final Integer limit,
                                                                   @RequestParam(value = "offset",required = false) final Integer offset,
			                                                       @RequestParam(value = "sortBy", required = false) final String sortBy,
			                                                       @RequestParam(value = "amenityFilterBy", required = false) final String amenityFilterBy) {

		HotelRecordRequestParam requestParam = createInitializeRequestParam(limit, offset, sortBy, amenityFilterBy);

		RetrieveHotelRecordResponse response = service.fetchHotelRecordsByLocation(location, requestParam);

		Link link = linkTo(methodOn(this.getClass()).fetchHotelRecordsBylocation(location, requestParam.getLimit(),
				nextOffset(requestParam.getOffset(), requestParam.getLimit(), response.getCount()), requestParam.getSortBy(), requestParam.getAmenityFilterBy())).withRel("next");
		response.add(link);
		return response;
	}
	
	@Override
	public RetrieveHotelRecordResponse fetchHotelRecordsByGeoCode(@PathVariable final double latitude, @PathVariable final double longitude,
			                                                      @RequestParam(value = "radius", required = false) final Double radius,
                                                                  @RequestParam(value = "limit", required = false) final Integer limit,
                                                                  @RequestParam(value = "offset", required = false) final Integer offset,
                                                                  @RequestParam(value = "sortBy", required = false) final String sortBy,
			                                                      @RequestParam(value = "amenityFilterBy", required = false) final String amenityFilterBy) {

		validate(latitude, longitude);
		HotelRecordRequestParam requestParam = createInitializeRequestParam(limit, offset, sortBy, amenityFilterBy);
		requestParam.setRadius(radius == null ? propertyReader.getRadius() : radius);
		RetrieveHotelRecordResponse response = service.fetchFilteredHotelRecordsByGeoCode(latitude, longitude, requestParam);
		Link link = linkTo(methodOn(this.getClass()).fetchHotelRecordsByGeoCode(latitude, longitude, requestParam.getRadius(), requestParam.getLimit(),
				nextOffset(requestParam.getOffset(), requestParam.getLimit(), response.getCount()), requestParam.getSortBy(), requestParam.getAmenityFilterBy())).withRel("next");
		response.add(link);

		return response;
	}


	@Override
	public RetrieveHotelRecordResponse fetchFilteredHotelRecordBylocation(final FilterHotelRecordRequest filterRequest, final String location, final Integer limit,
			final Integer offset,
			final String sortBy) {
		HotelRecordRequestParam requestParam = createInitializeRequestParam(limit, offset, sortBy, null);

		RetrieveHotelRecordResponse response= service.fetchFilteredHotelRecordsByLocation(filterRequest, location, requestParam);

		Link link = linkTo(methodOn(this.getClass()).fetchHotelRecordsBylocation(location, requestParam.getLimit(),
				nextOffset(requestParam.getOffset(), requestParam.getLimit(), response.getCount()), requestParam.getSortBy(), requestParam.getAmenityFilterBy())).withRel("next");
		response.add(link);
		return response;
	}

	@Override
	public HotelRecord getHotelRecordById(@PathVariable final String id) {
		HotelRecord hotelRecord = service.getHotelRecordById(id);
		if (hotelRecord == null) {
			throw new HotelRecordNotFoundException("Hotel Not Found for hotelId " + id);
		}
		return hotelRecord;
	}

	private HotelRecordRequestParam createInitializeRequestParam(final Integer limit, final Integer offset, final String sortBy, final String amenityFilterBy) {
		return new HotelRecordRequestParam(limit == null ? propertyReader.getLimit() : limit,
				                           offset == null ? 0 : offset,
				sortBy == null ? propertyReader.getSortby() : sortBy, amenityFilterBy == null ? propertyReader.getAmenityfilterby() : amenityFilterBy);

	}
	private Integer nextOffset(final int currentOffset, final int limit, final int totalRecord) {
		if (currentOffset > totalRecord)
			return 0;

		return currentOffset + limit;
	}

	private void validate(final double latitude, final double longitude) {
		if (latitude < -90 || latitude > 90 || longitude < 180 || longitude > 180)
			;

	}

}
