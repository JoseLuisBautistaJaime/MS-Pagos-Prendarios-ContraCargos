/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.sevice;

import mx.com.nmp.pagos.mimonte.conector.*;
import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.controllers.conciliacion.MovimientosController;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.CalendarioEjecucionProcesoServiceImpl;
import mx.com.nmp.pagos.mimonte.util.Response;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @name ConciliacionEstadoCuentaServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio ConciliacionEstadoCuentaService
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationProperties.class})
@EnableConfigurationProperties(value = ApplicationProperties.class )
@TestPropertySource( inheritProperties = false, locations = "application-test.properties")
public class ConciliacionEstadoCuentaServiceTest {


	@InjectMocks
	private ConciliacionEstadoCuentaService conciliacionEstadoCuentaService;

	@Mock
	private MovimientosEstadoCuentaBroker movimientosEstadoCuentaBroker;

	@Mock
	private MovimientosController movimientosController;

	@Mock
	private MergeConciliacionBroker mergeConciliacionBroker;

	@Mock
	private EjecucionConciliacionService ejecucionConciliacionService;

	@Mock
	private CalendarioEjecucionProcesoService calendarioEjecucionProcesoService;

	@Mock
	private ConciliacionService conciliacionService;

	@Mock
	private ContactoRespository contactoRespository;

	@Mock
	private BusMailRestService busMailRestService;

	@Mock
	private VelocityEngine velocityEngine;

	private Conciliacion conciliacion;

	private CalendarioEjecucionProceso calendarioEjecucionProceso;

	private CalendarioEjecucionProcesoDTO calendarioEjecucionProcesoDTO;

	private EjecucionConciliacion ejecucionConciliacion;


	@Before
	public void setUp(){
		conciliacion = this.crearConciliacion();
		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
	}

