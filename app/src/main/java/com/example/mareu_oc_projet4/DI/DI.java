package com.example.mareu_oc_projet4.DI;

import com.example.mareu_oc_projet4.services.DummyMeetingApiService;
import com.example.mareu_oc_projet4.services.DummyRoomApiService;
import com.example.mareu_oc_projet4.services.MeetingApiService;
import com.example.mareu_oc_projet4.services.RoomApiService;

public class DI {

    private static MeetingApiService serviceReunion = new DummyMeetingApiService();
    private static RoomApiService serviceSalle = new DummyRoomApiService();

    /**
     * Get an instance on @{@link MeetingApiService}
     * @return
     */
    public static MeetingApiService getReunionApiService() {
        return serviceReunion;
    }

    /**
     * Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingApiService getNewInstanceReunionApiService() {
        return new DummyMeetingApiService();
    }

    public static RoomApiService getSalleApiService() { return serviceSalle; }

    public static RoomApiService getNewInstanceSalleApiService() {
        return new DummyRoomApiService();
    }


}
