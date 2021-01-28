package com.vspit.weatherclient.interfaces;

import com.vspit.weatherclient.model.APIResponse;

public interface WeatherService {

      APIResponse getHourlyForecast(String postalCode);

   
}
