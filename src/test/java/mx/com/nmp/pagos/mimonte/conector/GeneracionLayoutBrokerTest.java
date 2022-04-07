/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.conector.impl.GeneracionLayoutBrokerBus;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusGeneracionLayoutRestService;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.GeneracionLayoutResponseDTO;
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
 * @name GeneracionLayoutBrokerTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el conector GeneracionLayoutBrokerBus
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class GeneracionLayoutBrokerTest {


	@InjectMocks
	private GeneracionLayoutBroker generacionLayoutBrokerBus = new GeneracionLayoutBrokerBus();

	@Mock
	private BusGeneracionLayoutRestService busGeneracionLayoutRestService;

	@Test
	public void t1_generarLayouts(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("codigo", "0");
		response.put("descripcion", "Test");
		when(busGeneracionLayoutRestService.generarLayouts(any())).thenReturn(response);
		GeneracionLayoutResponseDTO resultado = generacionLayoutBrokerBus.generarLayouts(1L,2);
		verify(busGeneracionLayoutRestService, times(1)).generarLayouts(any());
		assertNotNull(resultado);
	}

	@Test
	public void t2_generarLayouts(){
		when(busGeneracionLayoutRestService.generarLayouts(any())).thenReturn(null);
		GeneracionLayoutResponseDTO resultado = generacionLayoutBrokerBus.generarLayouts(1L,2);
		verify(busGeneracionLayoutRestService, times(1)).generarLayouts(any());
		assertNotNull(resultado);
	}

	@Test
	public void t3_generarLayouts(){
		Map<String, Object> response = new HashMap<String, Object>();
		when(busGeneracionLayoutRestService.generarLayouts(any())).thenReturn(null);
		GeneracionLayoutResponseDTO resultado = generacionLayoutBrokerBus.generarLayouts(1L,2);
		verify(busGeneracionLayoutRestService, times(1)).generarLayouts(any());
		assertNotNull(resultado);
	}

	@Test
	public void t4_generarLayouts(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("codigoError", "404");
		response.put("descripcionError", "Test");
		response.put("tipoError", "Test");
		when(busGeneracionLayoutRestService.generarLayouts(any())).thenReturn(response);
		GeneracionLayoutResponseDTO resultado = generacionLayoutBrokerBus.generarLayouts(1L,2);
		verify(busGeneracionLayoutRestService, times(1)).generarLayouts(any());
		assertNotNull(resultado);
	}

	@Test
	public void t5_generarLayouts(){
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busGeneracionLayoutRestService).generarLayouts(any());
		GeneracionLayoutResponseDTO resultado = generacionLayoutBrokerBus.generarLayouts(1L,2);
		verify(busGeneracionLayoutRestService, times(1)).generarLayouts(any());
		assertNotNull(resultado);
	}

}