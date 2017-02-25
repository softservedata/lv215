/* UserConnectionKey 1.0 02/18/2017 */
package com.softserve.edu.schedule.entity;

import java.io.Serializable;

/**
 * Composite key for UserConnection entity.
 *
 * @version 1.0 18 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class UserConnectionKey implements Serializable {

    private static final long serialVersionUID = 2223465537802726241L;

    /**
     * User id.
     */
    private String userId;

    /**
     * Provider id.
     */
    private String providerId;

    /**
     * Provider user id.
     */
    private String providerUserId;

    /**
     * Default constructor.
     */
    public UserConnectionKey() {
    }

    /**
     * Parameterized constructor.
     *
     * @param userId
     *            user id.
     *
     * @param providerId
     *            provider id.
     *
     * @param providerUserId
     *            provider user id.
     *
     */
    public UserConnectionKey(final String userId, final String providerId,
            final String providerUserId) {
        this.userId = userId;
        this.providerId = providerId;
        this.providerUserId = providerUserId;
    }

    /**
     * Calculate hashcode.
     *
     * @return hashcode.
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((providerId == null) ? 0 : providerId.hashCode());
        result = prime * result
                + ((providerUserId == null) ? 0 : providerUserId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    /**
     * Check equality.
     *
     * @param obj
     *            object to check equality
     *
     * @return true if equals.
     *
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserConnectionKey other = (UserConnectionKey) obj;
        if (providerId == null) {
            if (other.providerId != null) {
                return false;
            }
        } else if (!providerId.equals(other.providerId)) {
            return false;
        }
        if (providerUserId == null) {
            if (other.providerUserId != null) {
                return false;
            }
        } else if (!providerUserId.equals(other.providerUserId)) {
            return false;
        }
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        return true;
    }

}
