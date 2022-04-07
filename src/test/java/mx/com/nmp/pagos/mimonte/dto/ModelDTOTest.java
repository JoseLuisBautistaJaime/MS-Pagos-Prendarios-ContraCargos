/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @name ModelDTOTest
 * @description Clase de pruebas automatizadas para el proyecto Conciliacion
 *              correspondiente a los dto que se implementan
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 28/03/2022 15:35 hrs.
 * @version 0.1
 *
 */

@RunWith(SpringRunner.class)
public class ModelDTOTest {

	private CalendarioEjecucionProcesoDTO calendarioEjecucionProcesoDTO;
	private DiaInhabilDTO diaInhabilDTO;
	private EjecucionPreconciliacionDTO ejecucionPreconciliacionDTO;
	private EstatusEjecucionConciliacionDTO estatusEjecucionConciliacionDTO;
	private EstatusEjecucionPreconciliacionDTO estatusEjecucionPreconciliacionDTO;
	private ProcesoDTO procesoDTO;
	private TrazadoEjecucionConciliacionDTO trazadoEjecucionConciliacionDTO;
	private EjecucionConciliacionDTO ejecucionConciliacionDTO;
	private CalendarioEjecucionProcesoRequestDTO calendarioEjecucionProcesoRequestDTO;
	private ConciliacionEjecucionDTO conciliacionEjecucionDTO;
	private MontoLayoutConciliacionDTO montoLayoutConciliacionDTO;
	private ConsultaConciliacionEtapa2DTO consultaConciliacionEtapa2DTO;
	private DatosNotificacionDTO datosNotificacionDTO;

	@Before
	public void setUp() {
		diaInhabilDTO = crearDiaInhabilDTO();
		estatusEjecucionConciliacionDTO = crearEstatusEjecucionConciliacionDTO();
		estatusEjecucionPreconciliacionDTO = crearEstatusEjecucionPreconciliacionDTO();
		ejecucionConciliacionDTO = crearEjecucionConciliacionDTO();
		ejecucionPreconciliacionDTO = crearEjecucionPreconciliacionDTO();
		trazadoEjecucionConciliacionDTO =crearTrazadoEjecucionDTO();
		procesoDTO = crearCatalogoProcesoDTO();
		calendarioEjecucionProcesoDTO = crearCalendarioEjecucionProcesoDTO();
		conciliacionEjecucionDTO = crearConciliacionEjecucionDTO();
		montoLayoutConciliacionDTO = crearMontoLayoutConciliacionDTO();
		consultaConciliacionEtapa2DTO = crearConsultaConciliacionEtapa2DTO();
		datosNotificacionDTO = crearDatosNotificacionDTO();
		calendarioEjecucionProcesoRequestDTO = crearCalendarioEjecucionProcesoRequestDTO();
	}

