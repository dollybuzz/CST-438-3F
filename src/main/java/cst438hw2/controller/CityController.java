package cst438hw2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cst438hw2.domain.*;
import cst438hw2.service.CityService;

//This method handles the http get request. The @PathVariable annotation tells spring to
//parse the url and put the text that occurs after /cities/ into the cityName parameter

@Controller
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/cities/{city}")
	public String getWeather(@PathVariable("city") String cityName, Model model) {
		CityInfo cityInfo = cityService.getCityInfo(cityName);
		
		model.addAttribute(cityInfo);
		
		if(cityInfo == null)
		{
			model.addAttribute("cityName", cityName);
		} else
		{
			model.addAttribute("cityInfo", cityInfo);
		}
		return "service";
	} 

	@PostMapping("/cities/reservation")
	public String createReservation(
		@RequestParam("city") String cityName, 
		@RequestParam("level") String level, 
		@RequestParam("email") String email, 
		Model model
	){
		model.addAttribute("city", cityName);
		model.addAttribute("level", level);
		model.addAttribute("email", email);
		cityService.requestReservation(cityName, level, email);
		return "request_reservation";
	}
}