/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.sevice;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.DiaInhabilRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.DiaInhabilService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * @name DiaInhabilServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio DiaInhabilService
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 12:55 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class DiaInhabilServiceTest {

	@InjectMocks
	private DiaInhabilService diaInhabilService = new DiaInhabilServiceImpl();

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
	public void t1_buscarByFecha(){
		when(diaInhabilRepository.findByFecha(any())).thenReturn(diaInhabil);
		CatalogoDiaInhabil respuesta = diaInhabilService.buscarByFecha( diaInhabil.getFecha() );
		assertNotNull(respuesta);
	}

	@Test
	public void t2_consultarByPropiedades() {
		List<DiaInhabilDTO> lista = new ArrayList<DiaInhabilDTO>(){{add(diaInhabilDTO);}};
		when(diaInhabilRepository.findByPropiedades(any(),anyString())).thenReturn(lista);
		List<DiaInhabilDTO> listaResultados = diaInhabilService.consultarByPropiedades(filtroDiaInhabilDTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void t3_saveDiaInhabil() {
		when(diaInhabilRepository.save(any())).thenReturn(diaInhabil);
		when(diaInhabilRepository.findByFecha(any())).thenReturn(diaInhabil);
		assertThrows(ConciliacionException.class, () -> diaInhabilService.saveDiaInhabil(diaInhabilDTO));
	}

	@Test
	public void t4_saveDiaInhabil() {
		when(diaInhabilRepository.save(any())).thenReturn(diaInhabil);
		when(diaInhabilRepository.findByFecha(any())).thenReturn(null);
		DiaInhabilDTO respuesta = diaInhabilService.saveDiaInhabil(diaInhabilDTO);
		assertNotNull(respuesta);
	}

}