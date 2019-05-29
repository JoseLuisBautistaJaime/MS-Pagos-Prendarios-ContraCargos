/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.ComisionSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransaccionesRequestDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccionProyeccion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccionReal;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoEnum;

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
			final String requestuser) {
		MovimientoComision movimientoComision = null;
		if (null != comisionSaveDTO) {
			movimientoComision = new MovimientoComision();
			movimientoComision.setCreatedBy(requestuser);
			movimientoComision.setCreatedDate(new Date());
			movimientoComision.setEstatus(true);
			movimientoComision.setNuevo(true);
			movimientoComision.setLastModifiedBy(null);
			movimientoComision.setLastModifiedDate(null);
			movimientoComision.setDescripcion(comisionSaveDTO.getDescripcion());
			movimientoComision.setFechaCargo(comisionSaveDTO.getFechaCargo());
			movimientoComision.setFechaOperacion(comisionSaveDTO.getFechaOperacion());
			movimientoComision.setId(comisionSaveDTO.getId());
			movimientoComision.setIdConciliacion(comisionSaveDTO.getFolio());
			movimientoComision.setMonto(comisionSaveDTO.getMonto());
		}
		return movimientoComision;
	}

	/**
	 * Construye un entity de tipo MovimientoConciliacion a partir de un id de
	 * conciliacion y un usuario de request header
	 * 
	 * @param conciliacionId
	 * @param requestUser
	 * @return
	 */
	public static MovimientoConciliacion buildMovimientoConciliacionFromConciliacionIdAndRequestUser(
			final Integer conciliacionId, final String requestUser) {
		MovimientoConciliacion movimientoConciliacion = null;
		if (null != conciliacionId && conciliacionId > 0) {
			movimientoConciliacion = new MovimientoConciliacion();
			movimientoConciliacion.setNuevo(true);
			movimientoConciliacion.setCreatedDate(new Date());
			movimientoConciliacion.setLastModifiedBy(null);
			movimientoConciliacion.setLastModifiedDate(null);
			movimientoConciliacion.setCreatedBy(requestUser);
			movimientoConciliacion.setIdConciliacion(conciliacionId);
		}
		return movimientoConciliacion;
	}

	public static ComisionTransaccion buildComisionTransaccionFromComisionesTransDTOAndComisionesTransaccionesRequestDTO(
			ComisionesTransDTO comisionesTransDTO, ComisionesTransaccionesRequestDTO comisionesTransaccionesRequestDTO,
			Integer idConciliacion, String requestUser) {
		ComisionTransaccion comisionTransaccion = null;
		if (null != comisionesTransDTO) {
			comisionTransaccion = new ComisionTransaccion();
			comisionTransaccion.setLastModifiedBy(null);
			comisionTransaccion.setLastModifiedDate(null);
			comisionTransaccion.setComision(comisionesTransDTO.getReal().getComision());
			Set<ComisionTransaccionProyeccion> proyecciones = new TreeSet<>();
			proyecciones.add(new ComisionTransaccionProyeccion(0L, TipoMovimientoEnum.COMISION.getId(),
					comisionesTransDTO.getProyeccion().getOperaciones().get(0).getTransacciones(),
					comisionesTransDTO.getProyeccion().getOperaciones().get(0).getComision(),
					comisionesTransDTO.getProyeccion().getOperaciones().get(0).getIvaComision(),
					comisionesTransDTO.getProyeccion().getOperaciones().get(0).getTotalComision()));
			proyecciones.add(new ComisionTransaccionProyeccion(0L, TipoMovimientoEnum.IVA_COMISION.getId(),
					comisionesTransDTO.getProyeccion().getOperaciones().get(1).getTransacciones(),
					comisionesTransDTO.getProyeccion().getOperaciones().get(1).getComision(),
					comisionesTransDTO.getProyeccion().getOperaciones().get(1).getIvaComision(),
					comisionesTransDTO.getProyeccion().getOperaciones().get(1).getTotalComision()));
			comisionTransaccion.setComisionTransaccionProyeccionSet(proyecciones);
			Set<ComisionTransaccionReal> reales = new TreeSet<>();
			reales.add(new ComisionTransaccionReal(0L, comisionesTransDTO.getReal().getComision(),
					comisionesTransDTO.getReal().getIvaComision(), comisionesTransDTO.getReal().getTotalComision()));
			comisionTransaccion.setComisionTransaccionRealSet(reales);
			comisionTransaccion.setCreatedBy(requestUser);
			comisionTransaccion.setCreatedDate(new Date());
			comisionTransaccion.setFechaDesde(comisionesTransaccionesRequestDTO.getFechaDesde());
			comisionTransaccion.setFechaHasta(comisionesTransaccionesRequestDTO.getFechaHasta());
			comisionTransaccion.setId(0L);
			comisionTransaccion.setIdConciliacion(idConciliacion);
		}
		return comisionTransaccion;
	}
}
