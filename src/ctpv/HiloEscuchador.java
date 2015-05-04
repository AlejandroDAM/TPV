package ctpv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import comunicacion.InformacionTPV;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HiloEscuchador extends Thread {
    private static final int NUM_CLIENTES = 6;
    private int contador;
    private int contadorVentana;
    private static final int PUERTO = 2000;
    private CTPV ventanaServidor;

    public HiloEscuchador(CTPV ventanaServidor) {
        super();
        this.ventanaServidor = ventanaServidor;
    }

    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            while (true) {
                Socket conexionCliente = servidor.accept();
                contador = ventanaServidor.getContadorTPV();
                ObjectInputStream entrada = new ObjectInputStream(conexionCliente.getInputStream());
                //while (true) {
                    if (conexionCliente != null) {
                        InformacionTPV datosTPV = (InformacionTPV) entrada.readObject();
                        if (datosTPV.getEstado()==1 && contador < NUM_CLIENTES) {
                            ventanaServidor.añadirVentana(datosTPV.getId());
                            System.out.println("Ventana añadida");
                        } else if (datosTPV.getEstado()==0){
                            ventanaServidor.removerVentana(datosTPV.getId());
                            System.out.println("Ventana cerrada");   
                        } else if (datosTPV.getEstado()==2){
                            VentanaInterna ventanaInterna = ventanaServidor.getVentanaInterna(datosTPV.getId());
                            JTable tabla = datosTPV.getTabla();
                            JScrollPane jScrollPane1 = ventanaInterna.getjScrollPane1();
                            jScrollPane1.setViewportView(tabla);
                            JLabel jLabelPrecio = ventanaInterna.getjLabelPrecio();
                            jLabelPrecio.setText(datosTPV.getBig()+" €");
                        }else{
                            System.out.println("Maximas conexiones establecidas");
                        }
                    //}
                    }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
