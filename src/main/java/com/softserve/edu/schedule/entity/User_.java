package com.softserve.edu.schedule.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> mail;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, String> position;
    public static volatile SingularAttribute<User, String> pathImage;
    public static volatile SingularAttribute<User, UserStatus> status;
    public static volatile SingularAttribute<User, UserRole> role;
    public static volatile ListAttribute<User, Subject> subjects;
    public static volatile ListAttribute<User, UserGroup> groups;
}
