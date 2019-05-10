package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:58 PM
 */
public class MovimientoEstadoCuenta {

	private long id;
	private Long idReporte;
	private Date fecha;
	private String descripcion;
	private BigDecimal depositos;
	private BigDecimal retiros;
	private BigDecimal saldo;
	private long idEstadoCuenta;
	private String clavePais;
	private String sucursal;
	private Date fechaOperacion;
	private Date fechaValor;
	private String libre;
	private String claveLeyenda;
	private int tipoMovimiento;
	private BigDecimal importe;
	private String dato;
	private String concepto;
	private String codigoDato;
	private String referenciaAmpliada;
	private String referencia;

	public MovimientoEstadoCuenta(){

	}

	public void finalize() throws Throwable {

	}

}