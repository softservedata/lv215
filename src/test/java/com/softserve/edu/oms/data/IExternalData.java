package com.softserve.edu.oms.data;
import java.util.List;

public interface IExternalData {

	List<List<String>> getAllCells(String absoluteFilePath, String sheetName);

}