	@Test
	public void t1_modelTest()  {

		ConciliacionDTO conciliacionDTO = new ConciliacionDTO();

		DiaInhabilDTO diaInhabilDTO2 = new DiaInhabilDTO(diaInhabilDTO.getId(), diaInhabilDTO.getDescripcionCorta(), diaInhabilDTO.getDescripcion(), diaInhabilDTO.getFecha());
		assertNotNull(diaInhabilDTO2);
		assertNotNull(diaInhabilDTO2.toString());
		DiaInhabilDTO diaInhabilDTO3 = new DiaInhabilDTO(diaInhabilDTO.getId());
		assertNotNull(diaInhabilDTO3);
		DiaInhabilDTO diaInhabilDTO4 = new DiaInhabilDTO( diaInhabilDTO.getId(), diaInhabilDTO.getDescripcionCorta(), diaInhabilDTO.getDescripcion(), diaInhabilDTO.getFecha());
		assertNotNull(diaInhabilDTO4);
		assertTrue(diaInhabilDTO.equals(diaInhabilDTO));
		assertFalse(diaInhabilDTO.equals(diaInhabilDTO3));
		assertFalse(diaInhabilDTO.equals(conciliacionDTO));

		EstatusEjecucionConciliacionDTO estatusEjecucionConciliacionDTO2 = new EstatusEjecucionConciliacionDTO(estatusEjecucionConciliacionDTO.getId(), estatusEjecucionConciliacionDTO.getDescripcionCorta(), estatusEjecucionConciliacionDTO.getDescripcion(), estatusEjecucionConciliacionDTO.getOrderNumber());
		assertNotNull(estatusEjecucionConciliacionDTO2);
		assertNotNull(estatusEjecucionConciliacionDTO2.toString());
		EstatusEjecucionConciliacionDTO estatusEjecucionConciliacionDTO3 = new EstatusEjecucionConciliacionDTO(estatusEjecucionConciliacionDTO.getId());
		assertNotNull(estatusEjecucionConciliacionDTO3);
		assertTrue(estatusEjecucionConciliacionDTO.equals(estatusEjecucionConciliacionDTO));
		assertFalse(estatusEjecucionConciliacionDTO.equals(estatusEjecucionConciliacionDTO3));
		assertFalse(estatusEjecucionConciliacionDTO.equals(conciliacionDTO));

		EstatusEjecucionPreconciliacionDTO estatusEjecucionPreconciliacionDTO2 = new EstatusEjecucionPreconciliacionDTO(estatusEjecucionPreconciliacionDTO.getId(), estatusEjecucionPreconciliacionDTO.getDescripcionCorta(), estatusEjecucionPreconciliacionDTO.getDescripcion(), estatusEjecucionPreconciliacionDTO.getOrderNumber());
		assertNotNull(estatusEjecucionPreconciliacionDTO2);
		assertNotNull(estatusEjecucionPreconciliacionDTO2.toString());
		EstatusEjecucionPreconciliacionDTO estatusEjecucionPreconciliacionDTO3 = new EstatusEjecucionPreconciliacionDTO(estatusEjecucionPreconciliacionDTO.getId());
		assertNotNull(estatusEjecucionPreconciliacionDTO3);
		assertTrue(estatusEjecucionPreconciliacionDTO.equals(estatusEjecucionPreconciliacionDTO));
		assertFalse(estatusEjecucionPreconciliacionDTO.equals(estatusEjecucionPreconciliacionDTO3));
		assertFalse(estatusEjecucionPreconciliacionDTO.equals(conciliacionDTO));

		EjecucionConciliacionDTO ejecucionConciliacionDTO2 = new EjecucionConciliacionDTO(ejecucionConciliacionDTO.getId(), ejecucionConciliacionDTO.getEstatus().getId(), ejecucionConciliacionDTO.getEstatus().getDescripcionCorta(), ejecucionConciliacionDTO.getEstatus().getDescripcion(), ejecucionConciliacionDTO.getEstatus().getOrderNumber(), ejecucionConciliacionDTO.getConciliacion().getFolioConciliacion(), ejecucionConciliacionDTO.getConciliacion().getFolio(), ejecucionConciliacionDTO.getFechaEjecucion(), ejecucionConciliacionDTO.getFechaPeriodoInicio(), ejecucionConciliacionDTO.getFechaPeriodoFin(), ejecucionConciliacionDTO.getCreatedBy(), ejecucionConciliacionDTO.getCreatedDate(), ejecucionConciliacionDTO.getLastModifiedDate(), ejecucionConciliacionDTO.getLastModifiedBy(), ejecucionConciliacionDTO.getCorresponsal());
		assertNotNull(ejecucionConciliacionDTO2);
		assertNotNull(ejecucionConciliacionDTO2.toString());
		EjecucionConciliacionDTO ejecucionConciliacionDTO3 = new EjecucionConciliacionDTO(ejecucionConciliacionDTO.getId(), ejecucionConciliacionDTO.getEstatus(),ejecucionConciliacionDTO.getEstatusDescripcion(), ejecucionConciliacionDTO.getConciliacion(), ejecucionConciliacionDTO.getFechaEjecucion(), ejecucionConciliacionDTO.getFechaPeriodoInicio(), ejecucionConciliacionDTO.getFechaPeriodoFin(), ejecucionConciliacionDTO.getCreatedBy(), ejecucionConciliacionDTO.getCreatedDate(), ejecucionConciliacionDTO.getLastModifiedDate(), ejecucionConciliacionDTO.getLastModifiedBy(), ejecucionConciliacionDTO.getCorresponsal());
		assertNotNull(ejecucionConciliacionDTO3);
		assertTrue(ejecucionConciliacionDTO.equals(ejecucionConciliacionDTO));
		assertFalse(ejecucionConciliacionDTO.equals(new EjecucionConciliacionDTO()));
		assertFalse(ejecucionConciliacionDTO.equals(conciliacionDTO));

		EjecucionPreconciliacionDTO ejecucionPreconciliacionDTO2 = new EjecucionPreconciliacionDTO(ejecucionPreconciliacionDTO.getId(), ejecucionPreconciliacionDTO.getEstatus(), ejecucionPreconciliacionDTO.getEstatusDescripcion(), ejecucionPreconciliacionDTO.getFechaEjecucion(), ejecucionPreconciliacionDTO.getFechaPeriodoInicio(), ejecucionPreconciliacionDTO.getFechaPeriodoFin(), ejecucionPreconciliacionDTO.getCreatedBy(), ejecucionPreconciliacionDTO.getCreatedDate(), ejecucionPreconciliacionDTO.getLastModifiedDate(), ejecucionPreconciliacionDTO.getLastModifiedBy(), ejecucionPreconciliacionDTO.getCorresponsal());
		assertNotNull(ejecucionPreconciliacionDTO2);
		assertNotNull(ejecucionPreconciliacionDTO2.toString());
		EjecucionPreconciliacionDTO ejecucionPreconciliacionDTO3 = new EjecucionPreconciliacionDTO(ejecucionPreconciliacionDTO.getId(), ejecucionPreconciliacionDTO.getEstatus(), ejecucionPreconciliacionDTO.getEstatusDescripcion(), ejecucionPreconciliacionDTO.getFechaEjecucion(), ejecucionPreconciliacionDTO.getFechaPeriodoInicio(), ejecucionPreconciliacionDTO.getFechaPeriodoFin(), ejecucionPreconciliacionDTO.getCreatedBy(), ejecucionPreconciliacionDTO.getCreatedDate(), ejecucionPreconciliacionDTO.getLastModifiedDate(), ejecucionPreconciliacionDTO.getLastModifiedBy(), ejecucionPreconciliacionDTO.getCorresponsal());
		assertNotNull(ejecucionPreconciliacionDTO3);
		assertTrue(ejecucionPreconciliacionDTO.equals(ejecucionPreconciliacionDTO));
		assertFalse(ejecucionPreconciliacionDTO.equals(new EjecucionPreconciliacionDTO()));
		assertFalse(ejecucionPreconciliacionDTO.equals(conciliacionDTO));

		TrazadoEjecucionConciliacionDTO trazadoEjecucionConciliacionDTO2 = new TrazadoEjecucionConciliacionDTO(trazadoEjecucionConciliacionDTO.getId(), trazadoEjecucionConciliacionDTO.getEstatusEjecucion(), trazadoEjecucionConciliacionDTO.getEjecucionConciliacion(), trazadoEjecucionConciliacionDTO.getEstatusDescripcion(), trazadoEjecucionConciliacionDTO.getFechaInicio(), trazadoEjecucionConciliacionDTO.getFechaFin());
		assertNotNull(trazadoEjecucionConciliacionDTO2);
		assertNotNull(trazadoEjecucionConciliacionDTO2.toString());
		assertTrue(trazadoEjecucionConciliacionDTO.equals(trazadoEjecucionConciliacionDTO));
		assertFalse(trazadoEjecucionConciliacionDTO.equals(new TrazadoEjecucionConciliacion()));
		assertFalse(trazadoEjecucionConciliacionDTO.equals(conciliacionDTO));

		ProcesoDTO procesoDTO2 = new ProcesoDTO(procesoDTO.getId(), procesoDTO.getDescripcionCorta(), procesoDTO.getDescripcion());
		assertNotNull(procesoDTO2);
		assertNotNull(procesoDTO2.toString());
		ProcesoDTO procesoDTO3 = new ProcesoDTO(procesoDTO.getId());
		assertNotNull(procesoDTO3);
		assertTrue(procesoDTO.equals(procesoDTO));
		assertFalse(procesoDTO.equals(procesoDTO3));
		assertFalse(procesoDTO.equals(conciliacionDTO));

		CalendarioEjecucionProcesoDTO calendarioEjecucionProcesoDTO2 = new CalendarioEjecucionProcesoDTO(calendarioEjecucionProcesoDTO.getId(), calendarioEjecucionProcesoDTO.getProceso().getId(), calendarioEjecucionProcesoDTO.getProceso().getDescripcionCorta(), calendarioEjecucionProcesoDTO.getProceso().getDescripcion(), calendarioEjecucionProcesoDTO.getConfiguracionAutomatizacion(), calendarioEjecucionProcesoDTO.getReintentos(), calendarioEjecucionProcesoDTO.getRangoDiasCoberturaMin(), calendarioEjecucionProcesoDTO.getRangoDiasCoberturaMax(), calendarioEjecucionProcesoDTO.getActivo(), calendarioEjecucionProcesoDTO.getCreatedBy(), calendarioEjecucionProcesoDTO.getCreatedDate(), calendarioEjecucionProcesoDTO.getLastModifiedDate(), calendarioEjecucionProcesoDTO.getLastModifiedBy(), calendarioEjecucionProcesoDTO.getCorresponsal());
		assertNotNull(calendarioEjecucionProcesoDTO2);
		assertNotNull(calendarioEjecucionProcesoDTO2.toString());
		assertTrue(calendarioEjecucionProcesoDTO.equals(calendarioEjecucionProcesoDTO));
		assertFalse(calendarioEjecucionProcesoDTO.equals(new CalendarioEjecucionProcesoDTO()));
		assertFalse(calendarioEjecucionProcesoDTO.equals(conciliacionDTO));

		CalendarioEjecucionProcesoRequestDTO calendarioEjecucionProcesoRequestDTO2 = new CalendarioEjecucionProcesoRequestDTO(calendarioEjecucionProcesoRequestDTO.getIdCalendario(), calendarioEjecucionProcesoRequestDTO.getProceso(), calendarioEjecucionProcesoRequestDTO.getConfiguracionAutomatizacion(), calendarioEjecucionProcesoRequestDTO.getReintentos(), calendarioEjecucionProcesoRequestDTO.getRangoDiasCoberturaMin(), calendarioEjecucionProcesoRequestDTO.getRangoDiasCoberturaMax(), calendarioEjecucionProcesoRequestDTO.getActivo(), calendarioEjecucionProcesoRequestDTO.getCorresponsal());
		assertNotNull(calendarioEjecucionProcesoRequestDTO2);
		assertNotNull(calendarioEjecucionProcesoRequestDTO2.toString());
		assertTrue(calendarioEjecucionProcesoRequestDTO.equals(calendarioEjecucionProcesoRequestDTO));
		assertFalse(calendarioEjecucionProcesoRequestDTO.equals(new CalendarioEjecucionProcesoRequestDTO()));
		assertFalse(calendarioEjecucionProcesoRequestDTO.equals(conciliacionDTO));

		ConciliacionEjecucionDTO conciliacionEjecucionDTO2 = new ConciliacionEjecucionDTO(conciliacionEjecucionDTO.getFolioConciliacion(),conciliacionEjecucionDTO.getFolio());
		assertNotNull(conciliacionEjecucionDTO2);
		assertNotNull(conciliacionEjecucionDTO2.toString());
		assertTrue(conciliacionEjecucionDTO.equals(conciliacionEjecucionDTO));
		assertFalse(conciliacionEjecucionDTO.equals(new ConciliacionEjecucionDTO()));
		assertFalse(conciliacionEjecucionDTO.equals(conciliacionDTO));

		MontoLayoutConciliacionDTO montoLayoutConciliacionDTO2 = new MontoLayoutConciliacionDTO(montoLayoutConciliacionDTO.getFolioConciliacion(), montoLayoutConciliacionDTO.getAcumulado(), montoLayoutConciliacionDTO.getTipoLayout());
		assertNotNull(montoLayoutConciliacionDTO2);
		assertNotNull(montoLayoutConciliacionDTO2.toString());
		assertTrue(montoLayoutConciliacionDTO.equals(montoLayoutConciliacionDTO2));
		assertFalse(montoLayoutConciliacionDTO.equals(new MontoLayoutConciliacionDTO()));
		assertFalse(montoLayoutConciliacionDTO.equals(conciliacionDTO));

		ConsultaConciliacionEtapa2DTO consultaConciliacionEtapa2DTO2 = new ConsultaConciliacionEtapa2DTO( new Date(), new Date(), consultaConciliacionEtapa2DTO.getListaSubEstatus(), consultaConciliacionEtapa2DTO.getCorresponsal());
		assertNotNull(consultaConciliacionEtapa2DTO2);
		assertNotNull(consultaConciliacionEtapa2DTO2.toString());
		assertTrue(consultaConciliacionEtapa2DTO.equals(consultaConciliacionEtapa2DTO));
		assertFalse(consultaConciliacionEtapa2DTO.equals(new ConsultaConciliacionEtapa2DTO()));
		assertFalse(consultaConciliacionEtapa2DTO.equals(conciliacionDTO));

		DatosNotificacionDTO datosNotificacionDTO2 = new DatosNotificacionDTO(datosNotificacionDTO.getFolio(), new Date(), new Date());
		assertNotNull(datosNotificacionDTO2);
		assertNotNull(datosNotificacionDTO2.toString());
		DatosNotificacionDTO datosNotificacionDTO3 = new DatosNotificacionDTO(datosNotificacionDTO.getFolio(), new Date(), new Date(), datosNotificacionDTO.getCorreponsal());
		assertNotNull(datosNotificacionDTO3);
		DatosNotificacionDTO datosNotificacionDTO4 = new DatosNotificacionDTO(datosNotificacionDTO.getFolio(), datosNotificacionDTO.getIdEntidad(), datosNotificacionDTO.getEntidad(), datosNotificacionDTO.getIdCuenta(), datosNotificacionDTO.getCuenta(), new Date(), new Date(), datosNotificacionDTO.getCorreponsal(), new Date(), new Date());
		assertNotNull(datosNotificacionDTO4);
		DatosNotificacionDTO datosNotificacionDTO5 = new DatosNotificacionDTO(datosNotificacionDTO.getFolio(), datosNotificacionDTO.getIdEntidad(), datosNotificacionDTO.getEntidad(), datosNotificacionDTO.getIdCuenta(), datosNotificacionDTO.getCuenta(), new Date(), new Date(), datosNotificacionDTO.getCorreponsal());
		assertNotNull(datosNotificacionDTO5);
		DatosNotificacionDTO datosNotificacionDTO6 = new DatosNotificacionDTO(datosNotificacionDTO.getFolio(), datosNotificacionDTO.getIdEntidad(), datosNotificacionDTO.getEntidad(), datosNotificacionDTO.getIdCuenta(), datosNotificacionDTO.getCuenta(), new Date(), new Date(), datosNotificacionDTO.getCorreponsal());
		assertNotNull(datosNotificacionDTO6);
		assertTrue(datosNotificacionDTO.equals(datosNotificacionDTO));
		assertFalse(datosNotificacionDTO.equals(datosNotificacionDTO3));
		assertFalse(datosNotificacionDTO.equals(conciliacionDTO));

	}

