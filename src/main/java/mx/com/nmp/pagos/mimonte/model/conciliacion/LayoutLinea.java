package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;

import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:58:05 PM
 */
public class LayoutLinea extends Updatable {

	private long id;
	private Layout layout;
	private String linea;
	private String cuenta;
	private String depId;
	private String unidadOperativa;
	private String negocio;
	private String proyectoNmp;
	private BigDecimal monto;

	public LayoutLinea(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}