/* UserGroupForAndroidDTO 1.0 02/21/2017 */
package com.softserve.edu.schedule.dto;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.schedule.entity.UserGroupLevel;

/**
 * A DTO class to transport userGroup data to android devices.
 *
 * @version 1.0 21 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class UserGroupForAndroidDTO {

    /**
     * User group id.
     */
    private Long id;

    /**
     * User group name.
     */
    private String name;

    /**
     * User group description.
     */
    private String description;

    /**
     * Level of the group. The higher the value - the more important group.
     */
    private UserGroupLevel level;

    /**
     * User group manager.
     */
    private String curatorFullName;

    /**
     * List of users in this group.
     */
    private List<String> usersFullNames = new ArrayList<>();

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the level
     */
    public UserGroupLevel getLevel() {
        return level;
    }

    /**
     * @return the curatorFullName
     */
    public String getCuratorFullName() {
        return curatorFullName;
    }

    /**
     * @return the usersFullNames
     */
    public List<String> getUsersFullNames() {
        return usersFullNames;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(UserGroupLevel level) {
        this.level = level;
    }

    /**
     * @param curatorFullName
     *            the curatorFullName to set
     */
    public void setCuratorFullName(String curatorFullName) {
        this.curatorFullName = curatorFullName;
    }

    /**
     * @param usersFullNames
     *            the usersFullNames to set
     */
    public void setUsersFullNames(List<String> usersFullNames) {
        this.usersFullNames = usersFullNames;
    }

}
