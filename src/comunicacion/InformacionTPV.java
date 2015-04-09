package comunicacion;

import java.io.Serializable;
/**
 *
 * @author Alejandro
 */
public class InformacionTPV implements Serializable {
	private long id;
	private boolean estado;
	public InformacionTPV(long id, boolean estado) {
		super();
		this.id = id;
		this.estado = estado;
	}
	public long getId() {
		return id;
	}
	public boolean isEstado() {
		return estado;
	}
	
	
}
