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
	 * ConsultaEjecucionConciliacionRequestDTO devolviendo como resultado una lista de tipo
	 * ConsultaEjecucionConciliacionDTO.
	 */
	@Override
	public List<ConsultaEjecucionConciliacionDTO> consultarByPropiedades(ConsultaEjecucionConciliacionRequestDTO consultaEjecucionConciliacionRequestDTO) {

		// Declaracion de objetos necesarios
		List<ConsultaEjecucionConciliacionDTO> result = null;

		// Se hace UPPERCASE de nombre corresponsal
		consultaEjecucionConciliacionRequestDTO.setCorresponsal(null != consultaEjecucionConciliacionRequestDTO.getCorresponsal() ? consultaEjecucionConciliacionRequestDTO.getCorresponsal().toUpperCase() : null);

		// Ajuste de fechas para filtros
		if (null ==  consultaEjecucionConciliacionRequestDTO.getFechaEjecucionDesde() && null !=  consultaEjecucionConciliacionRequestDTO.getFechaEjecucionHasta()) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 1975);
			cal.set(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			 consultaEjecucionConciliacionRequestDTO.setFechaEjecucionDesde(cal.getTime());
		}
		if (null !=  consultaEjecucionConciliacionRequestDTO.getFechaEjecucionDesde() && null ==  consultaEjecucionConciliacionRequestDTO.getFechaEjecucionHasta()) {
			Calendar cal = Calendar.getInstance();
			 consultaEjecucionConciliacionRequestDTO.setFechaEjecucionHasta(cal.getTime());
		}
		if (null !=  consultaEjecucionConciliacionRequestDTO.getFechaEjecucionDesde()
				&& null !=  consultaEjecucionConciliacionRequestDTO.getFechaEjecucionHasta()) {
			Calendar ini = Calendar.getInstance();
			Calendar fin = Calendar.getInstance();
			ini.setTime( consultaEjecucionConciliacionRequestDTO.getFechaEjecucionDesde());
			fin.setTime( consultaEjecucionConciliacionRequestDTO.getFechaEjecucionHasta());
			ini.set(Calendar.HOUR_OF_DAY, 0);
			ini.set(Calendar.MINUTE, 0);
			ini.set(Calendar.SECOND, 0);
			ini.set(Calendar.MILLISECOND, 0);
			fin.set(Calendar.HOUR_OF_DAY, 23);
			fin.set(Calendar.MINUTE, 59);
			fin.set(Calendar.SECOND, 59);
			fin.set(Calendar.MILLISECOND, 59);
			 consultaEjecucionConciliacionRequestDTO.setFechaEjecucionDesde(ini.getTime());
			 consultaEjecucionConciliacionRequestDTO.setFechaEjecucionHasta(fin.getTime());
		}

		result = ejecucionConciliacionRepository.findByPropiedades(
							 consultaEjecucionConciliacionRequestDTO.getIdEstatus(),  consultaEjecucionConciliacionRequestDTO.getIdEntidad(), consultaEjecucionConciliacionRequestDTO.getIdCuenta(), consultaEjecucionConciliacionRequestDTO.getIdConciliacion(),
							 consultaEjecucionConciliacionRequestDTO.getFechaPeriodoInicio(), consultaEjecucionConciliacionRequestDTO.getFechaPeriodoFin(),consultaEjecucionConciliacionRequestDTO.getFechaEjecucionDesde(), consultaEjecucionConciliacionRequestDTO.getFechaEjecucionHasta(),
						      null !=  consultaEjecucionConciliacionRequestDTO.getCorresponsal() ? CorresponsalEnum.getByNombre( consultaEjecucionConciliacionRequestDTO.getCorresponsal()) : null );


		return result;
	}

	/**
	 * Metodo que realiza una busqueda a partir del id de la ejecución del proceso de conciliación
	 * devolviendo como resultado un objeto de tipo ConsultaEjecucionConciliacionDTO con el resultado de la busqueda.
	 */
	@Override
	public ConsultaEjecucionConciliacionDTO consultarByIdEjecucion(Long idEjecucionConciliacion) throws ConciliacionException {

		// Declaracion de objetos necesarios
		ConsultaEjecucionConciliacionDTO result = null;


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
	 * Actualiza el estatus de ejecución del proceso de conciliación
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void actualizaEstatusEjecucionConciliacion(ActualizarEstatusEjecucionRequestDTO actualizarEstatusRequestDTO, String usuario)  {

		long start = 0;
		long finish = 0;
		long globalStart = 0;
		long globalFinish = 0;

		globalStart = System.currentTimeMillis();
		LOG.debug("T>>> INICIA ACTUALIZACION DEl ESTATUS: {}", sdf.format(new Date(globalStart)));

		// Se actualiza el estatus de la ejecución del proceso de conciliación que se recibio como
		// parametro, adicionalmente se actualizan los campos createdBy y createdDate
		try {
			start = System.currentTimeMillis();
			LOG.debug("T>>> INICIA ACTUALIZACION DEl ESTATUS EN BASE DE DATOS: {}", sdf.format(new Date(start)));
			ejecucionConciliacionRepository.actualizaEstatusEjecucionConciliacion(actualizarEstatusRequestDTO.getIdEjecucionConciliacion(), new EstatusEjecucionConciliacion(actualizarEstatusRequestDTO.getIdEstatusEjecucion()), usuario, new Date(),actualizarEstatusRequestDTO.getDescripcionEstatus());
			finish = System.currentTimeMillis();
			LOG.debug("T>>> FINALIZA ACTUALIZACION DEl ESTATUS EN BASE DE DATOS: {}, EN: {}", sdf.format(new Date(finish)), (finish-start) );
		} catch (Exception ex) {
			LOG.error("Error al actualizar el estatus de la ejecución del proceso de conciliación.", ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_144.getDescripcion(),	CodigoError.NMP_PMIMONTE_BUSINESS_144);
		}

		// Registro de trazado de estatus
		try {
			start = System.currentTimeMillis();
			LOG.debug("T>>> INICIA REGISTRO DEl TRAZADO DE ESTATUS: {}", sdf.format(new Date(start)));
			TrazadoEjecucionConciliacion trazado = new TrazadoEjecucionConciliacion();
			trazado.setEjecucionConciliacion(new EjecucionConciliacion(actualizarEstatusRequestDTO.getIdEjecucionConciliacion()));
			trazado.setEstatus(new EstatusEjecucionConciliacion(actualizarEstatusRequestDTO.getIdEstatusEjecucion()));
			trazado.setEstatusDescripcion(actualizarEstatusRequestDTO.getDescripcionEstatus());
			trazado.setFechaInicio(actualizarEstatusRequestDTO.getFechaInicio());
			trazado.setFechaFin(actualizarEstatusRequestDTO.getFechaFin());
			trazadoEjecucionConciliacionRepository.save(trazado);
			finish = System.currentTimeMillis();
			LOG.debug("T>>> FINALIZA REGISTRO DEl TRAZADO DE ESTATUS: {}, EN: {}", sdf.format(new Date(finish)), (finish-start) );
		} catch (Exception ex) {
			LOG.error("Error al registrar el trazado de estatus de la ejecución del proceso de conciliación.", ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_145.getDescripcion(),	CodigoError.NMP_PMIMONTE_BUSINESS_145);
		}

		globalFinish = System.currentTimeMillis();
		LOG.debug("T>>> FINALIZA ACTUALIZACION DEl ESTATUS GENERAL: {}, EN: {}", sdf.format(new Date(globalFinish)), (globalFinish-globalStart) );
	}


}
