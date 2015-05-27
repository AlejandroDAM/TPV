/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import ctpv.CTPV;
import ctpv.VentanaInterna;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import tpv.ProductoPedido;

/**
 *
 * @author Alejandro
 */
public class HiloComunicador extends Thread {

    private Socket accept;
    private CTPV ventanaServidor;
    private ObjectInputStream entrada;

    public HiloComunicador(Socket accept, CTPV ventanaServidor) {
        this.accept = accept;
        this.ventanaServidor = ventanaServidor;
    }

    @Override
    public void run() {
                try {
                    entrada = new ObjectInputStream(accept.getInputStream());
                    while(true){
                
                        InformacionTPV datosTPV = null;
                        datosTPV = (InformacionTPV) entrada.readObject();

                        VentanaInterna ventanaInterna = ventanaServidor.getVentanaInterna(datosTPV.getId());
                        if (datosTPV.getEstado() == 1) {
                            ventanaServidor.añadirVentana(datosTPV.getId());
                            System.out.println("Ventana añadida");
                        } else if (datosTPV.getEstado() == 0) {
                            ventanaServidor.removerVentana(datosTPV.getId());
                            System.out.println("Ventana cerrada");
                        } else if (datosTPV.getEstado() == 2) {
                            HashMap<String, ProductoPedido> listaPedidos = datosTPV.getListaPedidos();
                            DefaultTableModel modeloTabla = new DefaultTableModel();
                            //Añadimos las columnas a la tabla
                            modeloTabla.addColumn("Productos");
                            modeloTabla.addColumn("Cantidad");
                            modeloTabla.addColumn("Sub-total");
                            for (String string : listaPedidos.keySet()) {
                                modeloTabla.addRow(listaPedidos.get(string).getProducto());
                            }
                            JTable tabla = ventanaInterna.getjTable1();
                            tabla.setModel(modeloTabla);
                            JScrollPane jScrollPane1 = ventanaInterna.getjScrollPane1();
                            jScrollPane1.setViewportView(tabla);
                            JLabel jLabelPrecio = ventanaInterna.getjLabelPrecio();
                            jLabelPrecio.setText(datosTPV.getBig() + " €");
                        } else {
                            System.out.println("Maximas conexiones establecidas");
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(HiloComunicador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(HiloComunicador.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

}