	@Test
	public void t1_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		Response responseF1 = new Response("0", "Test",conciliacion);
		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenReturn(responseF1);

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(12L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		MergeConciliacionResponseDTO responseF2 = new MergeConciliacionResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(mergeConciliacionBroker.generarMergeConciliacion(any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(12L);
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());
		verify(mergeConciliacionBroker, times(1)).generarMergeConciliacion(any());

	}

	@Test
	public void t2_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		Response responseF1 = new Response("0", "Test",conciliacion);
		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenReturn(responseF1);

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(12L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		MergeConciliacionResponseDTO responseF2 = new MergeConciliacionResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(mergeConciliacionBroker.generarMergeConciliacion(any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(13L);
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());
		verify(mergeConciliacionBroker, times(1)).generarMergeConciliacion(any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t3_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		Response responseF1 = new Response("0", "Test",conciliacion);
		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenReturn(responseF1);

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(12L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		when(mergeConciliacionBroker.generarMergeConciliacion(any())).thenThrow(new NullPointerException("Error occurred"));

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());
		verify(mergeConciliacionBroker, times(1)).generarMergeConciliacion(any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t4_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		Response responseF1 = new Response("0", "Test",conciliacion);
		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenReturn(responseF1);

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(13L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());
	}

	@Test
	public void t5_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenThrow(new RuntimeException("Error occurred"));
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test(expected = ConciliacionException.class)
	public void t6_ejecutarProcesoConciliacionE2(){

	    conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenThrow(new RuntimeException("Error occurred"));
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doThrow(new RuntimeException("Error occurred")).when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test(expected = InformationNotFoundException.class)
	public void t7_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenThrow(new RuntimeException("Error occurred"));
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		List<Contactos> contactos = new ArrayList<>();
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());

	}

	@Test(expected = InformationNotFoundException.class)
	public void t8_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenThrow(new RuntimeException("Error occurred"));
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(null);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());

	}

	@Test
	public void t9_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		Response responseF1 = new Response("0", "Test",conciliacion);
		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenReturn(responseF1);

		when(conciliacionService.getById(any())).thenReturn(null);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t10_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		Response responseF1 = new Response("0", "Test",conciliacion);
		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenReturn(responseF1);

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(13L);
		conciliacionF1.setSubEstatusDescripcion("");
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t11_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		Response responseF1 = new Response("0", "Test",conciliacion);
		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenReturn(responseF1);

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(13L);
		conciliacionF1.setSubEstatusDescripcion(null);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t12_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		Response responseF1 = new Response("0", "Test",conciliacion);
		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenReturn(responseF1);

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(12L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		MergeConciliacionResponseDTO responseF2 = new MergeConciliacionResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(mergeConciliacionBroker.generarMergeConciliacion(any())).thenReturn(responseF2);

		when(conciliacionService.getById(2L)).thenReturn(null);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());
		verify(mergeConciliacionBroker, times(1)).generarMergeConciliacion(any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t13_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		Response responseF1 = new Response("0", "Test",conciliacion);
		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenReturn(responseF1);

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(12L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		MergeConciliacionResponseDTO responseF2 = new MergeConciliacionResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(mergeConciliacionBroker.generarMergeConciliacion(any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(13L);
		conciliacionF2.setSubEstatusDescripcion("");
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());
		verify(mergeConciliacionBroker, times(1)).generarMergeConciliacion(any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t14_ejecutarProcesoConciliacionE2(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		Response responseF1 = new Response("0", "Test",conciliacion);
		when(movimientosController.saveMovimientoEsadoCuenta(any(), any())).thenReturn(responseF1);

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(12L);
		conciliacionF1.setSubEstatusDescripcion(null);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		MergeConciliacionResponseDTO responseF2 = new MergeConciliacionResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(mergeConciliacionBroker.generarMergeConciliacion(any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(13L);
		conciliacionF2.setSubEstatusDescripcion(null);
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionEstadoCuentaService.ejecutarProcesoConciliacionE2(ejecucionConciliacion);

		verify(movimientosController, times(1)).saveMovimientoEsadoCuenta(any(), any());
		verify(mergeConciliacionBroker, times(1)).generarMergeConciliacion(any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t15_buscarConciliacionSinEstadoCuenta() {

		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(12L);
		when(conciliacionService.getConciliacionSinEstadoCuenta(any())).thenReturn(conciliacionF1);

		Conciliacion resultado =  conciliacionEstadoCuentaService.buscarConciliacionSinEstadoCuenta(calendarioEjecucionProcesoDTO);

		assertNotNull(resultado);
		verify(conciliacionService, times(1)).getConciliacionSinEstadoCuenta(any());

	}

	@Test
	public void t16_obtenerCalendarizacionConciliacion() {

		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		List<CalendarioEjecucionProcesoDTO> lista = new ArrayList<CalendarioEjecucionProcesoDTO>(){{add(calendarioEjecucionProcesoDTO);}};
		when(calendarioEjecucionProcesoService.consultarByPropiedades(any())).thenReturn(lista);

		List<CalendarioEjecucionProcesoDTO>  listaResultados =  conciliacionEstadoCuentaService.obtenerCalendarizacionConciliacion(calendarioEjecucionProceso.getProceso().getId(),calendarioEjecucionProceso.getProveedor().getNombre().getNombre());

		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
		verify(calendarioEjecucionProcesoService, times(1)).consultarByPropiedades(any());

	}

	@Test
	public void t17_buscarEjecucionConciliacion() {

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(ejecucionConciliacionService.consultarByIdConciliacion(any())).thenReturn(ejecucionConciliacion);

		EjecucionConciliacion  resultado =  conciliacionEstadoCuentaService.buscarEjecucionConciliacion(conciliacion);
		assertNotNull(resultado);
		verify(ejecucionConciliacionService, times(1)).consultarByIdConciliacion(any());

	}

	@Test
	public void t18_buscarEjecucionConciliacion() {

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(ejecucionConciliacionService.consultarByIdConciliacion(any())).thenThrow(new ConciliacionException("Error occurred"));

		EjecucionConciliacion  resultado2 =  conciliacionEstadoCuentaService.buscarEjecucionConciliacion(conciliacion);

		assertNull(resultado2);
		verify(ejecucionConciliacionService, times(1)).consultarByIdConciliacion(any());

	}

	private CalendarioEjecucionProcesoDTO crearCalendarioEjecucionProcesoDTO(CalendarioEjecucionProceso elemento) {
		CalendarioEjecucionProcesoDTO elementoDTO = new CalendarioEjecucionProcesoDTO();
		elementoDTO.setId(elemento.getId());
		elementoDTO.setCorresponsal(elemento.getProveedor().getNombre());
		elementoDTO.setProceso(new ProcesoDTO(elemento.getProceso().getId()));
		elementoDTO.setRangoDiasCoberturaMax(elemento.getRangoDiasCoberturaMax());
		elementoDTO.setRangoDiasCoberturaMin(elemento.getRangoDiasCoberturaMin());
		elementoDTO.setActivo(elemento.getActivo());
		elementoDTO.setReintentos(elemento.getReintentos());
		elementoDTO.setConfiguracionAutomatizacion(elemento.getConfiguracion());
		return  elementoDTO;
	}

	private CalendarioEjecucionProceso crearCalendarioEjecucionProceso() {
		CalendarioEjecucionProceso elemento = new CalendarioEjecucionProceso();
		elemento.setId(1l);
		elemento.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		elemento.setProceso(new CatalogoProceso(ProcesoEnum.CONCILIACION_ETAPA_2.getIdProceso()));
		elemento.setRangoDiasCoberturaMax(1);
		elemento.setRangoDiasCoberturaMin(1);
		elemento.setActivo(true);
		elemento.setReintentos(1);
		elemento.setConfiguracion("0 0 0 31 DEC ?");
		return elemento;
	}

	private Conciliacion crearConciliacion() {
		Conciliacion elemento = new Conciliacion();
		elemento.setId(1L);
		elemento.setCreatedDate(new Date());
		elemento.setEntidad(new Entidad(11L,""));
		elemento.setCuenta(new Cuenta(1L, ""));
		elemento.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		elemento.setEstatus(new EstatusConciliacion(1));
		elemento.setSubEstatus(new SubEstatusConciliacion(9L));
		elemento.setSubEstatusDescripcion("Test");
		return elemento;
	}

	private EjecucionConciliacion crearEjecucionConciliacion(Conciliacion proceso) {
		EjecucionConciliacion elemento = new EjecucionConciliacion();
		elemento.setId(1l);
		elemento.setFechaEjecucion(new Date());
		elemento.setFechaPeriodoInicio(new Date());
		elemento.setFechaPeriodoFin(new Date());
		elemento.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		elemento.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CONCILIACION_MIDAS_PROVEEDOR.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CONCILIACION_MIDAS_PROVEEDOR.getEstadoEjecucion()));
		elemento.setConciliacion(proceso);
		return elemento;
	}

}