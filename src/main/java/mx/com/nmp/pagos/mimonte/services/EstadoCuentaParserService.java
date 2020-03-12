/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services;

import mx.com.nmp.pagos.mimonte.dto.EstadoCuentaWraper;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaFileLayout;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;

/**
 * @name EstadoCuentaParser
 * @description Clase que se encarga de extraer el contenido del archivo de estado de cuenta
 *
 * @author Jorge Galvez
 * @version 0.1
 */
public interface EstadoCuentaParserService {

	/**
	 * Extraer el archivo del estado de cuenta y construye un objeto EstadoCuenta
	 * @param estadoCuenta
	 * @return
	 * @throws ConciliacionException
	 */
	public EstadoCuentaWraper extract(EstadoCuentaFileLayout estadoCuenta) throws ConciliacionException;

}
