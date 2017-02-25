/*
 * Implementation of the MeetingHistoryDAO interface. 
 */
package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.MeetingHistoryDAO;
import com.softserve.edu.schedule.entity.MeetingHistory;
import com.softserve.edu.schedule.entity.MeetingHistory_;

/**
 * This class is implementation of MeetingHistoryDAO interface.
 *
 * @version 1.0 22.02.2017
 * @author Bohdan Melnyk
 */
@Repository
public class MeetingHistoryDAOImpl extends CrudDAOImpl<MeetingHistory>
        implements MeetingHistoryDAO {

    /**
     * Constructor for this class.
     */
    public MeetingHistoryDAOImpl() {
        super(MeetingHistory.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.dao.MeetingHistoryDAO#
     * getMeetingHistoryByIdMeeting(java.lang.String)
     */
    public List<MeetingHistory> getMeetingHistoryByIdMeeting(
            final String idMeeting) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<MeetingHistory> cq = builder
                .createQuery(MeetingHistory.class);
        Root<MeetingHistory> root = cq.from(MeetingHistory.class);
        cq.where(builder.like(root.get(MeetingHistory_.idMeeting.getName()),
                idMeeting));
        return getEm().createQuery(cq).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingHistoryDAO#getAllMeetingHistory()
     */
    public List<MeetingHistory> getAllMeetingHistory() {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<MeetingHistory> cq = builder
                .createQuery(MeetingHistory.class);
        return getEm().createQuery(cq).getResultList();
    }
}
