package com.sr.springcircuitbreaker.service;

import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    public String getWeather(String city) {
        //Simulation of weather API call
        if("error".equals(city)){
            throw new RuntimeException("Weather API call failed with error");
        } else if ("timeput".equalsIgnoreCase(city)) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            throw new RuntimeException("Weather API call timeput");
        }
        return "Sunny in "+city;
    }
}
