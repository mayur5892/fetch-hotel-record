package com.tavisca.travel.core.hotelrecordfetch.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.tavisca.travel.core.hotelrecordfetch.entity.HotelRecordEntity;
import com.tavisca.travel.core.hotelrecordfetch.model.HotelRecord;
import com.tavisca.travel.core.hotelrecordfetch.repository.HotelRepository;

@Component
public class CachingRepository {
	
	Map<String, String> SORTING_CRITERIA = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("rating", "HOTEL_RATING");
		}
	};

	@Autowired
	private HotelRepository repository;

	@Cacheable("hotelRecordsFetch")
	public List<HotelRecord> fetchHotelRecordsByLocation(final String location, final String sortBy) {
		String sortCriteria = SORTING_CRITERIA.get(sortBy);
		return repository.retrieveHotelsByLocation(location, sortCriteria);
	}

	@Cacheable("hotelRecordById")
	public HotelRecord getHotelRecordById(final String id) {
		return repository.retrieveHotelById(id);
	}
	
	@Cacheable("hotelRecordsFetchByGeoCode")
	public List<HotelRecord> fetchHotelRecordsByGeoCode(final Double latitude, final Double longitude, final String sortBy, final Double radius) {
		String sortCriteria = SORTING_CRITERIA.get(sortBy);
		List<HotelRecordEntity> hotelRecordEntity = repository.retrieveHotelsByGeoCode(latitude, longitude, sortCriteria, radius);
		List<HotelRecord> hotelRecords = new ArrayList<>();
		hotelRecordEntity.stream().forEach(entity -> {
			hotelRecords.add(entity.getHotelRecord());
		});
		;
		return hotelRecords;
	}

}
