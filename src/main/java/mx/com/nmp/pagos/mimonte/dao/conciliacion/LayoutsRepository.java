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
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;

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
	@Query("from Layout l where l.idConciliacion = :idConciliacion and l.tipo = :tipo")
	public List<Layout> findByIdConciliacionAndTipo(@Param("idConciliacion") final Long idConciliacion,
			@Param("tipo") final TipoLayoutEnum tipo);

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
	public List<Layout> findByTipo(@Param("tipo") final TipoLayoutEnum tipo);

	/**
	 * Regresa una lista de layouts por folio de conciliacion
	 * 
	 * @param idConciliacion
	 * @return
	 */
	@Query("SELECT la FROM Layout la WHERE la.idConciliacion = :idConciliacion")
	public List<Layout> checkFolioAndLayoutsRelationship(@Param("idConciliacion") final Long idConciliacion);

	/**
	 * Elimina los layouts que fueron creados a partir de movimientos generados en
	 * la conciliacion
	 * 
	 * @param idConciliacion
	 */
	@Modifying
	@Query("DELETE FROM LayoutLinea WHERE layout.id IN (SELECT id FROM Layout WHERE idConciliacion = :idConciliacion) AND nuevo = :nuevo")
	public void deleteByIdConciliacionAndNuevo(@Param("idConciliacion") Long idConciliacion,
			@Param("nuevo") boolean nuevo);

	/**
	 * Regresa un valor de 1 cuando el id de layout proporcionado si existe, de lo
	 * contrario regresa un 0
	 * 
	 * @param idLayout
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ((SELECT COUNT(l.id) FROM to_layout l WHERE l.id = :idLayout) >= (SELECT 1)) THEN 1 ELSE 0 END")
	public Object checkIfIdExist(@Param("idLayout") final Long idLayout);

	/**
	 * Regresa un valor de 1 cuando el folio de conciliacion y el id de layout
	 * proporcionados tienen relacion, de loc ontrario regresa un 0
	 * 
	 * @param folio
	 * @param idLayout
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ((SELECT COUNT(l.id) FROM to_layout l WHERE l.id = :idLayout AND l.id_conciliacion = :folio) >= (SELECT 1)) THEN 1 ELSE 0 END")
	public Object checkIfFolioIdRelationshipExist(@Param("folio") final Integer folio,
			@Param("idLayout") final Long idLayout);

	/**
	 * Regresa un valor 1 cuando el layout especificado existe y puede ser eliminado
	 * (nuevo =1), de lo contrario regresa un 0
	 * 
	 * @param folio
	 * @param idLayout
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ((SELECT COUNT(ll.id) FROM to_layout_linea ll INNER JOIN to_layout l ON ll.id_layout = l.id WHERE l.id = :idLayout AND l.id_conciliacion = :folio  AND ll.nuevo = 0) <> 0 ) THEN 0 ELSE 1 END")
	public Object checkIfLineasAreNew(@Param("folio") final Long folio, @Param("idLayout") final Long idLayout);

	/**
	 * Regresa un valor de 1 cuando el id de conciliacion y el tipo de layout son
	 * compatibles, de lo contrario regresa un 0
	 * 
	 * @param folio
	 * @param tipo
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ((SELECT COUNT(l.id) FROM to_layout l WHERE l.id_conciliacion = :folio AND l.tipo = :tipo) > (SELECT 0)) THEN 1 ELSE 0 END")
	public Object checkRightFolioAndTipoLayout(@Param("folio") final Long folio, @Param("tipo") final String tipo);

	/**
	 * Regrea un valor de 1 si todos los ids de lineas de layout pertenecen a la
	 * conciliacion especificada y que son lineas eliminables (nuevo = 1)
	 * 
	 * @param folio
	 * @param ids
	 * @param tam
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ((SELECT COUNT(ll.id) FROM to_layout_linea ll INNER JOIN to_layout l ON l.id = ll.id_layout WHERE ll.id IN :ids AND l.id_conciliacion = :folio AND ll.nuevo = 1) = :tam) THEN 1 ELSE 0 END")
	public Object checkLineasIdsAndFolioRelationship(@Param("folio") final Long folio,
			@Param("ids") final List<Long> ids, @Param("tam") final Integer tam);

}
