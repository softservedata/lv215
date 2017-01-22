package com.softserve.edu.schedule.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Meeting.class)
public class Meeting_ {
    public static volatile SingularAttribute<Meeting, Long> id;
    public static volatile SingularAttribute<Meeting, Subject> subject;
    public static volatile SingularAttribute<Meeting, Room> room;
    public static volatile SingularAttribute<Meeting, LocalDate> date;
    public static volatile SingularAttribute<Meeting, LocalTime> startTime;
    public static volatile SingularAttribute<Meeting, LocalTime> endTime;
    public static volatile ListAttribute<Meeting, UserGroup> groups;
    public static volatile SingularAttribute<Meeting, User> owner;
    public static volatile SingularAttribute<Meeting, MeetingStatus> status;
    public static volatile SingularAttribute<Meeting, Integer> level;
    public static volatile SingularAttribute<Meeting, String> description;
}
