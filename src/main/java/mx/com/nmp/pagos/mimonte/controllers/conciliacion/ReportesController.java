/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.math.BigDecimal;
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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportePagosLibresOuterDTO;
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
	public Response save(@RequestBody ReporteRequestDTO reporteRequestDTO) {
		ReportePagosLibresOuterDTO reportePagosLibresOuterDTO = buildDummy1();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				reportePagosLibresOuterDTO);
	}

	/**
	 * Construye un objeto dummy
	 * 
	 * @return
	 */
	public static ReportePagosLibresOuterDTO buildDummy1() {
		ReportePagosLibresOuterDTO reportePagosLibresOuterDTO = new ReportePagosLibresOuterDTO();
		List<ReportePagosLibresDTO> reportePagosLibresDTOList = new ArrayList<>();

		// Reporte 1
		ReportePagosLibresDTO reportePagosLibresDTO = new ReportePagosLibresDTO();
		reportePagosLibresDTO.setCanal("Portal NMP");
		reportePagosLibresDTO.setFecha(new Date());
		reportePagosLibresDTO.setMonto(17000.00);
		reportePagosLibresDTO.setOperacion("Cobro Refrendo");
		reportePagosLibresDTO.setPartida(12345678L);
		reportePagosLibresDTO.setSucursal(1);
		reportePagosLibresDTO.setTipoProducto("CLASICO");

		// Reporte 2
		ReportePagosLibresDTO reportePagosLibresDTO2 = new ReportePagosLibresDTO();
		reportePagosLibresDTO2.setCanal("Otro");
		reportePagosLibresDTO2.setFecha(new Date());
		reportePagosLibresDTO2.setMonto(18000.00);
		reportePagosLibresDTO2.setOperacion("Abono -PagosLibres");
		reportePagosLibresDTO2.setPartida(100356L);
		reportePagosLibresDTO2.setSucursal(10);
		reportePagosLibresDTO2.setTipoProducto("PAGOS LIBRES");
		
		// Reporte 3
		ReportePagosLibresDTO reportePagosLibresDTO3 = new ReportePagosLibresDTO();
		reportePagosLibresDTO3.setCanal("Portal NMP");
		reportePagosLibresDTO3.setFecha(new Date());
		reportePagosLibresDTO3.setMonto(19000.00);
		reportePagosLibresDTO3.setOperacion("Abono -PagosLibres");
		reportePagosLibresDTO3.setPartida(1110088L);
		reportePagosLibresDTO3.setSucursal(10);
		reportePagosLibresDTO3.setTipoProducto("CLASICO");
		
		// Reporte 4
		ReportePagosLibresDTO reportePagosLibresDTO4 = new ReportePagosLibresDTO();
		reportePagosLibresDTO4.setCanal("Otro");
		reportePagosLibresDTO4.setFecha(new Date());
		reportePagosLibresDTO4.setMonto(20000.00);
		reportePagosLibresDTO4.setOperacion("Cobro Refrendo");
		reportePagosLibresDTO4.setPartida(12345989L);
		reportePagosLibresDTO4.setSucursal(30);
		reportePagosLibresDTO4.setTipoProducto("CLASICO");
		
		// Reporte 5
		ReportePagosLibresDTO reportePagosLibresDTO5 = new ReportePagosLibresDTO();
		reportePagosLibresDTO5.setCanal("Mobile");
		reportePagosLibresDTO5.setFecha(new Date());
		reportePagosLibresDTO5.setMonto(21000.00);
		reportePagosLibresDTO5.setOperacion("Cobro Desempeño en Linea");
		reportePagosLibresDTO5.setPartida(147775678L);
		reportePagosLibresDTO5.setSucursal(30);
		reportePagosLibresDTO5.setTipoProducto("CLASICO");
		
		// Reporte 6
		ReportePagosLibresDTO reportePagosLibresDTO6 = new ReportePagosLibresDTO();
		reportePagosLibresDTO6.setCanal("Portal NMP");
		reportePagosLibresDTO6.setFecha(new Date());
		reportePagosLibresDTO6.setMonto(22000.00);
		reportePagosLibresDTO6.setOperacion("Cobro Refrendo");
		reportePagosLibresDTO6.setPartida(12332111L);
		reportePagosLibresDTO6.setSucursal(3);
		reportePagosLibresDTO6.setTipoProducto("PAGOS LIBRES");
		
		reportePagosLibresDTOList.add(reportePagosLibresDTO);
		reportePagosLibresDTOList.add(reportePagosLibresDTO2);
		reportePagosLibresDTOList.add(reportePagosLibresDTO3);
		reportePagosLibresDTOList.add(reportePagosLibresDTO4);
		reportePagosLibresDTOList.add(reportePagosLibresDTO5);
		reportePagosLibresDTOList.add(reportePagosLibresDTO6);
		
		reportePagosLibresOuterDTO.setMovimientos(reportePagosLibresDTOList);
		reportePagosLibresOuterDTO.setTotalMovimientos(6);
		reportePagosLibresOuterDTO.setMontoTotal(new BigDecimal("117000.00"));
		
		return reportePagosLibresOuterDTO;
		
		
	}
}
