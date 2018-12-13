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

	public final class DataObjectNames {
		private DataObjectNames() {
			/**
			 * hidden constructor
			 */
		}

		public final class ClienteData{
			private ClienteData() {
				/**
				 * hidden constructor
				 */
			}
			
			/**
			 * Propiedad que contiene el mapeo de id de cliente en un uso dinamico
			 */
			public static final String ID_CLIENTE = "{mimonte.variables.dss.cliente.idcliente}";
		}
		
		public final class PagoData {
			private PagoData() {
				/**
				 * hidden constructor
				 */
			}
			
			/**
			 * Propiedad que contiene el mapeo de monto total de un pago en un uso dinamico
			 */
			public static final String MONTO_TOTAL = "{mimonte.variables.dss.pago.montototal}";
		}

		public final class TarjetaData {
			private TarjetaData() {
				/**
				 * hidden constructor
				 */
			}

			/**
			 * Propiedad que contiene el mapeo de un alias de tarjeta en un uso dinamico
			 */
			public static final String ALIAS_TARJETA = "{mimonte.variables.dss.tarjeta.aliastarjeta}";
			
			/**
			 * Propiedad que contiene el mapeo de digitos tarjeta en un uso dinamico
			 */
			public static final String DIGITOS_TARJETA = "{mimonte.variables.dss.tarjeta.digitostarjeta}";
			
			/**
			 * Propiedad que contiene el mapeo de un token de trajeta en un uso dinamico
			 */
			public static final String TOKEN_TARJETA = "{mimonte.variables.dss.tarjeta.tokentarjeta}";
			
			/**
			 * Propiedad que contiene el mapeo de id de tipo de tarjeta en un uso dinamico
			 */
			public static final String TIPO_TARJETA_ID = "{mimonte.variables.dss.tarjeta.tipotarjeta.id}";
		}
	}

}
