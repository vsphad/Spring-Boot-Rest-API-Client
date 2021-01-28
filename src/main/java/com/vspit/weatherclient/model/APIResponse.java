package com.vspit.weatherclient.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class APIResponse {
    private String countryCode;
    private String stateCode;
    private String timezone;
    private LocalDateTime coolestHourOfDay;
    private List<Weather> weatherList;

    @JsonCreator
    public APIResponse(@JsonProperty("country_code") String countryCode, @JsonProperty("state_code") String stateCode, @JsonProperty("timezone") String timezone,
            @JsonProperty("data") List<Weather> weatherList) {
        this.countryCode = countryCode;
        this.stateCode = stateCode;
        this.timezone = timezone;
        this.weatherList = weatherList;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public LocalDateTime getCoolestHourOfDay() {
        return coolestHourOfDay;
    }

    public void setCoolestHourOfDay(LocalDateTime coolestHourOfDay) {
        this.coolestHourOfDay = coolestHourOfDay;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }
}