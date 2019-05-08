/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;

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
//			movimientoMidasDTO.setEstatus(movimientoMidas.getEstatus());
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

	/**
	 * Construye un objeto de tipo MovimientoProveedorDTO a partir de un objeto de
	 * tipo MovimientoProveedor
	 * 
	 * @param movimientoProveedor
	 * @return
	 */
	public static MovimientoProveedorDTO buildMovimientoProveedorDTOFromMovimientoProveedor(
			MovimientoProveedor movimientoProveedor) {
		MovimientoProveedorDTO movimientoProveedorDTO = null;
		if (null != movimientoProveedor) {
			movimientoProveedorDTO = new MovimientoProveedorDTO();
//			movimientoProveedorDTO.setCodigoAutorizacion(movimientoProveedor.getCodigoAutorizacion());
//			movimientoProveedorDTO.setCodigoPuertaEnlace(movimientoProveedor.getCodigoPuertaEnlace());
//			movimientoProveedorDTO.setCodigoRespuesta(movimientoProveedor.getCodigoRespuesta());
//			movimientoProveedorDTO.setEntidadGestora(movimientoProveedor.getEntidadGestora());
//			movimientoProveedorDTO.setEsquemaTarjeta(movimientoProveedor.getEsquemaTarjeta());
//			movimientoProveedorDTO.setFecha(movimientoProveedor.getFecha());
//			movimientoProveedorDTO.setId(movimientoProveedor.getId());
//			movimientoProveedorDTO.setIdComerciante(movimientoProveedor.getIdComerciante());
//			movimientoProveedorDTO.setIdentificadorBanco(movimientoProveedor.getIdentificadorBanco());
//			movimientoProveedorDTO.setIdentificadorCuenta(movimientoProveedor.getIdentificadorCuenta());
//			movimientoProveedorDTO.setIdPedido(movimientoProveedor.getIdPedido());
//			movimientoProveedorDTO.setIdTransaccion(movimientoProveedor.getIdTransaccion());
//			movimientoProveedorDTO.setMetodoPago(movimientoProveedor.getMetodoPago());
//			movimientoProveedorDTO.setMoneda(movimientoProveedor.getMoneda());
//			movimientoProveedorDTO.setMonto(movimientoProveedor.getMonto());
//			movimientoProveedorDTO.setNumeroLotePago(movimientoProveedor.getNumeroLotePago());
//			movimientoProveedorDTO.setOrigenTransaccion(movimientoProveedor.getOrigenTransaccion());
//			movimientoProveedorDTO.setReciboTransaccion(movimientoProveedor.getReciboTransaccion());
//			movimientoProveedorDTO.setRecomendacionRiesgo(movimientoProveedor.getRecomendacionRiesgo());
//			movimientoProveedorDTO.setReferenciaPedido(movimientoProveedor.getReferenciaPedido());
//			movimientoProveedorDTO.setReferenciaTransaccion(movimientoProveedor.getReferenciaTransaccion());
//			movimientoProveedorDTO.setRespuesta3DS(movimientoProveedor.getRespuesta3ds());
//			movimientoProveedorDTO.setRespuestaAVS(movimientoProveedor.getRespuestaAvs());
//			movimientoProveedorDTO.setRespuestaCSC(movimientoProveedor.getRespuestaCsc());
//			movimientoProveedorDTO.setResultado(movimientoProveedor.getResultado());
//			movimientoProveedorDTO.setResultadoRevisionRiesgo(movimientoProveedor.getResultadoRevisionRiesgo());
//			movimientoProveedorDTO.setT3dsECI(movimientoProveedor.getRespuesta3ds());
//			movimientoProveedorDTO.setTipoTransaccion(movimientoProveedor.getTipoTransaccion());
//			movimientoProveedorDTO.setTitularCuenta(movimientoProveedor.getTitularCuenta());
		}
		return movimientoProveedorDTO;
	}

	/**
	 * Construye una lista de objetos de tipo MovimientoProveedorDTO a partir de una
	 * lista de objetos de tipo MovimientoProveedor
	 * 
	 * @param movimientoProveedorList
	 * @return
	 */
	public static List<MovimientoProveedorDTO> buildMovimientoProveedorDTOListFromMovimientoProveedorList(
			List<MovimientoProveedor> movimientoProveedorList) {
		List<MovimientoProveedorDTO> movimientoProveedorDTO = null;
		if (null != movimientoProveedorList) {
			movimientoProveedorDTO = new ArrayList<>();
			for (MovimientoProveedor movimientoProveedor : movimientoProveedorList) {
				movimientoProveedorDTO.add(buildMovimientoProveedorDTOFromMovimientoProveedor(movimientoProveedor));
			}
		}
		return movimientoProveedorDTO;
	}
}
