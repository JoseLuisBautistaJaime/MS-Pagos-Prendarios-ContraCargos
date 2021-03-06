/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.nmp.pagos.mimonte.dto.BaseEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO2;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDetalleDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusDevolucionDTO;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.EstatusDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;

/**
 * @name DevolucionesBuilder
 * @description Builder que se encaraga de fabricar objetos a partir de entities
 *              y viceversa
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 23/05/2019 17:04 hrs.
 * @version 0.1
 */
public class DevolucionesBuilder {

	public DevolucionesBuilder() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * ocupa
	 * 
	 * @param movimientoDevolucion
	 * @return
	 */
	public static List<DevolucionConDTO> buildDevolucionConDTOListFromMovimientoDevolucionList(
			List<MovimientoDevolucion> movimientoDevolucion) {
		List<DevolucionConDTO> devolucionConDTOList = null;
		if (movimientoDevolucion != null && !movimientoDevolucion.isEmpty()) {
			devolucionConDTOList = new ArrayList<>();
			for (MovimientoDevolucion movimientosDevolucion : movimientoDevolucion) {
				devolucionConDTOList.add(buildDevolucionConDTOFromMovimientoDevolucion(movimientosDevolucion));
			}
		}
		return devolucionConDTOList;
	}

	/**
	 * ocupa
	 * 
	 * @param movimientoDevolucion
	 * @return
	 */
	public static DevolucionConDTO buildDevolucionConDTOFromMovimientoDevolucion(
			MovimientoDevolucion movimientoDevolucion) {
		DevolucionConDTO devolucionConDTO = null;
		if (movimientoDevolucion != null) {
			devolucionConDTO = new DevolucionConDTO();
			devolucionConDTO.setId(movimientoDevolucion.getId());
			devolucionConDTO.setEstatus(EstatusDevolucionesBuilder
					.buildEstatusDevolucionDTOFromEstatusDevolucion(movimientoDevolucion.getEstatus()));
			devolucionConDTO.setFecha(movimientoDevolucion.getFecha());
			devolucionConDTO.setMonto(movimientoDevolucion.getMonto());
			devolucionConDTO.setEsquemaTarjeta(movimientoDevolucion.getEsquemaTarjeta());
			devolucionConDTO.setIdentificacionCuenta(movimientoDevolucion.getIdentificadorCuenta());
			devolucionConDTO.setTitular(movimientoDevolucion.getTitular());
			devolucionConDTO.setCodigoAutorizacion(movimientoDevolucion.getCodigoAutorizacion());
			devolucionConDTO.setSucursal(movimientoDevolucion.getSucursal());
			devolucionConDTO.setFechaLiquidacion(movimientoDevolucion.getFechaLiquidacion());
		}
		return devolucionConDTO;
	}

	/**
	 * ocupa
	 * 
	 * @param devoluciones
	 * @return
	 */
	public static List<DevolucionEntidadDTO> buildDevolucionEntidadDTOListFromDevolucionEntidadDetalleDTOList(
			List<DevolucionEntidadDetalleDTO> devoluciones) {
		List<DevolucionEntidadDTO> devolucionEntidadDTOList = null;
		if (devoluciones != null && !devoluciones.isEmpty()) {
			devolucionEntidadDTOList = new ArrayList<>();
			for (DevolucionEntidadDetalleDTO d : devoluciones) {
				devolucionEntidadDTOList.add(buildDevolucionEntidadDTOFromDevolucionEntidadDetalleDTO(d));
			}
		}
		return devolucionEntidadDTOList;
	}

