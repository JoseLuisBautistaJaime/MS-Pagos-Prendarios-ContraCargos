/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.CodigoEstadoCuentaBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.dao.CategoriaRepository;
import mx.com.nmp.pagos.mimonte.dao.CodigoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaUpdtDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Categoria;
import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.services.CatalogoAdmService;

/**
 * @name CodigoEstadoCuentaServiceImpl
 * @description Clase que implementa la clase CatalgoAdmService y realiza
 *              operaciones de logica de negocios relacionadas con el catalogo
 *              CodigoEstadoCuenta
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/03/2019 12:39 hrs.
 * @version 0.1
 */
@Service("codigoEstadoCuentaServiceImpl")
public class CodigoEstadoCuentaServiceImpl implements CatalogoAdmService<CodigoEstadoCuentaDTO> {

	/**
	 * Repository del catalogo CodigoEstadoCuenta
	 */
	@Autowired
	@Qualifier("codigoEstadoCuentaRepository")
	private CodigoEstadoCuentaRepository codigoEstadoCuentaRepository;

	/**
	 * Repository del catalogo Entidad
	 */
	@Autowired
	@Qualifier("entidadRepository")
	private EntidadRepository entidadRepository;

	/**
	 * Repository del catalogo Categoria
	 */
	@Autowired
	@Qualifier("categoriaRepository")
	private CategoriaRepository categoriaRepository;

