package cst438hw2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cst438hw2.domain.*;
import cst438hw2.service.CityService;

@Controller
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	/*This method will call the CityService class which will return data from the world.city
	 * sample table and get current weather for the city. The HTML page returned by the controller
	 * should contain city information, the country name, the current temperature, and the local time of
	 * the temperature reading.*/
	@GetMapping("/cities/{city}")
	public String getWeather(@PathVariable("city") String cityName, Model model) {

		model.addAttribute(cityName);
		return "city";
	} 
	
}