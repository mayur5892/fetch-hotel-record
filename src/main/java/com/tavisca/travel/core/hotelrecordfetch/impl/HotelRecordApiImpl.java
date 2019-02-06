package com.tavisca.travel.core.hotelrecordfetch.impl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tavisca.travel.core.hotelrecordfetch.controller.HotelRecordApi;
import com.tavisca.travel.core.hotelrecordfetch.exception.HotelRecordNotFoundException;
import com.tavisca.travel.core.hotelrecordfetch.model.FilterHotelRecordRequest;
import com.tavisca.travel.core.hotelrecordfetch.model.HotelRecord;
import com.tavisca.travel.core.hotelrecordfetch.model.HotelRecordRequestParam;
import com.tavisca.travel.core.hotelrecordfetch.model.RetrieveHotelRecordResponse;
import com.tavisca.travel.core.hotelrecordfetch.service.HotelRecordService;

@RestController
public class HotelRecordApiImpl implements HotelRecordApi {

	private static final Integer DEFAULT_LIMIT = 5;
	private static final String DEFAULT_AMENITY_FILTER_CRITERIA = "GROUP_NAME";
	private static final String DEFAULT_SORTBY = "rating";

	@Autowired
	private HotelRecordService service;
	@Override
	public RetrieveHotelRecordResponse fetchHotelRecordsBylocation(@RequestParam(value = "location") final String location,
			                                                       @RequestParam(value = "limit",required = false) final Integer limit,
                                                                   @RequestParam(value = "offset",required = false) final Integer offset,
			                                                       @RequestParam(value = "sortBy", required = false) final String sortBy,
			                                                       @RequestParam(value = "amenityFilterBy", required = false) final String amenityFilterBy) {

		HotelRecordRequestParam requestParam = createInitializeRequestParam(location, limit, offset, sortBy, amenityFilterBy);

		RetrieveHotelRecordResponse response = service.fetchHotelRecordsByLocation(location, requestParam);

		buildNextRecordLink(requestParam, location, response);
		return response;
	}

	@Override
	public RetrieveHotelRecordResponse fetchFilteredHotelRecordBylocation(final FilterHotelRecordRequest filterRequest, final String location, final Integer limit,
			final Integer offset,
			final String sortBy) {
		HotelRecordRequestParam requestParam = createInitializeRequestParam(location, limit, offset, sortBy, null);
		RetrieveHotelRecordResponse response= service.fetchFilteredHotelRecordsByLocation(filterRequest, location, requestParam);
		// TODO buildNextRecordLink(requestParam, location, response);
		return response;
	}

	@Override
	public HotelRecord getHotelRecordById(@PathVariable final String id) {
		HotelRecord hotelRecord = service.getHotelRecordById(id);
		if (hotelRecord == null) {
			throw new HotelRecordNotFoundException("Hotel Not Found for hotelId " + id);
		}
		Link selfRel = linkTo(methodOn(this.getClass()).getHotelRecordById(id)).withSelfRel();
		hotelRecord.add(selfRel);
		return hotelRecord;
	}

	private HotelRecordRequestParam createInitializeRequestParam(final String location, final Integer limit, final Integer offset, final String sortBy,
			final String amenityFilterBy) {
		HotelRecordRequestParam requestParam = new HotelRecordRequestParam();
		requestParam.setLimit(limit == null ? DEFAULT_LIMIT : limit);
		requestParam.setOffset(offset == null ? 0 : offset);
		requestParam.setSortBy(sortBy == null ? DEFAULT_SORTBY : sortBy);
		requestParam.setAmenityFilterBy(amenityFilterBy == null ? DEFAULT_AMENITY_FILTER_CRITERIA : amenityFilterBy);
		return requestParam;
	}
	
	private void buildNextRecordLink(final HotelRecordRequestParam requestParam, final String location, final RetrieveHotelRecordResponse response) {
		Link link = null;
		if (requestParam.getOffset() > response.getCount())
			link = linkTo(methodOn(this.getClass()).fetchHotelRecordsBylocation(location, requestParam.getLimit(), 0, requestParam.getSortBy(), requestParam.getAmenityFilterBy()))
					.withRel("next");
		else
			link = linkTo(methodOn(this.getClass()).fetchHotelRecordsBylocation(location, requestParam.getLimit(), requestParam.getOffset() + requestParam.getLimit(),
					requestParam.getSortBy(), requestParam.getAmenityFilterBy())).withRel("next");
		response.add(link);
	}
	
	



}
