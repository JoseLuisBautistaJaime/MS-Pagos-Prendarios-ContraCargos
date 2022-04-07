/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestGeneracionLayoutDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestGestionConciliacionDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @name BusGeneracionLayoutRestServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio REST  BusGeneracionLayoutRestService
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class BusGeneracionLayoutRestServiceTest {

	@Mock
	private BusGeneracionLayoutRestService busGeneracionLayoutRestService;

	@Test
	public void t1_generarLayouts() {

		BusRestGeneracionLayoutDTO body = new BusRestGeneracionLayoutDTO(1L,2);
		LinkedHashMap<String, Object> responsePOST = new LinkedHashMap<>();
		responsePOST.put("codigo", "0");

		when(busGeneracionLayoutRestService.postForGetToken(any(), any())).thenReturn("Test");
		when(busGeneracionLayoutRestService.postForObjectHttpClient(any(), any(), any(), any())).thenReturn(responsePOST);
		when(busGeneracionLayoutRestService.generarLayouts(any())).thenCallRealMethod();

		Map<String, Object> resultado = busGeneracionLayoutRestService.generarLayouts(body);

		verify(busGeneracionLayoutRestService, times(1)).postForGetToken(any(), any());
		verify(busGeneracionLayoutRestService, times(1)).postForObjectHttpClient(any(), any(), any(), any());
		assertNotNull(resultado);

	}

	@Test(expected = HttpClientErrorException.class)
	public void t2_generarLayouts() {

		BusRestGeneracionLayoutDTO body = new BusRestGeneracionLayoutDTO(1L,2);

		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busGeneracionLayoutRestService).postForGetToken(any(), any());
		when(busGeneracionLayoutRestService.generarLayouts(any())).thenCallRealMethod();

		Map<String, Object> resultado = busGeneracionLayoutRestService.generarLayouts(body);

		verify(busGeneracionLayoutRestService, times(1)).postForGetToken(any(), any());
		assertNull(resultado);
	}

	@Test(expected = HttpClientErrorException.class)
	public void t3_generarLayouts() {

		BusRestGeneracionLayoutDTO body = new BusRestGeneracionLayoutDTO(1L,2);

		when(busGeneracionLayoutRestService.postForGetToken(any(), any())).thenReturn("Test");
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busGeneracionLayoutRestService).postForObjectHttpClient(any(), any(), any(), any());
		when(busGeneracionLayoutRestService.generarLayouts(any())).thenCallRealMethod();

		Map<String, Object> resultado = busGeneracionLayoutRestService.generarLayouts(body);

		verify(busGeneracionLayoutRestService, times(1)).postForGetToken(any(), any());
		verify(busGeneracionLayoutRestService, times(1)).postForObjectHttpClient(any(), any(), any(), any());
		assertNull(resultado);

	}

	@Test
	public void t4_createHeadersPostTo() {

		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		when(busGeneracionLayoutRestService.createHeadersPostTo(any(), any())).thenCallRealMethod();
		HttpHeaders resultado = busGeneracionLayoutRestService.createHeadersPostTo(auth,header);
		assertNotNull(resultado);

	}


}