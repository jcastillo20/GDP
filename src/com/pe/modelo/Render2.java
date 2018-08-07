/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.modelo;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author JUAN
 */
public class Render2 extends DefaultTableCellRenderer{
    
       @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        Color c = new Color(254, 115,28);
        Color c1 =new Color(215, 219, 219);
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if(value instanceof JButton){
            JButton btn = (JButton)value;
            if(isSelected){
                btn.setForeground(table.getSelectionForeground());
                btn.setBackground(table.getSelectionBackground());
            }else{
                btn.setForeground(table.getForeground());
                btn.setBackground(UIManager.getColor("Button.background"));
            }
            
            return btn;
        }
           
        return super.getTableCellRendererComponent(table, value, isSelected, 
                hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

}
