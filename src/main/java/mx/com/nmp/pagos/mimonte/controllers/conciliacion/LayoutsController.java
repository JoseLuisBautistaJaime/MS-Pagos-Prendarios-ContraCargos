/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;
import mx.com.nmp.pagos.mimonte.services.conciliacion.LayoutsService;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * @name LayoutsController
 * @description Clase de tipo Controlador spring que expone los servicios
 *              relacionados con Layouts
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 03/04/2019 10:25 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "", description = "REST API para Layouts", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Layouts" })
public class LayoutsController {

	/**
	 * Fabrica de beans
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Instancia que imprime logs de los eventos
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(LayoutsController.class);

	/**
	 * Service para Layouts
	 */
	@Autowired
	private LayoutsService layoutsService;

	/**
	 * Permite consultar los layouts para Pagos, Comisiones y Devoluciones.
	 * 
	 * @param folio
	 * @param tipoLayout
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/layouts/{folio}/{tipoLayout}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Permite consultar los layouts para Pagos, Comisiones y Devoluciones.", tags = {
			"Layouts" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByFolioAndTipoLayout(@PathVariable(value = "folio", required = true) Long folio,
			@PathVariable(value = "tipoLayout", required = true) String tipoLayout) {
		LayoutDTO layoutDTO = layoutsService.consultarUnLayout(folio, tipoLayout);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				layoutDTO);
	}
	
	/**
	 * Permite consultar todos los layouts de una conciliación.
	 * 
	 * @param folio
	 * @param tipoLayout
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/layouts/{folio}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Permite consultar los layouts para Pagos, Comisiones y Devoluciones.", tags = {
			"Layouts" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByFolio(@PathVariable(value = "folio", required = true) Long folio) {
		List<LayoutDTO> layoutDTOs = layoutsService.consultarLayouts(folio);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				layoutDTOs);
	}

	/**
	 * Permite agregar layouts para Pagos, Comisiones y Devoluciones.
	 * 
	 * @param layoutSaveDTO
	 * @param userRequest
	 * @return  
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/layouts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite agregar layouts para Pagos, Comisiones y Devoluciones.", tags = {
			"Layouts" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Layouts agregados con éxito."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response saveLayout(@RequestBody LayoutDTO layoutDTOe) {
		//LOG.error("------" + layoutDTOe.toString());
		layoutsService.saveLayout(layoutDTOe);
		LayoutDTO layoutDTOs = layoutsService.consultarUnLayout(layoutDTOe.getFolio(), layoutDTOe.getTipoLayout().toString());
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Layouts agregados con éxito.",
				layoutDTOs);
	}

	/**
	 * Permite eliminar layouts para Pagos, Comisiones y Devoluciones
	 * 
	 * @param folio
	 * @param idLayout
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/layouts/{folio}/{idLayout}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Permite eliminar layouts para Pagos, Comisiones y Devoluciones", tags = {
			"Layouts" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Layout eliminado con éxito."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response deleteLayout(@PathVariable(name = "folio", required = true) Long folio,
			@PathVariable(name = "idLayout", required = true) Long idLayout) {
		layoutsService.eliminarUnLayout(folio, idLayout);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Layout eliminado con éxito.", null);
	}

	/**
	 * Construye un objeto dummy
	 * 
	 * @return
	 */
//	  public static LayoutDTO buildDummy1() { LayoutDTO layoutDTO = new
//	  LayoutDTO(); LayoutCabeceraDTO layoutCabeceraDTO = new LayoutCabeceraDTO();
//	  List<LayoutLineaDTO> layoutLineaDTOList = new ArrayList<>(); LayoutLineaDTO
//	  layoutLineaDTO1 = new LayoutLineaDTO(); LayoutLineaDTO layoutLineaDTO2 = new
//	  LayoutLineaDTO(); 
//	  layoutCabeceraDTO.setCabecera("H");
//	  layoutCabeceraDTO.setCodigoOrigen("B");
//	  layoutCabeceraDTO.setDescripcion("COMISION ECOMM 14122018");
//	  layoutCabeceraDTO.setFecha(new Date());
//	  layoutCabeceraDTO.setUnidadNegocio("NMP01"); 
//    layoutCabeceraDTO.setId(1L);
//	  layoutLineaDTO1.setCuenta("6402001001"); 
//    layoutLineaDTO1.setDepId("");
//	  layoutLineaDTO1.setId(1L); 
//    layoutLineaDTO1.setLinea("L");
//	  layoutLineaDTO1.setMonto(30.00); 
//    layoutLineaDTO1.setNegocio("PRENDA");
//	  layoutLineaDTO1.setProyectoNMP("SUCS_NB");
//	  layoutLineaDTO1.setUnidadOperativa("13000");
//	  layoutLineaDTO2.setCuenta("6402001001"); layoutLineaDTO2.setDepId("");
//	  layoutLineaDTO2.setId(2L); layoutLineaDTO2.setLinea("L");
//	  layoutLineaDTO2.setMonto(90.00); layoutLineaDTO2.setNegocio("PRENDA");
//	  layoutLineaDTO2.setProyectoNMP("SUCS_NB");
//	  layoutLineaDTO2.setUnidadOperativa("13001");
//	  layoutLineaDTOList.add(layoutLineaDTO1);
//	  layoutLineaDTOList.add(layoutLineaDTO2);
//	  layoutDTO.setCabecera(layoutCabeceraDTO);
//	  layoutDTO.setLineas(layoutLineaDTOList);
//	  layoutDTO.setTipoLayout(TipoLayoutEnum.PAGOS); layoutDTO.setFolio(1); return
//	  layoutDTO; }
	 

}
