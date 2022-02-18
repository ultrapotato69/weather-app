package org.ultrapotato.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
public class AverageTemperature {

	@Id
	@GeneratedValue
	private Long id;
	private LocalDate startDay;
	private LocalDate endDay;
	private Double averageTemperature;


	public AverageTemperature() {
	}

	public AverageTemperature(Double averageTemperature, LocalDate startDay, LocalDate endDay) {
		this.averageTemperature = averageTemperature;
		this.startDay = startDay;
		this.endDay = endDay;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
