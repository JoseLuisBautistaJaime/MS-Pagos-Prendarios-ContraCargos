/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.sevice;

import mx.com.nmp.pagos.mimonte.conector.GestionConciliacionBroker;
import mx.com.nmp.pagos.mimonte.conector.MergeConciliacionBroker;
import mx.com.nmp.pagos.mimonte.conector.MovimientosNocturnosBroker;
import mx.com.nmp.pagos.mimonte.conector.MovimientosProveedorBroker;
import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.*;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @name ConciliacionMidasProveedorServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio ConciliacionMidasProveedorService
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
public class ConciliacionMidasProveedorServiceTest {


	@InjectMocks
	private ConciliacionMidasProveedorService conciliacionMidasProveedorService;

	@Mock
	private MovimientosNocturnosBroker movimientosNocturnosBrokerBus;

	@Mock
	private GestionConciliacionBroker gestionConciliacionBroker;

	@Mock
	private MovimientosProveedorBroker movimientosProveedorBrokerBus;

	@Mock
	private MergeConciliacionBroker mergeConciliacionBroker;

	@Mock
	private EjecucionConciliacionService ejecucionConciliacionService;

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

    private EjecucionConciliacionDTO ejecucionConciliacionDTO;

	@Before
	public void setUp(){
		conciliacion = this.crearConciliacion();
		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
	}

	@Test
	public void t1_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(1L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		MovimientosNocturnosResponseDTO responseF2 = new MovimientosNocturnosResponseDTO("0", "Test", "Test", Boolean.TRUE);
		when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(3L);
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		MovimientosProveedorResponseDTO responseF3 = new MovimientosProveedorResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(movimientosProveedorBrokerBus.cargarMovimientosProveedor(any(), any(), any(), any(), any(),any())).thenReturn(responseF3);

		Conciliacion conciliacionF4 = this.crearConciliacion();
		conciliacionF4.setId(4L);
		conciliacionF4.getSubEstatus().setId(6L);
		when(conciliacionService.getById(3L)).thenReturn(conciliacionF4);

		MergeConciliacionResponseDTO responseF4 = new MergeConciliacionResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(mergeConciliacionBroker.generarMergeConciliacion(any())).thenReturn(responseF4);

		Conciliacion conciliacionF5 = this.crearConciliacion();
		conciliacionF5.setId(5L);
		conciliacionF5.getSubEstatus().setId(9L);
		when(conciliacionService.getById(4L)).thenReturn(conciliacionF5);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(movimientosProveedorBrokerBus, times(1)).cargarMovimientosProveedor(any(), any(), any(), any(), any(), any());
		verify(mergeConciliacionBroker, times(1)).generarMergeConciliacion(any());

	}

