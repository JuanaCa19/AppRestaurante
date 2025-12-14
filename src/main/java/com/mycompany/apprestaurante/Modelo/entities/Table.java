package com.mycompany.apprestaurante.Modelo.entities;

public class Table {
    private int id;
    private int capacity;
    private String availade; 
    public Table() {
    }

    public Table(int capacity, String availade) {
        this.capacity = capacity;
        this.availade = availade;
    }

    public String getAvailade() {
        return availade;
    }

    public void setAvailade(String availade) {
        this.availade = availade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
