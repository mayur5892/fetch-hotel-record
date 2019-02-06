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




}
