package com.softserve.edu.schedule.controller.docviews;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.softserve.edu.schedule.entity.MeetingHistory;
import com.softserve.edu.schedule.service.MeetingHistoryService;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * 
 * @author www.codejava.net
 *
 */
public class ExcelView extends AbstractXlsView {

    @Autowired
    MeetingHistoryService meetingHistoryService;

    @Override
    public void buildExcelDocument(Map<String, Object> model, Workbook workbook,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=MeetingsArchive.xls");

        List<MeetingHistory> listMH = meetingHistoryService.getAll();
        
        Sheet sheet = workbook.createSheet("Meeting History");
        
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
        
        int rowCount = 1;
        for (MeetingHistory meetingHistory : listMH) {
            Row courseRow = sheet.createRow(rowCount++);
            courseRow.createCell(0).setCellValue(meetingHistory.getId());
            courseRow.createCell(1).setCellValue(Long.parseLong(meetingHistory.getIdMeeting()));
            courseRow.createCell(2).setCellValue(meetingHistory.getSubject());
            courseRow.createCell(3).setCellValue(meetingHistory.getOwner());
            courseRow.createCell(4).setCellValue(meetingHistory.getLocation());
            courseRow.createCell(5).setCellValue(meetingHistory.getAddress());
            courseRow.createCell(6).setCellValue(meetingHistory.getRoom());
            courseRow.createCell(7)
                    .setCellValue(meetingHistory.getDate().toString());
            courseRow.createCell(8)
                    .setCellValue(meetingHistory.getStartTime().toString());
            courseRow.createCell(9)
                    .setCellValue(meetingHistory.getEndTime().toString());
            courseRow.createCell(10).setCellValue(meetingHistory.getGroups());
            courseRow.createCell(11)
                    .setCellValue(meetingHistory.getDescription());

        }

        OutputStream outStream = null;

        try {
            outStream = response.getOutputStream();
            workbook.write(outStream);
            outStream.flush();
        } finally {
            outStream.close();
        }
    }

}