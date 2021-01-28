package com.vspit.weatherclient.service;

import com.vspit.weatherclient.exception.WeatherForecastNotFoundException;
import com.vspit.weatherclient.interfaces.WeatherService;
import com.vspit.weatherclient.model.APIResponse;
import com.vspit.weatherclient.model.Weather;
import com.vspit.weatherclient.utility.WeatherTemperatureComparator;
import com.vspit.weatherclient.utility.WeatherLocalDateTimeComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class WeatherServiceImpl implements WeatherService {

	@Value("${WeatherApiUrl}")
	private String url;
	@Value("${ApiKey}")
	private String key;
	@Autowired
	RestTemplate restTemplate;
	private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@Override
	public APIResponse getHourlyForecast(String postalCode) {
		// TODO Auto-generated method stub

		String buildUrl = url.replace("zip", postalCode).replace("auth", key);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<APIResponse> response = restTemplate.exchange(buildUrl, HttpMethod.GET, entity,
				APIResponse.class);
		int statuscode = response.getStatusCode().value();

		if (statuscode == 200) {

			APIResponse apiresponse = response.getBody();
			if (apiresponse == null) {

				logger.error("*** Weather forecast not found for  provided zipcode  :: {}", postalCode);
				throw new WeatherForecastNotFoundException(
						"Weather forecast for tomorrow not found for provided zipcode");
			}
			LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(apiresponse.getTimezone()));

			List<Weather> weatherList = apiresponse.getWeatherList().stream()
					.filter(weather -> weather.getLocalDateTime().toLocalDate().isAfter(localDateTime.toLocalDate()))
					.limit(24).sorted(new WeatherLocalDateTimeComparator()).collect(Collectors.toList());
			apiresponse.setWeatherList(weatherList);
			apiresponse.setWeatherList(weatherList);
			LocalDateTime coolestHourOfDay = apiresponse.getWeatherList().stream()
					.min(new WeatherTemperatureComparator()).get().getLocalDateTime();
			apiresponse.setCoolestHourOfDay(coolestHourOfDay);
			logger.info("*** Coolest hour of the day is  :: {}", coolestHourOfDay);
			return apiresponse;

		}

		else {
			logger.error("*** Weather forecast not found for  provided zipcode  :: {}", postalCode);
			throw new WeatherForecastNotFoundException("Weather forecast for tomorrow not found for provided zipcode");

		}

	}
}
