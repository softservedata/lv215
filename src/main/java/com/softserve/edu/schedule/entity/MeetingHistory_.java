package com.softserve.edu.schedule.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MeetingHistory.class)
public class MeetingHistory_ {
	public static volatile SingularAttribute<MeetingHistory, Long> id;
	public static volatile SingularAttribute<MeetingHistory, String> idMeeting;
	public static volatile SingularAttribute<MeetingHistory, String> subject;
	public static volatile SingularAttribute<MeetingHistory, String> room;
	public static volatile SingularAttribute<MeetingHistory, String> location;
	public static volatile SingularAttribute<MeetingHistory, String> address;
	public static volatile SingularAttribute<MeetingHistory, LocalDate> date;
	public static volatile SingularAttribute<MeetingHistory, LocalTime> startTime;
	public static volatile SingularAttribute<MeetingHistory, LocalTime> endTime;
	public static volatile SingularAttribute<MeetingHistory, String> groups;
	public static volatile SingularAttribute<MeetingHistory, String> owner;
	public static volatile SingularAttribute<MeetingHistory, String> description;
}
