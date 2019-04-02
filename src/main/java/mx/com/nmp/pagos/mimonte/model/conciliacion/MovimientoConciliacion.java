package mx.com.nmp.pagos.mimonte.model.conciliacion;

import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:47 PM
 */
public abstract class MovimientoConciliacion extends Updatable {

	private Integer id;
	private Integer idConciliacion;
	private Boolean nuevo;

	public MovimientoConciliacion(){

	}

}