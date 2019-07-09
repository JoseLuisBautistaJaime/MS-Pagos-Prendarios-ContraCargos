/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.DevolucionesBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientoDevolucionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosTransitoBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusDevolucionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusTransitoRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoDevolucionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoTransitoRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionesIdsMovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FolioRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LiquidacionMovimientosRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.EstatusDevolucion;
import mx.com.nmp.pagos.mimonte.model.EstatusTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;
import mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.SolicitarDevolucionesService;

/**
 * @name DevolucionesServiceImpl
 * @description Clase de capa de servicio para las devoluciones que sirve para
 *              realizar operaciones de logica de negocios
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/05/2019 17:48 hrs.
 * @version 0.1
 */
@Service("devolucionesServiceImpl")
public class DevolucionesServiceImpl implements DevolucionesService {

	@Autowired
	private MovimientoDevolucionRepository movimientoDevolucionRepository;

	@Autowired
	private ConciliacionRepository conciliacionRepository;

	@Autowired
	private EstatusDevolucionRepository estatusDevolucionRepository;

	@Autowired
	private EstatusTransitoRepository estatusTransitoRepository;

	@Autowired
	private MovimientoTransitoRepository movimientoTransitoRepository;

	@Autowired
	private EntidadRepository entidadRepository;

	@Autowired
	private SolicitarDevolucionesService solicitarDevolucionesService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#
	 * consultaDevolucion(java.lang.Integer)
	 */
	@Override
	public List<DevolucionConDTO> consultaDevolucion(Integer folio) {

		if (folio < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);

		List<MovimientoDevolucion> devoluciones = movimientoDevolucionRepository.findByIdConciliacion(folio);
		if (devoluciones == null || devoluciones.isEmpty())
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND,
					CodigoError.NMP_PMIMONTE_0009);

		return DevolucionesBuilder.buildDevolucionConDTOListFromMovimientoDevolucionList(devoluciones);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#
	 * liquidarDevoluciones(mx.com.nmp.pagos.mimonte.dto.conciliacion.
	 * LiquidacionMovimientosRequestDTO, java.lang.String)
	 */
	@Override
	@Transactional
	public List<DevolucionEntidadDTO> liquidarDevoluciones(LiquidacionMovimientosRequestDTO liquidarDevoluciones,
			String usuario) {

		if (liquidarDevoluciones == null || liquidarDevoluciones.getFolio() == null
				|| liquidarDevoluciones.getMovimientos() == null) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		}

		List<DevolucionEntidadDTO> movimientosLiquidados = new ArrayList<DevolucionEntidadDTO>();

