/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import mx.com.nmp.pagos.mimonte.dto.ComisionSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransProyeccionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransRealDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransaccionesOperacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransaccionesRequestDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccionProyeccion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccionReal;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.OperacionComisionProyeccionEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoComisionEnum;

/**
 * @name ComisionesBuilder
 * @description Clase abstracta que se encarag de convertir objetos Comisiones
 *              de tipo DTO a Entities y viceversa
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 27/05/2019 13:59 hrs.
 * @version 0.1
 */
public abstract class ComisionesBuilder {

	private ComisionesBuilder() {
		super();
	}

	/**
	 * Construye un entity de tipo MovimientoComision a partir de un objeto de tipo
	 * ComisionSaveDTO
	 * 
	 * @param comisionSaveDTO
	 * @param requestuser
	 * @return
	 */
	public static MovimientoComision buildMovimientoComisionFromComisionSaveDTO(final ComisionSaveDTO comisionSaveDTO,
			final String requestuser, final boolean flagNew) {
		MovimientoComision movimientoComision = null;
		if (null != comisionSaveDTO) {
			movimientoComision = new MovimientoComision();
			movimientoComision.setCreatedBy(flagNew ? requestuser : null);
			movimientoComision.setCreatedDate(flagNew ? new Date() : null);
			movimientoComision.setEstatus(true);
			movimientoComision.setNuevo(true);
			movimientoComision.setLastModifiedBy(flagNew ? null : requestuser);
			movimientoComision.setLastModifiedDate(flagNew ? null : new Date());
			movimientoComision.setDescripcion(comisionSaveDTO.getDescripcion());
			movimientoComision.setFechaCargo(comisionSaveDTO.getFechaCargo());
			movimientoComision.setFechaOperacion(comisionSaveDTO.getFechaOperacion());
			movimientoComision.setId(comisionSaveDTO.getId());
			movimientoComision.setIdConciliacion(comisionSaveDTO.getFolio());
			movimientoComision.setMonto(comisionSaveDTO.getMonto());
			movimientoComision.setTipoComision(TipoMovimientoComisionEnum.COMISION);
		}
		return movimientoComision;
	}

	/**
	 * Constrye un entity de tipo ComisionTransaccion a partir de los objetos
	 * ComisionTransaccion, ComisionesTransDTO, ComisionesTransaccionesRequestDTO,
	 * Integer, String
	 * 
	 * @param comisionTransaccionVer
	 * @param comisionesTransDTO
	 * @param comisionesTransaccionesRequestDTO
	 * @param idConciliacion
	 * @param requestUser
	 * @return
	 */
	public static ComisionTransaccion buildComisionTransaccionFromComisionesTransDTOAndComisionesTransaccionesRequestDTO(
			Long comisionTransaccionVer, ComisionesTransDTO comisionesTransDTO,
			ComisionesTransaccionesRequestDTO comisionesTransaccionesRequestDTO, Long idConciliacion,
			String requestUser) {
		ComisionTransaccion comisionTransaccion = null;
		if (null != comisionesTransDTO) {
			comisionTransaccion = new ComisionTransaccion();
			comisionTransaccion.setLastModifiedBy(null != comisionTransaccionVer ? requestUser : null);
			comisionTransaccion.setLastModifiedDate(null != comisionTransaccionVer ? new Date() : null);
			comisionTransaccion.setComision(comisionesTransaccionesRequestDTO.getComision());
			comisionTransaccion.setCreatedBy(null == comisionTransaccionVer ? requestUser : null);
			comisionTransaccion.setCreatedDate(null == comisionTransaccionVer ? new Date() : null);
			comisionTransaccion.setFechaDesde(comisionesTransaccionesRequestDTO.getFechaDesde());
			comisionTransaccion.setFechaHasta(comisionesTransaccionesRequestDTO.getFechaHasta());
			Long id = null != comisionTransaccionVer ? comisionTransaccionVer : 0L;
			comisionTransaccion.setId(id);

			Conciliacion conciliacion = new Conciliacion();
			conciliacion.setId(idConciliacion);

			comisionTransaccion.setConciliacion(conciliacion);
		}
		return comisionTransaccion;
	}

