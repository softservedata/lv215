/*
 * SubjectService.java
 * 1.0
 * 03 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.mongodb.gridfs.GridFSDBFile;
import com.softserve.edu.schedule.dto.FileForSubjectDTO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.SubjectFilter;

/**
 * A simple service interface to handle the operation required to manipulate an
 * Subject object and SubjectDTO object.
 *
 * @version 1.0 04 Jan 2016
 * @author Ped'ko Volodymyr
 *
 */
public interface SubjectService {

	/**
	 * Field for subject metadata.
	 */
	String SUBJECT_METADATA_ID = "metadata.subjectId";

	/**
	 * Field for subject id.
	 */
	String SUBJECT_ID = "subjectId";

	/**
	 * Field for content disposition.
	 */
	String CONTENT_DISPOSITION = "content-Disposition";

	/**
	 * Field for attachment and file name.
	 */
	String ATTACHMENT_FILENAME = "attachment; filename=";

	/**
	 * Saving Subject in database.
	 *
	 * @param subject
	 *            - SubjectDTO object
	 */
	public void create(final SubjectDTO subject);

	/**
	 * Updating Subject in database.
	 *
	 * @param subject
	 *            - SubjectDTO object
	 */
	public void update(final SubjectDTO subject);

	/**
	 * Return a SubjectDTO object if found.
	 *
	 * @param id
	 *            of Subject transfer object
	 * 
	 * @return SubjectDTO object
	 */
	public SubjectDTO getById(final Long id);

	/**
	 * Return a List of SubjectDTO objects.
	 *
	 * @return List of SubjectDTO objects
	 */
	public List<SubjectDTO> getAll();

	/**
	 * Get all UserForSubjectDTO.
	 *
	 * @return List of the UserForSubjectDTO objects for SubjectDTO.
	 */
	public List<UserForSubjectDTO> getAllUserForSubjectDTO();

	/**
	 * Delete existed Subject from the database by id.
	 *
	 * @param id
	 *            a SubjectDTO id to delete from database.
	 */
	public void deleteById(final Long id);

	/**
	 * Return a searched SubjectDTO.
	 *
	 * @return searched SubjectDTO
	 */
	public List<SubjectDTO> getSubjectByName(final String subjectName);

	/**
	 * Find all subjects entities in the database with applied filter
	 * 
	 * @param subjectFilter
	 *            a filter to apply.
	 * 
	 * @param subjectPaginator
	 *            the subjectPaginator to set.
	 * 
	 * @return List of the subject DTO objects.
	 */
	public List<SubjectDTO> getSubjectsPageWithFilter(
	        final SubjectFilter subjectFilter,
	        final Paginator subjectPaginator);

	/**
	 * Method for uploading files.
	 * 
	 * @param fileForSubjectDTO
	 * 
	 * @throws IOException
	 */
	public void uploadFile(final FileForSubjectDTO fileForSubjectDTO)
	        throws IOException;

	/**
	 * Method for showing subjects files on show page.
	 * 
	 * @param id
	 * 
	 * @return List of subject files.
	 */
	public List<String> showSubjectFiles(final Long id);

	/**
	 * Method for deleting file form subject.
	 * 
	 * @param id
	 * 
	 * @param fileName
	 * 
	 */
	public void deleteSubjectFileById(final Long id, final String fileName);

	/**
	 * Method for downloading file.
	 * 
	 * @param id
	 * 
	 * @param fileName
	 * 
	 * @param response
	 * 
	 * @throws IOException
	 */
	public void retriveSubjectFileById(final Long id, final String fileName,
	        final HttpServletResponse response) throws IOException;

	/**
	 * Method for validation purpose.
	 * 
	 * @param id
	 * 
	 * @param fileName
	 * 
	 * @return GridFSDBFile object
	 */
	public GridFSDBFile retriveSubjectFileById(final String id,
	        final String fileName);
}
