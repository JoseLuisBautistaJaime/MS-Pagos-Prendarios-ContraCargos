/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.com.nmp.pagos.mimonte.model.EstatusBonificacion;

/**
 * @name EstatusBonificacionRepository
 * @description Interface de capa DAO que sirve para consultar estatus de bonificaciones
 *
 * @author Quarksoft
 * @creationDate unknown hrs.
 * @version 0.1
 */
@Repository("estatusBonificacionRepository")
public interface EstatusBonificacionRepository extends JpaRepository<EstatusBonificacion, Integer> {

}
