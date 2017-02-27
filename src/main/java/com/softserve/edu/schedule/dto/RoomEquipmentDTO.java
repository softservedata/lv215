/* RoomEquipmentDTO 1.0 01/17/2017 */
package com.softserve.edu.schedule.dto;

import com.softserve.edu.schedule.service.implementation.validators.Validate;

/**
 * A DTO class to transport room equipment data.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Validate
public class RoomEquipmentDTO {

    /**
     * Id for database.
     */
    private Long id;

    /**
     * Equipment name.
     */
    private String name;

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
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

}
