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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * @name MovimientosEstadoCuentaController
 * @description Clase de tipo Controlador spring que expone los servicios
 *              relacionados con Movimientos de estados de cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 09:06 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "", description = "REST API para Movimientos de estados de cuenta", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"MoviminetosEstadoCuenta" })
public class MovimientosEstadoCuentaController {

	/**
	 * Fabrica de beans
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Instancia que imprime logs de los eventos
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(MovimientosEstadoCuentaController.class);

	/**
	 * Service para MovimientosEstadoCuenta
	 */
//	@Autowired
//	@Qualifier("movimientosEstadoCuentaService")
//	private MovimientosEstadoCuentaService movimientosEstadoCuentaService;

	/**
	 * Consulta movimientos estado de cuneta por filtros de objeto
	 * CommonConciliacionRequestDTO
	 * 
	 * @param commonConciliacionRequestDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/movimientos/estadocuenta/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite consultar los movimientos del estado de cuenta.", tags = {
			"MoviminetosEstadoCuenta" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody CommonConciliacionRequestDTO commonConciliacionRequestDTO,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		MovimientosEstadoCuentaDTO movimientosEstadoCuentaDTO = null;
		movimientosEstadoCuentaDTO = buildDummy1();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				movimientosEstadoCuentaDTO);
	}

	/**
	 * Construye una respuesta dummy
	 * @return
	 */
	public static MovimientosEstadoCuentaDTO buildDummy1() {
		MovimientosEstadoCuentaDTO movimientosEstadoCuentaDTO = new MovimientosEstadoCuentaDTO();
		List<MovimientoEstadoCuentaDTO> movimientoEstadoCuentaDTOList = new ArrayList<>();
		movimientosEstadoCuentaDTO.setTotal(100D);
		MovimientoEstadoCuentaDTO movimientoEstadoCuentaDTO1 = new MovimientoEstadoCuentaDTO();
		movimientoEstadoCuentaDTO1.setDepositos(0D);
		movimientoEstadoCuentaDTO1.setDescripcion("Ventas netas tarjeta …");
		movimientoEstadoCuentaDTO1.setFecha(new Date());
		movimientoEstadoCuentaDTO1.setRetiros(12882.62D);
		movimientoEstadoCuentaDTO1.setSaldo(0D);
		MovimientoEstadoCuentaDTO movimientoEstadoCuentaDTO2 = new MovimientoEstadoCuentaDTO();
		movimientoEstadoCuentaDTO2.setDepositos(0D);
		movimientoEstadoCuentaDTO2.setDescripcion("Cargos…");
		movimientoEstadoCuentaDTO2.setFecha(new Date());
		movimientoEstadoCuentaDTO2.setRetiros(245.00D);
		movimientoEstadoCuentaDTO2.setSaldo(0D);
		movimientoEstadoCuentaDTOList.add(movimientoEstadoCuentaDTO1);
		movimientoEstadoCuentaDTOList.add(movimientoEstadoCuentaDTO2);
		movimientosEstadoCuentaDTO.setMovimientos(movimientoEstadoCuentaDTOList);
		return movimientosEstadoCuentaDTO;
	}

}
