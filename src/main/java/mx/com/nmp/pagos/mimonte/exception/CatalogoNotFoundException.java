/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.exception;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;

/**
 * @name CatalogoNotFoundException
 * @description Clase se excepcion personalizada que indica que no se
 *              encontraron resultados de extrafilter durante la busqueda de los
 *              mismos
 *
 * @author Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version 0.1
 */
public class CatalogoNotFoundException extends RuntimeException {
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * El codigo del error a mostrar
	 */
	private CodigoError codigoError;

	/**
	 * Constructs a new runtime exception with the specified detail message. The
	 * cause is not initialized, and may subsequently be initialized by a call to
	 * {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for later
	 *                retrieval by the {@link #getMessage()} method.
	 */
	public CatalogoNotFoundException(String message) {
		super(message);
	}

	public CatalogoNotFoundException(String message, CodigoError codigoError) {
		super(message);
		this.codigoError = codigoError;
	}

	public CodigoError getCodigoError() {
		return codigoError;
	}

}