	private CalendarioEjecucionProcesoDTO crearCalendarioEjecucionProcesoDTO() {
		CalendarioEjecucionProcesoDTO elemento = new CalendarioEjecucionProcesoDTO();
		elemento.setId(1l);
		elemento.setCorresponsal(CorresponsalEnum.OPENPAY);
		elemento.setProceso(new ProcesoDTO(ProcesoEnum.CONCILIACION_ETAPA_1.getIdProceso()));
		elemento.setRangoDiasCoberturaMax(1);
		elemento.setRangoDiasCoberturaMin(1);
		elemento.setReintentos(1);
		elemento.setConfiguracionAutomatizacion("0 0 0 31 DEC ?");
		elemento.setActivo(Boolean.TRUE);
		return elemento;
	}

	private CalendarioEjecucionProcesoRequestDTO crearCalendarioEjecucionProcesoRequestDTO() {
		CalendarioEjecucionProcesoRequestDTO elemento = new CalendarioEjecucionProcesoRequestDTO();
		elemento.setCorresponsal(CorresponsalEnum.OPENPAY.getNombre());
		elemento.setProceso(ProcesoEnum.CONCILIACION_ETAPA_1.getIdProceso());
		elemento.setRangoDiasCoberturaMax(1);
		elemento.setRangoDiasCoberturaMin(1);
		elemento.setReintentos(1);
		elemento.setConfiguracionAutomatizacion("0 0 0 31 DEC ?");
		elemento.setActivo(Boolean.TRUE);
		return elemento;
	}

