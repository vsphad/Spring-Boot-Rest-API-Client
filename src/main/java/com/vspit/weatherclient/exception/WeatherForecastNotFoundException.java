package com.vspit.weatherclient.exception;


public class WeatherForecastNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WeatherForecastNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}