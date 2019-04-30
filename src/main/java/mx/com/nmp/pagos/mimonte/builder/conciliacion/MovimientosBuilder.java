/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;

/**
 * @name MovimientosBuilder
 * @description Clase que construye objetos en base a otros relacionados con
 *              movimientos en general
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 29/04/2019 19:47 hrs.
 * @version 0.1
 */
public abstract class MovimientosBuilder {

	private MovimientosBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo MovimientoMidasDTO a partir de un entity de tipo
	 * MovimientoMidas
	 * 
	 * @param movimientoMidas
	 * @return
	 */
	public static MovimientoMidasDTO buildMovimientoMidasDTOFromMovimientoMidas(MovimientoMidas movimientoMidas) {
		MovimientoMidasDTO movimientoMidasDTO = null;
		if (null != movimientoMidas) {
			movimientoMidasDTO = new MovimientoMidasDTO();
			movimientoMidasDTO.setCapitalActual(movimientoMidas.getCapital());
			movimientoMidasDTO.setComisiones(movimientoMidas.getComisiones());
			movimientoMidasDTO.setEstatus(movimientoMidas.getEstatus());
			movimientoMidasDTO.setFecha(movimientoMidas.getFecha());
			movimientoMidasDTO.setFolioPartida(movimientoMidas.getFolio());
			movimientoMidasDTO.setId(movimientoMidas.getId());
			movimientoMidasDTO.setInteres(movimientoMidas.getInteres());
			movimientoMidasDTO.setMontoOperacion(movimientoMidas.getMonto());
			movimientoMidasDTO.setNumAutorizacion(movimientoMidas.getNumAutorizacion());
			movimientoMidasDTO.setOperacionAbr(movimientoMidas.getOperacionAbr());
			movimientoMidasDTO.setOperacionDesc(movimientoMidas.getOperacionDesc());
			movimientoMidasDTO.setSucursal(movimientoMidas.getSucursal());
			movimientoMidasDTO.setTipoContratoAbr(movimientoMidas.getTipoContratoAbr());
			movimientoMidasDTO.setTipoContratoDesc(movimientoMidas.getTipoContratoDesc());
			movimientoMidasDTO.setTransaccion(movimientoMidas.getTransaccion());
		}
		return movimientoMidasDTO;
	}

	/**
	 * Construye una lista de objetos de tipo MovimientoMidasDTO a partir de una
	 * lista de entities de tipo MovimientoMidas
	 * 
	 * @param movimientoMidasList
	 * @return
	 */
	public static List<MovimientoMidasDTO> buildMovimientoMidasDTOListFromMovimientoMidasList(
			List<MovimientoMidas> movimientoMidasList) {
		List<MovimientoMidasDTO> movimientoMidasDTOList = null;
		if (null != movimientoMidasList) {
			movimientoMidasDTOList = new ArrayList<>();
			for (MovimientoMidas movimientoMidas : movimientoMidasList)
				movimientoMidasDTOList.add(buildMovimientoMidasDTOFromMovimientoMidas(movimientoMidas));
		}
		return movimientoMidasDTOList;
	}
}
