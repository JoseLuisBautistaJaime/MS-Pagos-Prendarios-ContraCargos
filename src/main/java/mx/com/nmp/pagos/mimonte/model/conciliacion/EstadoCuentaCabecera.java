package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 09-May-2019 10:28:44 PM
 */
public class EstadoCuentaCabecera {

	private long id;
	private String clavePais;
	private String sucursal;
	private String cuenta;
	private int tipoSaldo;
	private BigDecimal saldoInicial;
	private String monedaAlfabetica;
	private String digitoCuentaClabe;
	private String titularCuenta;
	private String plazaCuentaClabe;
	private String libre;

	public EstadoCuentaCabecera(){

	}

	public void finalize() throws Throwable {

	}

}