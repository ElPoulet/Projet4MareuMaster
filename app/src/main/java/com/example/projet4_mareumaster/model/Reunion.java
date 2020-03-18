package com.example.projet4_mareumaster.model;

public class Reunion {

    /** Sujet d'une réunion */
    private String sujet;

    /** Date de la réunion */
    private String date;

    /** Heure de la réunion */
    private String heureReunion;

    /** Minutes de la réunion */
    private String minReunion;

    /** Nom de la salle de réunion */
    private Salle salle;

    /** Participants de la réunion */
    private String participants;

    /**
     * Constructor
     * @param sujet
     * @param date
     * @param heureReunion
     * @param minReunion
     * @param salle
     * @param participants
     */

    public Reunion(String sujet,String date,String heureReunion, String minReunion, Salle salle,String participants) {
        this.sujet = sujet;
        this.date = date;
        this.heureReunion = heureReunion;
        this.minReunion = minReunion;
        this.salle = salle;
        this.participants = participants;
    }


    public String getSujet() { return sujet; }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureReunion() {return heureReunion;}

    public void setHeureReunion(String heureReunion) {this.heureReunion = heureReunion;}

    public String getMinReunion() {return minReunion;}

    public void setMinReunion(String minReunion) {this.minReunion = minReunion;}

    public Salle getSalle() { return salle; }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public String getParticipants() { return participants; }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getNomSalle() { return salle.getNameSalle(); }

    public int getNumSalle() { return salle.getIdSalle(); }

    public int getColor() { return salle.getColor(); }
}