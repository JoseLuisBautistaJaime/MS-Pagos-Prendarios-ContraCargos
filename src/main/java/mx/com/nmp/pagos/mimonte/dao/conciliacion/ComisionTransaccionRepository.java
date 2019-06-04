/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccion;

/**
 * @name ComisionTransaccionRepository
 * @description Interface que realiza operaciones a nivel de base de datos sobre
 *              la entidad ComisionTransaccion y entidades relacionadas
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 29/05/2019 14:38 hrs.
 * @version 0.1
 */
@Repository("comisionTransaccionRepository")
public interface ComisionTransaccionRepository extends JpaRepository<ComisionTransaccion, Long> {

	/**
	 * Encuentra una comision transaccion por id de conciliacion
	 * 
	 * @param conciliacionId
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT ct.id FROM to_comision_transaccion ct WHERE ct.id_conciliacion = :conciliacionId")
	public List<Integer> findByConciliacionId(@Param("conciliacionId") Integer conciliacionId);

}
