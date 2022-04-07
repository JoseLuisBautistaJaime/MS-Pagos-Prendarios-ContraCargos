package mx.com.nmp.pagos.mimonte.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.EjecucionPreconciliacionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Prueba los endpoints definidos en {@link mx.com.nmp.pagos.mimonte.controllers.conciliacion.EjecucionPreconciliacionController}
 *
 * @author Quarksoft
 */
@SpringBootTest
@AutoConfigureMockMvc
public class EjecucionPreconciliacionControllerTest {


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
     * Referencia al mock del servicio {@link EjecucionPreconciliacionService}
     */
    @MockBean
    private EjecucionPreconciliacionService ejecucionPreconciliacionService;


    /**
     * Referencia al {@link ObjectMapper}
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Datos de prueba
     */

    private EjecucionPreconciliacion ejecucionPreconciliacion;

    private FiltroEjecucionPreconciliacionDTO filtroEjecucionPreconciliacionDTO;

    private EjecucionPreconciliacionDTO ejecucionPreconciliacionDTO;


     /**
     * Prueba para verificar el funcionamiento del endpoint /ejecucionpreconciliacion/consulta
     */
    @Test
    public void t1_consultar() throws Exception  {

        ejecucionPreconciliacion =this.crearElemento();
        filtroEjecucionPreconciliacionDTO = this.crearFiltro(ejecucionPreconciliacion);
        ejecucionPreconciliacionDTO = this.crearElementoDTO(ejecucionPreconciliacion);
        List<EjecucionPreconciliacionDTO> listaResultado = new ArrayList<EjecucionPreconciliacionDTO>(){{add(ejecucionPreconciliacionDTO);}};
        given(ejecucionPreconciliacionService.consultarByPropiedades(any())).willReturn(listaResultado);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/ejecucionpreconciliacion/consulta")
                .content(objectMapper.writeValueAsString(filtroEjecucionPreconciliacionDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    Assertions.assertNotNull(mr.getResponse().getContentAsString());
                });

    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /ejecucionpreconciliacion/consulta
     */
    @Test
    public void t2_consultar() throws Exception  {

        ejecucionPreconciliacion = this.crearElemento();
        filtroEjecucionPreconciliacionDTO = this.crearFiltro(ejecucionPreconciliacion);
        given(ejecucionPreconciliacionService.consultarByPropiedades(any())).willReturn(null);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/ejecucionpreconciliacion/consulta")
                .content(objectMapper.writeValueAsString(filtroEjecucionPreconciliacionDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    Assertions.assertTrue(mr.getResponse().getContentLength() == 0);
                });

    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /ejecucionpreconciliacion/consulta
     */
    @Test
    public void t3_consultar() throws Exception  {

        filtroEjecucionPreconciliacionDTO = new FiltroEjecucionPreconciliacionDTO();
        given(ejecucionPreconciliacionService.consultarByPropiedades(any())).willReturn(null);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/ejecucionpreconciliacion/consulta")
                .content(objectMapper.writeValueAsString(filtroEjecucionPreconciliacionDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    private EjecucionPreconciliacion crearElemento() {
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

    private EjecucionPreconciliacionDTO crearElementoDTO(EjecucionPreconciliacion elemento) {
        EjecucionPreconciliacionDTO elementoDTO = new EjecucionPreconciliacionDTO();
        elementoDTO.setId(elemento.getId());
        elementoDTO.setCorresponsal(elemento.getProveedor().getNombre());
        elementoDTO.setEstatus(new EstatusEjecucionPreconciliacionDTO(elemento.getEstatus().getId()));
        elementoDTO.setEstatusDescripcion(elemento.getEstatusDescripcion());
        elementoDTO.setFechaEjecucion(elemento.getFechaEjecucion());
        elementoDTO.setFechaPeriodoInicio(elemento.getFechaPeriodoInicio());
        elementoDTO.setFechaPeriodoFin(elemento.getFechaPeriodoFin());
        return elementoDTO;
    }

    private FiltroEjecucionPreconciliacionDTO crearFiltro(EjecucionPreconciliacion elemento) {
        FiltroEjecucionPreconciliacionDTO filtro = new FiltroEjecucionPreconciliacionDTO();
        filtro.setFechaPeriodoInicio(elemento.getFechaPeriodoInicio());
        filtro.setFechaPeriodoFin(elemento.getFechaPeriodoFin());
        filtro.setCorresponsal(elemento.getProveedor().getNombre().getNombre());
        filtro.setIdEstatus(elemento.getEstatus().getId());
        filtro.setFechaEjecucionDesde(elemento.getFechaEjecucion());
        filtro.setFechaEjecucionHasta(elemento.getFechaEjecucion());
        filtro.setEstatusDescripcion(elemento.getEstatusDescripcion());
        return filtro;
    }

}
