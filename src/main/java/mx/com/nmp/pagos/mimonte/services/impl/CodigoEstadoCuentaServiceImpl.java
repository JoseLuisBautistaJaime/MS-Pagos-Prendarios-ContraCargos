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

import mx.com.nmp.pagos.mimonte.builder.CodigoEstadoCuentaBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.dao.CodigoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaUpdtDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;
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
	 * Guarda un nuevo catalogo CodigoEstadoCuenta
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(CodigoEstadoCuentaDTO e, String createdBy)
			throws EmptyResultDataAccessException, CatalogoException {
		CodigoEstadoCuenta codigoEC = null;
		codigoEC = codigoEstadoCuentaRepository.findByEntidadIdAndCategoriaId(e.getEntidad().getId(),
				e.getCategoria().getId());
		if (null == codigoEC) {
			if (null != e)
				e.setCreatedBy(createdBy);
			CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
			CodigoEstadoCuenta ent = CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTO(e);
			codigoEstadoCuentaRepository.saveAndFlush(ent);
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
		if (null != e)
			e.setLastModifiedBy(lastModifiedBy);
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		CodigoEstadoCuenta ent = CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTOUpdt(e);
		codigoEstadoCuentaDTO = CodigoEstadoCuentaBuilder
				.buildCodigoEstadoCuentaDTOFromCodigoEstadoCuenta(codigoEstadoCuentaRepository.saveAndFlush(ent));
		return (T) codigoEstadoCuentaDTO;
	}

	/**
	 * Regresa un catalogo CodigoEstadoCuenta por su id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) throws EmptyResultDataAccessException {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		codigoEstadoCuentaDTO = codigoEstadoCuentaRepository.findById(id).isPresent() ? CodigoEstadoCuentaBuilder
				.buildCodigoEstadoCuentaDTOFromCodigoEstadoCuenta(codigoEstadoCuentaRepository.findById(id).get())
				: null;
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
		lst = CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaUpdtDTOListFromCodigoEstadoCuentaList(
				codigoEstadoCuentaRepository.findByEntidad_Id(idEntidad));
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
