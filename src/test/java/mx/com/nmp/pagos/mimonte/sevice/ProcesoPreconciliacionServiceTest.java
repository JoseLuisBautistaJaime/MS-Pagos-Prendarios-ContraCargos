/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.sevice;

import mx.com.nmp.pagos.mimonte.conector.*;
import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
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
 * @name ProcesoPreconciliacionServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio ProcesoPreconciliacionService
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
public class ProcesoPreconciliacionServiceTest {


	@InjectMocks
	private ProcesoPreconciliacionService procesoPreconciliacionService;
	
	@Mock
	private PreconciliacionBroker preconciliacionBrokerBus;

	@Mock
	private EjecucionPreconciliacionService ejecucionPreconciliacionService;

	@Mock
	private CalendarioEjecucionProcesoService calendarioEjecucionProcesoService;

	@Mock
	private DiaInhabilService diaInhabilService;

	@Mock
	private ContactoRespository contactoRespository;

	@Mock
	private BusMailRestService busMailRestService;

	@Mock
	private VelocityEngine velocityEngine;

	private CalendarioEjecucionProceso calendarioEjecucionProceso;

	private CalendarioEjecucionProcesoDTO calendarioEjecucionProcesoDTO;

	private EjecucionPreconciliacion ejecucionPreconciliacion;

	private EjecucionPreconciliacionDTO ejecucionPreconciliacionDTO;

	@Before
	public void setUp(){
		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
	}

	@Test
	public void t1_ejecutarProcesoPreconciliacion(){

		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		ProcesoPreconciliacionResponseDTO responseF1 = new ProcesoPreconciliacionResponseDTO("0", "Test","Test", Boolean.TRUE);
		when(preconciliacionBrokerBus.ejecutarPreconciliacion(any(), any(), any())).thenReturn(responseF1);

		when(ejecucionPreconciliacionService.save(any(), any())).thenReturn(ejecucionPreconciliacion);

		procesoPreconciliacionService.ejecutarProcesoPreconciliacion(calendarioEjecucionProcesoDTO,ejecucionPreconciliacion);

		verify(preconciliacionBrokerBus, times(1)).ejecutarPreconciliacion(any(), any(), any());
		verify(ejecucionPreconciliacionService, times(1)).save(any(), any());

	}

