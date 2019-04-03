/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.dto.BaseEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionesIdsMovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusDevolucionDTO;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * @name DevolucionesController
 * @description Clase de tipo Controlador spring que expone los servicios
 *              relacionados con Devoluciones
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 11:21 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operaciones relacionadas con Devoluciones.", description = "REST API para Devoluciones", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Devoluciones" })
public class DevolucionesController {

	/**
	 * Fabrica de beans
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Imprime logs de la aplicacion
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(DevolucionesController.class);

	/**
	 * Service para Devoluciones
	 */
//	@Autowired
//	@Qualifier("devolucionesService")
//	private DevolucionesService devolucionesService;

	/**
	 * Permite agregar y modificar comisiones al listado de comisiones del estado de
	 * cuenta
	 * 
	 * @param comisionSaveDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/devoluciones/consulta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la administración de devoluciones a nivel entidad - Consulta de devoluciones para todas las entidades bancarias.", tags = {
			"Devoluciones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultar(@RequestBody DevolucionDTO devolucionDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		List<DevolucionEntidadDTO> devolucionEntidadDTOList = buildDummy1();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				devolucionEntidadDTOList);
	}

	/**
	 * Realiza la administración de devoluciones a nivel entidad - Actualización de
	 * la fecha y liquidación para las devoluciones.
	 * 
	 * @param devolucionUpdtDTOList
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/conciliacion/devoluciones/actualizacion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Realiza la administración de devoluciones a nivel entidad - Actualización de la fecha y liquidación para las devoluciones.", tags = {
			"Devoluciones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Actualizacion exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response actualizar(@RequestBody List<DevolucionUpdtDTO> devolucionUpdtDTOList,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		List<DevolucionEntidadDTO> devolucionEntidadDTOList = buildDummy1();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				devolucionEntidadDTOList);
	}

	/**
	 * Realiza la administración de devoluciones a nivel entidad - Enviar solicitud
	 * de devoluciones.
	 * 
	 * @param devolucionesIdsMovimientosDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/devoluciones/solicitar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la administración de devoluciones a nivel entidad - Enviar solicitud de devoluciones.", tags = {
			"Devoluciones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Actualizacion exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response solicitar(@RequestBody DevolucionesIdsMovimientosDTO devolucionesIdsMovimientosDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		List<DevolucionEntidadDTO> devolucionEntidadDTOList = buildDummy1();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				devolucionEntidadDTOList);
	}

	/**
	 * Construye un objeto dummy
	 * 
	 * @return
	 */
	public static List<DevolucionEntidadDTO> buildDummy1() {
		List<DevolucionEntidadDTO> devolucionEntidadDTOListList = new ArrayList<>();
		DevolucionEntidadDTO devolucionEntidadDTO = new DevolucionEntidadDTO();
		devolucionEntidadDTO.setId(1L);
		devolucionEntidadDTO.setEntidad(new BaseEntidadDTO(1L, "Banco 1", "Banco 1"));
		devolucionEntidadDTO.setFecha(new Date());
		devolucionEntidadDTO.setEstatus(new EstatusDevolucionDTO(2, "Solicitada", true));
		devolucionEntidadDTO.setSucursal(3);
		devolucionEntidadDTO.setMonto(150.00);
		devolucionEntidadDTO.setEsquemaTarjeta("Visa");
		devolucionEntidadDTO.setIdentificadorCuenta("4152xxxxxxxx953");
		devolucionEntidadDTO.setTitular("Juana Garcia Garcia");
		devolucionEntidadDTO.setCodigoAutorizacion("859363");
		devolucionEntidadDTO.setFechaLiquidacion(new Date());
		devolucionEntidadDTOListList.add(devolucionEntidadDTO);
		return devolucionEntidadDTOListList;
	}

}
