/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Layout;

/**
 * @name LayoutsRepository
 * @description Interface de tipo repository que contiene metodos para realizar
 *              operaciones a nivel de base de datos sobre objetos relacionados
 *              con layouts.
 *
 * @author Quarksoft
 * @creationDate 23-05-2019
 * @version 0.1
 */
@Repository
public interface LayoutsRepository extends JpaRepository<Layout, Long> {
	/**
	 * Elimina la línea de un layout
	 * 
	 * @param id
	 */
	@Modifying
	@Query("delete from LayoutLinea l where l.layout.id = :id")
	public void eliminarUnLayoutLineas(@Param("id") final Long id);

	/**
	 * Elimina el header de un layout
	 * 
	 * @param id
	 */
	@Modifying
	@Query("delete from LayoutHeader l where l.layout.id = :id")
	public void eliminarUnLayoutHeader(@Param("id") final Long id);

	/**
	 * Obtiene un layout
	 * 
	 * @param idConciliacion
	 * @param tipo
	 * @return
	 */
	@Query("from Layout l inner join l.layoutHeader inner join l.layoutLineas where l.idConciliacion = :idConciliacion and l.tipo = :tipo")
	public List<Object[]> findByIdConciliacionAndTipo(@Param("idConciliacion") final Long idConciliacion,
			@Param("tipo") final String tipo);

	/**
	 * Verifica si existe la conciliación en los layouts
	 * 
	 * @param idConciliacion
	 * @return
	 */
	public List<Layout> findByIdConciliacion(@Param("idConciliacion") final Long idConciliacion);

	/**
	 * Obtiene los tipos de layouts
	 * 
	 * @param tipo
	 * @return
	 */
	public List<Layout> findByTipo(@Param("tipo") final String tipo);

	/**
	 * Regresa una lista de layouts por folio de conciliacion
	 * @param idConciliacion
	 * @return
	 */
	@Query("SELECT la FROM Layout la WHERE la.idConciliacion = :idConciliacion")
	public List<Layout> checkFolioAndLayoutsRelationship(@Param("idConciliacion") final Long idConciliacion);
	
}
