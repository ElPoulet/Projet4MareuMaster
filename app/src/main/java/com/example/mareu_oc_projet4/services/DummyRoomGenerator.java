package com.example.mareu_oc_projet4.services;

import android.graphics.Color;

import com.example.mareu_oc_projet4.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyRoomGenerator {

    public static List<Room> dummyRoomGenerator = Arrays.asList(
            new Room(1,"Salle 1", Color.argb(255,38,196,236)),
            new Room(2,"Salle 2", Color.argb(255,98,19,205)),
            new Room(3,"Salle 3", Color.argb(255,52,219,19)),
            new Room(4,"Salle 4", Color.argb(255,255,135,0)),
            new Room(5,"Salle 5", Color.argb(255,255,216,0)),
            new Room(6,"Salle 6", Color.argb(255,228,0,255)),
            new Room(7,"Salle 7", Color.argb(255,0,43,255)),
            new Room(8,"Salle 8", Color.argb(255,255,0,120)),
            new Room(9,"Salle 9", Color.argb(255,255,0,0)),
            new Room(10,"Salle 10", Color.argb(255,255,0,97))
    );

    static ArrayList<Room> generateRoom() {
        return new ArrayList<>(dummyRoomGenerator);
    }



}

