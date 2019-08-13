package mx.com.nmp.pagos.mimonte.constans;

/**
 * Nombre: PagoConstants Descripcion: Clase que almacena las constantes usadas
 * para operaciones relacionadas con el pago de partidas / contratos.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 20/11/2018 10:00 hrs.
 * @version 0.1
 */
public final class PagoConstants {

	private PagoConstants() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * Mensaje que es mostrado cuando se intenta agregar un pago con una cantidad de partidas superior al especificado
	 */
	public static final String PARTIDAS_QUANTITY_EXCEDED = "La cantidad de partidas rebasa el limite permitdo";
	
	/**
	 * @description Constantes relacionadas con el tamano de campos de base de datos
	 *
	 * @author Ismael Flores iaguilar@quarksoft.net
	 * @creationDate 06/02/2019 13:19 hrs.
	 * @version 0.1
	 */
	public static final class FieldSizeConstants {

		private FieldSizeConstants() {
			/**
			 * Hidden constructor
			 */
		}

		/**
		 * Mensaje que es mostrado cuando se revasa la longitud del concepto del pago
		 */
		public static final String CONCEPTO_SIZE_TOO_LONG = "La longitud del concepto es muy grande";
		
		/**
		 * Mensaje que es mostrado cuando se revasa la longitud del nombre de la operacion
		 */
		public static final String NOMBRE_OPERACION_SIZE_TOO_LONG = "La longitud del nombre de la operacion es muy grande";

		/**
		 * Mensaje que es mostrado cuando se intenta guardar un id de transaccion midas
		 * superior al definido en base de datos
		 */
		public static final String MIDAS_TRANSACTION_ID_TOO_LONG = "El id de transaccion midas es muy grande";

		/**
		 * Mensaje que es mostrado cuando se intenta ingresar un numero de monto total
		 * mayor al definido en base de datos
		 */
		public static final String TOTAL_AMOUNT_TOO_LONG = "El monto total es muy grande";

		/**
		 * Mensaje que es mostrado cuando el id del cliente es menor o igual a 0
		 */
		public static final String CLIENT_ID_LESS_OR_EQUAL_THAN_0 = "El id de cliente debe ser mayor a 0";

		/**
		 * Mensaje que es mostrado cuando se intenta ingresar un id de cliente mas
		 * grande que el definido en base de datos
		 */
		public static final String CLIENT_ID_TOO_LONG = "El id de cliente es muy grande";

		/**
		 * Mensaje que es mostrado cuando se intenta ingresar un folio de partida que
		 * revaza en tamano al definido en base de datos
		 */
		public static final String CONTRACT_FOLIO_TOO_LONG = "El folio de partida es muy largo";

		/**
		 * Mensaje que es mostrado cuando el id de operacion es de un tamano superior al
		 * definido en base de datos
		 */
		public static final String OPERATION_ID_TOO_LONG = "El id de operacion es muy largo";

		/**
		 * Mensaje que es mostrado cuando se intenta insertar un monto superior al
		 * dfinido en base de datos
		 */
		public static final String OPERATION_AMOUNT_TOO_LONG = "El monto de una operacion es muy grande";
		
	}

	/**
	 * @description Constantes relacionadas con variables que existen en el archivo
	 *              de propiedades de la aplicacion
	 *
	 * @author Ismael Flores iaguilar@quarksoft.net
	 * @creationDate 06/02/2019 13:19 hrs.
	 * @version 0.1
	 */
	public static final class VariablesConstants {

		private VariablesConstants() {
			/**
			 * empty constructor
			 */
		}

		/**
		 * Nombre de la propiedad que en archivo de configuracion YML que contiene el
		 * dato de la cantidad maxima de tarjetas posibles por cliente
		 */
		public static final String MAXIMUM_AMOUNT_OF_CARDS_PROPERTY = "${mimonte.variables.cantidad-maxima-tarjetas}";
	}

	/**
	 * @description Constantes relacionadas claves de mapas usados en la apliccion
	 *              para el modulo de pagos
	 *
	 * @author Ismael Flores iaguilar@quarksoft.net
	 * @creationDate 06/02/2019 13:19 hrs.
	 * @version 0.1
	 */
	public static final class MapValuesConstants {
		private MapValuesConstants() {
			/**
			 * hidden constructor
			 */
		}