	private EjecucionConciliacionDTO crearEjecucionConciliacionDTO() {
		EjecucionConciliacionDTO elemento = new EjecucionConciliacionDTO();
		elemento.setId(1l);
		elemento.setFechaEjecucion(new Date());
		elemento.setFechaPeriodoInicio(new Date());
		elemento.setFechaPeriodoFin(new Date());
		elemento.setCorresponsal(CorresponsalEnum.OPENPAY);
		elemento.setEstatus(new EstatusEjecucionConciliacionDTO(EstatusEjecucionConciliacionEnum.CREADA.getIdEstadoEjecucion()));
		elemento.setConciliacion(new ConciliacionEjecucionDTO(1L,1L));
		elemento.setEstatusDescripcion("TEST");
		return elemento;
	}

	private EjecucionPreconciliacionDTO crearEjecucionPreconciliacionDTO() {
		EjecucionPreconciliacionDTO elemento = new EjecucionPreconciliacionDTO();
		elemento.setId(1l);
		elemento.setFechaEjecucion(new Date());
		elemento.setFechaPeriodoInicio(new Date());
		elemento.setFechaPeriodoFin(new Date());
		elemento.setCorresponsal(CorresponsalEnum.OPENPAY);
		elemento.setEstatus(new EstatusEjecucionPreconciliacionDTO(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getIdEstadoEjecucion()));
		elemento.setEstatusDescripcion(EstatusEjecucionPreconciliacionEnum.DESCARGACORRECTA.getEstadoEjecucion());
		return elemento;
	}

