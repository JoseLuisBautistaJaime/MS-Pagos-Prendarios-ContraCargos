package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

import mx.com.nmp.pagos.mimonte.model.EstatusTransito;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:53 PM
 */
public class MovimientoTransito {

	private long id;
	private EstatusTransito estatus;
	private String folio;
	private Long sucursal;
	private Date fecha;
	private String operacionDesc;
	private BigDecimal monto;
	private String tipoContratoDesc;
	private String esquemaTarjeta;
	private String cuenta;
	private String titular;

	public MovimientoTransito(){

	}

}