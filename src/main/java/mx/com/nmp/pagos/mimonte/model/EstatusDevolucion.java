package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @name EstatusOperacion
 * @description Clase que mapea el catalogo de operaciones
 * @date: 31/01/2018 14:49 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_estatus_devolucion")
public class EstatusDevolucion extends AbstractCatalogo implements Serializable {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 4424811334427701915L;

	public EstatusDevolucion() {
		super();
	}

}
