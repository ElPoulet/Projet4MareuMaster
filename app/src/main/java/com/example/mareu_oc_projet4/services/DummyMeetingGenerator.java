package com.example.mareu_oc_projet4.services;

import android.graphics.Color;

import com.example.mareu_oc_projet4.model.Meeting;
import com.example.mareu_oc_projet4.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_GENERATOR = Arrays.asList(
            new Meeting("Réunion A","12 01 2019","12","05", new Room(1,"Salle 1", Color.argb(255,38,196,236)),"machin@gmail.com, directeur@hotmail.fr"),
            new Meeting("Réunion B","18 10 2020","15","35",new Room(4,"Salle 4", Color.argb(255,98,19,205)),"direction@outlook.com")

    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_GENERATOR);
    }
}