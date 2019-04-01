package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Date;

import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:54 PM
 */
public class Reporte extends Updatable {

	private long id;
	private Long idConciliacion;
	private TipoReporteEnum tipo;
	private Boolean disponible;
	private Date fechaDesde;
	private Date fechaHasta;

	public Reporte(){

	}

}