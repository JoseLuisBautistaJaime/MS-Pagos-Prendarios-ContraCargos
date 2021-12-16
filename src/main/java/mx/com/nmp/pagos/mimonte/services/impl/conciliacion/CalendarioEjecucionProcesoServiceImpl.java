/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.CalendarioEjecucionProcesoRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroCalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.services.conciliacion.CalendarioEjecucionProcesoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * @name CalendarioEjecucionProcesoServiceImpl
 * @description Clase de capa de servicio para la calendarización de los procesos automatizados que sirve para
 *              realizar operaciones de lógica de negocios
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 30/11/2021 10:48 hrs.
 * @version 0.1
 */
@Service("calendarioEjecucionProcesoServiceImpl")
public class CalendarioEjecucionProcesoServiceImpl implements CalendarioEjecucionProcesoService {

	/**
	 * Instancia para impresion de LOG's
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CalendarioEjecucionProcesoServiceImpl.class);


	@Autowired
	private CalendarioEjecucionProcesoRepository calendarioEjecucionProcesoRepository;



	/**
	 * Metodo que realiza una busqueda a partir de un objeto de tipo
	 * FiltroCalendarioEjecucionProcesoDTO devolviendo como resultado una lista de tipo
	 * CalendarioEjecucionProcesoDTO.
	 */
	@Override
	public List<CalendarioEjecucionProcesoDTO> consultarByPropiedades(FiltroCalendarioEjecucionProcesoDTO filtroDTO) {

		// Declaracion de objetos necesarios
		List<CalendarioEjecucionProcesoDTO> result = null;

		// Se hace UPPERCASE de nombre corresponsal
		filtroDTO.setCorresponsal(null != filtroDTO.getCorresponsal() ? filtroDTO.getCorresponsal().toUpperCase() : null);

		result = calendarioEjecucionProcesoRepository.findByPropiedades(filtroDTO.getIdCalendario(), filtroDTO.getIdProceso(), filtroDTO.getActivo(), filtroDTO.getReintentos(), null !=  filtroDTO.getCorresponsal() ? CorresponsalEnum.getByNombre( filtroDTO.getCorresponsal()) : null );

		return result;
	}
}
