/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.exception;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;

/**
 * @name CatalogoException
 * @description Clase de excepcion personalizada que indica que hubo un problema
 *              con una operaicon relacionada con un catalogo
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 04/03/2019 10:10 hrs.
 * @version 0.1
 */
public class CatalogoException extends RuntimeException {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 5467450105229263241L;

	/**
	 * El codigo del error a mostrar
	 */
	private CodigoError codigoError;

	public CatalogoException(String message) {
		super(message);
	}

	public CatalogoException(String message, CodigoError codigoError) {
		super(message);
		this.codigoError = codigoError;
	}

	public CodigoError getCodigoError() {
		return codigoError;
	}

}