		/**
		 * Valor de nombre de clave en mapa en id de afiliacion
		 */
		public static final String ID_AFILIACION_MAPPING_NAME = "idAfiliacionMap";

		/**
		 * Valor de nombre de el tipoAutorizacion en la afiliacion
		 */
		public static final String TIPO_AUTORIZACION_MAPPING_NAME = "tipoAutorizacion";

	}

	/**
	 * @description Constantes relacionadas con el tamano de campos de base de datos
	 *              usados para el mapeo en la aplicacion
	 *
	 * @author Ismael Flores iaguilar@quarksoft.net
	 * @creationDate 06/02/2019 13:19 hrs.
	 * @version 0.1
	 */
	public static final class DataBaseFieldType {

		private DataBaseFieldType() {
			/**
			 * hidden constructor
			 */
		}

		public static final Integer PAGO_DESCRIPCION_FIELD = 200;
		public static final Long PAGO_ID_TRANSACCION_MIDAS_FIELD = JavaTypeLimit.LONG_VALUE;
		public static final Double PAGO_MONTO_TOTAL_FIELD = JavaTypeLimit.DOUBLE_VALUE;
		public static final Long PAGO_ID_CLIENTE_FIELD = JavaTypeLimit.LONG_VALUE;
		public static final Long PAGO_FOLIO_PARTIDA_FIELD = JavaTypeLimit.LONG_VALUE;
		public static final Integer PAGO_ID_OPERACION_FIELD = JavaTypeLimit.INTEGER_VALUE;
		public static final Integer PAGO_NOMBRE_OPERACION_FIELD = 200;
		public static final Double PAGO_MONTO_OPERACION_FIELD = JavaTypeLimit.DOUBLE_VALUE;

	}

	/**
	 * @description Constantes relacionadas con el tamano estandar de campos de JAVA
	 *
	 * @author Ismael Flores iaguilar@quarksoft.net
	 * @creationDate 06/02/2019 13:19 hrs.
	 * @version 0.1
	 */
	public static final class JavaTypeLimit {
		public static final Long LONG_VALUE = 9223372036854775807L;
		public static final Integer INTEGER_VALUE = 2147483647;
		public static final Double DOUBLE_VALUE = 1.7976931348623157E+308;
	}

	/**
	 * Mensaje que sera enviado si se se relaliza el pago correctamente
	 */
	public static final String MSG_SUCCESS = "Registro de pago realizado correctamente.";

	/**
	 * Mensaje que será mostrado cuando no se pueda realizar el pago por un error
	 * desconocido
	 * 
	 */
	public static final String UNKNOWN_PAY_ERROR = "Error desconocido al intentar realizar pago";

	/**
	 * Mensaje que sera mostrado cuando se desee guardar una nueva tarjeta en un
	 * pago, pero los datos de esta esten incompletos
	 */
	public static final String INCOMPLETE_CARD_DATA = "Falta uno o mas datos de la tarjeta";

	/**
	 * Mensaje que sera mostrado cuando se desee guardar una nueva tarjeta en un
	 * pago, pero los datos de esta esten incompletos
	 */
	public static final String NO_OPERATIONS_MESSAGE = "No existen operaciones";

	/**
	 * Mensaje que sera mostrado cuando se desee guardar una tarjeta pero el cliente
	 * en cuestion ya haya alcanzado la cantidad maxima posible de tarjetas
	 * guardadas
	 */
	public static final String MAXIMUM_AMOUNT_OF_CARDS_ACHIEVED = "Ya se ha alcanzado la cantidad máxima de tarjetas por cliente";

	/**
	 * Mensaje que es mostrado cuando se intenta guardar una tarjeta que ya existe
	 * mediante el servicio de Pago
	 */
	public static final String MSG_CARD_WAS_NOT_SAVED = "El pago se registro de manera correcta pero la tarjeta no se guardo porque ya existe";

	/**
	 * Mensaje que es enviado cuando se intenta registrar el pago de un cliente que
	 * no existe
	 */
	public static final String CLIENTE_NOT_FOUND = "El cliente no existe";

