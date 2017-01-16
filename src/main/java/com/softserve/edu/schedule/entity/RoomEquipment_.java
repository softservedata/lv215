package com.softserve.edu.schedule.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(RoomEquipment.class)
public class RoomEquipment_ {
    public static volatile SingularAttribute<RoomEquipment, Long> id;
    public static volatile SingularAttribute<RoomEquipment, String> name;
    public static volatile ListAttribute<RoomEquipment, Room> rooms;
}
