package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:46 PM
 */
public class Global {

	private long id;
	private Long idConciliacion;
	private Date fecha;
	private Integer movmientos;
	private Integer partidas;
	private BigDecimal monto;
	private BigDecimal importeMidas;
	private BigDecimal importeProveedor;
	private BigDecimal importeBanco;
	private Integer devoluciones;
	private BigDecimal diferenciaProveedorMidas;
	private BigDecimal diferenciaProveedorBanco;

	public Global(){

	}

}