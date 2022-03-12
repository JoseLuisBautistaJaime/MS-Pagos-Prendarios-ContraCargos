
package mx.com.nmp.pagos.mimonte.sevice;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.EjecucionPreconciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusEjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroEjecucionPreconciliacionDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.EjecucionPreconciliacionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.junit.Before;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.List;


@RunWith(SpringRunner.class)
public class  EjecucionPreconciliacionServiceTest {

	@InjectMocks
	private EjecucionPreconciliacionServiceImpl ejecucionPreconciliacionService;

	@Mock
	private EjecucionPreconciliacionRepository ejecucionPreconciliacionRepository;

	private EjecucionPreconciliacion ejecucionPreconciliacion;

	private FiltroEjecucionPreconciliacionDTO filtroEjecucionPreconciliacionDTO;

	private EjecucionPreconciliacionDTO ejecucionPreconciliacionDTO;

	@Before
	public void setUp(){

		ejecucionPreconciliacion = new EjecucionPreconciliacion();
		ejecucionPreconciliacion.setId(1l);
		ejecucionPreconciliacion.setFechaEjecucion(new Date());
		ejecucionPreconciliacion.setFechaPeriodoInicio(new Date());
		ejecucionPreconciliacion.setFechaPeriodoFin(new Date());
		ejecucionPreconciliacion.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		ejecucionPreconciliacion.setEstatus(new EstatusEjecucionPreconciliacion(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getIdEstadoEjecucion()));
		ejecucionPreconciliacion.setEstatusDescripcion(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getEstadoEjecucion());

		filtroEjecucionPreconciliacionDTO = new FiltroEjecucionPreconciliacionDTO();
		filtroEjecucionPreconciliacionDTO.setFechaPeriodoInicio(ejecucionPreconciliacion.getFechaPeriodoInicio());
		filtroEjecucionPreconciliacionDTO.setFechaPeriodoFin(ejecucionPreconciliacion.getFechaPeriodoFin());
		filtroEjecucionPreconciliacionDTO.setCorresponsal(ejecucionPreconciliacion.getProveedor().getNombre().getNombre());
		filtroEjecucionPreconciliacionDTO.setIdEstatus(ejecucionPreconciliacion.getEstatus().getId());
		filtroEjecucionPreconciliacionDTO.setFechaEjecucionDesde(ejecucionPreconciliacion.getFechaEjecucion());
		filtroEjecucionPreconciliacionDTO.setFechaEjecucionHasta(ejecucionPreconciliacion.getFechaEjecucion());
		filtroEjecucionPreconciliacionDTO.setEstatusDescripcion(ejecucionPreconciliacion.getEstatusDescripcion());

		ejecucionPreconciliacionDTO = new EjecucionPreconciliacionDTO();
		ejecucionPreconciliacionDTO.setId(ejecucionPreconciliacion.getId());
		ejecucionPreconciliacionDTO.setCorresponsal(ejecucionPreconciliacion.getProveedor().getNombre());
		ejecucionPreconciliacionDTO.setEstatus(new EstatusEjecucionPreconciliacionDTO(ejecucionPreconciliacion.getEstatus().getId()));
		ejecucionPreconciliacionDTO.setEstatusDescripcion(ejecucionPreconciliacion.getEstatusDescripcion());
		ejecucionPreconciliacionDTO.setFechaEjecucion(ejecucionPreconciliacion.getFechaEjecucion());
		ejecucionPreconciliacionDTO.setFechaPeriodoInicio(ejecucionPreconciliacion.getFechaPeriodoInicio());
		ejecucionPreconciliacionDTO.setFechaPeriodoFin(ejecucionPreconciliacion.getFechaPeriodoFin());

	}


	@Test
	public void  consultarByPropiedades(){
		List<EjecucionPreconciliacionDTO> lista = new ArrayList<EjecucionPreconciliacionDTO>(){{add(ejecucionPreconciliacionDTO);}};
		when(ejecucionPreconciliacionRepository.findByPropiedades(anyInt(), anyString(), any(), any(), any(), any(), any())).thenReturn(lista);
		List<EjecucionPreconciliacionDTO> listaResultados = ejecucionPreconciliacionService.consultarByPropiedades(filtroEjecucionPreconciliacionDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}


	@Test
	public void save(){
		when(ejecucionPreconciliacionRepository.save(any())).thenReturn(ejecucionPreconciliacion);
		EjecucionPreconciliacion respuesta = ejecucionPreconciliacionService.save(ejecucionPreconciliacion,"Test");
		assertNotNull(respuesta);
	}


}