	@Test
	public void t2_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(1L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		MovimientosNocturnosResponseDTO responseF2 = new MovimientosNocturnosResponseDTO("0", "Test", "Test", Boolean.TRUE);
		when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(3L);
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		MovimientosProveedorResponseDTO responseF3 = new MovimientosProveedorResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(movimientosProveedorBrokerBus.cargarMovimientosProveedor(any(), any(), any(), any(), any(),any())).thenReturn(responseF3);

		Conciliacion conciliacionF4 = this.crearConciliacion();
		conciliacionF4.setId(4L);
		conciliacionF4.getSubEstatus().setId(6L);
		when(conciliacionService.getById(3L)).thenReturn(conciliacionF4);

		MergeConciliacionResponseDTO responseF4 = new MergeConciliacionResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(mergeConciliacionBroker.generarMergeConciliacion(any())).thenReturn(responseF4);

		Conciliacion conciliacionF5 = this.crearConciliacion();
		conciliacionF5.setId(5L);
		conciliacionF5.getSubEstatus().setId(10L);
		when(conciliacionService.getById(4L)).thenReturn(conciliacionF5);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(movimientosProveedorBrokerBus, times(1)).cargarMovimientosProveedor(any(), any(), any(), any(), any(), any());
		verify(mergeConciliacionBroker, times(1)).generarMergeConciliacion(any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t3_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);
		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(1L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		MovimientosNocturnosResponseDTO responseF2 = new MovimientosNocturnosResponseDTO("0", "Test", "Test", Boolean.TRUE);
		when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(3L);
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		MovimientosProveedorResponseDTO responseF3 = new MovimientosProveedorResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(movimientosProveedorBrokerBus.cargarMovimientosProveedor(any(), any(), any(), any(), any(),any())).thenReturn(responseF3);

		Conciliacion conciliacionF3 = this.crearConciliacion();
		conciliacionF3.setId(4L);
		conciliacionF3.getSubEstatus().setId(6L);
		when(conciliacionService.getById(3L)).thenReturn(conciliacionF3);

		when(mergeConciliacionBroker.generarMergeConciliacion(any())).thenThrow(new NullPointerException("Error occurred"));

		Conciliacion conciliacionF4 = this.crearConciliacion();
		conciliacionF4.setId(5L);
		conciliacionF4.getSubEstatus().setId(9L);
		when(conciliacionService.getById(4L)).thenReturn(conciliacionF4);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(movimientosProveedorBrokerBus, times(1)).cargarMovimientosProveedor(any(), any(), any(), any(), any(), any());
		verify(mergeConciliacionBroker, times(1)).generarMergeConciliacion(any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t4_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);
		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());


		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(1L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		MovimientosNocturnosResponseDTO responseF2 = new MovimientosNocturnosResponseDTO("0", "Test", "Test", Boolean.TRUE);
		when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(3L);
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		MovimientosProveedorResponseDTO responseF3 = new MovimientosProveedorResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(movimientosProveedorBrokerBus.cargarMovimientosProveedor(any(), any(), any(), any(), any(),any())).thenReturn(responseF3);

		Conciliacion conciliacionF3 = this.crearConciliacion();
		conciliacionF3.setId(4L);
		conciliacionF3.getSubEstatus().setId(9L);
		when(conciliacionService.getById(3L)).thenReturn(conciliacionF3);

		MergeConciliacionResponseDTO responseF4 = new MergeConciliacionResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(mergeConciliacionBroker.generarMergeConciliacion(any())).thenReturn(responseF4);

		Conciliacion conciliacionF4 = this.crearConciliacion();
		conciliacionF4.setId(5L);
		conciliacionF4.getSubEstatus().setId(9L);
		when(conciliacionService.getById(4L)).thenReturn(conciliacionF4);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(movimientosProveedorBrokerBus, times(1)).cargarMovimientosProveedor(any(), any(), any(), any(), any(), any());

	}

	@Test
	public void t5_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);
		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(1L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		MovimientosNocturnosResponseDTO responseF2 = new MovimientosNocturnosResponseDTO("0", "Test", "Test", Boolean.TRUE);
		when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(3L);
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		MovimientosProveedorResponseDTO responseF3 = new MovimientosProveedorResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(movimientosProveedorBrokerBus.cargarMovimientosProveedor(any(), any(), any(), any(), any(),any())).thenReturn(responseF3);

		Conciliacion conciliacionF4 = this.crearConciliacion();
		conciliacionF4.setId(4L);
		conciliacionF4.getSubEstatus().setId(7L);
		when(conciliacionService.getById(3L)).thenReturn(conciliacionF4);

		MergeConciliacionResponseDTO responseF4 = new MergeConciliacionResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(mergeConciliacionBroker.generarMergeConciliacion(any())).thenReturn(responseF4);

		Conciliacion conciliacionF5 = this.crearConciliacion();
		conciliacionF5.setId(5L);
		conciliacionF5.getSubEstatus().setId(9L);
		when(conciliacionService.getById(4L)).thenReturn(conciliacionF5);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(movimientosProveedorBrokerBus, times(1)).cargarMovimientosProveedor(any(), any(), any(), any(), any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t6_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);
		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(1L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		MovimientosNocturnosResponseDTO responseF2 = new MovimientosNocturnosResponseDTO("0", "Test", "Test", Boolean.TRUE);
		when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(3L);
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		when(movimientosProveedorBrokerBus.cargarMovimientosProveedor(any(), any(), any(), any(), any(),any())).thenThrow(new RuntimeException("Error occurred"));

		Conciliacion conciliacionF4 = this.crearConciliacion();
		conciliacionF4.setId(4L);
		conciliacionF4.getSubEstatus().setId(6L);
		when(conciliacionService.getById(3L)).thenReturn(conciliacionF4);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);


		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(movimientosProveedorBrokerBus, times(1)).cargarMovimientosProveedor(any(), any(), any(), any(), any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t7_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);
		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(1L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		MovimientosNocturnosResponseDTO responseF2 = new MovimientosNocturnosResponseDTO("0", "Test", "Test", Boolean.TRUE);
		when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(4L);
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t8_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);
		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(1L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenThrow(new RuntimeException("Error occurred"));

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t9_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenThrow(new RuntimeException("Error occurred"));

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test(expected = ConciliacionException.class)
	public void t10_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenThrow(new RuntimeException("Error occurred"));

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doThrow(new RuntimeException("Error occurred")).when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test(expected = InformationNotFoundException.class)
	public void t11_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenThrow(new RuntimeException("Error occurred"));

		List<Contactos> contactos = new ArrayList<>();
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());

	}

