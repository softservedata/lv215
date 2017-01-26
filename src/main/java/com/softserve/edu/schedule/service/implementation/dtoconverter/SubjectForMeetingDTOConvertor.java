package com.softserve.edu.schedule.service.implementation.dtoconverter;


import org.springframework.stereotype.Service;
import com.softserve.edu.schedule.dto.SubjectForMeetingDTO;
import com.softserve.edu.schedule.entity.Subject;

@Service
public class SubjectForMeetingDTOConvertor {



    public SubjectForMeetingDTO getDTO(final Subject subject) {
        if (subject != null) {
            SubjectForMeetingDTO subjectForMeetingDTO = new SubjectForMeetingDTO();
            if (subject.getId() != null) {
                subjectForMeetingDTO.setId(subject.getId());
            }
            if (subject.getName() != null) {
                subjectForMeetingDTO.setName(subject.getName());
                return subjectForMeetingDTO;
            }
        }
        return null;
    }
}
