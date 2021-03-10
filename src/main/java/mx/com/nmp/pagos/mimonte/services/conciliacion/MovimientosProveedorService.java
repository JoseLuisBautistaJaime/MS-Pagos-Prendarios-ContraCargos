/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.aspects.ActividadGenericMethod;
import mx.com.nmp.pagos.mimonte.aspects.ObjectsInSession;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.ReporteBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoProveedorRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ReporteRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.MovimientoJdbcRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CommonConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.MovimientosException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubTipoActividadEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoActividadEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

/**
 * @name MovimientosProveedorService
 * @description Clase service que realiza operaciones con movimientos
 *              relacionados con movimientos de proveedor
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:38 PM
 */
@Service("movimientosProveedorService")
public class MovimientosProveedorService {

	/**
	 * Utilizada para manipular los mensajes informativos y de error.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(MovimientosProveedorService.class);

	/**
	 * repository de movimientos proveedor
	 */
	@Autowired
	@Qualifier("movimientoProveedorRepository")
	private MovimientoProveedorRepository movimientoProveedorRepository;

	/**
	 * Repository de Reporte
	 */
	@Autowired
	@Qualifier("reporteRepository")
	private ReporteRepository reporteRepository;

	/**
	 * Conciliacion
	 */
	@Autowired
	private ConciliacionRepository conciliacionRepository;

	@Autowired
	private MovimientoJdbcRepository movimientoJdbcRepository;


	/**
	 * Registro de actividades
	 */
	@Autowired
	private ActividadGenericMethod actividadGenericMethod;

	@Autowired
	private ObjectsInSession objectsInSession;
	
	// Temporal format para los LOGs de timers
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public MovimientosProveedorService() {
		super();
	}

	/**
	 * Regresa el total de registros encontrados compatibles con el id de
	 * conciliacion especificado como parametro
	 * 
	 * @param idConciliacion
	 * @return
	 */
	public Long countByConciliacionId(final Long idConciliacion) {
		return movimientoProveedorRepository.countByReporteConciliacionId(idConciliacion, TipoReporteEnum.PROVEEDOR);
	}


	/**
	 * Guarda una lista de entidades de tipo MovimientoProveedor
	 * 
	 * @param listRequestDTO
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(MovimientoTransaccionalListRequestDTO listRequestDTO, final String userRequest)
			throws ConciliacionException {
		long start = 0;
		long finish = 0;
		long bigStart = 0;
		long bigFinsh = 0;		

		bigStart = System.currentTimeMillis();
		LOG.debug("T>>> INICIA PERSISTENACIA GENERAL DE MOVIMIENTOS PROVEEDOR: {}", sdf.format(new Date(bigStart)));
		
		LOG.debug("Save {} movimientos proveedor", listRequestDTO.getFolio());
		start = System.currentTimeMillis();
		LOG.debug("T>>> INICIA OBTENCION DE CONBCILIACION POR FOLIO: {}", sdf.format(new Date(start)));
		Conciliacion conciliacion = this.conciliacionRepository.findByFolio(listRequestDTO.getFolio());
		finish = System.currentTimeMillis();
		LOG.debug("T>>> FINALIZA OBTENCION DE CONCILIACION POR FOLIO: {}, EN: {}", sdf.format(new Date(finish)), (finish-start));
		if (conciliacion == null) {
			throw new ConciliacionException("Conciliacion con el folio " + listRequestDTO.getFolio() + " no existe",
					CodigoError.NMP_PMIMONTE_BUSINESS_045);
		}

		if (conciliacion.getSubEstatus() == null || conciliacion.getSubEstatus().getId() == null ||
				!ConciliacionConstants.CON_SUB_ESTATUS_CARGA_MOV_PT.contains(conciliacion.getSubEstatus().getId())) {
			LOG.error("La conciliacion no tiene un sub-estatus valido. Sub-estatus: [" + conciliacion.getSubEstatus() + "]");
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_030.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_030);
		}

		// Obtiene el reporte del proveedor transaccional
		Reporte reporte = null;
		/*
		 * try { reporte =
		 * reporteRepository.findLastByIdConciliacionAndTipo(listRequestDTO.getFolio(),
		 * TipoReporteEnum.PROVEEDOR); } catch (Exception ex) { ex.printStackTrace();
		 * throw new ConciliacionException(ex.getMessage()); }
		 */

