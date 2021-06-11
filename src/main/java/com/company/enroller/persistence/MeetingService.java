package com.company.enroller.persistence;

import java.util.Collection;

import com.company.enroller.model.Participant;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Meeting;

@Component("meetingService")
public class MeetingService {

	DatabaseConnector connector;
	Session session;

	public MeetingService() {
		session = DatabaseConnector.getInstance().getSession();
	}

	public Collection<Meeting> getAll() {
		return session.createCriteria(Meeting.class).list();
	}

	public Meeting findById(long id){
		Meeting meeting = (Meeting) session.get(Meeting.class, id);
		return meeting;
	}


	public void add(Meeting meeting){
		Transaction transaction = this.session.beginTransaction();
		session.save(meeting);
		transaction.commit();
	}

	public Meeting findByTitle(String title) {
		Meeting meeting = (Meeting) session.get(Meeting.class, title);
		return meeting;
	}
}
