/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import com.ibm.icu.util.Calendar;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.*;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.CuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.*;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
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
	private CuentaRepository cuentaRepository;

	@Autowired
	private EntidadRepository entidadRepository;

	@Autowired
	private EstatusEjecucionConciliacionRepository estatusEjecucionConciliacionRepository;

	@Autowired
	private ConciliacionRepository conciliacionRepository;

	@Autowired
	private EjecucionConciliacionRepository ejecucionConciliacionRepository;


	// Temporal format para los LOGs de timers
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	/**
	 * Metodo que realiza una busqueda a partir de un objeto de tipo
	 * ConsultaEjecucionConciliacionRequestDTO devolviendo como resultado una lista de tipo
	 * ConsultaEjecucionConciliacionDTO.
	 */
	@Override
	public List<ConsultaEjecucionConciliacionDTO> consulta(ConsultaEjecucionConciliacionRequestDTO consultaEjecucionConciliacionRequestDTO) {

		// Declaracion de objetos necesarios
		List<ConsultaEjecucionConciliacionDTO> result = null;
		List<Long> conciliacionIdList;
		List<Object[]> objectResult = null;
		Optional<Entidad> entidad = null;
		Optional<Conciliacion> conciliacion = null;
		Optional<EstatusEjecucionConciliacion> estatusEjecucionConciliacion = null;

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

		// Valida que la entidad especificada exista
		if (null !=  consultaEjecucionConciliacionRequestDTO && null !=  consultaEjecucionConciliacionRequestDTO.getIdEntidad()) {
			entidad = entidadRepository.findById( consultaEjecucionConciliacionRequestDTO.getIdEntidad());
			if (!entidad.isPresent())
				throw new ConciliacionException(CatalogConstants.NO_ENTIDAD_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_022);
		}

		// Valida que la cuenta especificada exista
		if (null !=  consultaEjecucionConciliacionRequestDTO && null !=  consultaEjecucionConciliacionRequestDTO.getIdCuenta()) {
			// Búsqueda y validacion del idCuenta.
			Optional<Cuenta> cuenta = cuentaRepository.findById(consultaEjecucionConciliacionRequestDTO.getIdCuenta());
			if (!cuenta.isPresent()) {
				throw new ConciliacionException(CatalogConstants.NO_CUENTA_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_021);
			}
		}

		// Valida que la conciliación especificada exista
		if (null !=  consultaEjecucionConciliacionRequestDTO && null !=  consultaEjecucionConciliacionRequestDTO.getIdConciliacion()) {
			conciliacion = conciliacionRepository.findById(consultaEjecucionConciliacionRequestDTO.getIdConciliacion());
			if (!conciliacion.isPresent())
				throw new ConciliacionException(ConciliacionConstants.CONCILIACION_ID_NOT_FOUND, CodigoError.NMP_PMIMONTE_BUSINESS_018);
		}

		// Valida que el id del estatus de ejecución del proceso de conciliacion  exista
		if (null !=  consultaEjecucionConciliacionRequestDTO && null !=  consultaEjecucionConciliacionRequestDTO.getIdEstatus()) {
			estatusEjecucionConciliacion = estatusEjecucionConciliacionRepository.findById( consultaEjecucionConciliacionRequestDTO.getIdEstatus());
			if (!estatusEjecucionConciliacion.isPresent())
				throw new ConciliacionException(ConciliacionConstants.ESTATUS_EJECUCION_CONCILIACION_DOESNT_EXISTS, CodigoError.NMP_PMIMONTE_BUSINESS_139);
		}

		result = EjecucionConciliacionBuilder.buildConsultaEjecucionConciliacionDTOListFromEjecucionConciliacionList(
				ejecucionConciliacionRepository.findByPropiedades(
							 consultaEjecucionConciliacionRequestDTO.getIdEstatus(),  consultaEjecucionConciliacionRequestDTO.getIdEntidad(), consultaEjecucionConciliacionRequestDTO.getIdCuenta(), consultaEjecucionConciliacionRequestDTO.getIdConciliacion(),
							 consultaEjecucionConciliacionRequestDTO.getFechaPeriodoInicio(), consultaEjecucionConciliacionRequestDTO.getFechaPeriodoFin(),consultaEjecucionConciliacionRequestDTO.getFechaEjecucionDesde(), consultaEjecucionConciliacionRequestDTO.getFechaEjecucionHasta(),
						      null !=  consultaEjecucionConciliacionRequestDTO.getCorresponsal() ? CorresponsalEnum.getByNombre( consultaEjecucionConciliacionRequestDTO.getCorresponsal()) : null ));


		return result;
	}
}
