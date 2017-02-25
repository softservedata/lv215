/*
 * SubjectDAO.java
 * 1.0
 * 20 Feb 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dao.implementation;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.softserve.edu.schedule.dao.FileStorageDAO;

/**
 * A simple DAO interface to handle the NoSQL database operations.
 *
 * @version 1.0 20 Feb 2017
 * @author Ped'ko Volodymyr
 *
 */
@Repository
public class FileStorageDAOImpl implements FileStorageDAO {

	/**
	 * Field for gridFsTemplate.
	 */
    @Autowired
    private GridFsTemplate gridFsTemplate;

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
    @Override
    public String store(final InputStream inputStream, final String fileName,
            final DBObject metadata) {
        return gridFsTemplate.store(inputStream, fileName, metadata).getId()
                .toString();
    }

	/**
	 * Method for retrieving files from database.
	 * 
	 * @param id
	 * 
	 * @param type
	 * 
	 * @return GridFSDBFile type files from database
	 */
    @Override
    public List<GridFSDBFile> findAllByIdAndType(final String id,
            final String type) {
        return gridFsTemplate.find(new Query(Criteria.where(type).is(id)));
    }

	/**
	 * Method for deleting files from database.
	 * 
	 * @param pattern
	 * 
	 * @param id
	 */
    @Override
    public void deleteById(final String pattern, final String id) {
        gridFsTemplate.delete(new Query(Criteria.where(pattern).is(id)));
    }

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
    @Override
    public GridFSDBFile retriveByIdAndFileName(final String id,
            final String fileName, final String type) {
        return gridFsTemplate.findOne(new Query(
                Criteria.where(type).is(id).and("filename").is(fileName)));
    }

	/**
	 * Method for deleting files from database.
	 * 
	 * @param id
	 * 
	 * @param fileName
	 * 
	 * @param type
	 */
    @Override
    public void deleteByIdAndFileName(final String id, final String fileName,
            final String type) {
        gridFsTemplate.delete(new Query(
                Criteria.where(type).is(id).and("filename").is(fileName)));
    }

	/**
	 * Method for retrieving files from database.
	 * 
	 * @param id
	 * 
	 * @param type
	 * 
	 * @return GridFSDBFile type files from database
	 */
    @Override
    public GridFSDBFile findByIdAndType(final String id, final String type) {
        return gridFsTemplate.findOne(new Query(Criteria.where(type).is(id)));
    }

}
