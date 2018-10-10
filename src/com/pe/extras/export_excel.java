package com.pe.extras;

import com.lowagie.text.pdf.hyphenation.TernaryTree;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import jxl.*;

public class export_excel {

    private File archi;
    private List<JTable> tabla;
    private List<JTable> tabla1;
    private List<JTable> tabla2;
    private List<String> nom_hoja;
    private WritableCellFormat fomato_fila;
    private WritableCellFormat fomato_columna;
    private WritableSheet sheet;

    public export_excel(List<JTable> tab, List<JTable> tab1, List<JTable> tab2, File ar) throws Exception {
        this.archi = ar;
        this.tabla = tab;
        this.tabla1 = tab1;
        this.tabla2 = tab2;
        if (tab.size() < 0 && tab1.size() < 0 && tab2.size() < 0) {
            throw new Exception("ERROR");
        }
    }

    public export_excel(List<JTable> tab, File ar) throws Exception {
        this.archi = ar;
        this.tabla = tab;
        if (tab.size() < 0) {
            throw new Exception("ERROR");
        }
    }

    export_excel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean export() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(archi));
            WritableWorkbook w = Workbook.createWorkbook(out);

            sheet = w.createSheet("Detalle Pedido", 0);
            sheet.getSettings().setZoomFactor(80);
            PrimeraHoja();
            sheet = w.createSheet("Detalle por Mes", 1);
            sheet.getSettings().setZoomFactor(80);
            SegundaHoja();
            sheet = w.createSheet("Detalle por Usuario", 2);
            sheet.getSettings().setZoomFactor(80);
            TerceraHoja();

            for (int index = 0; index < tabla.size(); index++) {
                JTable table = tabla.get(index);

                WritableSheet s = w.getSheet(0);

                for (int i = 0; i < table.getColumnCount(); i++) {
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object objeto = table.getValueAt(j, i);

                        createColumna(s, table.getColumnName(i), i);//crea la columna
                        createFilas(s, i, j + 1, String.valueOf(objeto));//crea las filas

                    }
                }
            }
            for (int index = 0; index < tabla1.size(); index++) {
                JTable table1 = tabla1.get(index);

                WritableSheet s1 = w.getSheet(1);

                for (int i = 0; i < table1.getColumnCount(); i++) {
                    for (int j = 0; j < table1.getRowCount(); j++) {
                        Object objeto = table1.getValueAt(j, i);

                        createColumna(s1, table1.getColumnName(i), i);//crea la columna
                        createFilas(s1, i, j + 1, String.valueOf(objeto));//crea las filas

                    }
                }
            }
            for (int index = 0; index < tabla2.size(); index++) {
                JTable table = tabla2.get(index);

                WritableSheet s2 = w.getSheet(2);

                for (int i = 0; i < table.getColumnCount(); i++) {
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object objeto = table.getValueAt(j, i);

                        createColumna(s2, table.getColumnName(i), i);//crea la columna
                        createFilas(s2, i, j + 1, String.valueOf(objeto));//crea las filas

                    }
                }
            }
            w.write();
            w.close();

            out.close();
            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (WriteException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean export_ReportePedido(String detalle) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(archi));
            WritableWorkbook w = Workbook.createWorkbook(out);

            sheet = w.createSheet(detalle, 0);
            sheet.getSettings().setZoomFactor(70);
            Hoja_ReportePedidos();

            for (int index = 0; index < tabla.size(); index++) {
                JTable table = tabla.get(index);

                WritableSheet s = w.getSheet(0);

                for (int i = 0; i < table.getColumnCount(); i++) {
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object objeto = table.getValueAt(j, i);

                        createColumna(s, table.getColumnName(i), i);//crea la columna
                        createFilas(s, i, j + 1, String.valueOf(objeto));//crea las filas

                    }
                }
            }
            w.write();
            w.close();

            out.close();
            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (WriteException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean exportBusquedaDNI() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(archi));
            WritableWorkbook w = Workbook.createWorkbook(out);

            sheet = w.createSheet("Busqueda por DNI", 0);
            sheet.getSettings().setZoomFactor(80);
            PrimeraHoja();

            for (int index = 0; index < tabla.size(); index++) {
                JTable table = tabla.get(index);

                WritableSheet s = w.getSheet(0);

                for (int i = 0; i < table.getColumnCount(); i++) {
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object objeto = table.getValueAt(j, i);

                        createColumna(s, table.getColumnName(i), i);//crea la columna
                        createFilas(s, i, j + 1, String.valueOf(objeto));//crea las filas

                    }
                }

            }
            w.write();
            w.close();

            out.close();
            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (WriteException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void createColumna(WritableSheet sheet, String columna, int number_columna) throws WriteException {
        //creamos el tipo de letra
        WritableFont times10pt = new WritableFont(WritableFont.TAHOMA, 14);
        // definimos el formato d ela celda
        WritableCellFormat times = new WritableCellFormat(times10pt);
        // Permite si se ajusta autom�ticamente a las c�lulas
        times.setWrap(true);

        // crea una negrita con subrayado
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD, false);
        fomato_columna = new WritableCellFormat(times10ptBoldUnderline);
        fomato_columna.setAlignment(jxl.format.Alignment.CENTRE);
        fomato_columna.setBackground(Colour.YELLOW2);
        fomato_columna.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

        // Permite que se ajusta autom�ticamente a las c�lulas
        //fomato_columna.setWrap(true);
        CellView cevell = new CellView();
        cevell.setSize(920);
        cevell.setDimension(90);
        cevell.setFormat(times);

        //cevell.setFormat(fomato_columna);
        //cevell.setAutosize(true);
        // escribimos las columnas
        addColumna(sheet, number_columna, 0, columna, fomato_columna);//numero de columna , 0 es la fila
    }

    /**
     * *************************************
     */
    private void createFilas(WritableSheet sheet, int number_columna, int filas, String name_filas) throws WriteException {
        //creamos el tipo de letra
        WritableFont times10pt = new WritableFont(WritableFont.ARIAL, 11);
        times10pt.setColour(Colour.GOLD);
        // definimos el formato d ela celda
        WritableCellFormat times = new WritableCellFormat(times10pt);
        times.setBorder(Border.TOP, BorderLineStyle.MEDIUM, Colour.GOLD);
        // Permite si se ajusta autom�ticamente a las c�lulas
        times.setWrap(true);
        // crea una negrita con subrayado
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
        fomato_fila = new WritableCellFormat(times10ptBoldUnderline);
        fomato_fila.setAlignment(jxl.format.Alignment.LEFT);
        fomato_fila.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
        // Permite que se ajusta autom�ticamente a las c�lulas
        //fomato_fila.setWrap(true);
        CellView cevell = new CellView();
        cevell.setDimension(70);
        cevell.setFormat(times);
        //cevell.setFormat(fomato_fila);

        //cevell.setAutosize(true);
        // escribimos las columnas
        addFilas(sheet, number_columna, filas, name_filas, fomato_fila);
    }

    /**
     * ********************************
     */
    private void addColumna(WritableSheet sheet, int column, int row, String s, WritableCellFormat format) throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, format);
        sheet.addCell(label);
    }

    private void addFilas(WritableSheet sheet, int column, int row, String s, WritableCellFormat format) throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, format);
        sheet.addCell(label);
    }

    private void Hoja_ReportePedidos() {
        sheet.setColumnView(0, 42);
        sheet.setColumnView(1, 24);
        sheet.setColumnView(2, 28);
        sheet.setColumnView(3, 59);
        sheet.setColumnView(4, 47);
        sheet.setColumnView(5, 30);
        sheet.setColumnView(6, 40);

    }

    private void PrimeraHoja() {
        sheet.setColumnView(0, 15);
        sheet.setColumnView(1, 59);
        sheet.setColumnView(2, 17);
        sheet.setColumnView(3, 17);
        sheet.setColumnView(4, 15);
        sheet.setColumnView(5, 26);
        sheet.setColumnView(6, 25);
        sheet.setColumnView(7, 46);
        sheet.setColumnView(8, 12);
        sheet.setColumnView(9, 30);
        sheet.setColumnView(10, 24);
        sheet.setColumnView(11, 20);

    }

    private void SegundaHoja() {
        sheet.setColumnView(0, 17);
        sheet.setColumnView(1, 17);
        sheet.setColumnView(2, 22);
        sheet.setColumnView(3, 21);
        sheet.setColumnView(4, 9);
        sheet.setColumnView(5, 15);
        sheet.setColumnView(6, 15);

    }

    private void TerceraHoja() {
        sheet.setColumnView(0, 59);
        sheet.setColumnView(1, 22);
        sheet.setColumnView(2, 14);
        sheet.setColumnView(3, 14);
    }

    private void HojaDNI() {
        sheet.setColumnView(0, 80);
        sheet.setColumnView(1, 450);
        sheet.setColumnView(2, 80);
    }

}