	/**
	 * ocupa
	 * 
	 * @param devolucionEntidadDetalleDTO
	 * @return
	 */
	public static DevolucionEntidadDTO buildDevolucionEntidadDTOFromDevolucionEntidadDetalleDTO(
			DevolucionEntidadDetalleDTO devolucionEntidadDetalleDTO) {
		DevolucionEntidadDTO devolucionEntidadDTO = null;
		if (devolucionEntidadDetalleDTO != null) {
			devolucionEntidadDTO = new DevolucionEntidadDTO();
			devolucionEntidadDTO.setId(devolucionEntidadDetalleDTO.getId());
			devolucionEntidadDTO.setFecha(devolucionEntidadDetalleDTO.getFecha());
			devolucionEntidadDTO.setSucursal(devolucionEntidadDetalleDTO.getSucursal());
			devolucionEntidadDTO.setIdentificadorCuenta(devolucionEntidadDetalleDTO.getIdentificadorCuenta());
			devolucionEntidadDTO.setMonto(devolucionEntidadDetalleDTO.getMonto());
			devolucionEntidadDTO.setEsquemaTarjeta(devolucionEntidadDetalleDTO.getEsquemaTarjeta());
			devolucionEntidadDTO.setTitular(devolucionEntidadDetalleDTO.getTitular());
			devolucionEntidadDTO.setCodigoAutorizacion(devolucionEntidadDetalleDTO.getCodigoAutorizacion());
			devolucionEntidadDTO.setFechaLiquidacion(devolucionEntidadDetalleDTO.getFechaLiquidacion());
			devolucionEntidadDTO.setEntidad(new BaseEntidadDTO(devolucionEntidadDetalleDTO.getIdEntidad(),
					devolucionEntidadDetalleDTO.getNombreEntidad(),
					devolucionEntidadDetalleDTO.getDescripcionEntidad()));
			devolucionEntidadDTO.setEstatus(new EstatusDevolucionDTO(devolucionEntidadDetalleDTO.getIdEstatus(),
					devolucionEntidadDetalleDTO.getDescripcionEstatus(), devolucionEntidadDetalleDTO.getEstatus()));

		}
		return devolucionEntidadDTO;
	}

	public static List<DevolucionEntidadDTO> buildDevolucionEntidadDTOListFromMovimientoDevolucionList(
			List<MovimientoDevolucion> movimientosDevolucion) {
		List<DevolucionEntidadDTO> devolucionEntidadDTOList = null;
		if (movimientosDevolucion != null && !movimientosDevolucion.isEmpty()) {
			devolucionEntidadDTOList = new ArrayList<>();
			for (MovimientoDevolucion md : movimientosDevolucion) {
				devolucionEntidadDTOList.add(buildDevolucionEntidadDTOFromMovimientoDevolucion(md));
			}
		}
		return devolucionEntidadDTOList;
	}

	public static DevolucionEntidadDTO buildDevolucionEntidadDTOFromMovimientoDevolucion(
			MovimientoDevolucion movimientoDevolucion) {
		DevolucionEntidadDTO devolucionEntidadDTO = null;
		if (movimientoDevolucion != null) {
			devolucionEntidadDTO = new DevolucionEntidadDTO();
			devolucionEntidadDTO.setId(movimientoDevolucion.getId());
			devolucionEntidadDTO.setFecha(movimientoDevolucion.getFecha());
			devolucionEntidadDTO.setSucursal(movimientoDevolucion.getSucursal());
			devolucionEntidadDTO.setIdentificadorCuenta(movimientoDevolucion.getIdentificadorCuenta());
			devolucionEntidadDTO.setMonto(movimientoDevolucion.getMonto());
			devolucionEntidadDTO.setEsquemaTarjeta(movimientoDevolucion.getEsquemaTarjeta());
			devolucionEntidadDTO.setTitular(movimientoDevolucion.getTitular());
			devolucionEntidadDTO.setCodigoAutorizacion(movimientoDevolucion.getCodigoAutorizacion());
			devolucionEntidadDTO.setFechaLiquidacion(movimientoDevolucion.getFechaLiquidacion());
			devolucionEntidadDTO.setEstatus(EstatusDevolucionesBuilder
					.buildEstatusDevolucionDTOFromEstatusDevolucionSolicita(movimientoDevolucion.getEstatus()));
		}
		return devolucionEntidadDTO;
	}

