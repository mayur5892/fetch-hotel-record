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
	private CachingRepository hotelRecordCache;

	public RetrieveHotelRecordResponse fetchHotelRecordsByLocation(final String location, final HotelRecordRequestParam requestParams) {
		List<HotelRecord> hotelRecords = hotelRecordCache.fetchHotelRecordsByLocation(location, requestParams.getSortBy());
		if (hotelRecords == null || hotelRecords.isEmpty())
			throw new HotelRecordNotFoundException("No hotels found for location " + location);
		return responseBuilder.buildRetrieveHotelRecordResposne(hotelRecords, requestParams);

	}

	public RetrieveHotelRecordResponse fetchFilteredHotelRecordsByLocation(final FilterHotelRecordRequest filterRequest, final String location,
			final HotelRecordRequestParam requestParams) {
		List<HotelRecord> hotelRecords = hotelRecordCache.fetchHotelRecordsByLocation(location, requestParams.getSortBy());
		if (hotelRecords == null || hotelRecords.isEmpty())
			throw new HotelRecordNotFoundException("No hotels found for location " + location);
		return responseBuilder.buildFilteredRetrieveHotelRecordResposne(filterRequest, hotelRecords, requestParams);

	}

	public HotelRecord getHotelRecordById(final String id) {
		return hotelRecordCache.getHotelRecordById(id);
	}

	public RetrieveHotelRecordResponse fetchFilteredHotelRecordsByGeoCode(final Double latitude, final Double longitude, final HotelRecordRequestParam requestParam) {
		 List<HotelRecord> hotelRecords = hotelRecordCache.fetchHotelRecordsByGeoCode(latitude, longitude, requestParam.getSortBy(), requestParam.getRadius());
		 if (hotelRecords == null || hotelRecords.isEmpty())
			throw new HotelRecordNotFoundException("No hotels found near GeoCode (" + latitude + ", " + longitude + ")");
			return responseBuilder.buildRetrieveHotelRecordResposne(hotelRecords, requestParam);
	}

}
