package com.example.mareu_oc_projet4.event;

import com.example.mareu_oc_projet4.model.Meeting;

public class DeleteMeetingEvent {
    /**
     * meeting a supprimer
     */
    public Meeting meeting;

    /**
     *
     * @param meeting
     */
    public DeleteMeetingEvent(Meeting meeting){
        this.meeting = meeting;
    }
}
