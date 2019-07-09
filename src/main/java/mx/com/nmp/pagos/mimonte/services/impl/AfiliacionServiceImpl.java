/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.AfiliacionBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dao.AfiliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Afiliacion;
import mx.com.nmp.pagos.mimonte.services.CatalogoAdmService;

/**
 * @name AfiliacionServiceImpl
 * @description Clase de capa de servicio para el catalogo de afiliaciones que
 *              sirve para realizar operaciones de logica de negocios para el
 *              catalogo de afiliaciones
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 13/03/2019 20:58 hrs.
 * @version 0.1
 */
@Service("afiliacionServiceImpl")
public class AfiliacionServiceImpl implements CatalogoAdmService<AfiliacionDTO> {

	/**
	 * Repository para Afiliacion
	 */
	@Autowired
	@Qualifier("afiliacionRepository")
	private AfiliacionRepository afiliacionRepository;

	/**
	 * Guarda una Afiliacion
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(AfiliacionDTO e, String createdBy) {
		Afiliacion afiliacion = null;
		Afiliacion afiliacionSave = null;
		AfiliacionDTO afiliacionDTO = null;
		// Valida si ya existe una afiliacion con el numeroe specificado
		afiliacion = afiliacionRepository.findByNumero(e.getNumero());
		if (null != afiliacion)
			throw new CatalogoException(CatalogConstants.NUMERO_AFILIACION_ALREADY_EXISTS, CodigoError.NMP_PMIMONTE_BUSINESS_004);
		if (null != e)
			e.setCreatedBy(createdBy);
		// Crea una entidad afiliacion, la guarda y crea una afiliacion DTO para
		// regresar
		afiliacionSave = AfiliacionBuilder.buildAfiliacionFromAfiliacionDTO(e, null, null);
		afiliacionSave = afiliacionRepository.save(afiliacionSave);
		afiliacionDTO = AfiliacionBuilder.buildAfiliacionDTOFromAfiliacion(afiliacionSave);
		return (T) afiliacionDTO;
	}

	/**
	 * Actualiza una afilicacion
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(AfiliacionDTO e, String lastModifiedBy) {
		Optional<Afiliacion> afiliacion = null;
		Afiliacion afiliacionUpdt = null;
		AfiliacionDTO afiliacionDTO = null;
		// Valida si existe una afiliacion con el id especificado
		afiliacion = afiliacionRepository.findById(e.getId());
		if (!afiliacion.isPresent())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);
		Afiliacion afiliacionByNum = null;
		// Valida que no exista ya una afiliacion con ese numero
		afiliacionByNum = afiliacionRepository.findByNumero(e.getNumero());
		if (null != afiliacionByNum)
			throw new CatalogoException(CatalogConstants.NUMERO_AFILIACION_ALREADY_EXISTS, CodigoError.NMP_PMIMONTE_BUSINESS_004);
		if (null != e)
			e.setLastModifiedBy(lastModifiedBy);
		// Crea una entidad afiliacion la guarda y crea una entidad DTO para regresar
		afiliacionUpdt = AfiliacionBuilder.buildAfiliacionFromAfiliacionDTO(e, null, null);
		afiliacionUpdt = afiliacionRepository.save(afiliacionUpdt);
		afiliacionDTO = AfiliacionBuilder.buildAfiliacionDTOFromAfiliacion(afiliacionUpdt);
		return (T) afiliacionDTO;
	}

	/**
	 * Regresa una afiliacion por id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) throws EmptyResultDataAccessException {
		AfiliacionDTO afiliacionDTO = null;
		Optional<Afiliacion> afiliacion = null;
		// Encuentra una afiliacion por id
		afiliacion = afiliacionRepository.findById(id);
		// Si la afiliacion existe crea un DTO de dicha afiliacion
		if (afiliacion.isPresent())
			afiliacionDTO = AfiliacionBuilder.buildAfiliacionDTOFromAfiliacion(afiliacion.get());
		return (T) afiliacionDTO;
	}

	/**
	 * Regresa un objeto de tipo AfiliacionDTO a partir de un id de Cuenta asociada
	 * 
	 * @param idCuenta
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public Set<AfiliacionDTO> findByCuentasId(final Long idCuenta) throws EmptyResultDataAccessException {
		Set<Afiliacion> afiliaciones = null;
		afiliaciones = afiliacionRepository.findByCuentas_Id(idCuenta);
		return AfiliacionBuilder.buildAfiliacionDTOSetFromAfiliacionSet(afiliaciones);
	}

	/**
	 * Regresa todas las afiliaciones
	 */
	@Override
	public List<? extends AbstractCatalogoDTO> findAll() {
		return AfiliacionBuilder.buildAfiliacionDTOListFromAfiliacionList(afiliacionRepository.findAll());
	}

	/**
	 * Elimina una afiliacion por id
	 */
	@Override
	public void deleteById(Long id) throws EmptyResultDataAccessException, DataIntegrityViolationException {
		Optional<Afiliacion> afiliacion = null;
		// Busca una afiliacion por id
		afiliacion = afiliacionRepository.findById(id);
		// Valida si la afiliacion existe
		if (!afiliacion.isPresent())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);
		afiliacionRepository.deleteById(id);
	}

	/**
	 * Encuentra una afiliacion por numero
	 * 
	 * @param numeroAfiliacion
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws                                javax.persistence.NonUniqueResultException
	 */
	public AfiliacionDTO findByNumero(final String numeroAfiliacion)
			throws EmptyResultDataAccessException, javax.persistence.NonUniqueResultException {
		Afiliacion afiliacion = null;
		afiliacion = afiliacionRepository.findByNumero(numeroAfiliacion);
		return AfiliacionBuilder.buildAfiliacionDTOFromAfiliacion(afiliacion);
	}

}
