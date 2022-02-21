package org.ultrapotato.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ultrapotato.client.WeatherRestClient;
import org.ultrapotato.domain.AverageTemperature;
import org.ultrapotato.dto.AvgTempWithoutId;
import org.ultrapotato.repository.AverageTemperatureRepo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
	@Mock
	private AverageTemperatureRepo averageTemperatureRepo;
	@Mock
	private WeatherRestClient weatherRestClient;
	@InjectMocks
	private WeatherServiceImpl weatherServiceImpl;

	LocalDate endDay;
	LocalDate startDay;
	Double averageTemperature;
	AverageTemperature savedAvgTemp;
	AverageTemperature avgTempNullId;
	AvgTempWithoutId avgTempWithoutId;

	@BeforeEach
	void init(){
		startDay = LocalDate.of(2016, 12, 20);
		endDay  = LocalDate.of(2016, 12, 25);
		averageTemperature = 10.0;

		savedAvgTemp = new AverageTemperature();
		savedAvgTemp.setAverageTemperature(averageTemperature);
		savedAvgTemp.setStartDay(startDay);
		savedAvgTemp.setEndDay(endDay);
		savedAvgTemp.setId(123L);

		avgTempNullId = new AverageTemperature(averageTemperature, startDay, endDay);

		avgTempWithoutId = new AvgTempWithoutId(
				savedAvgTemp.getStartDay(),
				savedAvgTemp.getEndDay(),
				savedAvgTemp.getAverageTemperature());
	}

	@Test
	void GetAvgTempFoundedInDBTest() {
		when(averageTemperatureRepo.findByStartDayAndEndDay(startDay, endDay)).thenReturn(savedAvgTemp);
		AvgTempWithoutId avgTemp = weatherServiceImpl.getAvgTemp(startDay, endDay);
		assertEquals(avgTemp, avgTempWithoutId);
	}

	@Test
	void GetAvgTempNotFoundedInDBTest() {
		when(averageTemperatureRepo.findByStartDayAndEndDay(startDay, endDay)).thenReturn(null);
		when(averageTemperatureRepo.save(any())).thenReturn(savedAvgTemp);
		AvgTempWithoutId avgTemp = weatherServiceImpl.getAvgTemp(startDay, endDay);
		assertEquals(avgTemp, avgTempWithoutId);
		verify(weatherRestClient).findAverageTemperature(startDay, endDay);
	}


}

