package ctpv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import comunicacion.InformacionTPV;
import javax.swing.JFrame;

public class HiloEscuchador extends Thread {
    private static final int NUM_CLIENTES = 6;
    private int contador;
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
                contador = ventanaServidor.getContador();
                if (contador < NUM_CLIENTES){
                    Socket conexionCliente = servidor.accept();
                    if (conexionCliente != null) {
                        ObjectInputStream entrada = new ObjectInputStream(conexionCliente.getInputStream());
                        InformacionTPV datosTPV = (InformacionTPV) entrada.readObject();
                        if (datosTPV.isEstado() && contador <= NUM_CLIENTES) {
                            ventanaServidor.añadirVentana(datosTPV.getId());
                        } else if (!datosTPV.isEstado()){
                            ventanaServidor.removerVentana(datosTPV.getId());
                        } else{
                            System.out.println("Maximas conexiones establecidas");
                        }
                    }
                }else{
                    System.out.println("Maximas conexiones establecidas");
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