		// Si no existe el reporte se crea uno nuevo
		if (reporte == null) {
			start = System.currentTimeMillis();
			LOG.debug("T>>> INICIA CONSTRUCCION DE ENTIDAD REPORTE: {}", sdf.format(new Date(start)));
			reporte = ReporteBuilder.buildReporte(conciliacion, listRequestDTO.getFechaDesde(),
					listRequestDTO.getFechaHasta(), userRequest);
			finish = System.currentTimeMillis();
			LOG.debug("T>>> FINALIZA CONSTRUCCION DE ENTIDAD REPORTE: {}, EN: {}", sdf.format(new Date(finish)), (finish-start) );
		}

		// Se guarda el reporte y los movimientos
		try {

			start = System.currentTimeMillis();
			LOG.debug("T>>> INICIA PERSISTENCIA DE ENTIDAD REPORTE: {}", sdf.format(new Date(start)));
			reporte = reporteRepository.save(reporte);
			finish = System.currentTimeMillis();
			LOG.debug("T>>> FINALIZA PERSISTENCIA DE ENTIDAD REPORTE: {}, EN: {}",sdf.format(new Date(finish)), (finish-start) );

			start = System.currentTimeMillis();
			LOG.debug("T>>> INICIA CONSTRUCCION DE MOVIMIENTOS PROVEEDOR: {}", sdf.format(new Date(start)));
			List<MovimientoProveedor> movimientoProveedorList = MovimientosBuilder
					.buildMovimientoProveedorListFromMovimientoTransaccionalListRequestDTO(listRequestDTO,
							reporte.getId());
			finish = System.currentTimeMillis();
			LOG.debug("T>>> FINALIZA CONSTRUCCION DE MOVIMIENTOS PROVEEDOR: {}, EN: {}", sdf.format(new Date(finish)), (finish-start) );

			// Verificar si se guarda en batch
			if (!CollectionUtils.isEmpty(movimientoProveedorList)) {
				start = System.currentTimeMillis();
				LOG.debug("T>>> INICIA PERSISTENCIA DE MOVIMIENTOS PROVEEDOR: {}", sdf.format(new Date(start)));
				movimientoJdbcRepository.insertarLista(movimientoProveedorList);
				finish = System.currentTimeMillis();
				LOG.debug("T>>> FINALIZA PERSISTENCIA DE MOVIMEINTOS PROVEEDOR: {}, EN: {}", sdf.format(new Date(finish)), (finish-start) );
			}

			// Registro de actividad
			start = System.currentTimeMillis();
			LOG.debug("T>>> INICIA REGISTRO DE ACTIVIDADES: {}", sdf.format(new Date(start)));
			actividadGenericMethod.registroActividadV2(objectsInSession.getFolioByIdConciliacion(listRequestDTO.getFolio()),
					"Se registraron " + listRequestDTO.getMovimientos().size()
							+ " movimientos provenientes del proveedor transaccional,"
							+ " para la conciliacion con el folio: " + listRequestDTO.getFolio(),
					TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.MOVIMIENTOS);
			finish = System.currentTimeMillis();
			LOG.debug("T>>> FINALIZA REGISTRO DE ACTIVIDADES: {}, EN: {}", sdf.format(new Date(finish)), (finish-start) );

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MovimientosException(ConciliacionConstants.REPORT_GENERATION_ERROR_MESSAGE,
					CodigoError.NMP_PMIMONTE_BUSINESS_044);
		}
		
		bigFinsh = System.currentTimeMillis();
		LOG.debug("T>>> FINALIZA PERSISTENCIA GENERAL D MOVIMIENTOS PROVEEDOR: {}, EN: {}", sdf.format(new Date(bigFinsh)), (bigFinsh- bigStart) );
	}

	/**
	 * Regresa una lista de los movimientos de provedor paginados y por un folio de
	 * conciliacion especifico
	 * 
	 * @param commonConciliacionRequestDTO
	 * @return
	 */
	public List<MovimientoProveedorDTO> findByFolio(final CommonConciliacionRequestDTO commonConciliacionRequestDTO) {
		return MovimientosBuilder.buildMovimientoProveedorDTOListFromMovimientoProveedorList(
				movimientoProveedorRepository.findByReporteConciliacionId(commonConciliacionRequestDTO.getFolio(), TipoReporteEnum.PROVEEDOR));
	}

}