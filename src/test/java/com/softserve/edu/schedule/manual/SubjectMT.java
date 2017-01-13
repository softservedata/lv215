package com.softserve.edu.schedule.manual;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.Subject_;
import com.softserve.edu.schedule.service.SubjectService;

public class SubjectMT {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:/WEB-INF/applicationContext.xml");
        ctx.refresh();
        SubjectService subjectService = ctx.getBean("subjectService",
                SubjectService.class);

        List<Subject> subjects = subjectService.getAll();
        for (Subject sub : subjects) {
            System.out.println(sub);
        }
        System.out.println("Sort by asc");
        for (Subject subject : subjectService.sort("id", Order.ASC)) {
            System.out.println(subject);
        }
        System.out.println("Sort by desc");
        for (Subject subject : subjectService.sort("id", Order.DESC)) {
            System.out.println(subject);
        }
        System.out.println("Search");
        for (Subject subject : subjectService
                .search(Subject_.description.getName(), "mon")) {
            System.out.println(subject);
        }
        System.out.println("Search Tutors");
        for (Subject subject : subjectService.searchTutors("iv")) {
            System.out.println(subject);
        }
    }
}
