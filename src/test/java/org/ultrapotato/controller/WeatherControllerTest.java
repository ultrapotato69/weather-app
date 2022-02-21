package org.ultrapotato.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.ultrapotato.dto.AvgTempWithoutId;
import org.ultrapotato.service.WeatherService;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

	@MockBean
	private WeatherService weatherService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void getOkAvgTempResponse() throws Exception {
		AvgTempWithoutId avgTempWithoutId = new AvgTempWithoutId(
				LocalDate.of(2020, 5 , 8 ),
				LocalDate.of(2020, 5, 10),
				5.5);

		when(weatherService	.getAvgTemp(
				eq(LocalDate.of(2020, 5 , 8 )),
				eq(LocalDate.of(2020, 5, 10))))
				.thenReturn(avgTempWithoutId);

		mockMvc.perform(get("/weather/novosibirsk/akademgorodok/average_temperature")
				.param("start", "2020-05-08").param("end", "2020-05-10"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.averageTemperature").value("5.5"))
				.andExpect(jsonPath("$.startDay").value("2020-05-08"))
				.andExpect(jsonPath("$.endDay").value("2020-05-10"));
	}

	@Test
	void getBadRequestAvgTempResponse() throws Exception {
		mockMvc.perform(get("/weather/novosibirsk/akademgorodok/average_temperature"))
				.andExpect(status().isBadRequest());
	}


}