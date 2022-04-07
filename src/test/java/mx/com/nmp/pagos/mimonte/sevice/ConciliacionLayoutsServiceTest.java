/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.sevice;

import mx.com.nmp.pagos.mimonte.conector.GeneracionLayoutBroker;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @name ConciliacionLayoutsServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio ConciliacionLayoutsService
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
public class ConciliacionLayoutsServiceTest {


	@InjectMocks
	private ConciliacionLayoutsService conciliacionLayoutsService;

	@Mock
	private GeneracionLayoutBroker generacionLayoutBroker;
	
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


	@Before
	public void setUp(){
		conciliacion = this.crearConciliacion();
		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
	}

	@Test
	public void t1_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GeneracionLayoutResponseDTO responseF1 = new GeneracionLayoutResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenReturn(responseF1);

		MontoLayoutConciliacionDTO montoAcumulado = new MontoLayoutConciliacionDTO(conciliacion.getId(), 0L, TipoLayoutEnum.PAGOS.toString());
		List<MontoLayoutConciliacionDTO> listaMontos = new ArrayList<MontoLayoutConciliacionDTO>(){{add(montoAcumulado);}};
		when(conciliacionService.calcularMontosLayoutsConciliacion(any())).thenReturn(listaMontos);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		GeneracionLayoutResponseDTO responseF2 = new GeneracionLayoutResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(generacionLayoutBroker.generarLayouts(1L, 3 )).thenReturn(responseF2);

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(2)).generarLayouts(any(), any());
		verify(conciliacionService, times(1)).calcularMontosLayoutsConciliacion(any());

	}

	@Test
	public void t2_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GeneracionLayoutResponseDTO responseF1 = new GeneracionLayoutResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenReturn(responseF1);

		MontoLayoutConciliacionDTO montoAcumulado = new MontoLayoutConciliacionDTO(conciliacion.getId(), 0L, TipoLayoutEnum.PAGOS.toString());
		List<MontoLayoutConciliacionDTO> listaMontos = new ArrayList<MontoLayoutConciliacionDTO>(){{add(montoAcumulado);}};
		when(conciliacionService.calcularMontosLayoutsConciliacion(any())).thenReturn(listaMontos);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		GeneracionLayoutResponseDTO responseF2 = new GeneracionLayoutResponseDTO("404", "Test", "Test",  Boolean.FALSE);
		when(generacionLayoutBroker.generarLayouts(1L, 3 )).thenReturn(responseF2);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(2)).generarLayouts(any(), any());
		verify(conciliacionService, times(1)).calcularMontosLayoutsConciliacion(any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t3_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GeneracionLayoutResponseDTO responseF1 = new GeneracionLayoutResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenReturn(responseF1);

		MontoLayoutConciliacionDTO montoAcumulado = new MontoLayoutConciliacionDTO(conciliacion.getId(), 0L, TipoLayoutEnum.PAGOS.toString());
		List<MontoLayoutConciliacionDTO> listaMontos = new ArrayList<MontoLayoutConciliacionDTO>(){{add(montoAcumulado);}};
		when(conciliacionService.calcularMontosLayoutsConciliacion(any())).thenReturn(listaMontos);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());
		when(generacionLayoutBroker.generarLayouts(1L, 3)).thenThrow(new NullPointerException("Error occurred"));

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(2)).generarLayouts(any(), any());
		verify(conciliacionService, times(1)).calcularMontosLayoutsConciliacion(any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t4_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GeneracionLayoutResponseDTO responseF1 = new GeneracionLayoutResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenReturn(responseF1);

		MontoLayoutConciliacionDTO montoAcumulado = new MontoLayoutConciliacionDTO(conciliacion.getId(), 1L, TipoLayoutEnum.PAGOS.toString());
		List<MontoLayoutConciliacionDTO> listaMontos = new ArrayList<MontoLayoutConciliacionDTO>(){{add(montoAcumulado);}};
		when(conciliacionService.calcularMontosLayoutsConciliacion(any())).thenReturn(listaMontos);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(1)).generarLayouts(any(), any());
		verify(conciliacionService, times(1)).calcularMontosLayoutsConciliacion(any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t5_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GeneracionLayoutResponseDTO responseF1 = new GeneracionLayoutResponseDTO("0", "Test", "Test",  Boolean.TRUE);
		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenReturn(responseF1);

		List<MontoLayoutConciliacionDTO> listaMontos = new ArrayList<>();
		when(conciliacionService.calcularMontosLayoutsConciliacion(any())).thenReturn(listaMontos);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(1)).generarLayouts(any(), any());
		verify(conciliacionService, times(1)).calcularMontosLayoutsConciliacion(any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t6_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenThrow(new RuntimeException("Error occurred"));
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(1)).generarLayouts(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t7_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GeneracionLayoutResponseDTO responseF1 = new GeneracionLayoutResponseDTO("400", "Test", "Test",  Boolean.TRUE);
		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenReturn(responseF1);

		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(1)).generarLayouts(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test(expected = ConciliacionException.class)
	public void t8_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenThrow(new RuntimeException("Error occurred"));
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);
		doThrow(new RuntimeException("Error occurred")).when(busMailRestService).enviaEmail(any());

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(1)).generarLayouts(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test(expected = InformationNotFoundException.class)
	public void t9_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenThrow(new RuntimeException("Error occurred"));
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		List<Contactos> contactos = new ArrayList<>();
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(1)).generarLayouts(any(), any());

	}

	@Test(expected = InformationNotFoundException.class)
	public void t10_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenThrow(new RuntimeException("Error occurred"));
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(null);

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(1)).generarLayouts(any(), any());

	}

	@Test(expected = ConciliacionException.class)
	public void t11_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GeneracionLayoutResponseDTO responseF1 = new GeneracionLayoutResponseDTO("400", "Test", "Test",  Boolean.TRUE);
		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenReturn(responseF1);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);
		doThrow(new RuntimeException("Error occurred")).when(busMailRestService).enviaEmail(any());

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(1)).generarLayouts(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test(expected = InformationNotFoundException.class)
	public void t12_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GeneracionLayoutResponseDTO responseF1 = new GeneracionLayoutResponseDTO("400", "Test", "Test",  Boolean.TRUE);
		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenReturn(responseF1);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());

		List<Contactos> contactos = new ArrayList<>();
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(1)).generarLayouts(any(), any());

	}

	@Test(expected = InformationNotFoundException.class)
	public void t13_ejecutarProcesoConciliacionE3(){

		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		GeneracionLayoutResponseDTO responseF1 = new GeneracionLayoutResponseDTO("400", "Test", "Test",  Boolean.TRUE);
		when(generacionLayoutBroker.generarLayouts(1L, 2 )).thenReturn(responseF1);
		doNothing().when(ejecucionConciliacionService).guardarEjecucionConciliacion(any(), any());
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(null);

		conciliacionLayoutsService.ejecutarProcesoConciliacionE3(ejecucionConciliacion);

		verify(generacionLayoutBroker, times(1)).generarLayouts(any(), any());

	}


	@Test
	public void t14_buscarConciliacionSinLayouts() {

		conciliacion = this.crearConciliacion();
		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		List<Conciliacion> lista = new ArrayList<Conciliacion>(){{add(conciliacion);}};
		when(conciliacionService.getConciliacionSinLayout(any())).thenReturn(lista);

		List<Conciliacion> listaResultado =  conciliacionLayoutsService.buscarConciliacionSinLayouts(calendarioEjecucionProcesoDTO);

		assertNotNull(listaResultado);
		verify(conciliacionService, times(1)).getConciliacionSinLayout(any());

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