package com.example.mareu_oc_projet4.services;

import com.example.mareu_oc_projet4.model.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeeting();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetings() {return meetings;}

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);}

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMeetings(Meeting meeting){
        meetings.add(meeting);
    }



}
