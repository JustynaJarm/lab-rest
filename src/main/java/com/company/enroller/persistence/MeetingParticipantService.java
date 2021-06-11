package com.company.enroller.persistence;



import com.company.enroller.model.Meeting;
import com.company.enroller.model.MeetingParticipant;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("meetingParticipantService")
public class MeetingParticipantService {

    DatabaseConnector connector;
    Session session;

    public MeetingParticipantService() {
        session = DatabaseConnector.getInstance().getSession();
    }

    public void add(MeetingParticipant meetingParticipant){
        Transaction transaction = this.session.beginTransaction();
        session.save(meetingParticipant);
        transaction.commit();
    }

    public Collection<MeetingParticipant> getAll() {
        return session.createCriteria(MeetingParticipant.class).list();
    }

    public MeetingParticipant findById(long id){
        MeetingParticipant meetingService = (MeetingParticipant) session.get(MeetingParticipant.class, id);
        return meetingService;
    }

    public Collection<MeetingParticipant> findByMeetingId(int meetingId) {
        String hql = "FROM MeetingParticipant S WHERE S.meetingId='" + meetingId + "'";
        Query query = session.createQuery(hql);
        return query.list();
    }
}
