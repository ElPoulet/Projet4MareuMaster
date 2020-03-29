package com.example.mareu_oc_projet4.model;

public class Room {

    /** Numéro Room */
    private Integer idRoom;

    /** Nom de la Room */
    private String nameRoom;

    /** Disponibilité */

    private int color;

    /**
     * Constructor
     * @param idRoom
     * @param nameRoom;
     * @param color
     */

    public Room(Integer idRoom, String nameRoom, int color) {
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.color = color;
    }

    public Integer getIdRoom() { return idRoom; }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() { return nameRoom; }

    public void setNameRoom(String nameRoom) { this.nameRoom = nameRoom; }

    public int getColor() { return color; }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString(){
        return nameRoom;
    }
}
