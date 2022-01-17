/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import com.ibm.icu.util.Calendar;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.*;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.*;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @name EjecucionConciliacionServiceImpl
 * @description Clase de capa de servicio para la ejecución  del proceso de conciliación que sirve para
 *              realizar operaciones de logica de negocios
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 04/11/2021 11:48 hrs.
 * @version 0.1
 */
@Service("ejecucionConciliacionServiceImpl")
public class EjecucionConciliacionServiceImpl implements EjecucionConciliacionService {

	/**
	 * Instancia para impresion de LOG's
	 */
	private static final Logger LOG = LoggerFactory.getLogger(EjecucionConciliacionServiceImpl.class);


	@Autowired
	private EjecucionConciliacionRepository ejecucionConciliacionRepository;

	@Autowired
	private TrazadoEjecucionConciliacionRepository trazadoEjecucionConciliacionRepository;


	// Temporal format para los LOGs de timers
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	/**
	 * Metodo que realiza una busqueda a partir de un objeto de tipo
	 * FiltroEjecucionConciliacionDTO devolviendo como resultado una lista de tipo
	 * EjecucionConciliacionDTO.
	 */
	@Override
	public List<EjecucionConciliacionDTO> consultarByPropiedades(FiltroEjecucionConciliacionDTO filtroEjecucionConciliacionDTO) {

		// Declaracion de objetos necesarios
		List<EjecucionConciliacionDTO> result = null;

		// Se hace UPPERCASE de nombre corresponsal
		filtroEjecucionConciliacionDTO.setCorresponsal(null != filtroEjecucionConciliacionDTO.getCorresponsal() ? filtroEjecucionConciliacionDTO.getCorresponsal().toUpperCase() : null);

		// Ajuste de fechas para filtros
		if (null ==  filtroEjecucionConciliacionDTO.getFechaEjecucionDesde() && null !=  filtroEjecucionConciliacionDTO.getFechaEjecucionHasta()) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 1975);
			cal.set(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			filtroEjecucionConciliacionDTO.setFechaEjecucionDesde(cal.getTime());
		}
		if (null !=  filtroEjecucionConciliacionDTO.getFechaEjecucionDesde() && null ==  filtroEjecucionConciliacionDTO.getFechaEjecucionHasta()) {
			Calendar cal = Calendar.getInstance();
			filtroEjecucionConciliacionDTO.setFechaEjecucionHasta(cal.getTime());
		}
		if (null !=  filtroEjecucionConciliacionDTO.getFechaEjecucionDesde()
				&& null !=  filtroEjecucionConciliacionDTO.getFechaEjecucionHasta()) {
			Calendar ini = Calendar.getInstance();
			Calendar fin = Calendar.getInstance();
			ini.setTime( filtroEjecucionConciliacionDTO.getFechaEjecucionDesde());
			fin.setTime( filtroEjecucionConciliacionDTO.getFechaEjecucionHasta());
			ini.set(Calendar.HOUR_OF_DAY, 0);
			ini.set(Calendar.MINUTE, 0);
			ini.set(Calendar.SECOND, 0);
			ini.set(Calendar.MILLISECOND, 0);
			fin.set(Calendar.HOUR_OF_DAY, 23);
			fin.set(Calendar.MINUTE, 59);
			fin.set(Calendar.SECOND, 59);
			fin.set(Calendar.MILLISECOND, 59);
			filtroEjecucionConciliacionDTO.setFechaEjecucionDesde(ini.getTime());
			filtroEjecucionConciliacionDTO.setFechaEjecucionHasta(fin.getTime());
		}

		result = ejecucionConciliacionRepository.findByPropiedades(
				filtroEjecucionConciliacionDTO.getIdEstatus(),  filtroEjecucionConciliacionDTO.getIdEntidad(), filtroEjecucionConciliacionDTO.getIdCuenta(), filtroEjecucionConciliacionDTO.getIdConciliacion(),
				filtroEjecucionConciliacionDTO.getFechaPeriodoInicio(), filtroEjecucionConciliacionDTO.getFechaPeriodoFin(),filtroEjecucionConciliacionDTO.getFechaEjecucionDesde(), filtroEjecucionConciliacionDTO.getFechaEjecucionHasta(),
						      null !=  filtroEjecucionConciliacionDTO.getCorresponsal() ? CorresponsalEnum.getByNombre( filtroEjecucionConciliacionDTO.getCorresponsal()) : null );


