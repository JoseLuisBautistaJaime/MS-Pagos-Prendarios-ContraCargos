/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util.validacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;

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
		return (null != commonConciliacionRequestDTO && null != commonConciliacionRequestDTO.getFolio()
				&& null != commonConciliacionRequestDTO.getPagina()
				&& null != commonConciliacionRequestDTO.getResultados() && commonConciliacionRequestDTO.getFolio() > 0
				&& commonConciliacionRequestDTO.getResultados() > 0 && commonConciliacionRequestDTO.getPagina() >= 0
				&& null != commonConciliacionRequestDTO.getEstatus());
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
		return (null != commonConciliacionRequestDTO && null != commonConciliacionRequestDTO.getFolio()
				&& null != commonConciliacionRequestDTO.getPagina()
				&& null != commonConciliacionRequestDTO.getResultados() && commonConciliacionRequestDTO.getFolio() > 0
				&& commonConciliacionRequestDTO.getResultados() > 0 && commonConciliacionRequestDTO.getPagina() >= 0);
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
		if (null != movimientoProcesosNocturnosListResponseDTO
				&& null != movimientoProcesosNocturnosListResponseDTO.getFolio()
				&& null != movimientoProcesosNocturnosListResponseDTO.getMovimientos()
				&& !movimientoProcesosNocturnosListResponseDTO.getMovimientos().isEmpty()
				&& null != movimientoProcesosNocturnosListResponseDTO.getFechaDesde()
				&& null != movimientoProcesosNocturnosListResponseDTO.getFechaHasta()) {
			for (MovimientoMidasRequestDTO movimientoMidasRequestDTO : movimientoProcesosNocturnosListResponseDTO
					.getMovimientos())
				if (null == movimientoMidasRequestDTO.getCapitalActual()
						|| null == movimientoMidasRequestDTO.getComisiones()
						|| null == movimientoMidasRequestDTO.getFolioPartida()
						|| null == movimientoMidasRequestDTO.getInteres()
						|| null == movimientoMidasRequestDTO.getMontoOperacion()
						|| null == movimientoMidasRequestDTO.getSucursal()
						|| null == movimientoMidasRequestDTO.getTransaccion()
						|| null == movimientoMidasRequestDTO.getEstatus()
						|| null == movimientoMidasRequestDTO.getFecha()
						|| null == movimientoMidasRequestDTO.getNumAutorizacion()
						|| null == movimientoMidasRequestDTO.getOperacionAbr()
						|| null == movimientoMidasRequestDTO.getOperacionDesc()
						|| null == movimientoMidasRequestDTO.getTipoContratoAbr()
						|| null == movimientoMidasRequestDTO.getTipoContratoDesc()
						|| null == movimientoMidasRequestDTO.getCodigoError()
						|| null == movimientoMidasRequestDTO.getMensajeError()
						|| null == movimientoMidasRequestDTO.getIdTarjeta()
						|| null == movimientoMidasRequestDTO.getMarcaTarjeta()
						|| null == movimientoMidasRequestDTO.getTipoTarjeta()
						|| null == movimientoMidasRequestDTO.getTarjeta()
						|| null == movimientoMidasRequestDTO.getMonedaPago())
					return false;
		} else
			return false;
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
		if (null == movimientoTransaccionalListRequestDTO || null == movimientoTransaccionalListRequestDTO.getFolio()
				|| null == movimientoTransaccionalListRequestDTO.getMovimientos()
				|| movimientoTransaccionalListRequestDTO.getMovimientos().isEmpty()
				|| null == movimientoTransaccionalListRequestDTO.getFechaDesde()
				|| null == movimientoTransaccionalListRequestDTO.getFechaHasta())
			return false;
		else {
			for (MovimientoProveedorDTO movimientoProveedorDTO : movimientoTransaccionalListRequestDTO.getMovimientos())
				if (null == movimientoProveedorDTO.getAmount() || null == movimientoProveedorDTO.getAuthorization()
						|| null == movimientoProveedorDTO.getCard()
						|| null == movimientoProveedorDTO.getCard().getAddress()
						|| null == movimientoProveedorDTO.getCard().getAllowsCharges()
						|| null == movimientoProveedorDTO.getCard().getAllowsPayouts()
						|| null == movimientoProveedorDTO.getCard().getBankCode()
						|| null == movimientoProveedorDTO.getCard().getBankName()
						|| null == movimientoProveedorDTO.getCard().getBrand()
						|| null == movimientoProveedorDTO.getCard().getCreationDate()
						|| null == movimientoProveedorDTO.getCard().getCustomerId()
						|| null == movimientoProveedorDTO.getCard().getExpirationMonth()
						|| null == movimientoProveedorDTO.getCard().getExpirationYear()
						|| null == movimientoProveedorDTO.getCard().getHolderName()
						|| null == movimientoProveedorDTO.getCard().getId()
						|| null == movimientoProveedorDTO.getCard().getCardNumber()
						|| null == movimientoProveedorDTO.getCard().getType()
						|| null == movimientoProveedorDTO.getConciliated()
						|| null == movimientoProveedorDTO.getCreationDate()
						|| null == movimientoProveedorDTO.getCurrency()
						|| null == movimientoProveedorDTO.getCustomerId()
						|| null == movimientoProveedorDTO.getDescription()
						|| null == movimientoProveedorDTO.getErrorCode()
						|| null == movimientoProveedorDTO.getErrorMessage() || null == movimientoProveedorDTO.getId()
						|| null == movimientoProveedorDTO.getMethod()
						|| null == movimientoProveedorDTO.getPaymentMethod()
						|| null == movimientoProveedorDTO.getPaymentMethod().getType()
						|| null == movimientoProveedorDTO.getPaymentMethod().getUrl()
						|| null == movimientoProveedorDTO.getOperationDate()
						|| null == movimientoProveedorDTO.getOperationType()
						|| null == movimientoProveedorDTO.getOrderId() || null == movimientoProveedorDTO.getStatus()
						|| null == movimientoProveedorDTO.getTransactionType())
					return false;

		}
		return true;
	}

}
