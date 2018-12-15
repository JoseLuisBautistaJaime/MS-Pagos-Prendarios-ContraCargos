package mx.com.nmp.pagos.mimonte.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import mx.com.nmp.pagos.mimonte.MimonteApplication;
import mx.com.nmp.pagos.mimonte.controllers.TarjetasController;
import mx.com.nmp.pagos.mimonte.services.TarjetasService;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MimonteApplication.class, properties="")
@ActiveProfiles("test")
public class TarjetasControllerTest {

	private static final Long idCliente = 11111L;
	

	@Autowired
	private TarjetasService tarjetasService;

	@Autowired
	BeanFactory beanFactory;

	private MockMvc tarjetasController;


	@PostConstruct
	public void setup() {
		MockitoAnnotations.initMocks(this);
		TarjetasController tpc = new TarjetasController();
		ReflectionTestUtils.setField(tpc, "tarjetasService", tarjetasService);
		ReflectionTestUtils.setField(tpc, "beanFactory", beanFactory);

		tarjetasController = MockMvcBuilders.standaloneSetup(tpc).build();
	}


	/**
	 * Prueba recuperar tarjetas de un cliente
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Sql("/bd/test-tarjetas-h2.sql")
	public void getTest() throws Exception {
		tarjetasController.perform(get("/mimonte/v1/tarjetas/cliente/{idCliente}", idCliente))
		.andExpect(status().isOk())
		//.andReturn().getResponse().
		.andExpect(content().string("{\"code\":\"200 OK\",\"message\":\"Registros recuperados correctamente.\",\"object\":[{\"token\":\"TOKEN\",\"digitos\":\"1234\",\"alias\":\"Alias\",\"fechaAlta\":1544767200000,\"fechaModificacion\":1544767200000,\"tipo\":{\"id\":1,\"descripcionCorta\":\"Tarjeta corta\",\"descripcion\":\"Tarjeta\"},\"estatus\":{\"id\":1,\"descripcionCorta\":\"Estatus corta\",\"descripcion\":\"Estatus\"}}]}"));
	}

}
