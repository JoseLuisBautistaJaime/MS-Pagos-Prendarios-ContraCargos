/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDBDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;

/**
 * @name MovimientoEstadoCuentaRepository
 * 
 * @description Realiza operaciones a nivel base de datos a objetos relacionados
 *              con movimientos de estados de cuenta
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:34:04 PM
 */
@Repository("movimientoEstadoCuentaRepository")
public interface MovimientoEstadoCuentaRepository extends PagingAndSortingRepository<MovimientoEstadoCuenta, Long> {

	/**
	 * Cuenta los movimientos de estado cuenta relacionados con un folio de
	 * conciliacion especifico
	 * 
	 * @param folioConciliacion
	 * @return
	 */
	public Long jpqlCBIC(@Param("folioConciliacion") final Long folioConciliacion);

	/**
	 * Regresa una lista de objetos de tipo MovimientoEstadoCuentaDBDTO relacionados
	 * con un folio de conciliacion, ademas de regresar la lista en base a una
	 * paginacion especifica
	 * 
	 * @param folioConciliacion
	 * @param pageable
	 * @return
	 */
	public List<MovimientoEstadoCuentaDBDTO> jpqlFBIC(
			@Param("folioConciliacion") final Long folioConciliacion, Pageable pageable);

}