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
	
}
