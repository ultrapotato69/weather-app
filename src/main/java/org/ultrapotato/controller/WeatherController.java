package org.ultrapotato.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ultrapotato.domain.AverageTemperature;
import org.ultrapotato.dto.AvgTempWithoutId;
import org.ultrapotato.service.WeatherService;

import java.time.LocalDate;

@RestController
@RequestMapping("/weather/novosibirsk/akademgorodok/")
public class WeatherController {

	WeatherService weatherService;

	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@GetMapping("average_temperature")
	ResponseEntity<AvgTempWithoutId> getAverageTemperature(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
			){
		AvgTempWithoutId avgTemp = weatherService.getAvgTemp(start, end);

		return ResponseEntity.ok(avgTemp);
	}

}
