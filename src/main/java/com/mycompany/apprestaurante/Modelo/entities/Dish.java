package com.mycompany.apprestaurante.Modelo.entities;

public class Dish {
    private int id;
    private String nombre;
    private double price;

    public Dish() {
    }

    public Dish(int id, String nombre, double price) {
        this.id = id;
        this.nombre = nombre;
        this.price = price;
    }

    public Dish(double price, String nombre) {
        this.price = price;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