	/**
	 * Construye un objeto de objetos de tipo ComisionTransaccionProyeccion a partir
	 * de los objetos ComisionTransaccion y ComisionesTransDTO
	 * 
	 * @param comisionTransaccion
	 * @param comisionesTransDTO
	 * @return
	 */
	public static List<ComisionTransaccionProyeccion> buildComisionTransaccionProyeccionFromComisionTransaccion(
			List<Long> comisionTransaccionProyeccionList, ComisionTransaccion comisionTransaccion,
			ComisionesTransDTO comisionesTransDTO) {
		List<ComisionTransaccionProyeccion> proyecciones = new ArrayList<>();
		Long idCom = null;
		Long idIva = null;
		if (null != comisionTransaccionProyeccionList && !comisionTransaccionProyeccionList.isEmpty()
				&& comisionTransaccionProyeccionList.size() >= 2) {
			idCom = comisionTransaccionProyeccionList.get(0);
			idIva = comisionTransaccionProyeccionList.get(1);
		}
		proyecciones.add(new ComisionTransaccionProyeccion(null == idCom ? 0L : idCom,
				new ComisionTransaccion(comisionTransaccion.getId()), TipoMovimientoComisionEnum.COMISION.getId(),
				comisionesTransDTO.getProyeccion().getOperaciones().get(0).getTransacciones(),
				comisionesTransDTO.getProyeccion().getOperaciones().get(0).getComision(),
				comisionesTransDTO.getProyeccion().getOperaciones().get(0).getIvaComision(),
				comisionesTransDTO.getProyeccion().getOperaciones().get(0).getTotalComision()));
		proyecciones.add(new ComisionTransaccionProyeccion(null == idIva ? 0L : idIva,
				new ComisionTransaccion(comisionTransaccion.getId()), TipoMovimientoComisionEnum.IVA_COMISION.getId(),
				comisionesTransDTO.getProyeccion().getOperaciones().get(1).getTransacciones(),
				comisionesTransDTO.getProyeccion().getOperaciones().get(1).getComision(),
				comisionesTransDTO.getProyeccion().getOperaciones().get(1).getIvaComision(),
				comisionesTransDTO.getProyeccion().getOperaciones().get(1).getTotalComision()));
		comisionTransaccion.setComisionTransaccionProyeccionSet(proyecciones);
		return proyecciones;
	}

	/**
	 * Construye un objeto de tipo ComisionTransaccionReal a partir de dos objetos:
	 * ComisionTransaccion y ComisionesTransDTO
	 * 
	 * @param comisionTransaccion
	 * @param comisionesTransDTO
	 * @return
	 */
	public static ComisionTransaccionReal buildComisionTransaccionRealFromComisionTransaccion(
			Set<ComisionTransaccionReal> comisionTransaccionRealSet, ComisionTransaccion comisionTransaccion,
			ComisionesTransDTO comisionesTransDTO) {
		ComisionTransaccionReal comisionTransaccionReal = null;
		Long id = null;
		Iterator<ComisionTransaccionReal> it = null;
		if (null != comisionTransaccionRealSet && !comisionTransaccionRealSet.isEmpty()) {
			it = comisionTransaccionRealSet.iterator();
			while (it.hasNext()) {
				id = it.next().getId();
			}
		}
		comisionTransaccionReal = new ComisionTransaccionReal(null == id ? 0L : id,
				new ComisionTransaccion(comisionTransaccion.getId()), comisionesTransDTO.getReal().getComision(),
				comisionesTransDTO.getReal().getIvaComision(), comisionesTransDTO.getReal().getTotalComision());

		return comisionTransaccionReal;
	}

	/**
	 * Construye un objeto de tipo MovimientoComision a partir de un objeto de tipo
	 * ComisionesRequestDTO
	 * 
	 * @param comisionesRequestDTO
	 * @param requestUser
	 * @return
	 */
	public static MovimientoComision buildMovimientoComisionFromComisionesRequestDTO(
			final ComisionesRequestDTO comisionesRequestDTO, final String requestUser, final Long folio) {
		MovimientoComision movimientoComision = null;
		if (null != comisionesRequestDTO) {
			movimientoComision = new MovimientoComision();
			movimientoComision.setCreatedBy(requestUser);
			movimientoComision.setCreatedDate(new Date());
			movimientoComision.setDescripcion(comisionesRequestDTO.getDescripcion());
			movimientoComision.setEstatus(comisionesRequestDTO.getEstatus());
			movimientoComision.setId(comisionesRequestDTO.getId());
			movimientoComision.setMonto(comisionesRequestDTO.getMonto());
			movimientoComision.setFechaOperacion(comisionesRequestDTO.getFechaOperacion());
			movimientoComision.setFechaCargo(comisionesRequestDTO.getFechaCargo());
			movimientoComision.setIdConciliacion(folio);
			movimientoComision.setTipoComision(TipoMovimientoComisionEnum.COMISION);
			movimientoComision.setNuevo(true);
		}
		return movimientoComision;
	}

