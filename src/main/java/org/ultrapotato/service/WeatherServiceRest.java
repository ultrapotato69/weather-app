package org.ultrapotato.service;

import java.time.LocalDate;

public interface WeatherServiceRest {
	double findAverageTemperature(LocalDate startDay, LocalDate endDay);
}
