/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.Date;

import mx.com.nmp.pagos.mimonte.dto.ComisionSaveDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoConciliacion;

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
}