	/**
	 * Mensaje que es enviado cuando la suma de el monto de las operaciones es
	 * diferente al monto total
	 */
	public static final String IRREGULAR_OPERATIONS_AMOUNT = "La suma del monto de las operaciones debe ser igual a el total de el pago";

	/**
	 * Mensaje que es mostrado cuando el monto total del pago es menor o igual 0
	 */
	public static final String TOTAL_AMOUNT_LESS_OR_EQUAL_THAN_0 = "El monto total del pago es menor o igual a 0";

	/**
	 * Mensaje que es enviado cuando el id de una operacion es menor o igual a 0
	 */
	public static final String OPERATION_ID_LESS_OR_EQUAL_THAN_0 = "El id de una o mas operaciones es menor o igual a 0";

	/**
	 * Mensaje que es enviado cuando el monto de una operacion es menor o igual a 0
	 */
	public static final String OPERATION_AMOUNT_LESS_OR_EQUAL_THAN_0 = "El monto de una o mas operaciones es menor o igual a 0";

	/**
	 * Mensaje que es enviado cuando el folio de la partida no es un valor numerico
	 * entero
	 */
	public static final String NUMBER_FORMAT_IN_FOLIO_CONTRATO = "El formato del folio de la partida o contrato debe ser numerico";

	/**
	 * Mensaje que es enviado cuando el id de transaccion de midas no es un valor
	 * numerico entero
	 */
	public static final String NUMBER_FORMAT_IN_ID_TRANSACCION_MIDAS = "El formato del id de transaccion de midas debe ser numerico";

	/**
	 * Mensaje que es mostrado cuando se intenetan agregar registro de operaciones
	 * con id de transaccion que ya existe
	 */
	public static final String TRANSACTION_ID_ALREADY_EXISTS = "La transaccion que desea registrar ya existe";

	/**
	 * Mensaje que es mostrado cuando se genra un error durante la validacion de si
	 * el id de transaccion ya existe
	 */
	public static final String MSG_CAN_NO_CHECK_IF_PAGO_EXISTS = "No se puede comprobar si el pago existe, contacte al Administrador";

	/**
	 * Mensaje que es mostrado cuando se intenta guardar una tarjeta y el request no
	 * tiene el objeto tarjeta
	 */
	public static final String MSG_CARD_DATA_IS_NULL = "Si desea guardar la tarjeta el objeto tarjeta no debe ser nulo";

	/**
	 * Mensaje que es mostrado cuando se intenat violar una regla de integridad de
	 * base de datos
	 */
	public static final String CONSTRAINT_DATABASE_ERROR = "Error con uno mas ids de base de datos que intenta insertar";

	/**
	 * Mensaje que es enviado cuando la infromacion del pago es nula o vacia
	 */
	public static final String PAYMENT_INFORMATION_EMPTY_OR_NULL = "La informacion del pago es nula o vacia";

	/**
	 * Mensaje que es mostrado cuando el objeto tarjeta es vacio o nulo
	 */
	public static final String CARD_OBJECT_IS_NULL_OR_EMPTY = "El objeto tarjeta es nulo o vacio";

	/**
	 * Mensaje que es mostrado cuando se genera un error no esperado o no controlado
	 * y se realiza un rollback
	 */
	public static final String ROLL_BACK_EXCEPCION_MESSAGE = "Ocurrio un error insesperado y no se realizo ninguna accion, por favor revize los datos del request";
	
	/**
	 * Mensaje que es mostrado cuando el concepto del pago es vacio
	 */
	public static final String CONCEPTO_PAGO_CANT_BE_EMPTY = "El concepto del pago no puede ser vacio";

	/**
	 * Mensaje que es mostrado cuando el id de transaccion midas es vacio
	 */
	public static final String ID_TRANSACCION_MIDAS_CANT_BE_EMPTY = "El id de transaccion midas no puede ser vacio";
	
	/**
	 * Mensaje que es mostrado cuando el folio del contrato es vacio
	 */
	public static final String FOLIO_CONTRATO_CANT_BE_EMPTY = "El folio del contrato no puede ser vacio";
	
	/**
	 * Mensaje que es mostrado cuando el nombre de la operacion es vacio
	 */
	public static final String NOMBRE_OPERACION_CANT_BE_EMPTY = "El nombre de la operacion no puede ser vacio";
	
}
