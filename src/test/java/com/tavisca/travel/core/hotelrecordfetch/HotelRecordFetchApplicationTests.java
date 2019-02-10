package com.tavisca.travel.core.hotelrecordfetch;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavisca.travel.core.hotelrecordfetch.model.FilterHotelRecordRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelRecordFetchApplicationTests {


	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	 @Autowired
     private ObjectMapper objectMapper;


	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).apply(documentationConfiguration(this.restDocumentation)).build();
	}

	@Test
	public void getHotelByIdTest() throws Exception {
		this.mockMvc.perform(RestDocumentationRequestBuilders
				.get("/hotelrecord/id/{id}", 2962701))
		             .andExpect(status().isOk())
				     .andExpect(content().contentType("application/json;charset=UTF-8"))
				     .andDo(document("hotelrecord/get-by-id",
				    		 pathParameters(parameterWithName("id").description("Identifier of the hotel to be obtained.")),
				    		 relaxedResponseFields(fieldWithPath("name").description("Name of the hotel."),
				    				 fieldWithPath("rating").description("Rating of the hotel."),
		                             fieldWithPath("supplierFamily").description("SupplierFamily of the hotel."),
								     fieldWithPath("hotelCurrencyCode").description("CurrencyCode of the currrency accepted at the hotel."),
								     fieldWithPath("hotelChain").description("HotelChain of the hotel."),
								     fieldWithPath("contact").description("Contact of the hotel."))));
	}
	
	@Test
	public void getHotelRecordsByLocationTest() throws Exception{
		this.mockMvc.perform(RestDocumentationRequestBuilders
				    .get("/hotelrecords").param("location", "Paris"))
		            .andExpect(status().isOk())
				    .andExpect(content().contentType("application/json;charset=UTF-8"))
				    .andDo(document("hotelrecord/get-by-location",
						requestParameters(parameterWithName("location").description("Location of the hotels to be fetched."))));

	}
	
	@Test
	public void getHotelRecordByGeoCode() throws Exception {
		this.mockMvc.perform(RestDocumentationRequestBuilders.get("/hotelrecords/latitude/{latitude}/longitude/{longitude}", 49.00435, 2.520804)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andDo(document("hotelrecord/get-by-geoCode",
						pathParameters(parameterWithName("latitude").description("Latitude of the point for searching nearby hotels."),
								parameterWithName("longitude").description("Longitude of the point for searching nearby hotels."))));

	}

	@Test
	public void fetchFilteredHotelRecordsByLocationTest()throws Exception{
		FilterHotelRecordRequest request=new FilterHotelRecordRequest();
		request.setMinimumRating(4);
		request.getHotelChain().add("Independent Managed Hotels");
		request.getAmenities().add("Bar");

		this.mockMvc.perform(RestDocumentationRequestBuilders
				    .post("/hotelrecords").param("location", "Paris")
				    .contentType(MediaType.APPLICATION_JSON)
				    .content(objectMapper.writeValueAsString(request)))
		            .andExpect(status().isOk())
		            .andExpect(content().contentType("application/json;charset=UTF-8"))
		            .andDo(document("hotelrecord/get-filtered-record-by-location",
						requestParameters(parameterWithName("location").description("Location of the hotels to be fetched.")),
						requestFields(fieldWithPath("minimumRating").description("Provide minimum rating required in the  hotel to be filtered"),
								      fieldWithPath("amenities").description("Provide amenities required in the hotel to be filtered"),
								      fieldWithPath("hotelChain").description("Provide hotelChain required in the hotel to be filtered"))))
		            ;
		
	}

}

