/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.conector.impl.MovimientosNocturnosBrokerBus;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMovimientosNocturnosRestService;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosNocturnosResponseDTO;
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
 * @name MovimientosNocturnosBrokerTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el conector MovimientosNocturnosBrokerBus
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class MovimientosNocturnosBrokerTest {
	
	@InjectMocks
	private MovimientosNocturnosBroker movimientosNocturnosBrokerBus = new MovimientosNocturnosBrokerBus();

	@Mock
	private BusMovimientosNocturnosRestService busMovimientosNocturnosRestService;

	@Test
	public void t1_cargarMovimientosNocturnos(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("codigo", "0");
		response.put("descripcion", "Test");
		when(busMovimientosNocturnosRestService.cargarMovimientosNocturnos(any())).thenReturn(response);
		MovimientosNocturnosResponseDTO resultado = movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(1L, new Date(), new Date(), "OPENPAY", 2L );
		verify(busMovimientosNocturnosRestService, times(1)).cargarMovimientosNocturnos(any());
		assertNotNull(resultado);
	}

	@Test
	public void t2_cargarMovimientosNocturnos(){
		when(busMovimientosNocturnosRestService.cargarMovimientosNocturnos(any())).thenReturn(null);
		MovimientosNocturnosResponseDTO resultado = movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(1L, new Date(), new Date(), "OPENPAY", 2L );
		verify(busMovimientosNocturnosRestService, times(1)).cargarMovimientosNocturnos(any());
		assertNotNull(resultado);
	}

	@Test
	public void t3_cargarMovimientosNocturnos(){
		Map<String, Object> response = new HashMap<String, Object>();
		when(busMovimientosNocturnosRestService.cargarMovimientosNocturnos(any())).thenReturn(null);
		MovimientosNocturnosResponseDTO resultado = movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(1L, new Date(), new Date(), "OPENPAY", 2L );
		verify(busMovimientosNocturnosRestService, times(1)).cargarMovimientosNocturnos(any());
		assertNotNull(resultado);
	}

	@Test
	public void t4_cargarMovimientosNocturnos(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("codigoError", "404");
		response.put("descripcionError", "Test");
		response.put("tipoError", "Test");
		when(busMovimientosNocturnosRestService.cargarMovimientosNocturnos(any())).thenReturn(response);
		MovimientosNocturnosResponseDTO resultado = movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(1L, new Date(), new Date(), "OPENPAY", 2L );
		verify(busMovimientosNocturnosRestService, times(1)).cargarMovimientosNocturnos(any());
		assertNotNull(resultado);
	}

	@Test
	public void t5_cargarMovimientosNocturnos(){
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busMovimientosNocturnosRestService).cargarMovimientosNocturnos(any());
		MovimientosNocturnosResponseDTO resultado = movimientosNocturnosBrokerBus.cargarMovimientosNocturnos(1L, new Date(), new Date(), "OPENPAY", 2L );
		verify(busMovimientosNocturnosRestService, times(1)).cargarMovimientosNocturnos(any());
		assertNotNull(resultado);
	}

}