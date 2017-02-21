package com.softserve.edu.schedule.dto;

public class MeetingForChartDTO {
	private String subject;
	private Long time;

	public MeetingForChartDTO(String subject, Long time) {
		this.subject = subject;
		this.time = time;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

}
