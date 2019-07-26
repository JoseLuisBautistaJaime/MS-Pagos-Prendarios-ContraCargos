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

	/**
	 * Clase para especificar los valores constantes de tipos de movimientos en
	 * consulta de movimientos
	 * 
	 * @author user
	 *
	 */
	public static class TipoMovimiento {

		private TipoMovimiento() {
			super();
		}

		/**
		 * Tipo Movimiento de deposito
		 */
		public static final int TIPO_DEPOSITO = 1;

		/**
		 * Tipo Movimiento de retiro
		 */
		public static final int TIPO_RETIRO = 2;

	}

	/**
	 * Subclase de constantes utiles para mostrar mensajes y tamanios de campos en
	 * validaciones
	 * 
	 * @author user
	 *
	 */
	public static final class Validation {

		private Validation() {
			super();
		}

		/**
		 * Mensaje que es mostrado cuando se intenta ingresar una fecha inicial
		 * posterior a la fecha final
		 */
		public static final String INITIAL_DATE_AFTER_FINAL_DATE = "La fecha inicial es posterior a la fecha final.";

		/**
		 * Mensaje que es mostrado cuando no se encuentra informacion para los
		 * parametros de busqueda ingresados
		 */
		public static final String NO_INFORMATION_FOUND = "No se encontro informacion para los parametros ingresados.";

		/**
		 * Mensaje que es mostrado cuando no se encuentra una abreviatura y/o
		 * coincidencia con un id de catalogo
		 */
		public static final String NO_CATALOG_INFORMATION = "No se encontro informacion de uno o mas catalogos.";

		/**
		 * Mensaje que es mostrado cuando no se encuentra informacion para los
		 * parametros de busqueda ingresados
		 */
		public static final String NO_INFORMATION_FOUND_FOR_IDS = "Alguno de los ids ingresados no se encuentra en el estado Solicitada.";

		/**
		 * Mensaje que es mostrado cuando se genera un error de validacion de parametros
		 * de entrada
		 */
		public static final String VALIDATION_PARAM_ERROR = "Se genero un error de validacion.";

	}

	/**
	 * User
	 */
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
	public static final String ENTITY_VALIDATION_SIZE_MESSAGE_10 = "Debe ingresar maximo 10 caracteres.";

	/**
	 * Mensaje que es mostrado cuando s intenta ingresar una cadena de caracteres
	 * mayor a 45
	 */
	public static final String ENTITY_VALIDATION_SIZE_MESSAGE_45 = "Debe ingresar maximo 45 caracteres.";

	/**
	 * Mensaje que es mostrado si se genera un error en la creacion del objeto
	 * reporte en BD
	 */
	public static final String REPORT_GENERATION_ERROR_MESSAGE = "Se ha generado un error durante la generacion del reporte.";

	/**
	 * Mensaje que es mostrado cuando se realiza una consulta de manera exitosa
	 */
	public static final String MSG_SUCCESSFUL_MOVIMIENTOS_QUERY = "Consulta de movimientos exitosa.";

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
	public static final String CONCILIACION_ID_NOT_FOUND = "No se encontro el id de conciliacion ingresado.";

	/**
	 * Mensaje mostrado cuando se ocurre un error al cambiar el estatus.
	 */
	public static final String AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS = "Ocurrio un error al actualizar el estatus de la devolución.";

	/**
	 * Se regresa cuando no se encuentra informacion en una busqueda relacionada con
	 * los procesos de conciliacion en general
	 */
	public static final String INFORMATION_NOT_FOUND = "No se encontro informacion relacionada con los parametros de busqueda.";

	/**
	 * Mensaje que es mostrado cuando no es posible obtener una conciliacion de
	 * acuerdo a los parametros recibidos
	 */
	public static final String CONCILIACION_NOT_FOUND_FOR_SUCH_PARAMS = "No se encontro una conciliacion para los parametros especificados.";

	/**
	 * Mensaje que es mostrado cuando no es posible obtener una comision de acuerdo
	 * a los parametros recibidos
	 */
	public static final String COMISION_NOT_FOUND_FOR_SUCH_PARAMS = "No se encontro una conciliacion para los parametros especificados.";

	/**
	 * Mensaje que es mostrado cuando se intenta eliminar una comision y esta no
	 * puede ser eliminada por regla de negocio
	 */
	public static final String COMISION_CANT_BE_DELETED = "La comision no puede ser eliminada ya que fue no fue dada de alta desde la aplicacion.";

	/**
	 * Mensaje que es mostrado cuando no se encuentran contactos a los cuales enviar
	 * el email
	 */
	public static final String THERE_IS_NO_CONTACTS_TO_SEND_MAIL = "No existen contactos a los cuales enviar e-mail.";

	/**
	 * Mensaje que es mostrado cuando no se puede obtener el token en el servicio de
	 * email
	 */
	public static final String CANNOT_GET_TOKEN = "No fue posible obtener el token de autorizacion.";

	/**
	 * Mensaje que aparece cuando el email no pudo ser enviado
	 */
	public static final String MAIL_CANNOT_BE_SEND = "El e-mail no pudo ser enviado.";

	/**
	 * Mensaje que aparece cuando el email no pudo ser enviado
	 */
	public static final String ESTADO_CUENTA_CANNOT_BE_CONSULT = "Estado de cuenta no puede ser consultado.";
	/**
	 * Mensaje que es mostrado cuando se genera un error durante la consulta de
	 * movimientos en transito
	 */
	public static final String ERROR_ON_GET_MOVIMIENTOS_TRANSITO = "Se genero un error al consultar los movimientos en transito.";

	/**
	 * Mensaje que es mostrado cuando se genera un error durante la actualizacion de
	 * movimientos en transito
	 */
	public static final String ERROR_ON_UPDATE_MOVIMIENTOS_TRANSITO = "Se genero un error al consultar los movimientos en transito.";

	/**
	 * Mensaje que es mostrado cuando se genera un error durante la insercion de
	 * movimientos pago
	 */
	public static final String ERROR_ON_INSERT_MOVIMIENTOS_PAGO = "Se genero un error al insertar los movimientos pago.";

	/**
	 * Mensaje que es mostrado cuando se genera un error durante la construccion del
	 * email de movimientos en transito
	 */
	public static final String ERROR_ON_BUILD_EMAIL = "Se genero un error durante la creacion del e-mail.";

	/**
	 * Mensaje que es mostrado cuando se genera un error durante el envio de emial
	 * de movimientos en transito
	 */
	public static final String ERROR_ON_SENDING_EMAIL = "Se genero un error durante el envio del e-mail.";

	/**
	 * Mensaje que es mostrado cuando se inicia procesos de conciliación.
	 */
	public static final String CONCILIATION_PROCESS_BEGINS = "Se inicia proceso de conciliacion.";

	/**
	 * Mensaje que es mostrado cuando se envia de forma exitosa la conciliación.
	 */
	public static final String CONCILIATION_SENT_SUCCESSFULLY = "Conciliacion enviada de forma exitosa.";

	/**
	 * Mensaje que es mostrado cuando se actualiza correctamente el sub estatus de
	 * la conciliación.
	 */
	public static final String SUB_STATUS_RECONCILIATION_UPDATED_CORRECTLY = "Sub estatus conciliacion actualizado correctamente.";

	/**
	 * Mensaje que es mostrado cuando se actualiza correctamente el proceso.
	 */
	public static final String STATUS_PROCESS_CORRECTLY_UPDATED = "Estatus proceso actualizado correctamente.";

	/**
	 * Mensaje que es mostrado cuando solicitud de pago es exitosa.
	 */
	public static final String SUCCESSFUL_PAYMENT_APPLICATION = "Solicitud pago exitosa.";

	/**
	 * Mensaje que es mostrado cuando al marcar la solicitud es exitosa.
	 */
	public static final String MARK_AS_SUCCESSFUL_RETURN = "Marcar como devolucion exitosa.";

	/**
	 * Mensaje que es mostrado cuando la consulta de la devolución es exitosa.
	 */
	public static final String SUCCESSFUL_RETURNS_CONSULTATION = "Consulta devoluciones exitosa.";

	/**
	 * Mensaje que es mostrado cuando la solicitud de la devolución es exitosa.
	 */
	public static final String REQUEST_SUCCESSFUL_RETURN = "Solicitud devolucion exitosa.";

	/**
	 * Mensaje que es mostrado cuando la solicitud de la devolución es liquidada
	 * correctamente.
	 */
	public static final String SUCCESSFUL_CLEARANCE_REQUEST = "Solicitud liquidacion exitosa.";

	/**
	 * Mensaje que es mostrado cuando el identificador peoplesoft es actualizado
	 * correctamente.
	 */
	public static final String IDENTIFIER_PS_UPDATED_IN_THE_CONCILIATION = "Identificador PS actualizado en la conciliación.";

	/**
	 * Mensaje que es mostrado cuando se desea actualizar a un sub estatus con un
	 * orden inferior al actual
	 */
	public static final String WRONG_ORDER_SUB_STATUS = "El subestatus que desea actualizar tiene un orden incorrecto.";

	// CATALOGOS

	public static final Long CATEGORIA_ESTADO_CUENTA_COMISIONES = 2L;
	public static final Long CATEGORIA_ESTADO_CUENTA_IVA = 4L;
	public static final Long CATEGORIA_ESTADO_CUENTA_DEVOLUCIONES = 6L;

	// Estatus conciliacion
	public static final Integer ESTATUS_CONCILIACION_EN_PROCESO = 1;
	public static final Integer ESTATUS_CONCILIACION_FINALIZADA = 2;

	// Subestatus conciliacion
	public static final Long SUBESTATUS_CONCILIACION_CREADA = 1L;
	public static final Long SUBESTATUS_CONCILIACION_ENVIADA = 14L;
	public static final Long SUBESTATUS_CONCILIACION_ERROR = 15L;
	public static final Long SUBESTATUS_CONCILIACION_FINALIZADA = 16L;

	// Estatus devoluciones
	public static final Integer ESTATUS_DEVOLUCION_PENDIENTE = 1;
	public static final Integer ESTATUS_DEVOLUCION_SOLICITADA = 2;
	public static final Integer ESTATUS_DEVOLUCION_LIQUIDADA = 3;

	// Estatus transito
	public static final Integer ESTATUS_TRANSITO_NO_IDENTIFICADO_MIDAS = 1;
	public static final Integer ESTATUS_TRANSITO_SOLICITADA = 2;
	public static final Integer ESTATUS_TRANSITO_MARCADO_DEVOLUCION = 4;

	// Tipo de contacts
	public static final Long TIPO_CONTACTO_MIDAS = 1l;
	public static final Long TIPO_CONTACTO_ENTIDAD = 2l;

	public static final String ESTATUS_TRANSACCION_OPENPAY_SUCCESS = "success";
	public static final String TIPO_TRANSACCION_OPENPAY_CARGO = "charge";

	/**
	 * Mensaje que es mostrado cuando se desea actualizar un sub estatus que no
	 * tiene asociado nungun estatus
	 */
	public static final String NO_STATUS_FOR_SUCH_SUB_STATUS = "No se encontro un estatus asociado al id de subestatus especificado.";

	/**
	 * Mensaje que es mostrado cuando se genera un error obteniendo el actual sub
	 * estatus de conciliacion
	 */
	public static final String ERROR_GETTING_CURRENT_SUB_STATUS = "Error obteniendo el sub estatus de conciliacion actual.";

	/**
	 * Mensaje que es mostrado cuando se intenta ingresar una fecha inicial
	 * posterior a la fecha final
	 */
	public static final String FECHA_DESDE_IS_AFTER_FECHA_HASTA = "La fecha inicial es posterioro a la fecha final.";

	/**
	 * Mensaje que es mostrado cuando no se encuentra un id de comision especificado
	 */
	public static final String COMISION_ID_NOT_FOUND = "El id de comision especificado no fue encontrado.";

	/**
	 * Mensaje que es mostrado cuando se intenta asociar una cuenta con una entidad
	 * que no estan relacionadas en base de datos
	 */
	public static final String THERE_IS_NO_SUCH_CUENTA_ENTIDAD_RELATIONSHIP = "No existe relacion entre la cuenta y la entidad especificada.";

	/**
	 * Mensaje que es mostrado cuando los datos de tipos y/o subtipos de base de
	 * datos son incongruentes con los del ENUM
	 */
	public static final String ENUM_TYPE_OR_SUB_TYPE_INCONCISTENCY = "Ocurrio un error con el tipo y/o sub tipo de las actividades";

	/**
	 * Mensaje que es mostrado cuando hay inconsistencia en una o mas fechas
	 */
	public static final String WRONG_OR_INCONSISTENT_FECHAS = "Hay inconsistencia en una o mas fechas";

	/**
	 * Longitud maxima permitida para la actualizacion de la descripcion del
	 * subestatus de conciliacion
	 */
	public static final Integer SUB_ESTATUS_DESCRIPCION_MAX_PERM_LENGHT = 250;

	/**
	 * Mensaje que es mostrado cuando el estatus de la conciliacion se actualiza
	 * exitosamente
	 */
	public static final String SUB_ESTATUS_UPDATED_OK = "Actualizacion de sub estatus correcta.";

	/**
	 * Mensaje que es mostrado cuando se genera un error durante la validacion del
	 * sub estatus de conciliacion
	 */
	public static final String ERROR_WHILE_VALIDATING_SUB_ESTAUS = "Se genero un error durante la validacion del sub-estatus";

	/**
	 * Mensaje que es mostrado cuando no se encuentra una folio de conciliacion
	 * asociado a uno o mas layouts
	 */
	public static final String THERE_IS_NO_CONCILIACION_LAYOUT_RELATIONSHIP = "La conciliacion no tiene asociado ningun layout";

	/**
	 * Mensaje que se muestra cuando no se encuentra un id de layout especificado
	 */
	public static final String LAYOUT_ID_DOESNT_EXIST = "El id de layout especificado no existe";

	/**
	 * Mensaje que es mostrado cuando se genera un error durante la validacion del
	 * id de conciliacion
	 */
	public static final String ERROR_WHILE_ID_CONCILIACION_VALIDATION = "Se genero un error durante la validacion del folio de la conciliacion";

	/**
	 * Mensaje que es mostrado cuando no hay relacion entre el folio y los movimientos ingresados o no existe uno o mas movimientos ingresados
	 */
	public static final String NO_RELATION_BETWEEN_CONC_AND_MOVS_OR_DONESNT_EXISTS_SUCH_MOVS = "No hay relacion entre el folio y los movimientos ingresados o no existe uno o mas movimientos ingresados";
	
	/**
	 * Mensaje que es mostrado cuando se ingresa una sola fecha y esta es incorecta
	 */
	public static final String FECHA_IS_WRONG = "La fecha ingresada es incorrecta";

	/**
	 * Mensaje que es mostrado cuando se genera un error consultando el id del
	 * estatus de movimientos transito marcado como dev
	 */
	public static final String GETTING_DEV_ESTATUS_HAS_GONE_WRONG = "Se registro un error durante la comparacion dele status de la validacion";

	/**
	 * Mensaje que es mostrado cuando se intenta actualizar un movimiento transito a
	 * un estatus marcado como devoluaicon cuando ya ha sido marcado antes
	 */
	public static final String NOT_ALLOWED_STATUS_IDS = "Uno o mas ids tiene un estatus incorrecto y no se puede actualizar";
}