	private EstatusEjecucionPreconciliacionDTO crearEstatusEjecucionPreconciliacionDTO() {
		EstatusEjecucionPreconciliacionDTO elemento = new EstatusEjecucionPreconciliacionDTO();
		elemento.setId(1);
		elemento.setDescripcion("TEST");
		elemento.setDescripcionCorta("TEST");
		elemento.setOrderNumber(1);
		return elemento;
	}

	private ProcesoDTO crearCatalogoProcesoDTO() {
		ProcesoDTO elemento = new ProcesoDTO();
		elemento.setId(1);
		elemento.setDescripcion("TEST");
		elemento.setDescripcionCorta("TEST");
		return elemento;
	}

	private EstatusEjecucionConciliacionDTO crearEstatusEjecucionConciliacionDTO() {
		EstatusEjecucionConciliacionDTO elemento = new EstatusEjecucionConciliacionDTO();
		elemento.setId(1);
		elemento.setDescripcion("TEST");
		elemento.setDescripcionCorta("TEST");
		elemento.setOrderNumber(1);
		return elemento;
	}

	private TrazadoEjecucionConciliacionDTO crearTrazadoEjecucionDTO() {
		TrazadoEjecucionConciliacionDTO trazado = new TrazadoEjecucionConciliacionDTO();
		trazado.setId(1L);
		trazado.setFechaInicio(new Date());
		trazado.setFechaFin(new Date());
		trazado.setEjecucionConciliacion(new EjecucionConciliacionDTO());
		trazado.setEstatusEjecucion(new EstatusEjecucionConciliacionDTO());
		trazado.setEstatusDescripcion("TEST");
		return trazado;
	}

