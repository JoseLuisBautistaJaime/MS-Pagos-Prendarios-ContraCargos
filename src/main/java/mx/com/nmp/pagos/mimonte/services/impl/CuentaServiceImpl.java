/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.CuentaBuilder;
import mx.com.nmp.pagos.mimonte.dao.CuentaRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaEntDTO;
import mx.com.nmp.pagos.mimonte.services.CatalogoAdmService;

/**
 * @name CuentaServiceImpl
 * @description Clase que implementa los metodos de operaciones basicas sobre el
 *              catalogo de cuentas
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 13/03/2019 20:18 hrs.
 * @version 0.1
 */
@Service("cuentaServiceImpl")
public class CuentaServiceImpl implements CatalogoAdmService<CuentaBaseDTO> {

	/**
	 * Repository de Cuenta
	 */
	@Autowired
	@Qualifier("cuentaRepository")
	private CuentaRepository cuentaRepository;

	/**
	 * Guarda una cuenta
	 * 
	 * @param e
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(CuentaBaseDTO e, String createdBy) {
		if (null != e)
			e.setCreatedBy(createdBy);
		return (T) CuentaBuilder
				.buildCuentaBaseDTOFromCuenta(cuentaRepository.save(CuentaBuilder.buildCuentaFromCuentaBaseDTO(e)));
	}

	/**
	 * Actualiza una cuenta
	 * 
	 * @param e
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(CuentaBaseDTO e, String lastModifiedBy) {
		if (null != e)
			e.setLastModifiedBy(lastModifiedBy);
		return (T) CuentaBuilder
				.buildCuentaBaseDTOFromCuenta(cuentaRepository.save(CuentaBuilder.buildCuentaFromCuentaBaseDTO(e)));
	}

	/**
	 * Regresa una cuenta por id
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) throws EmptyResultDataAccessException {
		return (T) CuentaBuilder.buildCuentaBaseDTOFromCuenta(
				cuentaRepository.findById(id).isPresent() ? cuentaRepository.findById(id).get() : null);
	}

	/**
	 * Regresa una lista de objetos de tipo CuentaDTO en base a un id de entidad
	 * asociada
	 * 
	 * @param idEntidad
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<CuentaEntDTO> findByEntidadId(final Long idEntidad) throws EmptyResultDataAccessException {
		return CuentaBuilder.buildCuentaEntDTOListFromCuentaList(cuentaRepository.findByEntidades_Id(idEntidad));
	}

	@Override
	public List<? extends AbstractCatalogoDTO> findAll() {
		return CuentaBuilder.buildCuentaBaseDTOListFromCuentaList(cuentaRepository.findAll());
	}

	/**
	 * Elimina una cuenta por id
	 */
	@Override
	public void deleteById(Long id) throws EmptyResultDataAccessException {
		cuentaRepository.deleteById(id);
	}

	/**
	 * Rergresa una cuenta por numero
	 * 
	 * @param numeroCuenta
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public CuentaEntDTO findByNumeroCuenta(final String numeroCuenta) throws EmptyResultDataAccessException {
		return CuentaBuilder.buildCuentaEntDTOFromCuenta(cuentaRepository.findByNumeroCuenta(numeroCuenta));
	}

	/**
	 * Actualiza el estatus de una cuenta a false (inactivo)
	 * 
	 * @param estatus
	 * @param id
	 * @throws EmptyResultDataAccessException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateEstatusById(final Boolean estatus, final Long id) throws EmptyResultDataAccessException {
		cuentaRepository.updateEstatusById(estatus, id);
	}

}
