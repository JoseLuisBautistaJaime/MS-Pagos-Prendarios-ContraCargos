package mx.com.nmp.pagos.mimonte.controller;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionEjecucionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EjecucionConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstatusEjecucionConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroEjecucionConciliacionDTO;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.EjecucionConciliacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Prueba los endpoints definidos en {@link mx.com.nmp.pagos.mimonte.controllers.conciliacion.EjecucionConciliacionController}
 *
 * @author Quarksoft
 */
@SpringBootTest
@AutoConfigureMockMvc
public class EjecucionConciliacionControllerTest {


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
     * Referencia al mock del servicio {@link EjecucionConciliacionService}
     */
    @MockBean
    private EjecucionConciliacionService ejecucionConciliacionService;


    /**
     * Referencia al {@link ObjectMapper}
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Datos de prueba
     */

    private EjecucionConciliacion ejecucionConciliacion;

    private FiltroEjecucionConciliacionDTO filtroEjecucionConciliacionDTO;

    private EjecucionConciliacionDTO ejecucionConciliacionDTO;


     /**
     * Prueba para verificar el funcionamiento del endpoint /ejecucionconciliacion/consulta
     */
    @Test
    public void t1_consultar() throws Exception  {

        ejecucionConciliacion = this.crearElemento();
        filtroEjecucionConciliacionDTO = this.crearFiltro(ejecucionConciliacion);
        ejecucionConciliacionDTO = this.crearElementoDTO(ejecucionConciliacion);
        List<EjecucionConciliacionDTO> listaResultado = new ArrayList<EjecucionConciliacionDTO>(){{add(ejecucionConciliacionDTO);}};
        given(ejecucionConciliacionService.consultarByPropiedades(any())).willReturn(listaResultado);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/ejecucionconciliacion/consulta")
                .content(objectMapper.writeValueAsString(filtroEjecucionConciliacionDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    Assertions.assertNotNull(mr.getResponse().getContentAsString());
                });

    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /ejecucionconciliacion/consulta
     */
    @Test
    public void t2_consultar() throws Exception  {

        ejecucionConciliacion = this.crearElemento();
        filtroEjecucionConciliacionDTO = this.crearFiltro(ejecucionConciliacion);
        given(ejecucionConciliacionService.consultarByPropiedades(any())).willReturn(null);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/ejecucionconciliacion/consulta")
                .content(objectMapper.writeValueAsString(filtroEjecucionConciliacionDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    Assertions.assertTrue(mr.getResponse().getContentLength() == 0);
                });

    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /ejecucionconciliacion/consulta
     */
    @Test
    public void t3_consultar() throws Exception  {

        filtroEjecucionConciliacionDTO = new FiltroEjecucionConciliacionDTO();
        given(ejecucionConciliacionService.consultarByPropiedades(any())).willReturn(null);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/ejecucionconciliacion/consulta")
                .content(objectMapper.writeValueAsString(filtroEjecucionConciliacionDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }


    /**
     * Prueba para verificar el funcionamiento del endpoint /ejecucionconciliacion/consulta/{idEjecucion}
     */
    @Test
    public void t4_consultarByIdEjecucion() throws Exception  {

        ejecucionConciliacion = this.crearElemento();
        ejecucionConciliacionDTO = this.crearElementoDTO(ejecucionConciliacion);
        given(ejecucionConciliacionService.consultarByIdEjecucion(any())).willReturn(ejecucionConciliacionDTO);

        this.mockMvc.perform(get(ENDPOINT_BASE+"/ejecucionconciliacion/consulta/{idEjecucion}", ejecucionConciliacionDTO.getId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    Assertions.assertNotNull(mr.getResponse().getContentAsString());
                });

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

}
