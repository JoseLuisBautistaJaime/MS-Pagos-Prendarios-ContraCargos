/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoRequestDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusEjecucionConciliacion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @name ModelTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a las entidades o modelos
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */

@RunWith(SpringRunner.class)
public class ModelTest {

	private CatalogoDiaInhabil diaInhabil;
	private EstatusEjecucionConciliacion estatusEjecucionConciliacion;
	private EstatusEjecucionPreconciliacion estatusEjecucionPreconciliacion;
	private EjecucionConciliacion ejecucionConciliacion;
	private EjecucionPreconciliacion ejecucionPreconciliacion;
	private TrazadoEjecucionConciliacion trazadoEjecucionConciliacion;
	private CatalogoProceso catalogoProceso;
	private CalendarioEjecucionProceso calendarioEjecucionProceso;

	@Before
	public void setUp() {
		diaInhabil = crearDiaInhabil();
		estatusEjecucionConciliacion = crearEstatusEjecucionConciliacion();
		estatusEjecucionPreconciliacion = crearEstatusEjecucionPreconciliacion();
		ejecucionConciliacion = crearEjecucionConciliacion(this.crearConciliacion());
		ejecucionPreconciliacion = crearEjecucionPreconciliacion();
		trazadoEjecucionConciliacion =crearTrazadoEjecucion(ejecucionConciliacion);
		catalogoProceso = crearCatalogoProceso();
		calendarioEjecucionProceso = crearCalendarioEjecucionProceso();
	}

