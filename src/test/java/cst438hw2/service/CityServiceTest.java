package cst438hw2.service;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;


import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.anyString;

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
		List<City> tests = new ArrayList<City>();
		tests.add(new City(3838, "Honolulu", "USA", "Hawaii", 371657));
		City testCity = new City(3838, "Honolulu", "USA", "Hawaii", 371657);
		CityInfo realInfo = cityService.getCityInfo("Honolulu");
		
		given(cityRepository.findByName("Honolulu")).willReturn(tests);
		given(countryRepository.findByCode("USA")).willReturn(new Country("USA", "United States"));
		given(weatherService.getTempAndTime("Honolulu")).willReturn(new TempAndTime(294.88, 1584501287, -36000));
		
		assertThat(realInfo).isEqualTo(testCity);
	}
	
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

	}

}
