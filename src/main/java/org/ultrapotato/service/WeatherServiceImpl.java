package org.ultrapotato.service;

import org.springframework.stereotype.Service;
import org.ultrapotato.client.WeatherRestClient;
import org.ultrapotato.domain.AverageTemperature;
import org.ultrapotato.dto.AvgTempWithoutId;
import org.ultrapotato.repository.AverageTemperatureRepo;

import java.time.LocalDate;

@Service
public class WeatherServiceImpl implements WeatherService {

	private final WeatherRestClient weatherServiceRest;
	private final AverageTemperatureRepo averageTemperatureRepo;

	public WeatherServiceImpl(WeatherRestClient weatherServiceRest, AverageTemperatureRepo averageTemperatureRepo) {
		this.weatherServiceRest = weatherServiceRest;
		this.averageTemperatureRepo = averageTemperatureRepo;
	}

	@Override
	public AvgTempWithoutId getAvgTemp(LocalDate startDay, LocalDate endDay) {
		AvgTempWithoutId avgTempWithoutId;
		AverageTemperature avgTempFromDb = averageTemperatureRepo.findByStartDayAndEndDay(startDay, endDay);
		if (avgTempFromDb != null){
			avgTempWithoutId = new AvgTempWithoutId(
					avgTempFromDb.getStartDay(),
					avgTempFromDb.getEndDay(),
					avgTempFromDb.getAverageTemperature());
		} else {
			double avg = weatherServiceRest.findAverageTemperature(startDay, endDay);
			AverageTemperature avgTempFromRest = new AverageTemperature(avg, startDay, endDay);
			AverageTemperature savedAvgTemp = averageTemperatureRepo.save(avgTempFromRest);
			avgTempWithoutId = new AvgTempWithoutId(
					savedAvgTemp.getStartDay(),
					savedAvgTemp.getEndDay(),
					savedAvgTemp.getAverageTemperature());
		}

		return avgTempWithoutId;
	}
}