	@Test(expected = InformationNotFoundException.class)
	public void t12_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenThrow(new RuntimeException("Error occurred"));

		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(null);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());

	}

	@Test
	public void t13_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);
		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(1L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		MovimientosNocturnosResponseDTO responseF2 = new MovimientosNocturnosResponseDTO("0", "Test", "Test", Boolean.TRUE);
		when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenReturn(responseF2);

		when(conciliacionService.getById(2L)).thenReturn(null);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t14_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);
		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		MovimientosNocturnosResponseDTO responseF2 = new MovimientosNocturnosResponseDTO("0", "Test", "Test", Boolean.TRUE);
        when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenReturn(responseF2);

		when(conciliacionService.getById(any())).thenReturn(conciliacion);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t15_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);
		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(1L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		MovimientosNocturnosResponseDTO responseF2 = new MovimientosNocturnosResponseDTO("0", "Test", "Test", Boolean.TRUE);
		when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(4L);
		conciliacionF2.setSubEstatusDescripcion("");
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t16_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);
		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(0L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);
		doNothing().when(conciliacionService).actualizaSubEstatusConciliacion(any(), any());

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(1L);
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		MovimientosNocturnosResponseDTO responseF2 = new MovimientosNocturnosResponseDTO("0", "Test", "Test", Boolean.TRUE);
		when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenReturn(responseF2);

		Conciliacion conciliacionF3 = this.crearConciliacion();
		conciliacionF3.setId(4L);
		conciliacionF3.getSubEstatus().setId(3L);
		when(conciliacionService.getById(3L)).thenReturn(conciliacionF3);

		MovimientosProveedorResponseDTO responseF3 = new MovimientosProveedorResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(movimientosProveedorBrokerBus.cargarMovimientosProveedor(any(), any(), any(), any(), any(),any())).thenReturn(responseF3);

		when(conciliacionService.getById(4L)).thenReturn(conciliacion);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(movimientosProveedorBrokerBus, times(1)).cargarMovimientosProveedor(any(), any(), any(), any(), any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t17_ejecutarProcesoConciliacionE1(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GestionConciliacionResponseDTO responseF1 = new GestionConciliacionResponseDTO("0", "Test", ejecucionConciliacion.getConciliacion().getId().toString(), Boolean.TRUE);
		when(gestionConciliacionBroker.crearConciliacion(any(), any(), any())).thenReturn(responseF1);
		when(ejecucionConciliacionService.save(any(), any())).thenReturn(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Conciliacion conciliacionF1 = this.crearConciliacion();
		conciliacionF1.setId(2L);
		conciliacionF1.getSubEstatus().setId(1L);
		when(conciliacionService.getById(1L)).thenReturn(conciliacionF1);

		MovimientosNocturnosResponseDTO responseF2 = new MovimientosNocturnosResponseDTO("0", "Test", "Test", Boolean.TRUE);
		when(movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(any(), any(), any(), any(), any())).thenReturn(responseF2);

		Conciliacion conciliacionF2 = this.crearConciliacion();
		conciliacionF2.setId(3L);
		conciliacionF2.getSubEstatus().setId(3L);
		when(conciliacionService.getById(2L)).thenReturn(conciliacionF2);

		MovimientosProveedorResponseDTO responseF3 = new MovimientosProveedorResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(movimientosProveedorBrokerBus.cargarMovimientosProveedor(any(), any(), any(), any(), any(),any())).thenReturn(responseF3);

		Conciliacion conciliacionF3 = this.crearConciliacion();
		conciliacionF3.setId(4L);
		conciliacionF3.getSubEstatus().setId(7L);
		conciliacionF3.setSubEstatusDescripcion("");
		when(conciliacionService.getById(3L)).thenReturn(conciliacionF3);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionMidasProveedorService.ejecutarProcesoConciliacionE1(ejecucionConciliacion);

		verify(gestionConciliacionBroker, times(1)).crearConciliacion(any(), any(), any());
		verify(movimientosNocturnosBrokerBus, times(1)).cargarMovimientosNocturnos(any(), any(), any(), any(), any());
		verify(movimientosProveedorBrokerBus, times(1)).cargarMovimientosProveedor(any(), any(), any(), any(), any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

    @Test
    public void t18_crearEjecucionConciliacion() {

        calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
        calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

        EjecucionConciliacion resultado =conciliacionMidasProveedorService.crearEjecucionConciliacion(calendarioEjecucionProcesoDTO);

        assertNotNull(resultado);

    }

    @Test
    public void t19_crearConciliacion() {

        conciliacion = this.crearConciliacion();
        ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

        Conciliacion resultado =conciliacionMidasProveedorService.crearConciliacion(ejecucionConciliacion);

        assertNotNull(resultado);

    }

    @Test
    public void t20_validarDuplicidadEjecucion() {

        conciliacion = this.crearConciliacion();
        ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);
        ejecucionConciliacionDTO = this.crearEjecucionConciliacionDTO(ejecucionConciliacion);

        when(ejecucionConciliacionService.consultarByPropiedades(any())).thenReturn(null);
        assertTrue(conciliacionMidasProveedorService.validarDuplicidadEjecucion(ejecucionConciliacion));

        List<EjecucionConciliacionDTO> listaResultados1 = new ArrayList<>();
        when(ejecucionConciliacionService.consultarByPropiedades(any())).thenReturn(listaResultados1);
        assertTrue(conciliacionMidasProveedorService.validarDuplicidadEjecucion(ejecucionConciliacion));

        List<EjecucionConciliacionDTO> listaResultados2 = new ArrayList<>();
        listaResultados2.add(ejecucionConciliacionDTO);
        when(ejecucionConciliacionService.consultarByPropiedades(any())).thenReturn(listaResultados2);
        assertFalse(conciliacionMidasProveedorService.validarDuplicidadEjecucion(ejecucionConciliacion));

    }

    @Test
    public void t21_validarDuplicidadProceso() {

        conciliacion = this.crearConciliacion();
        ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);
        ConsultaConciliacionDTO consultaConciliacionDTO = new ConsultaConciliacionDTO();
        consultaConciliacionDTO.setFolio(ejecucionConciliacion.getConciliacion().getId());
        consultaConciliacionDTO.setFolioConciliacion(ejecucionConciliacion.getConciliacion().getFolio());
        consultaConciliacionDTO.setIdCorresponsal(ejecucionConciliacion.getProveedor().getNombre());

        when(conciliacionService.consulta(any())).thenReturn(null);
        assertTrue(conciliacionMidasProveedorService.validarDuplicidadProceso(ejecucionConciliacion));

        List<ConsultaConciliacionDTO> listaResultados1 = new ArrayList<>();
        when(conciliacionService.consulta(any())).thenReturn(listaResultados1);
        assertTrue(conciliacionMidasProveedorService.validarDuplicidadProceso(ejecucionConciliacion));

        List<ConsultaConciliacionDTO> listaResultados2 = new ArrayList<>();
        listaResultados2.add(consultaConciliacionDTO);
        when(conciliacionService.consulta(any())).thenReturn(listaResultados2);
        assertFalse(conciliacionMidasProveedorService.validarDuplicidadProceso(ejecucionConciliacion));

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
		elemento.setProceso(new CatalogoProceso(ProcesoEnum.CONCILIACION_ETAPA_1.getIdProceso()));
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
		elemento.setSubEstatus(new SubEstatusConciliacion(1L));
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
		elemento.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CREADA.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CREADA.getEstadoEjecucion()));
		elemento.setConciliacion(proceso);
		return elemento;
	}

    private EjecucionConciliacionDTO crearEjecucionConciliacionDTO(EjecucionConciliacion elemento) {
        EjecucionConciliacionDTO elementoDTO = new EjecucionConciliacionDTO();
        elementoDTO.setId(elemento.getId());
        elementoDTO.setCorresponsal(elemento.getProveedor().getNombre());
        elementoDTO.setEstatus(new EstatusEjecucionConciliacionDTO(elemento.getEstatus().getId()));
        elementoDTO.setEstatusDescripcion(elemento.getEstatus().getDescripcion());
        elementoDTO.setFechaEjecucion(elemento.getFechaEjecucion());
        elementoDTO.setFechaPeriodoInicio(elemento.getFechaPeriodoInicio());
        elementoDTO.setFechaPeriodoFin(elemento.getFechaPeriodoFin());
        elementoDTO.setConciliacion(new ConciliacionEjecucionDTO(elemento.getConciliacion().getFolio(), elemento.getConciliacion().getId()));
        return elementoDTO;
    }


}