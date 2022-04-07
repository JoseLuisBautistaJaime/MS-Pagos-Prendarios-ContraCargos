/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.sevice;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.CalendarioEjecucionProcesoRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.scheduling.ConciliacionEstadoCuentaScheduler;
import mx.com.nmp.pagos.mimonte.scheduling.ConciliacionLayoutsScheduler;
import mx.com.nmp.pagos.mimonte.scheduling.ConciliacionMidasProveedorScheduler;
import mx.com.nmp.pagos.mimonte.scheduling.PreconciliacionScheduler;
import mx.com.nmp.pagos.mimonte.services.conciliacion.CalendarioEjecucionProcesoService;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.CalendarioEjecucionProcesoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @name CalendarioEjecucionProcesoServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio CalendarioEjecucionProcesoService
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 12:55 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class CalendarioEjecucionProcesoServiceTest {

	@InjectMocks
	private CalendarioEjecucionProcesoService calendarioEjecucionProcesoService= new CalendarioEjecucionProcesoServiceImpl();

	@Mock
	private PreconciliacionScheduler preconciliacionScheduler;

	@Mock
	private ConciliacionMidasProveedorScheduler conciliacionMidasProveedorScheduler;

	@Mock
	private ConciliacionEstadoCuentaScheduler conciliacionEstadoCuentaScheduler;

	@Mock
	private ConciliacionLayoutsScheduler conciliacionLayoutsScheduler;

	@Mock
	private CalendarioEjecucionProcesoRepository calendarioEjecucionProcesoRepository;

	private CalendarioEjecucionProceso calendarioEjecucionProceso;

	private FiltroCalendarioEjecucionProcesoDTO filtroCalendarioEjecucionProcesoDTO;

	private CalendarioEjecucionProcesoDTO calendarioEjecucionProcesoDTO;

	@Before
	public void setUp(){
		calendarioEjecucionProceso = this.crearElemento();
		filtroCalendarioEjecucionProcesoDTO = this.crearFiltro(calendarioEjecucionProceso);
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		doNothing().when(preconciliacionScheduler).lanzarPreconciliacionAutomatizada(any());
		doNothing().when(conciliacionMidasProveedorScheduler).lanzarConciliacionEtapa1(any());
		doNothing().when(conciliacionEstadoCuentaScheduler).lanzarConciliacionEtapa2(any());
		doNothing().when(conciliacionLayoutsScheduler).lanzarConciliacionEtapa3(any());
	}

	private CalendarioEjecucionProcesoDTO crearElementoDTO(CalendarioEjecucionProceso elemento) {
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

	private FiltroCalendarioEjecucionProcesoDTO crearFiltro(CalendarioEjecucionProceso elemento) {
		FiltroCalendarioEjecucionProcesoDTO filtro = new FiltroCalendarioEjecucionProcesoDTO();
		filtro.setIdCalendario(elemento.getId());
		filtro.setIdProceso(elemento.getProceso().getId());
		filtro.setCorresponsal(elemento.getProveedor().getNombre().getNombre());
		filtro.setActivo(elemento.getActivo());
		filtro.setReintentos(elemento.getReintentos());
		return filtro;
	}

	private CalendarioEjecucionProceso crearElemento() {
		CalendarioEjecucionProceso elemento = new CalendarioEjecucionProceso();
		elemento.setId(1l);
		elemento.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		elemento.setProceso(new CatalogoProceso(ProcesoEnum.PRE_CONCILIACION.getIdProceso()));
		elemento.setRangoDiasCoberturaMax(1);
		elemento.setRangoDiasCoberturaMin(1);
		elemento.setActivo(true);
		elemento.setReintentos(1);
		elemento.setConfiguracion("0 0 0 31 DEC ?");
		return elemento;
	}

	@Test
	public void  t1_consultarByPropiedades(){
		calendarioEjecucionProceso = this.crearElemento();
		filtroCalendarioEjecucionProcesoDTO = this.crearFiltro(calendarioEjecucionProceso);
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		List<CalendarioEjecucionProcesoDTO> lista = new ArrayList<>();
		lista.add(calendarioEjecucionProcesoDTO);
		filtroCalendarioEjecucionProcesoDTO.setCorresponsal(null);
		when(calendarioEjecucionProcesoRepository.findByPropiedades(any(), any(), any(), any(), any())).thenReturn(lista);
		List<CalendarioEjecucionProcesoDTO> listaResultados = calendarioEjecucionProcesoService.consultarByPropiedades(filtroCalendarioEjecucionProcesoDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void  t2_consultarByPropiedades(){
		calendarioEjecucionProceso = this.crearElemento();
		filtroCalendarioEjecucionProcesoDTO = this.crearFiltro(calendarioEjecucionProceso);
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		List<CalendarioEjecucionProcesoDTO> lista = new ArrayList<>();
		lista.add(calendarioEjecucionProcesoDTO);
		when(calendarioEjecucionProcesoRepository.findByPropiedades(any(), any(), any(), any(), any())).thenReturn(lista);
		List<CalendarioEjecucionProcesoDTO> listaResultados = calendarioEjecucionProcesoService.consultarByPropiedades(filtroCalendarioEjecucionProcesoDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void t3_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProceso.getProceso().setId(ProcesoEnum.PRE_CONCILIACION.getIdProceso());
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		Optional<CalendarioEjecucionProceso> resultadoConsulta  = Optional.of(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.findById(any())).thenReturn(resultadoConsulta);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(calendarioEjecucionProceso);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.FALSE);
		assertNotNull(respuesta);
		assertTrue(respuesta.getProceso().getId().equals(ProcesoEnum.PRE_CONCILIACION.getIdProceso()));
	}

	@Test
	public void t4_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProceso.getProceso().setId(ProcesoEnum.CONCILIACION_ETAPA_1.getIdProceso());
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		Optional<CalendarioEjecucionProceso> resultadoConsulta  = Optional.of(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.findById(any())).thenReturn(resultadoConsulta);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(calendarioEjecucionProceso);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.FALSE);
		assertNotNull(respuesta);
		assertTrue(respuesta.getProceso().getId().equals(ProcesoEnum.CONCILIACION_ETAPA_1.getIdProceso()));
	}

	@Test
	public void t5_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProceso.getProceso().setId(ProcesoEnum.CONCILIACION_ETAPA_2.getIdProceso());
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		Optional<CalendarioEjecucionProceso> resultadoConsulta  = Optional.of(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.findById(any())).thenReturn(resultadoConsulta);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(calendarioEjecucionProceso);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.FALSE);
		assertNotNull(respuesta);
		assertTrue(respuesta.getProceso().getId().equals(ProcesoEnum.CONCILIACION_ETAPA_2.getIdProceso()));
	}

	@Test
	public void t6_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProceso.getProceso().setId(ProcesoEnum.CONCILIACION_ETAPA_3.getIdProceso());
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		Optional<CalendarioEjecucionProceso> resultadoConsulta  = Optional.of(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.findById(any())).thenReturn(resultadoConsulta);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(calendarioEjecucionProceso);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.FALSE);
		assertNotNull(respuesta);
		assertTrue(respuesta.getProceso().getId().equals(ProcesoEnum.CONCILIACION_ETAPA_3.getIdProceso()));
	}

	@Test
	public void t7_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProceso.getProceso().setId(ProcesoEnum.PRE_CONCILIACION.getIdProceso());
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(calendarioEjecucionProceso);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.TRUE);
		assertNotNull(respuesta);
		assertTrue(respuesta.getProceso().getId().equals(ProcesoEnum.PRE_CONCILIACION.getIdProceso()));
	}

	@Test
	public void t8_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProceso.getProceso().setId(ProcesoEnum.CONCILIACION_ETAPA_1.getIdProceso());
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(calendarioEjecucionProceso);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.TRUE);
		assertNotNull(respuesta);
		assertTrue(respuesta.getProceso().getId().equals(ProcesoEnum.CONCILIACION_ETAPA_1.getIdProceso()));
	}

	@Test
	public void t9_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProceso.getProceso().setId(ProcesoEnum.CONCILIACION_ETAPA_2.getIdProceso());
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(calendarioEjecucionProceso);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.TRUE);
		assertNotNull(respuesta);
		assertTrue(respuesta.getProceso().getId().equals(ProcesoEnum.CONCILIACION_ETAPA_2.getIdProceso()));
	}

	@Test
	public void t10_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProceso.getProceso().setId(ProcesoEnum.CONCILIACION_ETAPA_3.getIdProceso());
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(calendarioEjecucionProceso);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.TRUE);
		assertNotNull(respuesta);
		assertTrue(respuesta.getProceso().getId().equals(ProcesoEnum.CONCILIACION_ETAPA_3.getIdProceso()));
	}

	@Test
	public void t11_saveCalendarioEjecucionProceso(){
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(null);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.TRUE);
		assertNull(respuesta);
	}

	@Test
	public void t12_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProceso.setId(null);
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(calendarioEjecucionProceso);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.TRUE);
		assertNotNull(respuesta);
	}

	@Test
	public void t13_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProceso.setId(0L);
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(calendarioEjecucionProceso);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.TRUE);
		assertNotNull(respuesta);
	}

	@Test
	public void t14_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		Optional<CalendarioEjecucionProceso> resultadoConsulta  = Optional.empty();
		when(calendarioEjecucionProcesoRepository.findById(any())).thenReturn(resultadoConsulta);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(calendarioEjecucionProceso);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.FALSE);
		assertNotNull(respuesta);
	}

	@Test
	public void t15_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		Optional<CalendarioEjecucionProceso> resultadoConsulta  = Optional.empty();
		when(calendarioEjecucionProcesoRepository.findById(any())).thenReturn(resultadoConsulta);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(null);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.FALSE);
		assertNull(respuesta);
	}

	@Test
	public void t16_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		Optional<CalendarioEjecucionProceso> resultadoConsulta  = Optional.of(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.findById(any())).thenReturn(resultadoConsulta);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(null);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.FALSE);
		assertNull(respuesta);
	}

	@Test
	public void t17_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		calendarioEjecucionProceso.setId(null);
		Optional<CalendarioEjecucionProceso> resultadoConsulta  = Optional.of(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.findById(any())).thenReturn(resultadoConsulta);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(null);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.FALSE);
		assertNull(respuesta);
	}

	@Test
	public void t18_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProceso.setId(0L);
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		Optional<CalendarioEjecucionProceso> resultadoConsulta  = Optional.of(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.findById(any())).thenReturn(resultadoConsulta);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(null);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.FALSE);
		assertNull(respuesta);
	}

	@Test
	public void t19_saveCalendarioEjecucionProceso(){
		calendarioEjecucionProceso = this.crearElemento();
		calendarioEjecucionProceso.setConfiguracion("");
		calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
		when(calendarioEjecucionProcesoRepository.save(any())).thenReturn(calendarioEjecucionProceso);
		CalendarioEjecucionProcesoDTO respuesta = calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(calendarioEjecucionProcesoDTO, Boolean.TRUE);
		assertNotNull(respuesta);
		assertTrue(respuesta.getConfiguracionAutomatizacion().isEmpty());
	}
}