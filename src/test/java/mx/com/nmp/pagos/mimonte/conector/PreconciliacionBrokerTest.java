/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.conector.impl.PreconciliacionBrokerBus;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusProcesoPreconciliacionRestService;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ProcesoPreconciliacionResponseDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @name PreconciliacionBrokerTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el conector PreconciliacionBrokerBus
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class PreconciliacionBrokerTest {


	@InjectMocks
	private PreconciliacionBroker preconciliacionBroker = new PreconciliacionBrokerBus();

	@Mock
	private BusProcesoPreconciliacionRestService busProcesoPreconciliacionRestService;

	private EjecucionPreconciliacion ejecucionPreconciliacion;


	@Test
	public void t1_ejecutarPreconciliacion(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("codigo", "0");
		response.put("descripcion", "Test");
		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		when(busProcesoPreconciliacionRestService.ejecutarProcesoPreconciliacion(any())).thenReturn(response);
		ProcesoPreconciliacionResponseDTO resultado = preconciliacionBroker.ejecutarPreconciliacion(ejecucionPreconciliacion.getFechaPeriodoInicio(),ejecucionPreconciliacion.getFechaPeriodoFin(),ejecucionPreconciliacion.getProveedor().getNombre().getNombre());
		verify(busProcesoPreconciliacionRestService, times(1)).ejecutarProcesoPreconciliacion(any());
		assertNotNull(resultado);
	}

	@Test
	public void t2_ejecutarPreconciliacion(){
		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		when(busProcesoPreconciliacionRestService.ejecutarProcesoPreconciliacion(any())).thenReturn(null);
		ProcesoPreconciliacionResponseDTO resultado = preconciliacionBroker.ejecutarPreconciliacion(ejecucionPreconciliacion.getFechaPeriodoInicio(),ejecucionPreconciliacion.getFechaPeriodoFin(),ejecucionPreconciliacion.getProveedor().getNombre().getNombre());
		verify(busProcesoPreconciliacionRestService, times(1)).ejecutarProcesoPreconciliacion(any());
		assertNotNull(resultado);
	}

	@Test
	public void t3_ejecutarPreconciliacion(){
		Map<String, Object> response = new HashMap<String, Object>();
		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		when(busProcesoPreconciliacionRestService.ejecutarProcesoPreconciliacion(any())).thenReturn(null);
		ProcesoPreconciliacionResponseDTO resultado = preconciliacionBroker.ejecutarPreconciliacion(ejecucionPreconciliacion.getFechaPeriodoInicio(),ejecucionPreconciliacion.getFechaPeriodoFin(),ejecucionPreconciliacion.getProveedor().getNombre().getNombre());
		verify(busProcesoPreconciliacionRestService, times(1)).ejecutarProcesoPreconciliacion(any());
		assertNotNull(resultado);
	}

	@Test
	public void t4_ejecutarPreconciliacion(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("codigoError", "404");
		response.put("descripcionError", "Test");
		response.put("tipoError", "Test");
		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		when(busProcesoPreconciliacionRestService.ejecutarProcesoPreconciliacion(any())).thenReturn(response);
		ProcesoPreconciliacionResponseDTO resultado = preconciliacionBroker.ejecutarPreconciliacion(ejecucionPreconciliacion.getFechaPeriodoInicio(),ejecucionPreconciliacion.getFechaPeriodoFin(),ejecucionPreconciliacion.getProveedor().getNombre().getNombre());
		verify(busProcesoPreconciliacionRestService, times(1)).ejecutarProcesoPreconciliacion(any());
		assertNotNull(resultado);
	}

	@Test
	public void t5_ejecutarPreconciliacion(){
		ejecucionPreconciliacion =this.crearEjecucionPreconciliacion();
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busProcesoPreconciliacionRestService).ejecutarProcesoPreconciliacion(any());
		ProcesoPreconciliacionResponseDTO resultado = preconciliacionBroker.ejecutarPreconciliacion(ejecucionPreconciliacion.getFechaPeriodoInicio(),ejecucionPreconciliacion.getFechaPeriodoFin(),ejecucionPreconciliacion.getProveedor().getNombre().getNombre());
		verify(busProcesoPreconciliacionRestService, times(1)).ejecutarProcesoPreconciliacion(any());
		assertNotNull(resultado);
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

}