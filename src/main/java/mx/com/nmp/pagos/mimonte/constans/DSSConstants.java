package mx.com.nmp.pagos.mimonte.constans;

/**
 * Nombre: DSSConstants Descripcion: Clase que almacena las constantes usadas en
 * el modulo DSS.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 13/11/2018 10:35 hrs.
 * @version 0.1
 */
public class DSSConstants {

	private DSSConstants() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * Mensaje que se usa en excepcion cuando no se encuentran reglas de negocio
	 * asociadas a un cliente
	 */
	public static final String NO_RULES_FOUND_MESSAGE = "No se encontro ninguna regla para el cliente solicitado";

	/**
	 * Mensaje que se muestra cuando se intenta buscar un cliente y se genera un
	 * error
	 */
	public static final String CLIENT_ERROR_MESSAGE = "Se genero un error relacionado con el cliente";

	/**
	 * Propiedad para mapear el nombre de la variable idCliente del archivo de
	 * propiedades
	 */
	public static final String ID_CLIENTE_PROP = "${mimonte.variables.dss.idcliente}";

	/**
	 * Propiedad para mapear el nombre de la variable idReglaNegocio del archivo de
	 * propiedades
	 */
	public static final String ID_REGLA_PROP = "${mimonte.variables.dss.idregla}";

	/**
	 * Propiedad para mapear el nombre de la variable IdAfiliacion del archivo de
	 * propiedades
	 */
	public static final String ID_AFILAICION_REGLA_PROP = "${mimonte.variables.dss.idafiliacionregla}";

	/**
	 * Propiedad para mapear el tipo de afiliacion de el archivo de propiedades
	 */
	public static final String ID_TIPO_REGLA_PROP = "${mimonte.variables.dss.idtipo}";

}
