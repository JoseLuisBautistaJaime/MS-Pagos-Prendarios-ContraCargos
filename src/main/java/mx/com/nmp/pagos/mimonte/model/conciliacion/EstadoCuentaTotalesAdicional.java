package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 09-May-2019 10:28:46 PM
 */
public class EstadoCuentaTotalesAdicional {

	private long id;
	private String clavePais;
	private String sucursal;
	private String cuenta;
	private String noCargos;
	private BigDecimal importeTotalCargos;
	private int noAbonos;
	private BigDecimal importeTotalAbonos;
	private int tipoSaldo;
	private BigDecimal saldoFinal;
	private String monedaAlfabetica;
	public EstadoCuenta totalesAdicional;

	public EstadoCuentaTotalesAdicional(){

	}

	public void finalize() throws Throwable {

	}

}