package com.mycompany.lab2;

import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.List;

public class Lab2 extends javax.swing.JFrame {
    
    private List<RecIntegral> records = new ArrayList<>();

    public Lab2() {
        initComponents();
        setupTable();
    }
    
    private void setupTable() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column < 3;
            }
        };
        
        model.addColumn("Нижний предел");  
        model.addColumn("Верхний предел");   
        model.addColumn("шаг");             
        model.addColumn("Результат");       
        
        jTable1.setModel(model);
        
        // Обновление
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    
                    if (row >= 0 && column >= 0 && column < 3 && row < records.size()) {
                        try {
                            double a = Double.parseDouble(model.getValueAt(row, 0).toString());
                            double b = Double.parseDouble(model.getValueAt(row, 1).toString());
                            double n = Double.parseDouble(model.getValueAt(row, 2).toString());
                            
                            RecIntegral rec = records.get(row);
                            rec.setLowerBound(a);
                            rec.setUpperBound(b);
                            rec.setStep(n);
                            rec.setResult(0);
                            
                            model.setValueAt("", row, 3);
                        } catch (NumberFormatException ex) {
                        }
                    }
                }
            }
        });
        
        updateCollectionSize();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton(); 
        jButton2 = new javax.swing.JButton(); 
        jButton3 = new javax.swing.JButton(); 
        jButton4 = new javax.swing.JButton(); 
        jButton5 = new javax.swing.JButton(); 
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();  
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 204, 255), null));

        jButton1.setText("добавить");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("очистить строку");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("подтвердить");
        jButton3.addActionListener(this::jButton3ActionPerformed);
        
        jButton4.setText("очистить таблицу");
        jButton4.addActionListener(this::jButton4ActionPerformed);
        
        jButton5.setText("заполнить из коллекции");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jLabel1.setText("Нижний предел");  
        jLabel2.setText("Шаг");
        jLabel3.setText("Верхний предел");
        
        jLabel4.setText("Размер коллекции: 0");
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Нижний предел", "Верхний предел", "шаг", "Результат"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("1/x");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel2)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    
    private void updateCollectionSize() {
        jLabel4.setText("Размер коллекции: " + records.size());
    }
    
    // добавить
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            double a = Double.parseDouble(jTextField2.getText());
            double b = Double.parseDouble(jTextField3.getText());
            double n = Double.parseDouble(jTextField4.getText());
            
            RecIntegral record = new RecIntegral(a, b, n, 0);
            records.add(record);
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{a, b, n, ""});
            
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            
            updateCollectionSize();
            
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Введите корректные числа!");
        }
    }                                        
    
    // очистить строку
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            if (selectedRow < records.size()) {
                records.remove(selectedRow);
            }
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.removeRow(selectedRow);
            
            updateCollectionSize();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Выберите строку для удаления!");
        }
    }                                        
    
    // подтвердить
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int selectedRow = jTable1.getSelectedRow();
        
        if (selectedRow >= 0) {
            try {
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                
                double a = Double.parseDouble(model.getValueAt(selectedRow, 0).toString());
                double b = Double.parseDouble(model.getValueAt(selectedRow, 1).toString()); 
                double n = Double.parseDouble(model.getValueAt(selectedRow, 2).toString());       
                
                double result = Calculator.integral(a, b, n);
                
                if (selectedRow < records.size()) {
                    RecIntegral rec = records.get(selectedRow);
                    rec.setLowerBound(a);
                    rec.setUpperBound(b);
                    rec.setStep(n);
                    rec.setResult(result);
                }
                
                model.setValueAt(String.format("%.6f", result), selectedRow, 3);
                
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ошибка в данных строки!");
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Выберите строку для вычисления!");
        }
    }
    
    // очистить таблицу ( конкретно тут только вид удаляем )
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    }
    
    // заполнить из колекции
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        model.setRowCount(0);
        
        // Заполняем данными из коллекции
        for (RecIntegral rec : records) {
            String result = rec.getResult() == 0 ? "" : String.format("%.6f", rec.getResult());
            model.addRow(new Object[]{
                rec.getLowerBound(),
                rec.getUpperBound(),
                rec.getStep(),
                result
            });
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Lab2().setVisible(true));
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
}