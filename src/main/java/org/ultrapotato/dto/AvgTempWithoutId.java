package org.ultrapotato.dto;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

public class AvgTempWithoutId {
	@ApiModelProperty(value = "начало периода, включительно")
	private LocalDate startDay;
	@ApiModelProperty(value = "конец периода, не включительно")
	private LocalDate endDay;
	@ApiModelProperty(value = "средняя температура")
	private Double averageTemperature;

	public AvgTempWithoutId(LocalDate startDay, LocalDate endDay, Double averageTemperature) {
		this.startDay = startDay;
		this.endDay = endDay;
		this.averageTemperature = averageTemperature;
	}

	public LocalDate getStartDay() {
		return startDay;
	}

	public void setStartDay(LocalDate startDay) {
		this.startDay = startDay;
	}

	public LocalDate getEndDay() {
		return endDay;
	}

	public void setEndDay(LocalDate endDay) {
		this.endDay = endDay;
	}

	public Double getAverageTemperature() {
		return averageTemperature;
	}

	public void setAverageTemperature(Double averageTemperature) {
		this.averageTemperature = averageTemperature;
	}
}
