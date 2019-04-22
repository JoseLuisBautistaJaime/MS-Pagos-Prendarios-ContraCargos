package mx.com.nmp.pagos.mimonte.model.conciliacion;

import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.EstatusConciliacion;
import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * Registro de conciliacion.
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:43 PM
 */
public class Conciliacion extends Updatable {

	private long id;
	private EstatusConciliacion estatus;
	private Entidad entidad;
	private Cuenta cuenta;
	private String peopleSoftId;
	private Global global;

	public Conciliacion(){

	}


}