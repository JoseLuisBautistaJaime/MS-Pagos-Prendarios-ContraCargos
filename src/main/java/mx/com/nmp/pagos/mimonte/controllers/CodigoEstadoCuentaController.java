package mx.com.nmp.pagos.mimonte.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.nmp.pagos.mimonte.builder.CodigoEstadoCuentaBuilder;
import mx.com.nmp.pagos.mimonte.dto.BaseCodigoDTO;
import mx.com.nmp.pagos.mimonte.dto.CategoriaDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaGenDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadDTO;
import mx.com.nmp.pagos.mimonte.services.impl.CodigoEstadoCuentaServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;

/**
 * Nombre: CodigoEstadoCuentaController Descripcion: Clase que expone el
 * servicio REST para las operaciones relacionadas con el catalogo de codigos de
 * estados de cuenta
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/03/2019 13:09 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operciones sobre el catalogo de codigos de estados de cuenta.", description = "REST API para realizar operaciones sobre el catalogo de codigos de estados de cuenta", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"CodigoEstadoCuenta" })
public class CodigoEstadoCuentaController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(CodigoEstadoCuentaController.class);

	/**
	 * Service para el catalogo de codigos de estados de cuenta
	 */
//	@Autowired
//	@Qualifier("codigoEstadoCuentaServiceImpl")
	private CodigoEstadoCuentaServiceImpl codigoEstadoCuentaServiceImpl;

	/**
	 * Guarda un nuevo catalogo Codigo Estado dse Cuenta
	 * 
	 * @param pagoRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/catalogos/codigos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Crea un nuevo catalogo de codigos de estados de cuenta.", tags = {
			"CodigoEstadoCuenta" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Codigo de estado de cuenta creado"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody CodigoEstadoCuentaDTO codigoEstadoCuentaDTO) {
		
//		BaseCodigoDTO bc = CodigoEstadoCuentaBuilder.buildBaseCodigoDTOFromCodigoEstadoCuentaGenDTO(
//				(CodigoEstadoCuentaGenDTO) codigoEstadoCuentaServiceImpl.save(codigoEstadoCuentaDTO));
		
		BaseCodigoDTO baseCodigoDTO = new BaseCodigoDTO(1L, true, "Leyenda");
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				"Codigo de estado de cuenta guardado correctamente", baseCodigoDTO);
	}

	/**
	 * Actualiza un Codigo Estado dse Cuenta
	 * 
	 * @param pagoRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/codigos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Actualiza un catalogo codigo de estado de cuenta.", tags = {
			"CodigoEstadoCuenta" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Codigo de estado de cuenta actualizado"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response update(@RequestBody CodigoEstadoCuentaDTO codigoEstadoCuentaDTOReq) {

//		BaseCodigoDTO bc = CodigoEstadoCuentaBuilder.buildBaseCodigoDTOFromCodigoEstadoCuentaGenDTO(
//				(CodigoEstadoCuentaGenDTO) codigoEstadoCuentaServiceImpl.save(codigoEstadoCuentaDTOReq));

		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = buildDummy();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				"Codigo de estado de cuenta actualizado correctamente", codigoEstadoCuentaDTO);
	}

	/**
	 * Regresa un Codigo Estado dse Cuenta por id
	 * 
	 * @param idCodigo
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/codigos/{idCodigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto catalogo codigo de estado de cuenta en base a su id", tags = {
			"CodigoEstadoCuenta" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Codigo de estado de cuenta encontrado"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findById(@PathVariable(value = "idCodigo", required = true) Long idCodigo) {

//		BaseCodigoDTO bc = CodigoEstadoCuentaBuilder.buildBaseCodigoDTOFromCodigoEstadoCuentaGenDTO(
//				(CodigoEstadoCuentaGenDTO) codigoEstadoCuentaServiceImpl.findById(idCodigo));

		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = buildDummy();
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				"Codigo de estado de cuenta recuperado correctamente", codigoEstadoCuentaDTO);
	}

	/**
	 * Regrsa uno o más objetos Codigo Estado dse Cuenta por nombre y etsatus
	 * 
	 * @param idEntidad
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/codigos/entidad/{idEntidad}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa uno o mas objetos catalogo coigo de estado de cuenta en base a un id de su entidad asociada", tags = {
			"CodigoEstadoCuenta" })
	@ApiResponses({
			@ApiResponse(code = 200, response = Response.class, message = "Codigo de estado de cuenta encontrado"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByNombreAndEstatus(@PathVariable(value = "idEntidad", required = true) Long idEntidad) {

//		List<CodigoEstadoCuentaDTO> lstX = codigoEstadoCuentaServiceImpl.findByEntidad_Id(idEntidad);

		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = buildDummy();
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO2 = buildDummy();
		List<CodigoEstadoCuentaDTO> lst = new ArrayList<>();
		lst.add(codigoEstadoCuentaDTO);
		lst.add(codigoEstadoCuentaDTO2);
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(),
				"Codigos de estado de cuenta recuperados correctamente", lst);
	}

	/**
	 * Crea un objeto de respuesta dummy
	 * 
	 * @return
	 */
	public static CodigoEstadoCuentaDTO buildDummy() {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = new CodigoEstadoCuentaDTO(1L, "Geronimo", true,
				new EntidadDTO("Banamex", null, null, "Banco Banamex"),
				new CategoriaDTO(1, "Categoria 1", "Categoria Uno"));
		return codigoEstadoCuentaDTO;
	}

}
