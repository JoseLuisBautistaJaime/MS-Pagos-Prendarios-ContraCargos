/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.sevice;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.ConciliacionService;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.ConciliacionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * @name ConciliacionServiceTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a el servicio ConciliacionService
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 12:55 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
public class ConciliacionServiceTest {

	@InjectMocks
	private ConciliacionService conciliacionService = new ConciliacionServiceImpl();

	@Mock
	private ConciliacionRepository conciliacionRepository;;

	private Conciliacion conciliacion;

	private ConsultaConciliacionEtapa2DTO filtroEtapa2DTO;

	private ConsultaConciliacionEtapa3DTO filtroEtapa3DTO;

	private MontoLayoutConciliacionDTO montoLayoutConciliacionDTO;

	@Before
	public void setUp(){

		conciliacion = new Conciliacion();
		conciliacion.setId(1L);
		conciliacion.setCreatedDate(new Date());
		conciliacion.setEntidad(new Entidad(11L,""));
		conciliacion.setCuenta(new Cuenta(1L, ""));
		conciliacion.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
		conciliacion.setEstatus(new EstatusConciliacion(1));
		conciliacion.setSubEstatus(new SubEstatusConciliacion(1L));

		filtroEtapa2DTO = new ConsultaConciliacionEtapa2DTO();
		filtroEtapa2DTO.setCorresponsal(conciliacion.getProveedor().getNombre().getNombre());
		filtroEtapa2DTO.setFechaDesde(conciliacion.getCreatedDate().toString());
		filtroEtapa2DTO.setFechaHasta(conciliacion.getCreatedDate().toString());
		filtroEtapa2DTO.setListaSubEstatus(new ArrayList<Long>(){{add(conciliacion.getSubEstatus().getId());}});

		filtroEtapa3DTO = new ConsultaConciliacionEtapa3DTO();
		filtroEtapa3DTO.setCorresponsal(conciliacion.getProveedor().getNombre().getNombre());
		filtroEtapa3DTO.setFechaDesde(conciliacion.getCreatedDate());
		filtroEtapa3DTO.setFechaHasta(conciliacion.getCreatedDate());
		filtroEtapa3DTO.setListaSubEstatus(new ArrayList<Long>(){{add(conciliacion.getSubEstatus().getId());}});

		montoLayoutConciliacionDTO = new MontoLayoutConciliacionDTO();
		montoLayoutConciliacionDTO.setAcumulado(0L);
		montoLayoutConciliacionDTO.setFolioConciliacion(1L);
		montoLayoutConciliacionDTO.setTipoLayout("PAGOS");


	}

	@Test
	public void getConciliacionSinEstadoCuenta() {
		List<BigInteger>  listaIdConciliaciones = new ArrayList<>();
		listaIdConciliaciones.add(BigInteger.valueOf(conciliacion.getId()));
		List<Conciliacion>  listaConciliaciones = new ArrayList<Conciliacion>(){{add(conciliacion);}};
		when(conciliacionRepository.findConciliacionSinEstadoCuenta(any(), any(), anyList(), any())).thenReturn(listaIdConciliaciones);
		when(conciliacionRepository.findByFolios(anyList())).thenReturn(listaConciliaciones);
		Conciliacion resultados = conciliacionService.getConciliacionSinEstadoCuenta(filtroEtapa2DTO);
		assertNotNull(resultados);
	}

	@Test
	public void getConciliacionSinLayout() {
		List<BigInteger>  listaIdConciliaciones = new ArrayList<BigInteger>(){{add(BigInteger.valueOf(conciliacion.getId()));}};
		List<Conciliacion>  listaConciliaciones = new ArrayList<Conciliacion>(){{add(conciliacion);}};
		when(conciliacionRepository.findConciliacionSinLayouts(any(), any(), anyList(), any())).thenReturn(listaIdConciliaciones);
		when(conciliacionRepository.findByFolios(anyList())).thenReturn(listaConciliaciones);
		List<Conciliacion> listaResultados = conciliacionService.getConciliacionSinLayout(filtroEtapa3DTO);
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}

	@Test
	public void calcularMontosLayoutsConciliacion() {
		List<Map<String, Object>> listaCalculos = new ArrayList<>();
		Map<String, Object> campo1 = new HashMap<String, Object>() {{
			put("id_conciliacion", BigInteger.valueOf(montoLayoutConciliacionDTO.getFolioConciliacion()));
			put("acumulado", BigDecimal.valueOf(montoLayoutConciliacionDTO.getAcumulado()));
			put("tipo", montoLayoutConciliacionDTO.getTipoLayout());
		}};
        listaCalculos.add(campo1);
		when(conciliacionRepository.calcularMontosLayoutsConciliacion(anyLong())).thenReturn(listaCalculos);
		List<MontoLayoutConciliacionDTO> listaResultados = conciliacionService.calcularMontosLayoutsConciliacion(conciliacion.getId());
		assertNotNull(listaResultados);
		assertTrue(!listaResultados.isEmpty());
	}


	@Test
	public void findProcesoByFolio() {
		when(conciliacionRepository.findProcesoByFolio(anyLong())).thenReturn(conciliacion);
		Conciliacion resultados = conciliacionService.findProcesoByFolio(conciliacion.getId());
		assertNotNull(resultados);
	}



}