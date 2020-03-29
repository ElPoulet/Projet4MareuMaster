package com.example.mareu_oc_projet4.services;

import com.example.mareu_oc_projet4.model.Meeting;

import java.util.List;

public interface MeetingApiService {
    List<Meeting> getMeetings();

    void deleteMeeting(Meeting meeting);

    void setMeetings(Meeting meeting);
}
