/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import com.ibm.icu.util.Calendar;
import mx.com.nmp.pagos.mimonte.conector.GestionConciliacionBroker;
import mx.com.nmp.pagos.mimonte.conector.MergeConciliacionBroker;
import mx.com.nmp.pagos.mimonte.conector.MovimientosNocturnosBroker;
import mx.com.nmp.pagos.mimonte.conector.MovimientosProveedorBroker;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAdjuntoDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMailDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Nombre: ConciliacionMidasProveedorService Descripcion: Clase que proporciona los métodos para
 * consultar y cargar los movimientos nocturnos de MIDAS y los movimientos del proveedor.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 21/01/2022 15:18 hrs.
 * @version 0.1
 */
@Component
public class ConciliacionMidasProveedorService extends ConciliacionCommonService {

	/**
	 * Conector encargado de conciliar los movimientos nocturnos de MIDAS.
	 */
	@Inject
	private MovimientosNocturnosBroker movimientosNocturnosBrokerBus;

	/**
	 * Conector encargado de crear el proceso de conciliación.
	 */
	@Inject
	private GestionConciliacionBroker gestionConciliacionBroker;


	/**
	 * Conector encargado de conciliar los movimientos del proveedor.
	 */
	@Inject
	private MovimientosProveedorBroker movimientosProveedorBrokerBus;

	/**
	 * Conector encargado de conciliar los movimientos cargados.
	 */
	@Inject
	private MergeConciliacionBroker mergeConciliacionBroker;


	/**
	 * Método que ejecuta el proceso de conciliación etapa 1 - carga de movimientos nocturnos de MIDAS / carga de movimientos del proveedor.
	 * @param ejecucionConciliacion
	 *
	 */
	public void ejecutarProcesoConciliacionE1( EjecucionConciliacion ejecucionConciliacion){
		String descripcionEstatusFase="";
		Date inicioFase = obtenerFechaActual();
		Date finFase = null;
		Conciliacion conciliacionCreada = null;
		Boolean flgEjecucionCorrecta = true;

		try {
			inicioFase= obtenerFechaActual();
			GestionConciliacionResponseDTO response = gestionConciliacionBroker.crearConciliacion(ejecucionConciliacion.getConciliacion().getCuenta().getId(), ejecucionConciliacion.getConciliacion().getEntidad().getId(), ejecucionConciliacion.getProveedor().getNombre().getNombre());
			conciliacionCreada = obtenerConciliacionCargaMovimientos(Long.valueOf(response.getFolio()), ConciliacionConstants.SUBESTATUS_CONCILIACION_CREADA );
			finFase = obtenerFechaActual();
			descripcionEstatusFase = ConciliacionConstants.SUCCESSFUL_CREACION_CONCILIACION;
		} catch (Exception ex) {
			ex.printStackTrace();
			finFase = obtenerFechaActual();
			descripcionEstatusFase = ex.getMessage();
			flgEjecucionCorrecta = false;
		}

		if(conciliacionCreada != null) {
			ejecucionConciliacion.setConciliacion(conciliacionCreada);
			ejecucionConciliacion = ejecucionConciliacionService.save(ejecucionConciliacion, "sistema");
			generarTrazadoEjecucionFase(ejecucionConciliacion, inicioFase, finFase, descripcionEstatusFase);
		}

		if(flgEjecucionCorrecta  && conciliacionCreada != null){

			try {
				inicioFase= obtenerFechaActual();
				MovimientosNocturnosResponseDTO response = movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(conciliacionCreada.getId(),ejecucionConciliacion.getFechaPeriodoInicio(), ejecucionConciliacion.getFechaPeriodoFin(),conciliacionCreada.getProveedor().getNombre().getNombre(), 1L);
				finFase = obtenerFechaActual();
				flgEjecucionCorrecta = response.getCargaCorrecta();
				descripcionEstatusFase = response.getMessage();
			} catch (Exception ex) {
				ex.printStackTrace();
				finFase = obtenerFechaActual();
				descripcionEstatusFase = ex.getMessage();
				flgEjecucionCorrecta = false;
			}

			if(flgEjecucionCorrecta) {

				Conciliacion resultadoEjecucion= escucharSubEstatusConciliacion(conciliacionCreada.getId(),ConciliacionConstants.CON_SUB_ESTATUS_RESULTADO_CARGA_MOV_PN);

				if(resultadoEjecucion != null  &&  resultadoEjecucion.getSubEstatus().getId() == ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_MIDAS_COMPLETADA){
					flgEjecucionCorrecta= true;
					descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : ConciliacionConstants.SUCCESSFUL_CARGA_MOVS_MIDAS;
					conciliacionCreada = resultadoEjecucion;
					ejecucionConciliacion.setConciliacion(conciliacionCreada);
				} else{
					flgEjecucionCorrecta= false;
					descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : ConciliacionConstants.ERROR_CARGA_MOVS_MIDAS;
				}
			}

			ejecucionConciliacion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CARGA_MOVIMIENTOS_MIDAS.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CARGA_MOVIMIENTOS_MIDAS.getEstadoEjecucion()));
			generarTrazadoEjecucionFase(ejecucionConciliacion,inicioFase,finFase,descripcionEstatusFase);

