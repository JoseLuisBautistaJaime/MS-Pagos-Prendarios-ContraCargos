package mx.com.nmp.pagos.mimonte.constans;

/**
 * Nombre: DSSConstants
 * Descripcion: Clase que almacena las constantes usadas en
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

	public final class QueryData {
		private QueryData() {
			/**
			 * hidden constructor
			 */
		}

		/**
		 * Variable para cargar la consulta 1
		 */
		public static final String QUERY_1 = "${mimonte.variables.querys.query1}";

		/**
		 * Variable para cargar la consulta 2
		 */
		public static final String QUERY_2 = "${mimonte.variables.querys.query2}";

		/**
		 * Variable para cargar la consulta 3
		 */
		public static final String QUERY_3 = "${mimonte.variables.querys.query3}";
	}

}
