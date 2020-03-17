package cst438hw2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438hw2.domain.*;

//Gets date from the database, uses CountryRepository and CityRepository to obtain information on the city and country

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	public CityInfo getCityInfo(String cityName) {
		
		City city;
		Country country;
		TempAndTime tempAndTime;
		double temp;
		String time;
		
		List<City> cities = cityRepository.findByName(cityName);
		
		if(!cities.isEmpty())
		{
			city = cities.get(0);
		} else {
			return null;
		}
		
		country = countryRepository.findByCode(city.getCountryCode());
		tempAndTime = weatherService.getTempAndTime(cityName);
		temp = tempAndTime.getTemp();
		time = tempAndTime.timeToString(tempAndTime.getTime());
		
		
		CityInfo cityInfo = new CityInfo(city.getId(), city.getName(), city.getCountryCode(), 
				country.getName(), city.getDistrict(), city.getPopulation(),
				temp, time);
		
		return cityInfo;
	}
}
