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

    @Override
    public String store(final InputStream inputStream, final String fileName,
            final DBObject metadata) {
        return gridFsTemplate.store(inputStream, fileName, metadata).getId()
                .toString();
    }

    @Override
    public List<GridFSDBFile> findAllByIdAndType(final String id,
            final String type) {
        return gridFsTemplate.find(new Query(Criteria.where(type).is(id)));
    }

    @Override
    public void deleteById(final String pattern, final String id) {
        gridFsTemplate.delete(new Query(Criteria.where(pattern).is(id)));
    }

    @Override
    public GridFSDBFile retriveByIdAndFileName(final String id,
            final String fileName, final String type) {
        return gridFsTemplate.findOne(new Query(
                Criteria.where(type).is(id).and("filename").is(fileName)));
    }

    @Override
    public void deleteByIdAndFileName(final String id, final String fileName,
            final String type) {
        gridFsTemplate.delete(new Query(
                Criteria.where(type).is(id).and("filename").is(fileName)));
    }

    @Override
    public GridFSDBFile findByIdAndType(final String id, final String type) {
        return gridFsTemplate.findOne(new Query(Criteria.where(type).is(id)));
    }

}
