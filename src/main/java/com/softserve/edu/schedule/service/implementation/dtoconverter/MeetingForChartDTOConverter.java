/* MeetingForChartDTOConverter 1.0 4 Feb 2017 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.softserve.edu.schedule.entity.Meeting;

/**
 * This class provides conversion operation between list of meetings and data
 * for building charts
 *
 * @version 1.0 04 February 2017
 *
 * @author Oleksandr Butyter
 */
@Component
public class MeetingForChartDTOConverter {

	/**
	 * Method returns data for building charts by list of meetings.
	 * 
	 * @param meetings
	 *            list of meetings to convert
	 * @return data for building charts
	 */
	public Map<String, Long> getChartData(final List<Meeting> meetings) {
		Map<String, Long> chartData = new HashMap<>();
		meetings.forEach(e -> {
			if (!chartData.containsKey(e.getSubject().getName())) {
				chartData.put(e.getSubject().getName(), e.getStartTime().until(e.getEndTime(), ChronoUnit.MINUTES));
			} else {
				chartData.put(e.getSubject().getName(), chartData.get(e.getSubject().getName())
						+ e.getStartTime().until(e.getEndTime(), ChronoUnit.MINUTES));
			}
		});
		return chartData;
	}

}
