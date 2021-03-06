/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.extras;

import com.pe.controlador.Detalle_PedidoDAO;
import com.pe.util.conexion;
import com.pe.vista.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author JuanCR
 */
public class Mensajes {
    public static String plomo="#818181";
    public static String negro="#000000";
    public static String idUsuario;
    
    public static void msjMuestra(String contenido){
        JOptionPane.showMessageDialog(null, contenido);
    }
    
     public static void msjError(String contenido){
        JOptionPane.showMessageDialog(null, contenido,"Error ..!!",JOptionPane.ERROR_MESSAGE);
    }

    public static void msjconfirmacion(){
        FrmPrincipal principal=null;
        String title="Cerrar Sesión";
        String message="¿ Desea Cerrar Sesión ?";
        int rpta=JOptionPane.showConfirmDialog(null, message,title,JOptionPane.YES_NO_OPTION);
        if(rpta==JOptionPane.YES_OPTION){
            msjMuestra("Hasta Pronto");
            conexion.CerrarBD(conexion.Conexion());
            System.exit(0);
        }else{
            msjMuestra("Nos mantenemos aqui");
        }
    }
    
    public static void GuardarIDUsuario(String ID){
        idUsuario=ID;
    }
    
     public static String ObtenerIDUsuario(){
        return idUsuario;
    }
    public static void msjconfirmacionActualizarPedido(String fecha,String idDetallePedido,String idPedido){
        Detalle_PedidoDAO dao=new Detalle_PedidoDAO();
        String title="Actualizar el envio";
        String message="¿ Desea actualizar el pedido de la siguiente fecha - "+fecha+" ?";
        int rpta=JOptionPane.showConfirmDialog(null, message,title,JOptionPane.YES_NO_OPTION);
        if(rpta==JOptionPane.YES_OPTION){
            //msjMuestra(fecha +"-"+idDetallePedido+"-"+idPedido);
            dao.actualizarPedido(idDetallePedido, idPedido);
        }else{
            msjMuestra("Accion cancelada");
        }
    }
     public static void msjconfirmacionEliminarPedido(String fecha,String idDetallePedido,String idPedido,String idPaquete){
         
         Detalle_PedidoDAO dao=new Detalle_PedidoDAO();
        String title="Eliminar Reporte";
        String message="¿ Desea Eliminar el pedido de la siguiente fecha - "+fecha+" ?";
        int rpta=JOptionPane.showConfirmDialog(null, message,title,JOptionPane.YES_NO_OPTION);
        if(rpta==JOptionPane.YES_OPTION){
            //msjMuestra(fecha +"-"+idDetallePedido+"-"+idPedido);
            dao.eliminarPedido(idDetallePedido, idPedido, idPaquete);
        }else{
            msjMuestra("Accion cancelada");
        }
    }
     public static void msjVerDetalle_Paquete(){
        FrmPrincipal principal=null;
        String title="Detalle Paquete";
        String message="¿ Desea ver el Detalle de Paquete ?";
        int rpta=JOptionPane.showConfirmDialog(null, message,title,JOptionPane.YES_NO_OPTION);
        if(rpta==JOptionPane.YES_OPTION){
            msjMuestra("Cargando");
            
        }else{
            System.out.println("Dijo no ver Detalle_paquete");
        }
    }
    public static void Hint(JTextField txt,String mensaje,int tamaño){
        if(tamaño==0){
            txt.setText(mensaje);
            txt.setForeground(java.awt.Color.decode(plomo));       
        }
    }
    public static void sinHit(JTextField txt){
        
            txt.setText("");
            txt.setForeground(java.awt.Color.decode(negro));
                  
    }
      public static void centerComponent(Component c) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = c.getSize();

        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }

        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        c.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
    }
}
