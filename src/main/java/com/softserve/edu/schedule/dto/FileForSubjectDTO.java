package com.softserve.edu.schedule.dto;

import org.springframework.web.multipart.MultipartFile;

import com.softserve.edu.schedule.service.implementation.validators.Validate;

@Validate
public class FileForSubjectDTO {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
