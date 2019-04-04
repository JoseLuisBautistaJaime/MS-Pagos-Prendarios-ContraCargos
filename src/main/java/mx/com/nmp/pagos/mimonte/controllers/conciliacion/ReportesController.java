/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosLibresDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteRequestDTO;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * @name ReportesController
 * @description Clase de tipo Controlador spring que expone los servicios
 *              relacionados con Reportes
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 13:20 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite obtener reportes diversos.", description = "REST API para Reportes", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Reportes" })
public class ReportesController {

	/**
	 * Fabrica de beans
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Imprime logs de la aplicacion
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(ReportesController.class);

	/**
	 * Permite obtener el reporte de pagos en linea.
	 * 
	 * @param reporteRequestDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/conciliacion/reportes/pagosenlinea", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite obtener el reporte de pagos en linea.", tags = { "Reportes" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody ReporteRequestDTO reporteRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		ReportePagosLibresDTO reportePagosLibresDTO = buildDummy1();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				reportePagosLibresDTO);
	}

	/**
	 * Construye un objeto dummy
	 * 
	 * @return
	 */
	public static ReportePagosLibresDTO buildDummy1() {
		ReportePagosLibresDTO reportePagosLibresDTO = new ReportePagosLibresDTO();
		reportePagosLibresDTO.setCanal("Portal NMP");
		reportePagosLibresDTO.setFecha(new Date());
		reportePagosLibresDTO.setMonto(17000.00);
		reportePagosLibresDTO.setOperacion("Cobro Desempenio en Linea");
		reportePagosLibresDTO.setPartida("12345678");
		reportePagosLibresDTO.setSucursal(1);
		reportePagosLibresDTO.setTipoProducto("Clasico");
		return reportePagosLibresDTO;
	}
}
