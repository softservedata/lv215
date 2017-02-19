/* UserConnection 1.0 02/18/2017 */
package com.softserve.edu.schedule.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * An entity class for storage user social connections.
 *
 * @version 1.0 18 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Entity
@Table(name = "userconnection")
@IdClass(UserConnectionKey.class)
public class UserConnection {

    /**
     * User id.
     */
    @Column(nullable = false)
    @Id
    private String userId;

    /**
     * Provider id.
     */
    @Column(nullable = false)
    @Id
    private String providerId;

    /**
     * Provider user id.
     */
    @Id
    private String providerUserId;

    /**
     * Connection rank.
     */
    @Column(nullable = false)
    private Integer rank;

    /**
     * User display name.
     */
    private String displayName;

    /**
     * User profile URL.
     */
    @Column(length = 512)
    private String profileUrl;

    /**
     * User image URL.
     */
    @Column(length = 512)
    private String imageUrl;

    /**
     * Provider access token.
     */
    @Column(nullable = false, length = 512)
    private String accessToken;

    /**
     * Provider secret.
     */
    @Column(length = 512)
    private String secret;

    /**
     * Provider refresh token.
     */
    @Column(length = 512)
    private String refreshToken;

    /**
     * Token expire time.
     */
    private Long expireTime;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * @return the providerUserId
     */
    public String getProviderUserId() {
        return providerUserId;
    }

    /**
     * @return the rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return the profileUrl
     */
    public String getProfileUrl() {
        return profileUrl;
    }

    /**
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @return the secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @return the refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @return the expireTime
     */
    public Long getExpireTime() {
        return expireTime;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * @param providerId
     *            the providerId to set
     */
    public void setProviderId(final String providerId) {
        this.providerId = providerId;
    }

    /**
     * @param providerUserId
     *            the providerUserId to set
     */
    public void setProviderUserId(final String providerUserId) {
        this.providerUserId = providerUserId;
    }

    /**
     * @param rank
     *            the rank to set
     */
    public void setRank(final Integer rank) {
        this.rank = rank;
    }

    /**
     * @param displayName
     *            the displayName to set
     */
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    /**
     * @param profileUrl
     *            the profileUrl to set
     */
    public void setProfileUrl(final String profileUrl) {
        this.profileUrl = profileUrl;
    }

    /**
     * @param imageUrl
     *            the imageUrl to set
     */
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @param accessToken
     *            the accessToken to set
     */
    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @param secret
     *            the secret to set
     */
    public void setSecret(final String secret) {
        this.secret = secret;
    }

    /**
     * @param refreshToken
     *            the refreshToken to set
     */
    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * @param expireTime
     *            the expireTime to set
     */
    public void setExpireTime(final Long expireTime) {
        this.expireTime = expireTime;
    }

}
