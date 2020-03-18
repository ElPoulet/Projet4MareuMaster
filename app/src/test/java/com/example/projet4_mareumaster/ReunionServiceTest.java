package com.example.projet4_mareumaster;

import android.graphics.Color;

import com.example.projet4_mareumaster.DI.DI;
import com.example.projet4_mareumaster.model.Reunion;
import com.example.projet4_mareumaster.model.Salle;
import com.example.projet4_mareumaster.services.ReunionApiService;
import com.example.projet4_mareumaster.vues.ReunionAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ReunionServiceTest {

    public List<Reunion> mReunionList;
    private ReunionAdapter mAdapter;
    private ReunionApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceReunionApiService();
        mReunionList = service.getReunions();
        mAdapter = new ReunionAdapter(mReunionList);
    }

    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunionToDelete = service.getReunions().get(0);
        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunions().contains(reunionToDelete));
    }

    @Test
    public void addReunionWithSuccess() {
        Reunion reunion = new Reunion("Sujet Test","15 Janvier","15","35",new Salle(4,"Salle 4", Color.argb(255,98,19,205)),"machin");
        service.setReunions(reunion);
        reunion = service.getReunions().get(2);
        assertTrue(reunion.getSujet() == "Sujet Test");
    }

    @Test
    public void salleFilterWithSucces() {
        String test = "Salle 4";
        mAdapter.salleFilter(test);
        Reunion reunion = service.getReunions().get(0);
        assertTrue(reunion.getNomSalle() == test);
    }

    @Test
    public void dateFilterWithSucces() {
        String test = "18 10 2020";
        mAdapter.dateFilter(test);
        Reunion reunion = service.getReunions().get(0);
        assertTrue(reunion.getDate() == test);


    }
}
