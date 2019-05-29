package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 28-May-2019 8:17:09 PM
 */
public class ComisionTransaccionReal {

	private Long id;
	private ComisionTransaccion comisionTransaccion;
	private BigDecimal comision;
	private BigDecimal ivaComision;
	private BigDecimal total;

	public ComisionTransaccionReal(){

	}

	public void finalize() throws Throwable {

	}

}