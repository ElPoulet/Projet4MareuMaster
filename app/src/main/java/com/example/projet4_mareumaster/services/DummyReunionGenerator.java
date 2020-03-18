package com.example.projet4_mareumaster.services;

import android.graphics.Color;

import com.example.projet4_mareumaster.model.Reunion;
import com.example.projet4_mareumaster.model.Salle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_GENERATOR = Arrays.asList(
            new Reunion("Réunion A","12 01 2019","12","05", new Salle(1,"Salle 1", Color.argb(255,38,196,236)),"machin@gmail.com, directeur@hotmail.fr"),
            new Reunion("Réunion B","18 10 2020","15","35",new Salle(4,"Salle 4", Color.argb(255,98,19,205)),"direction@outlook.com")

    );

    static List<Reunion> generateReunion() {
        return new ArrayList<>(DUMMY_GENERATOR);
    }
}