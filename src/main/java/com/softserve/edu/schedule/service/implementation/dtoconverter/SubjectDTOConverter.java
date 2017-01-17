package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.entity.Subject;

@Service("roomDTOConverter")
public class SubjectDTOConverter {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserDTOConverter userDTOConverter;

    public Subject getEntity(SubjectDTO subjectDTO) {
        if (subjectDTO != null) {
            Subject subject = new Subject();
            if (subjectDTO.getId() != null) {
                subject.setId(subjectDTO.getId());
            }
            if (subjectDTO.getName() != null) {
                subject.setName(subjectDTO.getName());
            }
            if (subjectDTO.getDescription() != null) {
                subject.setDescription(subjectDTO.getDescription());
            }
            subjectDTO.getUsers().forEach(
                    u -> subject.getUsers().add(userDAO.getById(u.getId())));
            return subject;
        }
        return null;
    }

    public SubjectDTO getDTO(Subject subject) {
        if (subject != null) {
            SubjectDTO subjectDTO = new SubjectDTO();
            if (subject.getId() != null) {
                subjectDTO.setId(subject.getId());
            }
            if (subject.getName() != null) {
                subjectDTO.setName(subject.getName());
            }
            if (subject.getDescription() != null) {
                subjectDTO.setDescription(subject.getDescription());
            }
            //TODO
          /*  subject.getUsers().forEach(u->subjectDTO.getUsers().add();*/
        }
        return null;
    }

}
