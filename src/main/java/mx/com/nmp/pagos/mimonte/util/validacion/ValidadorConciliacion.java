/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util.validacion;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Calendar;

import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.ComisionSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarIdPSRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarSubEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionDeleteDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransaccionesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionResponseSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadesRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReporteRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
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
		} catch (Exception ex) {
			return false;
		}
		return (commonConciliacionRequestDTO.getFolio() > 0);
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
		} catch (Exception ex) {
			return false;
		}
		return (commonConciliacionRequestDTO.getFolio() > 0);
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
					assertNotNull(movimientoMidasRequestDTO.getIdOperacion());
					assertNotNull(movimientoMidasRequestDTO.getIdTipoContrato());
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
			ini.setTime(saveEstadoCuentaRequestDTO.getFechaInicial());
			fin.setTime(saveEstadoCuentaRequestDTO.getFechaFinal());
			if (ini.after(fin))
				throw new ConciliacionException(ConciliacionConstants.Validation.INITIAL_DATE_AFTER_FINAL_DATE);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * Valida que un objeto de tipo ReporteRequestDTO sea correcto y no sea nulo ni
	 * ninguno de sus atributos requeridos
	 * 
	 * @param reporteRequestDTO
	 * @return
	 */
	public static boolean validateReporteRequestDTO(ReporteRequestDTO reporteRequestDTO) {
		try {
			if (null != reporteRequestDTO && null != reporteRequestDTO.getFechaDesde()
					&& null != reporteRequestDTO.getFechaHasta()) {
				Calendar ini = Calendar.getInstance();
				Calendar fin = Calendar.getInstance();
				ini.setTime(reporteRequestDTO.getFechaDesde());
				fin.setTime(reporteRequestDTO.getFechaHasta());
				if (fin.before(ini))
					throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
			}
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public static boolean validaConciliacionResponseSaveDTO(ConciliacionResponseSaveDTO conciliacionRequestDTO,
			String createdBy) {
		try {
			assertNotNull(conciliacionRequestDTO.getCuenta().getId());
			assertNotNull(conciliacionRequestDTO.getEntidad().getId());
			if (conciliacionRequestDTO.getCuenta().getId() < 1)
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
			if (conciliacionRequestDTO.getEntidad().getId() < 1)
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
			if (createdBy == null || createdBy.isEmpty() || createdBy.equals(""))
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * Valida que un objeto de tipo ComisionSaveDTO contenga todos los atributos
	 * requeridos y estos sean valores validos
	 * 
	 * @param comisionSaveDTO
	 * @return
	 */
	public static boolean validateComisionSaveDTO(ComisionSaveDTO comisionSaveDTO) {
		Calendar today = Calendar.getInstance();
		try {
			assertNotNull(comisionSaveDTO);
			assertNotNull(comisionSaveDTO.getDescripcion());
			assertNotNull(comisionSaveDTO.getFechaCargo());
			assertNotNull(comisionSaveDTO.getFechaOperacion());
			assertNotNull(comisionSaveDTO.getFolio());
			assertNotNull(comisionSaveDTO.getId());
			assertNotNull(comisionSaveDTO.getMonto());
		} catch (Exception ex) {
			return false;
		}
		return (!comisionSaveDTO.getDescripcion().isEmpty() && !"".equals(comisionSaveDTO.getDescripcion())
				&& !today.before(comisionSaveDTO.getFechaCargo()) && !today.before(comisionSaveDTO.getFechaOperacion())
				&& comisionSaveDTO.getId() > 0 && comisionSaveDTO.getMonto().compareTo(new BigDecimal("0")) > 0);
	}

	/**
	 * Valida que un objeto de tipo ComisionDeleteDTO contenga los atributos
	 * requeridos y que estos sean valores validos
	 * 
	 * @param comisionDeleteDTO
	 * @return
	 */
	public static boolean validateComisionDeleteDTO(ComisionDeleteDTO comisionDeleteDTO) {
		try {
			assertNotNull(comisionDeleteDTO);
			assertNotNull(comisionDeleteDTO.getFolio());
			assertNotNull(comisionDeleteDTO.getIdComisiones());
			if (!comisionDeleteDTO.getIdComisiones().isEmpty()) {
				for (Long com : comisionDeleteDTO.getIdComisiones()) {
					assertNotNull(com);
				}
			} else
				return false;
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * Valida un objeto de tipo ComisionesTransaccionesRequestDTO para ver que
	 * contenga todos los atributos requeridos y que estos sean valores validos
	 * 
	 * @param comisionesTransaccionesRequestDTO
	 * @return
	 */
	public static boolean validateComisionesTransaccionesRequestDTO(
			ComisionesTransaccionesRequestDTO comisionesTransaccionesRequestDTO) {
		Calendar desde = Calendar.getInstance();
		Calendar hasta = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		try {
			assertNotNull(comisionesTransaccionesRequestDTO);
			assertNotNull(comisionesTransaccionesRequestDTO.getComision());
			assertNotNull(comisionesTransaccionesRequestDTO.getFechaDesde());
			assertNotNull(comisionesTransaccionesRequestDTO.getFechaHasta());
			assertNotNull(comisionesTransaccionesRequestDTO.getIdConciliacion());
		} catch (Exception ex) {
			return false;
		}
		desde.setTime(comisionesTransaccionesRequestDTO.getFechaDesde());
		hasta.setTime(comisionesTransaccionesRequestDTO.getFechaHasta());
		return (!today.before(desde) && !today.before(hasta) && (desde.before(hasta) || desde.equals(hasta)));
	}

	/**
	 * Valida un objeto de tipo SolicitarPagosRequestDTO para que tenga todos los
	 * atributos requeridos y estos sean valores validos
	 * 
	 * @param solicitarPagosRequestDTO
	 * @return
	 */
	public static boolean validateSolicitarPagosRequestDTO(SolicitarPagosRequestDTO solicitarPagosRequestDTO) {
		try {
			assertNotNull(solicitarPagosRequestDTO);
			assertNotNull(solicitarPagosRequestDTO.getFolio());
			assertNotNull(solicitarPagosRequestDTO.getIdMovimientos());
			if (!solicitarPagosRequestDTO.getIdMovimientos().isEmpty())
				for (Integer val : solicitarPagosRequestDTO.getIdMovimientos())
					assertNotNull(val);
			else
				return false;
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * Valida que un objeto de tipo Integer no sea nulo
	 * 
	 * @param value
	 * @return
	 */
	public static boolean validateInteger(final Integer value) {
		try {
			assertNotNull(value);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * Valida que el objeto de tipo ActualizarIdPSRequest contenga los atributos
	 * requeridos y estos contengan valores validos
	 * 
	 * @param actualizarIdPSRequest
	 * @return
	 */
	public static boolean validateActualizarIdPSRequest(ActualizarIdPSRequest actualizarIdPSRequest) {
		try {
			assertNotNull(actualizarIdPSRequest);
			assertNotNull(actualizarIdPSRequest.getFolio());
		} catch (Exception ex) {
			return false;
		}
		return !(null == actualizarIdPSRequest.getIdAsientoContable()
				&& null == actualizarIdPSRequest.getIdPolizaTesoreria());
	}

	/**
	 * Valida un objeto de tipo ActualizarSubEstatusRequestDTO para que contenga
	 * todos los atributos requeridos y estos a su vez sean valores validos
	 * 
	 * @param actualizarSubEstatusRequestDTO
	 * @return
	 */
	public static boolean validateActualizarSubEstatusRequestDTO(
			ActualizarSubEstatusRequestDTO actualizarSubEstatusRequestDTO) {
		try {
			assertNotNull(actualizarSubEstatusRequestDTO);
			assertNotNull(actualizarSubEstatusRequestDTO.getFolio());
			assertNotNull(actualizarSubEstatusRequestDTO.getIdSubEstatus());
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * Valida un objeto de tipo ResumenConciliacionRequestDTO para que contenga
	 * todos los campos requeridos y estaos a su vez sean valores validos
	 * 
	 * @param resumenConciliacionRequestDTO
	 * @return
	 */
	public static boolean validateResumenConciliacionRequestDTO(
			ResumenConciliacionRequestDTO resumenConciliacionRequestDTO) {
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		Calendar hoy = Calendar.getInstance();
		if (null != resumenConciliacionRequestDTO && null != resumenConciliacionRequestDTO.getFechaInicial()
				&& null != resumenConciliacionRequestDTO.getFechaFinal()) {
			ini.setTime(resumenConciliacionRequestDTO.getFechaInicial());
			fin.setTime(resumenConciliacionRequestDTO.getFechaFinal());
			if (ini.after(fin) || hoy.before(ini))
				return false;
		}
		return true;
	}

	/**
	 * Valida un objeto de tipo ConsultaActividadesRequestDTO para que contenga
	 * todos los atributos requeridos y a su vez estos sean valires validos
	 * 
	 * @param consultaActividadesRequestDTO
	 * @return
	 */
	public static boolean validateConsultaActividadesRequestDTO(
			ConsultaActividadesRequestDTO consultaActividadesRequestDTO) {
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		Calendar hoy = Calendar.getInstance();
		if (null != consultaActividadesRequestDTO && null != consultaActividadesRequestDTO.getFechaDesde()
				&& null != consultaActividadesRequestDTO.getFechaHasta()) {
			ini.setTime(consultaActividadesRequestDTO.getFechaDesde());
			fin.setTime(consultaActividadesRequestDTO.getFechaHasta());
			if (ini.after(fin) || hoy.before(ini))
				return false;
		}
		return true;
	}

	/**
	 * Valida un objeto de tipo ConsultaActividadesRequest para que contenga todos
	 * los atributos requeridos y que esteos tengan valores validos
	 * 
	 * @param consultaActividadesRequest
	 * @return
	 */
	public static boolean validateConsultaActividadesRequest(ConsultaActividadesRequest consultaActividadesRequest) {
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		Calendar hoy = Calendar.getInstance();
		if (null != consultaActividadesRequest && null != consultaActividadesRequest.getFechaDesde()
				&& null != consultaActividadesRequest.getFechaHasta()) {
			ini.setTime(consultaActividadesRequest.getFechaDesde());
			fin.setTime(consultaActividadesRequest.getFechaHasta());
			if (ini.after(fin) || hoy.before(ini))
				return false;
		}
		return true;
	}

}
