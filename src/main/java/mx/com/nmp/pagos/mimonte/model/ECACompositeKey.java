/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

/**
 * @name ECACompositeKey
 * @description Clase que encapsula la informacion de un objeto de tipo
 *              ECACompositeKey que es donde se guardan laas relaciones entre
 *              entidades, cuentas y afiliaciones
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 18/04/2019 00:27 hrs.
 * @version 0.1
 */
@SuppressWarnings("unused")
public class ECACompositeKey implements java.io.Serializable {

	/**
	 * Setial id
	 */
	private static final long serialVersionUID = 1L;

	private Long entidad;
	private Long cuenta;
	private Long afiliacion;

}
