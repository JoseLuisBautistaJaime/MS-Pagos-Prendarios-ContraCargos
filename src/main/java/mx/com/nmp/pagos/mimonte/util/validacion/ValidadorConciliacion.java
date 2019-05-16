/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util.validacion;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;

/**
 * @name ValidadorConciliacion
 * @description Clase que contiene validaciones para los objetos de conciliacion
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 29/04/2019 12:37 hrs.
 * @version 0.1
 */
public interface ValidadorConciliacion {

	/**
	 * Valida que los parametros de entrada de un objeto
	 * CommonConciliacionEstatusRequestDTO sean correctos
	 * 
	 * @param commonConciliacionRequestDTO
	 * @return
	 */
	public static boolean validateCommonConciliacionEstatusRequestDTO(
			final CommonConciliacionEstatusRequestDTO commonConciliacionRequestDTO) {
		try {
			assertNotNull(commonConciliacionRequestDTO);
			assertNotNull(commonConciliacionRequestDTO.getFolio());
			assertNotNull(commonConciliacionRequestDTO.getPagina());
			assertNotNull(commonConciliacionRequestDTO.getResultados());
//			assertNotNull(commonConciliacionRequestDTO.getEstatus());
		} catch (Exception ex) {
			return false;
		}
		return (commonConciliacionRequestDTO.getFolio() > 0 && commonConciliacionRequestDTO.getResultados() > 0
				&& commonConciliacionRequestDTO.getPagina() >= 0);
	}

	/**
	 * Valida que los parametros de entrada de un objeto
	 * CommonConciliacionRequestDTO sean correctos
	 * 
	 * @param commonConciliacionRequestDTO
	 * @return
	 */
	public static boolean validateCommonConciliacionRequestDTO(
			final CommonConciliacionRequestDTO commonConciliacionRequestDTO) {
		try {
			assertNotNull(commonConciliacionRequestDTO);
			assertNotNull(commonConciliacionRequestDTO.getFolio());
			assertNotNull(commonConciliacionRequestDTO.getPagina());
			assertNotNull(commonConciliacionRequestDTO.getResultados());
		} catch (Exception ex) {
			return false;
		}
		return (commonConciliacionRequestDTO.getFolio() > 0 && commonConciliacionRequestDTO.getResultados() > 0
				&& commonConciliacionRequestDTO.getPagina() >= 0);
	}

	/**
	 * Valida que un objeto de tipo MovimientoProcesosNocturnosListResponseDTO sea
	 * correcto y contenga todos los atributos necesarios
	 * 
	 * @param movimientoProcesosNocturnosListResponseDTO
	 * @return
	 */
	public static boolean validateMovimientoProcesosNocturnosListResponseDTO(
			MovimientoProcesosNocturnosListResponseDTO movimientoProcesosNocturnosListResponseDTO) {
		try {
			assertNotNull(movimientoProcesosNocturnosListResponseDTO);
			assertNotNull(movimientoProcesosNocturnosListResponseDTO.getFolio());
			assertNotNull(movimientoProcesosNocturnosListResponseDTO.getMovimientos());
			assertNotNull(movimientoProcesosNocturnosListResponseDTO.getFechaDesde());
			assertNotNull(movimientoProcesosNocturnosListResponseDTO.getFechaHasta());
			if (!movimientoProcesosNocturnosListResponseDTO.getMovimientos().isEmpty()) {
				for (MovimientoMidasRequestDTO movimientoMidasRequestDTO : movimientoProcesosNocturnosListResponseDTO
						.getMovimientos()) {
					assertNotNull(movimientoMidasRequestDTO.getCapitalActual());
//					assertNotNull(null == movimientoMidasRequestDTO.getComisiones());
					assertNotNull(movimientoMidasRequestDTO.getFolioPartida());
					assertNotNull(movimientoMidasRequestDTO.getInteres());
					assertNotNull(movimientoMidasRequestDTO.getMontoOperacion());
					assertNotNull(movimientoMidasRequestDTO.getSucursal());
					assertNotNull(movimientoMidasRequestDTO.getTransaccion());
					assertNotNull(movimientoMidasRequestDTO.getEstatus());
					assertNotNull(movimientoMidasRequestDTO.getFecha());
					assertNotNull(movimientoMidasRequestDTO.getNumAutorizacion());
					assertNotNull(movimientoMidasRequestDTO.getOperacionAbr());
					assertNotNull(movimientoMidasRequestDTO.getOperacionDesc());
					assertNotNull(movimientoMidasRequestDTO.getTipoContratoAbr());
					assertNotNull(movimientoMidasRequestDTO.getTipoContratoDesc());
					assertNotNull(movimientoMidasRequestDTO.getCodigoError());
					assertNotNull(movimientoMidasRequestDTO.getMensajeError());
					assertNotNull(movimientoMidasRequestDTO.getIdTarjeta());
					assertNotNull(movimientoMidasRequestDTO.getMarcaTarjeta());
					assertNotNull(movimientoMidasRequestDTO.getTipoTarjeta());
					assertNotNull(movimientoMidasRequestDTO.getTarjeta());
					assertNotNull(movimientoMidasRequestDTO.getMonedaPago());
					assertNotNull(movimientoMidasRequestDTO.getImporteTransaccion());
					movimientoMidasRequestDTO.setId(null);
				}
			} else
				return false;
		} catch (Exception ex) {
			return false;
		}
		return true;

	}

