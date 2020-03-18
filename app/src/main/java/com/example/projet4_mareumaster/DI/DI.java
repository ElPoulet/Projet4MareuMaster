package com.example.projet4_mareumaster.DI;

import com.example.projet4_mareumaster.services.DummyReunionApiService;
import com.example.projet4_mareumaster.services.DummySalleApiService;
import com.example.projet4_mareumaster.services.ReunionApiService;
import com.example.projet4_mareumaster.services.SalleApiService;

public class DI {

    private static ReunionApiService serviceReunion = new DummyReunionApiService();
    private static SalleApiService serviceSalle = new DummySalleApiService();

    /**
     * Get an instance on @{@link ReunionApiService}
     * @return
     */
    public static ReunionApiService getReunionApiService() { return serviceReunion; }

    /**
     * Get always a new instance on @{@link ReunionApiService}. Useful for tests, so we ensure the context is clean.
     */
    public static ReunionApiService getNewInstanceReunionApiService() {
        return new DummyReunionApiService();
    }

    public static SalleApiService getSalleApiService() { return serviceSalle; }

    public static SalleApiService getNewInstanceSalleApiService() {
        return new DummySalleApiService();
    }
}
