/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.constans;

/**
 * @name LayoutMailConstants
 * @description Clase de constantes que almacena los layouts a usar en el envio de mails
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 04/06/2019 22:12 hrs.
 * @version 0.1
 */
public final class LayoutMailConstants {

	private LayoutMailConstants() {
		super();
	}

	/**
	 * Layout HTML para Solicitud de Pagos
	 */
	public static final String HTML_SOLICITAR_PAGOS_LAYOUT = "<!DOCTYPE>\r\n" + 
			"<HTML>\r\n" + 
			"  <HEAD>\r\n" + 
			"    <STYLE>\r\n" + 
			"      body{\r\n" + 
			"        font-family: georgia, serif;\r\n" + 
			"        font-size: 20px;\r\n" + 
			"      }\r\n" + 
			"      span {\r\n" + 
			"        color: #7B1837;\r\n" + 
			"      }\r\n" + 
			"      h3 {\r\n" + 
			"        font-family: georgia, serif;\r\n" + 
			"        color: #194D6F;\r\n" + 
			"        font-style: bold;\r\n" + 
			"      }\r\n" + 
			"    </STYLE>\r\n" + 
			"    <h3>[headContent]</h3>\r\n" + 
			"  </HEAD>\r\n" + 
			"  <BODY>\r\n" + 
			"    [bodyContent]\r\n" + 
			"  </BODY>\r\n" + 
			"</HTML>\r\n" + 
			"";

}
