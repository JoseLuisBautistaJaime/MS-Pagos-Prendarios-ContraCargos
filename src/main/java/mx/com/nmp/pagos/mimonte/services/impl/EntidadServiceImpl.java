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

import mx.com.nmp.pagos.mimonte.builder.EntidadBuilder;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadResponseDTO;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.services.CatalogoAdmService;

/**
 * @name EntidadServiceImpl
 * @description Clase de capa de servicio para el catalogo de entidades que
 *              sirve para realizar operaciones de logica de negocios para el
 *              catalogo de entidades
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/03/2019 12:33 hrs.
 * @version 0.1
 */
@Service("entidadServiceImpl")
public class EntidadServiceImpl implements CatalogoAdmService<EntidadDTO> {

	/**
	 * Repository del catalogo Entidad
	 */
	@Autowired
	@Qualifier("entidadRepository")
	private EntidadRepository entidadRepository;

	/**
	 * Guarda una entidad
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(EntidadDTO e, String createdBy) {
		if (null != e)
			e.setCreatedBy(createdBy);
		return (T) EntidadBuilder
				.buildEntidadDTOFromEntidad(entidadRepository.save(EntidadBuilder.buildEntidadFromEntidadDTO(e)));
	}

	/**
	 * Actualiza una entidad
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(EntidadDTO e, String lastModifiedBy) {
		if (null != e)
			e.setLastModifiedBy(lastModifiedBy);
		return (T) EntidadBuilder
				.buildEntidadDTOFromEntidad(entidadRepository.save(EntidadBuilder.buildEntidadFromEntidadDTO(e)));
	}

	/**
	 * Encuentra una entidad por id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) throws EmptyResultDataAccessException {
		return (T) EntidadBuilder.buildEntidadDTOFromEntidad(
				entidadRepository.findById(id).isPresent() ? entidadRepository.findById(id).get() : null);
	}

	/**
	 * Encuentra una entidad por su nombre y estatus
	 * 
	 * @param nombre
	 * @param estatus
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<EntidadResponseDTO> findByNombreAndEstatus(final String nombre, final Boolean estatus)
			throws EmptyResultDataAccessException {
		return EntidadBuilder
				.buildEntidadResponseDTOListFromEntidadList(entidadRepository.findByNombreAndEstatus(nombre, estatus));
	}

	/**
	 * Encuentra todas las entidades
	 */
	@Override
	public List<? extends AbstractCatalogoDTO> findAll() {
		return (List<EntidadDTO>) EntidadBuilder
				.buildEntidadDTOListFromEntidadList((List<Entidad>) entidadRepository.findAll());
	}

	/**
	 * Elimina una entidad por id
	 */
	@Override
	public void deleteById(Long id) throws EmptyResultDataAccessException {
		entidadRepository.deleteById(id);
	}

	/**
	 * Actualiza el estatus de un catalogo Entidad por id
	 * 
	 * @param estatus
	 * @param id
	 * @throws EmptyResultDataAccessException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateEstatusById(final Boolean estatus, final Long id) throws EmptyResultDataAccessException {
		entidadRepository.setEstatusById(estatus, id);
	}

	/**
	 * Regresa una lista de entidades por estatus
	 * 
	 * @param estatus
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<EntidadResponseDTO> findByEstatus(final Boolean estatus) throws EmptyResultDataAccessException {
		return EntidadBuilder.buildEntidadResponseDTOListFromEntidadList(entidadRepository.findByEstatus(estatus));
	}

	/**
	 * Regresa una lista de entidades por nombre
	 * 
	 * @param nombre
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<EntidadResponseDTO> findByNombre(final String nombre) throws EmptyResultDataAccessException {
		return EntidadBuilder.buildEntidadResponseDTOListFromEntidadList(entidadRepository.findByNombre(nombre));
	}

}
