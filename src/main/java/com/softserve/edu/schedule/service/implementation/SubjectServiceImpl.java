/*
 * SubjectServiceImpl.java
 * 1.0
 * 03 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.softserve.edu.schedule.aspects.PerfomanceLoggable;
import com.softserve.edu.schedule.dao.FileStorageDAO;
import com.softserve.edu.schedule.dao.SubjectDAO;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.FileForSubjectDTO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.SubjectFilter;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.SubjectDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserForSubjectDTOConverter;

/**
 * A SubjectService implementation to handle the operation required to
 * manipulate an Subject object and Subject DTO object.
 *
 * @version 1.0 03 Jan 2016
 * @author Ped'ko Volodymyr
 *
 */
@Service
@PerfomanceLoggable
public class SubjectServiceImpl implements SubjectService {

	/**
	 * Field for subjectDAO.
	 */
	@Autowired
	private SubjectDAO subjectDao;

	/**
	 * Field for userDao.
	 */
	@Autowired
	private UserDAO userDao;

	@Autowired
	private FileStorageDAO fileStorageDao;

	/**
	 * Field for subjectDTOConverter.
	 */
	@Autowired
	private SubjectDTOConverter subjectDTOConverter;

	/**
	 * Field for userForSubjectDTOconverter.
	 */
	@Autowired
	private UserForSubjectDTOConverter userForSubjectDTOconverter;

	/**
	 * Saving Subject in database.
	 *
	 * @param subject
	 *            - SubjectDTO object
	 */
	@Override
	@Transactional
	public void create(final SubjectDTO subject) {
		subjectDao.create(subjectDTOConverter.getEntity(subject));
	}

	/**
	 * Updating Subject in database.
	 *
	 * @param subject
	 *            - SubjectDTO object
	 */
	@Override
	@Transactional
	public void update(final SubjectDTO subject) {
		subjectDao.update(subjectDTOConverter.getEntity(subject));
	}

	/**
	 * Return a SubjectDTO object if found.
	 *
	 * @param id
	 *            of Subject transfer object
	 * @return SubjectDTO object
	 */
	@Override
	@Transactional(readOnly = true)
	public SubjectDTO getById(final Long id) {
		return subjectDTOConverter.getDTO(subjectDao.getById(id));
	}

	/**
	 * Return a searched SubjectDTO.
	 *
	 * @return searched SubjectDTO
	 */
	@Override
	public List<SubjectDTO> getSubjectByName(final String subjectName) {
		return subjectDao.getSubjectByName(subjectName).stream()
		        .map(s -> subjectDTOConverter.getDTO(s))
		        .collect(Collectors.toList());
	}

	/**
	 * Return a List of SubjectDTO objects.
	 *
	 * @return List of SubjectDTO objects
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SubjectDTO> getAll() {
		return subjectDao.getAll().stream()
		        .map(s -> subjectDTOConverter.getDTO(s))
		        .collect(Collectors.toList());
	}

	/**
	 * Get all UserForSubjectDTO.
	 *
	 * @return List of the UserForSubjectDTO objects for SubjectDTO.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserForSubjectDTO> getAllUserForSubjectDTO() {
		return userDao.getAllActiveUsers().stream()
		        .map(u -> userForSubjectDTOconverter.getDTO(u))
		        .collect(Collectors.toList());
	}

	/**
	 * Delete existed Subject from the database by id.
	 *
	 * @param id
	 *            a SubjectDTO id to delete from database.
	 */
	@Override
	@Transactional
	public void deleteById(final Long id) {
		fileStorageDao.deleteById(SUBJECT_METADATA_ID, Long.toString(id));
		subjectDao.delete(subjectDao.getSubjectsWithMeetingDetailsById(id));
	}

	/**
	 * Find all subjects entities in the database with applied filter
	 * 
	 * @param subjectFilter
	 *            a filter to apply.
	 * @param subjectPaginator
	 *            the subjectPaginator to set
	 * @return List of the subject DTO objects.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SubjectDTO> getSubjectsPageWithFilter(
	        final SubjectFilter subjectFilter,
	        final Paginator subjectPaginator) {
		return subjectDao
		        .getSubjectsPageWithFilter(subjectFilter, subjectPaginator)
		        .stream().map(s -> subjectDTOConverter.getDTO(s))
		        .collect(Collectors.toList());
	}

	/**
	 * Method for uploading files.
	 * 
	 * @param fileForSubjectDTO
	 * 
	 * @throws IOException
	 */
	@Override
	public void uploadFile(final FileForSubjectDTO fileForSubjectDTO)
	        throws IOException {
		DBObject metadata = new BasicDBObject();
		metadata.put(SUBJECT_ID, Long.toString(fileForSubjectDTO.getId()));
		fileStorageDao.store(fileForSubjectDTO.getFile().getInputStream(),
		        fileForSubjectDTO.getFile().getOriginalFilename(), metadata);
	}

	/**
	 * Method for showing subjects files on show page.
	 * 
	 * @param id
	 * 
	 * @return List of subject files.
	 */
	@Override
	public List<String> showSubjectFiles(final Long id) {
		return fileStorageDao
		        .findAllByIdAndType(Long.toString(id), SUBJECT_METADATA_ID)
		        .stream().map(f -> f.getFilename())
		        .collect(Collectors.toList());
	}

	/**
	 * Method for deleting file form subject.
	 * 
	 * @param id
	 * 
	 * @param fileName
	 * 
	 */
	@Override
	public void deleteSubjectFileById(final Long id, final String fileName) {
		fileStorageDao.deleteByIdAndFileName(Long.toString(id), fileName,
		        SUBJECT_METADATA_ID);
	}

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
	@Override
	public void retriveSubjectFileById(final Long id, final String fileName,
	        HttpServletResponse response) throws IOException {
		GridFSDBFile file = fileStorageDao.retriveByIdAndFileName(
		        Long.toString(id), fileName, SUBJECT_METADATA_ID);
		response.setContentType(file.getContentType());
		response.setContentLength((new Long(file.getLength()).intValue()));
		response.setHeader(CONTENT_DISPOSITION,
		        ATTACHMENT_FILENAME + file.getFilename());
		IOUtils.copyLarge(file.getInputStream(), response.getOutputStream());
	}

	/**
	 * Method for validation purpose.
	 * 
	 * @param id
	 * 
	 * @param fileName
	 * 
	 * @return GridFSDBFile object
	 */
	@Override
	public GridFSDBFile retriveSubjectFileById(final String id,
	        final String fileName) {
		return fileStorageDao.retriveByIdAndFileName(id, fileName,
		        SUBJECT_METADATA_ID);
	}
}
