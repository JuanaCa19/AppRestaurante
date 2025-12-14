/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.apprestaurante.Vista.viewAdmin;

import com.mycompany.apprestaurante.Controlador.orderController.OrderController;
import com.mycompany.apprestaurante.Controlador.tableController.TableController;
import com.mycompany.apprestaurante.Controlador.waiterController.WaiterController;
import com.mycompany.apprestaurante.Modelo.dao.DishDAO;
import com.mycompany.apprestaurante.Modelo.dao.OrderDAO;
import com.mycompany.apprestaurante.Modelo.dao.TableDAO;
import com.mycompany.apprestaurante.Modelo.dao.WaiterDAO;
import com.mycompany.apprestaurante.Modelo.entities.Dish;
import com.mycompany.apprestaurante.Modelo.entities.Order;
import com.mycompany.apprestaurante.Modelo.entities.Table;
import com.mycompany.apprestaurante.Modelo.entities.Waiter;
import com.mycompany.apprestaurante.Modelo.interfaces.IDishDAO;
import com.mycompany.apprestaurante.Modelo.interfaces.IOrderDAO;
import com.mycompany.apprestaurante.Modelo.interfaces.ITableDAO;
import com.mycompany.apprestaurante.Modelo.interfaces.IWaiterDAO;
import com.mycompany.apprestaurante.Vista.Login.login;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author carlo
 */
public class viewMainAdmin extends javax.swing.JFrame {

    /**
     * Creates new form viewMainAdmin
     */
    /**
     * labelCurrentOccupation= porcentaje de ocupacion listTables= tabla de
     * mesas tableShowWaiter= tabla meseros
     *
     */
    
    private TableController tablecontroller = new TableController(); 
    private OrderController ordercontroller = new OrderController(); 
    private WaiterController waitercontroller = new WaiterController(); 
    
