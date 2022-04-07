/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;


import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionMidasProveedorService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @name ConciliacionMidasProveedorSchedulerTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente al scheduler  ConciliacionMidasProveedorScheduler
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class ConciliacionMidasProveedorSchedulerTest {


	@InjectMocks
	private ConciliacionMidasProveedorScheduler conciliacionMidasProveedorScheduler;
	
	@Mock
	private ConciliacionMidasProveedorService conciliacionMidasProveedor;

	private Conciliacion conciliacion;

	private CalendarioEjecucionProceso calendarioEjecucionProceso;

	private CalendarioEjecucionProcesoDTO calendarioEjecucionProcesoDTO;

	private EjecucionConciliacion ejecucionConciliacion;


	@Test
	public void t1_createSchedulers(){

		calendarioEjecucionProceso = crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		List<CalendarioEjecucionProcesoDTO> listaConfiguraciones = new ArrayList<>();
		when(conciliacionMidasProveedor.obtenerCalendarizacionConciliacion(any(), any())).thenReturn(listaConfiguraciones);
		Assertions.assertDoesNotThrow(() -> conciliacionMidasProveedorScheduler.createSchedulers());

		listaConfiguraciones.add(calendarioEjecucionProcesoDTO);
		when(conciliacionMidasProveedor.obtenerCalendarizacionConciliacion(any(), any())).thenReturn(listaConfiguraciones);
		Assertions.assertDoesNotThrow(() -> conciliacionMidasProveedorScheduler.createSchedulers());

		listaConfiguraciones.get(0).setConfiguracionAutomatizacion("");
		when(conciliacionMidasProveedor.obtenerCalendarizacionConciliacion(any(), any())).thenReturn(listaConfiguraciones);
		Assertions.assertDoesNotThrow(() -> conciliacionMidasProveedorScheduler.createSchedulers());

		verify(conciliacionMidasProveedor, times(3)).obtenerCalendarizacionConciliacion(any(), any());

	}

	@Test
	public void t2_lanzarConciliacionEtapa1(){

		calendarioEjecucionProceso = crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);
		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(conciliacionMidasProveedor.crearEjecucionConciliacion(any())).thenReturn(ejecucionConciliacion);
		when(conciliacionMidasProveedor.validarDuplicidadEjecucion(any())).thenReturn(Boolean.TRUE);
		when(conciliacionMidasProveedor.crearConciliacion(any())).thenReturn(conciliacion);
		when(conciliacionMidasProveedor.validarDuplicidadProceso(any())).thenReturn(Boolean.TRUE);
		doNothing().when(conciliacionMidasProveedor).ejecutarProcesoConciliacionE1(any());

		Assertions.assertDoesNotThrow(() -> conciliacionMidasProveedorScheduler.lanzarConciliacionEtapa1(calendarioEjecucionProcesoDTO));

		verify(conciliacionMidasProveedor, times(1)).crearEjecucionConciliacion(any());
		verify(conciliacionMidasProveedor, times(1)).validarDuplicidadEjecucion(any());
		verify(conciliacionMidasProveedor, times(1)).crearConciliacion(any());
		verify(conciliacionMidasProveedor, times(1)).validarDuplicidadProceso(any());
		verify(conciliacionMidasProveedor, times(1)).ejecutarProcesoConciliacionE1(any());

	}

	@Test
	public void t3_lanzarConciliacionEtapa1(){

		calendarioEjecucionProceso = crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);
		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(conciliacionMidasProveedor.crearEjecucionConciliacion(any())).thenReturn(ejecucionConciliacion);
		when(conciliacionMidasProveedor.validarDuplicidadEjecucion(any())).thenReturn(Boolean.FALSE);

		Assertions.assertDoesNotThrow(() -> conciliacionMidasProveedorScheduler.lanzarConciliacionEtapa1(calendarioEjecucionProcesoDTO));

		verify(conciliacionMidasProveedor, times(1)).crearEjecucionConciliacion(any());
		verify(conciliacionMidasProveedor, times(1)).validarDuplicidadEjecucion(any());

	}

	@Test
	public void t4_lanzarConciliacionEtapa1(){

		calendarioEjecucionProceso = crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);
		conciliacion = this.crearConciliacion();
		ejecucionConciliacion = this.crearEjecucionConciliacion(conciliacion);

		when(conciliacionMidasProveedor.crearEjecucionConciliacion(any())).thenReturn(ejecucionConciliacion);
		when(conciliacionMidasProveedor.validarDuplicidadEjecucion(any())).thenReturn(Boolean.TRUE);
		when(conciliacionMidasProveedor.crearConciliacion(any())).thenReturn(conciliacion);
		when(conciliacionMidasProveedor.validarDuplicidadProceso(any())).thenReturn(Boolean.FALSE);

		Assertions.assertDoesNotThrow(() -> conciliacionMidasProveedorScheduler.lanzarConciliacionEtapa1(calendarioEjecucionProcesoDTO));

		verify(conciliacionMidasProveedor, times(1)).crearEjecucionConciliacion(any());
		verify(conciliacionMidasProveedor, times(1)).validarDuplicidadEjecucion(any());
		verify(conciliacionMidasProveedor, times(1)).crearConciliacion(any());
		verify(conciliacionMidasProveedor, times(1)).validarDuplicidadProceso(any());

	}

	private CalendarioEjecucionProcesoDTO crearCalendarioEjecucionProcesoDTO(CalendarioEjecucionProceso elemento) {
		CalendarioEjecucionProcesoDTO elementoDTO = new CalendarioEjecucionProcesoDTO();
		elementoDTO.setId(elemento.getId());
		elementoDTO.setCorresponsal(elemento.getProveedor().getNombre());
		elementoDTO.setProceso(new ProcesoDTO(elemento.getProceso().getId()));
		elementoDTO.setRangoDiasCoberturaMax(elemento.getRangoDiasCoberturaMax());
		elementoDTO.setRangoDiasCoberturaMin(elemento.getRangoDiasCoberturaMin());
		elementoDTO.setActivo(elemento.getActivo());
		elementoDTO.setReintentos(elemento.getReintentos());
		elementoDTO.setConfiguracionAutomatizacion(elemento.getConfiguracion());
		return  elementoDTO;
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
		elemento.setEntidad(new Entidad(11L,""));
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

}