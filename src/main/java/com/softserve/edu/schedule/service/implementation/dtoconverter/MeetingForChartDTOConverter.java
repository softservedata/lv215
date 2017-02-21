package com.softserve.edu.schedule.service.implementation.dtoconverter;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.softserve.edu.schedule.dto.MeetingForChartDTO;
import com.softserve.edu.schedule.entity.Meeting;

@Component
public class MeetingForChartDTOConverter {

	public List<MeetingForChartDTO> getListDTO(List<Meeting> meetings){
		List<MeetingForChartDTO> meetingsForChartDTO = new ArrayList<>();
		for(Meeting meeting: meetings){
			if(1==0){
				
			} else {
				meetingsForChartDTO.add(new MeetingForChartDTO(meeting.getSubject().getName(), meeting.getStartTime().until(meeting.getEndTime(), ChronoUnit.MINUTES)));
			}
		}
		return meetingsForChartDTO;
	}
	
}

