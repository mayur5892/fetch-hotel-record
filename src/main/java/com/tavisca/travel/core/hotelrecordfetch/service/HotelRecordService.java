package com.tavisca.travel.core.hotelrecordfetch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavisca.travel.core.hotelrecordfetch.bo.HotelRecordResponseBuilder;
import com.tavisca.travel.core.hotelrecordfetch.cache.CachingRepository;
import com.tavisca.travel.core.hotelrecordfetch.exception.HotelRecordNotFoundException;
import com.tavisca.travel.core.hotelrecordfetch.model.FilterHotelRecordRequest;
import com.tavisca.travel.core.hotelrecordfetch.model.HotelRecord;
import com.tavisca.travel.core.hotelrecordfetch.model.HotelRecordRequestParam;
import com.tavisca.travel.core.hotelrecordfetch.model.RetrieveHotelRecordResponse;



@Service
public class HotelRecordService {

	@Autowired
	private HotelRecordResponseBuilder responseBuilder;

	@Autowired
	private CachingRepository repository;

	public RetrieveHotelRecordResponse fetchHotelRecordsByLocation(final String location, final HotelRecordRequestParam requestParams) {
		List<HotelRecord> hotelRecords = repository.fetchHotelRecordsByLocation(location, requestParams.getSortBy());
		if (hotelRecords == null || hotelRecords.isEmpty())
			throw new HotelRecordNotFoundException("No hotels found for location " + location);
		return responseBuilder.buildRetrieveHotelRecordResposne(hotelRecords, requestParams);

	}

	public RetrieveHotelRecordResponse fetchFilteredHotelRecordsByLocation(final FilterHotelRecordRequest filterRequest, final String location,
			final HotelRecordRequestParam requestParams) {
		List<HotelRecord> hotelRecords = repository.fetchHotelRecordsByLocation(location, requestParams.getSortBy());
		if (hotelRecords == null || hotelRecords.isEmpty())
			throw new HotelRecordNotFoundException("No hotels found for location " + location);
		return responseBuilder.buildFilteredRetrieveHotelRecordResposne(filterRequest, hotelRecords, requestParams);

	}

	public HotelRecord getHotelRecordById(final String id) {
		return repository.getHotelRecordById(id);
	}

}
