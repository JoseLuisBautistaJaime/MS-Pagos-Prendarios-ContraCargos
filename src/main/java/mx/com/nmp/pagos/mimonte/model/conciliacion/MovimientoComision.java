package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:51 PM
 */
public class MovimientoComision {

	private long id;
	private Date fechaOperacion;
	private Date fechaCargo;
	private BigDecimal monto;
	private String descripcion;

	public MovimientoComision(){

	}

}