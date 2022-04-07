/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.constans.MailServiceConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAuthDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestCorresponsalDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestHeaderDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMovimientosProveedorDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
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
 * @name BusMovimientosProveedorRestServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio REST  BusMovimientosProveedorRestService
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class BusMovimientosProveedorRestServiceTest {

	@Mock
	private BusMovimientosProveedorRestService busMovimientosProveedorRestService;

	@Test
	public void t1_cargarMovimientosProveedor() {

		BusRestMovimientosProveedorDTO body = new BusRestMovimientosProveedorDTO(1L, new Date(), new Date(), new BusRestCorresponsalDTO("OPENPAY"), 3L, "Bancomer");
		LinkedHashMap<String, Object> responsePOST = new LinkedHashMap<>();
		responsePOST.put("codigo", "0");

		when(busMovimientosProveedorRestService.postForGetToken(any(), any())).thenReturn("Test");
		when(busMovimientosProveedorRestService.postForObjectHttpClient(any(), any(), any(), any())).thenReturn(responsePOST);
		when(busMovimientosProveedorRestService.cargarMovimientosProveedor(any())).thenCallRealMethod();

		Map<String, Object> resultado = busMovimientosProveedorRestService.cargarMovimientosProveedor(body);

		verify(busMovimientosProveedorRestService, times(1)).postForGetToken(any(), any());
		verify(busMovimientosProveedorRestService, times(1)).postForObjectHttpClient(any(), any(), any(), any());
		assertNotNull(resultado);

	}

	@Test(expected = HttpClientErrorException.class)
	public void t2_cargarMovimientosProveedor() {

		BusRestMovimientosProveedorDTO body = new BusRestMovimientosProveedorDTO(1L, new Date(), new Date(), new BusRestCorresponsalDTO("OPENPAY"), 3L, "Bancomer");

		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busMovimientosProveedorRestService).postForGetToken(any(), any());
		when(busMovimientosProveedorRestService.cargarMovimientosProveedor(any())).thenCallRealMethod();

		Map<String, Object> resultado = busMovimientosProveedorRestService.cargarMovimientosProveedor(body);

		verify(busMovimientosProveedorRestService, times(1)).postForGetToken(any(), any());
		assertNull(resultado);
	}

	@Test(expected = HttpClientErrorException.class)
	public void t3_cargarMovimientosProveedor() {

		BusRestMovimientosProveedorDTO body = new BusRestMovimientosProveedorDTO(1L, new Date(), new Date(), new BusRestCorresponsalDTO("OPENPAY"), 3L, "Bancomer");

		when(busMovimientosProveedorRestService.postForGetToken(any(), any())).thenReturn("Test");
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busMovimientosProveedorRestService).postForObjectHttpClient(any(), any(), any(), any());
		when(busMovimientosProveedorRestService.cargarMovimientosProveedor(any())).thenCallRealMethod();

		Map<String, Object> resultado = busMovimientosProveedorRestService.cargarMovimientosProveedor(body);

		verify(busMovimientosProveedorRestService, times(1)).postForGetToken(any(), any());
		verify(busMovimientosProveedorRestService, times(1)).postForObjectHttpClient(any(), any(), any(), any());
		assertNull(resultado);

	}

	@Test
	public void t4_createHeadersPostTo() {

		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		when(busMovimientosProveedorRestService.createHeadersPostTo(any(), any())).thenCallRealMethod();
		HttpHeaders resultado = busMovimientosProveedorRestService.createHeadersPostTo(auth,header);
		assertNotNull(resultado);

	}


}