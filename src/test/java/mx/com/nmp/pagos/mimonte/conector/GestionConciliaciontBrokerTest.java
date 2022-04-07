/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;


import mx.com.nmp.pagos.mimonte.conector.impl.GestionConciliacionBrokerBus;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusGestionConciliacionRestService;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.GestionConciliacionResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @name GestionConciliaciontBrokerTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el conector GestionConciliaciontBrokerBus
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class GestionConciliaciontBrokerTest {


	@InjectMocks
	private GestionConciliacionBroker gestionConciliacionBrokerBus = new GestionConciliacionBrokerBus();

	@Mock
	private BusGestionConciliacionRestService busGestionConciliacionRestService;

	@Test
	public void t1_crearProcesoConciliacion(){
		LinkedHashMap<String, Object> conciliacion = new LinkedHashMap<>();
		conciliacion.put("folio",1);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("codigo", "0");
		response.put("descripcion", "Test");
		response.put("conciliacion", conciliacion);
		when(busGestionConciliacionRestService.crearProcesoConciliacion(any())).thenReturn(response);
		GestionConciliacionResponseDTO resultado = gestionConciliacionBrokerBus.crearConciliacion(1L,1L,"OPENPAY");
		verify(busGestionConciliacionRestService, times(1)).crearProcesoConciliacion(any());
		assertNotNull(resultado);
	}

	@Test
	public void t2_crearProcesoConciliacion(){
		when(busGestionConciliacionRestService.crearProcesoConciliacion(any())).thenReturn(null);
		GestionConciliacionResponseDTO resultado = gestionConciliacionBrokerBus.crearConciliacion(1L,1L,"OPENPAY");
		verify(busGestionConciliacionRestService, times(1)).crearProcesoConciliacion(any());
		assertNotNull(resultado);
	}

	@Test
	public void t3_crearProcesoConciliacion(){
		Map<String, Object> response = new HashMap<String, Object>();
		when(busGestionConciliacionRestService.crearProcesoConciliacion(any())).thenReturn(null);
		GestionConciliacionResponseDTO resultado = gestionConciliacionBrokerBus.crearConciliacion(1L,1L,"OPENPAY");
		verify(busGestionConciliacionRestService, times(1)).crearProcesoConciliacion(any());
		assertNotNull(resultado);
	}

	@Test
	public void t4_crearProcesoConciliacion(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("codigoError", "404");
		response.put("descripcionError", "Test");
		response.put("tipoError", "Test");
		when(busGestionConciliacionRestService.crearProcesoConciliacion(any())).thenReturn(response);
		GestionConciliacionResponseDTO resultado = gestionConciliacionBrokerBus.crearConciliacion(1L,1L,"OPENPAY");
		verify(busGestionConciliacionRestService, times(1)).crearProcesoConciliacion(any());
		assertNotNull(resultado);
	}

	@Test
	public void t5_crearProcesoConciliacion(){
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busGestionConciliacionRestService).crearProcesoConciliacion(any());
		GestionConciliacionResponseDTO resultado = gestionConciliacionBrokerBus.crearConciliacion(1L,1L,"OPENPAY");
		verify(busGestionConciliacionRestService, times(1)).crearProcesoConciliacion(any());
		assertNotNull(resultado);
	}

}