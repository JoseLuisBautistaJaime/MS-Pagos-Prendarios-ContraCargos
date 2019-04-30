/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;

/**
 * @name MovimientosMidasRepository
 * @description Realiza operaciones a nivel base de datos relacionados con
 *              movimientos de midas
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:34:01 PM
 */
@Repository("movimientosMidasRepository")
public interface MovimientosMidasRepository extends PagingAndSortingRepository<MovimientoMidas, Long> {

	public List<MovimientoMidas> findByReporteConciliacion_Id(final Long conciliacionId, Pageable pageable);

}