		return result;
	}

	/**
	 * Metodo que realiza una busqueda a partir del id de la ejecución del proceso de conciliación
	 * devolviendo como resultado un objeto de tipo EjecucionConciliacionDTO con el resultado de la busqueda.
	 */
	@Override
	public EjecucionConciliacionDTO consultarByIdEjecucion(Long idEjecucionConciliacion) throws ConciliacionException {

		// Declaracion de objetos necesarios
		EjecucionConciliacionDTO result = null;


		LOG.info(">> consultarById");

		if (idEjecucionConciliacion == null) {
			LOG.debug("El id recibido es nulo");
			throw new ConciliacionException(ConciliacionConstants.ERROR_WHILE_ID_EJECUCION_CONCILIACION_VALIDATION, CodigoError.NMP_PMIMONTE_BUSINESS_141);
		}

		try {
			result = ejecucionConciliacionRepository.findByIdEjecucion(idEjecucionConciliacion);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_GET_EJECUCION_CONCILIACION, CodigoError.NMP_PMIMONTE_BUSINESS_143);
		}

		if (result == null) {
			throw new ConciliacionException(ConciliacionConstants.EJECUCION_CONCILIACION_DOESNT_EXISTS, CodigoError.NMP_PMIMONTE_BUSINESS_142);
		} else {
			result.setListaTrazado(trazadoEjecucionConciliacionRepository.findTrazadoEstatusEjecucionConciliacion(idEjecucionConciliacion));
		}

		return result;

	}

	/**
	 * Metodo que guarda los datos de una ejecución del proceso de conciliación
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void guardarEjecucionConciliacion(TrazadoEjecucionConciliacion trazadoEjecucionConciliacion, String usuario){

		long start = 0;
		long finish = 0;
		long globalStart = 0;
		long globalFinish = 0;
		
		globalStart = System.currentTimeMillis();
		LOG.debug("T>>> INICIA ACTUALIZACION DEl ESTATUS: {}", sdf.format(new Date(globalStart)));

		// Se actualiza el estatus de la ejecución del proceso de conciliación que se recibio como
		// parametro, adicionalmente se actualizan los campos createdBy y createdDate
		try {
			if( trazadoEjecucionConciliacion.getEjecucionConciliacion().getId() != null && trazadoEjecucionConciliacion.getEjecucionConciliacion().getId() > 0L  ){
				start = System.currentTimeMillis();
				LOG.debug("T>>> INICIA ACTUALIZACION DEl ESTATUS EN BASE DE DATOS: {}", sdf.format(new Date(start)));
				ejecucionConciliacionRepository.actualizaEstatusEjecucionConciliacion(trazadoEjecucionConciliacion.getEjecucionConciliacion().getId(), trazadoEjecucionConciliacion.getEjecucionConciliacion().getEstatus(), usuario, new Date());
				finish = System.currentTimeMillis();
				LOG.debug("T>>> FINALIZA ACTUALIZACION DEl ESTATUS EN BASE DE DATOS: {}, EN: {}", sdf.format(new Date(finish)), (finish - start));
			} else {
				start = System.currentTimeMillis();
				LOG.debug("T>>> INICIA CREACION DE LA EJECUCION: {}", sdf.format(new Date(start)));
				EjecucionConciliacion ejecucionCreada = ejecucionConciliacionRepository.save(trazadoEjecucionConciliacion.getEjecucionConciliacion());
				trazadoEjecucionConciliacion.getEjecucionConciliacion().setId(ejecucionCreada.getId());
				finish = System.currentTimeMillis();
				LOG.debug("T>>> FINALIZA CREACION DE LA EJECUCION: {}, EN: {}", sdf.format(new Date(finish)), (finish - start));

			}
		} catch (Exception ex) {
			LOG.error("Error al actualizar el estatus de la ejecución del proceso de conciliación.", ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_144.getDescripcion(),	CodigoError.NMP_PMIMONTE_BUSINESS_144);
		}

		// Registro de trazado de estatus
		try {
			start = System.currentTimeMillis();
			LOG.debug("T>>> INICIA REGISTRO DEl TRAZADO DE ESTATUS: {}", sdf.format(new Date(start)));
			trazadoEjecucionConciliacionRepository.save(trazadoEjecucionConciliacion);
			finish = System.currentTimeMillis();
			LOG.debug("T>>> FINALIZA REGISTRO DEl TRAZADO DE ESTATUS: {}, EN: {}", sdf.format(new Date(finish)), (finish-start) );
		} catch (Exception ex) {
			LOG.error("Error al registrar el trazado de estatus de la ejecución del proceso de conciliación.", ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_145.getDescripcion(),	CodigoError.NMP_PMIMONTE_BUSINESS_145);
		}

		globalFinish = System.currentTimeMillis();
		LOG.debug("T>>> FINALIZA ACTUALIZACION DEl ESTATUS : {}, EN: {}", sdf.format(new Date(globalFinish)), (globalFinish-globalStart) );
	}


	/**
	 * Se encarga de guardar/actualizar una nueva EjecucionConciliacion
	 * @param ejecucionConciliacion
	 * @param registerBy
	 * @return
	 * @throws ConciliacionException
	 */
	@Override
	@Transactional
	public EjecucionConciliacion save(EjecucionConciliacion ejecucionConciliacion, String registerBy) throws ConciliacionException {
		EjecucionConciliacion resultado = null;
		try {
			ejecucionConciliacion.setCreatedDate(new Date());
			ejecucionConciliacion.setCreatedBy(registerBy);
			resultado = this.ejecucionConciliacionRepository.save(ejecucionConciliacion);
		} catch (Exception ex) {
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_0011.getDescripcion(), CodigoError.NMP_PMIMONTE_0011);
		}
		return resultado;
	}

}
