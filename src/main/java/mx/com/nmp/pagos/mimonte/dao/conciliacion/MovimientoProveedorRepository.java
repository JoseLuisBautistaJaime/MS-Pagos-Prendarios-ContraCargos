/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;

/**
 * @name MovimientoProveedorRepository
 * @description Realiza operaciones a nivel base de datos relacionados con
 *              movimientos de proveedor
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:34:02 PM
 */
@Repository("movimientoProveedorRepository")
public interface MovimientoProveedorRepository extends PagingAndSortingRepository<MovimientoProveedor, Long> {
}