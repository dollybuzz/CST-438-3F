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

	
	//Testing if valid data returns a real city
	@Test
	public void testCityFound() throws Exception {
		
		//test City data using city repository
		List<City> cityList = new ArrayList<City>();
		cityList.add(new City((long)3838, "Honolulu", "USA", "Hawaii", 371657));
	
		//test Weather data
		TempAndTime weatherTestInfo = new TempAndTime(297.16, 1584666600, -36000);
				
		//test Country data
		Country countryTestInfo = new Country("USA", "Hawaii");
		
		//test CityInfo data using fake values
		CityInfo cityTestInfo = new CityInfo((long)3838, "Honolulu", "USA", "Hawaii", "Hawaii", 371657, 297.16, "1584666600");
		
		//building tests
		//given is built into spring and "creates" mock conditions (MockBeans), sets up the MockBeans' functionalities
		given(cityRepository.findByName("Honolulu")).willReturn(cityList);
		given(weatherService.getTempAndTime("Honolulu")).willReturn(weatherTestInfo);
		given(countryRepository.findByCode("USA")).willReturn(countryTestInfo);
		
		CityInfo cityRealInfo = cityService.getCityInfo("Honolulu");
		
		//performing test
		//assert is the main testing and will throw an error if contents are not true
		assertThat(cityRealInfo).isEqualTo(cityTestInfo);
	}
	
	//Testing that invalid data returns null
	@Test 
	public void  testCityNotFound() {
		//test data
		CityInfo testCityInfo = null;
		TempAndTime fakeData = new TempAndTime(1, 200, 1584666680);
		
		//building test
		given(weatherService.getTempAndTime("InvalidCity")).willReturn(fakeData);
		
		CityInfo realCityInfo = cityService.getCityInfo("InvalidCity");
		
		//performing test
		assertThat(testCityInfo).isEqualTo(realCityInfo);
	}
	
	//Testing that duplicate City names return the first city in the table
	@Test
	public void  testCityMultiple() {
		//test data
		List<City> cityList = new ArrayList<City>();
		cityList.add(new City((long)568, "Los Angeles", "CHL", "Chile", 158215));
		CityInfo testCityInfo = new CityInfo((long)568, "Los Angeles", "CHL", "Chile", "Chile", 158215, 288.47, "1584841045");
		Country testCountry = new Country("CHL", "Chile");
		TempAndTime testTempTime = new TempAndTime(288.47, 1584841045, -25200);
				
		//building test
		given(cityRepository.findByName("Los Angeles")).willReturn(cityList);
		given(countryRepository.findByCode("CHL")).willReturn(testCountry);
		given(weatherService.getTempAndTime("Los Angeles")).willReturn(testTempTime);
		
		CityInfo firstRealCityInfoFromTable = cityService.getCityInfo("Los Angeles");
		
		//performing test
		assertThat(firstRealCityInfoFromTable).isEqualTo(testCityInfo);
	}
}
