package com.example.projet4_mareumaster.services;

import com.example.projet4_mareumaster.model.Reunion;

import java.util.List;

public interface ReunionApiService {
    List<Reunion> getReunions();

    void deleteReunion(Reunion reunion);

    void setReunions(Reunion reunion);
}
