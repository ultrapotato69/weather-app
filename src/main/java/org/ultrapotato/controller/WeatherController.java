package org.ultrapotato.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ultrapotato.dto.AvgTempWithoutId;
import org.ultrapotato.service.WeatherService;

import java.time.LocalDate;

@RestController
@RequestMapping("/weather/novosibirsk/akademgorodok/")
public class WeatherController {

	private final WeatherService weatherService;

	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@ApiOperation(value = "Получить среднюю температуру",
			notes = "Получить сведения о средней температуре в Академгородке на период дней в формате yyyy-mm-dd." +
					" Наблюдения по температуре ведутся с 11 марта 2016 года")
	@GetMapping("average_temperature")
	ResponseEntity<AvgTempWithoutId> getAverageTemperature(
			@RequestParam
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@ApiParam(required = true, value = "начало периода, включительно", defaultValue = "2020-02-01")
					LocalDate start,
			@RequestParam
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@ApiParam(required = true, value = "конец периода, не включительно", defaultValue = "2020-03-01")
					LocalDate end
			){
		AvgTempWithoutId avgTemp = weatherService.getAvgTemp(start, end);
		return ResponseEntity.ok(avgTemp);
	}

}
