package com.example.projet4_mareumaster.services;

import com.example.projet4_mareumaster.model.Salle;

import java.util.List;

public class DummySalleApiService implements SalleApiService{

    private List<Salle> salles = DummySalleGenerator.generateSalle();

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<Salle> getSalles() {return salles;}

}