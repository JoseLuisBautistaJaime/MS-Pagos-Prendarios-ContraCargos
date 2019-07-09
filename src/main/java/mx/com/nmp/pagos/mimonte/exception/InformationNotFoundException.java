/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.exception;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;

/**
 * @name InformationNotFoundException
 * @description Clase de excepcion lanzada cuando no se encuentran resultados de
 *              la informacion solicitada
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @createdDate 29/04/2019 18:24 hrs.
 */
public class InformationNotFoundException extends RuntimeException {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Codigo de error
	 */
	private CodigoError codigoError;

	public InformationNotFoundException(String message) {
		super(message);
	}

	public InformationNotFoundException(String message, CodigoError codigoError) {
		super(message);
		this.codigoError = codigoError;
	}

	public CodigoError getCodigoError() {
		return codigoError;
	}

}
