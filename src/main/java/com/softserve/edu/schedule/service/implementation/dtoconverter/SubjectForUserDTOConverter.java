package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;
import com.softserve.edu.schedule.dto.SubjectForUserDTO;
import com.softserve.edu.schedule.entity.Subject;


/**
 * An class to provide SubjectForUserDTO from Subject entity.
 *
 * @version 1.0 20 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Service
public class SubjectForUserDTOConverter {

    /**
     * Save new SubjectForUserDTO object from Subject entity.
     *
     * @param subject
     *            a subject from database.
     */
    public SubjectForUserDTO getDTO(Subject subject) {
        if (subject != null) {
            SubjectForUserDTO subjectFUDTO = new SubjectForUserDTO();
            if (subject.getId() != null) {
                subjectFUDTO.setId(subject.getId());
            }
            if (subject.getName() != null) {
                subjectFUDTO.setName(subject.getName());
            }
            return subjectFUDTO;
        }
        return null;
    }
}
