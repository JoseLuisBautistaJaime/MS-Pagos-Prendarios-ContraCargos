/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.constans;

/**
 * @name ConciliacionConstants
 * @description Clase con valores con valores constantes utiliazados en los
 *              procesos relacionados a Conciliacion
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @createdDate 26/04/2019 15:33 hrs.
 */
public final class ConciliacionConstants {

	private ConciliacionConstants() {
		super();
	}

	public static final String USER_SYSTEM = "System";
	
	/**
	 * Valor estatico para un tamanio de 10 caracteres
	 */
	public static final int ENTITY_VALIDATION_SIZE_VALUE_10 = 10;

	/**
	 * Valor estatico para un tamanio de 45 caracteres
	 */
	public static final int ENTITY_VALIDATION_SIZE_VALUE_45 = 45;

	/**
	 * Mensaje que es mostrado cuando s intenta ingresar una cadena de caracteres
	 * mayor a 10
	 */
	public static final String ENTITY_VALIDATION_SIZE_MESSAGE_10 = "Debe ingresar maximo 10 caracteres";

	/**
	 * Mensaje que es mostrado cuando s intenta ingresar una cadena de caracteres
	 * mayor a 45
	 */
	public static final String ENTITY_VALIDATION_SIZE_MESSAGE_45 = "Debe ingresar maximo 45 caracteres";

	/**
	 * Mensaje que es mostrado si se genera un error en la creacion del objeto
	 * reporte en BD
	 */
	public static final String REPORT_GENERATION_ERROR_MESSAGE = "Se ha generado un error durante la generacion del reporte";

	/**
	 * Mensaje que es mostrado cuando se realiza una consulta de manera exitosa
	 */
	public static final String MSG_SUCCESSFUL_MOVIMIENTOS_QUERY = "Consulta movimientos exitosa.";

	/**
	 * Valor que se setea por default para el alta de la conciliacion
	 */
	public static final String EN_PROCESO = "En Proceso";

	/**
	 * Valor que se setea por default para el alta de la subConciliacion;
	 */
	public static final String CREADA = "Creada";

	/**
	 * Mensaje mostrado para un alta exitosa.
	 */
	public static final String SAVE_SUCCESSFUL = "Alta exitosa.";

	/**
	 * Mensaje mostrado para una consulta exitosa.
	 */
	public static final String SUCCESSFUL_SEARCH = "Consulta exitosa.";

	/**
	 * Mensaje mostrado para una consulta exitosa.
	 */
	public static final String SUCCESSFUL_UPDATE = "Actualización exitosa.";

	/**
	 * Mensaje mostrado para una consulta exitosa.
	 */
	public static final String SUCCESSFUL_GENERACION = "Se inicia proceso de conciliacion.";

	/**
	 * Mensaje que es mostrado cuando se intenta buscar o asociar un id de
	 * conciliacion que no existe
	 */
	public static final String CONCILIACION_ID_NOT_FOUND = "No se encontro el id de conciliacion ingresado";

	/**
	 * Mensaje mostrado cuando se ocurre un error al cambiar el estatus.
	 */
	public static final String AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS = "Ocurrio un error al actualizar el estatus de la devolución";

	/**
	 * Constante para buscar devoluciones a partir del nombre.
	 */
	public static final String PENDING = "Pendiente";

	/**
	 * Constante para buscar devoluciones a partir del nombre.
	 */
	public static final String REQUEST = "Solicitada";

	/**
	 * Constante para buscar devoluciones a partir del nombre.
	 */
	public static final String LIQUIDATE = "Liquidada";

	/**
	 * Se regresa cuando no se encuentra informacion en una busqueda relacionada con
	 * los procesos de conciliacion en general
	 */
	public static final String INFORMATION_NOT_FOUND = "No se encontro informacion relacionada con los parametros de busqueda";

	/**
	 * Mensaje que es mostrado cuando no es posible obtener una conciliacion de
	 * acuerdo a los parametros recibidos
	 */
	public static final String CONCILIACION_NOT_FOUND_FOR_SUCH_PARAMS = "No se encontro una conciliacion para los parametros especificados";

