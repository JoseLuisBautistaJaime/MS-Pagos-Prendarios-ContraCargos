package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Date;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 09-May-2019 10:28:41 PM
 */
public class EstadoCuenta {

	private long id;
	private long idReporte;
	private EstadoCuentaCabecera cabecera;
	private EstadoCuentaTotales totales;
	private EstadoCuentaTotalesAdicional totalesAdicional;
	private Date fechaCarga;
	private int totalMovimientos;

	public EstadoCuenta(){

	}

	public void finalize() throws Throwable {

	}

}