/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package comunicacion;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.swing.JTable;

/**
 *
 * @author Alejandro
 */
public class InformacionTicket implements Serializable {
    private JTable tabla;
    private BigDecimal big;

    public InformacionTicket(JTable tabla, BigDecimal big) {
        this.tabla = tabla;
        this.big = big;
    }

    public JTable getTabla() {
        return tabla;
    }

    public BigDecimal getBig() {
        return big;
    }
    
}
