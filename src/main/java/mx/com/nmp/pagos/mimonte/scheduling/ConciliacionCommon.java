/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;

import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMailDTO;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroCalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TrazadoEjecucionConciliacion;
import mx.com.nmp.pagos.mimonte.services.conciliacion.CalendarioEjecucionProcesoService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.EjecucionConciliacionService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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

public abstract class ConciliacionCommon {

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

	/**
	 * Velocity HTML layouts Engine
	 */
	@Autowired
	public VelocityEngine velocityEngine;

	/**
	 * Contiene las propiedades del sistema
	 */
	@Autowired
	public ApplicationProperties applicationProperties;

	/**
	 * Repository de contactos
	 */
	@Autowired
	public ContactoRespository contactoRespository;

	/**
	 * Objeto para consumo de servicio Rest para envio de e-mail
	 */
	@Autowired
	@Qualifier("busMailRestService")
	public BusMailRestService busMailRestService;


	public ConciliacionCommon() {
		super();
	}


	/**
	 * Método encargado de consultar las configuraciones de las calendarizaciones de los procesos automatizados.
	 * @param idProceso
	 * @param corresponsal
	 * @return
	 */
	public List<CalendarioEjecucionProcesoDTO> obtenerCalendarizacionConciliacion(Integer idProceso, String corresponsal) {
		FiltroCalendarioEjecucionProcesoDTO filtro = new FiltroCalendarioEjecucionProcesoDTO();
		filtro.setActivo(true);
		filtro.setIdProceso(idProceso);
		filtro.setCorresponsal(corresponsal);
		List<CalendarioEjecucionProcesoDTO> listaResultados = calendarioEjecucionProcesoService.consultarByPropiedades(filtro);
		return listaResultados;
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

	/**
	 * Método que construye el cuerpo del correo electrónico con el que se notificara cuando el proceso automatizado falla u obtiene un resultado erróneo.
	 * @param contactos
	 * @param ejecucionConciliacion
	 *
	 */
	public abstract BusRestMailDTO construyeEMailProcesoConciliacion(List<Contactos> contactos, EjecucionConciliacion ejecucionConciliacion);


	/**
	 * Método que envia las notificación vía correo electrónico  cuando el proceso automatizado falla u obtiene un resultado erróneo.
	 * @param ejecucionConciliacion
	 *
	 */
	public abstract void enviarNotificacionEjecucionErronea(EjecucionConciliacion ejecucionConciliacion);

}
