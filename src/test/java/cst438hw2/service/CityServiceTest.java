package cst438hw2.service;
 
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.BDDMockito.given;


import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import cst438hw2.domain.*;
 
@SpringBootTest
public class CityServiceTest {

	@MockBean
	private WeatherService weatherService;
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;

	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testCityFound() throws Exception {
		
		//test City data using city repository
		List<City> cityList = new ArrayList<City>();
		cityList.add(new City(3838, "Honolulu", "USA", "Hawaii", 371657));
	
		//test Weather data
		TempAndTime weatherTestInfo = new TempAndTime(297.16, 1584666600, -36000);
				
		//test Country data
		Country countryTestInfo = new Country("USA", "Hawaii");
		
		//test CityInfo data using fake values
		CityInfo cityTestInfo = new CityInfo((long)3838, "Honolulu", "USA", "Hawaii", "Oahu", 371657, 74.93, "2020-03-19T15:35:39Z");
		CityInfo cityRealInfo = cityService.getCityInfo("Honolulu");
		
		//performing tests
		//Note: assert is the main testing and will throw an error if contents are not true
		//given is built into spring and "creates" mock conditions, sets up the test
		given(cityRepository.findByName("Honolulu")).willReturn(cityList);
		given(weatherService.getTempAndTime("Honolulu")).willReturn(weatherTestInfo);
		given(countryRepository.findByCode("USA")).willReturn(countryTestInfo);
		assertThat(cityRealInfo).isEqualTo(cityTestInfo); //won't pass, cityRealInfo returns null object
		//refer to CityService.java
		
	}
	/*
	@Test 
	public void  testCityNotFound() {
		City testCity = new City(24, "TestCity", "USA", "Somewhere", 1);
		CityInfo realInfo = cityService.getCityInfo(testCity.getName());
		
		assertThat(testCity).isEqualTo(realInfo);
	}
	
	@Test 
	public void  testCityMultiple() {
		List<City> tests = new ArrayList<City>();
		tests.add(new City(568, "Los Angeles", "CHL", "Chile", 158215));
		tests.add(new City(3794, "Los Angeles", "USA", "California", 3694820));
		Country testCountry = new Country("CHL", "Chile");
		TempAndTime testTempTime = new TempAndTime(282.35, 1584506871, -25200);
		
		given(cityRepository.findByName("Los Angeles")).willReturn(tests);
		given(countryRepository.findByCode("CHL")).willReturn(new Country("CHL", "Chile"));
		given(weatherService.getTempAndTime("Los Angeles")).willReturn(testTempTime);
		
		CityInfo realInfo = cityService.getCityInfo("Los Angeles");
		
		assertThat(realInfo).isEqualTo(testCountry);
	}*/
}
