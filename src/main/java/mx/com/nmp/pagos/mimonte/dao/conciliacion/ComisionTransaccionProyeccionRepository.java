/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccionProyeccion;

/**
 * @name ComisionTransaccionProyeccionRepository
 * @description Interface de tipo repsotorio de spring que realiza operaciones a
 *              nivel base de datos sobre la entidad
 *              ComisionTransaccionProyeccion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 30/05/2019 14:35 hrs.
 * @version 0.1
 */
@Repository("comisionTransaccionProyeccionRepository")
public interface ComisionTransaccionProyeccionRepository extends JpaRepository<ComisionTransaccionProyeccion, Long> {
}
