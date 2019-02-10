package com.tavisca.travel.core.hotelrecordfetch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tavisca.travel.core.hotelrecordfetch.entity.HotelRecordEntity;
import com.tavisca.travel.core.hotelrecordfetch.model.HotelRecord;

@Repository
public interface HotelRepository extends CrudRepository<HotelRecordEntity, Integer> {

	@Query("select h.hotelRecord from HotelRecordEntity h where h.hotelLocation=(:location) AND h.isLatest=true order by :sortBy")
	List<HotelRecord> retrieveHotelsByLocation(@Param("location") String location, @Param("sortBy") String sortBy);

	@Query("select h.hotelRecord from HotelRecordEntity h where h.hotelId=(:id) AND h.isLatest=true")
	HotelRecord retrieveHotelById(@Param("id") String id);

	@Query(value = "SELECT * FROM hotel_record WHERE "
			+ "ST_DWithin(Geography(ST_MakePoint(cast(hotel_data->'geoCode'->>'latitude' as float),cast(hotel_data->'geoCode'->>'longitude' as float))),"
			+ "Geography(ST_MakePoint(:latitude, :longitude)),:radius) AND IS_LATEST=true order by (:sortBy);", nativeQuery = true)
	List<HotelRecordEntity> retrieveHotelsByGeoCode(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("sortBy") String sortBy,
			@Param("radius") Double radius);




}
