/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutEditionValidationDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLinea;

/**
 * @name LayoutLineasRepository
 * @description Interface de tipo repository que contiene metodos para realizar
 *              operaciones a nivel de base de datos sobre objetos relacionados
 *              con LayoutLineas.
 *
 * @author Quarksoft
 * @creationDate 23-05-2019
 * @version 0.1
 */
@Repository
public interface LayoutLineasRepository extends JpaRepository<LayoutLinea, Long> {

	/**
	 * Regresa una lista de objetos de tipo LayoutEditionValidationDTO con los ids
	 * solicitados
	 * 
	 * @param ids
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutEditionValidationDTO(ll.id, ll.createdBy) FROM LayoutLinea ll WHERE ll.id IN(:ids)")
	public List<LayoutEditionValidationDTO> getIdsDataValidateCreateOrUpdate(final List<Long> ids);

}
