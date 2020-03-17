package cst438hw2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438hw2.domain.CityInfo;
import cst438hw2.domain.TempAndTime;

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
		temp = weatherService.getTempAndTime(cityName).temp;
		time = weatherService.getTempAndTime(cityName).time;
	
		
		CityInfo cityInfo = new CityInfo(city.getId(), city.getName(), city.getCountryCode(), 
				country.getCountryName(), city.getDistrict(), city.getPopulation(),
				temp, time);
		
		return cityInfo;
	}
}
