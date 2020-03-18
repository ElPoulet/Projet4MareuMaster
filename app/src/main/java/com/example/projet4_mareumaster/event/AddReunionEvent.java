package com.example.projet4_mareumaster.event;

import com.example.projet4_mareumaster.model.Reunion;

public class AddReunionEvent {

    /**
     * reunion à supprimer
     */
    public Reunion reunion;

    /**
     * @param reunion
     */
    public AddReunionEvent(Reunion reunion) { this.reunion = reunion; }

}
