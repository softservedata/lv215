/*
 * FileForSubjectDTO.java
 * 1.0
 * 20 Feb 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dto;

import org.springframework.web.multipart.MultipartFile;

import com.softserve.edu.schedule.service.implementation.validators.Validate;

/**
 * A DTO class to transport file data.
 *
 * @version 1.0 20 Feb 2017
 *
 * @author Ped'ko Volodymyr
 *
 * @since 1.8
 */
@Validate
public class FileForSubjectDTO {

    /**
     * FileForSubjectDTO id.
     */
	private Long id;
	
    /**
     * MultipartFile file.
     */
	private MultipartFile file;

    /**
     * @return the id
     */
	public Long getId() {
		return id;
	}

    /**
     * @param id
     *            the id to set
     */
	public void setId(final Long id) {
		this.id = id;
	}

    /**
     * @return the file
     */
	public MultipartFile getFile() {
		return file;
	}

    /**
     * @param file
     *            the file to set
     */
	public void setFile(final MultipartFile file) {
		this.file = file;
	}
}
