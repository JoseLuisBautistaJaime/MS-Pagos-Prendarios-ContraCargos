/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.constans;

/**
 * @name CodigoError
 * @description Mapea los mensajes a enviar en estructura ENUM agrupando tipo de
 *              error y tipo de severidad
 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */

public enum CodigoError {
	NMP_PMIMONTE_0001("Los parametros no son correctos",
			TipoError.CLIENTE, SeveridadError.BAJA),
	NMP_PMIMONTE_0004("El resultado que desea consultar no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_0005("No existen registros para el catalogo especificado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_0009("No existen informacion para mostrar",
			TipoError.CLIENTE, SeveridadError.BAJA),
	NMP_PMIMONTE_0006("Cuerpo de la peticion vacio o ocurrio un error al leerla",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_0007("Los valores de las propiedades del cuerpo de la petición no son correctos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_0008("Los parametros de la peticion no son correctos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_9999("Ocurrio un error inesperado, intente mas tarde",
			TipoError.CLIENTE, SeveridadError.MEDIA),

	// ERRORES RELACIONADOS CON REGLA DE NEGOCIO

	// CATALOGOS
	NMP_PMIMONTE_BUSINESS_001("El id solicitado no fue encontrado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_002("Se han encontrado datos inconsistentes en la base de datos",
			TipoError.SERVIDOR, SeveridadError.ALTA),
	NMP_PMIMONTE_BUSINESS_003("El actual registro tiene relaciones con otro(s) registro(s) y/u objeto(s) de datos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_004("El numero de afiliacion ingresado ya existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_005("El numero de cuenta ingresado ya existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_006("El numero de afiliacion ingresado no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_007("El objeto cuenta es nulo",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_009("No existe una o mas relaciones entre cuenta y afiliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_010("La entidad ingresada ya existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_011("El tipo de contacto no fue encontrado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_012("Uno o mas ids especificados no existen",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_013("El formato del e-mail es incorrecto",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_014("No hay resultados para el id, nombre y e-mail ingresados",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_015("El e-mail ingresado ya existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_016("El actual contacto tiene entidades asociadas",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_059("El codigo de estado de cuenta ya existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_060("La entidad especificada no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_061("La categoria especificada no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_062("El codigo de estado de cuenta no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_074("Los datos de uno o mas contactos es inconsistente",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_075("La cuenta ya ha sido dada de baja",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_076("La entidad ya ha sido dada de baja",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_077("El nombre del catalogo no fue encontrado",
			TipoError.CLIENTE, SeveridadError.MEDIA),

	// CONCILIACION
	NMP_PMIMONTE_BUSINESS_017("No se encontraron conciliaciones para los parametros especificados",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_018("El id de conciliacion no fue encontrado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_019("El id de comision no fue encontrado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_020("La comision no puede ser eliminada ya que no fue dada de alta desde la aplicacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_021("La cuenta especificada no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_022("La entidad especificada no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_023("No existe una o mas relaciones cuenta-entidad especificadas",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_024("El estatus de conciliacion 'En Proceso' no fue encontrado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_025("El estatus de conciliacion 'Creada' no fue encontrado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_026("Ya existe una conciliacion para la entidad, cuenta y fecha especificadas",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_027("No hay un estatus asociado al sub-estatus especificado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_028("No se puede obtener el actual estatus",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_029("El orden de sub-estatus es incorrecto",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_030("La conciliacion tiene un sub-estatus incorrecto",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_031("Error al actualizar los id's de asientos contables",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_032("No hay reportes disponibles para generar la conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_033("Se requieren al menos 2 reportes para generar la conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_034("La conciliacion tiene un estatus incorrecto",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_035("Ocurrio un error al actualizar el estatus de la devolucion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_036("Folio de conciliacion incorrecto",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_037("La fecha inicial es superior a la fecha final",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_038("Error al consultar movimientos en transito",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_039("Error al actualizar movimientos en transito",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_040("Error al insertar movimientos pago",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_041("Error al construir e-mail de solicitud de pagos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_042("Error al enviar e-mail de solicitud de pagos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_043("No hay contactos a los cuales enviar e-mail de solicitud de pagos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_044("Se genero un error durante la generacion del reporte",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_045("La conciliacion con el folio especificado no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_046("Error al gurdar la solicitud de consulta de estado de cuenta",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_047("Error al leer el archivo de estado de cuenta",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_048("Error al parsear el archivo de estado de cuenta",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_049("Error al persistir los archivos de estado de cuenta",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_050("Archivo de estado cuenta invalido",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_051("Archivo de estado cuenta contiene cabecera incorrecta",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_052("Archivo de estado cuenta contiene totales incorrectos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_053("Archivo de estado cuenta contiene totales adicionales incorrectos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_054("No se encuentra el archivo de estado cuenta",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_055("Implementacion no definida",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_056("No hay contactos a los cuales enviar e-mail de solicitud de devoluciones",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_057("Error al enviar e-mail de solicitud de devoluciones",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_058("Error al obtener el contenido para el e-mail de solicitud de devoluciones",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_063("Error al recibir el archivo de estado de cuenta",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_064("No se puede obtener el token para envio de e-mail",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_065("El estado de cuenta no puede ser consultado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_066("El e-mail no puede ser consultado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_067("Linea(s) del estado de cuenta con longitud incorrecta",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_068("Error al consultar movimientos midas",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_069("Error al consultar movimientos proveedor transaccional",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_070("Error al consultar movimientos estado cuenta",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_071("Error al consultar estado cuenta",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_072("Error al obtener los codigos de estado cuenta",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_073("No existen codigos de estado de cuenta para la categoria de comisiones configurada",
			TipoError.CLIENTE, SeveridadError.MEDIA);

	/**
	 * Descripción del error
	 */
	private String descripcion;

	/**
	 * Tipo de error
	 */
	private TipoError tipoError;

	/**
	 * Severidad del error
	 */
	private SeveridadError severidadError;

	/**
	 * Constructor
	 *
	 * @param descripcion    Descripción del error
	 * @param tipoError      Tipo de error
	 * @param severidadError Severidad del error
	 */
	CodigoError(String descripcion, TipoError tipoError, SeveridadError severidadError) {
		this.descripcion = descripcion;
		this.tipoError = tipoError;
		this.severidadError = severidadError;
	}

	/**
	 * Recupera el valor de {@code codigo}
	 *
	 * @return Valor de {@code codigo}
	 */
	public String getCodigo() {
		return name();
	}

	/**
	 * Recupera el valor de {@code descripcion}
	 *
	 * @return Valor de {@code descripcion}
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Recupera el valor de {@code tipoError}
	 *
	 * @return Valor de {@code tipoError}
	 */
	public TipoError getTipoError() {
		return tipoError;
	}

	/**
	 * Recupera el valor de {@code severidadError}
	 *
	 * @return Valor de {@code severidadError}
	 */
	public SeveridadError getSeveridadError() {
		return severidadError;
	}
}
