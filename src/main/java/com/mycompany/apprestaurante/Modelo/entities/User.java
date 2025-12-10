package com.mycompany.apprestaurante.Modelo.entities;

public class User {
    private int id;
    private String name;
    private String nameUser;
    private String password;

    public User() {
    }

    public User(String name, String nameUser, String password) {
        this.name = name;
        this.nameUser = nameUser;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
