package com.softserve.edu.schedule.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Room.class)
public class Room_ {
    public static volatile SingularAttribute<Room, Long> id;
    public static volatile SingularAttribute<Room, String> name;
    public static volatile SingularAttribute<Room, Integer> capacity;
    public static volatile SingularAttribute<Room, Location> location;
    public static volatile ListAttribute<Room, RoomEquipment> equipments;
    public static volatile ListAttribute<Room, Meeting> meetings;
}
