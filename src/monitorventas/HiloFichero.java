/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package monitorventas;

/**
 *
 * @author Alejandro
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julen-PC
 */
public class HiloFichero implements Runnable {

    public static final String fichero = "Ventas.txt";
    private MV ventana;

    public HiloFichero(MV f) {
        ventana = f;

    }

    @Override
    public void run() {
        while (true) {
            try {
                ventana.contarLineasFichero();
                Thread.sleep(500);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(HiloFichero.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HiloFichero.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloFichero.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }

}
