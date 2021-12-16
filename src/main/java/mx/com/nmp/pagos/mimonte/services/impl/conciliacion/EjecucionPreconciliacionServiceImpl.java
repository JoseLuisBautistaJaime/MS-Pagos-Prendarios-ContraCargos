/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import com.ibm.icu.util.Calendar;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EjecucionPreconciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroEjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.EjecucionPreconciliacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @name EjecucionPreconciliacionServiceImpl
 * @description Clase de capa de servicio para la ejecución  del proceso de pre-conciliación que sirve para
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
	private EjecucionPreconciliacionRepository ejecucionPreconciliacionRepository;


	// Temporal format para los LOGs de timers
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	/**
	 * Metodo que realiza una busqueda a partir de un objeto de tipo
	 * FiltroEjecucionPreconciliacionDTO devolviendo como resultado una lista de tipo
	 * EjecucionPreconciliacionDTO.
	 */
	@Override
	public List<EjecucionPreconciliacionDTO> consultarByPropiedades(FiltroEjecucionPreconciliacionDTO filtroEjecucionPreconciliacionDTO) {

		// Declaracion de objetos necesarios
		List<EjecucionPreconciliacionDTO> result = null;

		// Se hace UPPERCASE de nombre corresponsal
		filtroEjecucionPreconciliacionDTO.setCorresponsal(null != filtroEjecucionPreconciliacionDTO.getCorresponsal() ? filtroEjecucionPreconciliacionDTO.getCorresponsal().toUpperCase() : null);

		// Ajuste de fechas para filtros
		if (null ==  filtroEjecucionPreconciliacionDTO.getFechaEjecucionDesde() && null !=  filtroEjecucionPreconciliacionDTO.getFechaEjecucionHasta()) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 1975);
			cal.set(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			 filtroEjecucionPreconciliacionDTO.setFechaEjecucionDesde(cal.getTime());
		}
		if (null !=  filtroEjecucionPreconciliacionDTO.getFechaEjecucionDesde() && null ==  filtroEjecucionPreconciliacionDTO.getFechaEjecucionHasta()) {
			Calendar cal = Calendar.getInstance();
			 filtroEjecucionPreconciliacionDTO.setFechaEjecucionHasta(cal.getTime());
		}
		if (null !=  filtroEjecucionPreconciliacionDTO.getFechaEjecucionDesde()
				&& null !=  filtroEjecucionPreconciliacionDTO.getFechaEjecucionHasta()) {
			Calendar ini = Calendar.getInstance();
			Calendar fin = Calendar.getInstance();
			ini.setTime( filtroEjecucionPreconciliacionDTO.getFechaEjecucionDesde());
			fin.setTime( filtroEjecucionPreconciliacionDTO.getFechaEjecucionHasta());
			ini.set(Calendar.HOUR_OF_DAY, 0);
			ini.set(Calendar.MINUTE, 0);
			ini.set(Calendar.SECOND, 0);
			ini.set(Calendar.MILLISECOND, 0);
			fin.set(Calendar.HOUR_OF_DAY, 23);
			fin.set(Calendar.MINUTE, 59);
			fin.set(Calendar.SECOND, 59);
			fin.set(Calendar.MILLISECOND, 59);
			 filtroEjecucionPreconciliacionDTO.setFechaEjecucionDesde(ini.getTime());
			 filtroEjecucionPreconciliacionDTO.setFechaEjecucionHasta(fin.getTime());
		}


		result =
				ejecucionPreconciliacionRepository.findByPropiedades(
						filtroEjecucionPreconciliacionDTO.getIdEstatus(),  filtroEjecucionPreconciliacionDTO.getEstatusDescripcion(), filtroEjecucionPreconciliacionDTO.getFechaPeriodoInicio(), filtroEjecucionPreconciliacionDTO.getFechaPeriodoFin(),
						filtroEjecucionPreconciliacionDTO.getFechaEjecucionDesde(), filtroEjecucionPreconciliacionDTO.getFechaEjecucionHasta(),
						null !=  filtroEjecucionPreconciliacionDTO.getCorresponsal() ? CorresponsalEnum.getByNombre( filtroEjecucionPreconciliacionDTO.getCorresponsal()) : null );


		return result;
	}


	/**
	 * Se encarga de guardar/actualizar una nueva EjecucionPreconciliacion
	 * @param ejecucionPreconciliacion
	 * @param registerBy
	 * @return
	 * @throws ConciliacionException
	 */
	@Override
	@Transactional
	public EjecucionPreconciliacion save(EjecucionPreconciliacion ejecucionPreconciliacion, String registerBy) throws ConciliacionException {
		EjecucionPreconciliacion resultado = null;
		try {
			ejecucionPreconciliacion.setCreatedDate(new Date());
			ejecucionPreconciliacion.setCreatedBy(registerBy);
			resultado = this.ejecucionPreconciliacionRepository.save(ejecucionPreconciliacion);
		} catch (Exception ex) {
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_0011.getDescripcion(), CodigoError.NMP_PMIMONTE_0011);
		}
		return resultado;
	}
}
