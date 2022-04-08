/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.sevice;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.EjecucionConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.TrazadoEjecucionConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.EjecucionConciliacionService;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.EjecucionConciliacionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @name EjecucionConciliacionServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio EjecucionConciliacionService
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 12:55 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class EjecucionConciliacionServiceTest {

	@InjectMocks
	private EjecucionConciliacionService ejecucionConciliacionService= new EjecucionConciliacionServiceImpl();

	@Mock
	private EjecucionConciliacionRepository ejecucionConciliacionRepository;

	@Mock
	private TrazadoEjecucionConciliacionRepository trazadoEjecucionConciliacionRepository;

	private EjecucionConciliacion ejecucionConciliacion;

	private FiltroEjecucionConciliacionDTO filtroEjecucionConciliacionDTO;

	private EjecucionConciliacionDTO ejecucionConciliacionDTO;

	private TrazadoEjecucionConciliacion trazadoEjecucionConciliacion;

	@Before
	public void setUp() {

		ejecucionConciliacion = this.crearElemento();

		filtroEjecucionConciliacionDTO = this.crearFiltro(ejecucionConciliacion);

		trazadoEjecucionConciliacion = this.crearTrazado(ejecucionConciliacion);

		ejecucionConciliacionDTO = this.crearElementoDTO(ejecucionConciliacion);
	}

	private EjecucionConciliacionDTO crearElementoDTO(EjecucionConciliacion elemento) {
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

	private TrazadoEjecucionConciliacion crearTrazado(EjecucionConciliacion elemento) {
		TrazadoEjecucionConciliacion trazado = new TrazadoEjecucionConciliacion();
		trazado.setId(1L);
		trazado.setFechaInicio(new Date());
		trazado.setFechaFin(new Date());
		trazado.setEjecucionConciliacion(elemento);
		trazado.setEstatus(elemento.getEstatus());
		trazado.setEstatusDescripcion(elemento.getEstatus().getDescripcion());
		return trazado;
	}

	private FiltroEjecucionConciliacionDTO crearFiltro(EjecucionConciliacion elemento) {
		FiltroEjecucionConciliacionDTO filtroDTO = new FiltroEjecucionConciliacionDTO();
		filtroDTO.setFechaPeriodoInicio(elemento.getFechaPeriodoInicio());
		filtroDTO.setFechaPeriodoFin(elemento.getFechaPeriodoFin());
		filtroDTO.setCorresponsal(elemento.getProveedor().getNombre().getNombre());
		filtroDTO.setIdEstatus(elemento.getEstatus().getId());
		filtroDTO.setFechaEjecucionDesde(elemento.getFechaEjecucion());
		filtroDTO.setFechaEjecucionHasta(elemento.getFechaEjecucion());
		filtroDTO.setIdConciliacion(elemento.getConciliacion().getId());
		filtroDTO.setIdCuenta(elemento.getConciliacion().getCuenta().getId());
		filtroDTO.setIdEntidad(elemento.getConciliacion().getEntidad().getId());
		return filtroDTO;
	}

	private EjecucionConciliacion crearElemento() {
		Conciliacion conciliacion = new Conciliacion();
		conciliacion.setId(1L);
		conciliacion.setCreatedDate(new Date());
		conciliacion.setEntidad(new Entidad(11L, ""));
		conciliacion.setCuenta(new Cuenta(1L, ""));
		conciliacion.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		conciliacion.setEstatus(new EstatusConciliacion(1));
		conciliacion.setSubEstatus(new SubEstatusConciliacion(1L));

		EjecucionConciliacion elemento = new EjecucionConciliacion();
		elemento.setId(1l);
		elemento.setFechaEjecucion(new Date());
		elemento.setFechaPeriodoInicio(new Date());
		elemento.setFechaPeriodoFin(new Date());
		elemento.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		elemento.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CREADA.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CREADA.getEstadoEjecucion()));
		elemento.setConciliacion(conciliacion);
		return elemento;

	}

	@Test
	public void t1_consultarByPropiedades() {
		ejecucionConciliacion = this.crearElemento();
		filtroEjecucionConciliacionDTO = this.crearFiltro(ejecucionConciliacion);
		ejecucionConciliacionDTO = this.crearElementoDTO(ejecucionConciliacion);
		List<EjecucionConciliacionDTO> lista = new ArrayList<EjecucionConciliacionDTO>(){{add(ejecucionConciliacionDTO);}};
		when(ejecucionConciliacionRepository.findByPropiedades(any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(lista);
		List<EjecucionConciliacionDTO> listaResultados = ejecucionConciliacionService.consultarByPropiedades(filtroEjecucionConciliacionDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void t2_consultarByPropiedades() {
		ejecucionConciliacion = this.crearElemento();
		filtroEjecucionConciliacionDTO = this.crearFiltro(ejecucionConciliacion);
		ejecucionConciliacionDTO = this.crearElementoDTO(ejecucionConciliacion);
		filtroEjecucionConciliacionDTO.setFechaEjecucionDesde(null);
		List<EjecucionConciliacionDTO> lista = new ArrayList<EjecucionConciliacionDTO>(){{add(ejecucionConciliacionDTO);}};
		when(ejecucionConciliacionRepository.findByPropiedades(any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(lista);
		List<EjecucionConciliacionDTO> listaResultados = ejecucionConciliacionService.consultarByPropiedades(filtroEjecucionConciliacionDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void t3_consultarByPropiedades() {
		ejecucionConciliacion = this.crearElemento();
		filtroEjecucionConciliacionDTO = this.crearFiltro(ejecucionConciliacion);
		ejecucionConciliacionDTO = this.crearElementoDTO(ejecucionConciliacion);
		filtroEjecucionConciliacionDTO.setFechaEjecucionHasta(null);
		List<EjecucionConciliacionDTO> lista = new ArrayList<EjecucionConciliacionDTO>(){{add(ejecucionConciliacionDTO);}};
		when(ejecucionConciliacionRepository.findByPropiedades(any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(lista);
		List<EjecucionConciliacionDTO> listaResultados = ejecucionConciliacionService.consultarByPropiedades(filtroEjecucionConciliacionDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void t4_consultarByPropiedades() {
		ejecucionConciliacion = this.crearElemento();
		filtroEjecucionConciliacionDTO = this.crearFiltro(ejecucionConciliacion);
		ejecucionConciliacionDTO = this.crearElementoDTO(ejecucionConciliacion);
		filtroEjecucionConciliacionDTO.setCorresponsal(null);
		List<EjecucionConciliacionDTO> lista = new ArrayList<EjecucionConciliacionDTO>(){{add(ejecucionConciliacionDTO);}};
		when(ejecucionConciliacionRepository.findByPropiedades(any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(lista);
		List<EjecucionConciliacionDTO> listaResultados = ejecucionConciliacionService.consultarByPropiedades(filtroEjecucionConciliacionDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void t5_consultarByIdEjecucion() {
		ejecucionConciliacion = this.crearElemento();
		ejecucionConciliacionDTO = this.crearElementoDTO(ejecucionConciliacion);
		when(ejecucionConciliacionRepository.findByIdEjecucion(anyLong())).thenReturn(ejecucionConciliacionDTO);
		EjecucionConciliacionDTO resultado =  ejecucionConciliacionService.consultarByIdEjecucion(ejecucionConciliacion.getId());
		assertNotNull(resultado);
	}

	@Test
	public void t6_consultarByIdEjecucion() {
		when(ejecucionConciliacionRepository.findByIdEjecucion(any())).thenReturn(ejecucionConciliacionDTO);
		assertThrows(ConciliacionException.class, () -> ejecucionConciliacionService.consultarByIdEjecucion(null));
	}

	@Test
	public void t7_consultarByIdEjecucion() {
		when(ejecucionConciliacionRepository.findByIdEjecucion(any())).thenReturn(null);
		assertThrows(ConciliacionException.class, () -> ejecucionConciliacionService.consultarByIdEjecucion(1L));
	}

	@Test(expected = ConciliacionException.class)
	public void t8_consultarByIdEjecucion() {
		when(ejecucionConciliacionRepository.findByIdEjecucion(any())).thenThrow(new NullPointerException("Error occurred"));
		EjecucionConciliacionDTO resultado =  ejecucionConciliacionService.consultarByIdEjecucion(1L);
	}

	@Test
	public void t9_guardarEjecucionConciliacion(){
		ejecucionConciliacion = this.crearElemento();
		trazadoEjecucionConciliacion = this.crearTrazado(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionRepository).actualizaEstatusEjecucionConciliacion(anyLong(), any(), anyString(), any());
		when(ejecucionConciliacionRepository.save(any())).thenReturn(ejecucionConciliacion);
		when(trazadoEjecucionConciliacionRepository.save( any())).thenReturn(trazadoEjecucionConciliacion);
		Assertions.assertDoesNotThrow(() -> ejecucionConciliacionService.guardarEjecucionConciliacion(trazadoEjecucionConciliacion, "TEST"));
	}

	@Test
	public void t10_guardarEjecucionConciliacion(){
		ejecucionConciliacion = this.crearElemento();
		trazadoEjecucionConciliacion = this.crearTrazado(ejecucionConciliacion);
		trazadoEjecucionConciliacion.getEjecucionConciliacion().setId(null);
		doNothing().when(ejecucionConciliacionRepository).actualizaEstatusEjecucionConciliacion(anyLong(), any(), anyString(), any());
		when(ejecucionConciliacionRepository.save(any())).thenReturn(this.crearElemento());
		when(trazadoEjecucionConciliacionRepository.save( any())).thenReturn(trazadoEjecucionConciliacion);
		Assertions.assertDoesNotThrow(() -> ejecucionConciliacionService.guardarEjecucionConciliacion(trazadoEjecucionConciliacion, "TEST"));
	}

	@Test
	public void t11_guardarEjecucionConciliacion(){
		ejecucionConciliacion = this.crearElemento();
		trazadoEjecucionConciliacion = this.crearTrazado(ejecucionConciliacion);
		trazadoEjecucionConciliacion.getEjecucionConciliacion().setId(0L);
		doNothing().when(ejecucionConciliacionRepository).actualizaEstatusEjecucionConciliacion(anyLong(), any(), anyString(), any());
		when(ejecucionConciliacionRepository.save(any())).thenReturn(this.crearElemento());
		when(trazadoEjecucionConciliacionRepository.save( any())).thenReturn(trazadoEjecucionConciliacion);
		Assertions.assertDoesNotThrow(() -> ejecucionConciliacionService.guardarEjecucionConciliacion(trazadoEjecucionConciliacion, "TEST"));
	}


	@Test(expected = ConciliacionException.class)
	public void t12_guardarEjecucionConciliacion(){
		ejecucionConciliacion = this.crearElemento();
		trazadoEjecucionConciliacion = this.crearTrazado(ejecucionConciliacion);
		trazadoEjecucionConciliacion.getEjecucionConciliacion().setId(0L);
		when(ejecucionConciliacionRepository.save(any())).thenReturn(Exception.class);
		ejecucionConciliacionService.guardarEjecucionConciliacion(trazadoEjecucionConciliacion, "TEST");
	}

	@Test(expected = ConciliacionException.class)
	public void t13_guardarEjecucionConciliacion(){
		ejecucionConciliacion = this.crearElemento();
		trazadoEjecucionConciliacion = this.crearTrazado(ejecucionConciliacion);
		doNothing().when(ejecucionConciliacionRepository).actualizaEstatusEjecucionConciliacion(anyLong(), any(), anyString(), any());
		when(trazadoEjecucionConciliacionRepository.save( any())).thenReturn(Exception.class);
		ejecucionConciliacionService.guardarEjecucionConciliacion(trazadoEjecucionConciliacion, "TEST");
	}

	@Test
	public void t14_save() {
		ejecucionConciliacion = this.crearElemento();
		when(ejecucionConciliacionRepository.save(any())).thenReturn(ejecucionConciliacion);
		EjecucionConciliacion respuesta = ejecucionConciliacionService.save(ejecucionConciliacion,"Test");
		assertNotNull(respuesta);
	}

	@Test(expected = ConciliacionException.class)
	public void t15_save() {
		ejecucionConciliacion = this.crearElemento();
		when(ejecucionConciliacionRepository.save(any())).thenReturn(Exception.class);
		ejecucionConciliacionService.save(ejecucionConciliacion,"Test");
	}


	@Test
	public void t16_consultarByIdConciliacion(){
		ejecucionConciliacion = this.crearElemento();
		when(ejecucionConciliacionRepository.findByIdConciliacion(any())).thenReturn(ejecucionConciliacion);
		EjecucionConciliacion resultado =  ejecucionConciliacionService.consultarByIdConciliacion(ejecucionConciliacion.getConciliacion().getId());
		assertNotNull(resultado);
	}

	@Test(expected = ConciliacionException.class)
	public void t17_consultarByIdConciliacion(){
		ejecucionConciliacion = this.crearElemento();
		when(ejecucionConciliacionRepository.findByIdConciliacion(any())).thenReturn(ejecucionConciliacion);
		EjecucionConciliacion resultado =  ejecucionConciliacionService.consultarByIdConciliacion(null);
	}

	@Test(expected = ConciliacionException.class)
	public void t18_consultarByIdConciliacion(){
		when(ejecucionConciliacionRepository.findByIdConciliacion(any())).thenThrow(new NullPointerException("Error occurred"));
		EjecucionConciliacion resultado =  ejecucionConciliacionService.consultarByIdConciliacion(1L);
	}


}