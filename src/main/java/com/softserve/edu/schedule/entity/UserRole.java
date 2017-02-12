/* UserRole 1.0 12/25/2016 */
package com.softserve.edu.schedule.entity;

/**
 * An enumeration of user roles.
 *
 * @version 1.0 25 December 2016
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public enum UserRole {

    /**
     * User role values.
     */
    ROLE_USER("simpleUser"), ROLE_MODERATOR("moderator"), ROLE_SUPERVISOR("supervisor"), ROLE_ADMIN("admin");

    /**
     * User role message code for localization purposes.
     */
    private String role;

    /**
     * User group level constructor.
     *
     * @param role
     *            user role
     */
    UserRole(final String role) {
        this.role = role;
    }

    /**
     * Message code getter.
     *
     * @return message code
     */
    public String getRole() {
        return role;
    }
}
