/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.com.nmp.pagos.mimonte.aspects.ActividadGenericMethod;
import mx.com.nmp.pagos.mimonte.aspects.ObjectsInSession;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.BonificacionesBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusBonificacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoBonificacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.BonificacionDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper;
import mx.com.nmp.pagos.mimonte.model.EstatusBonificacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoBonificacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubTipoActividadEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoActividadEnum;
import mx.com.nmp.pagos.mimonte.services.conciliacion.BonificacionesService;
import mx.com.nmp.pagos.mimonte.util.ConciliacionDataValidator;

/**
 * @name BonificacionesServiceImpl
 * @description Clase de capa de servicio para las bonificaciones que sirve para
 *              realizar operaciones de logica de negocios
 *
 * @author Jorge Galvez
 * @creationDate 11/11/2020 17:48 hrs.
 * @version 0.1
 */
@Service("bonificacionesService")
public class BonificacionesServiceImpl implements BonificacionesService {
	/**
	 * Log de actividades en el servidor
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BonificacionesServiceImpl.class);


	@Autowired
	private MovimientoBonificacionRepository movimientoBonificacionRepository;

	@Autowired
	private EstatusBonificacionRepository estatusBonificacionRepository;

	@Autowired
	private ConciliacionHelper conciliacionHelper;

	/**
	 * Registro de actividades
	 */
	@Autowired
	private ActividadGenericMethod actividadGenericMethod;

	/**
	 * Validador generico para datos relacionados conconciliacion
	 */
	@Autowired
	private ConciliacionDataValidator conciliacionDataValidator;

	@Inject
	private ObjectsInSession objectsInSession;

	public List<BonificacionDTO> consulta(Long folio) {

		if (folio < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		Conciliacion conciliacion = this.conciliacionHelper.getConciliacionByFolio(folio, null);

		List<MovimientoBonificacion> bonificaciones = movimientoBonificacionRepository
				.findByIdConciliacion(conciliacion.getId());
		/*if (bonificaciones == null || bonificaciones.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0009);*/

		return BonificacionesBuilder.buildListDTO(bonificaciones);
	}


	@Transactional
	public BonificacionDTO save(BonificacionDTO dto, String usuario) {

		// Valida que el folio de conciliacion exista
		conciliacionDataValidator.validateFolioExists(dto.getFolio());

		// Validar campos
		if (dto.getEstatus() == null || dto.getEstatus().getId() == 0) {
			throw new ConciliacionException("Estatus incorrecto",
					CodigoError.NMP_PMIMONTE_0001);
		}

		// Extrae los ids y los pone en una lista
		MovimientoBonificacion bonificacion = BonificacionesBuilder.build(dto);
		try {

			// Se obtiene de la BD
			if (bonificacion.getId() != null && bonificacion.getId() > 0) {
				Optional<MovimientoBonificacion> movBonificacionOpt = movimientoBonificacionRepository.findById(bonificacion.getId());
				if (!movBonificacionOpt.isPresent()) {
					throw new ConciliacionException("Bonificacion con id " + bonificacion.getId() + " no existe",
							CodigoError.NMP_PMIMONTE_0001);
				}
				MovimientoBonificacion bonificacionBD = movBonificacionOpt.get();
				bonificacion.setCreatedBy(bonificacionBD.getCreatedBy());
				bonificacion.setCreatedDate(bonificacionBD.getCreatedDate());
			}
			
			// Get status
			Optional<EstatusBonificacion> estatusBonificacionOpt = estatusBonificacionRepository.findById(dto.getEstatus().getId().intValue());
			if (!estatusBonificacionOpt.isPresent()) {
				throw new ConciliacionException("Estatus no existe",
						CodigoError.NMP_PMIMONTE_0001);
			}
			bonificacion.setEstatus(estatusBonificacionOpt.get());

			if (bonificacion.getId() == null || bonificacion.getId() <= 0) { // New
				bonificacion.setCreatedBy(usuario);
				bonificacion.setCreatedDate(new Date());
			}
			else {
				bonificacion.setLastModifiedBy(usuario);
				bonificacion.setLastModifiedDate(new Date());
			}
			bonificacion = this.movimientoBonificacionRepository.save(bonificacion);
		} catch (ConciliacionException ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		} catch (Exception ex) {
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_0011.getDescripcion(),
					CodigoError.NMP_PMIMONTE_0011);
		}
		return BonificacionesBuilder.buildDTO(bonificacion);
	}


	@Transactional
	public void delete(Long idBonificacion, String deletedBy) throws ConciliacionException {

		Optional<MovimientoBonificacion> bonificacionOpt = this.movimientoBonificacionRepository.findById(idBonificacion);
		if (bonificacionOpt == null || !bonificacionOpt.isPresent()) {
			throw new ConciliacionException("No existe la bonificacion con id " + idBonificacion,
					CodigoError.NMP_PMIMONTE_0008);
		}

		try {
			MovimientoBonificacion bonificacion = bonificacionOpt.get();

			this.movimientoBonificacionRepository.delete(bonificacion);
			
			Long folioConciliacion = this.conciliacionHelper.getFolioConciliacionById(bonificacion.getIdConciliacion());

			// Registro de actividad
			actividadGenericMethod.registroActividadV2(objectsInSession.getFolioByIdConciliacion(bonificacion.getIdConciliacion()), "Se elimino la bonificacion para la conciliacion " + folioConciliacion,
					TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.MOVIMIENTOS);

		} catch (Exception ex) {
			ex.printStackTrace();
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_0011.getDescripcion(),
					CodigoError.NMP_PMIMONTE_0011);
		}
	}

}
