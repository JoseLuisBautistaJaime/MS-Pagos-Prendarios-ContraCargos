/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;

import com.ibm.icu.util.Calendar;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.ConciliacionBuilder;
import mx.com.nmp.pagos.mimonte.conector.MovimientosNocturnosBroker;
import mx.com.nmp.pagos.mimonte.conector.MovimientosProveedorBroker;
import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.constans.MailServiceConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAdjuntoDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMailDTO;
import mx.com.nmp.pagos.mimonte.controllers.conciliacion.ConciliacionController;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.CalendarioEjecucionProcesoService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService;
import mx.com.nmp.pagos.mimonte.services.conciliacion.EjecucionConciliacionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;
import mx.com.nmp.pagos.mimonte.util.Response;


/**
 * @name ConciliacionMidasProveedorScheduler
 * @description Componente para la calendarización de la ejecución automática del proceso de conciliación Etapa 1 (Carga de Movs. MIDAS / Proveedor).
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 21/12/2021 10:48 hrs.
 * @version 0.1
 */

@Configuration
@EnableScheduling
public class ConciliacionMidasProveedorScheduler implements SchedulingConfigurer {

	@Inject
	private MovimientosNocturnosBroker movimientosNocturnosBrokerBus;

	@Inject
	private MovimientosProveedorBroker movimientosProveedorBrokerBus;

    @Inject
    private ConciliacionController conciliacionController;


	/**
	 * Velocity HTML layouts Engine
	 */
	@Autowired
	private VelocityEngine velocityEngine;

	/**
	 * Contiene las propiedades del sistema
	 */
	@Autowired
	private ApplicationProperties applicationProperties;

	/**
	 * Clase de constantes que mapean propiedades relacionadas con el envio de
	 * e-mail por medio del servicio expuesto por BUS
	 */
	@Autowired
	private MailServiceConstants mc;

	@Autowired
	private CalendarioEjecucionProcesoService calendarioEjecucionProcesoService;

	@Autowired
	private ConciliacionService conciliacionService;

	@Autowired
	private EjecucionConciliacionService ejecucionConciliacionService;

	/**
	 * Repository de contactos
	 */
	@Autowired
	private ContactoRespository contactoRespository;

	/**
	 * Objeto para consumo de servicio Rest para envio de e-mail
	 */
	@Autowired
	@Qualifier("busMailRestService")
	private BusMailRestService busMailRestService;


