package com.softserve.edu.schedule.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserGroup.class)
public class UserGroup_ {
    public static volatile SingularAttribute<UserGroup, Long> id;
    public static volatile SingularAttribute<UserGroup, String> name;
    public static volatile SingularAttribute<UserGroup, String> description;
    public static volatile SingularAttribute<UserGroup, Integer> level;
    public static volatile SingularAttribute<UserGroup, User> curator;
    public static volatile ListAttribute<UserGroup, User> users;
    public static volatile ListAttribute<UserGroup, Meeting> meetings;
}
