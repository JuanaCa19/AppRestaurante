package com.mycompany.apprestaurante.Modelo.entities;

public class Table {
    private int id;
    private int capacity;

    public Table() {
    }

    public Table(int capacity) {
        this.capacity = capacity;
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
