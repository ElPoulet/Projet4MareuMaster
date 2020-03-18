package com.example.projet4_mareumaster.services;

import com.example.projet4_mareumaster.model.Reunion;

import java.util.List;

public class DummyReunionApiService implements ReunionApiService {

    private List<Reunion> reunions = DummyReunionGenerator.generateReunion();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Reunion> getReunions() {return reunions;}

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteReunion(Reunion reunion) {reunions.remove(reunion);}

    /**
     * {@inheritDoc}
     */
    @Override
    public void setReunions(Reunion reunion){
        reunions.add(reunion);
    }



}
