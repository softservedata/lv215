/*
 * SubjectDAO.java
 * 1.0
 * 20 Feb 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dao;

import java.io.InputStream;
import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * A simple DAO interface to handle the NoSQL database operations.
 *
 * @version 1.0 20 Feb 2017
 * @author Ped'ko Volodymyr
 *
 */
public interface FileStorageDAO {

	/**
	 * Method for saving file in database.
	 *
	 * @param inputStream
	 * 
	 * @param fileName
	 * 
	 * @param metadata
	 * 
	 * @return file _id in database
	 */
	String store(InputStream inputStream, String fileName, DBObject metadata);

	/**
	 * Method for retrieving file from database.
	 *
	 * @param inputStream
	 * 
	 * @param fileName
	 * 
	 * @param type
	 * 
	 * @return GridFSDBFile type file from database
	 */
	GridFSDBFile retriveByIdAndFileName(String id, String fileName,
	        String type);

	/**
	 * Method for retrieving files from database.
	 * 
	 * @param id
	 * 
	 * @param type
	 * 
	 * @return GridFSDBFile type files from database
	 */
	List<GridFSDBFile> findAllByIdAndType(String id, String type);

	/**
	 * Method for deleting files from database.
	 * 
	 * @param pattern
	 * 
	 * @param id
	 */
	void deleteById(String pattern, String id);

	/**
	 * Method for retrieving files from database.
	 * 
	 * @param id
	 * 
	 * @param type
	 * 
	 * @return GridFSDBFile type files from database
	 */
	GridFSDBFile findByIdAndType(String id, String type);

	/**
	 * Method for deleting files from database.
	 * 
	 * @param id
	 * 
	 * @param fileName
	 * 
	 * @param type
	 */
	void deleteByIdAndFileName(String id, String fileName, String type);

}