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
	NMP_PMIMONTE_0009("No existe informacion para mostrar",
			TipoError.CLIENTE, SeveridadError.BAJA),
	NMP_PMIMONTE_0006("Cuerpo de la peticion vacio o ocurrio un error al leerla",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_0007("Los valores de las propiedades del cuerpo de la petición no son correctos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_0008("Los parametros de la peticion no son correctos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_9999("Ocurrio un error inesperado, intente mas tarde",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_0010("Se genero un error relacionado con la validacion de expresiones regulares",
			TipoError.SERVIDOR, SeveridadError.ALTA),
	NMP_PMIMONTE_0011("Se ha generado un error con la base de datos",
			TipoError.SERVIDOR, SeveridadError.ALTA),
	NMP_PMIMONTE_0012("Se genero un error al tratar de persistir la actividad",
			TipoError.SERVIDOR, SeveridadError.ALTA),
	NMP_PMIMONTE_0013("Se genero un error durante el ajuste de fechas",
			TipoError.SERVIDOR, SeveridadError.ALTA),
	NMP_PMIMONTE_0014("Se genero un error durante las validaciones",
			TipoError.SERVIDOR, SeveridadError.ALTA),
	NMP_PMIMONTE_0015("El valor ingresado no es alfanumerico",
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
	NMP_PMIMONTE_BUSINESS_136("Se genero un problema al actualizar los contactos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_137("Uno o mas contactos no existen o no pertenecen a la entidad",
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
	NMP_PMIMONTE_BUSINESS_046("Error al guardar la solicitud de consulta de estado de cuenta",
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
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_078("Hay incongruencia entre fecha inicial y final",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_080("Hay un problema con la fecha inicial",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_081("Hay un problema con la fecha final",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_082("Hay un problema de incongruencia entre fecha inicial y/o final y la fecha actual",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_083("Se genero un problema durante la validacion del subestatus",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_084("La conciliacion no tiene asociado ningun layout",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_085("El id de layout especificado no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_086("Error durante la validacion del folio de conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_087("No hay relacion entre el folio y los movimientos ingresados o no existe uno o mas movimientos ingresados",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_088("La fecha ingresada es incorrecta",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_089("Se genero un error en la otencion del estatus de validacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_090("Uno o mas ids de movimientos tienen un estatus incorrecto",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_091("El estatus de conciliacion especificado no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_092("No existen movimientos-devolucion pendientes de solicitud",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_093("El estatus de uno o mas movimientos es incorrecto",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_094("Error al consultar la conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_095("Id de asiento contable e id de poliza de tesoreria nulos o vacios",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_096("Uno o mas movimientos devolucion no existen",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_097("Ocurrio un error al extraer los movimientos devolucion para compararlos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_098("Uno o mas movimientos devolucion tienen un estatus incorrecto",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_099("La comision no puede ser editada ya que no fue dada de alta desde la aplicacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_100("Uno o mas movimientos en transito no existen",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_101("Error al guardar y/o actualizar comisiones",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_102("Uno o mas movimientos de comision no existen",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_103("Error al consultar movimientos de comision",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_104("Ocurrio un error al solicitar una devolucion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_105("Ocurrio un error al obtener los movimientos devolucion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_106("Error al intentar eliminar la comision",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_107("Error al validar los ids de comision",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_108("Error intentando validar si se pueden eliminar movimientos comision",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_109("No hay relacion entre el folio y uno o mas movimientos comision ingresados",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_110("Error validando la relacion entre folio y movimientos comision",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_111("Uno o mas movimientos transito no pertenecen a la conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_112("Se genero un error validando el sub-estatus de la conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_113("Se genero un error durante la consulta de layouts",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_114("Se genero un error durante la eliminacion de layouts",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_115("No existe relacion entre la conciliacion y el layout especificado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_116("El layout no puede ser eliminado porque no se dio de alta desde la aplicacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_117("El id de linea de layout no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_118("La linea no puede ser eliminada porque el layout al que pertenece no se dio de alta desde la aplicacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_119("El folio de conciliacion y el tipo de layout no coinciden",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_120("Ocurrio un error al perisistiry/o actualizar lineas de los layouts",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_121("Uno o mas ids de lineas no coinciden con el folio de conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_122("Una o mas lineas de layout no existen o estas no fueron dadas de alta desde la aplicacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_123("Una o mas lineas de layout no existen, no fueron dadas de alta desde la aplicacion o bien no pertencen a la conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_124("Ocurrio un error durante la actualizacion de sub-estatus de la conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_125("Ocurrio un error durante la obtencion del estatus de la conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_126("Uno o mas movimientos transito no fueron dados de alta por el usuario",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_127("Uno o mas movimientos de comision no pueden ser editados/eliminados por que no fueron creados por el usuario",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_128("Uno o mas movimientos estan repetidos",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_129("Una o mas lineas no tiene el mismo tipo de Layout",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_130("Error al registrar los movimientos midas",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_131("Error al registrar los movimientos proveedor transaccional",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_132("Error al conciliar la informacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_133("El estatus de devolucion especificado no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_134("La linea no puede ser eliminada porque no se dio de alta desde la aplicacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_135("Aun no se ha realizado el merge de la conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_138("Los montos del layout no cuadran a cero.",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_139("El estatus de ejecucion del proceso de conciliacion que se especifico no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_140("El estatus de ejecucion del proceso de pre-conciliacion que se especifico no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_141("Error durante la validacion del id de la ejecucion del proceso de conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_142("La ejecucion del proceso de conciliacion con el id especificado no existe",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_143("Error al consultar la ejecucion del proceso de conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_144("Ocurrio un error durante la actualizacion de estatus de la ejecucion del proceso de  conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_145("Error al guardar el trazado de estatus de la ejecucion del proceso de conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_146("Error al construir e-mail del proceso de pre-conciliacion automatizado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_147("Error al enviar e-mail del proceso de pre-conciliacion automatizado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_148("El proceso de pre-conciliacion no puede ser ejecutado",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_149("Ya existe un dia inhabil registrado para la fecha especificada",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_PROCESAR_CONCILIACION("Error al procesar la conciliacion",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_CORRESPONSAL_CONCILIACION("No hay relacion entre el corresponsal y una o mas conciliaciones",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_COMISION_PROVEEDOR("No existe la configuracion para el porcentaje de comision cobrada",
			TipoError.CLIENTE, SeveridadError.MEDIA),
	NMP_PMIMONTE_BUSINESS_SERVICIO_PAGOS("El servicio de consulta de pagos no puede ser consultado",
			TipoError.CLIENTE, SeveridadError.MEDIA),


	;

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
