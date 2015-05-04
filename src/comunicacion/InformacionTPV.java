package comunicacion;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.swing.JTable;
/**
 *
 * @author Alejandro
 */
public class InformacionTPV implements Serializable {
	private long id;
	private int estado;
        private JTable tabla;
        private BigDecimal big;
        
	public InformacionTPV(long id, int estado, JTable tabla, BigDecimal big) {
		super();
		this.id = id;
		this.estado = estado;
                this.tabla = tabla;
                this.big = big;
	}
        
        public InformacionTPV(long id, int estado) {
		super();
		this.id = id;
		this.estado = estado;
	}
        
	public long getId() {
		return id;
	}
	public int getEstado() {
		return estado;
	}

        public JTable getTabla() {
            return tabla;
        }

        public BigDecimal getBig() {
            return big;
        }
	
	
}
