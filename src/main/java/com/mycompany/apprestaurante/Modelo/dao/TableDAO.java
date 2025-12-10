package com.mycompany.apprestaurante.Modelo.dao;

import com.mycompany.apprestaurante.Modelo.entities.Table;
import com.mycompany.apprestaurante.Modelo.interfaces.ITableDAO;

import java.util.List;

public class TableDAO implements ITableDAO {
    @Override
    public void saveTable(Table table) {

    }

    @Override
    public List<Table> listTable() {
        return List.of();
    }
}
