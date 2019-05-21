/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;

/**
 * @name MovimientosTransitoBuilder
 * @description Clase que construye objetos en base a otros relacionados con
 *              global
 * @author Jose Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 29/04/2019 19:47 hrs.
 * @version 0.1
 */
public abstract class MovimientosTransitoBuilder {

	public MovimientosTransitoBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo MovTransitoDTO a partir de un entitie de tipo
	 * MovimientoTransito.
	 * 
	 * @param movimientoTransito
	 * @return movTransitoDTO
	 */
	public static MovTransitoDTO buildMovTransitoDTOFromMovimientoTransito(MovimientoTransito movimientoTransito) {
		MovTransitoDTO movTransitoDTO = null;
		if (movimientoTransito != null) {
			movTransitoDTO = new MovTransitoDTO();
			movTransitoDTO.setId(movimientoTransito.getId());
			movTransitoDTO.setEstatus(EstatusMovimientoTransitoBuilder
					.buildEstatusMovTransitoDTOFromEstatusTransito(movimientoTransito.getEstatus()));
			movTransitoDTO.setFolio(movimientoTransito.getFolio());
			movTransitoDTO.setSucursal(movimientoTransito.getSucursal());
			movTransitoDTO.setFecha(movimientoTransito.getFecha());
			movTransitoDTO.setOperacion(movimientoTransito.getOperacionDesc());
			movTransitoDTO.setMonto(movimientoTransito.getMonto());
			movTransitoDTO.setTipoContrato(movimientoTransito.getTipoContratoDesc());
			movTransitoDTO.setEsquemaTarjeta(movimientoTransito.getEsquemaTarjeta());
			movTransitoDTO.setCuenta(movimientoTransito.getCuenta());
			movTransitoDTO.setTitularCuenta(movimientoTransito.getTitular());
		}
		return movTransitoDTO;
	}

	/**
	 * Construye un set de objetos de tipo ReporteEstadoCuentaDTO a partir de un set
	 * de entities de tipo Reporte.
	 * 
	 * @param reporteSet
	 * @return reporteEstadoCuentaDTOSet
	 */
	public static List<MovTransitoDTO> buildMovTransitoDTOListFromMovimientoTransitoList(
			List<MovimientoTransito> movimientoTransitoList) {
		List<MovTransitoDTO> movTransitoDTOList = null;
		if (movimientoTransitoList != null && !movimientoTransitoList.isEmpty()) {
			movTransitoDTOList = new ArrayList<>();
			for (MovimientoTransito mT : movimientoTransitoList) {
				movTransitoDTOList.add(buildMovTransitoDTOFromMovimientoTransito(mT));
			}
		}
		return movTransitoDTOList;
	}
}
