package org.ultrapotato.client;

import java.time.LocalDate;

public interface WeatherRestClient {
	double findAverageTemperature(LocalDate startDay, LocalDate endDay);
}
