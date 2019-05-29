package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 28-May-2019 8:17:10 PM
 */
public class ComisionTransaccion extends Updatable {

	private Long id;
	private Long idConciliacion;
	private Date fechaDesde;
	private Date fechaHasta;
	private BigDecimal comision;

	public ComisionTransaccion(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}