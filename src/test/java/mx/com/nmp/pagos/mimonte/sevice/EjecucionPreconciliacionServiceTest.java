/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.sevice;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.EjecucionPreconciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusEjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroEjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.EjecucionPreconciliacionService;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.EjecucionPreconciliacionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.junit.Before;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.List;

/**
 * @name EjecucionPreconciliacionServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio EjecucionPreconciliacionService
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 12:55 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class  EjecucionPreconciliacionServiceTest {

	@InjectMocks
	private EjecucionPreconciliacionService ejecucionPreconciliacionService= new EjecucionPreconciliacionServiceImpl();


	@Mock
	private EjecucionPreconciliacionRepository ejecucionPreconciliacionRepository;

	private EjecucionPreconciliacion ejecucionPreconciliacion;

	private FiltroEjecucionPreconciliacionDTO filtroEjecucionPreconciliacionDTO;

	private EjecucionPreconciliacionDTO ejecucionPreconciliacionDTO;

	@Before
	public void setUp(){

		ejecucionPreconciliacion =this.crearElemento();
		filtroEjecucionPreconciliacionDTO = this.crearFiltro(ejecucionPreconciliacion);
		ejecucionPreconciliacionDTO = this.crearElementoDTO(ejecucionPreconciliacion);

	}

	private EjecucionPreconciliacion crearElemento() {
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

	private EjecucionPreconciliacionDTO crearElementoDTO(EjecucionPreconciliacion elemento) {
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

	private FiltroEjecucionPreconciliacionDTO crearFiltro(EjecucionPreconciliacion elemento) {
		FiltroEjecucionPreconciliacionDTO filtro = new FiltroEjecucionPreconciliacionDTO();
		filtro.setFechaPeriodoInicio(elemento.getFechaPeriodoInicio());
		filtro.setFechaPeriodoFin(elemento.getFechaPeriodoFin());
		filtro.setCorresponsal(elemento.getProveedor().getNombre().getNombre());
		filtro.setIdEstatus(elemento.getEstatus().getId());
		filtro.setFechaEjecucionDesde(elemento.getFechaEjecucion());
		filtro.setFechaEjecucionHasta(elemento.getFechaEjecucion());
		filtro.setEstatusDescripcion(elemento.getEstatusDescripcion());
		return filtro;
	}


	@Test
	public void  t1_consultarByPropiedades(){
		filtroEjecucionPreconciliacionDTO = this.crearFiltro(ejecucionPreconciliacion);
		ejecucionPreconciliacionDTO = this.crearElementoDTO(ejecucionPreconciliacion);
		List<EjecucionPreconciliacionDTO> lista = new ArrayList<EjecucionPreconciliacionDTO>(){{add(ejecucionPreconciliacionDTO);}};
		when(ejecucionPreconciliacionRepository.findByPropiedades(anyInt(), anyString(), any(), any(), any(), any(), any())).thenReturn(lista);
		List<EjecucionPreconciliacionDTO> listaResultados = ejecucionPreconciliacionService.consultarByPropiedades(filtroEjecucionPreconciliacionDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void  t2_consultarByPropiedades(){
		filtroEjecucionPreconciliacionDTO = this.crearFiltro(ejecucionPreconciliacion);
		ejecucionPreconciliacionDTO = this.crearElementoDTO(ejecucionPreconciliacion);
		filtroEjecucionPreconciliacionDTO.setFechaEjecucionDesde(null);
		List<EjecucionPreconciliacionDTO> lista = new ArrayList<EjecucionPreconciliacionDTO>(){{add(ejecucionPreconciliacionDTO);}};
		when(ejecucionPreconciliacionRepository.findByPropiedades(anyInt(), anyString(), any(), any(), any(), any(), any())).thenReturn(lista);
		List<EjecucionPreconciliacionDTO> listaResultados = ejecucionPreconciliacionService.consultarByPropiedades(filtroEjecucionPreconciliacionDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void  t3_consultarByPropiedades(){
		filtroEjecucionPreconciliacionDTO = this.crearFiltro(ejecucionPreconciliacion);
		ejecucionPreconciliacionDTO = this.crearElementoDTO(ejecucionPreconciliacion);
		filtroEjecucionPreconciliacionDTO.setFechaEjecucionHasta(null);
		List<EjecucionPreconciliacionDTO> lista = new ArrayList<EjecucionPreconciliacionDTO>(){{add(ejecucionPreconciliacionDTO);}};
		when(ejecucionPreconciliacionRepository.findByPropiedades(anyInt(), anyString(), any(), any(), any(), any(), any())).thenReturn(lista);
		List<EjecucionPreconciliacionDTO> listaResultados = ejecucionPreconciliacionService.consultarByPropiedades(filtroEjecucionPreconciliacionDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void  t4_consultarByPropiedades(){
		filtroEjecucionPreconciliacionDTO = this.crearFiltro(ejecucionPreconciliacion);
		ejecucionPreconciliacionDTO = this.crearElementoDTO(ejecucionPreconciliacion);
		filtroEjecucionPreconciliacionDTO.setCorresponsal(null);
		List<EjecucionPreconciliacionDTO> lista = new ArrayList<EjecucionPreconciliacionDTO>(){{add(ejecucionPreconciliacionDTO);}};
		when(ejecucionPreconciliacionRepository.findByPropiedades(anyInt(), anyString(), any(), any(), any(), any(), any())).thenReturn(lista);
		List<EjecucionPreconciliacionDTO> listaResultados = ejecucionPreconciliacionService.consultarByPropiedades(filtroEjecucionPreconciliacionDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void t5_save(){
		when(ejecucionPreconciliacionRepository.save(any())).thenReturn(ejecucionPreconciliacion);
		EjecucionPreconciliacion respuesta = ejecucionPreconciliacionService.save(ejecucionPreconciliacion,"Test");
		assertNotNull(respuesta);
	}

	@Test(expected = ConciliacionException.class)
	public void t6_save(){
		when(ejecucionPreconciliacionRepository.save(any())).thenReturn(Exception.class);
		ejecucionPreconciliacionService.save(ejecucionPreconciliacion,"Test");
	}


}