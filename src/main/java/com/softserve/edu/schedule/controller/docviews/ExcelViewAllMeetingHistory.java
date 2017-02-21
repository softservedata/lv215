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

import com.softserve.edu.schedule.dto.MeetingHistoryDTO;
import com.softserve.edu.schedule.service.MeetingHistoryService;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * 
 * @author www.codejava.net
 *
 */
public class ExcelViewAllMeetingHistory extends AbstractXlsView {

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

        List<MeetingHistoryDTO> listMH = meetingHistoryService.getAll();
        
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
        for (MeetingHistoryDTO meetingHistoryDTO : listMH) {
            Row courseRow = sheet.createRow(rowCount++);
            courseRow.createCell(0).setCellValue(meetingHistoryDTO.getId());
            courseRow.createCell(1).setCellValue(Long.parseLong(meetingHistoryDTO.getIdMeeting()));
            courseRow.createCell(2).setCellValue(meetingHistoryDTO.getSubject());
            courseRow.createCell(3).setCellValue(meetingHistoryDTO.getOwner());
            courseRow.createCell(4).setCellValue(meetingHistoryDTO.getLocation());
            courseRow.createCell(5).setCellValue(meetingHistoryDTO.getAddress());
            courseRow.createCell(6).setCellValue(meetingHistoryDTO.getRoom());
            courseRow.createCell(7)
                    .setCellValue(meetingHistoryDTO.getDate().toString());
            courseRow.createCell(8)
                    .setCellValue(meetingHistoryDTO.getStartTime().toString());
            courseRow.createCell(9)
                    .setCellValue(meetingHistoryDTO.getEndTime().toString());
            courseRow.createCell(10).setCellValue(meetingHistoryDTO.getGroups());
            courseRow.createCell(11)
                    .setCellValue(meetingHistoryDTO.getDescription());

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