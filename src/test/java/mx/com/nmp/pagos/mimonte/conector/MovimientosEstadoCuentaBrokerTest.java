/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.conector.impl.MovimientosEstadoCuentaBrokerBus;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMovimientosEstadoCuentaRestService;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosEstadoCuentaResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @name MovimientosEstadoCuentaBrokerTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el conector MovimientosEstadoCuentaBrokerBus
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class MovimientosEstadoCuentaBrokerTest {
	
	@InjectMocks
	private MovimientosEstadoCuentaBroker movimientosEstadoCuentaBrokerBus = new MovimientosEstadoCuentaBrokerBus();

	@Mock
	private BusMovimientosEstadoCuentaRestService busMovimientosEstadoCuentaRestService;

	@Test
	public void t1_cargarMovimientosEstadoCuenta(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", "0");
		response.put("message", "Test");
		when(busMovimientosEstadoCuentaRestService.cargarMovimientosEstadoCuenta(any())).thenReturn(response);
		MovimientosEstadoCuentaResponseDTO resultado = movimientosEstadoCuentaBrokerBus.cargarMovimientosEstadoCuenta(1L, new Date(), new Date());
		verify(busMovimientosEstadoCuentaRestService, times(1)).cargarMovimientosEstadoCuenta(any());
		assertNotNull(resultado);
	}

	@Test
	public void t2_cargarMovimientosEstadoCuenta(){
		when(busMovimientosEstadoCuentaRestService.cargarMovimientosEstadoCuenta(any())).thenReturn(null);
		MovimientosEstadoCuentaResponseDTO resultado = movimientosEstadoCuentaBrokerBus.cargarMovimientosEstadoCuenta(1L, new Date(), new Date());
		verify(busMovimientosEstadoCuentaRestService, times(1)).cargarMovimientosEstadoCuenta(any());
		assertNotNull(resultado);
	}

	@Test
	public void t3_cargarMovimientosEstadoCuenta(){
		Map<String, Object> response = new HashMap<String, Object>();
		when(busMovimientosEstadoCuentaRestService.cargarMovimientosEstadoCuenta(any())).thenReturn(null);
		MovimientosEstadoCuentaResponseDTO resultado = movimientosEstadoCuentaBrokerBus.cargarMovimientosEstadoCuenta(1L, new Date(), new Date());
		verify(busMovimientosEstadoCuentaRestService, times(1)).cargarMovimientosEstadoCuenta(any());
		assertNotNull(resultado);
	}

	@Test
	public void t4_cargarMovimientosEstadoCuenta(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("codigoError", "404");
		response.put("code", "0");
		response.put("message", "Test");
		when(busMovimientosEstadoCuentaRestService.cargarMovimientosEstadoCuenta(any())).thenReturn(response);
		MovimientosEstadoCuentaResponseDTO resultado = movimientosEstadoCuentaBrokerBus.cargarMovimientosEstadoCuenta(1L, new Date(), new Date());
		verify(busMovimientosEstadoCuentaRestService, times(1)).cargarMovimientosEstadoCuenta(any());
		assertNotNull(resultado);
	}

	@Test
	public void t5_cargarMovimientosEstadoCuenta(){
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busMovimientosEstadoCuentaRestService).cargarMovimientosEstadoCuenta(any());
		MovimientosEstadoCuentaResponseDTO resultado = movimientosEstadoCuentaBrokerBus.cargarMovimientosEstadoCuenta(1L, new Date(), new Date());
		verify(busMovimientosEstadoCuentaRestService, times(1)).cargarMovimientosEstadoCuenta(any());
		assertNotNull(resultado);
	}

}