	/**
	 * Mensaje que es mostrado cuando no es posible obtener una comision de acuerdo
	 * a los parametros recibidos
	 */
	public static final String COMISION_NOT_FOUND_FOR_SUCH_PARAMS = "No se encontro una conciliacion para los parametros especificados";

	/**
	 * Mensaje que es mostrado cuando se intenta eliminar una comision y esta no
	 * puede ser eliminada por regla de negocio
	 */
	public static final String COMISION_CANT_BE_DELETED = "La comision no puede ser eliminada ya que fue no fue dada de alta desde la aplicacion.";

	/**
	 * Mensaje que es mostrado cuando no se encuentran contactos a los cuales enviar
	 * el email
	 */
	public static final String THERE_IS_NO_CONTACTS_TO_SEND_MAIL = "No existen contactos a los cuales enviar email";

	/**
	 * Mensaje que es mostrado cuando no se puede obtener el token en el servicio de
	 * email
	 */
	public static final String CANNOT_GET_MAIL_TOKEN = "No fue posible obtener el token del email";

	/**
	 * Mensaje que aparece cuando el email no pudo ser enviado
	 */
	public static final String MAIL_CANNOT_BE_SEND = "El e-mail no pudo ser enviado";

	/**
	 * Mensaje que es mostrado cuando se genera un error durante la consulta de
	 * movimientos en transito
	 */
	public static final String ERROR_ON_GET_MOVIMIENTOS_TRANSITO = "Se genero un error al consultar los movimientos en transito";

	/**
	 * Mensaje que es mostrado cuando se genera un error durante la actualizacion de
	 * movimientos en transito
	 */
	public static final String ERROR_ON_UPDATE_MOVIMIENTOS_TRANSITO = "Se genero un error al consultar los movimientos en transito";

	/**
	 * Mensaje que es mostrado cuando se genera un error durante la insercion de
	 * movimientos pago
	 */
	public static final String ERROR_ON_INSERT_MOVIMIENTOS_PAGO = "Se genero un error al insertar los movimientos pago";

	/**
	 * Mensaje que es mostrado cuando se genera un error durante la construccion del
	 * email de movimientos en transito
	 */
	public static final String ERROR_ON_BUILD_EMAIL = "Se genero un error durante la creacion del email";

	/**
	 * Mensaje que es mostrado cuando se genera un error durante el envio de emial
	 * de movimientos en transito
	 */
	public static final String ERROR_ON_SENDING_EMAIL = "Se genero un error durante el envio del email";

	/**
	 * Mensaje que es mostrado cuando se inicia procesos de conciliación.
	 */
	public static final String CONCILIATION_PROCESS_BEGINS = "Se inicia proceso de conciliacion.";
	
	
	/**
	 * Mensaje que es mostrado cuando se envia de forma exitosa la conciliación.
	 */
	public static final String CONCILIATION_SENT_SUCCESSFULLY = "Conciliacion Enviada de forma Exitosa.";
	
	/**
	 * Mensaje que es mostrado cuando se actualiza correctamente el sub estatus de la conciliación.
	 */
	public static final String SUB_STATUS_RECONCILIATION_UPDATED_CORRECTLY = "Sub Estatus Conciliacion actualizado correctamente.";
	
	
	/**
	 * Mensaje que es mostrado cuando se actualiza correctamente el proceso.
	 */
	public static final String STATUS_PROCESS_CORRECTLY_UPDATED = "Estatus Proceso actualizado correctamente.";
	
	/**
	 * Mensaje que es mostrado cuando solicitud de pago es exitosa.
	 */
	public static final String SUCCESSFUL_PAYMENT_APPLICATION = "Solicitud Pago Exitosa.";
	
	/**
	 * Mensaje que es mostrado cuando al marcar la solicitud es exitosa.
	 */
	public static final String MARK_AS_SUCCESSFUL_RETURN = "Marcar como Devolucion Exitosa.";
	
