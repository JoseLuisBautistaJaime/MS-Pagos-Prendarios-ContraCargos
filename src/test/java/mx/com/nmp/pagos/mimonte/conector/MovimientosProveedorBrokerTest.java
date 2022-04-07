/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.conector.impl.MovimientosProveedorBrokerBus;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMovimientosProveedorRestService;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientosProveedorResponseDTO;
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
 * @name MovimientosProveedorBrokerTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el conector MovimientosProveedorBrokerBus
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class MovimientosProveedorBrokerTest {
	
	@InjectMocks
	private MovimientosProveedorBroker movimientosProveedorBrokerBus = new MovimientosProveedorBrokerBus();

	@Mock
	private BusMovimientosProveedorRestService busMovimientosProveedorRestService;

	@Test
	public void t1_cargarMovimientosProveedor(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("codigo", "0");
		response.put("descripcion", "Test");
		when(busMovimientosProveedorRestService.cargarMovimientosProveedor(any())).thenReturn(response);
		MovimientosProveedorResponseDTO resultado = movimientosProveedorBrokerBus.cargarMovimientosProveedor(1L, new Date(), new Date(), "OPENPAY", 3L, "Bancomer");
		verify(busMovimientosProveedorRestService, times(1)).cargarMovimientosProveedor(any());
		assertNotNull(resultado);
	}

	@Test
	public void t2_cargarMovimientosProveedor(){
		when(busMovimientosProveedorRestService.cargarMovimientosProveedor(any())).thenReturn(null);
		MovimientosProveedorResponseDTO resultado = movimientosProveedorBrokerBus.cargarMovimientosProveedor(1L, new Date(), new Date(), "OPENPAY", 3L, "Bancomer");
		verify(busMovimientosProveedorRestService, times(1)).cargarMovimientosProveedor(any());
		assertNotNull(resultado);
	}

	@Test
	public void t3_cargarMovimientosProveedor(){
		Map<String, Object> response = new HashMap<String, Object>();
		when(busMovimientosProveedorRestService.cargarMovimientosProveedor(any())).thenReturn(null);
		MovimientosProveedorResponseDTO resultado = movimientosProveedorBrokerBus.cargarMovimientosProveedor(1L, new Date(), new Date(), "OPENPAY", 3L, "Bancomer");
		verify(busMovimientosProveedorRestService, times(1)).cargarMovimientosProveedor(any());
		assertNotNull(resultado);
	}

	@Test
	public void t4_cargarMovimientosProveedor(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("codigoError", "404");
		response.put("descripcionError", "Test");
		response.put("tipoError", "Test");
		when(busMovimientosProveedorRestService.cargarMovimientosProveedor(any())).thenReturn(response);
		MovimientosProveedorResponseDTO resultado = movimientosProveedorBrokerBus.cargarMovimientosProveedor(1L, new Date(), new Date(), "OPENPAY", 3L, "Bancomer");
		verify(busMovimientosProveedorRestService, times(1)).cargarMovimientosProveedor(any());
		assertNotNull(resultado);
	}

	@Test
	public void t5_cargarMovimientosProveedor(){
		doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Test")).when(busMovimientosProveedorRestService).cargarMovimientosProveedor(any());
		MovimientosProveedorResponseDTO resultado = movimientosProveedorBrokerBus.cargarMovimientosProveedor(1L, new Date(), new Date(), "OPENPAY", 3L, "Bancomer");
		verify(busMovimientosProveedorRestService, times(1)).cargarMovimientosProveedor(any());
		assertNotNull(resultado);
	}

}