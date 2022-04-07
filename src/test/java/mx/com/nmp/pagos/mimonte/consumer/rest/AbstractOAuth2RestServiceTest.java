/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest;

import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.constans.MailServiceConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * @name AbstractOAuth2RestServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio REST  AbstractOAuth2RestService
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationProperties.class, MailServiceConstants.class, BusProcesoPreconciliacionRestService.class,
		BusMovimientosProveedorRestService.class, BusMovimientosNocturnosRestService.class, BusMovimientosEstadoCuentaRestService.class,
		BusMergeConciliacionRestService.class, BusGestionConciliacionRestService.class, BusGeneracionLayoutRestService.class})
@EnableConfigurationProperties(value = ApplicationProperties.class )
@TestPropertySource( locations = "application-test.properties")
public class AbstractOAuth2RestServiceTest {

	@Autowired
	private BusProcesoPreconciliacionRestService busProcesoPreconciliacionRestService;

	@Autowired
	private BusMovimientosProveedorRestService busMovimientosProveedorRestService;

	@Autowired
	private BusMovimientosNocturnosRestService busMovimientosNocturnosRestService;

	@Autowired
	private BusMovimientosEstadoCuentaRestService busMovimientosEstadoCuentaRestService;

	@Autowired
	private BusMergeConciliacionRestService busMergeConciliacionRestService;

	@Autowired
	private BusGestionConciliacionRestService busGestionConciliacionRestService;

	@Autowired
	private BusGeneracionLayoutRestService busGeneracionLayoutRestService;

	@Autowired
	protected ApplicationProperties applicationProperties;

	@Test
	public void t1_createHeadersPostTo_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		HttpHeaders resultado = busProcesoPreconciliacionRestService.createHeadersPostTo(auth,header);
		assertNotNull(resultado);
	}

	@Test(expected = ResourceAccessException.class)
	public void t2_postForObjectHttpClient_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		BusRestPreconciliacionDTO body = new BusRestPreconciliacionDTO(new BusRestRangoFechasDTO(new Date(), new Date()), new BusRestCorresponsalDTO("OPENPAY"));
		String url = applicationProperties.getMimonte().getVariables().getProcesoPreconciliacion().getUrl();
		busProcesoPreconciliacionRestService.postForObjectHttpClient(auth,body,header,url);
	}


	@Test
	public void t3_createHeadersPostTo_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		HttpHeaders resultado = busMovimientosProveedorRestService.createHeadersPostTo(auth,header);
		assertNotNull(resultado);
	}

	@Test(expected = ResourceAccessException.class)
	public void t4_postForObjectHttpClient_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		BusRestMovimientosProveedorDTO body = new BusRestMovimientosProveedorDTO(1L, new Date(), new Date(), new BusRestCorresponsalDTO("OPENPAY"), 3L, "Bancomer");
		String url = applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getUrlMovimientosProveedor();
		busMovimientosProveedorRestService.postForObjectHttpClient(auth,body,header,url);
	}


	@Test
	public void t5_createHeadersPostTo_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		HttpHeaders resultado = busMovimientosNocturnosRestService.createHeadersPostTo(auth,header);
		assertNotNull(resultado);
	}

	@Test(expected = ResourceAccessException.class)
	public void t6_postForObjectHttpClient_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		BusRestMovimientosNocturnosDTO body = new BusRestMovimientosNocturnosDTO(1L, new Date(), new Date(), new BusRestCorresponsalDTO("OPENPAY"), 2L);
		String url = applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getUrlMovimientosNocturnos();
		busMovimientosNocturnosRestService.postForObjectHttpClient(auth,body,header,url);
	}


	@Test
	public void t7_createHeadersPostTo_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		HttpHeaders resultado = busMovimientosEstadoCuentaRestService.createHeadersPostTo(auth,header);
		assertNotNull(resultado);
	}

	@Test(expected = ResourceAccessException.class)
	public void t8_postForObjectHttpClient_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		BusRestMovimientosEstadoCuentaDTO body = new BusRestMovimientosEstadoCuentaDTO(1L, new Date(), new Date());
		String url = applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getUrlMovimientosEstadoCuenta();
		busMovimientosEstadoCuentaRestService.postForObjectHttpClient(auth,body,header,url);
	}


	@Test
	public void t9_createHeadersPostTo_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		HttpHeaders resultado = busMergeConciliacionRestService.createHeadersPostTo(auth,header);
		assertNotNull(resultado);
	}

	@Test(expected = ResourceAccessException.class)
	public void t10_postForObjectHttpClient_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		BusRestMergeConciliacionDTO body = new BusRestMergeConciliacionDTO(1L);
		String url = applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getUrlMergeConciliacion();
		busMergeConciliacionRestService.postForObjectHttpClient(auth,body,header,url);
	}


	@Test
	public void t11_createHeadersPostTo_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		HttpHeaders resultado = busGestionConciliacionRestService.createHeadersPostTo(auth,header);
		assertNotNull(resultado);

	}

	@Test(expected = ResourceAccessException.class)
	public void t12_postForObjectHttpClient_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		BusRestGestionConciliacionDTO body = new BusRestGestionConciliacionDTO(1L,11L, "OPEMPAY");
		String url = applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getUrlGestionConciliacion();
		busGestionConciliacionRestService.postForObjectHttpClient(auth,body,header,url);
	}

	@Test
	public void t13_createHeadersPostTo_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		HttpHeaders resultado = busGeneracionLayoutRestService.createHeadersPostTo(auth,header);
		assertNotNull(resultado);

	}

	@Test(expected = ResourceAccessException.class)
	public void t14_postForObjectHttpClient_Preconciliacion() {
		BusRestAuthDTO auth = new BusRestAuthDTO("user", "test");
		BusRestHeaderDTO header = new BusRestHeaderDTO("test");
		BusRestGeneracionLayoutDTO body = new BusRestGeneracionLayoutDTO(1L,2);
		String url = applicationProperties.getMimonte().getVariables().getProcesoConciliacion().getUrlGeneracionLayout();
		busGeneracionLayoutRestService.postForObjectHttpClient(auth,body,header,url);
	}


}