	/**
	 * Construye un objeto de tipo DevolucionEntidadDTO a partir de un entity de
	 * tipo MovimientoDevolucion y un map con las entidades
	 * 
	 * @param movimientoDevolucion
	 * @param entidades
	 * @return
	 */
	public static DevolucionEntidadDTO buildDevolucionEntidadDTOFromMovimientoDevolucion(
			MovimientoDevolucion movimientoDevolucion, Map<Integer, BaseEntidadDTO> entidades) {
		DevolucionEntidadDTO devolucionEntidadDTO = null;
		if (movimientoDevolucion != null) {
			devolucionEntidadDTO = new DevolucionEntidadDTO();
			devolucionEntidadDTO.setId(movimientoDevolucion.getId());
			devolucionEntidadDTO.setFecha(movimientoDevolucion.getFecha());
			devolucionEntidadDTO.setSucursal(movimientoDevolucion.getSucursal());
			devolucionEntidadDTO.setIdentificadorCuenta(movimientoDevolucion.getIdentificadorCuenta());
			devolucionEntidadDTO.setMonto(movimientoDevolucion.getMonto());
			devolucionEntidadDTO.setEsquemaTarjeta(movimientoDevolucion.getEsquemaTarjeta());
			devolucionEntidadDTO.setTitular(movimientoDevolucion.getTitular());
			devolucionEntidadDTO.setCodigoAutorizacion(movimientoDevolucion.getCodigoAutorizacion());
			devolucionEntidadDTO.setFechaLiquidacion(movimientoDevolucion.getFechaLiquidacion());
			devolucionEntidadDTO.setEstatus(EstatusDevolucionesBuilder
					.buildEstatusDevolucionDTOFromEstatusDevolucionSolicita(movimientoDevolucion.getEstatus()));
			devolucionEntidadDTO.setEntidad(entidades.get(movimientoDevolucion.getId()));
		}
		return devolucionEntidadDTO;
	}

	public static List<DevolucionEntidadDTO> buildDevolucionEntidadDTOListFromConciliacionList(
			List<Entidad> entidadList) {
		List<DevolucionEntidadDTO> DevolucionEntidadDTOList = null;
		if (entidadList != null && !entidadList.isEmpty()) {
			DevolucionEntidadDTOList = new ArrayList<>();
			for (Entidad entidad : entidadList) {
				DevolucionEntidadDTOList.add(buildDevolucionEntidadDTOFromConciliacion(entidad));
			}
		}
		return DevolucionEntidadDTOList;
	}

	public static DevolucionEntidadDTO buildDevolucionEntidadDTOFromConciliacion(Entidad entidad) {
		DevolucionEntidadDTO devolucionEntidadDTO = null;
		if (entidad != null) {
			devolucionEntidadDTO = new DevolucionEntidadDTO();
			devolucionEntidadDTO.setEntidad(EntidadBuilder.buildBaseEntidadDTOFromEntidad(entidad));
		}
		return null;

	}

	public static BaseEntidadDTO buildBaseEntidadDTOFromConciliacion(Conciliacion conciliacion) {
		BaseEntidadDTO baseEntidadDTO = null;
		if (conciliacion != null) {
			baseEntidadDTO = new BaseEntidadDTO();
			baseEntidadDTO.setId(conciliacion.getEntidad().getId());
			baseEntidadDTO.setNombre(conciliacion.getEntidad().getNombre());
			baseEntidadDTO.setDescripcion(conciliacion.getEntidad().getDescription());
		}
		return baseEntidadDTO;

	}

