/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import mx.com.nmp.pagos.mimonte.consumer.rest.dto.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @name BusMovimientosNocturnosRestServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio REST  BusMovimientosNocturnosRestService
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class BusMovimientosNocturnosRestServiceTest {

	@Mock
	private BusMovimientosNocturnosRestService busMovimientosNocturnosRestService;

	@Test
	public void t1_cargarMovimientosNocturnos() {

		BusRestMovimientosNocturnosDTO body = new BusRestMovimientosNocturnosDTO(1L, new Date(), new Date(), new BusRestCorresponsalDTO("OPENPAY"), 2L);
		LinkedHashMap<String, Object> responsePOST = new LinkedHashMap<>();
		responsePOST.put("codigo", "0");

		when(busMovimientosNocturnosRestService.postForGetToken(any(), any())).thenReturn("Test");
		when(busMovimientosNocturnosRestService.postForObjectHttpClient(any(), any(), any(), any())).thenReturn(responsePOST);
		when(busMovimientosNocturnosRestService.cargarMovimientosNocturnos(any())).thenCallRealMethod();

		Map<String, Object> resultado = busMovimientosNocturnosRestService.cargarMovimientosNocturnos(body);

		verify(busMovimientosNocturnosRestService, times(1)).postForGetToken(any(), any());
		verify(busMovimientosNocturnosRestService, times(1)).postForObjectHttpClient(any(), any(), any(), any());
		assertNotNull(resultado);

	}

	@Test(expected = HttpClientErrorException.class)
	public void t2_cargarMovimientosNocturnos() {

		BusRestMovimientosNocturnosDTO body = new BusRestMovimientosNocturnosDTO(1L, new Date(), new Date(), new BusRestCorresponsalDTO("OPENPAY"), 2L);

		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busMovimientosNocturnosRestService).postForGetToken(any(), any());
		when(busMovimientosNocturnosRestService.cargarMovimientosNocturnos(any())).thenCallRealMethod();

		Map<String, Object> resultado = busMovimientosNocturnosRestService.cargarMovimientosNocturnos(body);

		verify(busMovimientosNocturnosRestService, times(1)).postForGetToken(any(), any());
		assertNull(resultado);
	}

	@Test(expected = HttpClientErrorException.class)
	public void t3_cargarMovimientosNocturnos() {

		BusRestMovimientosNocturnosDTO body = new BusRestMovimientosNocturnosDTO(1L, new Date(), new Date(), new BusRestCorresponsalDTO("OPENPAY"), 2L);

		when(busMovimientosNocturnosRestService.postForGetToken(any(), any())).thenReturn("Test");
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busMovimientosNocturnosRestService).postForObjectHttpClient(any(), any(), any(), any());
		when(busMovimientosNocturnosRestService.cargarMovimientosNocturnos(any())).thenCallRealMethod();

		Map<String, Object> resultado = busMovimientosNocturnosRestService.cargarMovimientosNocturnos(body);

		verify(busMovimientosNocturnosRestService, times(1)).postForGetToken(any(), any());
		verify(busMovimientosNocturnosRestService, times(1)).postForObjectHttpClient(any(), any(), any(), any());
		assertNull(resultado);

	}

	@Test
	public void t4_createHeadersPostTo() {

		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		when(busMovimientosNocturnosRestService.createHeadersPostTo(any(), any())).thenCallRealMethod();
		HttpHeaders resultado = busMovimientosNocturnosRestService.createHeadersPostTo(auth,header);
		assertNotNull(resultado);

	}

}