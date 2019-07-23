/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services;

import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import mx.com.nmp.pagos.mimonte.dto.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadResponseDTO;

/**
 * @name EntidadService
 * @description Clase de define los metodos a usar en la capa de servicios de el
 *              catalogo de entidades y que no estan en la interface de
 *              servicios genericos
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 29/03/2019 10:20 hrs.
 * @version 0.1
 */
public interface EntidadService extends CatalogoAdmService<EntidadDTO> {

	/**
	 * Regresa una lista de objetos de tipo EntidadResponseDTO por nombre y estatus
	 * 
	 * @param nombre
	 * @param estatus
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<EntidadResponseDTO> findByNombreAndEstatus(final String nombre, final Boolean estatus)
			throws EmptyResultDataAccessException;

	/**
	 * Actualiza el estatus de un catalogo de entidad por id
	 * 
	 * @param estatus
	 * @param id
	 * @param lastModifiedBy
	 * @param lastModifiedDate
	 * @throws EmptyResultDataAccessException
	 */
	public void updateEstatusById(final Boolean estatus, final Long id, String lastModifiedBy, Date lastModifiedDate)
			throws EmptyResultDataAccessException;

}
