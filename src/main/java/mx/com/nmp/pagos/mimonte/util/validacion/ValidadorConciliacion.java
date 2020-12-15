/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util.validacion;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.ComisionSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaionConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarIdPSRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarSubEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionDeleteDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransaccionesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionResponseSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionesIdsMovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FolioRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LiquidacionMovimientosRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SaveEstadoCuentaRequestMultipleDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoActualizacionTransito;

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
		} catch (java.lang.AssertionError | Exception ex) {
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
		} catch (java.lang.AssertionError | Exception ex) {
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
					assertNotNull(movimientoMidasRequestDTO.getSucursal());
					assertNotNull(movimientoMidasRequestDTO.getMontoOperacion());
					assertNotNull(movimientoMidasRequestDTO.getEstatus());
					assertNotNull(movimientoMidasRequestDTO.getTransaccion());
					assertNotNull(movimientoMidasRequestDTO.getFecha());
					//assertNotNull(movimientoMidasRequestDTO.getOperacionAbr());
					//assertNotNull(movimientoMidasRequestDTO.getOperacionDesc());
					//assertNotNull(movimientoMidasRequestDTO.getTipoContratoAbr());
					//assertNotNull(movimientoMidasRequestDTO.getTipoContratoDesc());
					assertNotNull(movimientoMidasRequestDTO.getImporteTransaccion());
					movimientoMidasRequestDTO.setId(null);
				}
			} else
				return false;
		} catch (java.lang.AssertionError | Exception ex) {
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
			MovimientoTransaccionalListRequestDTO listRequestDTO) {

		boolean valido = false;
		try {
			assertNotNull(listRequestDTO);
			assertNotNull(listRequestDTO.getFolio());
			assertNotNull(listRequestDTO.getFechaDesde());
			assertNotNull(listRequestDTO.getFechaHasta());
			assertNotNull(listRequestDTO.getMovimientos());
			if (CollectionUtils.isNotEmpty(listRequestDTO.getMovimientos())) {
				for (MovimientoProveedorDTO movimientoProveedorDTO : listRequestDTO.getMovimientos()) {
					assertNotNull(movimientoProveedorDTO.getTransactionType());
					assertNotNull(movimientoProveedorDTO.getStatus());
					assertNotNull(movimientoProveedorDTO.getOperationDate());
					assertNotNull(movimientoProveedorDTO.getOrderId());
					assertNotNull(movimientoProveedorDTO.getAmount());
					movimientoProveedorDTO.setId(null);
				}
				valido = true;
			}
		} catch (java.lang.AssertionError | Exception ex) {
		}
		return valido;
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
		} catch (java.lang.AssertionError | Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean validateSaveEstadoCuentaRequestMultipleDTO(SaveEstadoCuentaRequestMultipleDTO saveEstadoCuentaRequestMultipleDTO) {
		try {
			assertNotNull(saveEstadoCuentaRequestMultipleDTO);
			assertNotNull(saveEstadoCuentaRequestMultipleDTO.getFolios());
			assertNotNull(saveEstadoCuentaRequestMultipleDTO.getFechaInicial());
			assertNotNull(saveEstadoCuentaRequestMultipleDTO.getFechaFinal());
			assertNotNull(saveEstadoCuentaRequestMultipleDTO.getIdCorresponsal());
		} catch (java.lang.AssertionError | Exception ex) {
			ex.printStackTrace();
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
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
						CodigoError.NMP_PMIMONTE_0008);
			if (conciliacionRequestDTO.getEntidad().getId() < 1)
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
						CodigoError.NMP_PMIMONTE_0008);
			if (createdBy == null || createdBy.isEmpty() || createdBy.equals(""))
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
						CodigoError.NMP_PMIMONTE_0008);
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
		try {
			assertNotNull(comisionSaveDTO);
			assertNotNull(comisionSaveDTO.getDescripcion());
			assertNotNull(comisionSaveDTO.getFechaCargo());
			assertNotNull(comisionSaveDTO.getFechaOperacion());
			assertNotNull(comisionSaveDTO.getFolio());
			assertNotNull(comisionSaveDTO.getId());
			assertNotNull(comisionSaveDTO.getMonto());
		} catch (java.lang.AssertionError | Exception ex) {
			return false;
		}
		return (!comisionSaveDTO.getDescripcion().isEmpty() && !"".equals(comisionSaveDTO.getDescripcion())
				&& comisionSaveDTO.getDescripcion().length() <= ConciliacionConstants.DESCRIPCION_MOV_COMISION_SIZE
				&& comisionSaveDTO.getId() > -1 && comisionSaveDTO.getMonto().compareTo(new BigDecimal("0")) > 0);
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
				for (Integer com : comisionDeleteDTO.getIdComisiones()) {
					assertNotNull(com);
				}
			} else
				return false;
		} catch (java.lang.AssertionError | Exception ex) {
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
		try {
			assertNotNull(comisionesTransaccionesRequestDTO);
			assertNotNull(comisionesTransaccionesRequestDTO.getComision());
			assertNotNull(comisionesTransaccionesRequestDTO.getFechaDesde());
			assertNotNull(comisionesTransaccionesRequestDTO.getFechaHasta());
			assertNotNull(comisionesTransaccionesRequestDTO.getIdConciliacion());
		} catch (java.lang.AssertionError | Exception ex) {
			return false;
		}
		return true;
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
		} catch (java.lang.AssertionError | Exception ex) {
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
	 * Valida que un objeto de tipo Long no sea nulo
	 * 
	 * @param value
	 * @return
	 */
	public static boolean validateLong(final Long value) {
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
		if (actualizarIdPSRequest == null || actualizarIdPSRequest.getFolio() == null) {
			return false;
		}

		// Al menos un atributo es requerido
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
			// Se valida que la longitud de la descripcion del susbestatus de la
			// conciliacion sea menor o igual al definido en base de datos, de no ser asi,
			// trunca la cadena de caracteres
			if (null != actualizarSubEstatusRequestDTO.getDescripcion()
					&& !"".equals(actualizarSubEstatusRequestDTO.getDescripcion()) && actualizarSubEstatusRequestDTO
							.getDescripcion().length() > ConciliacionConstants.SUB_ESTATUS_DESCRIPCION_MAX_PERM_LENGHT)
				actualizarSubEstatusRequestDTO.setDescripcion(actualizarSubEstatusRequestDTO.getDescripcion()
						.substring(0, (ConciliacionConstants.SUB_ESTATUS_DESCRIPCION_MAX_PERM_LENGHT - 3))
						.concat("..."));

		} catch (java.lang.AssertionError | Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * Valida que dos fechas recibidas inicial y final sean congruentes o vaidas
	 * entre ellas
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public static boolean validateFechasWithThemselves(Date fechaInicial, Date fechaFinal) {
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		ini.setTime(fechaInicial);
		fin.setTime(fechaFinal);
		return ((fin.after(ini) || ini.equals(fin)));
	}

	/**
	 * Valida que dos fechas recibidas inicial y final sean congruentes respecto a
	 * la fecha actual
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public static boolean validateFechasWithCurrent(Date fechaInicial, Date fechaFinal) {
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		Calendar hoy = Calendar.getInstance();
		Date current = new Date();
		ini.setTime(fechaInicial);
		fin.setTime(fechaFinal);
		hoy.setTime(current);
		hoy.set(Calendar.HOUR_OF_DAY, 0);
		hoy.set(Calendar.MINUTE, 0);
		hoy.set(Calendar.SECOND, 0);
		hoy.set(Calendar.MILLISECOND, 0);
		ini.set(Calendar.HOUR_OF_DAY, 0);
		ini.set(Calendar.MINUTE, 0);
		ini.set(Calendar.SECOND, 0);
		ini.set(Calendar.MILLISECOND, 0);
		fin.set(Calendar.HOUR_OF_DAY, 0);
		fin.set(Calendar.MINUTE, 0);
		fin.set(Calendar.SECOND, 0);
		fin.set(Calendar.MILLISECOND, 0);
		return ((hoy.after(ini) || hoy.equals(ini)) && (hoy.after(fin) || hoy.equals(fin)));
	}

	/**
	 * Valida que una fecha no sea mayor a la fecha actual
	 * 
	 * @param fecha
	 * @return
	 */
	public static boolean validateFecha(Date fecha) {
		Calendar date = Calendar.getInstance();
		Calendar hoy = Calendar.getInstance();
		date.setTime(fecha);
		return (hoy.equals(date) || date.before(hoy));
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
	 * Valida que los datos para la actualizacion de la devolucion son correctos
	 * 
	 * @param devolucionUpdtDTOList
	 * @return
	 */
	public static boolean validateActualizarDevolucionRequest(List<DevolucionUpdtDTO> devolucionUpdtDTOList) {
		if (null == devolucionUpdtDTOList || devolucionUpdtDTOList.isEmpty()) {
			return false;
		} else {
			for (DevolucionUpdtDTO devolucionUpdtDTO : devolucionUpdtDTOList) {
				if (null == devolucionUpdtDTO || null == devolucionUpdtDTO.getLiquidar()
						|| null == devolucionUpdtDTO.getFecha() || null == devolucionUpdtDTO.getIdMovimiento()
						|| devolucionUpdtDTO.getIdMovimiento().compareTo(0) == 0
						|| devolucionUpdtDTO.getIdMovimiento().compareTo(0) < 0)
					return false;
			}
		}
		return true;
	}

	/**
	 * Valida un objeto de tipo FolioRequestDTO para que este no sea nulo y sus
	 * atributos requeridos sean valores validos
	 * 
	 * @param folioRequestDTO
	 * @return
	 */
	public static boolean validateFolioRequestDTO(FolioRequestDTO folioRequestDTO) {
		return (null != folioRequestDTO && null != folioRequestDTO.getFolio() && folioRequestDTO.getFolio() > 0);
	}

	/**
	 * Valida que un objeto de tipo DevolucionRequestDTO no sea nulo y contenga
	 * atributos validos
	 * 
	 * @param devolucionRequestDTO
	 * @return
	 */
	public static boolean validateDevolucionRequestDTO(DevolucionRequestDTO devolucionRequestDTO) {
		return (null != devolucionRequestDTO.getEstatus()
				&& (null == devolucionRequestDTO.getEstatus()
						|| (null != devolucionRequestDTO.getEstatus() && devolucionRequestDTO.getEstatus() < 1))
				&& (null == devolucionRequestDTO.getIdEntidad()
						|| (null != devolucionRequestDTO.getIdEntidad() && devolucionRequestDTO.getIdEntidad() < 1)));
	}

	/**
	 * Valida dos fechas recibidas como fecha inicial y final independientemente si
	 * estas son nulas o no
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 */
	public static void validateFechasPrimary(Date fechaDesde, Date fechaHasta) {
		if (null != fechaDesde && null != fechaHasta && !validateFechasWithThemselves(fechaDesde, fechaHasta))
			throw new ConciliacionException(ConciliacionConstants.WRONG_OR_INCONSISTENT_FECHAS,
					CodigoError.NMP_PMIMONTE_BUSINESS_078);
		if (null != fechaDesde && null != fechaHasta && !validateFechasWithCurrent(fechaDesde, fechaHasta))
			throw new ConciliacionException(ConciliacionConstants.WRONG_OR_INCONSISTENT_FECHAS,
					CodigoError.NMP_PMIMONTE_BUSINESS_082);
		if (null != fechaDesde && null == fechaHasta && !validateFecha(fechaDesde))
			throw new ConciliacionException(ConciliacionConstants.FECHA_IS_WRONG,
					CodigoError.NMP_PMIMONTE_BUSINESS_088);
		if (null == fechaDesde && null != fechaHasta && !validateFecha(fechaHasta))
			throw new ConciliacionException(ConciliacionConstants.FECHA_IS_WRONG,
					CodigoError.NMP_PMIMONTE_BUSINESS_088);
	}

	/**
	 * Valida un objeto de tipo DevolucionesIdsMovimientosDTO para que no sea nulo y
	 * contenga todos los atributos requeridos
	 * 
	 * @param devolucionesIdsMovimientosDTO
	 * @return
	 */
	public static boolean validateDevolucionesIdsMovimientosDTO(
			DevolucionesIdsMovimientosDTO devolucionesIdsMovimientosDTO) {
		return (null != devolucionesIdsMovimientosDTO && null != devolucionesIdsMovimientosDTO.getIdsMovimientos()
				&& !devolucionesIdsMovimientosDTO.getIdsMovimientos().isEmpty());
	}

	/**
	 * Valida un objeto de tipo LiquidacionMovimientosRequestDTO para que no sea
	 * nulo y contenga todos los atributos requeridos con valores validos
	 * 
	 * @param liquidacionMovimientosRequestDTO
	 * @return
	 */
	public static boolean validateLiquidacionMovimientosRequestDTO(
			LiquidacionMovimientosRequestDTO liquidacionMovimientosRequestDTO) {
		if (null == liquidacionMovimientosRequestDTO || null == liquidacionMovimientosRequestDTO.getFolio()
				|| null == liquidacionMovimientosRequestDTO.getMovimientos()
				|| liquidacionMovimientosRequestDTO.getMovimientos().isEmpty())
			return false;
		else {
			for (MovimientosDTO movimiento : liquidacionMovimientosRequestDTO.getMovimientos()) {
				if (null == movimiento || null == movimiento.getFecha() || null == movimiento.getId()
						|| movimiento.getId() <= 0)
					return false;
			}
		}
		return true;
	}

	/**
	 * Valida un objeto de tipo ActualizaionConciliacionRequestDTO para que no sea
	 * nulo y que contenga todos los atributos requeridos y asu vez estos sean
	 * valores valudos
	 * 
	 * @param actualizaionConciliacionRequestDTO
	 * @return
	 */
	public static boolean validateActualizaionConciliacionRequestDTO(
			ActualizaionConciliacionRequestDTO actualizaionConciliacionRequestDTO) {
		// Valida que los objetos principales no sean nulos y/o vacios
		if (null == actualizaionConciliacionRequestDTO || null == actualizaionConciliacionRequestDTO.getFolio()
				|| ((null == actualizaionConciliacionRequestDTO.getComisiones()
						|| actualizaionConciliacionRequestDTO.getComisiones().isEmpty())
						&& (null == actualizaionConciliacionRequestDTO.getMovimientosTransito()
								|| actualizaionConciliacionRequestDTO.getMovimientosTransito().isEmpty())))
			return false;
		else {
			// Si las comisiones no son nulas valida sus atributos
			if (null != actualizaionConciliacionRequestDTO.getComisiones()
					&& !actualizaionConciliacionRequestDTO.getComisiones().isEmpty()) {
				for (ComisionesRequestDTO comision : actualizaionConciliacionRequestDTO.getComisiones()) {
					if (null == comision.getEstatus() || null == comision.getDescripcion()
							|| "".equals(comision.getDescripcion()) || null == comision.getFechaCargo()
							|| null == comision.getFechaOperacion() || null == comision.getId()
							|| comision.getId().compareTo(0) < 0 || null == comision.getMonto()
							|| comision.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
						return false;
					}
				}
			}
			// Si los movimientos en transito no son nulas valida sus atributos
			if (null != actualizaionConciliacionRequestDTO.getMovimientosTransito()
					&& !actualizaionConciliacionRequestDTO.getMovimientosTransito().isEmpty()) {
				for (MovTransitoRequestDTO movTransito : actualizaionConciliacionRequestDTO.getMovimientosTransito()) {
					if (null == movTransito.getId() || movTransito.getId().compareTo(0) < 0
							|| null == movTransito.getTipo() || "".equals(movTransito.getTipo())
							|| (!TipoMovimientoActualizacionTransito.PAGO.getNombre().equals(movTransito.getTipo())
									&& !TipoMovimientoActualizacionTransito.DEVOLUCION.getNombre()
											.equals(movTransito.getTipo()))) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
}
