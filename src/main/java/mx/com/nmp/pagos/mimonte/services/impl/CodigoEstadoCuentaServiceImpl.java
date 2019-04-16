/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.ArrayList;
import java.util.List;

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
		codigoEC = codigoEstadoCuentaRepository.findByEntidadIdAndCategoriaId(e.getEntidad().getId(),
				e.getCategoria().getId());
		if (null == codigoEC) {
			Entidad entidad = entidadRepository.findById(e.getEntidad().getId()).isPresent()
					? entidadRepository.findById(e.getEntidad().getId()).get()
					: null;
			Categoria categoria = categoriaRepository.findById(e.getCategoria().getId()).isPresent()
					? categoriaRepository.findById(e.getCategoria().getId()).get()
					: null;
			if (null == entidad)
				throw new CatalogoException(CatalogConstants.NO_ENTIDAD_FOUND);
			if (null == categoria)
				throw new CatalogoException(CatalogConstants.NO_CATEGORIA_FOUND);
			if (null != e)
				e.setCreatedBy(createdBy);
			CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
			CodigoEstadoCuenta ent = CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTO(e);
			codigoEstadoCuentaRepository.save(ent);
			ent.setEntidad(entidadRepository.findById(ent.getEntidad().getId()).isPresent()
					? entidadRepository.findById(ent.getEntidad().getId()).get()
					: null);
			ent.setCategoria(categoriaRepository.findById(ent.getCategoria().getId()).isPresent()
					? categoriaRepository.findById(ent.getCategoria().getId()).get()
					: null);
			codigoEstadoCuentaDTO = CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaDTOFromCodigoEstadoCuenta(ent);
			return (T) codigoEstadoCuentaDTO;
		} else
			throw new CatalogoException(CatalogConstants.CODIGO_E_C_ALREADY_EXISTS);
	}

	/**
	 * Actualiza un catalogo CodigoEstadoCuenta
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(CodigoEstadoCuentaDTO e, String lastModifiedBy) {
		CodigoEstadoCuenta codigoEC = null;
		codigoEC = codigoEstadoCuentaRepository.findByEntidadIdAndCategoriaId(e.getEntidad().getId(),
				e.getCategoria().getId());
		if (null == codigoEC) {
			Entidad entidad = entidadRepository.findById(e.getEntidad().getId()).isPresent()
					? entidadRepository.findById(e.getEntidad().getId()).get()
					: null;
			Categoria categoria = categoriaRepository.findById(e.getCategoria().getId()).isPresent()
					? categoriaRepository.findById(e.getCategoria().getId()).get()
					: null;
			if (null == entidad)
				throw new CatalogoException(CatalogConstants.NO_ENTIDAD_FOUND);
			if (null == categoria)
				throw new CatalogoException(CatalogConstants.NO_CATEGORIA_FOUND);
			if (null != e)
				e.setLastModifiedBy(lastModifiedBy);
			CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
			CodigoEstadoCuenta ent = CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTOUpdt(e);
			codigoEstadoCuentaDTO = CodigoEstadoCuentaBuilder
					.buildCodigoEstadoCuentaDTOFromCodigoEstadoCuenta(codigoEstadoCuentaRepository.save(ent));
			return (T) codigoEstadoCuentaDTO;
		} else
			throw new CatalogoException(CatalogConstants.CODIGO_E_C_ALREADY_EXISTS);
	}

	/**
	 * Regresa un catalogo CodigoEstadoCuenta por su id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) throws EmptyResultDataAccessException {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		CodigoEstadoCuenta codigoEstadoCuenta = codigoEstadoCuentaRepository.findById(id).isPresent()
				? codigoEstadoCuentaRepository.findById(id).get()
				: null;
		if (null == codigoEstadoCuenta)
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND);
		codigoEstadoCuentaDTO = CodigoEstadoCuentaBuilder
				.buildCodigoEstadoCuentaDTOFromCodigoEstadoCuenta(codigoEstadoCuenta);
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
		List<CodigoEstadoCuenta> codigoEstadoCuentaList = codigoEstadoCuentaRepository.findByEntidad_Id(idEntidad);
		if (null == codigoEstadoCuentaList || codigoEstadoCuentaList.isEmpty())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND);
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

	/**
	 * Actualiza el estatus de un catalogo CodigoEstadoCuenta a false (inactivo)
	 * 
	 * @param estatus
	 * @param idCodigo
	 */
	public void updateEstatus(final Boolean estatus, final Long idCodigo) {
		codigoEstadoCuentaRepository.setEstatusWhereId(estatus, idCodigo);
	}

}
