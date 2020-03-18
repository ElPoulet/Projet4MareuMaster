package com.example.projet4_mareumaster.services;

import android.graphics.Color;

import com.example.projet4_mareumaster.model.Salle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummySalleGenerator {

    public static List<Salle> DUMMY_SALLE_GENERATOR = Arrays.asList(
            new Salle(1,"Salle 1", Color.argb(255,38,196,236)),
            new Salle(2,"Salle 2", Color.argb(255,98,19,205)),
            new Salle(3,"Salle 3", Color.argb(255,52,219,19)),
            new Salle(4,"Salle 4", Color.argb(255,255,135,0)),
            new Salle(5,"Salle 5", Color.argb(255,255,216,0)),
            new Salle(6,"Salle 6", Color.argb(255,228,0,255)),
            new Salle(7,"Salle 7", Color.argb(255,0,43,255)),
            new Salle(8,"Salle 8", Color.argb(255,255,0,120)),
            new Salle(9,"Salle 9", Color.argb(255,255,0,0)),
            new Salle(10,"Salle 10", Color.argb(255,255,0,97))
    );

    static ArrayList<Salle> generateSalle() {
        return new ArrayList<>(DUMMY_SALLE_GENERATOR);
    }



}