	/**
	 Ejecuta tareas de programadas.
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

		Runnable runnableTask = () ->  lanzarConciliacionEtapa1();

		Trigger trigger = new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				String cronExpressions = obtenerCalendarizacionConciliacionEtapa1().getConfiguracionAutomatizacion();
				if (StringUtils.isEmpty(cronExpressions)) {
					return null;
				}
				CronTrigger crontrigger = new CronTrigger(cronExpressions);
				return crontrigger.nextExecutionTime(triggerContext);
			}
		};

		taskRegistrar.addTriggerTask(runnableTask , trigger);

	}

	public void lanzarConciliacionEtapa1() {
        CalendarioEjecucionProcesoDTO calendarizacion = this.obtenerCalendarizacionConciliacionEtapa1();
        EjecucionConciliacion ejecucionConciliacion  = this.crearEjecucionConciliacion(calendarizacion);
        if(this.validarDuplicidadEjecucion(ejecucionConciliacion)) {
			ejecucionConciliacion.setConciliacion(this.crearConciliacion(ejecucionConciliacion));
			if(this.validarDuplicidadProceso(ejecucionConciliacion)) {
				this.ejecutarProcesoConciliacionE1(ejecucionConciliacion);
			}
		}

	}

	public Conciliacion crearConciliacion(EjecucionConciliacion ejecucionConciliacion) {
		String idEntidad = applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getCorresponsal().getEntidad();
		String idCuenta = applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getCorresponsal().getCuenta();
		Conciliacion conciliacion = new Conciliacion();
		conciliacion.setCreatedDate(ejecucionConciliacion.getFechaEjecucion());
		conciliacion.setEntidad(new Entidad(null != idEntidad ? Long.valueOf(idEntidad) : 0));
		conciliacion.setCuenta(new Cuenta(null != idCuenta ? Long.valueOf(idCuenta) : 0));
		conciliacion.setProveedor(ejecucionConciliacion.getProveedor());
		return conciliacion;
	}

	public void ejecutarProcesoConciliacionE1( EjecucionConciliacion ejecucionConciliacion){
		String descripcionEstatusFase="";
		Date inicioFase = new Date();
		Date finFase = null;
		ConciliacionDTO conciliacionDTO = null;
		Boolean flgEjecucionCorrecta = true;

		try {
			inicioFase= new Date();
			ConciliacionResponseSaveDTO conciliacionResponseSaveDTO = ConciliacionBuilder.buildConciliacionResponseSaveDTOFromConciliacionRequestDTO( new ConciliacionRequestDTO( ejecucionConciliacion.getConciliacion().getEntidad().getId(), ejecucionConciliacion.getConciliacion().getCuenta().getId(), ejecucionConciliacion.getProveedor().getNombre().getNombre()), ejecucionConciliacion.getFechaEjecucion(), null, "Sistema");
			conciliacionDTO = conciliacionService.saveConciliacion(conciliacionResponseSaveDTO, "Sistema");
			finFase = new Date();
			ejecucionConciliacion.getConciliacion().setId(conciliacionDTO.getFolio());
			descripcionEstatusFase = "Cociliacón creada";
		} catch (Exception ex) {
			ex.printStackTrace();
			finFase = new Date();
			descripcionEstatusFase = ex.getMessage();
			flgEjecucionCorrecta = false;
		}

		ejecucionConciliacion= ejecucionConciliacionService.save(ejecucionConciliacion, "Sistema");
		this.generarTrazadoEjecucionFase(ejecucionConciliacion,inicioFase,finFase,descripcionEstatusFase);

		if(flgEjecucionCorrecta  && conciliacionDTO != null){

			try {
				inicioFase= new Date();
				MovimientosNocturnosResponseDTO response = movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(conciliacionDTO.getFolio(),ejecucionConciliacion.getFechaPeriodoInicio(), ejecucionConciliacion.getFechaPeriodoFin(),conciliacionDTO.getIdCorresponsal().getNombre(), conciliacionDTO.getSubEstatus().getId() );
				finFase = new Date();
				flgEjecucionCorrecta = response.getCargaCorrecta();
				descripcionEstatusFase = response.getMessage();
			} catch (Exception ex) {
				ex.printStackTrace();
				finFase = new Date();
				descripcionEstatusFase = ex.getMessage();
				flgEjecucionCorrecta = false;
			}

			if(flgEjecucionCorrecta) {

				Conciliacion resultadoEjecucion= this.escucharSubEstatusConciliacion(conciliacionDTO.getFolio(),ConciliacionConstants.CON_SUB_ESTATUS_RESULTADO_CARGA_MOV_PN);

				if(resultadoEjecucion != null  &&  resultadoEjecucion.getSubEstatus().getId() == ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_MIDAS_COMPLETADA){
					flgEjecucionCorrecta= true;
					descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : "Consulta de los movimientos nocturnos completada.";
					ejecucionConciliacion.setConciliacion(resultadoEjecucion);
				} else{
					flgEjecucionCorrecta= false;
					descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : "Falló la consulta de los movimientos nocturnos.";
				}
			}

			ejecucionConciliacion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CARGA_MOVIMIENTOS_MIDAS.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CARGA_MOVIMIENTOS_MIDAS.getEstadoEjecucion()));
			this.generarTrazadoEjecucionFase(ejecucionConciliacion,inicioFase,finFase,descripcionEstatusFase);

			if(flgEjecucionCorrecta){

				try {
					Conciliacion conciliacionActual = this.conciliacionService.getById(conciliacionDTO.getFolio());
					inicioFase= new Date();
					MovimientosProveedorResponseDTO response = movimientosProveedorBrokerBus.cargarMovimientosProveedor(conciliacionActual.getFolio(),ejecucionConciliacion.getFechaPeriodoInicio(), ejecucionConciliacion.getFechaPeriodoFin(),conciliacionActual.getProveedor().getNombre().getNombre(), conciliacionActual.getSubEstatus().getId(), "Bancomer" );
					finFase = new Date();
					flgEjecucionCorrecta = response.getCargaCorrecta();
					descripcionEstatusFase = response.getMessage();
				} catch (Exception ex) {
					ex.printStackTrace();
					finFase = new Date();
					descripcionEstatusFase = ex.getMessage();
					flgEjecucionCorrecta = true;
				}

				if(flgEjecucionCorrecta) {

					Conciliacion resultadoEjecucion= this.escucharSubEstatusConciliacion(conciliacionDTO.getFolio(),ConciliacionConstants.CON_SUB_ESTATUS_RESULTADO_CARGA_MOV_PT);

					if(resultadoEjecucion != null  &&  resultadoEjecucion.getSubEstatus().getId() == ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_OPEN_PAY_COMPLETADA){
						flgEjecucionCorrecta= true;
						descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : "Consulta de los movimientos del proveedor completada.";
						ejecucionConciliacion.setConciliacion(resultadoEjecucion);
					} else{
						flgEjecucionCorrecta= false;
						descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : "Falló la consulta de los movimientos del proveedor.";
					}
				}

				ejecucionConciliacion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CARGA_MOVIMIENTOS_PROVEEDOR.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CARGA_MOVIMIENTOS_PROVEEDOR.getEstadoEjecucion()));
				this.generarTrazadoEjecucionFase(ejecucionConciliacion,inicioFase,finFase,descripcionEstatusFase);

				if(flgEjecucionCorrecta){

                    try {
                        inicioFase= new Date();
                        Response response = conciliacionController.consultaGenerarFolio(conciliacionDTO.getFolio(),"Sistema");
						finFase = new Date();
                        flgEjecucionCorrecta = HttpStatus.OK.toString().equals(response.getCode());
                        descripcionEstatusFase = response.getMessage();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        finFase = new Date();
                        descripcionEstatusFase = ex.getMessage();
                        flgEjecucionCorrecta = false;
                    }

					if(flgEjecucionCorrecta) {

						Conciliacion resultadoEjecucion= this.escucharSubEstatusConciliacion(conciliacionDTO.getFolio(),ConciliacionConstants.CON_SUB_ESTATUS_RESULTADO_MERGE_CONCILIACION);

						if(resultadoEjecucion != null  &&  resultadoEjecucion.getSubEstatus().getId() == ConciliacionConstants.SUBESTATUS_CONCILIACION_CONCILIACION_COMPLETADA){
							flgEjecucionCorrecta= true;
							descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : "Conciliación de los movimientos nocturnos y del proveedor completada.";
							ejecucionConciliacion.setConciliacion(resultadoEjecucion);
						} else{
							flgEjecucionCorrecta= false;
							descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : "Falló la conciliación de los movimientos nocturnos y del proveedor.";
						}
					}

					ejecucionConciliacion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CONCILIACION_MIDAS_PROVEEDOR.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CONCILIACION_MIDAS_PROVEEDOR.getEstadoEjecucion()));
					this.generarTrazadoEjecucionFase(ejecucionConciliacion,inicioFase,finFase,descripcionEstatusFase);

				}
            }
		}

		if(!flgEjecucionCorrecta){
			this.enviarNotificacionEjecucionErronea(ejecucionConciliacion);
		}

	}

	private Conciliacion escucharSubEstatusConciliacion(Long idConciliacion, List<Long> listaEstados) {
		Conciliacion resultado = null;
		for (int i = 0; i <= 10; i++) {
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				continue;
			}
			try {
				resultado =this.conciliacionService.getById(idConciliacion);
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

	public void generarTrazadoEjecucionFase(EjecucionConciliacion ejecucionConciliacion, Date inicioFase, Date finFase, String descripcionEstatusFase) {
		TrazadoEjecucionConciliacion trazadoEjecucion = new TrazadoEjecucionConciliacion();
		trazadoEjecucion.setEjecucionConciliacion(ejecucionConciliacion);
		trazadoEjecucion.setFechaInicio(inicioFase);
		trazadoEjecucion.setFechaFin(finFase);
		trazadoEjecucion.setEstatus(ejecucionConciliacion.getEstatus());
		trazadoEjecucion.setEstatusDescripcion(descripcionEstatusFase);
		ejecucionConciliacionService.guardarEjecucionConciliacion(trazadoEjecucion,"Sistema");

	}

	public  void enviarNotificacionEjecucionErronea(EjecucionConciliacion ejecucionConciliacion) {
		BusRestMailDTO generalBusMailDTO = null;

		// Se obtienen los contactos del proceso de pre-conciliación
		List<Contactos> contactos = contactoRespository.findByIdTipoContacto(ConciliacionConstants.TIPO_CONTACTO_CONCILIACION);
		if (null == contactos || contactos.isEmpty()) {
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL, CodigoError.NMP_PMIMONTE_BUSINESS_151);
		}

		// Construye e-mail
		try {
			generalBusMailDTO = construyeEMailProcesoConciliacion( contactos, ejecucionConciliacion);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_BUILD_EMAIL,	CodigoError.NMP_PMIMONTE_BUSINESS_152);
		}
		try {
			// Envia e-mail
			busMailRestService.enviaEmail(generalBusMailDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_SENDING_EMAIL, CodigoError.NMP_PMIMONTE_BUSINESS_153);
		}

	}

	public BusRestMailDTO construyeEMailProcesoConciliacion(List<Contactos> contactos, EjecucionConciliacion ejecucionConciliacion) {

		// Se obtienen destinatarios
		// Se obtiene titulo, destinatarios, remitente y cuerpo del mensaje
		String titulo = applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionConciliacion().getTitle();
		String remitente = applicationProperties.getMimonte().getVariables().getMail().getFrom();
		String destinatarios = contactos.stream().map(Contactos::getEmail).collect(Collectors.joining(","));
		String template = applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionConciliacion().getVelocityTemplate();

		// Se constrye el cuerpo de correo HTML
		Map<String, Object> model = new HashMap<>();
		model.put("elemento", ejecucionConciliacion);
		String htmlMail = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", model);

		// Se construye el DTO
		BusRestMailDTO mailDTO = new BusRestMailDTO();
		mailDTO.setAdjuntos(new BusRestAdjuntoDTO(new ArrayList<>()));
		mailDTO.setAsunto(titulo);
		mailDTO.setContenidoHTML(htmlMail);
		mailDTO.setDe(remitente);
		mailDTO.setPara(destinatarios);

		return mailDTO;
	}

	private EjecucionConciliacion crearEjecucionConciliacion(CalendarioEjecucionProcesoDTO calendarizacion) {

		Date fechaActual = new Date();
		Calendar calendarEjecucion = Calendar.getInstance();
		calendarEjecucion.setTime(fechaActual);
		calendarEjecucion.add(Calendar.DAY_OF_YEAR, 0 - calendarizacion.getRangoDiasCobertura());
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();

		ini.setTime( calendarEjecucion.getTime());
		fin.setTime( calendarEjecucion.getTime());
		ini.set(Calendar.HOUR_OF_DAY, 0);
		ini.set(Calendar.MINUTE, 0);
		ini.set(Calendar.SECOND, 0);
		ini.set(Calendar.MILLISECOND, 0);
		fin.set(Calendar.HOUR_OF_DAY, 23);
		fin.set(Calendar.MINUTE, 59);
		fin.set(Calendar.SECOND, 59);
		fin.set(Calendar.MILLISECOND, 59);

		EjecucionConciliacion ejecucion =  new EjecucionConciliacion();
		ejecucion.setFechaEjecucion(new Date());
		ejecucion.setFechaPeriodoInicio(ini.getTime());
		ejecucion.setFechaPeriodoFin(fin.getTime());
		ejecucion.setProveedor(new Proveedor(calendarizacion.getCorresponsal()));
		ejecucion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CREADA.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CREADA.getEstadoEjecucion()));

		return ejecucion;

	}

	public boolean validarDuplicidadEjecucion(EjecucionConciliacion ejecucionConciliacion) {
		FiltroEjecucionConciliacionDTO filtro = new FiltroEjecucionConciliacionDTO();
		filtro.setFechaPeriodoInicio(ejecucionConciliacion.getFechaPeriodoInicio());
		filtro.setFechaPeriodoFin(ejecucionConciliacion.getFechaPeriodoFin());
		filtro.setCorresponsal(ejecucionConciliacion.getProveedor().getNombre().getNombre());
		List<EjecucionConciliacionDTO> listaResultados = ejecucionConciliacionService.consultarByPropiedades(filtro);
		if(null != listaResultados && !listaResultados.isEmpty()){
			return false;
		}
		return true;
	}

	public boolean validarDuplicidadProceso(EjecucionConciliacion ejecucionConciliacion) {
		ConsultaConciliacionRequestDTO filtro = new ConsultaConciliacionRequestDTO();
		filtro.setFechaDesde(ejecucionConciliacion.getConciliacion().getCreatedDate());
		filtro.setFechaHasta(ejecucionConciliacion.getConciliacion().getCreatedDate());
		filtro.setIdCorresponsal(ejecucionConciliacion.getConciliacion().getProveedor().getNombre().getNombre());
		filtro.setIdEntidad(ejecucionConciliacion.getConciliacion().getEntidad().getId());
		List<ConsultaConciliacionDTO> listaResultados = conciliacionService.consulta(filtro);
		if(null != listaResultados && !listaResultados.isEmpty()){
			return false;
		}
		return true;
	}


	public CalendarioEjecucionProcesoDTO obtenerCalendarizacionConciliacionEtapa1() {
		CalendarioEjecucionProcesoDTO calendarioEjecucionProceso = new CalendarioEjecucionProcesoDTO();
		FiltroCalendarioEjecucionProcesoDTO filtro = new FiltroCalendarioEjecucionProcesoDTO();
		filtro.setActivo(true);
		filtro.setIdProceso(ProcesoEnum.CONCILIACION_ETAPA_1.getIdProceso());
		filtro.setCorresponsal(CorresponsalEnum.OPENPAY.getNombre());
		List<CalendarioEjecucionProcesoDTO> listaResultados = calendarioEjecucionProcesoService.consultarByPropiedades(filtro);
		if(null != listaResultados && !listaResultados.isEmpty()){
			calendarioEjecucionProceso =  listaResultados.get(0);
		}
		return calendarioEjecucionProceso;
	}

}
