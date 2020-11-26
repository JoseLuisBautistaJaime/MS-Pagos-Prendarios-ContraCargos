/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.List;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.BonificacionDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoBonificacion;

/**
 * @name BonificacionesBuilder
 * @description Builder que se encaraga de fabricar objetos a partir de entities
 *              y viceversa
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 23/05/2019 17:04 hrs.
 * @version 0.1
 */
public class BonificacionesBuilder {

	public BonificacionesBuilder() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * ocupa
	 * 
	 * @param bonificaciones
	 * @return
	 */
	public static List<BonificacionDTO> buildListDTO(List<MovimientoBonificacion> bonificaciones) {
		List<BonificacionDTO> listDTO = null;
		if (bonificaciones != null && !bonificaciones.isEmpty()) {
			listDTO = new ArrayList<BonificacionDTO>();
			for (MovimientoBonificacion bonificacion : bonificaciones) {
				listDTO.add(buildDTO(bonificacion));
			}
		}
		return listDTO;
	}


	/**
	 * Construye el dto a partir de la entidad
	 * @param bonificacion
	 * @return
	 */
	public static BonificacionDTO buildDTO(MovimientoBonificacion bonificacion) {
		BonificacionDTO dto = null;
		if (bonificacion != null) {
			dto = new BonificacionDTO();
			dto.setId(bonificacion.getId());
			dto.setAsignacion(bonificacion.getAsignacion());
			dto.setNumDoc(bonificacion.getNumDoc());
			dto.setFechaDoc(bonificacion.getFechaDoc());
			dto.setTienda(bonificacion.getTienda());
			dto.setPlaza(bonificacion.getPlaza());
			dto.setImporteML(bonificacion.getImporteML());
			dto.setFolioBonificacion(bonificacion.getFolioBonificacion());
			dto.setEstatus(EstatusBonificacionesBuilder.buildDTO(bonificacion.getEstatus()));
			dto.setFolio(bonificacion.getIdConciliacion());
			dto.setCreatedBy(bonificacion.getCreatedBy());
			dto.setCreatedDate(bonificacion.getCreatedDate());
			dto.setLastModifiedBy(bonificacion.getLastModifiedBy());
			dto.setLastModifiedDate(bonificacion.getLastModifiedDate());
		}
		return dto;
	}

	/**
	 * Construye la entidad a apartir del dto
	 * @param dto
	 * @return
	 */
	public static MovimientoBonificacion build(BonificacionDTO dto) {
		MovimientoBonificacion bonificacion = null;
		if (dto != null) {
			bonificacion = new MovimientoBonificacion();
			bonificacion.setId(dto.getId());
			bonificacion.setAsignacion(dto.getAsignacion());
			bonificacion.setNumDoc(dto.getNumDoc());
			bonificacion.setFechaDoc(dto.getFechaDoc());
			bonificacion.setTienda(dto.getTienda());
			bonificacion.setPlaza(dto.getPlaza());
			bonificacion.setImporteML(dto.getImporteML());
			bonificacion.setFolioBonificacion(dto.getFolioBonificacion());
			bonificacion.setEstatus(EstatusBonificacionesBuilder.build(dto.getEstatus()));
			bonificacion.setIdConciliacion(dto.getFolio());
			bonificacion.setCreatedBy(dto.getCreatedBy());
			bonificacion.setCreatedDate(dto.getCreatedDate());
			bonificacion.setLastModifiedBy(dto.getLastModifiedBy());
			bonificacion.setLastModifiedDate(dto.getLastModifiedDate());
		}
		return bonificacion;
	}

}
