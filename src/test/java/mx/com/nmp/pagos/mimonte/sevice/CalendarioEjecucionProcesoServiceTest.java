
package mx.com.nmp.pagos.mimonte.sevice;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.CalendarioEjecucionProcesoRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.CalendarioEjecucionProcesoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class CalendarioEjecucionProcesoServiceTest {

	@InjectMocks
	private CalendarioEjecucionProcesoServiceImpl calendarioEjecucionProcesoService;

	@Mock
	private CalendarioEjecucionProcesoRepository calendarioEjecucionProcesoRepository;

	private CalendarioEjecucionProceso calendarioEjecucionProceso;

	private FiltroCalendarioEjecucionProcesoDTO filtroCalendarioEjecucionProcesoDTO;

	private CalendarioEjecucionProcesoDTO calendarioEjecucionProcesoDTO;

	@Before
	public void setUp(){

		calendarioEjecucionProceso = new CalendarioEjecucionProceso();
		calendarioEjecucionProceso.setId(1l);
		calendarioEjecucionProceso.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		calendarioEjecucionProceso.setProceso(new CatalogoProceso(ProcesoEnum.PRE_CONCILIACION.getIdProceso()));
		calendarioEjecucionProceso.setRangoDiasCoberturaMax(1);
		calendarioEjecucionProceso.setRangoDiasCoberturaMin(1);
		calendarioEjecucionProceso.setActivo(true);
		calendarioEjecucionProceso.setReintentos(1);
		calendarioEjecucionProceso.setConfiguracion("0/59 * * * * ?");

		filtroCalendarioEjecucionProcesoDTO = new FiltroCalendarioEjecucionProcesoDTO();
		filtroCalendarioEjecucionProcesoDTO.setIdCalendario(calendarioEjecucionProceso.getId());
		filtroCalendarioEjecucionProcesoDTO.setIdProceso(calendarioEjecucionProceso.getProceso().getId());
		filtroCalendarioEjecucionProcesoDTO.setCorresponsal(calendarioEjecucionProceso.getProveedor().getNombre().getNombre());
		filtroCalendarioEjecucionProcesoDTO.setActivo(calendarioEjecucionProceso.getActivo());
		filtroCalendarioEjecucionProcesoDTO.setReintentos(calendarioEjecucionProceso.getReintentos());

		calendarioEjecucionProcesoDTO = new CalendarioEjecucionProcesoDTO();
		calendarioEjecucionProcesoDTO.setId(calendarioEjecucionProceso.getId());
		calendarioEjecucionProcesoDTO.setCorresponsal(calendarioEjecucionProceso.getProveedor().getNombre());
		calendarioEjecucionProcesoDTO.setProceso(new ProcesoDTO(calendarioEjecucionProceso.getProceso().getId()));
		calendarioEjecucionProcesoDTO.setRangoDiasCoberturaMax(calendarioEjecucionProceso.getRangoDiasCoberturaMax());
		calendarioEjecucionProcesoDTO.setRangoDiasCoberturaMin(calendarioEjecucionProceso.getRangoDiasCoberturaMin());
		calendarioEjecucionProcesoDTO.setActivo(calendarioEjecucionProceso.getActivo());
		calendarioEjecucionProcesoDTO.setReintentos(calendarioEjecucionProceso.getReintentos());
		calendarioEjecucionProcesoDTO.setConfiguracionAutomatizacion(calendarioEjecucionProceso.getConfiguracion());

	}


	@Test
	public void  consultarByPropiedades(){
		List<CalendarioEjecucionProcesoDTO> lista = new ArrayList<>();
		lista.add(calendarioEjecucionProcesoDTO);
		when(calendarioEjecucionProcesoRepository.findByPropiedades(anyLong(), anyInt(), anyBoolean(), anyInt(), any())).thenReturn(lista);
		List<CalendarioEjecucionProcesoDTO> listaResultados = calendarioEjecucionProcesoService.consultarByPropiedades(filtroCalendarioEjecucionProcesoDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}


}