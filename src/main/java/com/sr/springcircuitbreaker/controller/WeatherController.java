package com.sr.springcircuitbreaker.controller;

import com.sr.springcircuitbreaker.service.WeatherService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    int retryCount = 0;


    @GetMapping("/weather")
    @CircuitBreaker(name = "getWeatherCircuitBreaker", fallbackMethod = "fallbackGetWeatherCircuitBreaker")
    @Retry(name = "getWeatherCircuitBreaker", fallbackMethod = "fallbackGetWeatherRetry")
    @RateLimiter(name = "getWeatherCircuitBreaker", fallbackMethod = "fallbackGetWeatherRateLimit")
    public String getWeather(@RequestParam String city) {
        System.out.println("fallbackGetWeather retryCount = "+retryCount++ +" at "+ LocalDateTime.now());

        return weatherService.getWeather(city);
    }
    public String fallbackGetWeatherCircuitBreaker(String city, Throwable throwable) {
        System.out.println("fallbackGetWeatherCircuitBreaker retryCount = "+retryCount);
        return String.format("fallbackGetWeatherCircuitBreaker: Weather service is currently unavailable for %s. Please try later", city);
    }
    public String fallbackGetWeatherRetry(String city, Throwable throwable) {
        System.out.println("fallbackGetWeatherRetry retryCount = "+retryCount);
        return String.format("fallbackGetWeatherRetry: Weather service is currently unavailable for %s. Please try later", city);
    }
    public String fallbackGetWeatherRateLimit(String city, Throwable throwable) {
        System.out.println("fallbackGetWeatherRateLimit retryCount = "+retryCount);
        return String.format("fallbackGetWeatherRateLimit: Weather service is currently unavailable for %s. Please try later", city);
    }
}
