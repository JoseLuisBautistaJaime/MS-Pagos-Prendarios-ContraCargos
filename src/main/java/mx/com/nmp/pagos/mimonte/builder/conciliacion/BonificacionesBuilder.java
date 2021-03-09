/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestConsultaPagosResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.BonificacionDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoBonificacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoBonificacionReferencia;

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
		List<BonificacionDTO> listDTO = new ArrayList<BonificacionDTO>();
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
			dto.setSucursal(bonificacion.getSucursal());
			dto.setCreatedBy(bonificacion.getCreatedBy());
			dto.setCreatedDate(bonificacion.getCreatedDate());
			dto.setLastModifiedBy(bonificacion.getLastModifiedBy());
			dto.setLastModifiedDate(bonificacion.getLastModifiedDate());
		}
		return dto;
	}

	/**
	 * Construye la entidad a apartir del dto y/o la referencia
	 * @param dto
	 * @param usuario 
	 * @param referencia
	 * @return
	 */
	public static MovimientoBonificacion build(BonificacionDTO dto, String usuario, BusRestConsultaPagosResponseDTO referenciaResponseDTO) {
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
			bonificacion.setSucursal(dto.getSucursal());
			bonificacion.setFolioBonificacion(dto.getFolioBonificacion());
			bonificacion.setEstatus(EstatusBonificacionesBuilder.build(dto.getEstatus()));
			bonificacion.setIdConciliacion(dto.getFolio());
			if (dto.getId() == null || dto.getId() <= 0) {
				bonificacion.setCreatedBy(usuario);
				bonificacion.setCreatedDate(new Date());
			}
			else {
				bonificacion.setLastModifiedBy(usuario);
				bonificacion.setLastModifiedDate(new Date());
			}

			// No existe la refencia, se crea la partida asociandola a la sucursal
			List<MovimientoBonificacionReferencia> referencias = null;
			if (referenciaResponseDTO == null) {
				referencias = buildReferencia(bonificacion);
			}
			else {
				referencias = buildReferencia(referenciaResponseDTO, bonificacion);
			}
			bonificacion.setReferencias(referencias);
		}
		return bonificacion;
	}

	private static List<MovimientoBonificacionReferencia> buildReferencia(MovimientoBonificacion bonificacion) {
		List<MovimientoBonificacionReferencia> referencias = new ArrayList<MovimientoBonificacionReferencia>();
		MovimientoBonificacionReferencia referencia = new MovimientoBonificacionReferencia();
		referencia.setMovimientoBonificacion(bonificacion);
		referencia.setMonto(bonificacion.getImporteML());
		referencia.setReferencia(bonificacion.getFolioBonificacion());
		referencia.setSucursal(bonificacion.getSucursal());
		referencias.add(referencia);
		return referencias;
	}


	private static List<MovimientoBonificacionReferencia> buildReferencia(BusRestConsultaPagosResponseDTO referenciaDTO, MovimientoBonificacion bonificacion) {
		List<MovimientoBonificacionReferencia> referencias = new ArrayList<MovimientoBonificacionReferencia>();
		if (referenciaDTO.getPagos() != null && referenciaDTO.getPagos().size() > 0
				&& referenciaDTO.getPagos().get(0).getPartidas() != null
				&& referenciaDTO.getPagos().get(0).getPartidas().size() > 0) {
			for (BusRestConsultaPagosResponseDTO.Partidas partida : referenciaDTO.getPagos().get(0).getPartidas()) {
				MovimientoBonificacionReferencia referencia = new MovimientoBonificacionReferencia();
				referencia.setMovimientoBonificacion(bonificacion);
				referencia.setMonto(partida.getImporteTransaccion());
				referencia.setReferencia(String.valueOf(partida.getIdPartida()));
				referencia.setSucursal(Integer.valueOf(partida.getSucursal()).longValue());
				referencias.add(referencia);
			}
		}
		else {
			throw new ConciliacionException("Referencia con datos incorrectos",
					CodigoError.NMP_PMIMONTE_0001);
		}
		return referencias;
	}

	public static MovimientoBonificacion buildBonificaciones(MovimientoBonificacion movBonificacionBD, Long idConciliacion) {
		MovimientoBonificacion movBonificacion = new MovimientoBonificacion();
		movBonificacion.setAsignacion(movBonificacionBD.getAsignacion());
		movBonificacion.setCreatedBy(movBonificacionBD.getCreatedBy());
		movBonificacion.setCreatedDate(movBonificacionBD.getCreatedDate());
		movBonificacion.setEstatus(movBonificacionBD.getEstatus());
		movBonificacion.setFolioBonificacion(movBonificacionBD.getFolioBonificacion());
		movBonificacion.setIdConciliacion(idConciliacion);
		movBonificacion.setImporteML(movBonificacionBD.getImporteML());
		movBonificacion.setLastModifiedBy(movBonificacionBD.getLastModifiedBy());
		movBonificacion.setLastModifiedDate(movBonificacionBD.getLastModifiedDate());
		movBonificacion.setNumDoc(movBonificacionBD.getNumDoc());
		movBonificacion.setFechaDoc(movBonificacionBD.getFechaDoc());
		movBonificacion.setPlaza(movBonificacionBD.getPlaza());
		movBonificacion.setSucursal(movBonificacionBD.getSucursal());
		movBonificacion.setTienda(movBonificacionBD.getTienda());
		movBonificacion.setReferencias(buildReferencias(movBonificacionBD.getReferencias()));
		return movBonificacion;
	}

	private static List<MovimientoBonificacionReferencia> buildReferencias(List<MovimientoBonificacionReferencia> movsReferenciasBD) {
		List<MovimientoBonificacionReferencia> movsReferencias = new ArrayList<MovimientoBonificacionReferencia>();
		if (movsReferenciasBD != null && movsReferenciasBD.size() > 0) {
			for (MovimientoBonificacionReferencia movReferenciaBD : movsReferenciasBD) {
				MovimientoBonificacionReferencia movReferencia = new MovimientoBonificacionReferencia();
				movReferencia.setMonto(movReferenciaBD.getMonto());
				movReferencia.setMovimientoBonificacion(movReferenciaBD.getMovimientoBonificacion());
				movReferencia.setReferencia(movReferenciaBD.getReferencia());
				movReferencia.setSucursal(movReferenciaBD.getSucursal());
				movsReferencias.add(movReferencia);
			}
		}
		return movsReferencias;
	}

}
