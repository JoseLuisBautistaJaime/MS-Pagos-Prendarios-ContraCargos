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

import mx.com.nmp.pagos.mimonte.model.ComisionProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

/**
 * @name ComisionesProveedorRepository
 * @description Interface de tipo repository que contiene metodos para realizar consultas de las comisiones que cobra el proveedor 
 * por operacion dependiendo del tipo de proveedor-corresponsal
 * @author Quarksoft
 * @creationDate 29-10-2020
 * @version 0.1
 */
@Repository
public interface ComisionesProveedorRepository extends JpaRepository<ComisionProveedor, Long> {
	
	/**
	 * Elimina un registro de Comision Proveedor
	 * @param id
	 */
	@Modifying
	@Query("DELETE FROM ComisionProveedor cp where cp.id = :id")
	public void eliminarUnComisionProveedor(@Param("id") final Integer id);

	/**
	 * Obtiene las Comisiones(comision, iva) asociadas al tipo de Corresponsal que se le pasa como parametro
	 * @param corresponsal
	 * @return Lista ComisionProveedor
	 */
	@Query("FROM ComisionProveedor cp WHERE cp.corresponsal = :corresponsal")
	public List<ComisionProveedor> findByCorresponsal(@Param("corresponsal") final CorresponsalEnum corresponsal);

	/**
	 * Consulta un registro especifo de Comision Proveedor en base al id del registro
	 * @param id
	 * @return Lista ComisionProveedor
	 */
	public List<ComisionProveedor> findById(@Param("id") final Integer id);

}
