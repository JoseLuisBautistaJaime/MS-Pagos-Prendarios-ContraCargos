/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.services.conciliacion.LayoutsService;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorLayout;

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
	 * Service para Layouts
	 */
	@Autowired
	private LayoutsService layoutsService;

	/**
	 * Permite consultar un layout, como PAGOS, COMISIONES_MOV, COMISIONES_GENERALES
	 * y DEVOLUCIONES.
	 * 
	 * @param folio
	 * @param tipoLayout
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/layouts/{folio}/{tipoLayout}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Permite consultar un layout, como PAGOS, COMISIONES_MOV, COMISIONES_GENERALES y DEVOLUCIONES.", tags = {
			"Layouts" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByFolioAndTipoLayout(@PathVariable(value = "folio", required = true) Long folio,
			@PathVariable(value = "tipoLayout", required = true) String tipoLayout) {
		if (!ValidadorLayout.validateConsultaUnLayout(folio, tipoLayout))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		LayoutDTO layoutDTO = layoutsService.consultarUnLayout(folio, tipoLayout);
		if (!ValidadorLayout.validateLayoutDTO(layoutDTO))
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0005);
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
	@ApiOperation(httpMethod = "GET", value = "Permite consultar todos los layouts de una conciliación.", tags = {
			"Layouts" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Consulta exitosa"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByFolio(@PathVariable(value = "folio", required = true) Long folio) {
		if (!ValidadorLayout.validateLong(folio))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		List<LayoutDTO> layoutDTOs = layoutsService.consultarLayouts(folio);
		if (!ValidadorLayout.validateLayoutDTOs(layoutDTOs))
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0005);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				layoutDTOs);
	}

	/**
	 * Permite agregar y editar un layout, como PAGOS, COMISIONES_MOV,
	 * COMISIONES_GENERALES y DEVOLUCIONES.
	 * 
	 * @param layoutSaveDTO
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/layouts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Permite agregar y editar un layout, como PAGOS, COMISIONES_MOV, COMISIONES_GENERALES y DEVOLUCIONES.", tags = {
			"Layouts" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Layouts agregados con éxito."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response saveLayout(@RequestBody LayoutDTO layoutDTOe,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String requestUser) {
		if (!ValidadorLayout.validateSaveLayout(layoutDTOe))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		layoutsService.saveLayout(layoutDTOe, requestUser);
		LayoutDTO layoutDTOs = layoutsService.consultarUnLayout(layoutDTOe.getFolio(),
				layoutDTOe.getTipoLayout().toString());
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Layouts agregados con éxito.",
				layoutDTOs);
	}

	/**
	 * Permite eliminar un layout, como PAGOS, COMISIONES_MOV, COMISIONES_GENERALES
	 * y DEVOLUCIONES.
	 * 
	 * @param folio
	 * @param idLayout
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/layouts/{folio}/{idLayout}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Permite eliminar un layout, como PAGOS, COMISIONES_MOV, COMISIONES_GENERALES y DEVOLUCIONES.", tags = {
			"Layouts" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Layout eliminado con éxito."),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response deleteLayout(@PathVariable(name = "folio", required = true) Long folio,
			@PathVariable(name = "idLayout", required = true) Long idLayout) {
		if (!ValidadorLayout.validateDeleteLayout(folio, idLayout))
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		boolean respuesta = layoutsService.eliminarUnLayout(folio, idLayout);
		if (respuesta)
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_001);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Layout eliminado con éxito.", null);
	}

}
