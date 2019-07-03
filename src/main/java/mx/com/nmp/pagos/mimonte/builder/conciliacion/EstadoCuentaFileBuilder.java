/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.Date;

import mx.com.nmp.pagos.mimonte.util.DateUtil;

/**
 * @name EstadoCuentaFileBuilder
 * 
 * @description Clase que se encarga de construir el nombre del archivo a consultar
 * @author Quarksoft
 * @version 1.0
 */
public class EstadoCuentaFileBuilder {

	public static String buildPath(Date fecha, String formato) {
		return DateUtil.formatDate(fecha, formato);
	}

	public static String buildFileName(Date fecha, String cuenta, String formato) {
		return DateUtil.formatDate(fecha, formato.replace("{CUENTA}", cuenta));
	}
	
	

}
