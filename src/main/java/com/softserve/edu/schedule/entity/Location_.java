package com.softserve.edu.schedule.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Location.class)
public class Location_ {
    public static volatile SingularAttribute<Location, Long> id;
    public static volatile SingularAttribute<Location, String> name;
    public static volatile SingularAttribute<Location, String> address;
    public static volatile SingularAttribute<Location, String> coordinates;
    public static volatile ListAttribute<Location, Room> rooms;
}
