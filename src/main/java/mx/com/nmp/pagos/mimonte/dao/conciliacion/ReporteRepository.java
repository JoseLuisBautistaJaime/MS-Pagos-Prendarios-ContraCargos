/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;

/**
 * @name ReporteRepository
 * @description Interface que define los metodos para realizar operaciones a
 *              nivel base de datos relacionada con Reporte
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @created 06/05/2019 13:48 hrs.
 */
@Repository("reporteRepository")
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
}
