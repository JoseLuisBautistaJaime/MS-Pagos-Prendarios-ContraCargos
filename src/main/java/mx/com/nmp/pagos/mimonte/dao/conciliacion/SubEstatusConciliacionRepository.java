/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubEstatusConciliacion;

/**
 * @name SubEstatusConciliacionRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con el sub estatus de la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 08/05/2019 21:55 hrs.
 * @version 0.1
 */
@Repository("subEstatusConciliacionRepository")
public interface SubEstatusConciliacionRepository extends JpaRepository<SubEstatusConciliacion, Long> {

	/**
	 * Búsqueda del sub estatus de la conciliación a partir de la descripcion.
	 * 
	 * @param descripcion
	 * @return
	 */
	@Query("FROM SubEstatusConciliacion sec WHERE sec.description = :descripcion")
	public SubEstatusConciliacion findByDescripcion(@Param("descripcion") final String descripcion);

	/**
	 * Se actualiza el subestatus de una conciliacion por folio de la misma
	 * 
	 * @param folio
	 * @param subEstatus
	 * @param usuario
	 * @param fecha
	 */
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE to_conciliacion SET id_sub_estatus_conciliacion = :subEstatus, id_estatus_conciliacion = :estatusConciliacion, sub_estatus_descripcion = :descripcion, last_modified_by = :usuario, last_modified_date = :fecha WHERE id = :folio")
	public void actualizaSubEstatusConciliacion(@Param("folio") final Long folio,
			@Param("subEstatus") SubEstatusConciliacion subEstatus, @Param("usuario") final String usuario,
			@Param("fecha") Date fecha, @Param("estatusConciliacion") final EstatusConciliacion estatusConciliacion,
			@Param("descripcion") final String descripcion);
	
	
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE to_conciliacion SET id_sub_estatus_conciliacion = :subEstatus, id_estatus_conciliacion = :estatusConciliacion, sub_estatus_descripcion = :descripcion, last_modified_by = :usuario, last_modified_date = :fecha WHERE id IN (:folios)")
	public void actualizaSubEstatusConciliacionMultiple(@Param("folios") final List<Long> folios,
			@Param("subEstatus") SubEstatusConciliacion subEstatus, @Param("usuario") final String usuario,
			@Param("fecha") Date fecha, @Param("estatusConciliacion") final EstatusConciliacion estatusConciliacion,
			@Param("descripcion") final String descripcion);

}
