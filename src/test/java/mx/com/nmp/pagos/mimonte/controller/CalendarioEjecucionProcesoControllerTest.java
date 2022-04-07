package mx.com.nmp.pagos.mimonte.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.scheduling.ConciliacionEstadoCuentaScheduler;
import mx.com.nmp.pagos.mimonte.scheduling.ConciliacionLayoutsScheduler;
import mx.com.nmp.pagos.mimonte.scheduling.ConciliacionMidasProveedorScheduler;
import mx.com.nmp.pagos.mimonte.scheduling.PreconciliacionScheduler;
import mx.com.nmp.pagos.mimonte.services.conciliacion.CalendarioEjecucionProcesoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Prueba los endpoints definidos en {@link mx.com.nmp.pagos.mimonte.controllers.conciliacion.CalendarioEjecucionProcesoController}
 *
 * @author Quarksoft
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CalendarioEjecucionProcesoControllerTest {


    /**
     * Constante del endpoint base
     */
    private final String ENDPOINT_BASE = "/mimonte";

    /**
     * Referencia al {@link MockMvc}
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Referencia al mock del servicio {@link CalendarioEjecucionProcesoService}
     */
    @MockBean
    private CalendarioEjecucionProcesoService calendarioEjecucionProcesoService;

    @MockBean
    private PreconciliacionScheduler preconciliacionScheduler;

    @MockBean
    private ConciliacionMidasProveedorScheduler conciliacionMidasProveedorScheduler;

    @MockBean
    private ConciliacionEstadoCuentaScheduler conciliacionEstadoCuentaScheduler;

    @MockBean
    private ConciliacionLayoutsScheduler conciliacionLayoutsScheduler;


    /**
     * Referencia al {@link ObjectMapper}
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Datos de prueba
     */

    private CalendarioEjecucionProcesoRequestDTO calendarioEjecucionProcesoRequestDTO;

    private CalendarioEjecucionProceso calendarioEjecucionProceso;

    private FiltroCalendarioEjecucionProcesoDTO filtroCalendarioEjecucionProcesoDTO;

    private CalendarioEjecucionProcesoDTO calendarioEjecucionProcesoDTO;

    @BeforeEach
    public void setUp(){

        calendarioEjecucionProceso = this.crearElemento();
        doNothing().when(preconciliacionScheduler).lanzarPreconciliacionAutomatizada(any());
        doNothing().when(conciliacionMidasProveedorScheduler).lanzarConciliacionEtapa1(any());
        doNothing().when(conciliacionEstadoCuentaScheduler).lanzarConciliacionEtapa2(any());
        doNothing().when(conciliacionLayoutsScheduler).lanzarConciliacionEtapa3(any());

    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /calendarioejecucion/consulta
     */
    @Test
    public void t1_consultar() throws Exception  {

        filtroCalendarioEjecucionProcesoDTO = this.crearFiltro(calendarioEjecucionProceso);
        calendarioEjecucionProcesoDTO = this.crearElementoDTO(calendarioEjecucionProceso);
        List<CalendarioEjecucionProcesoDTO> listaResultado = new ArrayList<CalendarioEjecucionProcesoDTO>(){{add(calendarioEjecucionProcesoDTO);}};
        given(calendarioEjecucionProcesoService.consultarByPropiedades(any())).willReturn(listaResultado);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/calendarioejecucion/consulta")
                .content(objectMapper.writeValueAsString(filtroCalendarioEjecucionProcesoDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    Assertions.assertNotNull(mr.getResponse().getContentAsString());
                });

    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /calendarioejecucion/consulta
     */
    @Test
    public void t2_consultar() throws Exception  {

        filtroCalendarioEjecucionProcesoDTO = this.crearFiltro(calendarioEjecucionProceso);
        given(calendarioEjecucionProcesoService.consultarByPropiedades(any())).willReturn(null);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/calendarioejecucion/consulta")
                .content(objectMapper.writeValueAsString(filtroCalendarioEjecucionProcesoDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    Assertions.assertTrue(mr.getResponse().getContentLength() == 0);
                });

    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /calendarioejecucion/consulta
     */
    @Test
    public void t3_consultar() throws Exception  {

        filtroCalendarioEjecucionProcesoDTO = new FiltroCalendarioEjecucionProcesoDTO();
        given(calendarioEjecucionProcesoService.consultarByPropiedades(any())).willReturn(null);
        this.mockMvc.perform(post(ENDPOINT_BASE+"/calendarioejecucion/consulta")
                .content(objectMapper.writeValueAsString(filtroCalendarioEjecucionProcesoDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }


    /**
     * Prueba para verificar el funcionamiento del endpoint /calendarioejecucion
     */
    @Test
    public void t4_crearConfiguracion() throws Exception  {

        calendarioEjecucionProcesoRequestDTO = this.crearElementoRequestDTO(calendarioEjecucionProceso);
        given(calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(any(),any())).willReturn(calendarioEjecucionProcesoDTO);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/calendarioejecucion")
                .content(objectMapper.writeValueAsString(calendarioEjecucionProcesoRequestDTO))
                .header("requestUser", "user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    Assertions.assertNotNull(mr.getResponse().getContentAsString());
                });
    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /calendarioejecucion
     */
    @Test
    public void t5_crearConfiguracion() throws Exception  {

        calendarioEjecucionProcesoRequestDTO = this.crearElementoRequestDTO(calendarioEjecucionProceso);
        calendarioEjecucionProcesoRequestDTO.setIdCalendario(null);
        given(calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(any(),any())).willReturn(calendarioEjecucionProcesoDTO);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/calendarioejecucion")
                .content(objectMapper.writeValueAsString(calendarioEjecucionProcesoRequestDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .header("requestUser", "user")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    Assertions.assertNotNull(mr.getResponse().getContentAsString());
                });
    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /calendarioejecucion
     */
    @Test
    public void t6_crearConfiguracion() throws Exception  {

        calendarioEjecucionProcesoRequestDTO = new CalendarioEjecucionProcesoRequestDTO();
        given(calendarioEjecucionProcesoService.saveCalendarioEjecucionProceso(any(),any())).willReturn(calendarioEjecucionProcesoDTO);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/calendarioejecucion")
                .content(objectMapper.writeValueAsString(calendarioEjecucionProcesoRequestDTO))
                .header("requestUser", "user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private CalendarioEjecucionProcesoDTO crearElementoDTO(CalendarioEjecucionProceso elemento) {
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

    private FiltroCalendarioEjecucionProcesoDTO crearFiltro(CalendarioEjecucionProceso elemento) {
        FiltroCalendarioEjecucionProcesoDTO filtro = new FiltroCalendarioEjecucionProcesoDTO();
        filtro.setIdCalendario(elemento.getId());
        filtro.setIdProceso(elemento.getProceso().getId());
        filtro.setCorresponsal(elemento.getProveedor().getNombre().getNombre());
        filtro.setActivo(elemento.getActivo());
        filtro.setReintentos(elemento.getReintentos());
        return filtro;
    }

    private CalendarioEjecucionProceso crearElemento() {
        CalendarioEjecucionProceso elemento = new CalendarioEjecucionProceso();
        elemento.setId(1l);
        elemento.setProveedor(new Proveedor(CorresponsalEnum.OPENPAY));
        elemento.setProceso(new CatalogoProceso(ProcesoEnum.PRE_CONCILIACION.getIdProceso()));
        elemento.setRangoDiasCoberturaMax(1);
        elemento.setRangoDiasCoberturaMin(1);
        elemento.setActivo(true);
        elemento.setReintentos(1);
        elemento.setConfiguracion("0 0 0 31 DEC ?");
        return elemento;
    }

    private CalendarioEjecucionProcesoRequestDTO crearElementoRequestDTO(CalendarioEjecucionProceso elemento) {
        CalendarioEjecucionProcesoRequestDTO requestDTO = new CalendarioEjecucionProcesoRequestDTO();
        requestDTO.setIdCalendario(elemento.getId());
        requestDTO.setCorresponsal(elemento.getProveedor().getNombre().getNombre());
        requestDTO.setProceso(elemento.getProceso().getId());
        requestDTO.setRangoDiasCoberturaMax(elemento.getRangoDiasCoberturaMax());
        requestDTO.setRangoDiasCoberturaMin(elemento.getRangoDiasCoberturaMin());
        requestDTO.setActivo(elemento.getActivo());
        requestDTO.setReintentos(elemento.getReintentos());
        requestDTO.setConfiguracionAutomatizacion(elemento.getConfiguracion());
        return requestDTO;
    }

}
