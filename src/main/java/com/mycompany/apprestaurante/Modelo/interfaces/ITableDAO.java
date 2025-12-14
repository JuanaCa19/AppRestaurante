package com.mycompany.apprestaurante.Modelo.interfaces;

import com.mycompany.apprestaurante.Modelo.entities.Dish;
import com.mycompany.apprestaurante.Modelo.entities.Table;

import java.util.List;

public interface ITableDAO {

    public boolean saveTable(Table table);

    public List<Table> listTable();
    
    public void modifyTable(int idTable);

}
