package com.mycompany.apprestaurante.Modelo.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Order {
    private int id;
    private LocalDate dateOrder;
    private LocalTime timeOrder;
    private int idWaiter;
    private int idTable;
    private String orderDescription;
    private double total;
    private boolean state;

    public Order() {
    }

    public Order(LocalDate dateOrder, LocalTime timeOrder, int idWaiter, int idTable, String orderDescription, double total) {
        this.dateOrder = dateOrder;
        this.timeOrder = timeOrder;
        this.idWaiter = idWaiter;
        this.idTable = idTable;
        this.orderDescription = orderDescription;
        this.total = total;
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

    public LocalDate getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDate dateOrder) {
        this.dateOrder = dateOrder;
    }

    public LocalTime getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(LocalTime timeOrder) {
        this.timeOrder = timeOrder;
    }

    public int getIdWaiter() {
        return idWaiter;
    }

    public void setIdWaiter(int idWaiter) {
        this.idWaiter = idWaiter;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
