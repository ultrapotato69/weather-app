package org.ultrapotato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ultrapotato.domain.AverageTemperature;

import java.time.LocalDate;

@Repository
public interface AverageTemperatureRepo extends JpaRepository<AverageTemperature, Long> {
	AverageTemperature findByStartDayAndEndDay(LocalDate startDay, LocalDate endDay);
}
