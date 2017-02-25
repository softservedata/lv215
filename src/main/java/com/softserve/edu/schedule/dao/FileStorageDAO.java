package com.softserve.edu.schedule.dao;

import java.io.InputStream;
import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

public interface FileStorageDAO {

    String store(InputStream inputStream, String fileName, DBObject metadata);

    GridFSDBFile retriveByIdAndFileName(String id, String fileName,
            String type);

    List<GridFSDBFile> findAllByIdAndType(String id, String type);

    void deleteById(String pattern, String id);

    GridFSDBFile findByIdAndType(String id, String type);

    void deleteByIdAndFileName(String id, String fileName, String type);

}