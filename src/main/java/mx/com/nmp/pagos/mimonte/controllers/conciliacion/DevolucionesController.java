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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionesIdsMovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusDevolucionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FolioRequestDTO;
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
	@PostMapping(value = "/devoluciones/consulta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la administración de devoluciones a nivel entidad - Consulta de devoluciones para todas las entidades bancarias.", tags = {
			"Devoluciones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta Devoluciones Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response consultar(@RequestBody DevolucionRequestDTO devolucionDTO) {
		List<DevolucionEntidadDTO> devolucionEntidadDTOList = buildDummy1();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Consulta Devoluciones Exitosa.",
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
	@PutMapping(value = "/devoluciones/actualizacion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Realiza la administración de devoluciones a nivel entidad - Actualización de la fecha y liquidación para las devoluciones.", tags = {
			"Devoluciones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Actualización Devoluciones Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response actualizar(@RequestBody List<DevolucionUpdtDTO> devolucionUpdtDTOList,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		List<DevolucionEntidadDTO> devolucionEntidadDTOList = buildDummy1();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Actualización Devoluciones Exitosa.",
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
	@PostMapping(value = "/devoluciones/solicitar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Realiza la administración de devoluciones a nivel entidad - Enviar solicitud de devoluciones.", tags = {
			"Devoluciones" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Solicitud Devoluciones Exitosa."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response solicitar(@RequestBody DevolucionesIdsMovimientosDTO devolucionesIdsMovimientosDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		List<DevolucionEntidadDTO> devolucionEntidadDTOList = buildDummy1();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Solicitud Devoluciones Exitosa.",
				devolucionEntidadDTOList);
	}

	/**
	 * Construye un objeto dummy
	 * 
	 * @return
	 */
	public static List<DevolucionEntidadDTO> buildDummy1() {
		List<DevolucionEntidadDTO> devolucionEntidadDTOListList = new ArrayList<>();
		// Devolcion 1
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
		
		// Devolcion 2
		DevolucionEntidadDTO devolucionEntidadDTO2 = new DevolucionEntidadDTO();
		devolucionEntidadDTO2.setId(2L);
		devolucionEntidadDTO2.setEntidad(new BaseEntidadDTO(2L, "Banco 2", "Banco 2"));
		devolucionEntidadDTO2.setFecha(new Date());
		devolucionEntidadDTO2.setEstatus(new EstatusDevolucionDTO(1, "Pendiente", true));
		devolucionEntidadDTO2.setSucursal(30);
		devolucionEntidadDTO2.setMonto(250.00);
		devolucionEntidadDTO2.setEsquemaTarjeta("Master Card");
		devolucionEntidadDTO2.setIdentificadorCuenta("6598xxxxxxxx100");
		devolucionEntidadDTO2.setTitular("Baltazar Gracian");
		devolucionEntidadDTO2.setCodigoAutorizacion("789654");
		devolucionEntidadDTO2.setFechaLiquidacion(new Date());
		
		// Devolcion 3
		DevolucionEntidadDTO devolucionEntidadDTO3 = new DevolucionEntidadDTO();
		devolucionEntidadDTO3.setId(3L);
		devolucionEntidadDTO3.setEntidad(new BaseEntidadDTO(3L, "Banco 3", "Banco 3"));
		devolucionEntidadDTO3.setFecha(new Date());
		devolucionEntidadDTO3.setEstatus(new EstatusDevolucionDTO(3, "Liquidada", true));
		devolucionEntidadDTO3.setSucursal(10);
		devolucionEntidadDTO3.setMonto(170.00);
		devolucionEntidadDTO3.setEsquemaTarjeta("Visa");
		devolucionEntidadDTO3.setIdentificadorCuenta("7789xxxxxxxx123");
		devolucionEntidadDTO3.setTitular("Juana de Arco");
		devolucionEntidadDTO3.setCodigoAutorizacion("1020301");
		devolucionEntidadDTO3.setFechaLiquidacion(new Date());
		
		// Devolcion 4
		DevolucionEntidadDTO devolucionEntidadDTO4 = new DevolucionEntidadDTO();
		devolucionEntidadDTO4.setId(4L);
		devolucionEntidadDTO4.setEntidad(new BaseEntidadDTO(4L, "Banco 4", "Banco 4"));
		devolucionEntidadDTO4.setFecha(new Date());
		devolucionEntidadDTO4.setEstatus(new EstatusDevolucionDTO(2, "Solicitada", true));
		devolucionEntidadDTO4.setSucursal(3);
		devolucionEntidadDTO4.setMonto(320.00);
		devolucionEntidadDTO4.setEsquemaTarjeta("Master Card");
		devolucionEntidadDTO4.setIdentificadorCuenta("9977xxxxxxxx002");
		devolucionEntidadDTO4.setTitular("Nicolas Maquiavelo");
		devolucionEntidadDTO4.setCodigoAutorizacion("1203457");
		devolucionEntidadDTO4.setFechaLiquidacion(new Date());
		
		// Devolcion 5
		DevolucionEntidadDTO devolucionEntidadDTO5 = new DevolucionEntidadDTO();
		devolucionEntidadDTO5.setId(5L);
		devolucionEntidadDTO5.setEntidad(new BaseEntidadDTO(5L, "Banco 5", "Banco 5"));
		devolucionEntidadDTO5.setFecha(new Date());
		devolucionEntidadDTO5.setEstatus(new EstatusDevolucionDTO(1, "Pendiente", true));
		devolucionEntidadDTO5.setSucursal(30);
		devolucionEntidadDTO5.setMonto(510.00);
		devolucionEntidadDTO5.setEsquemaTarjeta("Visa");
		devolucionEntidadDTO5.setIdentificadorCuenta("1047xxxxxxxx111");
		devolucionEntidadDTO5.setTitular("Napoleon Bonaparte");
		devolucionEntidadDTO5.setCodigoAutorizacion("1123301");
		devolucionEntidadDTO5.setFechaLiquidacion(new Date());
		
		// Devolcion 6
		DevolucionEntidadDTO devolucionEntidadDTO6 = new DevolucionEntidadDTO();
		devolucionEntidadDTO6.setId(6L);
		devolucionEntidadDTO6.setEntidad(new BaseEntidadDTO(6L, "Banco 6", "Banco 6"));
		devolucionEntidadDTO6.setFecha(new Date());
		devolucionEntidadDTO6.setEstatus(new EstatusDevolucionDTO(3, "Liquidada", true));
		devolucionEntidadDTO6.setSucursal(10);
		devolucionEntidadDTO6.setMonto(450.00);
		devolucionEntidadDTO6.setEsquemaTarjeta("MasterCard");
		devolucionEntidadDTO6.setIdentificadorCuenta("8899xxxxxxxx101");
		devolucionEntidadDTO6.setTitular("Isabel I");
		devolucionEntidadDTO6.setCodigoAutorizacion("1457996");
		devolucionEntidadDTO6.setFechaLiquidacion(new Date());
		
		devolucionEntidadDTOListList.add(devolucionEntidadDTO);
		devolucionEntidadDTOListList.add(devolucionEntidadDTO2);
		devolucionEntidadDTOListList.add(devolucionEntidadDTO3);
		devolucionEntidadDTOListList.add(devolucionEntidadDTO4);
		devolucionEntidadDTOListList.add(devolucionEntidadDTO5);
		devolucionEntidadDTOListList.add(devolucionEntidadDTO6);
		
		return devolucionEntidadDTOListList;
	}

}
