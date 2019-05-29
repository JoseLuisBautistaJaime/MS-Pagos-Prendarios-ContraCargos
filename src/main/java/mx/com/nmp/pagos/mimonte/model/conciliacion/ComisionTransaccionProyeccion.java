package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 28-May-2019 8:17:07 PM
 */
public class ComisionTransaccionProyeccion {

	private Long id;
	private ComisionTransaccion comisionTransaccion;
	private Integer operacion;
	private BigDecimal comsion;
	private BigDecimal ivaComision;
	private BigDecimal total;

	public ComisionTransaccionProyeccion(){

	}

	public void finalize() throws Throwable {

	}

}