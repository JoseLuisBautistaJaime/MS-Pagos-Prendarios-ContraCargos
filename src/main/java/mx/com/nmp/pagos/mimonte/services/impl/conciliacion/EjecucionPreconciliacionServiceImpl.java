/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import com.ibm.icu.util.Calendar;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.EjecucionPreconciliacionBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EjecucionPreconciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstatusEjecucionPreconciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaEjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaEjecucionPreconciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusEjecucionPreconciliacion;
import mx.com.nmp.pagos.mimonte.services.conciliacion.EjecucionPreconciliacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


/**
 * @name EjecucionPreconciliacionServiceImpl
 * @description Clase de capa de servicio para la ejecución  del proceso de preconciliación que sirve para
 *              realizar operaciones de lógica de negocios
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 05/11/2021 10:48 hrs.
 * @version 0.1
 */
@Service("ejecucionPreconciliacionServiceImpl")
public class EjecucionPreconciliacionServiceImpl implements EjecucionPreconciliacionService {

	/**
	 * Instancia para impresion de LOG's
	 */
	private static final Logger LOG = LoggerFactory.getLogger(EjecucionPreconciliacionServiceImpl.class);
	

	@Autowired
	private EstatusEjecucionPreconciliacionRepository estatusEjecucionPreconciliacionRepository;

	@Autowired
	private EjecucionPreconciliacionRepository ejecucionPreconciliacionRepository;


	// Temporal format para los LOGs de timers
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	/**
	 * Metodo que realiza una busqueda a partir de un objeto de tipo
	 * ConsultaEjecucionPreconciliacionRequestDTO devolviendo como resultado una lista de tipo
	 * ConsultaEjecucionPreconciliacionDTO.
	 */
	@Override
	public List<ConsultaEjecucionPreconciliacionDTO> consulta(ConsultaEjecucionPreconciliacionRequestDTO consultaEjecucionPreconciliacionRequestDTO) {

		// Declaracion de objetos necesarios
		List<ConsultaEjecucionPreconciliacionDTO> result = null;
		Optional<EstatusEjecucionPreconciliacion> estatusEjecucionPreconciliacion = null;

		// Se hace UPPERCASE de nombre corresponsal
		consultaEjecucionPreconciliacionRequestDTO.setCorresponsal(null != consultaEjecucionPreconciliacionRequestDTO.getCorresponsal() ? consultaEjecucionPreconciliacionRequestDTO.getCorresponsal().toUpperCase() : null);

		// Ajuste de fechas para filtros
		if (null ==  consultaEjecucionPreconciliacionRequestDTO.getFechaEjecucionDesde() && null !=  consultaEjecucionPreconciliacionRequestDTO.getFechaEjecucionHasta()) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 1975);
			cal.set(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			 consultaEjecucionPreconciliacionRequestDTO.setFechaEjecucionDesde(cal.getTime());
		}
		if (null !=  consultaEjecucionPreconciliacionRequestDTO.getFechaEjecucionDesde() && null ==  consultaEjecucionPreconciliacionRequestDTO.getFechaEjecucionHasta()) {
			Calendar cal = Calendar.getInstance();
			 consultaEjecucionPreconciliacionRequestDTO.setFechaEjecucionHasta(cal.getTime());
		}
		if (null !=  consultaEjecucionPreconciliacionRequestDTO.getFechaEjecucionDesde()
				&& null !=  consultaEjecucionPreconciliacionRequestDTO.getFechaEjecucionHasta()) {
			Calendar ini = Calendar.getInstance();
			Calendar fin = Calendar.getInstance();
			ini.setTime( consultaEjecucionPreconciliacionRequestDTO.getFechaEjecucionDesde());
			fin.setTime( consultaEjecucionPreconciliacionRequestDTO.getFechaEjecucionHasta());
			ini.set(Calendar.HOUR_OF_DAY, 0);
			ini.set(Calendar.MINUTE, 0);
			ini.set(Calendar.SECOND, 0);
			ini.set(Calendar.MILLISECOND, 0);
			fin.set(Calendar.HOUR_OF_DAY, 23);
			fin.set(Calendar.MINUTE, 59);
			fin.set(Calendar.SECOND, 59);
			fin.set(Calendar.MILLISECOND, 59);
			 consultaEjecucionPreconciliacionRequestDTO.setFechaEjecucionDesde(ini.getTime());
			 consultaEjecucionPreconciliacionRequestDTO.setFechaEjecucionHasta(fin.getTime());
		}

		// Valida que el id del estatus de ejecución del proceso de conciliacion  exista
		if (null !=  consultaEjecucionPreconciliacionRequestDTO && null !=  consultaEjecucionPreconciliacionRequestDTO.getIdEstatus()) {
			estatusEjecucionPreconciliacion = estatusEjecucionPreconciliacionRepository.findById( consultaEjecucionPreconciliacionRequestDTO.getIdEstatus());
			if (!estatusEjecucionPreconciliacion.isPresent())
				throw new ConciliacionException(ConciliacionConstants.ESTATUS_EJECUCION_PRECONCILIACION_DOESNT_EXISTS, CodigoError.NMP_PMIMONTE_BUSINESS_140);
		}

		result = EjecucionPreconciliacionBuilder.buildConsultaEjecucionPreconciliacionDTOListFromEjecucionPreconciliacionList(
				ejecucionPreconciliacionRepository.findByPropiedades(
						consultaEjecucionPreconciliacionRequestDTO.getIdEstatus(),  consultaEjecucionPreconciliacionRequestDTO.getEstatusDescripcion(), consultaEjecucionPreconciliacionRequestDTO.getFechaPeriodoInicio(), consultaEjecucionPreconciliacionRequestDTO.getFechaPeriodoFin(),
						consultaEjecucionPreconciliacionRequestDTO.getFechaEjecucionDesde(), consultaEjecucionPreconciliacionRequestDTO.getFechaEjecucionHasta(),
						null !=  consultaEjecucionPreconciliacionRequestDTO.getCorresponsal() ? CorresponsalEnum.getByNombre( consultaEjecucionPreconciliacionRequestDTO.getCorresponsal()) : null ));


		return result;
	}
}
