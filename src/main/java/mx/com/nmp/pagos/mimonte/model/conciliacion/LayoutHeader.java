package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Date;

import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:58:04 PM
 */
public class LayoutHeader extends Updatable {

	private long id;
	private Layout layout;
	private String cabecera;
	private String unidadNegocio;
	private String descripcion;
	private String codigoOrigen;
	private Date fecha;
	private String campo1;
	private String campo2;

	public LayoutHeader(){

	}


}