	/**
	 * Construye un objeto de tipo MovimientoComision a partir de un objeto de tipo
	 * ComisionesRequestDTO para un alta de nuevas comisiones
	 * 
	 * @param comisionesRequestDTO
	 * @param requestUser
	 * @param folio
	 * @param id
	 * @return
	 */
	public static MovimientoComision buildMovimientoComisionFromComisionesRequestDTONew(
			final ComisionesRequestDTO comisionesRequestDTO, final String requestUser, final Long folio, Integer id) {
		MovimientoComision movimientoComision = null;
		if (null != comisionesRequestDTO) {
			movimientoComision = new MovimientoComision();
			movimientoComision.setCreatedBy(requestUser);
			movimientoComision.setCreatedDate(new Date());
			movimientoComision.setDescripcion(comisionesRequestDTO.getDescripcion());
			movimientoComision.setEstatus(comisionesRequestDTO.getEstatus());
			movimientoComision.setId(id);
			movimientoComision.setMonto(comisionesRequestDTO.getMonto());
			movimientoComision.setFechaOperacion(comisionesRequestDTO.getFechaOperacion());
			movimientoComision.setFechaCargo(comisionesRequestDTO.getFechaCargo());
			movimientoComision.setIdConciliacion(folio);
			movimientoComision.setTipoComision(TipoMovimientoComisionEnum.COMISION);
			movimientoComision.setNuevo(true);
		}
		return movimientoComision;
	}

	public static ComisionesTransDTO buildComisionTransaccionDTOFromComisionTransaccion(
			ComisionTransaccion comisionTransaccion) {
		ComisionesTransDTO comisionesDTO = null;
		if (comisionTransaccion != null) {
			comisionesDTO = new ComisionesTransDTO();
			comisionesDTO.setComision(comisionTransaccion.getComision());
			comisionesDTO.setFechaDesde(comisionTransaccion.getFechaDesde());
			comisionesDTO.setFechaHasta(comisionTransaccion.getFechaHasta());
			comisionesDTO.setProyeccion(buildComisionesTransProyeccionDTOFromComisionesTransaccionProyeccion(
					comisionTransaccion.getComisionTransaccionProyeccionSet()));
			comisionesDTO.setReal(buildComisionesRealDTOFROMComisionTransaccionReal(
					comisionTransaccion.getComisionTransaccionRealSet()));

		}
		return comisionesDTO;
	}

	/**
	 * Construye un objeto de tipo ComisionesTransProyeccionDTO a partir de una
	 * lista de objetos de tipo ComisionTransaccionProyeccion
	 * 
	 * @param comisionTransaccionProyeccionSet
	 * @return
	 */
	private static ComisionesTransProyeccionDTO buildComisionesTransProyeccionDTOFromComisionesTransaccionProyeccion(
			List<ComisionTransaccionProyeccion> comisionTransaccionProyeccionSet) {
		List<ComisionesTransaccionesOperacionDTO> operaciones = null;
		ComisionesTransProyeccionDTO proy = null;
		String opName = null;
		if (comisionTransaccionProyeccionSet != null && !comisionTransaccionProyeccionSet.isEmpty()) {
			proy = new ComisionesTransProyeccionDTO();
			BigDecimal totalOperaciones = new BigDecimal(0);
			operaciones = new ArrayList<>();
			for (ComisionTransaccionProyeccion comisionTransaccionProyeccion : comisionTransaccionProyeccionSet) {
				if (null != comisionTransaccionProyeccion.getOperacion()) {
					opName = null != OperacionComisionProyeccionEnum
							.getById(comisionTransaccionProyeccion.getOperacion()) ? OperacionComisionProyeccionEnum
									.getById(comisionTransaccionProyeccion.getOperacion().intValue()).getDescripcion()
									: null;
				}
				ComisionesTransaccionesOperacionDTO operacion = new ComisionesTransaccionesOperacionDTO(opName,
						comisionTransaccionProyeccion.getTransacciones(), comisionTransaccionProyeccion.getComision(),
						comisionTransaccionProyeccion.getIvaComision(), comisionTransaccionProyeccion.getTotal());
				operaciones.add(operacion);
				totalOperaciones = totalOperaciones.add(comisionTransaccionProyeccion.getTotal());
			}
			proy.setOperaciones(operaciones);
			proy.setTotalOperaciones(totalOperaciones);
		}
		return proy;
	}

	private static ComisionesTransRealDTO buildComisionesRealDTOFROMComisionTransaccionReal(
			Set<ComisionTransaccionReal> comisionTransaccionRealSet) {
		ComisionesTransRealDTO real = null;
		if (comisionTransaccionRealSet != null && comisionTransaccionRealSet.size() > 0) {
			real = new ComisionesTransRealDTO();
			real.setComision(comisionTransaccionRealSet.iterator().next().getComision());
			real.setIvaComision(comisionTransaccionRealSet.iterator().next().getIvaComision());
			real.setTotalComision(comisionTransaccionRealSet.iterator().next().getTotal());
		}
		return real;
	}

}
