/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroCalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TrazadoEjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.services.conciliacion.CalendarioEjecucionProcesoService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.EjecucionConciliacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Nombre: ConciliacionCommon Descripción: Clase que proporciona los métodos comunes en
 * la ejecución del proceso de conciliación.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 19/01/2022 15:18 hrs.
 * @version 0.1
 */
@Component
public class ConciliacionCommon {

	/**
	 * Servicios para gestionar la  calendarización de los procesos automatizados
	 */
	@Autowired
	public CalendarioEjecucionProcesoService calendarioEjecucionProcesoService;

	/**
	 * Servicios para gestionar la información de la ejecución del proceso de conciliación
	 */
	@Autowired
	public EjecucionConciliacionService ejecucionConciliacionService;

	/**
	 * Servicios para gestionar la información del proceso de conciliación
	 */
	@Autowired
	public ConciliacionService conciliacionService;


	public ConciliacionCommon() {
		super();
	}


	/**
	 * Método encargado de consultar las configuraciones de las calendarizaciones de los procesos automatizados.
	 * @param idProceso
	 * @param corresponsal
	 * @return
	 */
	public CalendarioEjecucionProcesoDTO obtenerCalendarizacionConciliacion(Integer idProceso, String corresponsal) {
		CalendarioEjecucionProcesoDTO calendarioEjecucionProceso = new CalendarioEjecucionProcesoDTO();
		FiltroCalendarioEjecucionProcesoDTO filtro = new FiltroCalendarioEjecucionProcesoDTO();
		filtro.setActivo(true);
		filtro.setIdProceso(idProceso);
		filtro.setCorresponsal(corresponsal);
		List<CalendarioEjecucionProcesoDTO> listaResultados = calendarioEjecucionProcesoService.consultarByPropiedades(filtro);
		if(null != listaResultados && !listaResultados.isEmpty()){
			calendarioEjecucionProceso =  listaResultados.get(0);
		}
		return calendarioEjecucionProceso;
	}

	/**
	 * Método encargado de consultar la ejecución del proceso de conciliación apartir de la conciliación.
	 * @param conciliacion
	 * @return
	 */
	public EjecucionConciliacion buscarEjecucionConciliacion(Conciliacion conciliacion) {
		EjecucionConciliacion ejecucionConciliacion;
		try{
			ejecucionConciliacion= ejecucionConciliacionService.consultarByIdConciliacion(conciliacion.getId());
		} catch (ConciliacionException ex) {
			return null;
		}
		return ejecucionConciliacion;
	}


	/**
	 * Método encargado de consultar la ejecución del proceso de conciliación apartir de la conciliación.
	 * @param idConciliacion
	 * @param listaEstados
	 * @return
	 */
	public Conciliacion escucharSubEstatusConciliacion(Long idConciliacion, List<Long> listaEstados) {
		Conciliacion resultado = null;
		for (int i = 0; i <= 10; i++) {
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				continue;
			}
			try {
				resultado =conciliacionService.getById(idConciliacion);
			} catch (Exception ex) {
				continue;
			}
			if(resultado != null && listaEstados.contains(resultado.getSubEstatus().getId())){
				return resultado;
			} else{
				resultado = null;
			}
		}
		return resultado;
	}

	/**
	 * Método encargado de generar el trazado de estatus de la ejecución del proceso de conciliación.
	 * @param ejecucionConciliacion
	 * @param inicioFase
	 * @param finFase
	 * @param descripcionEstatusFase
	 * @return
	 */
	public void  generarTrazadoEjecucionFase(EjecucionConciliacion ejecucionConciliacion, Date inicioFase, Date finFase, String descripcionEstatusFase) {
		TrazadoEjecucionConciliacion trazadoEjecucion = new TrazadoEjecucionConciliacion();
		trazadoEjecucion.setEjecucionConciliacion(ejecucionConciliacion);
		trazadoEjecucion.setFechaInicio(inicioFase);
		trazadoEjecucion.setFechaFin(finFase);
		trazadoEjecucion.setEstatus(ejecucionConciliacion.getEstatus());
		trazadoEjecucion.setEstatusDescripcion(descripcionEstatusFase);
		ejecucionConciliacionService.guardarEjecucionConciliacion(trazadoEjecucion,"sistema");
	}

}