	@Test
	public void t1_modelTest()  {

		Conciliacion conciliacion = crearConciliacion();

		CatalogoDiaInhabil diaInhabil2 = new CatalogoDiaInhabil(diaInhabil.getId(), diaInhabil.getDescripcionCorta(), diaInhabil.descripcion, diaInhabil.getFecha());
		assertNotNull(diaInhabil2);
		assertNotNull(diaInhabil2.toString());
		CatalogoDiaInhabil diaInhabil3 = new CatalogoDiaInhabil(diaInhabil.getId());
		assertNotNull(diaInhabil3);
		CatalogoDiaInhabil diaInhabil4 = new CatalogoDiaInhabil( diaInhabil.getId(), diaInhabil.getDescripcionCorta(), diaInhabil.getDescripcion());
		assertNotNull(diaInhabil4);
		assertTrue(diaInhabil.equals(diaInhabil));
		assertFalse(diaInhabil.equals(diaInhabil3));
		assertFalse(diaInhabil.equals(conciliacion));

		EstatusEjecucionConciliacion estatusEjecucionConciliacion2 = new EstatusEjecucionConciliacion(estatusEjecucionConciliacion.getId(), estatusEjecucionConciliacion.getDescripcionCorta(), estatusEjecucionConciliacion.getDescripcion());
		assertNotNull(estatusEjecucionConciliacion2);
		assertNotNull(estatusEjecucionConciliacion2.toString());
		EstatusEjecucionConciliacion estatusEjecucionConciliacion3 = new EstatusEjecucionConciliacion(estatusEjecucionConciliacion.getId());
		assertNotNull(estatusEjecucionConciliacion3);
		assertTrue(estatusEjecucionConciliacion.equals(estatusEjecucionConciliacion));
		assertFalse(estatusEjecucionConciliacion.equals(estatusEjecucionConciliacion3));
		assertFalse(estatusEjecucionConciliacion.equals(conciliacion));

		EstatusEjecucionPreconciliacion estatusEjecucionPreconciliacion2 = new EstatusEjecucionPreconciliacion(estatusEjecucionPreconciliacion.getId(), estatusEjecucionPreconciliacion.getDescripcionCorta(), estatusEjecucionPreconciliacion.getDescripcion());
		assertNotNull(estatusEjecucionPreconciliacion2);
		assertNotNull(estatusEjecucionPreconciliacion2.toString());
		EstatusEjecucionPreconciliacion estatusEjecucionPreconciliacion3 = new EstatusEjecucionPreconciliacion(estatusEjecucionPreconciliacion.getId());
		assertNotNull(estatusEjecucionPreconciliacion3);
		assertTrue(estatusEjecucionPreconciliacion.equals(estatusEjecucionPreconciliacion));
		assertFalse(estatusEjecucionPreconciliacion.equals(estatusEjecucionPreconciliacion3));
		assertFalse(estatusEjecucionPreconciliacion.equals(conciliacion));

		EjecucionConciliacion ejecucionConciliacion2 = new EjecucionConciliacion(ejecucionConciliacion.getId(), ejecucionConciliacion.getEstatus(), ejecucionConciliacion.getConciliacion(), ejecucionConciliacion.getFechaEjecucion(), ejecucionConciliacion.getFechaPeriodoInicio(), ejecucionConciliacion.getFechaPeriodoFin(), ejecucionConciliacion.getProveedor());
		assertNotNull(ejecucionConciliacion2);
		assertNotNull(ejecucionConciliacion2.toString());
		EjecucionConciliacion ejecucionConciliacion3 = new EjecucionConciliacion(ejecucionConciliacion.getId());
		assertNotNull(ejecucionConciliacion3);
		assertTrue(ejecucionConciliacion.equals(ejecucionConciliacion));
		assertFalse(ejecucionConciliacion.equals(ejecucionConciliacion3));
		assertFalse(ejecucionConciliacion.equals(conciliacion));

		EjecucionPreconciliacion ejecucionPreconciliacion2 = new EjecucionPreconciliacion(ejecucionPreconciliacion.getId(), ejecucionPreconciliacion.getEstatus(), ejecucionPreconciliacion.getEstatusDescripcion(),ejecucionPreconciliacion.getFechaEjecucion(),ejecucionPreconciliacion.getFechaPeriodoInicio(), ejecucionPreconciliacion.getFechaPeriodoFin(), ejecucionPreconciliacion.getProveedor());
		assertNotNull(ejecucionPreconciliacion2);
		assertNotNull(ejecucionPreconciliacion2.toString());
		assertTrue(ejecucionPreconciliacion.equals(ejecucionPreconciliacion));
		assertFalse(ejecucionPreconciliacion.equals(new EjecucionPreconciliacion()));
		assertFalse(ejecucionPreconciliacion.equals(conciliacion));

		TrazadoEjecucionConciliacion trazadoEjecucionConciliacion2 = new TrazadoEjecucionConciliacion(trazadoEjecucionConciliacion.getId(), trazadoEjecucionConciliacion.getEstatus(), trazadoEjecucionConciliacion.getEjecucionConciliacion(), trazadoEjecucionConciliacion.getEstatusDescripcion(), trazadoEjecucionConciliacion.getFechaInicio(), trazadoEjecucionConciliacion.getFechaFin());
		assertNotNull(trazadoEjecucionConciliacion2);
		assertNotNull(trazadoEjecucionConciliacion2.toString());
		assertTrue(trazadoEjecucionConciliacion.equals(trazadoEjecucionConciliacion));
		assertFalse(trazadoEjecucionConciliacion.equals(new TrazadoEjecucionConciliacion()));
		assertFalse(trazadoEjecucionConciliacion.equals(conciliacion));

		CatalogoProceso catalogoProceso2 = new CatalogoProceso(catalogoProceso.getId(), catalogoProceso.getDescripcionCorta(), catalogoProceso.getDescripcion());
		assertNotNull(catalogoProceso2);
		assertNotNull(catalogoProceso2.toString());
		CatalogoProceso catalogoProceso3 = new CatalogoProceso(catalogoProceso.getId());
		assertNotNull(catalogoProceso3);
		assertTrue(catalogoProceso.equals(catalogoProceso));
		assertFalse(catalogoProceso.equals(catalogoProceso3));
		assertFalse(catalogoProceso.equals(conciliacion));

		CalendarioEjecucionProceso calendarioEjecucionProceso2 = new CalendarioEjecucionProceso(calendarioEjecucionProceso.getId(), calendarioEjecucionProceso.getProceso(), calendarioEjecucionProceso.getConfiguracion(), calendarioEjecucionProceso.getReintentos(), calendarioEjecucionProceso.getRangoDiasCoberturaMin(), calendarioEjecucionProceso.getRangoDiasCoberturaMax(), calendarioEjecucionProceso.getProveedor());
		assertNotNull(calendarioEjecucionProceso2);
		assertNotNull(calendarioEjecucionProceso2.toString());
		CalendarioEjecucionProceso calendarioEjecucionProceso3 = new CalendarioEjecucionProceso(calendarioEjecucionProceso.getId());
		assertNotNull(calendarioEjecucionProceso3);
		assertTrue(calendarioEjecucionProceso.equals(calendarioEjecucionProceso));
		assertFalse(calendarioEjecucionProceso.equals(calendarioEjecucionProceso3));
		assertFalse(calendarioEjecucionProceso.equals(conciliacion));

	}

