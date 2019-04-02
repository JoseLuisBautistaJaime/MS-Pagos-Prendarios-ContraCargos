package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

import mx.com.nmp.pagos.mimonte.model.EstatusDevolucion;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:49 PM
 */
public class MovimientoDevolucion {

	private long id;
	private EstatusDevolucion estatus;
	private Date fecha;
	private BigDecimal monto;
	private String esquemaTarjeta;
	private String identificadorCuenta;
	private String titular;
	private String codigoAutorizacion;
	private Long sucursal;

	public MovimientoDevolucion(){

	}


}