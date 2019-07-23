/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.AfiliacionBuilder;
import mx.com.nmp.pagos.mimonte.builder.CuentaBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dao.AfiliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.CuentaRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaEntDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Afiliacion;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.services.CatalogoAdmService;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorCatalogo;

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
	 * Repository de Afiliacion
	 */
	@Autowired
	@Qualifier("afiliacionRepository")
	private AfiliacionRepository afiliacionRepository;

	/**
	 * Guarda una cuenta
	 * 
	 * @param e
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(CuentaBaseDTO e, String createdBy) {
		// Valida si ya existe una cuenta con el numero especificado
		Cuenta cta = cuentaRepository.findByNumeroCuenta(e.getNumeroCuenta());
		if (null != cta)
			throw new CatalogoException(CatalogConstants.NUMERO_CUENTA_ALREADY_EXISTS,
					CodigoError.NMP_PMIMONTE_BUSINESS_005);
		if (null != e)
			e.setCreatedBy(createdBy);
		// Encuentra todas las afiliaciones
		List<Afiliacion> afiliacionesTest = afiliacionRepository.findAll();
		// Valida si las afiliaciones especificadas existen
		if (!ValidadorCatalogo.validateAfiliacionesExists(
				AfiliacionBuilder.buildAfiliacionDTOListFromAfiliacionList(afiliacionesTest), e.getAfiliaciones()))
			throw new CatalogoException(CatalogConstants.NUMERO_AFILIACION_DOESNT_EXISTS,
					CodigoError.NMP_PMIMONTE_BUSINESS_006);
		// Guarda la cuenta
		Cuenta cuenta = cuentaRepository.save(CuentaBuilder.buildCuentaFromCuentaBaseDTO(e));
		// Encuentra las afiliaciones para adjuntarlas (con todos sus atributos) al DTO
		// de respuesta
		Set<Afiliacion> afiliaciones = afiliacionRepository.findByCuentas_Id(cuenta.getId());
		cuenta.setAfiliaciones(afiliaciones);
		return (T) CuentaBuilder.buildCuentaBaseDTOFromCuenta(cuenta);
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
		if (null == e)
			throw new CatalogoException(CatalogConstants.CUENTA_IS_NULL, CodigoError.NMP_PMIMONTE_BUSINESS_007);
		else
			e.setLastModifiedBy(lastModifiedBy);
		// Valida si el id de la cuenta existe
		Optional<Cuenta> cta = cuentaRepository.findById(e.getId());
		if (!cta.isPresent())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);
		// Valida si ya existe una cuenta con el numeroe specificado
		Cuenta ctaByNum = cuentaRepository.findByNumeroCuenta(e.getNumeroCuenta());
		if (null != ctaByNum && null != ctaByNum.getId() && null != e.getId() && !ctaByNum.getId().equals(e.getId()))
			throw new CatalogoException(CatalogConstants.NUMERO_CUENTA_ALREADY_EXISTS,
					CodigoError.NMP_PMIMONTE_BUSINESS_005);
		// Valida si los numeros de afiliacion expecificados existen
		List<Afiliacion> afiliacionesTest = afiliacionRepository.findAll();
		if (!ValidadorCatalogo.validateAfiliacionesExists(
				AfiliacionBuilder.buildAfiliacionDTOListFromAfiliacionList(afiliacionesTest), e.getAfiliaciones()))
			throw new CatalogoException(CatalogConstants.NUMERO_AFILIACION_DOESNT_EXISTS,
					CodigoError.NMP_PMIMONTE_BUSINESS_006);
		// Construye la respuesta y regresa el objeto
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
		Optional<Cuenta> cuenta = null;
		CuentaBaseDTO cuentaBaseDTO = null;
		// Encuentra una cuenta por id
		cuenta = cuentaRepository.findById(id);
		// Valida si la cuenta existe y la pone en un DTO de respuesta
		if (cuenta.isPresent())
			cuentaBaseDTO = CuentaBuilder.buildCuentaBaseDTOFromCuenta(cuenta.get());
		return (T) cuentaBaseDTO;
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
		// Obtiene una lista de cuentas por id de entidad
		List<Cuenta> cuentas = cuentaRepository.qGetByEntidadId(idEntidad);
		// Valida que la lista no sea nula o vacia
		if (null == cuentas || cuentas.isEmpty())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);
		// Construye el objeto de respuesta y lo regresa
		return CuentaBuilder.buildCuentaEntDTOListFromCuentaList(cuentas);
	}

	/**
	 * Regresa todas las cuentas
	 */
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
	 * @throws                                javax.persistence.NonUniqueResultException
	 */
	public CuentaEntDTO findByNumeroCuenta(final String numeroCuenta)
			throws EmptyResultDataAccessException, javax.persistence.NonUniqueResultException {
		Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);
		if (null == cuenta)
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);
		return CuentaBuilder.buildCuentaEntDTOFromCuenta(cuenta);
	}

	/**
	 * Actualiza el estatus de una cuenta a false (inactivo)
	 * 
	 * @param estatus
	 * @param id
	 * @param lastModifiedBy
	 * @param lastModifiedDate
	 * @throws EmptyResultDataAccessException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateEstatusById(final Boolean estatus, final Long id, final String lastModifiedBy,
			Date lastModifiedDate) throws EmptyResultDataAccessException {
		Optional<Cuenta> cuenta = null;
		// Encuentra una cuenta por id
		cuenta = cuentaRepository.findById(id);
		// Valida que la cuenta existe
		if (!cuenta.isPresent())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);
		// Valida si la cuenta ya fue dada de baja previamente
		else if (!cuenta.get().getEstatus())
			throw new CatalogoException(CatalogConstants.CUENTA_HAS_ALREADY_DELETED,
					CodigoError.NMP_PMIMONTE_BUSINESS_075);
		// Actualiza el estatus de una cuenta
		cuentaRepository.updateEstatusById(estatus, id, lastModifiedBy, lastModifiedDate);
	}

}
