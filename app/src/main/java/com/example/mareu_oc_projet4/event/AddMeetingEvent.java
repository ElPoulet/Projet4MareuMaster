package com.example.mareu_oc_projet4.event;

import com.example.mareu_oc_projet4.model.Meeting;

public class AddMeetingEvent {

    /**
     * meeting a supprimer
     */
    public Meeting meeting;

    /**
     *
     * @param meeting
     */
    public AddMeetingEvent(Meeting meeting){
        this.meeting = meeting;
    }
}