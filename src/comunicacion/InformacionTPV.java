package comunicacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import tpv.ProductoPedido;
/**
 *
 * @author Alejandro
 */
public class InformacionTPV implements Serializable {
	private long id;
	private int estado;
        private HashMap<String, ProductoPedido> listaPedidos;
        private BigDecimal big;
        
        public InformacionTPV(long id, int estado) {
		super();
		this.id = id;
		this.estado = estado;
	}

    public InformacionTPV(long id, int estado, HashMap<String, ProductoPedido> listaPedidos, BigDecimal big) {
        this.id = id;
        this.estado = estado;
        this.listaPedidos = listaPedidos;
        this.big = big;
    }
        
                
        
	public long getId() {
		return id;
	}
        
	public int getEstado() {
		return estado;
	}

        public BigDecimal getBig() {
            return big;
        }

    public HashMap<String, ProductoPedido> getListaPedidos() {
        return listaPedidos;
    }
	
	
}
