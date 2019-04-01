package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:58:00 PM
 */
public class MovimientoProveedor {

	private long id;
	private Long idReporte;
	private String idComerciante;
	private String idPedido;
	private String referenciaPedido;
	private String idTransaccion;
	private String referenciaTransaccion;
	private String entidadGestora;
	private Date fecha;
	private String metodoPago;
	private String tipoTransaccion;
	private BigDecimal monto;
	private String moneda;
	private String resultado;
	private String codigoPuertaEnlace;
	private String esuemaTarjeta;
	private String identificadorCuenta;
	private String identificadorBanco;
	private String titularCuenta;
	private String codigoAutorizacion;
	private String codigoRespuesta;
	private String numeroLotePago;
	private String origenTransaccion;
	private String recomendacionRiesgo;
	private String resultadoRevisionRiesgo;
	private String respuestaAvs;
	private String respuestaCsc;
	private String respuesta3ds;
	private String _3dseci;
	private String reciboTransaccion;

	public MovimientoProveedor(){

	}

}