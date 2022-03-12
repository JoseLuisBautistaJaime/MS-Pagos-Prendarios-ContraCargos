
package mx.com.nmp.pagos.mimonte.sevice;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.DiaInhabilRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.DiaInhabilServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class DiaInhabilServiceTest {

	@InjectMocks
	private DiaInhabilServiceImpl diaInhabilService;

	@Mock
	private DiaInhabilRepository diaInhabilRepository;

	private CatalogoDiaInhabil diaInhabil;

	private FiltroDiaInhabilDTO filtroDiaInhabilDTO;

	private DiaInhabilDTO diaInhabilDTO;

	@Before
	public void setUp(){

		diaInhabil = new CatalogoDiaInhabil();
		diaInhabil.setId(1);
		diaInhabil.setFecha(new Date());
		diaInhabil.setDescripcion("TEST");
		diaInhabil.setDescripcionCorta("TEST");

		filtroDiaInhabilDTO = new FiltroDiaInhabilDTO();
		filtroDiaInhabilDTO.setFecha(diaInhabil.getFecha());
		filtroDiaInhabilDTO.setDescripcion(diaInhabil.getDescripcion());

		diaInhabilDTO = new DiaInhabilDTO();
		diaInhabilDTO.setId(diaInhabil.getId());
		diaInhabilDTO.setFecha(diaInhabil.getFecha());
		diaInhabilDTO.setDescripcion(diaInhabil.getDescripcion());
		diaInhabilDTO.setDescripcionCorta(diaInhabil.getDescripcionCorta());

	}

	@Test
	public void buscarByFecha(){
		when(diaInhabilRepository.findByFecha(any())).thenReturn(diaInhabil);
		CatalogoDiaInhabil respuesta = diaInhabilService.buscarByFecha( diaInhabil.getFecha() );
		assertNotNull(respuesta);
	}

	@Test
	public void consultarByPropiedades() {
		List<DiaInhabilDTO> lista = new ArrayList<DiaInhabilDTO>(){{add(diaInhabilDTO);}};
		when(diaInhabilRepository.findByPropiedades(any(),anyString())).thenReturn(lista);
		List<DiaInhabilDTO> listaResultados = diaInhabilService.consultarByPropiedades(filtroDiaInhabilDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void saveDiaInhabil() {
		when(diaInhabilRepository.save(any())).thenReturn(diaInhabil);
		DiaInhabilDTO respuesta = diaInhabilService.saveDiaInhabil(diaInhabilDTO);
		assertNotNull(respuesta);
	}

}