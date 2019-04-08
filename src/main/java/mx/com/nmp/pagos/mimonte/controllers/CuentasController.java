/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import mx.com.nmp.pagos.mimonte.builder.CuentaBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionEntDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionRespDTO;
import mx.com.nmp.pagos.mimonte.dto.CategoriaDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaEntDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaRespDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.services.impl.CuentaServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorCatalogo;

/**
 * @name cuentasController
 * @description Clase que expone el servicio REST para las operaciones
 *              relacionadas con el catalogo de cuentas
 *
 * @author Victor Manuel Moran Hernandez
 * @creationDate 12/03/2019 12:22 hrs.
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio que permite realizar operciones sobre el catalogo de cuentas.", description = "REST API para realizar operaciones sobre el catalogo de cuentas", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"Cuentas" })
public class CuentasController {

	/**
	 * Bean de la fabrica de instancias
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(CuentasController.class);

	@Autowired
	@Qualifier("cuentaServiceImpl")
	private CuentaServiceImpl cuentaServiceImpl;

	/**
	 * Guarda un nuevo catalogo Cuenta
	 * 
	 * @param pagoRequestDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/catalogos/cuentas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Crea un nuevo catalogo Cuenta.", tags = { "Cuentas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Cuenta creada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response save(@RequestBody CuentaDTO cuentaDTOReq,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String createdBy) {
		if (!ValidadorCatalogo.validateCuentaSave(cuentaDTOReq))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR);
		CuentaEntDTO cuentaEntDTO = null;
		try {
			cuentaEntDTO = CuentaBuilder.buildCuentaEntDTOFromCuentaBaseDTO(cuentaServiceImpl
					.save(CuentaBuilder.buildCuentaBaseDTOFromCuentaDTO(cuentaDTOReq, new Date(), null), createdBy));
		} catch (CatalogoException cex) {
			throw cex;
		}
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_SAVE,
				cuentaEntDTO);

//		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Cuenta creada correctamente",
//				buildDummyUpdt());
	}

	/**
	 * Actualiza un catalogo Cuenta
	 * 
	 * @param cuentasDTO
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/cuentas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Actualiza un catalogo cuentas.", tags = { "Cuentas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "cuentas actualizada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response update(@RequestBody CuentaDTO cuentaDTOReq,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {
		if (!ValidadorCatalogo.validateCuentaUpdate(cuentaDTOReq))
			throw new CatalogoException(CatalogConstants.CATALOG_VALIDATION_ERROR);
		CuentaEntDTO cuentaEntDTO = CuentaBuilder.buildCuentaEntDTOFromCuentaBaseDTO(cuentaServiceImpl
				.update(CuentaBuilder.buildCuentaBaseDTOFromCuentaDTO(cuentaDTOReq, null, new Date()), lastModifiedBy));
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_UPDATE,
				cuentaEntDTO);

//		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Cuenta actualizada correctamente",
//				buildDummyUpdt());
	}

	/**
	 * Obtiene un catalogo Cuenta por su id
	 * 
	 * @param idcuentas
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/cuentas/{numeroCuenta}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto catalogo cuentas en base a su id", tags = {
			"Cuentas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "cuentas encontrada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByNumeroCuenta(@PathVariable(value = "numeroCuenta", required = true) String numeroCuenta) {
		CuentaEntDTO cuentaEntDTO = null;
		try {
			cuentaEntDTO = cuentaServiceImpl.findByNumeroCuenta(numeroCuenta);
		} catch (EmptyResultDataAccessException erdaex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND);
		}
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				cuentaEntDTO);

//		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Cuenta recuperada correctamente",
//				buildDummyUpdt());
	}

	/**
	 * Obtiene uno o mas catalogos de Cuenta por su nombre y estatus
	 * 
	 * @param nombre
	 * @param estatus
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/cuentas/entidad/{idEntidad}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa un objeto catalogo Cuenta en base a su id", tags = { "Cuentas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "cuentas encontradas"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findByCuenta(@PathVariable(value = "idEntidad", required = true) Long idEntidad) {

		List<CuentaEntDTO> cuentaEntDTOList = null;
		try {
			cuentaEntDTOList = cuentaServiceImpl.findByEntidadId(idEntidad);
		} catch (EmptyResultDataAccessException erdaex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND);
		}
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				cuentaEntDTOList);

//		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "cuentas recuperadas correctamente",
//				buildDummyUpdtLst());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/catalogos/cuentas/{idCuenta}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Elimina el registro en base a su id", tags = { "Cuentas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Cuenta Eliminada"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response deleteByidcuenta(@PathVariable(value = "idCuenta", required = true) Long idCuenta,
			@RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String lastModifiedBy) {

		try {
			cuentaServiceImpl.updateEstatusById(false, idCuenta, lastModifiedBy, new Date());
		} catch (EmptyResultDataAccessException erdaex) {
			throw new CatalogoException(CatalogConstants.CATALOG_ID_NOT_FOUND);
		}

		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS_DELETE,
				null);
	}

	/**
	 * Regresa una lista con todas las cuentas
	 * 
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/catalogos/cuentas", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Regresa todos los catalogo Cuenta", tags = { "Cuentas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "cuentas encontradas"),
			@ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
			@ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
			@ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
			@ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
	public Response findAll() {
		@SuppressWarnings("unchecked")
		List<CuentaEntDTO> cuentaEntDTOList = CuentaBuilder
				.buildCuentaEntDTOListFromCuentaBaseDTOList((List<CuentaBaseDTO>) cuentaServiceImpl.findAll());
		return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), CatalogConstants.CONT_MSG_SUCCESS,
				null != cuentaEntDTOList ? cuentaEntDTOList : new ArrayList<>());
	}

	/**
	 * Crea un objeto de respuesta dummy
	 * 
	 * @return
	 */

	public static CuentaDTO buildDummyNew() {

		CuentaDTO cuentasDto = new CuentaDTO();
		List<AfiliacionRespDTO> afiliaciones = new ArrayList<AfiliacionRespDTO>();
		AfiliacionRespDTO afiliacionDto = new AfiliacionRespDTO();
		afiliacionDto.setId(234L);
		afiliacionDto.setNumero(12345678L);
		afiliaciones.add(afiliacionDto);
		List<CodigoEstadoCuentaDTO> codigos = new ArrayList<CodigoEstadoCuentaDTO>();
		CodigoEstadoCuentaDTO codigo = new CodigoEstadoCuentaDTO();
		CategoriaDTO cate = new CategoriaDTO();
		cate.setId(123l);
		cate.setDescripcion("Ventas");
		codigo.setEstatus(true);
		codigo.setCategoria(cate);
		codigo.setCodigo("Leyenda CCFF");
		codigo.setId(1234L);
		codigos.add(codigo);
		cuentasDto.setEstatus(true);
		cuentasDto.setId(234L);
		cuentasDto.setNumero("12345678");
		cuentasDto.setAfiliaciones(afiliaciones);

		return cuentasDto;
	}

	public static CuentaRespDTO dummyRespDTO() {
		CuentaRespDTO cuentaRespDTO = new CuentaRespDTO();
		cuentaRespDTO.setEstatus(true);
		cuentaRespDTO.setId(1L);
		cuentaRespDTO.setNumero("43275498759");
		return cuentaRespDTO;
	}

	/**
	 * Respuesta dummy para actualizar y consulta por id id de entidades asociadas
	 * 
	 * @return
	 */
	public static List<CuentaEntDTO> buildDummyUpdtLst() {
		List<CuentaEntDTO> cuentasDto = new ArrayList<>();
		CuentaEntDTO cuentaEntDTO1 = new CuentaEntDTO();
		CuentaEntDTO cuentaEntDTO2 = new CuentaEntDTO();
		List<AfiliacionEntDTO> afiliaciones = new ArrayList<>();
		List<AfiliacionEntDTO> afiliaciones2 = new ArrayList<>();
		AfiliacionEntDTO afiliacionDto = new AfiliacionEntDTO();
		AfiliacionEntDTO afiliacionDto2 = new AfiliacionEntDTO();
		AfiliacionEntDTO afiliacionDto3 = new AfiliacionEntDTO();
		AfiliacionEntDTO afiliacionDto4 = new AfiliacionEntDTO();
		afiliacionDto.setId(234L);
		afiliacionDto.setNumero(12345678L);
		afiliacionDto.setEstatus(true);
		afiliacionDto2.setId(555234L);
		afiliacionDto2.setNumero(4541545456L);
		afiliacionDto2.setEstatus(true);
		afiliacionDto3.setId(234111L);
		afiliacionDto3.setNumero(10000978L);
		afiliacionDto3.setEstatus(true);
		afiliacionDto4.setId(9090904L);
		afiliacionDto4.setNumero(112233L);
		afiliacionDto4.setEstatus(true);
		afiliaciones.add(afiliacionDto);
		afiliaciones.add(afiliacionDto2);
		afiliaciones2.add(afiliacionDto3);
		afiliaciones2.add(afiliacionDto4);
		List<CodigoEstadoCuentaDTO> codigos = new ArrayList<CodigoEstadoCuentaDTO>();
		CodigoEstadoCuentaDTO codigo = new CodigoEstadoCuentaDTO();
		CategoriaDTO cate = new CategoriaDTO();
		cate.setId(123l);
		cate.setDescripcion("Otra Categoria");
		codigo.setEstatus(true);
		codigo.setCategoria(cate);
		codigo.setCodigo("Leyenda FFFRR");
		codigo.setId(1234l);
		codigos.add(codigo);
		cuentaEntDTO1.setEstatus(true);
		cuentaEntDTO1.setId(234L);
		cuentaEntDTO1.setNumero("12345678");
		cuentaEntDTO1.setAfiliaciones(afiliaciones);
		cuentaEntDTO2.setEstatus(true);
		cuentaEntDTO2.setId(23443L);
		cuentaEntDTO2.setNumero("1234454556");
		cuentaEntDTO2.setAfiliaciones(afiliaciones2);
		cuentasDto.add(cuentaEntDTO1);
		cuentasDto.add(cuentaEntDTO2);
		return cuentasDto;
	}

	/**
	 * Respuesta dummy para consulta por
	 * 
	 * @return
	 */
	public static CuentaEntDTO buildDummyUpdt() {
		CuentaEntDTO cuentasDto = new CuentaEntDTO();
		List<AfiliacionEntDTO> afiliaciones = new ArrayList<>();
		AfiliacionEntDTO afiliacionDto = new AfiliacionEntDTO();
		AfiliacionEntDTO afiliacionDto2 = new AfiliacionEntDTO();
		afiliacionDto.setId(234L);
		afiliacionDto.setNumero(12345678L);
		afiliacionDto.setEstatus(true);
		afiliacionDto2.setId(888L);
		afiliacionDto2.setNumero(998811L);
		afiliacionDto2.setEstatus(true);
		afiliaciones.add(afiliacionDto);
		afiliaciones.add(afiliacionDto2);
		List<CodigoEstadoCuentaDTO> codigos = new ArrayList<CodigoEstadoCuentaDTO>();
		CodigoEstadoCuentaDTO codigo = new CodigoEstadoCuentaDTO();
		CategoriaDTO cate = new CategoriaDTO();
		cate.setId(123l);
		cate.setDescripcion("Otra Categoria");
		codigo.setEstatus(true);
		codigo.setCategoria(cate);
		codigo.setCodigo("Leyenda GGF");
		codigo.setId(1234l);
		codigos.add(codigo);
		cuentasDto.setEstatus(true);
		cuentasDto.setId(111234L);
		cuentasDto.setNumero("12443345678");
		cuentasDto.setAfiliaciones(afiliaciones);
		return cuentasDto;
	}

	public static CuentaDTO buildDummy() {

		CuentaDTO cuentasDto = new CuentaDTO();
		List<AfiliacionRespDTO> afiliaciones = new ArrayList<AfiliacionRespDTO>();
		AfiliacionRespDTO afiliacionDto = new AfiliacionRespDTO();
		afiliacionDto.setId(234L);
		afiliacionDto.setNumero(12345678L);
		afiliaciones.add(afiliacionDto);
		List<CodigoEstadoCuentaDTO> codigos = new ArrayList<CodigoEstadoCuentaDTO>();
		CodigoEstadoCuentaDTO codigo = new CodigoEstadoCuentaDTO();
		CategoriaDTO cate = new CategoriaDTO();
		cate.setId(123l);
		cate.setDescripcion("Ventas");
		codigo.setEstatus(true);
		codigo.setCategoria(cate);
		codigo.setCodigo("Leyenda CCX");
		codigo.setId(1234l);
		codigos.add(codigo);
		cuentasDto.setEstatus(true);
		cuentasDto.setId(234L);
		cuentasDto.setNumero("12345678");
		cuentasDto.setAfiliaciones(afiliaciones);

		return cuentasDto;
	}

	public static List<CuentaDTO> buildDummyEnti() {

		List<CuentaDTO> cuentas = new ArrayList<CuentaDTO>();
		CuentaDTO cuentasDto = new CuentaDTO();
		List<AfiliacionRespDTO> afiliaciones = new ArrayList<AfiliacionRespDTO>();
		AfiliacionRespDTO afiliacionDto = new AfiliacionRespDTO();
		afiliacionDto.setId(234L);
		afiliaciones.add(afiliacionDto);
		CategoriaDTO cate = new CategoriaDTO();
		cate.setId(123l);
		cate.setDescripcion("Categoria Dummy");
		cuentasDto.setEstatus(true);
		cuentasDto.setId(234L);
		cuentasDto.setNumero("12345678");
		cuentasDto.setAfiliaciones(afiliaciones);
		cuentas.add(cuentasDto);
		afiliacionDto.setId(12345L);
		afiliacionDto.setNumero(654321L);
		cuentas.add(cuentasDto);
		return cuentas;
	}

}