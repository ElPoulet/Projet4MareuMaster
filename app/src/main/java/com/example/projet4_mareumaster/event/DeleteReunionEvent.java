package com.example.projet4_mareumaster.event;

import com.example.projet4_mareumaster.model.Reunion;

public class DeleteReunionEvent {
    /**
     * reunion à supprimer
     */
    public Reunion reunion;

    /**
     * @param reunion
     */
    public DeleteReunionEvent(Reunion reunion) { this.reunion = reunion; }
}