    public viewMainAdmin() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);

        ImageIcon icono1 = new ImageIcon(getClass().getResource("/picture/Exit1.png"));
        Image imagen1 = icono1.getImage().getScaledInstance(lExit1.getWidth(), lExit1.getHeight(), Image.SCALE_SMOOTH);
        lExit1.setIcon(new ImageIcon(imagen1));
        loadStatistics(); 
    }
    
    public void loadStatistics(){
        
        double occupation = tablecontroller.getCurrentOccupation(); 
        CurrentOccupation.setText(String.format("%.0f%%", occupation));
        
        int totalOrder = ordercontroller.totalOrder(); 
        activeOrders.setText(String.valueOf(totalOrder));
        
        double totalSale = ordercontroller.obtenerVentasDelDia(); 
        salesDay.setText(String.valueOf(totalSale));
        
        int totalWaiter = waitercontroller.getActiveWaiters(); 
        activeWaiter.setText(String.valueOf(totalWaiter));
        
        int totalTableAvalaible = tablecontroller.getTableAvalaible(); 
        activeTable.setText(String.valueOf(totalTableAvalaible));
    }


    public void applyTableStyle(JTable tabla) {

        JTableHeader header = tabla.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.decode("#008D9B"));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));

        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                Color par = Color.WHITE;
                Color impar = Color.decode("#CCCCCC");

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? par : impar);
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(new Color(0, 120, 215));
                    c.setForeground(Color.WHITE);
                }

                return c;
            }
        });
    }

    public void viewTable() {
        ITableDAO tableDAO = new TableDAO();
        List<Table> lista = tableDAO.listTable();
        String[] columnas = {"ID", "Capacidad", "Estado"};
        DefaultTableModel tabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Table table : lista) {
            String state = "Inactivo"; 
            if(table.isState()){
                state = "Activo"; 
            }
            Object[] filas = {
                table.getId(),
                table.getCapacity(),
                state
            };
            tabla.addRow(filas);
        }
        listTables.setModel(tabla);
        applyTableStyle(listTables);
        listTables.getTableHeader().setResizingAllowed(false);
        listTables.getTableHeader().setReorderingAllowed(false);
    }

    public void viewWaiter() {
        IWaiterDAO dao = new WaiterDAO();
        List<Waiter> lista = dao.listWaiter();
        String[] columnas = {"ID", "Nombre", "Telefono"};
        DefaultTableModel tabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Waiter waiter : lista) {
            Object[] filas = {
                waiter.getId(),
                waiter.getName(),
                waiter.getPhone()
            };
            tabla.addRow(filas);
        }
        tableShowWaiter.setModel(tabla);
        applyTableStyle(tableShowWaiter);
        tableShowWaiter.getTableHeader().setResizingAllowed(false);
        tableShowWaiter.getTableHeader().setReorderingAllowed(false);
    }
    
    
    public void viewDish() {
        IDishDAO dao = new DishDAO();
        List<Dish> lista = dao.listDish();
        String[] columnas = {"ID", "Nombre", "Precio"};
        DefaultTableModel tabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Dish dish : lista) {
            Object[] filas = {
                dish.getId(),
                dish.getNombre(),
                dish.getPrice()
            };
            tabla.addRow(filas);
        }
        tableDish.setModel(tabla);
        applyTableStyle(tableDish);
        tableDish.getTableHeader().setResizingAllowed(false);
        tableDish.getTableHeader().setReorderingAllowed(false);
    }
    
    public void viewOrder(){
        IOrderDAO dao = new OrderDAO();
        List<Order> lista = dao.listOrder();
        String[] columnas = {"ID", "Dia del pedido", "Mesa", "ID mesero","Total"};
        DefaultTableModel tabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Order order : lista) {
            Object[] filas = {
                order.getId(),
                order.getDateOrder(),
                order.getIdTable(),
                order.getIdWaiter(),
                order.getTotal()
            };
            tabla.addRow(filas);
        }
        tableOrder.setModel(tabla);
        applyTableStyle(tableOrder);
        tableOrder.getTableHeader().setResizingAllowed(false);
        tableOrder.getTableHeader().setReorderingAllowed(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewMain = new javax.swing.JPanel();
        mainStatistics = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        currentOccupation = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        CurrentOccupation = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        activeOrders = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        salesDay = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        activeWaiter = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        activeTable = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tableManagement = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        buttonAddTable = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listTables = new javax.swing.JTable();
        waiterManagement = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        buttonAddWaiter = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableShowWaiter = new javax.swing.JTable();
        dishManagement = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        buttonAddDish = new javax.swing.JButton();
        searchDish = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDish = new javax.swing.JTable();
        orderManagement = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableOrder = new javax.swing.JTable();
        viewTop = new javax.swing.JPanel();
        lExit1 = new javax.swing.JLabel();
        viewMenu = new javax.swing.JPanel();
        buttonMain = new javax.swing.JButton();
        buttonTable = new javax.swing.JButton();
        buttonWaiter = new javax.swing.JButton();
        buttonDish = new javax.swing.JButton();
        buttonOrder = new javax.swing.JButton();
        buttonExti = new javax.swing.JButton();
        lPrinci = new javax.swing.JLabel();
        lPrinci1 = new javax.swing.JLabel();
        lPrinci2 = new javax.swing.JLabel();
        lPrinci3 = new javax.swing.JLabel();
        lPrinci4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewMain.setBackground(new java.awt.Color(255, 255, 255));
        viewMain.setLayout(new java.awt.CardLayout());

        mainStatistics.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Principal del Administrador");

        currentOccupation.setBackground(new java.awt.Color(255, 255, 255));
        currentOccupation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        currentOccupation.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Ocupacion actual");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel3.setText("Mesas ocupadas/Total mesas");

        javax.swing.GroupLayout currentOccupationLayout = new javax.swing.GroupLayout(currentOccupation);
        currentOccupation.setLayout(currentOccupationLayout);
        currentOccupationLayout.setHorizontalGroup(
            currentOccupationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentOccupationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, currentOccupationLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, currentOccupationLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CurrentOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        currentOccupationLayout.setVerticalGroup(
            currentOccupationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentOccupationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(CurrentOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Pedidos activos");

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel9.setText("<html><div style='text-align:center;'>Pedidos en preparación <br>o en servicio</div></html>");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(activeOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(12, 12, 12)
                .addComponent(activeOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Ventas del día");

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel11.setText("Total ventas hasta ahora");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(salesDay, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salesDay, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Meseros activos");

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel13.setText("Personal activo actualmente");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(activeWaiter, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(activeWaiter, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Mesas disponibles");

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 10)); // NOI18N
        jLabel15.setText("Mesas libres");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel14)))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(activeTable, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(activeTable, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainStatisticsLayout = new javax.swing.GroupLayout(mainStatistics);
        mainStatistics.setLayout(mainStatisticsLayout);
        mainStatisticsLayout.setHorizontalGroup(
            mainStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainStatisticsLayout.createSequentialGroup()
                .addGroup(mainStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainStatisticsLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(mainStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainStatisticsLayout.createSequentialGroup()
                                .addComponent(currentOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(mainStatisticsLayout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        mainStatisticsLayout.setVerticalGroup(
            mainStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainStatisticsLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(mainStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        viewMain.add(mainStatistics, "mainStatistics");

        tableManagement.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Gestión de Mesas");

        buttonAddTable.setBackground(new java.awt.Color(0, 141, 155));
        buttonAddTable.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        buttonAddTable.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddTable.setText("+ Agregar Mesa");
        buttonAddTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonAddTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonAddTableMouseClicked(evt);
            }
        });

        listTables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(listTables);

        javax.swing.GroupLayout tableManagementLayout = new javax.swing.GroupLayout(tableManagement);
        tableManagement.setLayout(tableManagementLayout);
        tableManagementLayout.setHorizontalGroup(
            tableManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableManagementLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(tableManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tableManagementLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(50, Short.MAX_VALUE))
                    .addGroup(tableManagementLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonAddTable)
                        .addGap(52, 52, 52))))
        );
        tableManagementLayout.setVerticalGroup(
            tableManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableManagementLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(tableManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(buttonAddTable))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        viewMain.add(tableManagement, "tableManagement");

        waiterManagement.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Gestión de Meseros");

        buttonAddWaiter.setBackground(new java.awt.Color(0, 141, 155));
        buttonAddWaiter.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        buttonAddWaiter.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddWaiter.setText("+ Agregar mesero");
        buttonAddWaiter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonAddWaiter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddWaiterActionPerformed(evt);
            }
        });

        tableShowWaiter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableShowWaiter);

        javax.swing.GroupLayout waiterManagementLayout = new javax.swing.GroupLayout(waiterManagement);
        waiterManagement.setLayout(waiterManagementLayout);
        waiterManagementLayout.setHorizontalGroup(
            waiterManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waiterManagementLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAddWaiter)
                .addGap(53, 53, 53))
            .addGroup(waiterManagementLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        waiterManagementLayout.setVerticalGroup(
            waiterManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waiterManagementLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(waiterManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(buttonAddWaiter))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        viewMain.add(waiterManagement, "waiterManagement");

        dishManagement.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Gestión de Platos");

        buttonAddDish.setBackground(new java.awt.Color(0, 141, 155));
        buttonAddDish.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        buttonAddDish.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddDish.setText("+ Agregar Plato");
        buttonAddDish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddDishActionPerformed(evt);
            }
        });

        searchDish.setText("Buscar Plato");
        searchDish.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchDishFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchDishFocusLost(evt);
            }
        });

        tableDish.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tableDish);

        javax.swing.GroupLayout dishManagementLayout = new javax.swing.GroupLayout(dishManagement);
        dishManagement.setLayout(dishManagementLayout);
        dishManagementLayout.setHorizontalGroup(
            dishManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dishManagementLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(dishManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dishManagementLayout.createSequentialGroup()
                        .addComponent(searchDish, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dishManagementLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonAddDish)
                        .addGap(61, 61, 61))))
            .addGroup(dishManagementLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 144, Short.MAX_VALUE))
        );
        dishManagementLayout.setVerticalGroup(
            dishManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dishManagementLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(dishManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(buttonAddDish))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchDish, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        viewMain.add(dishManagement, "dishManagement");

        orderManagement.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Gestión de Pedidos");

        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tableOrder);

        javax.swing.GroupLayout orderManagementLayout = new javax.swing.GroupLayout(orderManagement);
        orderManagement.setLayout(orderManagementLayout);
        orderManagementLayout.setHorizontalGroup(
            orderManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderManagementLayout.createSequentialGroup()
                .addGroup(orderManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderManagementLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(orderManagementLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        orderManagementLayout.setVerticalGroup(
            orderManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderManagementLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel7)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        viewMain.add(orderManagement, "orderManagement");

        getContentPane().add(viewMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 690, 440));

        viewTop.setBackground(new java.awt.Color(0, 141, 155));

        lExit1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lExit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lExit1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout viewTopLayout = new javax.swing.GroupLayout(viewTop);
        viewTop.setLayout(viewTopLayout);
        viewTopLayout.setHorizontalGroup(
            viewTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewTopLayout.createSequentialGroup()
                .addContainerGap(752, Short.MAX_VALUE)
                .addComponent(lExit1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        viewTopLayout.setVerticalGroup(
            viewTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewTopLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lExit1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        getContentPane().add(viewTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 80));

        viewMenu.setBackground(new java.awt.Color(204, 204, 204));
        viewMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonMain.setBackground(new java.awt.Color(204, 204, 204));
        buttonMain.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        buttonMain.setForeground(new java.awt.Color(0, 0, 0));
        buttonMain.setText("Principal");
        buttonMain.setBorder(null);
        buttonMain.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonMain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMainMouseClicked(evt);
            }
        });
        buttonMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMainActionPerformed(evt);
            }
        });
        viewMenu.add(buttonMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 49, 76, 28));

        buttonTable.setBackground(new java.awt.Color(204, 204, 204));
        buttonTable.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        buttonTable.setForeground(new java.awt.Color(0, 0, 0));
        buttonTable.setText("Mesas");
        buttonTable.setBorder(null);
        buttonTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonTable.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonTableMouseClicked(evt);
            }
        });
        viewMenu.add(buttonTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 83, 76, 28));

        buttonWaiter.setBackground(new java.awt.Color(204, 204, 204));
        buttonWaiter.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        buttonWaiter.setForeground(new java.awt.Color(0, 0, 0));
        buttonWaiter.setText("Meseros");
        buttonWaiter.setBorder(null);
        buttonWaiter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonWaiter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonWaiter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonWaiterMouseClicked(evt);
            }
        });
        buttonWaiter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonWaiterActionPerformed(evt);
            }
        });
        viewMenu.add(buttonWaiter, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 117, 76, 28));

        buttonDish.setBackground(new java.awt.Color(204, 204, 204));
        buttonDish.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        buttonDish.setForeground(new java.awt.Color(0, 0, 0));
        buttonDish.setText("Platos");
        buttonDish.setBorder(null);
        buttonDish.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonDish.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonDish.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDishMouseClicked(evt);
            }
        });
        viewMenu.add(buttonDish, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 151, 76, 28));

        buttonOrder.setBackground(new java.awt.Color(204, 204, 204));
        buttonOrder.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        buttonOrder.setForeground(new java.awt.Color(0, 0, 0));
        buttonOrder.setText("Pedidos");
        buttonOrder.setBorder(null);
        buttonOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonOrder.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonOrderMouseClicked(evt);
            }
        });
        viewMenu.add(buttonOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 185, 76, 28));

        buttonExti.setBackground(new java.awt.Color(204, 204, 204));
        buttonExti.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        buttonExti.setForeground(new java.awt.Color(0, 0, 0));
        buttonExti.setText("Cerrar sesión");
        buttonExti.setBorder(null);
        buttonExti.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonExti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExtiActionPerformed(evt);
            }
        });
        viewMenu.add(buttonExti, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 110, -1));

        lPrinci.setBackground(new java.awt.Color(204, 204, 204));
        lPrinci.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lPrinci.setOpaque(true);
        viewMenu.add(lPrinci, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 49, 34, 28));

        lPrinci1.setBackground(new java.awt.Color(204, 204, 204));
        lPrinci1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lPrinci1.setOpaque(true);
        viewMenu.add(lPrinci1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 83, 34, 28));

        lPrinci2.setBackground(new java.awt.Color(204, 204, 204));
        lPrinci2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lPrinci2.setOpaque(true);
        viewMenu.add(lPrinci2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 117, 34, 28));

        lPrinci3.setBackground(new java.awt.Color(204, 204, 204));
        lPrinci3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lPrinci3.setOpaque(true);
        viewMenu.add(lPrinci3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 151, 34, 28));

        lPrinci4.setBackground(new java.awt.Color(204, 204, 204));
        lPrinci4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lPrinci4.setOpaque(true);
        viewMenu.add(lPrinci4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 185, 34, 28));

        getContentPane().add(viewMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 110, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMainMouseClicked
        CardLayout cl = (CardLayout) viewMain.getLayout();
        cl.show(viewMain, "mainStatistics");
    }//GEN-LAST:event_buttonMainMouseClicked

    private void buttonTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonTableMouseClicked
        CardLayout cl = (CardLayout) viewMain.getLayout();
        cl.show(viewMain, "tableManagement");
        viewTable();
    }//GEN-LAST:event_buttonTableMouseClicked

    private void buttonWaiterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonWaiterMouseClicked
        CardLayout cl = (CardLayout) viewMain.getLayout();
        cl.show(viewMain, "waiterManagement");
         viewWaiter();
    }//GEN-LAST:event_buttonWaiterMouseClicked

    private void buttonDishMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDishMouseClicked
        CardLayout cl = (CardLayout) viewMain.getLayout();
        cl.show(viewMain, "dishManagement");
        viewDish(); 
    }//GEN-LAST:event_buttonDishMouseClicked

    private void buttonOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonOrderMouseClicked
        CardLayout cl = (CardLayout) viewMain.getLayout();
        cl.show(viewMain, "orderManagement");
        viewOrder(); 
    }//GEN-LAST:event_buttonOrderMouseClicked

    private void lExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lExit1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_lExit1MouseClicked

    private void buttonWaiterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonWaiterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonWaiterActionPerformed

    private void buttonAddTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAddTableMouseClicked
        tableForm table = new tableForm(this);
        table.setVisible(true);
    }//GEN-LAST:event_buttonAddTableMouseClicked

    private void buttonExtiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExtiActionPerformed
        login login = new login();
        this.setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_buttonExtiActionPerformed

    private void buttonAddWaiterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddWaiterActionPerformed
        waiterForm waiter = new waiterForm(this);
        waiter.setVisible(true);
    }//GEN-LAST:event_buttonAddWaiterActionPerformed

    private void searchDishFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchDishFocusGained
        if (searchDish.getText().equals("Buscar Plato")) {
            searchDish.setText("");
        }
    }//GEN-LAST:event_searchDishFocusGained

    private void searchDishFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchDishFocusLost
        if (searchDish.getText().isEmpty()) {
            searchDish.setText("Buscar Plato");
        }
    }//GEN-LAST:event_searchDishFocusLost

    private void buttonAddDishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddDishActionPerformed
        dishForm dish = new dishForm(this);
        dish.setVisible(true);
    }//GEN-LAST:event_buttonAddDishActionPerformed

    private void buttonMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonMainActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(viewMainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(viewMainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(viewMainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(viewMainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new viewMainAdmin().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CurrentOccupation;
    private javax.swing.JLabel activeOrders;
    private javax.swing.JLabel activeTable;
    private javax.swing.JLabel activeWaiter;
    private javax.swing.JButton buttonAddDish;
    private javax.swing.JButton buttonAddTable;
    private javax.swing.JButton buttonAddWaiter;
    private javax.swing.JButton buttonDish;
    private javax.swing.JButton buttonExti;
    private javax.swing.JButton buttonMain;
    private javax.swing.JButton buttonOrder;
    private javax.swing.JButton buttonTable;
    private javax.swing.JButton buttonWaiter;
    private javax.swing.JPanel currentOccupation;
    private javax.swing.JPanel dishManagement;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lExit1;
    private javax.swing.JLabel lPrinci;
    private javax.swing.JLabel lPrinci1;
    private javax.swing.JLabel lPrinci2;
    private javax.swing.JLabel lPrinci3;
    private javax.swing.JLabel lPrinci4;
    private javax.swing.JTable listTables;
    private javax.swing.JPanel mainStatistics;
    private javax.swing.JPanel orderManagement;
    private javax.swing.JLabel salesDay;
    private javax.swing.JTextField searchDish;
    private javax.swing.JTable tableDish;
    private javax.swing.JPanel tableManagement;
    private javax.swing.JTable tableOrder;
    private javax.swing.JTable tableShowWaiter;
    private javax.swing.JPanel viewMain;
    private javax.swing.JPanel viewMenu;
    private javax.swing.JPanel viewTop;
    private javax.swing.JPanel waiterManagement;
    // End of variables declaration//GEN-END:variables
}
