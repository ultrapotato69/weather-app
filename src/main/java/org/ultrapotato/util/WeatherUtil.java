package org.ultrapotato.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class WeatherUtil {

	public static double calcAvgTemperature(String[] body) {
		final DecimalFormat dfZero = new DecimalFormat("##.#");;
		double avg = 0;
		for (int i = 0; i < body.length; i++) {
			if (i % 2 != 0) {
				avg += Double.parseDouble(body[i]);
			}
		}
		NumberFormat nf = NumberFormat.getInstance();
		try {
			avg = nf.parse(dfZero.format(avg / (body.length / 2))).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return avg;
	}

	public static String formatLocalDate(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd+LLL+yyyy", new Locale("en", "EN"));
		return localDate.format(formatter);
	}
}
