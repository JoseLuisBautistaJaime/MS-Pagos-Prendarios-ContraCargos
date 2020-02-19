/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoComisionEnum;

/**
 * @name MovimientoComisionBuilder
 * @description Clase que construye objetos en base a otros relacionados con
 *              movimientoComision
 * @author Jose Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 29/04/2019 19:47 hrs.
 * @version 0.1
 */
public abstract class MovimientoComisionBuilder {

	public MovimientoComisionBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo ComisionesDTO a partir de un entitie de tipo
	 * MovimientoComision
	 * 
	 * @param movimientoComision
	 * @return comisionesDTO
	 */
	public static ComisionesDTO buildComisionesDTOFromMovimientoComision(MovimientoComision movimientoComision) {
		ComisionesDTO comisionesDTO = null;
		if (movimientoComision != null) {
			comisionesDTO = new ComisionesDTO();
			comisionesDTO.setId(movimientoComision.getId());
			comisionesDTO.setFecha(movimientoComision.getFechaOperacion());
			comisionesDTO.setFechaCargo(movimientoComision.getFechaCargo());
			comisionesDTO.setMonto(movimientoComision.getMonto());
			comisionesDTO.setDescripcion(movimientoComision.getDescripcion());
		}
		return comisionesDTO;
	}

	/**
	 * Construye un set de objetos de tipo ReporteEstadoCuentaDTO a partir de un set
	 * de entities de tipo Reporte.
	 * 
	 * @param reporteSet
	 * @return reporteEstadoCuentaDTOSet
	 */
	public static List<ComisionesDTO> buildComisionesDTOListFromMovimientoComisionList(
			List<MovimientoComision> MovimientoComisionList) {
		List<ComisionesDTO> ComisionesDTOList = null;
		if (MovimientoComisionList != null && !MovimientoComisionList.isEmpty()) {
			ComisionesDTOList = new ArrayList<>();
			for (MovimientoComision mC : MovimientoComisionList) {
				ComisionesDTOList.add(buildComisionesDTOFromMovimientoComision(mC));
			}
		}
		return ComisionesDTOList;
	}


	/**
	 * Se construye movimiento comisiones a partir de la entidad estado de cuenta
	 * @param movEstadoCuenta
	 * @param idConciliacion
	 * @param tipoComision
	 * @return
	 */
	public static MovimientoComision buildMovComisionFromMovEstadoCuenta(MovimientoEstadoCuenta movEstadoCuenta, Long idConciliacion, TipoMovimientoComisionEnum tipoComision) {
		MovimientoComision movComision = new MovimientoComision();
		movComision.setDescripcion(movEstadoCuenta.getConcepto());
		movComision.setEstatus(true);
		movComision.setFechaCargo(movEstadoCuenta.getFechaValor()); // TODO: Verificar si este es el dato correcto
		movComision.setFechaOperacion(movEstadoCuenta.getFechaOperacion());
		movComision.setIdMovimientoEstadoCuenta(movEstadoCuenta.getId());
		movComision.setMonto(movEstadoCuenta.getImporte());
		movComision.setNuevo(false);
		movComision.setTipoComision(tipoComision);
		movComision.setIdConciliacion(idConciliacion);
		return movComision;
	}

}