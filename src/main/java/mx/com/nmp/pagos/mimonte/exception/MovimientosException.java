/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.exception;

/**
 * @name MovimientosException
 * @description Clase de excepcion para encapsular excepciones lanzadas durante
 *              un proceso relacionado con movimientos en general
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:38 PM
 */
public class MovimientosException extends RuntimeException {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	public MovimientosException(String message) {
		super(message);
	}

}