	private CalendarioEjecucionProceso crearCalendarioEjecucionProceso() {
		CalendarioEjecucionProceso elemento = new CalendarioEjecucionProceso();
		elemento.setId(1l);
		elemento.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		elemento.setProceso(new CatalogoProceso(ProcesoEnum.CONCILIACION_ETAPA_1.getIdProceso()));
		elemento.setRangoDiasCoberturaMax(1);
		elemento.setRangoDiasCoberturaMin(1);
		elemento.setActivo(true);
		elemento.setReintentos(1);
		elemento.setConfiguracion("0 0 0 31 DEC ?");
		return elemento;
	}

	private Conciliacion crearConciliacion() {
		Conciliacion elemento = new Conciliacion();
		elemento.setId(1L);
		elemento.setCreatedDate(new Date());
		elemento.setEntidad(new Entidad(11L, ""));
		elemento.setCuenta(new Cuenta(1L, ""));
		elemento.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		elemento.setEstatus(new EstatusConciliacion(1));
		elemento.setSubEstatus(new SubEstatusConciliacion(1L));
		elemento.setSubEstatusDescripcion("Test");
		return elemento;
	}

	private EjecucionConciliacion crearEjecucionConciliacion(Conciliacion proceso) {
		EjecucionConciliacion elemento = new EjecucionConciliacion();
		elemento.setId(1l);
		elemento.setFechaEjecucion(new Date());
		elemento.setFechaPeriodoInicio(new Date());
		elemento.setFechaPeriodoFin(new Date());
		elemento.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		elemento.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CREADA.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CREADA.getEstadoEjecucion()));
		elemento.setConciliacion(proceso);
		return elemento;
	}

	private EjecucionPreconciliacion crearEjecucionPreconciliacion() {
		EjecucionPreconciliacion elemento = new EjecucionPreconciliacion();
		elemento.setId(1l);
		elemento.setFechaEjecucion(new Date());
		elemento.setFechaPeriodoInicio(new Date());
		elemento.setFechaPeriodoFin(new Date());
		elemento.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		elemento.setEstatus(new EstatusEjecucionPreconciliacion(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getIdEstadoEjecucion()));
		elemento.setEstatusDescripcion(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getEstadoEjecucion());
		return elemento;
	}

	private EstatusEjecucionPreconciliacion crearEstatusEjecucionPreconciliacion() {
		EstatusEjecucionPreconciliacion elemento = new EstatusEjecucionPreconciliacion();
		elemento.setId(1);
		elemento.setDescripcion("TEST");
		elemento.setDescripcionCorta("TEST");
		elemento.setOrderNumber(1);
		return elemento;
	}

	private CatalogoProceso crearCatalogoProceso() {
		CatalogoProceso elemento = new CatalogoProceso();
		elemento.setId(1);
		elemento.setDescripcion("TEST");
		elemento.setDescripcionCorta("TEST");
		return elemento;
	}

	private EstatusEjecucionConciliacion crearEstatusEjecucionConciliacion() {
		EstatusEjecucionConciliacion elemento = new EstatusEjecucionConciliacion();
		elemento.setId(1);
		elemento.setDescripcion("TEST");
		elemento.setDescripcionCorta("TEST");
		elemento.setOrderNumber(1);
		return elemento;
	}

	private TrazadoEjecucionConciliacion crearTrazadoEjecucion(EjecucionConciliacion elemento) {
		TrazadoEjecucionConciliacion trazado = new TrazadoEjecucionConciliacion();
		trazado.setId(1L);
		trazado.setFechaInicio(new Date());
		trazado.setFechaFin(new Date());
		trazado.setEjecucionConciliacion(elemento);
		trazado.setEstatus(elemento.getEstatus());
		trazado.setEstatusDescripcion(elemento.getEstatus().getDescripcion());
		return trazado;
	}

	private CatalogoDiaInhabil crearDiaInhabil() {
		CatalogoDiaInhabil elemento = new CatalogoDiaInhabil();
		elemento.setId(1);
		elemento.setFecha(new Date());
		elemento.setDescripcion("TEST");
		elemento.setDescripcionCorta("TEST");
		return elemento;
	}


}