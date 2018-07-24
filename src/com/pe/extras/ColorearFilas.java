/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.extras;

import java.awt.Color;
import java.awt.Component;
import java.awt.Transparency;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author JUAN
 */
public class ColorearFilas extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col
    ) {
        Color c = new Color(254, 115,28);
        Color c1 =new Color(215, 219, 219);
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);

        if (table.getValueAt(row, 6).toString().equals("Aprobado")) {

            if (table.getValueAt(row, 4).toString().equals("50") || table.getValueAt(row, 4).toString().equals("49") || table.getValueAt(row, 4).toString().equals("48") || table.getValueAt(row, 4).toString().equals("47") || table.getValueAt(row, 4).toString().equals("46") || table.getValueAt(row, 4).toString().equals("45") || table.getValueAt(row, 4).toString().equals("44")
                    || table.getValueAt(row, 4).toString().equals("43") || table.getValueAt(row, 4).toString().equals("42") || table.getValueAt(row, 4).toString().equals("41") || table.getValueAt(row, 4).toString().equals("40") || table.getValueAt(row, 4).toString().equals("39") || table.getValueAt(row, 4).toString().equals("38") || table.getValueAt(row, 4).toString().equals("37")
                    || table.getValueAt(row, 4).toString().equals("36") || table.getValueAt(row, 4).toString().equals("35") || table.getValueAt(row, 4).toString().equals("34") || table.getValueAt(row, 4).toString().equals("33") || table.getValueAt(row, 4).toString().equals("32") || table.getValueAt(row, 4).toString().equals("31") || table.getValueAt(row, 4).toString().equals("30")
                    || table.getValueAt(row, 4).toString().equals("29") || table.getValueAt(row, 4).toString().equals("28") || table.getValueAt(row, 4).toString().equals("27") || table.getValueAt(row, 4).toString().equals("26") || table.getValueAt(row, 4).toString().equals("25") || table.getValueAt(row, 4).toString().equals("24") || table.getValueAt(row, 4).toString().equals("23") || table.getValueAt(row, 4).toString().equals("22")
                    || table.getValueAt(row, 4).toString().equals("21") || table.getValueAt(row, 4).toString().equals("20") || table.getValueAt(row, 4).toString().equals("19") || table.getValueAt(row, 4).toString().equals("18") || table.getValueAt(row, 4).toString().equals("17") || table.getValueAt(row, 4).toString().equals("16") || table.getValueAt(row, 4).toString().equals("15")
                    || table.getValueAt(row, 4).toString().equals("14") || table.getValueAt(row, 4).toString().equals("13") || table.getValueAt(row, 4).toString().equals("12") || table.getValueAt(row, 4).toString().equals("11") || table.getValueAt(row, 4).toString().equals("10") || table.getValueAt(row, 4).toString().equals("9")
                    || table.getValueAt(row, 4).toString().equals("8") || table.getValueAt(row, 4).toString().equals("7") || table.getValueAt(row, 4).toString().equals("6") || table.getValueAt(row, 4).toString().equals("5") || table.getValueAt(row, 4).toString().equals("4") || table.getValueAt(row, 4).toString().equals("3") || table.getValueAt(row, 4).toString().equals("2") || table.getValueAt(row, 4).toString().equals("1")) {
                setBackground(c);                         

            } else {
                setBackground(c1);
                
            }

        } else {
            setBackground(c1);
        }

        return this;

    }

}
