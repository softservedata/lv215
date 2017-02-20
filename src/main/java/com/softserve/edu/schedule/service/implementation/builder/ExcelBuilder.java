package com.softserve.edu.schedule.service.implementation.builder;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.softserve.edu.schedule.entity.MeetingHistory;

import org.apache.poi.ss.usermodel.Row;

import java.text.DateFormat;

import java.util.List;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * 
 * @author www.codejava.net
 *
 */
@Component
public class ExcelBuilder extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        final DateFormat DATE_FORMAT = DateFormat
                .getDateInstance(DateFormat.SHORT);

        @SuppressWarnings("unchecked")
        List<MeetingHistory> listMH = (List<MeetingHistory>) model
                .get("listMeetingHistory");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Meeting History");

        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("IdMeeting");
        header.createCell(2).setCellValue("Subject");
        header.createCell(3).setCellValue("Owner");
        header.createCell(4).setCellValue("Location");
        header.createCell(5).setCellValue("Address");
        header.createCell(6).setCellValue("Room");
        header.createCell(7).setCellValue("Date");
        header.createCell(8).setCellValue("StartTime");
        header.createCell(9).setCellValue("EndTime");
        header.createCell(10).setCellValue("Groups");
        header.createCell(11).setCellValue("Description");

        // Create data cells
        int rowCount = 1;
        for (MeetingHistory meetingHistory : listMH) {
            Row courseRow = sheet.createRow(rowCount++);
            courseRow.createCell(0).setCellValue(meetingHistory.getId());
            courseRow.createCell(1).setCellValue(meetingHistory.getIdMeeting());
            courseRow.createCell(2).setCellValue(meetingHistory.getSubject());
            courseRow.createCell(3).setCellValue(meetingHistory.getOwner());
            courseRow.createCell(4).setCellValue(meetingHistory.getLocation());
            courseRow.createCell(5).setCellValue(meetingHistory.getAddress());
            courseRow.createCell(6).setCellValue(meetingHistory.getRoom());
            courseRow.createCell(7)
                    .setCellValue(DATE_FORMAT.format(meetingHistory.getDate()));
            courseRow.createCell(8)
                    .setCellValue(meetingHistory.getStartTime().toString());
            courseRow.createCell(9)
                    .setCellValue(meetingHistory.getEndTime().toString());
            courseRow.createCell(10).setCellValue(meetingHistory.getGroups());
            courseRow.createCell(11)
                    .setCellValue(meetingHistory.getDescription());

        }
    }
    // TODO Auto-generated method stub

}