package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:57 PM
 */
public class MovimientoMidas {

	private long id;
	private long idReporte;
	private String folio;
	private String operacionAbr;
	private String operacionDesc;
	private BigDecimal monto;
	private String tipoContratoAbr;
	private String tipoContratoDesc;
	private String numAutorizacion;
	private BigDecimal capital;
	private BigDecimal comisiones;
	private BigDecimal interes;
	private String estatus;

	public MovimientoMidas(){

	}

}