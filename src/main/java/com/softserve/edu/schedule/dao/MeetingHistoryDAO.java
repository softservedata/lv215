package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.entity.MeetingHistory;

public interface MeetingHistoryDAO extends CrudDAO<MeetingHistory> {

	public List<MeetingHistory> getMeetingHistoryByIdMeeting(final String idMeeting);
	
}
