package com.example.mareu_oc_projet4;

import android.graphics.Color;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.mareu_oc_projet4.DI.DI;
import com.example.mareu_oc_projet4.model.Meeting;
import com.example.mareu_oc_projet4.model.Room;
import com.example.mareu_oc_projet4.services.MeetingApiService;
import com.example.mareu_oc_projet4.view.MeetingAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class MeetingServiceTest {

    public List<Meeting> mMeetingList;
    private MeetingAdapter mAdapter;
    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceReunionApiService();
        mMeetingList = service.getMeetings();
        mAdapter = new MeetingAdapter(mMeetingList);
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void addMeetingWithSuccess() {
        Meeting meeting = new Meeting("Sujet Test","15 Janvier","15","35",new Room(4,"Salle 4", Color.argb(255,98,19,205)),"machin");
        service.setMeetings(meeting);
        meeting = service.getMeetings().get(2);
        assertTrue(meeting.getTopic() == "Sujet Test");
    }

    @Test
    public void RoomFilterWithSucces() {
        Meeting meeting = new Meeting("Sujet Test","15 Janvier","15","35",new Room(8,"Salle 8", Color.argb(255,98,19,205)),"machin");
        service.setMeetings(meeting);
        String test = "Salle 8";
        mAdapter.roomFilter(test);
        Meeting meetingReturn = service.getMeetings().get(0);
        assertTrue(meeting.getNameRoom() == test);
    }

    @Test
    public void dateFilterWithSucces() {
        Meeting meeting = new Meeting("Sujet Test","16 10 2020","15","35",new Room(4,"Salle 4", Color.argb(255,98,19,205)),"machin");
        service.setMeetings(meeting);
        String test = "16 10 2020";
        mAdapter.dateFilter(test);
        Meeting meetingReturn = service.getMeetings().get(0);
        assertTrue(meeting.getDate() == test);


    }
}
