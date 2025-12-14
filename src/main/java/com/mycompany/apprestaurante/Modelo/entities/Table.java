package com.mycompany.apprestaurante.Modelo.entities;

public class Table {
    private int id;
    private int capacity;
    private boolean state;
    public Table() {
    }

    public Table( int capacity, boolean availade) {

        this.capacity = capacity;
        this.state = availade;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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
