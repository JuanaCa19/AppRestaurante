package com.mycompany.apprestaurante.Modelo.entities;

public class OrderDish {
    private int id;
    private int idDish;
    private int idOrder;

    public OrderDish() {
    }

    public OrderDish(int idOrder, int idDish) {
        this.idOrder = idOrder;
        this.idDish = idDish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }
}
