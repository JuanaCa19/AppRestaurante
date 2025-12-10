package com.mycompany.apprestaurante.Modelo.entities;

public class Waiter {
    private int id;
    private String name;
    private String phone;

    public Waiter() {
    }

    public Waiter(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
