package com.softserve.edu.schedule.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Subject.class)
public class Subject_ {
    public static volatile SingularAttribute<Subject, Long> id;
    public static volatile SingularAttribute<Subject, String> name;
    public static volatile SingularAttribute<Subject, String> description;
    public static volatile ListAttribute<Subject, User> users;
    public static volatile ListAttribute<Subject, Meeting> meetings;
}