	/**
	 * Construye un objeto de tipo EstatusDevolucionDTO a partir de un entitie de
	 * tipo EstatusDevolucion.
	 * 
	 * @param estatusDevolucion
	 * @return
	 */
	public static EstatusDevolucionDTO buildEstatusDevolucionDTOEstatusDevolucion(EstatusDevolucion estatusDevolucion) {
		EstatusDevolucionDTO estatusDevolucionDTO = null;
		if (estatusDevolucion != null) {
			estatusDevolucionDTO = new EstatusDevolucionDTO();
			estatusDevolucionDTO.setId(estatusDevolucion.getId());
			estatusDevolucionDTO.setDescripcion(estatusDevolucion.getDescripcion());
			estatusDevolucionDTO.setEstatus(estatusDevolucion.getEstatus());
		}
		return estatusDevolucionDTO;

	}

	/**
	 * Construye un objeto de tipo DevolucionEntidadDTO a partir de los entities
	 * MovimientoDevolucion y Conciliacion.
	 * 
	 * @param movimientosDevolucion
	 * @param entidad
	 * @return
	 */
	public static DevolucionEntidadDTO buildDevolucionEntidadDTOFromMovimientosDevolucion(
			MovimientoDevolucion movimientosDevolucion, Entidad entidad) {
		DevolucionEntidadDTO devolucionEntidadDTO = null;
		if (movimientosDevolucion != null) {
			devolucionEntidadDTO = new DevolucionEntidadDTO();
			devolucionEntidadDTO.setId(movimientosDevolucion.getId());
			devolucionEntidadDTO.setEntidad(EntidadBuilder.buildBaseEntidadDTOFromEntidad(entidad));
			devolucionEntidadDTO.setFecha(movimientosDevolucion.getFecha());
			devolucionEntidadDTO
					.setEstatus(buildEstatusDevolucionDTOEstatusDevolucion(movimientosDevolucion.getEstatus()));
			devolucionEntidadDTO.setSucursal(movimientosDevolucion.getSucursal());
			devolucionEntidadDTO.setIdentificadorCuenta(movimientosDevolucion.getIdentificadorCuenta());
			devolucionEntidadDTO.setMonto(movimientosDevolucion.getMonto());
			devolucionEntidadDTO.setEsquemaTarjeta(movimientosDevolucion.getEsquemaTarjeta());
			devolucionEntidadDTO.setTitular(movimientosDevolucion.getTitular());
			devolucionEntidadDTO.setCodigoAutorizacion(movimientosDevolucion.getCodigoAutorizacion());
			devolucionEntidadDTO.setFechaLiquidacion(movimientosDevolucion.getFechaLiquidacion());
		}
		return devolucionEntidadDTO;
	}

