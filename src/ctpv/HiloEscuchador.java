package ctpv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import comunicacion.InformacionTPV;
import javax.swing.JFrame;

public class HiloEscuchador extends Thread {

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
                if (conexionCliente != null) {
                    ObjectInputStream entrada = new ObjectInputStream(conexionCliente.getInputStream());
                    InformacionTPV datosTPV = (InformacionTPV) entrada.readObject();
                    if (datosTPV.isEstado()) {
                        ventanaServidor.añadirVentana(datosTPV.getId());
                    } else {
                        ventanaServidor.removerVentana(datosTPV.getId());
                    }
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