	@Test
	public void t2_ejecutarProcesoPreconciliacion(){

		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		ProcesoPreconciliacionResponseDTO responseF1 = new ProcesoPreconciliacionResponseDTO("0", "Test", "Test", Boolean.FALSE);
		when(preconciliacionBrokerBus.ejecutarPreconciliacion(any(), any(), any())).thenReturn(responseF1);

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		when(ejecucionPreconciliacionService.save(any(), any())).thenReturn(ejecucionPreconciliacion);

		procesoPreconciliacionService.ejecutarProcesoPreconciliacion(calendarioEjecucionProcesoDTO,ejecucionPreconciliacion);

		verify(preconciliacionBrokerBus, times(2)).ejecutarPreconciliacion(any(), any(), any());
		verify(ejecucionPreconciliacionService, times(1)).save(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test
	public void t3_ejecutarProcesoPreconciliacion(){

		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		when(preconciliacionBrokerBus.ejecutarPreconciliacion(any(), any(), any())).thenThrow(new NullPointerException("Error occurred"));

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doNothing().when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		when(ejecucionPreconciliacionService.save(any(), any())).thenReturn(ejecucionPreconciliacion);

		procesoPreconciliacionService.ejecutarProcesoPreconciliacion(calendarioEjecucionProcesoDTO,ejecucionPreconciliacion);

		verify(preconciliacionBrokerBus, times(1)).ejecutarPreconciliacion(any(), any(), any());
		verify(ejecucionPreconciliacionService, times(1)).save(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

	}

	@Test(expected = InformationNotFoundException.class)
	public void t4_ejecutarProcesoPreconciliacion(){

		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		when(preconciliacionBrokerBus.ejecutarPreconciliacion(any(), any(), any())).thenThrow(new NullPointerException("Error occurred"));

		List<Contactos> contactos = new ArrayList<>();
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);

		when(ejecucionPreconciliacionService.save(any(), any())).thenReturn(ejecucionPreconciliacion);

		procesoPreconciliacionService.ejecutarProcesoPreconciliacion(calendarioEjecucionProcesoDTO,ejecucionPreconciliacion);

		verify(preconciliacionBrokerBus, times(1)).ejecutarPreconciliacion(any(), any(), any());
		verify(ejecucionPreconciliacionService, times(1)).save(any(), any());

	}

	@Test(expected = InformationNotFoundException.class)
	public void t5_ejecutarProcesoPreconciliacion(){

		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		when(preconciliacionBrokerBus.ejecutarPreconciliacion(any(), any(), any())).thenThrow(new NullPointerException("Error occurred"));
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(null);
		when(ejecucionPreconciliacionService.save(any(), any())).thenReturn(ejecucionPreconciliacion);

		procesoPreconciliacionService.ejecutarProcesoPreconciliacion(calendarioEjecucionProcesoDTO,ejecucionPreconciliacion);

		verify(preconciliacionBrokerBus, times(1)).ejecutarPreconciliacion(any(), any(), any());
		verify(ejecucionPreconciliacionService, times(1)).save(any(), any());

	}

    @Test
    public void t6_crearEjecucionPreconciliacion() {

        calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
        calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		EjecucionPreconciliacion resultado =procesoPreconciliacionService.crearEjecucionPreconciliacion(calendarioEjecucionProcesoDTO);

        assertNotNull(resultado);

    }

    @Test
    public void t7_validarDuplicidadProceso() {

		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		ejecucionPreconciliacionDTO = this.crearEjecucionPreconciliacionDTO(ejecucionPreconciliacion);

        when(ejecucionPreconciliacionService.consultarByPropiedades(any())).thenReturn(null);
        assertTrue(procesoPreconciliacionService.validarDuplicidadProceso(ejecucionPreconciliacion));

		List<EjecucionPreconciliacionDTO> listaResultados1 = new ArrayList<>();
        when(ejecucionPreconciliacionService.consultarByPropiedades(any())).thenReturn(listaResultados1);
        assertTrue(procesoPreconciliacionService.validarDuplicidadProceso(ejecucionPreconciliacion));

		List<EjecucionPreconciliacionDTO> listaResultados2 = new ArrayList<>();
        listaResultados2.add(ejecucionPreconciliacionDTO);
        when(ejecucionPreconciliacionService.consultarByPropiedades(any())).thenReturn(listaResultados2);
        assertFalse(procesoPreconciliacionService.validarDuplicidadProceso(ejecucionPreconciliacion));

    }

	@Test
	public void t8_validarFechaInhabil() {

		CatalogoDiaInhabil diaInhabil = new CatalogoDiaInhabil(0, "Test", "Test", new Date());

		when(diaInhabilService.buscarByFecha(any())).thenReturn(null);
		assertTrue(procesoPreconciliacionService.validarFechaInhabil(new Date()));

		when(diaInhabilService.buscarByFecha(any())).thenReturn(diaInhabil);
		assertTrue(procesoPreconciliacionService.validarFechaInhabil(new Date()));

	}

	@Test
	public void t9_obtenerCalendarizacionConciliacion() {

		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		List<CalendarioEjecucionProcesoDTO> lista = new ArrayList<CalendarioEjecucionProcesoDTO>(){{add(calendarioEjecucionProcesoDTO);}};
		when(calendarioEjecucionProcesoService.consultarByPropiedades(any())).thenReturn(lista);

		List<CalendarioEjecucionProcesoDTO>  listaResultados =  procesoPreconciliacionService.obtenerCalendarizacionPreconciliacion();

		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
		verify(calendarioEjecucionProcesoService, times(1)).consultarByPropiedades(any());

	}

	@Test(expected = ConciliacionException.class)
	public void t10_ejecutarProcesoPreconciliacion(){

		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		calendarioEjecucionProceso = this.crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		when(preconciliacionBrokerBus.ejecutarPreconciliacion(any(), any(), any())).thenThrow(new NullPointerException("Error occurred"));

		Contactos contacto = new Contactos();
		contacto.setNombre("Test");
		contacto.setEmail("test@mail.com");
		List<Contactos> contactos = new ArrayList<Contactos>(){{add(contacto);}};
		when(contactoRespository.findByIdTipoContacto(any())).thenReturn(contactos);
		doThrow(new RuntimeException("Error occurred")).when(busMailRestService).enviaEmail(any());
		when(velocityEngine.mergeTemplate(any(), any(), any(), any())).thenReturn(Boolean.TRUE);

		when(ejecucionPreconciliacionService.save(any(), any())).thenReturn(ejecucionPreconciliacion);

		procesoPreconciliacionService.ejecutarProcesoPreconciliacion(calendarioEjecucionProcesoDTO,ejecucionPreconciliacion);

		verify(preconciliacionBrokerBus, times(1)).ejecutarPreconciliacion(any(), any(), any());
		verify(ejecucionPreconciliacionService, times(1)).save(any(), any());
		verify(busMailRestService, times(1)).enviaEmail(any());

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

	private EjecucionPreconciliacion crearEjecucionPreconciliacion() {
		EjecucionPreconciliacion elemento = new EjecucionPreconciliacion();
		elemento.setId(1l);
		elemento.setFechaEjecucion(new Date());
		elemento.setFechaPeriodoInicio(new Date());
		elemento.setFechaPeriodoFin(new Date());
		elemento.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		elemento.setEstatus(new EstatusEjecucionPreconciliacion(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getIdEstadoEjecucion()));
		elemento.setEstatusDescripcion(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getEstadoEjecucion());
		return elemento;
	}

	private EjecucionPreconciliacionDTO crearEjecucionPreconciliacionDTO(EjecucionPreconciliacion elemento) {
		EjecucionPreconciliacionDTO elementoDTO = new EjecucionPreconciliacionDTO();
		elementoDTO.setId(elemento.getId());
		elementoDTO.setCorresponsal(elemento.getProveedor().getNombre());
		elementoDTO.setEstatus(new EstatusEjecucionPreconciliacionDTO(elemento.getEstatus().getId()));
		elementoDTO.setEstatusDescripcion(elemento.getEstatusDescripcion());
		elementoDTO.setFechaEjecucion(elemento.getFechaEjecucion());
		elementoDTO.setFechaPeriodoInicio(elemento.getFechaPeriodoInicio());
		elementoDTO.setFechaPeriodoFin(elemento.getFechaPeriodoFin());
		return elementoDTO;
	}

}