		try {

			// Se obtiene el estatus liquidada
			EstatusDevolucion edLiquidada = estatusDevolucionRepository
					.getOne(ConciliacionConstants.ESTATUS_DEVOLUCION_LIQUIDADA);

			// Por cada movimiento a liquidar:
			for (MovimientosDTO movimientos : liquidarDevoluciones.getMovimientos()) {

				// Se obtiene el movimiento a liquidar
				MovimientoDevolucion md = movimientoDevolucionRepository
						.findByIdConciliacionAndIdMovimiento(liquidarDevoluciones.getFolio(), movimientos.getId());
				if (md == null) {
					throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND,
							CodigoError.NMP_PMIMONTE_0009);
				}

				// Se actualiza el estatus a liquidada unicamente a los movimientos que se
				// encuentran solicitados
				if (md.getEstatus().getId() == ConciliacionConstants.ESTATUS_DEVOLUCION_SOLICITADA) {

					md.setEstatus(edLiquidada);
					md.setLastModifiedDate(new Date());
					md.setLastModifiedBy(usuario);
					movimientoDevolucionRepository.saveAndFlush(md);

					Entidad entidad = entidadRepository.findByConciliacion(md.getIdConciliacion());
					DevolucionEntidadDTO devolucionEntidadDTO = DevolucionesBuilder
							.buildDevolucionEntidadDTOFromMovimientosDevolucion(md, entidad);
					movimientosLiquidados.add(devolucionEntidadDTO);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS,
					CodigoError.NMP_PMIMONTE_BUSINESS_035);
		}
		return movimientosLiquidados;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#
	 * solicitarDevoluciones(mx.com.nmp.pagos.mimonte.dto.conciliacion.
	 * FolioRequestDTO, java.lang.String)
	 */
	@Override
	@Transactional
	public List<DevolucionEntidadDTO> solicitarDevoluciones(FolioRequestDTO folio, String modifiedBy)
			throws ConciliacionException {

		// Se validan parametros
		if (folio == null) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR,
					CodigoError.NMP_PMIMONTE_0008);
		}

		if (folio.getFolio() == null || folio.getFolio() <= 0) {
			throw new ConciliacionException("Folio incorrecto", CodigoError.NMP_PMIMONTE_BUSINESS_036);
		}

		List<DevolucionEntidadDTO> movimientosSolicitados = null;

		try {

			// Se obtienen los movimientos por folio
			List<MovimientoDevolucion> movimientosDevolucion = movimientoDevolucionRepository
					.findByFolio(folio.getFolio());
			movimientosSolicitados = solicitarDevoluciones(movimientosDevolucion, modifiedBy);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS, CodigoError.NMP_PMIMONTE_BUSINESS_035);
		}

		return movimientosSolicitados;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#consulta(
	 * mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionRequestDTO)
	 */
	@Override
	public List<DevolucionEntidadDTO> consulta(DevolucionRequestDTO devoluciones) {

		if (devoluciones.getEstatus() < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR, CodigoError.NMP_PMIMONTE_0008);
		if (devoluciones.getIdEntidad() < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR, CodigoError.NMP_PMIMONTE_0008);

		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		ini.setTime(devoluciones.getFechaDesde());
		fin.setTime(devoluciones.getFechaHasta());
		if (ini.after(fin))
			throw new ConciliacionException(ConciliacionConstants.Validation.INITIAL_DATE_AFTER_FINAL_DATE, CodigoError.NMP_PMIMONTE_BUSINESS_037);

		return DevolucionesBuilder.buildDevolucionEntidadDTOListFromDevolucionEntidadDetalleDTOList(
				conciliacionRepository.findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursal(
						devoluciones.getEstatus(), devoluciones.getIdEntidad(), devoluciones.getIdentificadorCuenta(),
						devoluciones.getSucursal(), devoluciones.getFechaDesde(), devoluciones.getFechaHasta()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#
	 * solicitarDevoluciones(mx.com.nmp.pagos.mimonte.dto.conciliacion.
	 * DevolucionesIdsMovimientosDTO, java.lang.String)
	 */
	@Override
	@Transactional
	public List<DevolucionEntidadDTO> solicitarDevoluciones(DevolucionesIdsMovimientosDTO solicitar,
			String modifiedBy) {

		if (solicitar == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR, CodigoError.NMP_PMIMONTE_0008);

		for (Integer sol : solicitar.getIdsMovimientos()) {
			if (sol == null)
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR, CodigoError.NMP_PMIMONTE_0008);
			if (sol < 1)
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR, CodigoError.NMP_PMIMONTE_0008);
		}

		List<DevolucionEntidadDTO> movimientosSolicitados = null;

		try {

			// Se obtienen los movimientos de devolucion usando los ids recibidos
			List<MovimientoDevolucion> movimientosDevolucion = movimientoDevolucionRepository
					.findAllById(solicitar.getIdsMovimientos());
			movimientosSolicitados = solicitarDevoluciones(movimientosDevolucion, modifiedBy);

		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS, CodigoError.NMP_PMIMONTE_BUSINESS_035);
		}
		return movimientosSolicitados;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService#
	 * marcarDevolucion(mx.com.nmp.pagos.mimonte.dto.conciliacion.
	 * SolicitarPagosRequestDTO, java.lang.String)
	 */
	@Override
	@Transactional
	public List<DevolucionConDTO> marcarDevolucion(SolicitarPagosRequestDTO marcarDevoluciones, String createdBy) {

		// Se validan parametros
		if (marcarDevoluciones == null || marcarDevoluciones.getIdMovimientos() == null) {
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR, CodigoError.NMP_PMIMONTE_0008);
		}

		List<DevolucionConDTO> movimientosMarcados = new ArrayList<DevolucionConDTO>();

		try {

			// Se obtiene el catalogo devolucion pendiente
			EstatusDevolucion edPendiente = estatusDevolucionRepository
					.getOne(ConciliacionConstants.ESTATUS_DEVOLUCION_PENDIENTE);

			// Se obtiene el catalogo de movimiento en transito transferido
			EstatusTransito etMarcadoDev = estatusTransitoRepository
					.getOne(ConciliacionConstants.ESTATUS_TRANSITO_MARCADO_DEVOLUCION);

			// Por cada movimiento en transito:
			for (Integer idMovimientoTransito : marcarDevoluciones.getIdMovimientos()) {

				// Se obtiene el movimiento en transito asignado al folio y al id
				MovimientoTransito movimientoTransito = movimientoTransitoRepository
						.findByIdFolioAndIdMovimiento(marcarDevoluciones.getFolio(), idMovimientoTransito);
				if (movimientoTransito == null) { // TODO: Validar el estatus del movimiento en transito
					throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND, CodigoError.NMP_PMIMONTE_0009);
				}

				// Se crea el movimiento devolucion en base al movimiento en transito
				MovimientoDevolucion movimientoDevolucion = MovimientoDevolucionBuilder
						.buildMovimientoFromMovimientoTransito(movimientoTransito, edPendiente, createdBy);
				movimientoDevolucionRepository.save(movimientoDevolucion);

				// Se actualiza el estatus del movimiento en transito como marcado para
				// devolucion (borrado logico)
				movimientoTransito.setLastModifiedBy(createdBy);
				movimientoTransito.setLastModifiedDate(new Date());
				movimientoTransito.setEstatus(etMarcadoDev);
				movimientoTransitoRepository.save(movimientoTransito);

				DevolucionConDTO devolucionConDTO = MovimientosTransitoBuilder
						.buildDevolucionConDTOFromMovimientoTransito(movimientoTransito, edPendiente);
				movimientosMarcados.add(devolucionConDTO);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS, CodigoError.NMP_PMIMONTE_BUSINESS_035);
		}

		return movimientosMarcados;
	}

	// PRIVATES /////////////////////////////////////////////

	/**
	 * Se encarga de actualizar el estatus de los movimientos de devoluciones a
	 * solicitado y de realizar el envio del email de solicitud
	 * 
	 * @param movimientosDevolucion
	 * @param usuario
	 * @return
	 */
	private List<DevolucionEntidadDTO> solicitarDevoluciones(List<MovimientoDevolucion> movimientosDevolucion,
			String usuario) {

		List<DevolucionEntidadDTO> devolucionesSolicitadas = null;

		// Se procede al envio de las devoluciones
		if (CollectionUtils.isNotEmpty(movimientosDevolucion)) {

			devolucionesSolicitadas = new ArrayList<DevolucionEntidadDTO>();

			try {

				// Se obtiene el catalogo de devolucion solicitada
				EstatusDevolucion edSolicitada = estatusDevolucionRepository
						.getOne(ConciliacionConstants.ESTATUS_DEVOLUCION_SOLICITADA);

				// Se filtran unicamente las devoluciones que tienen estatus pendiente
				// Se actualiza el estatus a solicitada
				for (MovimientoDevolucion md : movimientosDevolucion) {

					if (md.getEstatus().getId() == ConciliacionConstants.ESTATUS_DEVOLUCION_PENDIENTE) {
						md.setEstatus(edSolicitada);
						md.setLastModifiedDate(new Date());
						md.setLastModifiedBy(usuario);
						movimientoDevolucionRepository.saveAndFlush(md);

						// Se agrega el movimiento a la respuesta
						Entidad entidad = entidadRepository.findByConciliacion(md.getIdConciliacion());
						DevolucionEntidadDTO movimientoDTO = DevolucionesBuilder
								.buildDevolucionEntidadDTOFromMovimientosDevolucion(md, entidad);
						devolucionesSolicitadas.add(movimientoDTO);
					}
				}

				// Se envia el email con la solicitud de devoluciones
				if (CollectionUtils.isNotEmpty(devolucionesSolicitadas)) {
					solicitarDevolucionesService.enviarSolicitudDevoluciones(devolucionesSolicitadas);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS, CodigoError.NMP_PMIMONTE_BUSINESS_035);
			}
		}

		return devolucionesSolicitadas;
	}

}
