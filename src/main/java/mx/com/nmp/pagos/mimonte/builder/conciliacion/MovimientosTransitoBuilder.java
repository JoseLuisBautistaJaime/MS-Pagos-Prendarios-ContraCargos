/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoDTO;
import mx.com.nmp.pagos.mimonte.model.EstatusDevolucion;
import mx.com.nmp.pagos.mimonte.model.EstatusTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
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
			movTransitoDTO.setNumAutorizacion(movimientoTransito.getNumAutorizacion());
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
	
	public static DevolucionConDTO buildDevolucionConDTOFromMovimientoTransito(MovimientoTransito movTransito, EstatusDevolucion estatusDevolucion) {
		DevolucionConDTO devolucionConDTO = null;
		if(movTransito != null) {
			devolucionConDTO = new DevolucionConDTO();
			devolucionConDTO.setId(movTransito.getId());
			devolucionConDTO.setFecha(movTransito.getFecha());
			devolucionConDTO.setEstatus(EstatusDevolucionesBuilder.buildEstatusDevolucionDTOFromEstatusDevolucion(estatusDevolucion));
			devolucionConDTO.setMonto(movTransito.getMonto());
			devolucionConDTO.setEsquemaTarjeta(movTransito.getEsquemaTarjeta());
			devolucionConDTO.setIdentificacionCuenta(movTransito.getCuenta());
			devolucionConDTO.setTitular(movTransito.getTitular());
			devolucionConDTO.setCodigoAutorizacion(movTransito.getNumAutorizacion());
			devolucionConDTO.setSucursal(movTransito.getSucursal());
			devolucionConDTO.setFechaLiquidacion(null);
			
			
		}
		return devolucionConDTO;
	}
	
	public static List<DevolucionConDTO> buildDevolucionConDTOListFromMovimientoTransitoList(
			List<MovimientoTransito> movimientoTransito, EstatusDevolucion estatusDevolucion) {
		List<DevolucionConDTO> devolucionConDTOList = null;
		if (movimientoTransito != null && !movimientoTransito.isEmpty()) {
			devolucionConDTOList = new ArrayList<>();
			for (MovimientoTransito mt : movimientoTransito) {
				devolucionConDTOList.add(buildDevolucionConDTOFromMovimientoTransito(mt, estatusDevolucion));
			}

		}
		return devolucionConDTOList;
	}

	public static MovimientoTransito buildMovTransitoFromMovProveedor(MovimientoProveedor movProveedor, Integer idConciliacion) {
		MovimientoTransito movTransito = new MovimientoTransito();
		movTransito.setCreatedBy(ConciliacionConstants.USER_SYSTEM);
		movTransito.setCreatedDate(new Date());
		movTransito.setFolio(null); // TODO: No folio
		movTransito.setSucursal(null); // TODO: Sucursal
		movTransito.setFecha(movProveedor.getOperationDate());
		movTransito.setOperacionDesc(null); // TODO: Operacion date
		movTransito.setMonto(movProveedor.getAmount());
		movTransito.setTipoContratoDesc(null); // TODO: Tipo contrato
		movTransito.setEsquemaTarjeta(movProveedor.getTarjetaMovimientosProveedor().getBrand());
		movTransito.setCuenta(movProveedor.getTarjetaMovimientosProveedor().getCardNumber());
		movTransito.setTitular(movProveedor.getTarjetaMovimientosProveedor().getHolderName());
		movTransito.setNumAutorizacion(movProveedor.getAuthorization());
		movTransito.setMovimientoMidas(null); // TODO: No relacion
		movTransito.setNuevo(false);
		movTransito.setEstatus(new EstatusTransito(ConciliacionConstants.ESTATUS_TRANSITO_NO_IDENTIFICADO_OPEN_PAY.intValue()));
		movTransito.setIdConciliacion(idConciliacion);

		return movTransito;
	}


	/**
	 * Crea un movimiento transito a partir de un movimiento midas
	 * @param movMidas
	 * @param idConciliacion
	 * @return
	 */
	public static MovimientoTransito buildMovTransitoFromMovMidas(MovimientoMidas movMidas, MovimientoProveedor movProveedor, Integer idConciliacion) {
		MovimientoTransito movTransito = new MovimientoTransito();
		movTransito.setCreatedBy(ConciliacionConstants.USER_SYSTEM);
		movTransito.setCreatedDate(new Date());
		movTransito.setFolio(movMidas.getFolio() != null ? movMidas.getFolio().intValue() : null);
		movTransito.setSucursal(movMidas.getSucursal());
		movTransito.setFecha(movMidas.getFecha());
		movTransito.setOperacionDesc(movMidas.getOperacionDesc());
		movTransito.setMonto(movMidas.getMonto());
		movTransito.setTipoContratoDesc(movMidas.getTipoContratoDesc());
		movTransito.setEsquemaTarjeta(movMidas.getMarcaTarjeta());
		movTransito.setCuenta(movMidas.getTarjeta());
		movTransito.setTitular(movProveedor != null && movProveedor.getTarjetaMovimientosProveedor() != null ? movProveedor.getTarjetaMovimientosProveedor().getHolderName() : null);
		movTransito.setNumAutorizacion(movMidas.getNumAutorizacion());
		movTransito.setMovimientoMidas(movMidas);
		movTransito.setNuevo(false);
		movTransito.setEstatus(new EstatusTransito(ConciliacionConstants.ESTATUS_TRANSITO_NO_IDENTIFICADO_MIDAS.intValue()));
		movTransito.setIdConciliacion(idConciliacion);
		return movTransito;
	}

}