	/**
	 * Valida que un objeto de tipo MovimientoTransaccionalListRequestDTO sea
	 * correcto y contenga todos los atributos necesarios
	 * 
	 * @param movimientoTransaccionalListRequestDTO
	 * @return
	 */
	public static boolean validateMovimientoTransaccionalListRequestDTO(
			MovimientoTransaccionalListRequestDTO movimientoTransaccionalListRequestDTO) {
		try {
			assertNotNull(movimientoTransaccionalListRequestDTO);
			assertNotNull(movimientoTransaccionalListRequestDTO.getFolio());
			assertNotNull(movimientoTransaccionalListRequestDTO.getMovimientos());
			assertNotNull(movimientoTransaccionalListRequestDTO.getFechaDesde());
			assertNotNull(movimientoTransaccionalListRequestDTO.getFechaHasta());
			if (!movimientoTransaccionalListRequestDTO.getMovimientos().isEmpty()) {
				for (MovimientoProveedorDTO movimientoProveedorDTO : movimientoTransaccionalListRequestDTO
						.getMovimientos()) {
					assertNotNull(movimientoProveedorDTO.getAmount());
					assertNotNull(movimientoProveedorDTO.getAuthorization());
					assertNotNull(movimientoProveedorDTO.getCard());
					assertNotNull(movimientoProveedorDTO.getCard().getAddress());
					assertNotNull(movimientoProveedorDTO.getCard().getAllowsCharges());
					assertNotNull(movimientoProveedorDTO.getCard().getAllowsPayouts());
					assertNotNull(movimientoProveedorDTO.getCard().getBankCode());
					assertNotNull(movimientoProveedorDTO.getCard().getBankName());
					assertNotNull(movimientoProveedorDTO.getCard().getBrand());
					assertNotNull(movimientoProveedorDTO.getCard().getCreationDate());
					assertNotNull(movimientoProveedorDTO.getCard().getCustomerId());
					assertNotNull(movimientoProveedorDTO.getCard().getExpirationMonth());
					assertNotNull(movimientoProveedorDTO.getCard().getExpirationYear());
					assertNotNull(movimientoProveedorDTO.getCard().getHolderName());
					assertNotNull(movimientoProveedorDTO.getCard().getId());
					assertNotNull(movimientoProveedorDTO.getCard().getCardNumber());
					assertNotNull(movimientoProveedorDTO.getCard().getType());
					assertNotNull(movimientoProveedorDTO.getConciliated());
					assertNotNull(movimientoProveedorDTO.getCreationDate());
					assertNotNull(movimientoProveedorDTO.getCurrency());
					assertNotNull(movimientoProveedorDTO.getCustomerId());
					assertNotNull(movimientoProveedorDTO.getDescription());
					assertNotNull(movimientoProveedorDTO.getErrorCode());
					assertNotNull(movimientoProveedorDTO.getErrorMessage());
					assertNotNull(movimientoProveedorDTO.getMethod());
					assertNotNull(movimientoProveedorDTO.getPaymentMethod());
					assertNotNull(movimientoProveedorDTO.getPaymentMethod().getType());
					assertNotNull(movimientoProveedorDTO.getPaymentMethod().getUrl());
					assertNotNull(movimientoProveedorDTO.getOperationDate());
					assertNotNull(movimientoProveedorDTO.getOperationType());
					assertNotNull(movimientoProveedorDTO.getOrderId());
					assertNotNull(movimientoProveedorDTO.getStatus());
					assertNotNull(movimientoProveedorDTO.getTransactionType());
					assertNotNull(movimientoProveedorDTO.getIdMovimiento());
					movimientoProveedorDTO.setId(null);
				}
			} else
				return false;
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	/**
	 * Valida que la informacion dentro de un objeto de tipo
	 * SaveEstadoCuentaRequestDTO sea correcta y no sea nula
	 * 
	 * @param saveEstadoCuentaRequestDTO
	 * @return
	 */
	public static boolean validateSaveEstadoCuentaRequestDTO(SaveEstadoCuentaRequestDTO saveEstadoCuentaRequestDTO) {
		try {
			assertNotNull(saveEstadoCuentaRequestDTO);
			assertNotNull(saveEstadoCuentaRequestDTO.getFolio());
			assertNotNull(saveEstadoCuentaRequestDTO.getFechaInicial());
			assertNotNull(saveEstadoCuentaRequestDTO.getFechaFinal());
			Calendar ini = Calendar.getInstance();
			Calendar fin = Calendar.getInstance();
			if (ini.after(fin))
				throw new ConciliacionException(ConciliacionConstants.Validation.INITIAL_DATE_AFTER_FINAL_DATE);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

}
