/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoPago;

/**
 * @name MovimientosPagoRepository
 * @description Clase de tipo repositorio que se encarag de realizar operaciones
 *              a nivel base de datos en objetos relacionados con la
 *              entidadMovimientoPago
 *
 * @author Quarksoft
 * @creationDate 31-Mar-2019 6:34:08 PM
 * @version 0.1
 */
@Repository("movimientosPagoRepository")
public interface MovimientosPagoRepository extends JpaRepository<MovimientoPago, Integer> {
}