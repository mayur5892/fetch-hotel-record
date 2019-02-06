package com.tavisca.travel.core.hotelrecordfetch.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tavisca.travel.core.hotelrecordfetch.model.FilterHotelRecordRequest;
import com.tavisca.travel.core.hotelrecordfetch.model.HotelRecord;
import com.tavisca.travel.core.hotelrecordfetch.model.HotelRecordRequestParam;
import com.tavisca.travel.core.hotelrecordfetch.model.RetrieveHotelRecordResponse;

@Component
public class HotelRecordResponseBuilder {


	public RetrieveHotelRecordResponse buildRetrieveHotelRecordResposne(final List<HotelRecord> hotelRecords, final HotelRecordRequestParam requestParam) {
		RetrieveHotelRecordResponse response = new RetrieveHotelRecordResponse();
		response.setHotels(buildPaginatedResponse(hotelRecords, requestParam.getLimit(), requestParam.getOffset()));
		response.setCount(hotelRecords.size());
		Map<String, Map<String, Integer>> filters = new HashMap<String, Map<String, Integer>>();
		buildFilter(hotelRecords, filters, requestParam.getAmenityFilterBy());
		response.setFilters(filters);
		return response;

	}

	public RetrieveHotelRecordResponse buildFilteredRetrieveHotelRecordResposne(final FilterHotelRecordRequest filterRequest, final List<HotelRecord> hotelRecords,
			final HotelRecordRequestParam requestParams) {
		List<HotelRecord> filtedHotelRecords = hotelRecords.stream()
				.filter(hotel -> filterRequest.getMinimumRating() == null || hotel.getRating() >= filterRequest.getMinimumRating())
				.filter(hotel -> filterRequest.getAmenities() == null || filterForAmenity(filterRequest.getAmenities(), hotel))
				.filter(hotel -> filterRequest.getHotelChain() == null || filterForHotelChain(filterRequest.getHotelChain(), hotel)).collect(Collectors.toList());
		return buildRetrieveHotelRecordResposne(filtedHotelRecords, requestParams);
	}

	private boolean filterForHotelChain(final List<String> hotelChain, final HotelRecord hotel) {
		System.out.println("inside hotelchain");
		if (!hotelChain.isEmpty()) {
			return hotelChain.contains(hotel.getHotelChain().getName());
		}
		return true;
	}

	private boolean filterForAmenity(final List<String> amenities, final HotelRecord hotel) {
		System.out.println("Inside amenity filter");
		List<String> hotelAmenityList = new ArrayList<String>();
		hotel.getAmenities().forEach(amenity -> {
			String amenityName = amenity.getGroupName() != null ? amenity.getGroupName() : amenity.getName();
			hotelAmenityList.add(amenityName);
		});
		return hotelAmenityList.containsAll(amenities);
	}

	private void buildFilter(final List<HotelRecord> hotelRecords, final Map<String, Map<String, Integer>> filtersMap, final String requiredAmenity) {
		Set<String> amenities = new HashSet<>();
		hotelRecords.forEach(hotel -> {
			buildHotelRatingFilter(hotel, filtersMap);
			buildHotelChainFilter(hotel, filtersMap);
			buildHotelNameFilter(hotel, filtersMap);
			buildHotelAmenitiesFilter(hotel, filtersMap, requiredAmenity, amenities);
			amenities.clear();

		});
	}

	private void buildHotelAmenitiesFilter(final HotelRecord hotel, final Map<String, Map<String, Integer>> filtersMap, final String requiredAmenity,
			final Set<String> amenities) {
		if (null != hotel.getAmenities()) {
			hotel.getAmenities().forEach(amenity -> {
				if (null != amenity.getGroupName())
					updateFilterAttributeForAmenity("Amenities", amenity.getGroupName(), filtersMap, amenities);
				else if (null != amenity.getName() && requiredAmenity.equals("ALL"))
					updateFilterAttributeForAmenity("Amenities", amenity.getName(), filtersMap, amenities);
			});
		}

	}

	private void updateFilterAttributeForAmenity(final String categoryType, final String categoryName, final Map<String, Map<String, Integer>> filtersMap,
			final Set<String> amenities) {
		filtersMap.compute(categoryType, (key, value) -> {
			if (value == null) {
				Map<String, Integer> map = new TreeMap<>();
				map.put(categoryName, 1);
				amenities.add(categoryName);
				return map;
			}
			if (!amenities.contains(categoryName)) {
				value.compute(categoryName, (k, v) -> v = v == null ? 1 : v + 1);
				amenities.add(categoryName);
			}
			return value;
		});

	}

	private void buildHotelNameFilter(final HotelRecord hotel, final Map<String, Map<String, Integer>> filtersMap) {
		if (null != hotel.getName())
			updateFilterAttribute("Hotel Name", hotel.getName(), filtersMap);

	}

	private void buildHotelChainFilter(final HotelRecord hotel, final Map<String, Map<String, Integer>> filtersMap) {
		if (null != hotel.getHotelChain() && null != hotel.getHotelChain().getName())
			updateFilterAttribute("Hotel Chain", hotel.getHotelChain().getName(), filtersMap);

	}

	private void buildHotelRatingFilter(final HotelRecord hotel, final Map<String, Map<String, Integer>> filtersMap) {
		if (hotel.getRating() == 5)
			updateFilterAttribute("Star Rating", "5 Star", filtersMap);
		else if (hotel.getRating() >= 4)
			updateFilterAttribute("Star Rating", "4 Star", filtersMap);
		else if (hotel.getRating() >= 3)
			updateFilterAttribute("Star Rating", "3 Star", filtersMap);
		else if (hotel.getRating() >= 2)
			updateFilterAttribute("Star Rating", "2 Star", filtersMap);
		else if (hotel.getRating() >= 1)
			updateFilterAttribute("Star Rating", "1 Star", filtersMap);
		else if (hotel.getRating() == 0)
			updateFilterAttribute("Star Rating", "0 Star", filtersMap);

	}

	private void updateFilterAttribute(final String categoryType, final String categoryName, final Map<String, Map<String, Integer>> filtersMap) {
		filtersMap.compute(categoryType, (key, value) -> {
			if (value == null) {
				Map<String, Integer> map = new TreeMap<>();
				map.put(categoryName, 1);
				return map;
			}
			value.compute(categoryName, (k, v) -> v = v == null ? 1 : v + 1);
			return value;
		});

	}

	private List<HotelRecord> buildPaginatedResponse(final List<HotelRecord> hotelRecords, final Integer limit, final Integer offset) {
		if (offset >= hotelRecords.size()) {
			if (limit <= hotelRecords.size())
				return hotelRecords.subList(0, limit);
			return hotelRecords;

		}
		else if (limit + offset > hotelRecords.size())
			return hotelRecords.subList(offset, hotelRecords.size());

		return hotelRecords.subList(offset, limit + offset);

	}
}
