/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMergeConciliacionDTO;
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
 * @name BusMergeConciliacionRestServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio REST  BusMergeConciliacionRestService
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class BusMergeConciliacionRestServiceTest {

	@Mock
	private BusMergeConciliacionRestService busMergeConciliacionRestService;

	@Test
	public void t1_generarMergeConciliacion() {

		BusRestMergeConciliacionDTO body = new BusRestMergeConciliacionDTO(1L);
		LinkedHashMap<String, Object> responsePOST = new LinkedHashMap<>();
		responsePOST.put("codigo", "0");

		when(busMergeConciliacionRestService.postForGetToken(any(), any())).thenReturn("Test");
		when(busMergeConciliacionRestService.postForObjectHttpClient(any(), any(), any(), any())).thenReturn(responsePOST);
		when(busMergeConciliacionRestService.generarMergeConciliacion(any())).thenCallRealMethod();

		Map<String, Object> resultado = busMergeConciliacionRestService.generarMergeConciliacion(body);

		verify(busMergeConciliacionRestService, times(1)).postForGetToken(any(), any());
		verify(busMergeConciliacionRestService, times(1)).postForObjectHttpClient(any(), any(), any(), any());
		assertNotNull(resultado);

	}

	@Test(expected = HttpClientErrorException.class)
	public void t2_generarMergeConciliacion() {

		BusRestMergeConciliacionDTO body = new BusRestMergeConciliacionDTO(1L);

		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busMergeConciliacionRestService).postForGetToken(any(), any());
		when(busMergeConciliacionRestService.generarMergeConciliacion(any())).thenCallRealMethod();

		Map<String, Object> resultado = busMergeConciliacionRestService.generarMergeConciliacion(body);

		verify(busMergeConciliacionRestService, times(1)).postForGetToken(any(), any());
		assertNull(resultado);
	}

	@Test(expected = HttpClientErrorException.class)
	public void t3_generarMergeConciliacion() {

		BusRestMergeConciliacionDTO body = new BusRestMergeConciliacionDTO(1L);

		when(busMergeConciliacionRestService.postForGetToken(any(), any())).thenReturn("Test");
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busMergeConciliacionRestService).postForObjectHttpClient(any(), any(), any(), any());
		when(busMergeConciliacionRestService.generarMergeConciliacion(any())).thenCallRealMethod();

		Map<String, Object> resultado = busMergeConciliacionRestService.generarMergeConciliacion(body);

		verify(busMergeConciliacionRestService, times(1)).postForGetToken(any(), any());
		verify(busMergeConciliacionRestService, times(1)).postForObjectHttpClient(any(), any(), any(), any());
		assertNull(resultado);

	}

	@Test
	public void t4_createHeadersPostTo() {

		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		when(busMergeConciliacionRestService.createHeadersPostTo(any(), any())).thenCallRealMethod();
		HttpHeaders resultado = busMergeConciliacionRestService.createHeadersPostTo(auth,header);
		assertNotNull(resultado);

	}


}