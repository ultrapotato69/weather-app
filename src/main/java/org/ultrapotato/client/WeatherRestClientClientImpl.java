package org.ultrapotato.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.ultrapotato.util.WeatherUtil;

import java.time.LocalDate;

@Component
public class WeatherRestClientClientImpl implements WeatherRestClient {
	private final RestTemplate restTemplate;
	private final String serverUrl;


	public WeatherRestClientClientImpl(
			RestTemplate restTemplate,
			@Value("${application.server.url}") String serverUrl
	) {
		this.restTemplate = restTemplate;
		this.serverUrl = serverUrl;
	}

	public double findAverageTemperature(LocalDate startDay, LocalDate endDay) {

		String urlTemplate = UriComponentsBuilder.fromHttpUrl(serverUrl)
				.queryParam("action", "temperature")
				.queryParam("dat1", WeatherUtil.formatLocalDate(startDay))
				.queryParam("dat2", WeatherUtil.formatLocalDate(endDay))
				.encode()
				.toUriString();

		String[] responseBody = restTemplate.getForEntity(urlTemplate, String[].class).getBody();
		return WeatherUtil.calcAvgTemperature(responseBody);
	}

}
