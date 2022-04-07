/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.scheduling;


import mx.com.nmp.pagos.mimonte.dto.conciliacion.CalendarioEjecucionProcesoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ProcesoDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ProcesoPreconciliacionService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @name PreconciliacionSchedulerTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente al scheduler  PreconciliacionScheduler
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class PreconciliacionSchedulerTest {


	@InjectMocks
	private PreconciliacionScheduler preconciliacionScheduler;
	
	@Mock
	private ProcesoPreconciliacionService procesoPreconciliacionService;

	private CalendarioEjecucionProceso calendarioEjecucionProceso;

	private CalendarioEjecucionProcesoDTO calendarioEjecucionProcesoDTO;

	private EjecucionPreconciliacion ejecucionPreconciliacion;


	@Test
	public void t1_createSchedulers(){

		calendarioEjecucionProceso = crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);

		List<CalendarioEjecucionProcesoDTO> listaConfiguraciones = new ArrayList<>();
		when(procesoPreconciliacionService.obtenerCalendarizacionPreconciliacion()).thenReturn(listaConfiguraciones);
		Assertions.assertDoesNotThrow(() -> preconciliacionScheduler.createSchedulers());

		listaConfiguraciones.add(calendarioEjecucionProcesoDTO);
		when(procesoPreconciliacionService.obtenerCalendarizacionPreconciliacion()).thenReturn(listaConfiguraciones);
		Assertions.assertDoesNotThrow(() -> preconciliacionScheduler.createSchedulers());

		listaConfiguraciones.get(0).setConfiguracionAutomatizacion("");
		when(procesoPreconciliacionService.obtenerCalendarizacionPreconciliacion()).thenReturn(listaConfiguraciones);
		Assertions.assertDoesNotThrow(() -> preconciliacionScheduler.createSchedulers());

		verify(procesoPreconciliacionService, times(3)).obtenerCalendarizacionPreconciliacion();

	}

	@Test
	public void t2_lanzarPreconciliacionAutomatizada(){

		calendarioEjecucionProceso = crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);
		ejecucionPreconciliacion = this.crearEjecucionPreconciliacion();

		when(procesoPreconciliacionService.crearEjecucionPreconciliacion(any())).thenReturn(ejecucionPreconciliacion);
		when(procesoPreconciliacionService.validarFechaInhabil(any())).thenReturn(Boolean.TRUE);
		when(procesoPreconciliacionService.validarDuplicidadProceso(any())).thenReturn(Boolean.TRUE);
		doNothing().when(procesoPreconciliacionService).ejecutarProcesoPreconciliacion(any(),any());

		Assertions.assertDoesNotThrow(() -> preconciliacionScheduler.lanzarPreconciliacionAutomatizada(calendarioEjecucionProcesoDTO));

		verify(procesoPreconciliacionService, times(1)).crearEjecucionPreconciliacion(any());
		verify(procesoPreconciliacionService, times(1)).validarFechaInhabil(any());
		verify(procesoPreconciliacionService, times(1)).validarDuplicidadProceso(any());
		verify(procesoPreconciliacionService, times(1)).ejecutarProcesoPreconciliacion(any(), any());

	}

	@Test
	public void t3_lanzarPreconciliacionAutomatizada(){

		calendarioEjecucionProceso = crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);
		ejecucionPreconciliacion = this.crearEjecucionPreconciliacion();

		when(procesoPreconciliacionService.crearEjecucionPreconciliacion(any())).thenReturn(ejecucionPreconciliacion);
		when(procesoPreconciliacionService.validarFechaInhabil(any())).thenReturn(Boolean.FALSE);

		Assertions.assertDoesNotThrow(() -> preconciliacionScheduler.lanzarPreconciliacionAutomatizada(calendarioEjecucionProcesoDTO));

		verify(procesoPreconciliacionService, times(1)).crearEjecucionPreconciliacion(any());
		verify(procesoPreconciliacionService, times(1)).validarFechaInhabil(any());

	}

	@Test
	public void t4_lanzarPreconciliacionAutomatizada(){

		calendarioEjecucionProceso = crearCalendarioEjecucionProceso();
		calendarioEjecucionProcesoDTO = this.crearCalendarioEjecucionProcesoDTO(calendarioEjecucionProceso);
		ejecucionPreconciliacion = this.crearEjecucionPreconciliacion();

		when(procesoPreconciliacionService.crearEjecucionPreconciliacion(any())).thenReturn(ejecucionPreconciliacion);
		when(procesoPreconciliacionService.validarFechaInhabil(any())).thenReturn(Boolean.TRUE);
		when(procesoPreconciliacionService.validarDuplicidadProceso(any())).thenReturn(Boolean.FALSE);

		Assertions.assertDoesNotThrow(() -> preconciliacionScheduler.lanzarPreconciliacionAutomatizada(calendarioEjecucionProcesoDTO));

		verify(procesoPreconciliacionService, times(1)).crearEjecucionPreconciliacion(any());
		verify(procesoPreconciliacionService, times(1)).validarFechaInhabil(any());
		verify(procesoPreconciliacionService, times(1)).validarDuplicidadProceso(any());

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

}