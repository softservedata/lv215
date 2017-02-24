package com.softserve.edu.schedule.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileForSubjectDTO {

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
