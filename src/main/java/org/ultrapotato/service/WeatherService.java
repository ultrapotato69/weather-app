package org.ultrapotato.service;

import org.ultrapotato.dto.AvgTempWithoutId;

import java.time.LocalDate;

public interface WeatherService {
	AvgTempWithoutId getAvgTemp(LocalDate startDay, LocalDate endDay);
}
