/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import mx.com.nmp.pagos.mimonte.conector.PreconciliacionBroker;
import mx.com.nmp.pagos.mimonte.conector.impl.PreconciliacionBrokerBus;
import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.constans.MailServiceConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.*;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ProcesoPreconciliacionResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import org.h2.util.New;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.*;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @name BusProcesoPreconciliacionRestServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio REST  BusProcesoPreconciliacionRestService
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationProperties.class, MailServiceConstants.class})
@EnableConfigurationProperties(value = ApplicationProperties.class )
@TestPropertySource( locations = "application-test.properties")
public class BusProcesoPreconciliacionRestServiceTest {

	@Mock
	private BusProcesoPreconciliacionRestService busProcesoPreconciliacionRestService;

	@Test
	public void t1_ejecutarProcesoPreconciliacion() {

		BusRestPreconciliacionDTO body = new BusRestPreconciliacionDTO(new BusRestRangoFechasDTO(new Date(), new Date()), new BusRestCorresponsalDTO("OPENPAY"));
		LinkedHashMap<String, Object> responsePOST = new LinkedHashMap<>();
		responsePOST.put("codigo", "0");

		when(busProcesoPreconciliacionRestService.postForGetToken(any(), any())).thenReturn("Test");
		when(busProcesoPreconciliacionRestService.postForObjectHttpClient(any(), any(), any(), any())).thenReturn(responsePOST);
		when(busProcesoPreconciliacionRestService.ejecutarProcesoPreconciliacion(any())).thenCallRealMethod();

		Map<String, Object> resultado = busProcesoPreconciliacionRestService.ejecutarProcesoPreconciliacion(body);

		verify(busProcesoPreconciliacionRestService, times(1)).postForGetToken(any(), any());
		verify(busProcesoPreconciliacionRestService, times(1)).postForObjectHttpClient(any(), any(), any(), any());
		assertNotNull(resultado);

	}

	@Test(expected = HttpClientErrorException.class)
	public void t2_ejecutarProcesoPreconciliacion() {

		BusRestPreconciliacionDTO body = new BusRestPreconciliacionDTO(new BusRestRangoFechasDTO(new Date(), new Date()), new BusRestCorresponsalDTO("OPENPAY"));

		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busProcesoPreconciliacionRestService).postForGetToken(any(), any());
		when(busProcesoPreconciliacionRestService.ejecutarProcesoPreconciliacion(any())).thenCallRealMethod();

		Map<String, Object> resultado = busProcesoPreconciliacionRestService.ejecutarProcesoPreconciliacion(body);

		verify(busProcesoPreconciliacionRestService, times(1)).postForGetToken(any(), any());
		assertNull(resultado);
	}

	@Test(expected = HttpClientErrorException.class)
	public void t3_ejecutarProcesoPreconciliacion() {

		BusRestPreconciliacionDTO body = new BusRestPreconciliacionDTO(new BusRestRangoFechasDTO(new Date(), new Date()), new BusRestCorresponsalDTO("OPENPAY"));

		when(busProcesoPreconciliacionRestService.postForGetToken(any(), any())).thenReturn("Test");
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busProcesoPreconciliacionRestService).postForObjectHttpClient(any(), any(), any(), any());
		when(busProcesoPreconciliacionRestService.ejecutarProcesoPreconciliacion(any())).thenCallRealMethod();

		Map<String, Object> resultado = busProcesoPreconciliacionRestService.ejecutarProcesoPreconciliacion(body);

		verify(busProcesoPreconciliacionRestService, times(1)).postForGetToken(any(), any());
		verify(busProcesoPreconciliacionRestService, times(1)).postForObjectHttpClient(any(), any(), any(), any());
		assertNull(resultado);

	}

	@Test
	public void t4_createHeadersPostTo() {

		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		when(busProcesoPreconciliacionRestService.createHeadersPostTo(any(), any())).thenCallRealMethod();
		HttpHeaders resultado = busProcesoPreconciliacionRestService.createHeadersPostTo(auth,header);
		assertNotNull(resultado);

	}

}