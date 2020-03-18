package com.example.projet4_mareumaster.model;

public class Salle {

    /** Numéro Salle */
    private Integer idSalle;

    /** Nom de la Salle */
    private String nameSalle;

    /** Disponibilité */

    private int color;

    /**
     * Constructor
     * @param idSalle
     * @param nameSalle;
     * @param color
     */

    public Salle(Integer idSalle, String nameSalle, int color) {
        this.idSalle = idSalle;
        this.nameSalle = nameSalle;
        this.color = color;
    }

    public Integer getIdSalle() { return idSalle; }

    public void setIdSalle(Integer idSalle) {
        this.idSalle = idSalle;
    }

    public String getNameSalle() { return nameSalle; }

    public void setNameSalle(String nameSalle) { this.nameSalle = nameSalle; }

    public int getColor() { return color; }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString(){
        return nameSalle;
    }
}