	/**
	 * Guarda un nuevo catalogo CodigoEstadoCuenta
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(CodigoEstadoCuentaDTO e, String createdBy)
			throws EmptyResultDataAccessException, CatalogoException {
		CodigoEstadoCuenta codigoEC = null;
		Optional<Entidad> entidad = null;
		Optional<Categoria> categoria = null;
		// Valida por id de entidad, id categoria y codigo que el codigo estado cuenta
		// no exista
		codigoEC = codigoEstadoCuentaRepository.findByEntidadIdAndCategoriaIdAndCodigo(e.getEntidad().getId(),
				e.getCategoria().getId(), e.getCodigo());
		if (null != codigoEC)
			throw new CatalogoException(CatalogConstants.CODIGO_ALREADY_EXISTS);
		else {
			// Valida que la entidad y la categoria existan
			entidad = entidadRepository.findById(e.getEntidad().getId());
			categoria = categoriaRepository.findById(e.getCategoria().getId());
			if (!entidad.isPresent())
				throw new CatalogoException(CatalogConstants.NO_ENTIDAD_FOUND);
			if (!categoria.isPresent())
				throw new CatalogoException(CatalogConstants.NO_CATEGORIA_FOUND);
			if (null != e)
				e.setCreatedBy(createdBy);
			CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
			// Construye la entidad persistente del codigo estado cuenta
			CodigoEstadoCuenta ent = CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTO(e);
			// Guarda el codigo estado cuenta
			codigoEstadoCuentaRepository.save(ent);
			// Setea los objetos entidad y la categoria a el objeto principal
			ent.setEntidad(entidad.get());
			ent.setCategoria(categoria.get());
			// Construye el DTO de respuesta y lo regresa
			codigoEstadoCuentaDTO = CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaDTOFromCodigoEstadoCuenta(ent);
			return (T) codigoEstadoCuentaDTO;
		}
	}

	/**
	 * Actualiza un catalogo CodigoEstadoCuenta
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(CodigoEstadoCuentaDTO e, String lastModifiedBy) {
		CodigoEstadoCuenta ent = null;
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		Optional<CodigoEstadoCuenta> codigoEstadoCuenta = null;
		CodigoEstadoCuenta codigoEC = null;
		Optional<Entidad> entidad = null;
		Optional<Categoria> categoria = null;
		// Valida si la entidad y categoria existen
		entidad = entidadRepository.findById(e.getEntidad().getId());
		categoria = categoriaRepository.findById(e.getCategoria().getId());
		if (!entidad.isPresent())
			throw new CatalogoException(CatalogConstants.NO_ENTIDAD_FOUND);
		if (!categoria.isPresent())
			throw new CatalogoException(CatalogConstants.NO_CATEGORIA_FOUND);
		// Valida que el id de codigo estado cuenta existe
		codigoEstadoCuenta = codigoEstadoCuentaRepository.findById(e.getId());
		if (!codigoEstadoCuenta.isPresent())
			throw new CatalogoException(CatalogConstants.CODIGO_E_C_DOESNT_EXISTS);
		// Valida que el codigo no se repita, por su id de entidad, id de categoria y
		// codigo
		codigoEC = codigoEstadoCuentaRepository.findByEntidadIdAndCategoriaIdAndCodigo(e.getEntidad().getId(),
				e.getCategoria().getId(), e.getCodigo());
		if (null != codigoEC && !codigoEC.getId().equals(e.getId())) {
			throw new CatalogoException(CatalogConstants.CODIGO_ALREADY_EXISTS);
		}
		if (null != e)
			e.setLastModifiedBy(lastModifiedBy);
		// Construye el objeto a guardar
		ent = CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTOUpdt(e);
		// Guarda y al mismo tiempo construye el objeto de respuesta
		codigoEstadoCuentaDTO = CodigoEstadoCuentaBuilder
				.buildCodigoEstadoCuentaDTOFromCodigoEstadoCuenta(codigoEstadoCuentaRepository.save(ent));
		return (T) codigoEstadoCuentaDTO;
	}

	/**
	 * Regresa un catalogo CodigoEstadoCuenta por su id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) throws EmptyResultDataAccessException {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		Optional<CodigoEstadoCuenta> codigoEstadoCuenta = null;
		// Valida por id que el codigo exista
		codigoEstadoCuenta = codigoEstadoCuentaRepository.findById(id);
		if (!codigoEstadoCuenta.isPresent())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND);
		// Construye el objeto de respuesta y lo regresa
		codigoEstadoCuentaDTO = CodigoEstadoCuentaBuilder
				.buildCodigoEstadoCuentaDTOFromCodigoEstadoCuenta(codigoEstadoCuenta.get());
		return (T) codigoEstadoCuentaDTO;
	}

	/**
	 * Regresa una lista de catalogos CodigoEstadoCuenta por el id de una de sus
	 * entidades relacionadas
	 * 
	 * @param idEntidad
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<CodigoEstadoCuentaUpdtDTO> findByEntidadId(Long idEntidad) throws EmptyResultDataAccessException {
		List<CodigoEstadoCuentaUpdtDTO> lst = null;
		List<CodigoEstadoCuenta> codigoEstadoCuentaList = null;
		// Encuentra una lista de codigos por ud de entidad asociada
		codigoEstadoCuentaList = codigoEstadoCuentaRepository.findByEntidad_Id(idEntidad);
		// Valida que la lista no sea nula o vacia
		if (null == codigoEstadoCuentaList || codigoEstadoCuentaList.isEmpty())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND);
		// Construye una lista de respuesta y la regresa
		lst = CodigoEstadoCuentaBuilder
				.buildCodigoEstadoCuentaUpdtDTOListFromCodigoEstadoCuentaList(codigoEstadoCuentaList);
		return null != lst ? lst : new ArrayList<>();
	}

	/**
	 * Regresa todos los catalogos de tipo CodigoEstadoCuenta
	 */
	@Override
	public List<? extends AbstractCatalogoDTO> findAll() {
		List<CodigoEstadoCuentaDTO> lst = null;
		// Consulta todos los codigos y los regresa
		lst = CodigoEstadoCuentaBuilder
				.buildCodigoEstadoCuentaDTOListFromCodigoEstadoCuentaList(codigoEstadoCuentaRepository.findAll());
		return null != lst ? lst : new ArrayList<>();
	}

	/**
	 * Elimina un catalogo de codigo de estado de cuenta por id
	 */
	@Override
	public void deleteById(Long id) throws EmptyResultDataAccessException {
		codigoEstadoCuentaRepository.deleteById(id);
	}

}
