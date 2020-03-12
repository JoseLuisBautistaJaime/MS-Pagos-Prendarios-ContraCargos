package mx.com.nmp.pagos.mimonte.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @name EstatusOperacion
 * @description Clase que mapea el catalogo de operaciones
 * @date: 31/01/2018 14:49 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_estatus_operacion")
public class EstatusOperacion extends AbstractCatalogo {

	public EstatusOperacion() {
		super();
	}

}
