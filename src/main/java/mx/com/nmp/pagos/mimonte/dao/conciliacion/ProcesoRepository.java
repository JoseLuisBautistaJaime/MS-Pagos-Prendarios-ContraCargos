/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CatalogoProceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @name ProcesoRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con los procesos de automatización.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 19/11/2021 16:56 hrs.
 * @version 0.1
 */
@Repository("procesoRepository")
public interface ProcesoRepository extends JpaRepository<CatalogoProceso, Integer>{

	/**
	 * Búsqueda del proceso de automatización a partir de la descripción.
	 * @param descripcion
	 * @return
	 */
	@Query("FROM CatalogoProceso cp WHERE cp.descripcion = :descripcion")
	public CatalogoProceso findByDescripcion(@Param("descripcion") final String descripcion);

}
