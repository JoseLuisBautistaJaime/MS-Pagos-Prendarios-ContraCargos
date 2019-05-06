/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.MetodoPagoMovimientosProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.TarjetaMovimientosProveedorDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MetodoPagoMovimientosProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TarjetaMovimientosProveedor;

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
			movimientoMidasDTO.setEstadoTransaccion(movimientoMidas.getEstadoTransaccion());
			movimientoMidasDTO.setIdConsumidor(movimientoMidas.getIdConsumidor());
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
			movimientoProveedorDTO.setId(movimientoProveedor.getId());
			movimientoProveedorDTO.setMethod(movimientoProveedor.getMethod());
			movimientoProveedorDTO.getPaymentMethod()
					.setType(movimientoProveedor.getMetodoPagoMovimientosProveedor().getType());
			movimientoProveedorDTO.getPaymentMethod()
					.setUrl(movimientoProveedor.getMetodoPagoMovimientosProveedor().getUrl());
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
	 * Cinstruye un entity de tipo MovimientoMidas a partir de un objeto de tipo
	 * MovimientoMidasDTO
	 * 
	 * @param movimientoMidasDTO
	 * @param idReporte
	 * @return
	 */
	public static MovimientoMidas buildMovimientoMidasFromMovimientoMidasDTO(MovimientoMidasDTO movimientoMidasDTO,
			final Long idReporte) {
		MovimientoMidas movimientoMidas = new MovimientoMidas();
		movimientoMidas.setId(null);
		movimientoMidas.setReporte(idReporte);
		movimientoMidas.setIdConsumidor(movimientoMidasDTO.getIdConsumidor());
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
			for (MovimientoMidasDTO movimientoMidasDTO : movimientoProcesosNocturnosListResponseDTO.getMovimientos()) {
				movimientoMidasList.add(buildMovimientoMidasFromMovimientoMidasDTO(movimientoMidasDTO, idReporte));
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
			MovimientoProveedorDTO movimientoProveedorDTO, final long idReporte) {
		MovimientoProveedor movimientoProveedor = new MovimientoProveedor();
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
		movimientoProveedor.setId(movimientoProveedorDTO.getId());
		movimientoProveedor.setMethod(movimientoProveedorDTO.getMethod());
		movimientoProveedor.setOperationDate(movimientoProveedorDTO.getOperationDate());
		movimientoProveedor.setOperationType(movimientoProveedorDTO.getOperationType());
		movimientoProveedor.setOrderId(movimientoProveedorDTO.getOrderId());
		movimientoProveedor.setStatus(movimientoProveedorDTO.getStatus());
		movimientoProveedor.setTransactionType(movimientoProveedorDTO.getTransactionType());
		MetodoPagoMovimientosProveedor metodoPagoMovimientosProveedor = new MetodoPagoMovimientosProveedor();
		metodoPagoMovimientosProveedor.setType(movimientoProveedorDTO.getPaymentMethod().getType());
		metodoPagoMovimientosProveedor.setUrl(movimientoProveedorDTO.getPaymentMethod().getUrl());
		movimientoProveedor.setMetodoPagoMovimientosProveedor(metodoPagoMovimientosProveedor);
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
			MovimientoTransaccionalListRequestDTO movimientoTransaccionalListRequestDTO, final long idReporte) {
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
}
