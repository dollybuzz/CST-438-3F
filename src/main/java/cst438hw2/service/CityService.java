package cst438hw2.service;

import java.util.List;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
    @Autowired
    private FanoutExchange fanout;

	
	public CityInfo getCityInfo(String cityName) {
		
		City city;
		Country country;
		TempAndTime temp;
		TempAndTime time;
		
		List<City> cities = cityRepository.findByName(cityName);
		
		if (cities.size() == 0) {
			return null;
		}
		city = cities.get(0);
		country = countryRepository.findByCode(city.getCountryCode());
		temp = weatherService.getTempAndTime(cityName);
		time = weatherService.getTempAndTime(cityName);
		
		CityInfo cityInfo = new CityInfo(city.getId(), city.getName(), city.getCountryCode(), 
				country.getName(), city.getDistrict(), city.getPopulation(),
				temp.temp, time.timeToString());
		
		return cityInfo;
	}
	
	public void requestReservation( String cityName, String level, String email) {
		String msg  = "{\"cityName\": \""+ cityName + "\", \"level\": \""+level+"\", \"email\": \""+email+"\"}" ;
		System.out.println("Sending message:"+msg);
		rabbitTemplate.convertSendAndReceive(fanout.getName(), "", msg);
	}
}
