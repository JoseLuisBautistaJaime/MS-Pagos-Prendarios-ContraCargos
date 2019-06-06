/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.DevolucionesBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosTransitoBuilder;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusDevolucionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoDevolucionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoTransitoRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionConDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionUpdtDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionesIdsMovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FolioRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LiquidacionMovimientosRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.EstatusDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;
import mx.com.nmp.pagos.mimonte.services.conciliacion.DevolucionesService;

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
public class DevolucionesServiceImpl implements DevolucionesService{

	@Autowired
	private MovimientoDevolucionRepository movimientoDevolucionRepository; 
	
	@Autowired
	private ConciliacionRepository conciliacionRepository;
	
	@Autowired
	private EstatusDevolucionRepository estatusDevolucionRepository;
	
	@Autowired
	private MovimientoTransitoRepository movimientoTransitoRepository;
	
	@Override
	public List<DevolucionConDTO> consultaDevolucion(Integer folio) {

		if (folio < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);

		List<MovimientoDevolucion> devoluciones = movimientoDevolucionRepository.findByIdConciliacion(folio);
		if (devoluciones == null || devoluciones.isEmpty())
			throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

		return DevolucionesBuilder.buildDevolucionConDTOListFromMovimientoDevolucionList(devoluciones);
	}

