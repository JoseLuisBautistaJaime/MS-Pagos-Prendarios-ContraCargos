
package mx.com.nmp.pagos.mimonte.sevice;

import com.ibm.icu.util.Calendar;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EjecucionConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EjecucionPreconciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.TrazadoEjecucionConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.EjecucionConciliacionServiceImpl;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.EjecucionPreconciliacionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class EjecucionConciliacionServiceTest {

	@InjectMocks
	private EjecucionConciliacionServiceImpl ejecucionConciliacionService;

	@Mock
	private EjecucionConciliacionRepository ejecucionConciliacionRepository;

	@Mock
	private TrazadoEjecucionConciliacionRepository trazadoEjecucionConciliacionRepository;

	private EjecucionConciliacion ejecucionConciliacion;

	private FiltroEjecucionConciliacionDTO filtroEjecucionConciliacionDTO;

	private EjecucionConciliacionDTO ejecucionConciliacionDTO;

	private TrazadoEjecucionConciliacion trazadoEjecucionConciliacion;

	@Before
	public void setUp() {

		Conciliacion conciliacion = new Conciliacion();
		conciliacion.setId(1L);
		conciliacion.setCreatedDate(new Date());
		conciliacion.setEntidad(new Entidad(11L, ""));
		conciliacion.setCuenta(new Cuenta(1L, ""));
		conciliacion.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		conciliacion.setEstatus(new EstatusConciliacion(1));
		conciliacion.setSubEstatus(new SubEstatusConciliacion(1L));

		ejecucionConciliacion = new EjecucionConciliacion();
		ejecucionConciliacion.setId(1l);
		ejecucionConciliacion.setFechaEjecucion(new Date());
		ejecucionConciliacion.setFechaPeriodoInicio(new Date());
		ejecucionConciliacion.setFechaPeriodoFin(new Date());
		ejecucionConciliacion.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		ejecucionConciliacion.setEstatus(new EstatusEjecucionConciliacion(EstatusEjecucionConciliacionEnum.CREADA.getIdEstadoEjecucion(), EstatusEjecucionConciliacionEnum.CREADA.getEstadoEjecucion()));
		ejecucionConciliacion.setConciliacion(conciliacion);

		filtroEjecucionConciliacionDTO = new FiltroEjecucionConciliacionDTO();
		filtroEjecucionConciliacionDTO.setFechaPeriodoInicio(ejecucionConciliacion.getFechaPeriodoInicio());
		filtroEjecucionConciliacionDTO.setFechaPeriodoFin(ejecucionConciliacion.getFechaPeriodoFin());
		filtroEjecucionConciliacionDTO.setCorresponsal(ejecucionConciliacion.getProveedor().getNombre().getNombre());
		filtroEjecucionConciliacionDTO.setIdEstatus(ejecucionConciliacion.getEstatus().getId());
		filtroEjecucionConciliacionDTO.setFechaEjecucionDesde(ejecucionConciliacion.getFechaEjecucion());
		filtroEjecucionConciliacionDTO.setFechaEjecucionHasta(ejecucionConciliacion.getFechaEjecucion());
		filtroEjecucionConciliacionDTO.setIdConciliacion(ejecucionConciliacion.getConciliacion().getId());
		filtroEjecucionConciliacionDTO.setIdCuenta(ejecucionConciliacion.getConciliacion().getCuenta().getId());
		filtroEjecucionConciliacionDTO.setIdEntidad(ejecucionConciliacion.getConciliacion().getEntidad().getId());

		trazadoEjecucionConciliacion = new TrazadoEjecucionConciliacion();
		trazadoEjecucionConciliacion.setId(1L);
		trazadoEjecucionConciliacion.setFechaInicio(new Date());
		trazadoEjecucionConciliacion.setFechaFin(new Date());
		trazadoEjecucionConciliacion.setEjecucionConciliacion(ejecucionConciliacion);
		trazadoEjecucionConciliacion.setEstatus(ejecucionConciliacion.getEstatus());
		trazadoEjecucionConciliacion.setEstatusDescripcion(ejecucionConciliacion.getEstatus().getDescripcion());

		ejecucionConciliacionDTO = new EjecucionConciliacionDTO();
		ejecucionConciliacionDTO.setId(ejecucionConciliacion.getId());
		ejecucionConciliacionDTO.setCorresponsal(ejecucionConciliacion.getProveedor().getNombre());
		ejecucionConciliacionDTO.setEstatus(new EstatusEjecucionConciliacionDTO(ejecucionConciliacion.getEstatus().getId()));
		ejecucionConciliacionDTO.setEstatusDescripcion(ejecucionConciliacion.getEstatus().getDescripcion());
		ejecucionConciliacionDTO.setFechaEjecucion(ejecucionConciliacion.getFechaEjecucion());
		ejecucionConciliacionDTO.setFechaPeriodoInicio(ejecucionConciliacion.getFechaPeriodoInicio());
		ejecucionConciliacionDTO.setFechaPeriodoFin(ejecucionConciliacion.getFechaPeriodoFin());
		ejecucionConciliacionDTO.setConciliacion(new ConciliacionEjecucionDTO(ejecucionConciliacion.getConciliacion().getFolio(), ejecucionConciliacion.getConciliacion().getId()));
	}

	@Test
	public void consultarByPropiedades() {
		List<EjecucionConciliacionDTO> lista = new ArrayList<EjecucionConciliacionDTO>(){{add(ejecucionConciliacionDTO);}};
		when(ejecucionConciliacionRepository.findByPropiedades(anyInt(), anyLong(), anyLong(), anyLong(), any(), any(), any(), any(), any())).thenReturn(lista);
		List<EjecucionConciliacionDTO> listaResultados = ejecucionConciliacionService.consultarByPropiedades(filtroEjecucionConciliacionDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void consultarByIdEjecucion() {
		when(ejecucionConciliacionRepository.findByIdEjecucion(anyLong())).thenReturn(ejecucionConciliacionDTO);
		EjecucionConciliacionDTO resultado =  ejecucionConciliacionService.consultarByIdEjecucion(ejecucionConciliacion.getId());
		assertNotNull(resultado);
	}

	@Test
	public void guardarEjecucionConciliacion(){
		doNothing().when(ejecucionConciliacionRepository).actualizaEstatusEjecucionConciliacion(anyLong(), any(), anyString(), any());
		when(ejecucionConciliacionRepository.save(any())).thenReturn(ejecucionConciliacion);
		when(trazadoEjecucionConciliacionRepository.save( any())).thenReturn(trazadoEjecucionConciliacion);
		Assertions.assertDoesNotThrow(() -> ejecucionConciliacionService.guardarEjecucionConciliacion(trazadoEjecucionConciliacion, "TEST"));

	}


	@Test
	public void save() {
		when(ejecucionConciliacionRepository.save(any())).thenReturn(ejecucionConciliacion);
		EjecucionConciliacion respuesta = ejecucionConciliacionService.save(ejecucionConciliacion,"Test");
		assertNotNull(respuesta);
	}


	@Test
	public void consultarByIdConciliacion(){
		when(ejecucionConciliacionRepository.findByIdConciliacion(anyLong())).thenReturn(ejecucionConciliacion);
		EjecucionConciliacion resultado =  ejecucionConciliacionService.consultarByIdConciliacion(ejecucionConciliacion.getConciliacion().getId());
		assertNotNull(resultado);
	}



}