	private DiaInhabilDTO crearDiaInhabilDTO() {
		DiaInhabilDTO elemento = new DiaInhabilDTO();
		elemento.setId(1);
		elemento.setFecha(new Date());
		elemento.setDescripcion("TEST");
		elemento.setDescripcionCorta("TEST");
		return elemento;
	}

	private ConsultaConciliacionEtapa2DTO crearConsultaConciliacionEtapa2DTO() {
		ConsultaConciliacionEtapa2DTO elemento = new ConsultaConciliacionEtapa2DTO();
		elemento.setListaSubEstatus(new ArrayList<>());
		elemento.setFechaDesde("2022/04/06");
		elemento.setFechaHasta("2022/04/06");
		elemento.setCorresponsal("OPENPAY");
		return elemento;
	}

	private DatosNotificacionDTO crearDatosNotificacionDTO() {
		DatosNotificacionDTO elemento = new DatosNotificacionDTO();
		elemento.setIdEntidad(1L);
		elemento.setEntidad("TEST");
		elemento.setIdCuenta(1L);
		elemento.setCuenta("TEST");
		elemento.setFolio(1L);
		elemento.setCorreponsal("OPENPAY");
		elemento.setFechaInicio("2022/04/06");
		elemento.setFechaFin("2022/04/06");
		elemento.setFechaInicial("2022/04/06");
		elemento.setFechaFinal("2022/04/06");
		return elemento;
	}

	private MontoLayoutConciliacionDTO crearMontoLayoutConciliacionDTO() {
		MontoLayoutConciliacionDTO elemento = new MontoLayoutConciliacionDTO();
		elemento.setFolioConciliacion(1L);
		elemento.setTipoLayout("PAGOS");
		elemento.setAcumulado(0L);
		return elemento;
	}

	private ConciliacionEjecucionDTO crearConciliacionEjecucionDTO() {
		ConciliacionEjecucionDTO elemento = new ConciliacionEjecucionDTO();
		elemento.setFolioConciliacion(1L);
		elemento.setFolio(1L);
		return elemento;
	}


}