	/**
	 * Construye dos objetos uno de tipo DevolucionEntidadDTOAndDTO y otro de tipo
	 * DevolucionEntidadDTOAndDTO2 a partir de un entity de tipo
	 * MovimientoDevolucion
	 * 
	 * @param movimientosDevolucion
	 * @param entidad
	 * @return
	 */
	public static Map<String, Object> buildDevolucionEntidadDTOAndDTO2FromMovimientosDevolucion(
			MovimientoDevolucion movimientosDevolucion, Entidad entidad, String cuenta) {
		DevolucionEntidadDTO devolucionEntidadDTO = null;
		DevolucionEntidadDTO2 devolucionEntidadDTO2 = null;
		Map<String, Object> map = null;
		if (movimientosDevolucion != null) {
			map = new HashMap<>();
			devolucionEntidadDTO = new DevolucionEntidadDTO();
			devolucionEntidadDTO2 = new DevolucionEntidadDTO2();
			// DTO
			devolucionEntidadDTO.setId(movimientosDevolucion.getId());
			devolucionEntidadDTO.setEntidad(EntidadBuilder.buildBaseEntidadDTOFromEntidad(entidad));
			devolucionEntidadDTO.setFecha(movimientosDevolucion.getFecha());
			devolucionEntidadDTO
					.setEstatus(buildEstatusDevolucionDTOEstatusDevolucion(movimientosDevolucion.getEstatus()));
			devolucionEntidadDTO.setSucursal(movimientosDevolucion.getSucursal());
			devolucionEntidadDTO.setIdentificadorCuenta(movimientosDevolucion.getIdentificadorCuenta());
			devolucionEntidadDTO.setMonto(movimientosDevolucion.getMonto());
			devolucionEntidadDTO.setEsquemaTarjeta(movimientosDevolucion.getEsquemaTarjeta());
			devolucionEntidadDTO.setTitular(movimientosDevolucion.getTitular());
			devolucionEntidadDTO.setCodigoAutorizacion(movimientosDevolucion.getCodigoAutorizacion());
			devolucionEntidadDTO.setFechaLiquidacion(movimientosDevolucion.getFechaLiquidacion());
			// DTO2
			devolucionEntidadDTO2.setId(movimientosDevolucion.getId());
			devolucionEntidadDTO2.setEntidad(EntidadBuilder.buildBaseEntidadDTOFromEntidad(entidad));
			devolucionEntidadDTO2.setFecha(movimientosDevolucion.getFecha());
			devolucionEntidadDTO2
					.setEstatus(buildEstatusDevolucionDTOEstatusDevolucion(movimientosDevolucion.getEstatus()));
			devolucionEntidadDTO2.setSucursal(movimientosDevolucion.getSucursal());
			devolucionEntidadDTO2.setIdentificadorCuenta(movimientosDevolucion.getIdentificadorCuenta());
			devolucionEntidadDTO2.setMonto(movimientosDevolucion.getMonto());
			devolucionEntidadDTO2.setEsquemaTarjeta(movimientosDevolucion.getEsquemaTarjeta());
			devolucionEntidadDTO2.setTitular(movimientosDevolucion.getTitular());
			devolucionEntidadDTO2.setCodigoAutorizacion(movimientosDevolucion.getCodigoAutorizacion());
			devolucionEntidadDTO2.setFechaLiquidacion(movimientosDevolucion.getFechaLiquidacion());
			devolucionEntidadDTO2.setHora(movimientosDevolucion.getCreatedDate());
			devolucionEntidadDTO2.setCuenta(cuenta);
			map.put("DTO", devolucionEntidadDTO);
			map.put("DTO2", devolucionEntidadDTO2);
		}
		return map;
	}

	/**
	 * Construye una lista de objetos de tipo DevolucionEntidadDTO a partir de ua
	 * lista de entities de tipo MovimientoDevolucion
	 * 
	 * @param movimientosDevolucion
	 * @param entidad
	 * @return
	 */
	public static List<DevolucionEntidadDTO> buildDevolucionEntidadDTOListFromMovimientoDevolucionLista(
			List<MovimientoDevolucion> movimientosDevolucion, Entidad entidad) {
		List<DevolucionEntidadDTO> devolucionEntidadDTOList = null;
		if (movimientosDevolucion != null && !movimientosDevolucion.isEmpty()) {
			devolucionEntidadDTOList = new ArrayList<>();
			for (MovimientoDevolucion md : movimientosDevolucion) {
				devolucionEntidadDTOList.add(buildDevolucionEntidadDTOFromMovimientosDevolucion(md, entidad));
			}
		}
		return devolucionEntidadDTOList;
	}

	/**
	 * Construye una lista de objetos de tipo Integer a partir de una lista de
	 * objetos de tipo DevolucionUpdtDTO
	 * 
	 * @param devolucionUpdtDTOList
	 * @return
	 */
	public static List<Integer> buildIntegerListFromDevolucionUpdtDTOList(
			List<DevolucionUpdtDTO> devolucionUpdtDTOList) {
		List<Integer> list = null;
		if (null != devolucionUpdtDTOList) {
			list = new ArrayList<>();
			for (DevolucionUpdtDTO devolucionUpdtDTO : devolucionUpdtDTOList) {
				list.add(devolucionUpdtDTO.getIdMovimiento());
			}
		}
		return list;
	}

}
