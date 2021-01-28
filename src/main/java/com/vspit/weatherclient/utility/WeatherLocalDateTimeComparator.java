package com.vspit.weatherclient.utility;


import java.util.Comparator;

import com.vspit.weatherclient.model.Weather;

public class WeatherLocalDateTimeComparator implements Comparator<Weather> {

    @Override
    public int compare(Weather weather1, Weather weather2) {
        return weather1.getLocalDateTime().compareTo(weather2.getLocalDateTime());
    }
}
