/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services;

import java.util.Date;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaFileLayout;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaImplementacionEnum;

/**
 * @name EstadoCuentaReader
 * @description Clase que se encarga de la lectura del archivo de estado de cuenta
 *
 * @author Jorge Galvez
 * @version 0.1
 */
public interface EstadoCuentaReaderService {

	public EstadoCuentaFileLayout read(Date date, Long idConciliacion, EstadoCuentaImplementacionEnum implementacion);

}
