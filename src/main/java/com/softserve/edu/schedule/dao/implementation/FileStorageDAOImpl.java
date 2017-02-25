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

@Repository
public class FileStorageDAOImpl implements FileStorageDAO {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public String store(InputStream inputStream, String fileName,
            DBObject metadata) {
        return gridFsTemplate.store(inputStream, fileName, metadata).getId()
                .toString();
    }

    public GridFSDBFile getById(String id) {
        return gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
    }

    public GridFSDBFile getByFilename(String fileName) {
        return gridFsTemplate
                .findOne(new Query(Criteria.where("filename").is(fileName)));
    }

    public GridFSDBFile retrive(String fileName) {
        return gridFsTemplate
                .findOne(new Query(Criteria.where("filename").is(fileName)));
    }

    public List<GridFSDBFile> findAll() {
        return gridFsTemplate.find(null);
    }

    @Override
    public void delete(String id) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
    }

    @Override
    public List<GridFSDBFile> findAllByIdAndType(String id, String type) {
        return gridFsTemplate.find(new Query(Criteria.where(type).is(id)));
    }

    @Override
    public void deleteById(String pattern, String id) {
        gridFsTemplate.delete(new Query(Criteria.where(pattern).is(id)));
    }

    @Override
    public GridFSDBFile retriveByIdAndFileName(String id, String fileName,
            String type) {
        return gridFsTemplate.findOne(new Query(
                Criteria.where(type).is(id).and("filename").is(fileName)));
    }
    
    @Override
    public GridFSDBFile findByIdAndType(String id, String type) {
        return gridFsTemplate.findOne(new Query(Criteria.where(type).is(id)));
    }
    
	@Override
	public void deleteByIdAndFileName(String id, String fileName, String type) {
		gridFsTemplate.delete(new Query(
		        Criteria.where(type).is(id).and("filename").is(fileName)));

	}
}
