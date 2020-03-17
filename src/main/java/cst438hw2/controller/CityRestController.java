package cst438hw2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cst438hw2.domain.*;
import cst438hw2.service.CityService;

@RestController
public class CityRestController {
	
	@Autowired
	private CityService cityService;
	
	
	/*Routes a URL to the RestController which will return the same data in JSON format for
	 consumption by an AJAX call or mobile or other application.*/
	@GetMapping("/api/cities/{city}")
	public ResponseEntity<CityInfo> getWeather(@PathVariable("city") String cityName) {
		CityInfo info = cityService.getCityInfo(cityName);
		
		if(info == null)
		{
			return new ResponseEntity<CityInfo>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<CityInfo>(info, HttpStatus.OK);
	}
	
}