	/**
	 * Mensaje que es mostrado cuando la consulta de la devolución es exitosa.
	 */
	public static final String SUCCESSFUL_RETURNS_CONSULTATION = "Consulta Devoluciones Exitosa.";
	
	/**
	 * Mensaje que es mostrado cuando la solicitud de la devolución es exitosa.
	 */
	public static final String REQUEST_SUCCESSFUL_RETURN = "Solicitud Devolucion Exitosa.";
	
	/**
	 * Mensaje que es mostrado cuando la solicitud de la devolución es liquidada correctamente.
	 */
	public static final String  SUCCESSFUL_CLEARANCE_REQUEST = "Solicitud Liquidacion Exitosa.";
	
	/**
	 * Mensaje que es mostrado cuando el identificador peoplesoft es actualizado correctamente.
	 */
	public static final String IDENTIFIER_PS_UPDATED_IN_THE_CONCILIATION = "Identificador PS actualizado en la conciliación.";

	/**
	 * Mensaje que es mostrado cuando se desea actualizar a un sub estatus con un orden inferior al actual
	 */
	public static final String WRONG_ORDER_SUB_STATUS = "El subestatus que desea actualizar tiene un orden incorrecto";
	

	// CATALOGOS
	public static final Long ESTATUS_TRANSITO_NO_IDENTIFICADO_MIDAS = 1L;
	public static final Long CATEGORIA_ESTADO_CUENTA_COMISIONES = 2L;


	/**
	 * Valor constante que es enviado como parametro de consulta para discrimnacion por estatus de conciliacion en proceso
	 */
	public static final String CONCILIACION_EN_PROCESO_VALUE = "proceso";
	
	/**
	 * Valor constante que es enviado como parametro de consulta para discrimnacion por estatus de devolucioniquidada
	 */
	public static final String DEVOLUCION_LIQUIDAD_VALUE = "liquidada";
	
	/**
	 * Mensaje que es mostrado cuando se desea actualizar un sub estatus que no tiene asociado nungun estatus
	 */
	public static final String NO_STATUS_FOR_SUCH_SUB_STATUS = "No se encontro un estatus asociado al id de subestatus especificado";
	
	/**
	 * Mensaje que es mostrado cuando se genera un error obteniendo el actual sub estatus de conciliacion
	 */
	public static final String ERROR_GETTING_CURRENT_SUB_STATUS = "Error obteniendo el sub estatus de conciliacion actual";
	
	/**
	 * Subclase de cosntantes que se mapearan de propiedades del archivo de
	 * configuracion de spring
	 * 
	 * @author user
	 *
	 */
	public static final class ConstantProperties {
		private ConstantProperties() {
			super();
		}

		/**
		 * Valor del iva (IMPUESTO AL VALOR AGREGADO)
		 */
		public static final String IVA = "${mimonte.variables.iva}";
	}

	public static final class Validation {

		private Validation() {
			super();
		}

		/**
		 * Mensaje que es mostrado cuando no se encuentra informacion para los
		 * parametros de busqueda ingresados
		 */
		public static final String NO_INFORMATION_FOUND = "No se encontro informacion para los parametros ingresados";

		/**
		 * Mensaje que es mostrado cuando se genera un error de validacion de parametros
		 * de entrada
		 */
		public static final String VALIDATION_PARAM_ERROR = "Se genero un error de validacion";

		/**
		 * Mensaje que es mostrado cuando se intenta ingresar una fecha inicial
		 * posterior a la fecha final
		 */
		public static final String INITIAL_DATE_AFTER_FINAL_DATE = "La fecah inicial es posterior a la fecha final";

		/**
		 * Mensaje que es mostrado cuando no se encuentra una abreviatura y/o
		 * coincidencia con un id de catalogo
		 */
		public static final String NO_CATALOG_INFORMATION = "No se encontro informacion de uno o mas catalogos";

		/**
		 * Mensaje que es mostrado cuando no se encuentra informacion para los
		 * parametros de busqueda ingresados
		 */
		public static final String NO_INFORMATION_FOUND_FOR_IDS = "Alguno de los ids ingresados no esta en el estado Solicitada";

	}

}
