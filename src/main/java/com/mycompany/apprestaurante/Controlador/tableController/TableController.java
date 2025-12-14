/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprestaurante.Controlador.tableController;

import com.mycompany.apprestaurante.Modelo.dao.TableDAO;
import com.mycompany.apprestaurante.Modelo.entities.Table;
import com.mycompany.apprestaurante.Modelo.interfaces.ITableDAO;
import java.util.List;

/**
 *
 * @author carlo
 */
public class TableController {
    private ITableDAO tableDAO = new TableDAO(); 
    public boolean insertTable(int capacity, boolean availade){
        Table table = new Table(capacity,availade); 
        return tableDAO.saveTable(table); 
    }
    
    public List<Table> listTable(){
        return tableDAO.listTable(); 
    }
    
    public void modifyTable(int idTable){
        tableDAO.modifyTable(idTable);
    }
    
    public double getCurrentOccupation(){
        return tableDAO.getCurrentOccupation(); 
    }
}
