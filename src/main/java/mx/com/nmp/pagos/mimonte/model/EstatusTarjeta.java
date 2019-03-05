package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

/**
 * Nombre: EstatusTarjeta
 * Descripcion: Entidad que representa el estatus de la tarjeta dentro del sistema.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * Fecha: 21/11/2018 17:15 Hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_estatus_tarjeta")
public class EstatusTarjeta extends AbstractCatalogo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2044332050005722621L;

	public EstatusTarjeta() {
		super();
	}

}
