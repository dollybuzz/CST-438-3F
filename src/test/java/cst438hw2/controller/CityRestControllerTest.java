package cst438hw2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import cst438hw2.domain.*;
import cst438hw2.service.CityService;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {

	@MockBean
	private CityService cityService;

	@Autowired
	private MockMvc mvc;

	// This object will be magically initialized by the initFields method below.
	private JacksonTester<CityInfo> json;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void contextLoads() {
	}

	//Testing that valid data provides valid HTTP response
	@Test
	public void getValidCityInfo() throws Exception {
		
		//expected data
		CityInfo expected = new CityInfo((long)3838, "Honolulu", "USA", "Hawaii", "Hawaii", 371657, 297.16, "1584666600");
		
		//stub out cityService class
		given(cityService.getCityInfo("Honolulu")).willReturn(expected);
		
		//when
		MockHttpServletResponse response = mvc.perform(
				get("/api/cities/Honolulu").contentType(MediaType.APPLICATION_JSON)
						.content(json.write(expected).getJson()))
				.andReturn().getResponse();
		
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(
				json.write(expected).getJson());
	}
	
	//Testing that invalid data does not provide a valid HTTP response
	@Test
	public void getInvalidCityInfo() throws Exception {
		
		//expected data
		CityInfo invalidAttempt = new CityInfo((long)3838, "Honolulu", "USA", "Hawaii", "Oahu", 371657, 297.16, "1584666600");
		CityInfo expected = new CityInfo((long)3838, "Honolulu", "USA", "Hawaii", "Hawaii", 371657, 297.16, "1584666600");
				
		//stub out cityService class
		given(cityService.getCityInfo("Honolulu")).willReturn(expected);
				
		//when
		MockHttpServletResponse response = mvc.perform(
				get("/api/cities/test").contentType(MediaType.APPLICATION_JSON)
						.content(json.write(invalidAttempt).getJson()))
				.andReturn().getResponse();
				
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(response.getContentAsString()).isEqualTo("");
	}

}
