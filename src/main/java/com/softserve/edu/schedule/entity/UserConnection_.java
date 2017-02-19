package com.softserve.edu.schedule.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserConnection.class)
public class UserConnection_ {
    public static volatile SingularAttribute<UserConnection, String> userId;
    public static volatile SingularAttribute<UserConnection, String> providerId;
    public static volatile SingularAttribute<UserConnection, String> providerUserId;
    public static volatile SingularAttribute<UserConnection, Integer> rank;
    public static volatile SingularAttribute<UserConnection, String> displayName;
    public static volatile SingularAttribute<UserConnection, String> profileUrl;
    public static volatile SingularAttribute<UserConnection, String> imageUrl;
    public static volatile SingularAttribute<UserConnection, String> accessToken;
    public static volatile SingularAttribute<UserConnection, String> secret;
    public static volatile SingularAttribute<UserConnection, String> refreshToken;
    public static volatile SingularAttribute<UserConnection, Long> expireTime;

}
