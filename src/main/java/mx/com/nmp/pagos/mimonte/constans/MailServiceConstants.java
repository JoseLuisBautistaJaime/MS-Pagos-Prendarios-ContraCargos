/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.constans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @name MailServiceConstants
 * @description Interfaz que contiene valores constantes que mapean a
 *              propiedades del archivo de configuracion de spring y que estan
 *              relacionadas con el servicio de envio de mail o obtencion de
 *              token que expone BUS
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @createdDate 07/06/2019 15:33 hrs.
 */
@Component
public class MailServiceConstants {

	/**
	 * Titulo del e-mail para solicitud de pagos
	 */
	@Value("${mimonte.variables.mail.solicitud-pagos.title}")
	public String subjectMail;

	/**
	 * Tipo de codificacion para e-mail para solicitud de pagos
	 */
	@Value("${mimonte.variables.mail.solicitud-pagos.encode-type}")
	public String encodeType;

	/**
	 * Layout velocity para e-mail para solicitud de pagos
	 */
	@Value("${mimonte.variables.mail.solicitud-pagos.velocity-layout}")
	public String velocityLayout;

	/**
	 * Mail from para envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.from}")
	public String mailFrom;

	/**
	 * Usuario para envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.auth.user}")
	public String mailUser;

	/**
	 * Password para envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.auth.password}")
	public String mailPass;

	/**
	 * URL para servicio que provee el token
	 */
	@Value(value = "${mimonte.variables.mail.token.url}")
	public String urlGetToken;

	/**
	 * URL para el servicio de envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.send-mail.url}")
	public String urlSendEmail;

	/**
	 * Key de Cabecera de autenticacion
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.auth}")
	public String headerAuthKey;

	/**
	 * Valor de cabecera de autenticacion
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.auth-value}")
	public String headerAuthValue;

	/**
	 * Key de cabecera para tipo de contenido
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.content-type}")
	public String contentTypeKey;

	/**
	 * Valor para cabecera con tipo de contenido para servicio de token
	 */
	@Value(value = "${mimonte.variables.mail.token.headers.content-type-value}")
	public String tokenContentTypeValue;

	/**
	 * Valor para cabecera con tipo de contenido para servicio de envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.send-mail.headers.content-type-value}")
	public String senMailContentTypeValue;

	/**
	 * Key de Cabecera para id de consumidor
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.id-consumidor}")
	public String idConsumidorKey;

	/**
	 * Valor de cabecera para id consumidor
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.id-consumidor-value}")
	public String idConsumidorValue;

	/**
	 * Key para cabecera de id de destino
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.id-destino}")
	public String idDestinoKey;

	/**
	 * Valor para cabecera de id de destino
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.id-destino-value}")
	public String idDestinoValue;

	/**
	 * Key para cabecera de usuario
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.usuario}")
	public String usuarioKey;

	/**
	 * Valor para cabecera de usuario
	 */
	@Value(value = "${mimonte.variables.mail.common-headers.usuario-value}")
	public String usuarioValue;

	/**
	 * Key para cabecera de envio de mail que contiene el token
	 */
	@Value(value = "${mimonte.variables.mail.send-mail.headers.oauth-bearer-key}")
	public String oauthBearer;

	/**
	 * Bandera que indica si la operacion de envio se realizo correctamente
	 */
	@Value(value = "${mimonte.variables.mail.send-mail.success-flag}")
	public String flagSendingSuccess;

	/**
	 * Key de cabecera para propiedad GrantTypeKey
	 */
	@Value(value = "${mimonte.variables.mail.token.headers.grant-key}")
	public String headerGrantTypeKey;

	/**
	 * Valor de cabecera para propiedad GrantTypeKey
	 */
	@Value(value = "${mimonte.variables.mail.token.headers.grant-value}")
	public String headerGrantTypeValue;

	/**
	 * Key de cabecera para propiedad scope
	 */
	@Value(value = "${mimonte.variables.mail.token.headers.scope-key}")
	public String headerScopeKey;

	/**
	 * Valor de cabecera para propiedad scope
	 */
	@Value(value = "${mimonte.variables.mail.token.headers.scope-value}")
	public String headerScopeValue;

	/**
	 * Key para el objeto de respuesta 'respuesta' de envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.response.resp-key}")
	public String responseRespKey;

	/**
	 * Key para el objeto de respuesta 'codigo' de envio de e-mail
	 */
	@Value(value = "${mimonte.variables.mail.response.cod-key}")
	public String responseCodpKey;

	/**
	 * Clase que contiene las constantes que mapean las propiedades de textos del
	 * e-mail
	 * 
	 * @author user
	 *
	 */

	/**
	 * Mail text constnts properties
	 */
	@Value("${mimonte.variables.mail.text1}")
	public String text1;

	@Value("${mimonte.variables.mail.text2}")
	public String text2;

	@Value("${mimonte.variables.mail.text3}")
	public String text3;

	/**
	 * Constantes que mapean propiedades a poner como titulos de columna en la tabla
	 * de email de movimientos transito
	 * 
	 * @author user
	 *
	 */

	/**
	 * Table Headers constants properties
	 */
	@Value("${mimonte.variables.mail.table.headers.fechaTransaccion}")
	public String fechaTransaccion;

	@Value("${mimonte.variables.mail.table.headers.folioPartida}")
	public String folioPartida;

	@Value("${mimonte.variables.mail.table.headers.transaccion}")
	public String transaccion;

	@Value("${mimonte.variables.mail.table.headers.codigoDescuento}")
	public String codigoDescuento;

	@Value("${mimonte.variables.mail.table.headers.tipoTransaccion}")
	public String tipoTransaccion;

	@Value("${mimonte.variables.mail.table.headers.montoTransaccion}")
	public String montoTransaccion;

	@Value("${mimonte.variables.mail.table.headers.tipoMoneda}")
	public String tipoMoneda;

	@Value("${mimonte.variables.mail.table.headers.tipoCuenta}")
	public String tipoCuenta;

	@Value("${mimonte.variables.mail.table.headers.codigoRespuestaMotorPagosTransaccion}")
	public String codigoRespuestaMotorPagosTransaccion;

	@Value("${mimonte.variables.mail.table.headers.metodoPago}")
	public String metodoPago;

	@Value("${mimonte.variables.mail.table.headers.identificadorCuenta}")
	public String identificadorCuenta;

	@Value("${mimonte.variables.mail.table.headers.idTerminalAdquiriente}")
	public String idTerminalAdquiriente;

	@Value("${mimonte.variables.mail.table.headers.titularCuenta}")
	public String titularCuenta;

	@Value("${mimonte.variables.mail.table.headers.codigoAutorizacion}")
	public String codigoAutorizacion;

	@Value("${mimonte.variables.mail.table.headers.codigoRespuestaAdquiriente}")
	public String codigoRespuestaAdquiriente;

	@Value("${mimonte.variables.mail.table.headers.numeroLoteAdquiriente}")
	public String numeroLoteAdquiriente;

	@Value("${mimonte.variables.mail.table.headers.fuenteTransaccion}")
	public String fuenteTransaccion;

}