	@Override
	@Transactional
	public List<DevolucionEntidadDTO> liquidarDevoluciones(LiquidacionMovimientosRequestDTO liquidarDevoluciones,
			String usuario) {

		List<DevolucionEntidadDTO> response = null;

		try {

			for (MovimientosDTO movimientos : liquidarDevoluciones.getMovimientos()) {

				if (movimientos != null) {

					List<MovimientoDevolucion> movimientosDevolucion = movimientoDevolucionRepository.findByIdConciliacionAndIdMovimientoAndFecha(liquidarDevoluciones.getFolio(), movimientos.getId(), movimientos.getFecha());

					for (MovimientoDevolucion md : movimientosDevolucion) {

						if (movimientosDevolucion != null) {

							if (md.getEstatus().getNombre().equals(ConciliacionConstants.REQUEST)) {

								EstatusDevolucion ed = estatusDevolucionRepository.findByNombre(ConciliacionConstants.LIQUIDATE);

								md.setEstatus(ed);
								md.setLastModifiedDate(new Date());
								movimientoDevolucionRepository.saveAndFlush(md);

								Conciliacion conciliacion = conciliacionRepository.findById(liquidarDevoluciones.getFolio()).isPresent() ? conciliacionRepository.findById(liquidarDevoluciones.getFolio()).get() : null;

								response = DevolucionesBuilder.buildDevolucionEntidadDTOListFromMovimientoDevolucionLista(movimientosDevolucion, conciliacion);

							}

							throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND_FOR_IDS);

						}

						throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);
					}

				}
			}

		} catch (Exception e) {
			throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS);
		}
		return response;
	}

	@Override
	@Transactional
	public List<DevolucionEntidadDTO> solicitarDevoluciones(FolioRequestDTO folio, String usuario) {
		
		List<DevolucionEntidadDTO> response = null;
		
		try {
			
			if(folio.getFolio() != null) {
				
				List<MovimientoDevolucion> movimientosDevolucion = movimientoDevolucionRepository.findByFolio(folio.getFolio());
				
				if(movimientosDevolucion != null) {
					
					for(MovimientoDevolucion md : movimientosDevolucion) {
						
						if (md.getEstatus().getNombre().equals(ConciliacionConstants.PENDING)) {
							
							EstatusDevolucion ed = estatusDevolucionRepository.findByNombre(ConciliacionConstants.REQUEST);
								
								md.setEstatus(ed);
								md.setLastModifiedDate(new Date());
								movimientoDevolucionRepository.saveAndFlush(md);
							}
							
							Conciliacion conciliacion = conciliacionRepository.findById(md.getIdConciliacion()).isPresent()	? conciliacionRepository.findById(md.getIdConciliacion()).get()	: null;
							
							response = DevolucionesBuilder.buildDevolucionEntidadDTOListFromMovimientoDevolucionLista(movimientosDevolucion, conciliacion);
						}
					}
				
				throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);
				
				}
			}catch (Exception ex) {
				
				throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS);
				
				}
		return response;
		
		}

	@Override
	@Transactional
	public List<DevolucionEntidadDTO> actualizacionDevoluciones(List<DevolucionUpdtDTO> devoluciones) {

		List<DevolucionEntidadDTO> response = null;

		try {

			for (DevolucionUpdtDTO d : devoluciones) {

				if (d != null) {

					List<MovimientoDevolucion> movimientosDevolucion = movimientoDevolucionRepository.findByIdMovimientoAndFecha(d.getIdMovimiento(), d.getFecha());

					if (movimientosDevolucion != null) {

						if (d.getLiquidar() == true) {

							for (MovimientoDevolucion md : movimientosDevolucion) {

								EstatusDevolucion ed = estatusDevolucionRepository.findByNombre(ConciliacionConstants.LIQUIDATE);

								md.setFecha(d.getFecha());
								md.setFechaLiquidacion(d.getFecha());
								md.setEstatus(ed);
								movimientoDevolucionRepository.saveAndFlush(md);

								Conciliacion conciliacion = conciliacionRepository.findById(md.getIdConciliacion()).isPresent() ? conciliacionRepository.findById(md.getIdConciliacion()).get()	: null;

								response = DevolucionesBuilder.buildDevolucionEntidadDTOListFromMovimientoDevolucionLista(movimientosDevolucion, conciliacion);

							}

						}
					}

					throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);

				}
			}

		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS);
		}

		return response;
	}

	@Override
	public List<DevolucionEntidadDTO> consulta(DevolucionRequestDTO devoluciones) {

		if (devoluciones.getEstatus() < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		if (devoluciones.getIdEntidad() < 1)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		ini.setTime(devoluciones.getFechaDesde());
		fin.setTime(devoluciones.getFechaHasta());
		if (ini.after(fin))
			throw new ConciliacionException(ConciliacionConstants.Validation.INITIAL_DATE_AFTER_FINAL_DATE);

		return DevolucionesBuilder.buildDevolucionEntidadDTOListFromDevolucionEntidadDetalleDTOList(
				conciliacionRepository.findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursal(
						devoluciones.getEstatus(), devoluciones.getIdEntidad(), devoluciones.getIdentificadorCuenta(),
						devoluciones.getSucursal(), devoluciones.getFechaDesde(), devoluciones.getFechaHasta()));

	}

	@Override
	@Transactional
	public List<DevolucionEntidadDTO> solicitar(DevolucionesIdsMovimientosDTO solicitar) {
		
		if (solicitar == null)
			throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		
		for (Integer sol : solicitar.getIdsMovimientos()) {
			if (sol == null)
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
			if (sol < 1)
				throw new ConciliacionException(ConciliacionConstants.Validation.VALIDATION_PARAM_ERROR);
		}
		
		List<DevolucionEntidadDTO> response = null;
		
		try {
			for (Integer s : solicitar.getIdsMovimientos()) {
				if (s != null) {
					List<MovimientoDevolucion> movimientosDevolucion = movimientoDevolucionRepository.findByIdMovimientos(s);
					
					if (movimientosDevolucion != null) {
						for (MovimientoDevolucion md : movimientosDevolucion) {
							if (md.getEstatus().getNombre().equals(ConciliacionConstants.PENDING)) {
								EstatusDevolucion ed = estatusDevolucionRepository.findByNombre(ConciliacionConstants.REQUEST);
								
								md.setEstatus(ed);
								movimientoDevolucionRepository.saveAndFlush(md);
							}
							
							Conciliacion conciliacion = conciliacionRepository.findById(md.getIdConciliacion()).isPresent() ? conciliacionRepository.findById(md.getIdConciliacion()).get() : null;

							response = DevolucionesBuilder.buildDevolucionEntidadDTOListFromMovimientoDevolucionLista(movimientosDevolucion, conciliacion);
						}
					}
					throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);
				}
			}
		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS);
		}
		return response;
	}

	@Override
	@Transactional
	public List<DevolucionConDTO> marcarDevolucion(SolicitarPagosRequestDTO marcarDevoluciones, String createdBy) {

		List<DevolucionConDTO> response = null;
		MovimientoDevolucion movimientoDevolucion = null;

		try {

			for (Integer marcarDev : marcarDevoluciones.getIdMovimientos()) {
				if (marcarDev != null) {

					List<MovimientoTransito> movimientoTransito = movimientoTransitoRepository.findByIdFolioAndIdMovimiento(marcarDevoluciones.getFolio(), marcarDev);

					if (movimientoTransito != null) {

						EstatusDevolucion ed = estatusDevolucionRepository.findByNombre(ConciliacionConstants.PENDING);

						if (ed != null) {

							for (MovimientoTransito mt : movimientoTransito) {

								movimientoDevolucion = new MovimientoDevolucion();
								movimientoDevolucion.setIdConciliacion(mt.getIdConciliacion());
								movimientoDevolucion.setCreatedBy(createdBy);
								movimientoDevolucion.setCreatedDate(new Date());
								movimientoDevolucion.setLastModifiedBy(null);
								movimientoDevolucion.setLastModifiedDate(null);
								movimientoDevolucion.setNuevo(true);
								movimientoDevolucion.setEstatus(ed);
								movimientoDevolucion.setFecha(mt.getFecha());
								movimientoDevolucion.setMonto(mt.getMonto());
								movimientoDevolucion.setEsquemaTarjeta(mt.getEsquemaTarjeta());
								movimientoDevolucion.setIdentificadorCuenta(null);
								movimientoDevolucion.setTitular(mt.getTitular());
								movimientoDevolucion.setCodigoAutorizacion(null);
								movimientoDevolucion.setSucursal(mt.getSucursal());
								movimientoDevolucion.setFechaLiquidacion(null);

								movimientoDevolucionRepository.save(movimientoDevolucion);
								movimientoTransitoRepository.deleteById(marcarDev);

								response = MovimientosTransitoBuilder.buildDevolucionConDTOListFromMovimientoTransitoList(movimientoTransito, ed);

							}

						}
					}
					throw new ConciliacionException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND);
				}
			}
		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.AN_ERROR_OCCURS_IN_CHANGE_OF_STATUS);
		}
		return response;
	}
}
