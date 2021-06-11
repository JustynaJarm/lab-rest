package com.company.enroller.model;

import javax.persistence.*;

@Entity
@Table(name = "meeting_participant")
public class MeetingParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "meeting_id")
    private long meetingId;

    @Column (name = "participant_login")
    private String participantLogin;

    public MeetingParticipant() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(long meetingId) {
        this.meetingId = meetingId;
    }

    public String getParticipantLogin() {
        return participantLogin;
    }

    public void setParticipantLogin(String participantLogin) {
        this.participantLogin = participantLogin;
    }
}
