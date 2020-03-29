package com.example.mareu_oc_projet4.model;

public class Meeting {

    /** Sujet d'une réunion */
    private String topic;

    /** Date de la réunion */
    private String date;

    /** Heure de la réunion */
    private String hourMeeting;

    /** Minutes de la réunion */
    private String minMeeting;

    /** Nom de la room de réunion */
    private Room room;

    /** Participants de la réunion */
    private String participants;

    /**
     * Constructor
     * @param topic
     * @param date
     * @param hourMeeting
     * @param minMeeting
     * @param room
     * @param participants
     */

    public Meeting(String topic, String date, String hourMeeting, String minMeeting, Room room, String participants) {
        this.topic = topic;
        this.date = date;
        this.hourMeeting = hourMeeting;
        this.minMeeting = minMeeting;
        this.room = room;
        this.participants = participants;
    }


    public String getTopic() { return topic; }

    public void setSujet(String sujet) {
        this.topic = sujet;
    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHourMeeting() {return hourMeeting;}

    public void setHourMeeting(String hourMeeting) {this.hourMeeting = hourMeeting;}

    public String getMinMeeting() {return minMeeting;}

    public void setMinMeeting(String minMeeting) {this.minMeeting = minMeeting;}

    public Room getRoom() { return room; }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getParticipants() { return participants; }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getNameRoom() { return room.getNameRoom(); }

    public int getIdRoom() { return room.getIdRoom(); }

    public int getColor() { return room.getColor(); }
}
