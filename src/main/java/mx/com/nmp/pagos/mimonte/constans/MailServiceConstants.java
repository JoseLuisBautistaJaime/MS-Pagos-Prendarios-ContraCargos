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
	 * Clase que contiene las constantes que mapean las propiedades de textos del
	 * e-mail
	 * 
	 * @author user
	 *
	 */

	/**
	 * Mail text constnts properties
	 */
	@Value("${mimonte.variables.mail.solicitud-pagos.text1}")
	public String text1;

	@Value("${mimonte.variables.mail.solicitud-pagos.text2}")
	public String text2;

	@Value("${mimonte.variables.mail.solicitud-pagos.text3}")
	public String text3;

}
