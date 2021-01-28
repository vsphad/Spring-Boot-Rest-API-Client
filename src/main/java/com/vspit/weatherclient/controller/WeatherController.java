package com.vspit.weatherclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vspit.weatherclient.exception.InvalidZipcodeException;
import com.vspit.weatherclient.interfaces.WeatherService;
import com.vspit.weatherclient.model.APIResponse;
import com.vspit.weatherclient.service.WeatherServiceImpl;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/v1/weather")
public class WeatherController {

	private WeatherService weatherservice;

	@Autowired
	WeatherController(WeatherService weatherservice) {
		this.weatherservice = weatherservice;
	}

	private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@GetMapping(value = "/gethourlyforecast")
	public APIResponse getResponse(@RequestParam(name = "zipcode") String postalCode) {

		if (Objects.isNull(postalCode) || postalCode.trim().length() < 5)
			throw new InvalidZipcodeException("Zipcode should not be null or less than 5 characters");
		logger.info("*** Getting Weather forecast for zipcode :: {}", postalCode);

		return weatherservice.getHourlyForecast(postalCode);

	}

}
