package mx.com.nmp.pagos.mimonte.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.*;
import mx.com.nmp.pagos.mimonte.model.conciliacion.*;
import mx.com.nmp.pagos.mimonte.services.conciliacion.DiaInhabilService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
 * Prueba los endpoints definidos en {@link mx.com.nmp.pagos.mimonte.controllers.conciliacion.DiaInhabilController}
 *
 * @author Quarksoft
 */
@SpringBootTest
@AutoConfigureMockMvc
public class DiaInhabilControllerTest {


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
     * Referencia al mock del servicio {@link DiaInhabilService}
     */
    @MockBean
    private DiaInhabilService diaInhabilService;


    /**
     * Referencia al {@link ObjectMapper}
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Datos de prueba
     */

    private CatalogoDiaInhabil diaInhabil;

    private FiltroDiaInhabilDTO filtroDiaInhabilDTO;

    private DiaInhabilDTO diaInhabilDTO;

    @BeforeEach
    public void setUp(){

        diaInhabil = new CatalogoDiaInhabil();
        diaInhabil.setId(1);
        diaInhabil.setFecha(new Date());
        diaInhabil.setDescripcion("TEST");
        diaInhabil.setDescripcionCorta("TEST");

    }

    private DiaInhabilDTO crearElementoDTO(CatalogoDiaInhabil elemento) {
        DiaInhabilDTO elementoDTO = new DiaInhabilDTO();
        elementoDTO.setId(elemento.getId());
        elementoDTO.setFecha(elemento.getFecha());
        elementoDTO.setDescripcion(elemento.getDescripcion());
        elementoDTO.setDescripcionCorta(elemento.getDescripcionCorta());
        return elementoDTO;
    }

    private FiltroDiaInhabilDTO crearFiltro(CatalogoDiaInhabil elemento) {
        FiltroDiaInhabilDTO filtro = new FiltroDiaInhabilDTO();
        filtro.setFecha(elemento.getFecha());
        filtro.setDescripcion(elemento.getDescripcion());
        return filtro;
    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /diainhabil/consulta
     */
    @Test
    public void t1_consultar() throws Exception  {

        filtroDiaInhabilDTO= this.crearFiltro(diaInhabil);
        diaInhabilDTO= this.crearElementoDTO(diaInhabil);

        List<DiaInhabilDTO> listaResultado = new ArrayList<DiaInhabilDTO>(){{add(diaInhabilDTO);}};
        given(diaInhabilService.consultarByPropiedades(any())).willReturn(listaResultado);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/diainhabil/consulta")
                .content(objectMapper.writeValueAsString(filtroDiaInhabilDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    Assertions.assertNotNull(mr.getResponse().getContentAsString());
                });

    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /diainhabil/consulta
     */
    @Test
    public void t2_consultar() throws Exception  {

        filtroDiaInhabilDTO= this.crearFiltro(diaInhabil);
        given(diaInhabilService.consultarByPropiedades(any())).willReturn(null);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/diainhabil/consulta")
                .content(objectMapper.writeValueAsString(filtroDiaInhabilDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    Assertions.assertTrue(mr.getResponse().getContentLength() == 0);
                });

    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /diainhabil/consulta
     */
    @Test
    public void t3_consultar() throws Exception  {

        filtroDiaInhabilDTO= new FiltroDiaInhabilDTO();
        given(diaInhabilService.consultarByPropiedades(any())).willReturn(null);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/diainhabil/consulta")
                .content(objectMapper.writeValueAsString(filtroDiaInhabilDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }


    /**
     * Prueba para verificar el funcionamiento del endpoint /diainhabil
     */
    @Test
    public void t4_saveDiaInhabil() throws Exception  {

        diaInhabilDTO= this.crearElementoDTO(diaInhabil);
        given(diaInhabilService.saveDiaInhabil(any())).willReturn(diaInhabilDTO);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/diainhabil")
                .content(objectMapper.writeValueAsString(diaInhabilDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    Assertions.assertNotNull(mr.getResponse().getContentAsString());
                });
    }

    /**
     * Prueba para verificar el funcionamiento del endpoint /diainhabil
     */
    @Test
    public void t5_saveDiaInhabil() throws Exception  {

        diaInhabilDTO= new DiaInhabilDTO();
        given(diaInhabilService.saveDiaInhabil(any())).willReturn(diaInhabilDTO);

        this.mockMvc.perform(post(ENDPOINT_BASE+"/diainhabil")
                .content(objectMapper.writeValueAsString(diaInhabilDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


}
