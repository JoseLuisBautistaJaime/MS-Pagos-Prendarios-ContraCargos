/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.repository;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.EjecucionConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionEjecucionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EjecucionConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusEjecucionConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroEjecucionConciliacionDTO;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Clase de prueba para el repositorio EjecucionConciliacionRepository.
 *
 * @author jmreveles
 */
@RunWith(MockitoJUnitRunner.class)
public class EjecucionConciliacionRepositoryTest {

	/**
	 * Referencia al repositorio
	 */
	@Mock
	private EjecucionConciliacionRepository ejecucionConciliacionRepository;

	@Before
	public void setUp(){

	}

	private EjecucionConciliacionDTO crearElementoDTO(EjecucionConciliacion elemento) {
		EjecucionConciliacionDTO elementoDTO = new EjecucionConciliacionDTO();
		elementoDTO.setId(elemento.getId());
		elementoDTO.setCorresponsal(elemento.getProveedor().getNombre());
		elementoDTO.setEstatus(new EstatusEjecucionConciliacionDTO(elemento.getEstatus().getId()));
		elementoDTO.setEstatusDescripcion(elemento.getEstatus().getDescripcion());
		elementoDTO.setFechaEjecucion(elemento.getFechaEjecucion());
		elementoDTO.setFechaPeriodoInicio(elemento.getFechaPeriodoInicio());
		elementoDTO.setFechaPeriodoFin(elemento.getFechaPeriodoFin());
		elementoDTO.setConciliacion(new ConciliacionEjecucionDTO(elemento.getConciliacion().getFolio(), elemento.getConciliacion().getId()));
		return elementoDTO;
	}

	private TrazadoEjecucionConciliacion crearTrazado(EjecucionConciliacion elemento) {
		TrazadoEjecucionConciliacion trazado = new TrazadoEjecucionConciliacion();
		trazado.setId(1L);
		trazado.setFechaInicio(new Date());
		trazado.setFechaFin(new Date());
		trazado.setEjecucionConciliacion(elemento);
		trazado.setEstatus(elemento.getEstatus());
		trazado.setEstatusDescripcion(elemento.getEstatus().getDescripcion());
		return trazado;
	}

	private FiltroEjecucionConciliacionDTO crearFiltro(EjecucionConciliacion elemento) {
		FiltroEjecucionConciliacionDTO filtroDTO = new FiltroEjecucionConciliacionDTO();
		filtroDTO.setFechaPeriodoInicio(elemento.getFechaPeriodoInicio());
		filtroDTO.setFechaPeriodoFin(elemento.getFechaPeriodoFin());
		filtroDTO.setCorresponsal(elemento.getProveedor().getNombre().getNombre());
		filtroDTO.setIdEstatus(elemento.getEstatus().getId());
		filtroDTO.setFechaEjecucionDesde(elemento.getFechaEjecucion());
		filtroDTO.setFechaEjecucionHasta(elemento.getFechaEjecucion());
		filtroDTO.setIdConciliacion(elemento.getConciliacion().getId());
		filtroDTO.setIdCuenta(elemento.getConciliacion().getCuenta().getId());
		filtroDTO.setIdEntidad(elemento.getConciliacion().getEntidad().getId());
		return filtroDTO;
	}

	private EjecucionConciliacion crearElemento() {
		Conciliacion conciliacion = new Conciliacion();
		conciliacion.setId(1L);
		conciliacion.setCreatedDate(new Date());
		conciliacion.setEntidad(new Entidad(11L, ""));
		conciliacion.setCuenta(new Cuenta(1L, ""));
		conciliacion.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		conciliacion.setEstatus(new EstatusConciliacion(1));
		conciliacion.setSubEstatus(new SubEstatusConciliacion(1L));

		EjecucionConciliacion elemento = new EjecucionConciliacion();
		elemento.setId(1l);
		elemento.setFechaEjecucion(new Date());
		elemento.setFechaPeriodoInicio(new Date());
		elemento.setFechaPeriodoFin(new Date());
		elemento.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		elemento.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CREADA.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CREADA.getEstadoEjecucion()));
		elemento.setConciliacion(conciliacion);
		return elemento;

	}


	@Test
	public void t1_findByPropiedades() {
		EjecucionConciliacion ejecucionConciliacion = this.crearElemento();
		FiltroEjecucionConciliacionDTO filtroDTO = this.crearFiltro(ejecucionConciliacion);
		EjecucionConciliacionDTO ejecucionConciliacionDTO = this.crearElementoDTO(ejecucionConciliacion);
		List<EjecucionConciliacionDTO> lista = new ArrayList<EjecucionConciliacionDTO>(){{add(ejecucionConciliacionDTO);}};
		when(ejecucionConciliacionRepository.findByPropiedades(any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(lista);
		List<EjecucionConciliacionDTO> resultadoLista = ejecucionConciliacionRepository.findByPropiedades(
				filtroDTO.getIdEstatus(),  filtroDTO.getIdEntidad(), filtroDTO.getIdCuenta(), filtroDTO.getIdConciliacion(), filtroDTO.getFechaPeriodoInicio(),
				filtroDTO.getFechaPeriodoFin(),filtroDTO.getFechaEjecucionDesde(), filtroDTO.getFechaEjecucionHasta(), CorresponsalEnum.getByNombre( filtroDTO.getCorresponsal()) );

		assertNotNull(resultadoLista);
		assertTrue(!resultadoLista.isEmpty());
	}

}
