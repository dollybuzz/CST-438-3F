package cst438hw2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import cst438hw2.domain.TempAndTime;

//Gets time and temperature from the api.openweathermap.org weather server, uses TempAndTime helper class

@Service
public class WeatherService {
	
	private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
	private RestTemplate restTemplate;
	private String weatherUrl;
	private String apiKey;
	
	public WeatherService(	//Constructor, the value for weatherUrl and apiKey are obtained from the application.properties file
			@Value("${weather.url}") final String weatherUrl, 
			@Value("${weather.apikey}") final String apiKey ) {
		this.restTemplate = new RestTemplate();
		this.weatherUrl = weatherUrl;
		this.apiKey = apiKey; 
	}
	
	public  TempAndTime getTempAndTime(String cityName) {
		ResponseEntity<JsonNode> response = restTemplate.getForEntity(
				weatherUrl + "?q=" + cityName + "&appid=" + apiKey,
				JsonNode.class);
		JsonNode json = response.getBody();	//takes the text returned by the server and parses it into a tree like data structure
		//called JsonNode. Using the JsonNode object returned by getBody, attributes such as "dt" or "timezone" can be obtained
		//by a get method call. The "temp" attribute is nested inside an attribute named "main".
		//So it first performs a do get on "main" then a second do get on "temp" within "main".
		log.info("Status code from weather server:" + response.getStatusCodeValue());
		
		double temp = json.get("main").get("temp").asDouble();
		int timezone = json.get("timezone").asInt();
		long time = (json.get("dt").asLong()+timezone);
		return new TempAndTime(temp, time, timezone);
	}
}
