/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMovimientosProveedorRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorBatchDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;

/**
 * @name MovimientosProveedorService
 * @description Clase service que realiza operaciones con movimientos
 *              relacionados con movimientos de proveedor
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:38 PM
 */
@Service("movimientosProveedorService")
public class MovimientosProveedorService {

	public MovimientosProveedorService() {
		super();
	}

	/**
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 */
	public List<MovimientoProveedorDTO> listByDates(Date fechaDesde, Date fechaHasta) {
		return null;
	}

	/**
	 * 
	 * @param idConciliacion
	 */
	public Integer countByConciliacion(Long idConciliacion) {
		return 0;
	}

	/**
	 * 
	 * @param idConciliacion
	 * @param page
	 * @param pageSize
	 */
	public List<MovimientoProveedorDTO> listByConciliacion(Long idConciliacion, Integer page, Integer pageSize) {
		return null;
	}

	/**
	 * 
	 * @param criteria
	 * @param page
	 * @param pageSize
	 */
	public List<MovimientoProveedorDTO> search(ConsultaMovimientosProveedorRequestDTO criteria, Integer page,
			Integer pageSize) {
		return null;
	}

	/**
	 * 
	 * @param criterios
	 */
	public Integer count(ConsultaMovimientosProveedorRequestDTO criterios) {
		return 0;
	}

	/**
	 * 
	 * @param lista
	 */
	public void saveBatch(List<MovimientoProveedorBatchDTO> lista) {

	}

}