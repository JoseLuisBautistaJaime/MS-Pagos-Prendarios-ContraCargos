/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants.TipoMovimiento;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MetodoPagoMovimientosProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDBDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoPagoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.TarjetaMovimientosProveedorDTO;
import mx.com.nmp.pagos.mimonte.model.EstatusPago;
import mx.com.nmp.pagos.mimonte.model.conciliacion.IMovTransaccion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MetodoPagoMovimientosProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoPago;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TarjetaMovimientosProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoEnum;
import mx.com.nmp.pagos.mimonte.util.CollectionUtil;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	/**
	 * Utilizada para administrar los mensajes informativos y de error.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(MovimientosBuilder.class);

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
			movimientoMidasDTO.setEstadoTransaccion(movimientoMidas.getEstadoTransaccion());
			movimientoMidasDTO.setConsumidor(movimientoMidas.getConsumidor());
			movimientoMidasDTO.setImporteTransaccion(movimientoMidas.getImporteTransaccion());
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

		LOG.debug(">> buildMovimientoProveedorDTOFromMovimientoProveedor(" +
				movimientoProveedor.toString() + ")");
		MovimientoProveedorDTO movimientoProveedorDTO = null;
		if (null != movimientoProveedor) {
			movimientoProveedorDTO = new MovimientoProveedorDTO();
			movimientoProveedorDTO.setPaymentMethod(new MetodoPagoMovimientosProveedorDTO());
			movimientoProveedorDTO.setCard(new TarjetaMovimientosProveedorDTO());
			movimientoProveedorDTO.setAmount(movimientoProveedor.getAmount());
			movimientoProveedorDTO.setAuthorization(movimientoProveedor.getAuthorization());
			movimientoProveedorDTO.getCard()
					.setAddress(movimientoProveedor.getTarjetaMovimientosProveedor().getAddress());
			movimientoProveedorDTO.getCard()
					.setAllowsCharges(movimientoProveedor.getTarjetaMovimientosProveedor().getAllowsCharges());
			movimientoProveedorDTO.getCard()
					.setAllowsPayouts(movimientoProveedor.getTarjetaMovimientosProveedor().getAllowsPayouts());
			movimientoProveedorDTO.getCard()
					.setBankCode(movimientoProveedor.getTarjetaMovimientosProveedor().getBankCode());
			movimientoProveedorDTO.getCard()
					.setBankName(movimientoProveedor.getTarjetaMovimientosProveedor().getBankName());
			movimientoProveedorDTO.getCard().setBrand(movimientoProveedor.getTarjetaMovimientosProveedor().getBrand());
			movimientoProveedorDTO.getCard()
					.setCreationDate(movimientoProveedor.getTarjetaMovimientosProveedor().getCreationDate());
			movimientoProveedorDTO.getCard()
					.setCustomerId(movimientoProveedor.getTarjetaMovimientosProveedor().getCustomerId());
			movimientoProveedorDTO.getCard()
					.setExpirationMonth(movimientoProveedor.getTarjetaMovimientosProveedor().getExpirationMonth());
			movimientoProveedorDTO.getCard()
					.setExpirationYear(movimientoProveedor.getTarjetaMovimientosProveedor().getExpirationYear());
			movimientoProveedorDTO.getCard()
					.setHolderName(movimientoProveedor.getTarjetaMovimientosProveedor().getHolderName());
			movimientoProveedorDTO.getCard().setId(movimientoProveedor.getTarjetaMovimientosProveedor().getId());
			movimientoProveedorDTO.getCard()
					.setCardNumber(movimientoProveedor.getTarjetaMovimientosProveedor().getCardNumber());
			movimientoProveedorDTO.getCard().setType(movimientoProveedor.getTarjetaMovimientosProveedor().getType());
			movimientoProveedorDTO.setConciliated(movimientoProveedor.getConciliated());
			movimientoProveedorDTO.setCreationDate(movimientoProveedor.getCreationDate());
			movimientoProveedorDTO.setCurrency(movimientoProveedor.getCurrency());
			movimientoProveedorDTO.setCustomerId(movimientoProveedor.getCustomerId());
			movimientoProveedorDTO.setDescription(movimientoProveedor.getDescription());
			movimientoProveedorDTO.setErrorCode(movimientoProveedor.getErrorCode());
			movimientoProveedorDTO.setErrorMessage(movimientoProveedor.getErrorMessage());
			movimientoProveedorDTO.setIdMovimiento(movimientoProveedor.getIdMovimiento());
			movimientoProveedorDTO.setId(movimientoProveedor.getId());
			movimientoProveedorDTO.setMethod(movimientoProveedor.getMethod());

			if (movimientoProveedor.getMetodoPagoMovimientosProveedor() != null) {
				movimientoProveedorDTO.getPaymentMethod().setType(movimientoProveedor.getMetodoPagoMovimientosProveedor().getType());
				movimientoProveedorDTO.getPaymentMethod().setUrl(movimientoProveedor.getMetodoPagoMovimientosProveedor().getUrl());
			} else {
				movimientoProveedorDTO.getPaymentMethod().setType(null);
				movimientoProveedorDTO.getPaymentMethod().setUrl(null);
			}

			movimientoProveedorDTO.setOperationDate(movimientoProveedor.getOperationDate());
			movimientoProveedorDTO.setOperationType(movimientoProveedor.getOperationType());
			movimientoProveedorDTO.setOrderId(movimientoProveedor.getOrderId());
			movimientoProveedorDTO.setStatus(movimientoProveedor.getStatus());
			movimientoProveedorDTO.setTransactionType(movimientoProveedor.getTransactionType());
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

	/**
	 * Construye un entity de tipo MovimientoMidas a partir de un objeto de tipo
	 * MovimientoMidasRequestDTO
	 * 
	 * @param movimientoMidasDTO
	 * @param idReporte
	 * @return
	 */
	public static MovimientoMidas buildMovimientoMidasFromMovimientoMidasDTO(
			MovimientoMidasRequestDTO movimientoMidasDTO, final Long idReporte) {
		MovimientoMidas movimientoMidas = new MovimientoMidas();
		movimientoMidas.setId(null);
		movimientoMidas.setReporte(idReporte);
		movimientoMidas.setConsumidor(movimientoMidasDTO.getConsumidor());
		movimientoMidas.setEstadoTransaccion(movimientoMidasDTO.getEstadoTransaccion());
		movimientoMidas.setCapital(movimientoMidasDTO.getCapitalActual());
		movimientoMidas.setComisiones(movimientoMidasDTO.getComisiones());
		movimientoMidas.setEstatus(movimientoMidasDTO.getEstatus());
		movimientoMidas.setFecha(movimientoMidasDTO.getFecha());
		movimientoMidas.setFolio(movimientoMidasDTO.getFolioPartida());
		movimientoMidas.setInteres(movimientoMidasDTO.getInteres());
		movimientoMidas.setMonto(movimientoMidasDTO.getMontoOperacion());
		movimientoMidas.setNumAutorizacion(movimientoMidasDTO.getNumAutorizacion());
		movimientoMidas.setOperacionAbr(movimientoMidasDTO.getOperacionAbr());
		movimientoMidas.setOperacionDesc(movimientoMidasDTO.getOperacionDesc());
		movimientoMidas.setSucursal(movimientoMidasDTO.getSucursal());
		movimientoMidas.setTipoContratoAbr(movimientoMidasDTO.getTipoContratoAbr());
		movimientoMidas.setTipoContratoDesc(movimientoMidasDTO.getTipoContratoDesc());
		movimientoMidas.setTransaccion(movimientoMidasDTO.getTransaccion());
		movimientoMidas.setImporteTransaccion(movimientoMidasDTO.getImporteTransaccion());
		movimientoMidas.setIdOperacion(movimientoMidasDTO.getIdOperacion());
		movimientoMidas.setIdTipoContrato(movimientoMidasDTO.getIdTipoContrato());
		movimientoMidas.setCodigoError(movimientoMidasDTO.getCodigoError());
		movimientoMidas.setMensajeError(movimientoMidasDTO.getMensajeError());
		movimientoMidas.setIdTarjeta(movimientoMidasDTO.getIdTarjeta());
		movimientoMidas.setMarcaTarjeta(movimientoMidasDTO.getMarcaTarjeta());
		movimientoMidas.setTipoTarjeta(movimientoMidasDTO.getTipoTarjeta());
		movimientoMidas.setTarjeta(movimientoMidasDTO.getTarjeta());
		movimientoMidas.setMonedaPago(movimientoMidasDTO.getMonedaPago());
		return movimientoMidas;
	}

	/**
	 * Cosntruye una lista de entities de tipo MovimientoMidas a partir de un objeto
	 * de tipo MovimientoProcesosNocturnosListResponseDTO
	 * 
	 * @param movimientoProcesosNocturnosListResponseDTO
	 * @return
	 */
	public static List<MovimientoMidas> buildMovimientoMidasListFromMovimientoProcesosNocturnosListResponseDTO(
			MovimientoProcesosNocturnosListResponseDTO movimientoProcesosNocturnosListResponseDTO,
			final long idReporte) {
		List<MovimientoMidas> movimientoMidasList = null;
		if (null != movimientoProcesosNocturnosListResponseDTO
				&& null != movimientoProcesosNocturnosListResponseDTO.getFolio()
				&& null != movimientoProcesosNocturnosListResponseDTO.getMovimientos()
				&& !movimientoProcesosNocturnosListResponseDTO.getMovimientos().isEmpty()) {
			movimientoMidasList = new ArrayList<>();
			for (MovimientoMidasRequestDTO movimientoMidasRequestDTO : movimientoProcesosNocturnosListResponseDTO
					.getMovimientos()) {
				movimientoMidasList
						.add(buildMovimientoMidasFromMovimientoMidasDTO(movimientoMidasRequestDTO, idReporte));
			}
		}
		return movimientoMidasList;
	}

	/**
	 * Construyen un entity de tipo MovimientoProveedor apartir de un objeto de tipo
	 * MovimientoProveedorDTO
	 * 
	 * @param movimientoProveedorDTO
	 * @return
	 */
	public static MovimientoProveedor buildMovimientoProveedorFromMovimientoProveedorDTO(
			MovimientoProveedorDTO movimientoProveedorDTO, final int idReporte) {
		MovimientoProveedor movimientoProveedor = new MovimientoProveedor();
		movimientoProveedor.setId(null);
		movimientoProveedor.setReporte(idReporte);
		movimientoProveedor.setAmount(movimientoProveedorDTO.getAmount());
		movimientoProveedor.setAuthorization(movimientoProveedorDTO.getAuthorization());
		movimientoProveedor.setConciliated(movimientoProveedorDTO.getConciliated());
		movimientoProveedor.setCreationDate(movimientoProveedorDTO.getCreationDate());
		movimientoProveedor.setCurrency(movimientoProveedorDTO.getCurrency());
		movimientoProveedor.setCustomerId(movimientoProveedorDTO.getCustomerId());
		movimientoProveedor.setDescription(movimientoProveedorDTO.getDescription());
		movimientoProveedor.setErrorCode(movimientoProveedorDTO.getErrorCode());
		movimientoProveedor.setErrorMessage(movimientoProveedorDTO.getErrorMessage());
		movimientoProveedor.setIdMovimiento(movimientoProveedorDTO.getIdMovimiento());
		movimientoProveedor.setMethod(movimientoProveedorDTO.getMethod());
		movimientoProveedor.setOperationDate(movimientoProveedorDTO.getOperationDate());
		movimientoProveedor.setOperationType(movimientoProveedorDTO.getOperationType());
		movimientoProveedor.setOrderId(movimientoProveedorDTO.getOrderId());
		movimientoProveedor.setStatus(movimientoProveedorDTO.getStatus());
		movimientoProveedor.setTransactionType(movimientoProveedorDTO.getTransactionType());

		if (movimientoProveedorDTO.getPaymentMethod() != null) {
			MetodoPagoMovimientosProveedor metodoPagoMovimientosProveedor = new MetodoPagoMovimientosProveedor();
			metodoPagoMovimientosProveedor.setType(movimientoProveedorDTO.getPaymentMethod().getType());
			metodoPagoMovimientosProveedor.setUrl(movimientoProveedorDTO.getPaymentMethod().getUrl());
			movimientoProveedor.setMetodoPagoMovimientosProveedor(metodoPagoMovimientosProveedor);
		}

		if (movimientoProveedorDTO.getCard() != null) {
			TarjetaMovimientosProveedor tarjetaMovimientosProveedor = new TarjetaMovimientosProveedor();
			tarjetaMovimientosProveedor.setAddress(movimientoProveedorDTO.getCard().getAddress());
			tarjetaMovimientosProveedor.setAllowsCharges(movimientoProveedorDTO.getCard().getAllowsCharges());
			tarjetaMovimientosProveedor.setAllowsPayouts(movimientoProveedorDTO.getCard().getAllowsPayouts());
			tarjetaMovimientosProveedor.setBankCode(movimientoProveedorDTO.getCard().getBankCode());
			tarjetaMovimientosProveedor.setBankName(movimientoProveedorDTO.getCard().getBankName());
			tarjetaMovimientosProveedor.setBrand(movimientoProveedorDTO.getCard().getBrand());
			tarjetaMovimientosProveedor.setCardNumber(movimientoProveedorDTO.getCard().getCardNumber());
			tarjetaMovimientosProveedor.setCreationDate(movimientoProveedorDTO.getCard().getCreationDate());
			tarjetaMovimientosProveedor.setCustomerId(movimientoProveedorDTO.getCard().getCustomerId());
			tarjetaMovimientosProveedor.setExpirationMonth(movimientoProveedorDTO.getCard().getExpirationMonth());
			tarjetaMovimientosProveedor.setExpirationYear(movimientoProveedorDTO.getCard().getExpirationYear());
			tarjetaMovimientosProveedor.setHolderName(movimientoProveedorDTO.getCard().getHolderName());
			tarjetaMovimientosProveedor.setId(movimientoProveedorDTO.getCard().getId());
			tarjetaMovimientosProveedor.setType(movimientoProveedorDTO.getCard().getType());
			movimientoProveedor.setTarjetaMovimientosProveedor(tarjetaMovimientosProveedor);
		}

		return movimientoProveedor;
	}

	/**
	 * Construye una lista de entities de tipo MovimientoProveedor a partir de un
	 * objeto de tipo MovimientoTransaccionalListRequestDTO
	 * 
	 * @param movimientoTransaccionalListRequestDTO
	 * @return
	 */
	public static List<MovimientoProveedor> buildMovimientoProveedorListFromMovimientoTransaccionalListRequestDTO(
			MovimientoTransaccionalListRequestDTO movimientoTransaccionalListRequestDTO, final int idReporte) {
		List<MovimientoProveedor> movimientoProveedorList = null;
		MovimientoProveedor movimientoProveedor = null;
		if (null != movimientoTransaccionalListRequestDTO && null != movimientoTransaccionalListRequestDTO.getFolio()
				&& null != movimientoTransaccionalListRequestDTO.getMovimientos()
				&& !movimientoTransaccionalListRequestDTO.getMovimientos().isEmpty()) {
			movimientoProveedorList = new ArrayList<>();
			for (MovimientoProveedorDTO movimientoProveedorDTO : movimientoTransaccionalListRequestDTO
					.getMovimientos()) {
				movimientoProveedor = buildMovimientoProveedorFromMovimientoProveedorDTO(movimientoProveedorDTO,
						idReporte);
				movimientoProveedorList.add(movimientoProveedor);
			}
		}
		return movimientoProveedorList;
	}

	/**
	 * Construye un objeto de tipo MovimientoEstadoCuentaDTO a partir de un objeto
	 * de tipo MovimientoEstadoCuentaDBDTO
	 * 
	 * @param movimientoEstadoCuentaDBDTO
	 * @return
	 */
	public static MovimientoEstadoCuentaDTO buildMovimientoEstadoCuentaDTOFromMovimientoEstadoCuentaDBDTO(
			MovimientoEstadoCuentaDBDTO movimientoEstadoCuentaDBDTO, final int pos, final int fin) {
		MovimientoEstadoCuentaDTO movimientoEstadoCuentaDTO = null;
		if (null != movimientoEstadoCuentaDBDTO) {
			movimientoEstadoCuentaDTO = new MovimientoEstadoCuentaDTO();
			movimientoEstadoCuentaDTO.setDepositos(movimientoEstadoCuentaDBDTO.getTipoMovimiento().equals(
					ConciliacionConstants.TipoMovimiento.TIPO_ABONO) ? movimientoEstadoCuentaDBDTO.getImporte()
							: null);
			movimientoEstadoCuentaDTO.setDescripcion(movimientoEstadoCuentaDBDTO.getDescripcion());
			movimientoEstadoCuentaDTO.setFecha(movimientoEstadoCuentaDBDTO.getFecha());
			movimientoEstadoCuentaDTO.setId(movimientoEstadoCuentaDBDTO.getId());
			movimientoEstadoCuentaDTO.setRetiros(movimientoEstadoCuentaDBDTO.getTipoMovimiento()
					.equals(ConciliacionConstants.TipoMovimiento.TIPO_CARGO) ? movimientoEstadoCuentaDBDTO.getImporte()
							: null);
			if (pos == 0)
				movimientoEstadoCuentaDTO.setSaldo(movimientoEstadoCuentaDBDTO.getTotalInicial());
			if (pos == fin)
				movimientoEstadoCuentaDTO.setSaldo(movimientoEstadoCuentaDBDTO.getTotalFinal());
		}
		return movimientoEstadoCuentaDTO;
	}

	/**
	 * Construye una lista de objetos de tipo MovimientoEstadoCuentaDTO a partir de
	 * una lista de objetos de tipo MovimientoEstadoCuentaDBDTO
	 * 
	 * @param movimientoEstadoCuentaDBDTOList
	 * @return
	 */
	public static List<MovimientoEstadoCuentaDTO> buildMovimientoEstadoCuentaDTOListFromMovimientoEstadoCuentaDBDTOList(
			List<MovimientoEstadoCuentaDBDTO> movimientoEstadoCuentaDBDTOList) {
		List<MovimientoEstadoCuentaDTO> movimientoEstadoCuentaDTOList = null;
		int pos, fin;
		pos = 0;
		if (null != movimientoEstadoCuentaDBDTOList && !movimientoEstadoCuentaDBDTOList.isEmpty()) {
			fin = movimientoEstadoCuentaDBDTOList.size() - 1;
			movimientoEstadoCuentaDTOList = new ArrayList<>();
			for (MovimientoEstadoCuentaDBDTO movimientoEstadoCuentaDBDTO : movimientoEstadoCuentaDBDTOList) {
				movimientoEstadoCuentaDTOList.add(buildMovimientoEstadoCuentaDTOFromMovimientoEstadoCuentaDBDTO(
						movimientoEstadoCuentaDBDTO, pos, fin));
				pos++;
			}
		}
		return movimientoEstadoCuentaDTOList;
	}

	/**
	 * Construye un entity de tipo MovimientoPago a partir de un entity de tipo
	 * MovimientoConciliacion
	 * 
	 * @param mc
	 * @return
	 */
	public static MovimientoPago buildMovimientoPagoFromMovimientoConciliacion(MovimientoConciliacion mc,
			final String createdBy) {
		MovimientoPago movimientoPago = null;
		if (null != mc) {
			movimientoPago = new MovimientoPago();
			movimientoPago.setCreatedBy(createdBy);
			movimientoPago.setCreatedDate(new Date());
			movimientoPago.setEstatus(new EstatusPago(1));
			movimientoPago.setId(0);
			movimientoPago.setIdConciliacion(mc.getIdConciliacion());
			movimientoPago.setLastModifiedBy(null);
			movimientoPago.setLastModifiedDate(null);
			movimientoPago.setMovimientoMidas(
					null != mc.getMovimientoMidas() ? new MovimientoMidas(mc.getMovimientoMidas().getId()) : null);
			movimientoPago.setMonto(null != mc.getMovimientoMidas() && null != mc.getMovimientoMidas().getMonto()
					? mc.getMovimientoMidas().getMonto()
					: null);
			movimientoPago.setNuevo(false);
		}
		return movimientoPago;
	}

	/**
	 * Construye una lista de entities de tipo MovimientoPago a partir de una lista
	 * de entities de tipo MovimientoConciliacion
	 * 
	 * @param movimientoConciliacionList
	 * @return
	 */
	public static List<MovimientoPago> buildMovimientoPagoListFromMovimientoConciliacionList(
			List<MovimientoConciliacion> movimientoConciliacionList, final String createdBy) {
		List<MovimientoPago> movimientoPagoList = null;
		if (null != movimientoConciliacionList) {
			movimientoPagoList = new ArrayList<>();
			for (MovimientoConciliacion mc : movimientoConciliacionList) {
				movimientoPagoList.add(buildMovimientoPagoFromMovimientoConciliacion(mc, createdBy));
			}
		}
		return movimientoPagoList;
	}

	/**
	 * Construye un objeto de tipo MovimientoPagoDTO a partir de un entity de tipo
	 * MovimientoTransito
	 * 
	 * @param movimientoTransito
	 * @return
	 */
	public static MovimientoPagoDTO buildMovimientoPagoDTOFromMovimientoTransito(
			MovimientoTransito movimientoTransito) {
		MovimientoPagoDTO movimientoPagoDTO = null;
		if (null != movimientoTransito) {
			movimientoPagoDTO = new MovimientoPagoDTO();
			movimientoPagoDTO.setId(movimientoTransito.getId());
			movimientoPagoDTO.setEstatus(new EstatusPago(movimientoTransito.getEstatus().getId()));
			movimientoPagoDTO.setMonto(movimientoTransito.getMonto());
			movimientoPagoDTO
					.setMovimientoMidasDTO(new MovimientoMidasDTO(movimientoTransito.getMovimientoMidas().getId()));
		}
		return movimientoPagoDTO;
	}

	/**
	 * Construye una lista de objetos de tipo MovimientoPagoDTO a partir de una
	 * lista de entities de tipo MovimientoTransito
	 * 
	 * @param movimientosTransito
	 * @return
	 */
	public static List<MovimientoPagoDTO> buildMovimientoPagoDTOListFromMovimientoTransitoList(
			List<MovimientoTransito> movimientosTransito) {
		List<MovimientoPagoDTO> movimientosPagoDTO = null;
		if (null != movimientosTransito && !movimientosTransito.isEmpty()) {
			movimientosPagoDTO = new ArrayList<>();
			for (MovimientoTransito movimientoTransito : movimientosTransito) {
				movimientosPagoDTO.add(buildMovimientoPagoDTOFromMovimientoTransito(movimientoTransito));
			}
		}
		return movimientosPagoDTO;
	}

	/**
	 * Construye un entity de tipo MovimientoPago a partir de un objeto de tipo
	 * MovimientoPagoDTO
	 * 
	 * @param movimientoPagoDTO
	 * @param folio
	 * @return
	 */
	public static MovimientoPago buildMovimientoPagoFromMovimientoPagoDTO(MovimientoPagoDTO movimientoPagoDTO,
			final Long folio, final String requestUser) {
		MovimientoPago movimientoPago = null;
		if (null != movimientoPagoDTO) {
			movimientoPago = new MovimientoPago();
			movimientoPago.setId(null);
			movimientoPago.setEstatus(new EstatusPago(movimientoPagoDTO.getEstatus().getId()));
			movimientoPago.setMonto(movimientoPagoDTO.getMonto());
			movimientoPago.setIdConciliacion(folio);
			movimientoPago.setCreatedBy(requestUser);
			movimientoPago.setCreatedDate(new Date());
			movimientoPago.setNuevo(true);
			movimientoPago.setMovimientoMidas(new MovimientoMidas(movimientoPagoDTO.getMovimientoMidasDTO().getId()));

		}
		return movimientoPago;
	}

	/**
	 * Construye una lista de entities de tipo MovimientoPago a partir de una lista
	 * de objetos de tipo MovimientoPagoDTO
	 * 
	 * @param movimientoPagoDTOList
	 * @param folio
	 * @return
	 */
	public static List<MovimientoPago> buildMovimientoPagoListFromMovimientoPagoDTOList(
			List<MovimientoPagoDTO> movimientoPagoDTOList, final Long folio, final String requestUser) {
		List<MovimientoPago> movimientoPagoList = null;
		if (null != movimientoPagoDTOList && !movimientoPagoDTOList.isEmpty()) {
			movimientoPagoList = new ArrayList<>();
			for (MovimientoPagoDTO movimientoPagoDTO : movimientoPagoDTOList) {
				movimientoPagoList.add(buildMovimientoPagoFromMovimientoPagoDTO(movimientoPagoDTO, folio, requestUser));
			}
		}
		return movimientoPagoList;
	}

	/**
	 * Construye una lista de Integer de los ids contenidos en una lista de objetos
	 * de tipo MovimientosDTO
	 * 
	 * @param movimientosDTOList
	 * @return
	 */
	public static List<Integer> buildIdsListFromMovimientosDTOList(List<MovimientosDTO> movimientosDTOList) {
		List<Integer> list = null;
		if (null != movimientosDTOList && !movimientosDTOList.isEmpty()) {
			list = new ArrayList<>();
			for (MovimientosDTO movimiento : movimientosDTOList) {
				if (null != movimiento.getId())
					list.add(movimiento.getId());
			}
		}
		return list;
	}

	/**
	 * Construye una lista de objetos de tipo Integer a partir de una lista de
	 * objetos de tipoMovTransitoRequestDTO
	 * 
	 * @param movTransitoRequestDTOList
	 * @return
	 */
	public static List<Integer> buildIntegerListFromMovTransitoRequestDTOList(
			List<MovTransitoRequestDTO> movTransitoRequestDTOList) {
		List<Integer> list = null;
		if (null != movTransitoRequestDTOList && !movTransitoRequestDTOList.isEmpty()) {
			list = new ArrayList<>();
			for (MovTransitoRequestDTO movTransitoRequestDTO : movTransitoRequestDTOList) {
				list.add(movTransitoRequestDTO.getId());
			}
		}
		return list;
	}

	/**
	 * Construye un mapa con dos listas una con ids de comisiones a ser editados y
	 * otra con una lista de ids comisiones a ser eliminados de pendiendo de su
	 * estatus
	 * 
	 * @param comisionesRequestDTOList
	 * @return
	 */
	public static Map<String, List<Integer>> buildMapIntegerListFromComisionesRequestDTONO0List(
			List<ComisionesRequestDTO> comisionesRequestDTOList) {
		Map<String, List<Integer>> map = null;
		List<Integer> listComs = null;
		List<Integer> listComsDel = null;
		List<Integer> idsComComp = null;
		if (null != comisionesRequestDTOList && !comisionesRequestDTOList.isEmpty()) {
			listComs = new ArrayList<>();
			listComsDel = new ArrayList<>();
			idsComComp = new ArrayList<>();
			for (ComisionesRequestDTO comisionesRequestDTO : comisionesRequestDTOList) {
				if (!comisionesRequestDTO.getId().equals(0))
					idsComComp.add(comisionesRequestDTO.getId());
				if (!comisionesRequestDTO.getId().equals(0) && comisionesRequestDTO.getEstatus())
					listComs.add(comisionesRequestDTO.getId());
				else if (!comisionesRequestDTO.getId().equals(0) && !comisionesRequestDTO.getEstatus())
					listComsDel.add(comisionesRequestDTO.getId());
			}
			map = new HashMap<>();
			map.put("idsCom", listComs);
			map.put("idsComDel", listComsDel);
			map.put("idsComComp", idsComComp);
		}

		return map;
	}

	/**
	 * Cinstruye una lista de elementos de tipo Long a partir de una lista de
	 * elementos de tipo Object
	 * 
	 * @param objectList
	 * @return
	 */
	public static List<Long> buildLongListFromObjectList(List<Object> objectList) {
		List<Long> longList = null;
		if (null != objectList) {
			longList = new ArrayList<>();
			for (Object object : objectList) {
				longList.add(Long.valueOf(object.toString()));
			}
		}
		return longList;
	}


	/**
	 * Realiza la agrupacion de montos de los movimientos por sucursal
	 * @param movimientos
	 * @param tipoMov
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends IMovTransaccion> List<T> groupMovimientosBySucursal(List<T> movimientos, TipoMovimientoEnum tipoMov) {
		
		List<IMovTransaccion> allMovs = new ArrayList<IMovTransaccion>();
		
		if (movimientos != null && movimientos.size() > 0) {

			Map<Integer, IMovTransaccion> bySuc = new LinkedHashMap<Integer, IMovTransaccion>();
			for (IMovTransaccion mov : movimientos) {

				// Solo movimientos de tipo sucursal
				if (mov.getMovimientoMidas() != null && mov.getMovimientoMidas().getSucursal() != null) {
					Integer suc = mov.getMovimientoMidas().getSucursal();
					IMovTransaccion movBySuc = bySuc.get(suc);
					if (movBySuc == null) {
						movBySuc = mov;
						bySuc.put(suc, mov);
					}
					else {
						switch (tipoMov) {
							case DEVOLUCION:
								// Se agrega el monto al total
								MovimientoDevolucion movDev = (MovimientoDevolucion) mov;
								MovimientoDevolucion movDevBySuc = (MovimientoDevolucion) movBySuc;
								BigDecimal montoBySuc = movDevBySuc.getMonto() != null ? movDevBySuc.getMonto() : new BigDecimal(0);
								montoBySuc = montoBySuc.add(movDev.getMonto() != null ? movDev.getMonto() : new BigDecimal(0));
								movDevBySuc.setMonto(montoBySuc);
								break;
							case MIDAS:
								// Se agrega el monto al total
								MovimientoMidas movMidas = (MovimientoMidas) mov;
								MovimientoMidas movMidasBySuc = (MovimientoMidas) movBySuc;
								BigDecimal montoMidasBySuc = movMidasBySuc.getMonto() != null ? movMidasBySuc.getMonto() : new BigDecimal(0);
								montoMidasBySuc = montoMidasBySuc.add(movMidas.getMonto() != null ? movMidas.getMonto() : new BigDecimal(0));
								movMidasBySuc.setMonto(montoMidasBySuc);
								break;
						}
					}
				}
				else {
					allMovs.add(mov);
				}
			}
			
			// Se agregan todos los movs por sucursal
			allMovs.addAll(bySuc.values());
		}
		return (List<T>) allMovs;
	}

}
