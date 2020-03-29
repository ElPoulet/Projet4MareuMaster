package com.example.mareu_oc_projet4.services;

import com.example.mareu_oc_projet4.model.Room;

import java.util.List;

public class DummyRoomApiService implements RoomApiService {

    private List<Room> rooms = DummyRoomGenerator.generateRoom();

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<Room> getRooms() {return rooms;}

}