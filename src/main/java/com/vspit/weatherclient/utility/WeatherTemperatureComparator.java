package com.vspit.weatherclient.utility;


import java.util.Comparator;

import com.vspit.weatherclient.model.Weather;

public class WeatherTemperatureComparator implements Comparator<Weather> {

    @Override
    public int compare(Weather weather1, Weather weather2) {
        if (weather1.getTemperature() < weather2.getTemperature()) {
            return -1;
        } else if (weather1.getTemperature() > weather2.getTemperature()) {
            return 1;
        }

        return 0;
    }
}