			if(flgEjecucionCorrecta){

				try {
					Conciliacion conciliacionActual = this.conciliacionService.getById(conciliacionCreada.getId());
					inicioFase= obtenerFechaActual();
					MovimientosProveedorResponseDTO response = movimientosProveedorBrokerBus.cargarMovimientosProveedor(conciliacionActual.getId(),ejecucionConciliacion.getFechaPeriodoInicio(), ejecucionConciliacion.getFechaPeriodoFin(),conciliacionActual.getProveedor().getNombre().getNombre(), conciliacionActual.getSubEstatus().getId(), ConciliacionConstants.PROVEEDOR_BANCARIO_CARGA_MOVIMIENTOS );
					finFase = obtenerFechaActual();
					flgEjecucionCorrecta = response.getCargaCorrecta();
					descripcionEstatusFase = response.getMessage();
				} catch (Exception ex) {
					ex.printStackTrace();
					finFase = obtenerFechaActual();
					descripcionEstatusFase = ex.getMessage();
					flgEjecucionCorrecta = true;
				}

				if(flgEjecucionCorrecta) {

					Conciliacion resultadoEjecucion= escucharSubEstatusConciliacion(conciliacionCreada.getId(), ConciliacionConstants.CON_SUB_ESTATUS_RESULTADO_CARGA_MOV_PT);

					if(resultadoEjecucion != null  &&  ConciliacionConstants.CON_SUB_ESTATUS_RESULTADO_CARGA_MOV_PT_VALIDACION.contains(resultadoEjecucion.getSubEstatus().getId()) ){
						flgEjecucionCorrecta= true;
						descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : ConciliacionConstants.SUCCESSFUL_CARGA_MOVS_PROVEEDOR;
						conciliacionCreada = resultadoEjecucion;
						ejecucionConciliacion.setConciliacion(conciliacionCreada);
					} else{
						flgEjecucionCorrecta= false;
						descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null  && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : ConciliacionConstants.ERROR_CARGA_MOVS_PROVEEDOR;
					}
				}

				ejecucionConciliacion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CARGA_MOVIMIENTOS_PROVEEDOR.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CARGA_MOVIMIENTOS_PROVEEDOR.getEstadoEjecucion()));
				generarTrazadoEjecucionFase(ejecucionConciliacion,inicioFase,finFase,descripcionEstatusFase);

				if(flgEjecucionCorrecta){

					if(conciliacionCreada.getSubEstatus().getId() != ConciliacionConstants.SUBESTATUS_CONCILIACION_CONCILIACION_COMPLETADA) {

						try {
							inicioFase = obtenerFechaActual();
							MergeConciliacionResponseDTO response = mergeConciliacionBroker.generarMergeConciliacion(conciliacionCreada.getId());
							finFase = obtenerFechaActual();
							flgEjecucionCorrecta = response.getCargaCorrecta();
							descripcionEstatusFase = response.getMessage();
						} catch (Exception ex) {
							ex.printStackTrace();
							finFase = obtenerFechaActual();
							descripcionEstatusFase = ex.getMessage();
							flgEjecucionCorrecta = false;
						}

						if (flgEjecucionCorrecta) {

							Conciliacion resultadoEjecucion = escucharSubEstatusConciliacion(conciliacionCreada.getId(), ConciliacionConstants.CON_SUB_ESTATUS_RESULTADO_MERGE_CONCILIACION);

							if (resultadoEjecucion != null && resultadoEjecucion.getSubEstatus().getId() == ConciliacionConstants.SUBESTATUS_CONCILIACION_CONCILIACION_COMPLETADA) {
								flgEjecucionCorrecta = true;
								descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : ConciliacionConstants.SUCCESSFUL_MERGE_MIDAS_PROVEEDOR;
								conciliacionCreada = resultadoEjecucion;
								ejecucionConciliacion.setConciliacion(conciliacionCreada);
							} else {
								flgEjecucionCorrecta = false;
								descripcionEstatusFase = resultadoEjecucion.getSubEstatusDescripcion() != null && !resultadoEjecucion.getSubEstatusDescripcion().isEmpty() ? resultadoEjecucion.getSubEstatusDescripcion() : ConciliacionConstants.ERROR_MERGE_MIDAS_PROVEEDOR;
							}
						}
					} else {
						descripcionEstatusFase =  ConciliacionConstants.SUCCESSFUL_MERGE_MIDAS_PROVEEDOR;
					}

					ejecucionConciliacion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CONCILIACION_MIDAS_PROVEEDOR.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CONCILIACION_MIDAS_PROVEEDOR.getEstadoEjecucion()));
					generarTrazadoEjecucionFase(ejecucionConciliacion,inicioFase,finFase,descripcionEstatusFase);

				}
            }
		}

		if(!flgEjecucionCorrecta){
			this.enviarNotificacionEjecucionErronea(ejecucionConciliacion);
		}

	}

	public Conciliacion obtenerConciliacionCargaMovimientos(Long folio, Long subEstatusEsperado) {
		Conciliacion proceso = conciliacionService.getById(folio);
		if(proceso.getSubEstatus().getId() < subEstatusEsperado){
			ActualizarSubEstatusRequestDTO subEstatus = new ActualizarSubEstatusRequestDTO(proceso.getId(), subEstatusEsperado, proceso.getEstatus().getId(), "");
			conciliacionService.actualizaSubEstatusConciliacion(subEstatus,"sistema");
			proceso = conciliacionService.getById(folio);
		}
		return proceso;
	}

	/**
	 * Método que envia las notificación vía correo electrónico  cuando el proceso automatizado falla u obtiene un resultado erroneo.
	 * @param ejecucionConciliacion
	 *
	 */
	@Override
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

	/**
	 * Método que construye el cuerpo del correo electrónico con el que se notificara cuando el proceso automatizado falla u obtiene un resultado erróneo.
	 * @param contactos
	 * @param ejecucionConciliacion
	 * @return
	 *
	 */
	@Override
	public BusRestMailDTO construyeEMailProcesoConciliacion(List<Contactos> contactos, EjecucionConciliacion ejecucionConciliacion) {

		DatosNotificacionDTO datos = new DatosNotificacionDTO(ejecucionConciliacion.getConciliacion().getEntidad().getId(), ejecucionConciliacion.getConciliacion().getEntidad().getNombre(),ejecucionConciliacion.getConciliacion().getCuenta().getId(),ejecucionConciliacion.getConciliacion().getCuenta().getNumeroCuenta(), ejecucionConciliacion.getFechaPeriodoInicio(), ejecucionConciliacion.getFechaPeriodoFin(), ejecucionConciliacion.getProveedor().getNombre().getNombre());

		// Se obtienen destinatarios
		// Se obtiene titulo, destinatarios, remitente y cuerpo del mensaje
		String titulo = applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionConciliacionEtapa1().getTitle();
		String remitente = applicationProperties.getMimonte().getVariables().getMail().getFrom();
		String destinatarios = contactos.stream().map(Contactos::getEmail).collect(Collectors.joining(","));
		String template = applicationProperties.getMimonte().getVariables().getMail().getSolicitudEjecucionConciliacionEtapa1().getVelocityTemplate();

		// Se constrye el cuerpo de correo HTML
		Map<String, Object> model = new HashMap<>();
		model.put("elemento", datos);
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

	/**
	 * Método encargado de construir la ejecución del proceso de conciliación.
	 * @param calendarizacion
	 * @return
	 *
	 */
	public EjecucionConciliacion crearEjecucionConciliacion(CalendarioEjecucionProcesoDTO calendarizacion) {

		Date fechaActual = obtenerFechaActual();
		Calendar calendarEjecucionInicial = Calendar.getInstance();
		Calendar calendarEjecucionFin = Calendar.getInstance();
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();

		calendarEjecucionInicial.setTime(fechaActual);
		calendarEjecucionFin.setTime(fechaActual);

		calendarEjecucionInicial.add(Calendar.DAY_OF_YEAR, 0 - calendarizacion.getRangoDiasCoberturaMin());
		calendarEjecucionFin.add(Calendar.DAY_OF_YEAR, 0 - calendarizacion.getRangoDiasCoberturaMax());

		ini.setTime( calendarEjecucionInicial.getTime());
		fin.setTime( calendarEjecucionFin.getTime());
		ini.set(Calendar.HOUR_OF_DAY, 0);
		ini.set(Calendar.MINUTE, 0);
		ini.set(Calendar.SECOND, 0);
		ini.set(Calendar.MILLISECOND, 1);
		fin.set(Calendar.HOUR_OF_DAY, 23);
		fin.set(Calendar.MINUTE, 59);
		fin.set(Calendar.SECOND, 59);
		fin.set(Calendar.MILLISECOND, 59);

		EjecucionConciliacion ejecucion =  new EjecucionConciliacion();
		ejecucion.setFechaEjecucion(obtenerFechaActual());
		ejecucion.setFechaPeriodoInicio(ini.getTime());
		ejecucion.setFechaPeriodoFin(fin.getTime());
		ejecucion.setProveedor(new Proveedor(calendarizacion.getCorresponsal()));
		ejecucion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CREADA.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CREADA.getEstadoEjecucion()));

		return ejecucion;

	}

	/**
	 * Método encargado de construir el proceso de conciliación.
	 * @param ejecucionConciliacion
	 * @return
	 *
	 */
	public Conciliacion crearConciliacion(EjecucionConciliacion ejecucionConciliacion) {
		String idEntidad = applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getCorresponsal().getEntidad();
		String idCuenta = applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getCorresponsal().getCuenta();
		Conciliacion conciliacion = new Conciliacion();
		conciliacion.setCreatedDate(ejecucionConciliacion.getFechaEjecucion());
		conciliacion.setEntidad(new Entidad(null != idEntidad ? Long.valueOf(idEntidad) : 0,""));
		conciliacion.setCuenta(new Cuenta(null != idCuenta ? Long.valueOf(idCuenta) : 0, ""));
		conciliacion.setProveedor(ejecucionConciliacion.getProveedor());
		return conciliacion;
	}

	/**
	 * Método que valida que la ejecución del proceso de conciliación no se duplique.
	 * @param ejecucionConciliacion
	 * @return
	 *
	 */
	public boolean validarDuplicidadEjecucion(EjecucionConciliacion ejecucionConciliacion) {
		FiltroEjecucionConciliacionDTO filtro = new FiltroEjecucionConciliacionDTO();
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		ini.setTime( ejecucionConciliacion.getFechaPeriodoInicio() );
		fin.setTime( ejecucionConciliacion.getFechaPeriodoFin() );
		ini.set(Calendar.MILLISECOND, 0);
		fin.set(Calendar.MILLISECOND, 0);
		filtro.setFechaPeriodoInicio(ini.getTime());
		filtro.setFechaPeriodoFin(fin.getTime());
		filtro.setIdEstatus(EstatusEjecucionConciliacionEnum.CONCILIACION_MIDAS_PROVEEDOR.getIdEstadoEjecucion());
		filtro.setCorresponsal(ejecucionConciliacion.getProveedor().getNombre().getNombre());
		List<EjecucionConciliacionDTO> listaResultados = ejecucionConciliacionService.consultarByPropiedades(filtro);
		if(null != listaResultados && !listaResultados.isEmpty()){
			return false;
		}
		return true;
	}

	/**
	 * Método que valida que el proceso de conciliación no se duplique.
	 * @param ejecucionConciliacion
	 * @return
	 *
	 */
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

}
