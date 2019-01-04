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
import mx.com.nmp.pagos.mimonte.TestUtil;
import mx.com.nmp.pagos.mimonte.controllers.TarjetasController;
import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.EstatusTarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoTarjetaDTO;
import mx.com.nmp.pagos.mimonte.services.TarjetasService;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MimonteApplication.class, properties="")
@ActiveProfiles("test")
public class TarjetasControllerTest {

	private static final Integer idCliente = 123;
	private static final String token = "12346";
	private static final String alias = "Prueba Test";
	private static final String tokenDelete = "12347";
	
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
	public void getClienteTest() throws Exception {
		tarjetasController.perform(get("/mimonte/v1/tarjetas/cliente/{idCliente}", idCliente))
		.andExpect(status().isOk())
		//.andReturn().getResponse().
		.andExpect(content().string("{\r\n" + 
				"  \"code\": \"200 OK\",\r\n" + 
				"  \"message\": \"Registros recuperados correctamente.\",\r\n" + 
				"  \"object\": [\r\n" + 
				"    {\r\n" + 
				"      \"token\": \"12346\",\r\n" + 
				"      \"digitos\": \"1234\",\r\n" + 
				"      \"alias\": \"prueba\",\r\n" + 
				"      \"fechaAlta\": \"2018-12-04T19:12:50.000+0000\",\r\n" + 
				"      \"fechaModificacion\": \"2018-12-17T22:42:24.000+0000\",\r\n" + 
				"      \"tipo\": {\r\n" + 
				"        \"id\": 1,\r\n" + 
				"        \"descripcionCorta\": \"visa\",\r\n" + 
				"        \"descripcion\": \"visa\"\r\n" + 
				"      },\r\n" + 
				"      \"estatus\": {\r\n" + 
				"        \"id\": 1,\r\n" + 
				"        \"descripcionCorta\": \"Activa\",\r\n" + 
				"        \"descripcion\": \"Activa\"\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"token\": \"12347\",\r\n" + 
				"      \"digitos\": \"1234\",\r\n" + 
				"      \"alias\": \"Prueba\",\r\n" + 
				"      \"fechaAlta\": \"2018-12-04T19:12:50.000+0000\",\r\n" + 
				"      \"fechaModificacion\": \"2018-12-06T04:21:38.000+0000\",\r\n" + 
				"      \"tipo\": {\r\n" + 
				"        \"id\": 1,\r\n" + 
				"        \"descripcionCorta\": \"visa\",\r\n" + 
				"        \"descripcion\": \"visa\"\r\n" + 
				"      },\r\n" + 
				"      \"estatus\": {\r\n" + 
				"        \"id\": 1,\r\n" + 
				"        \"descripcionCorta\": \"Activa\",\r\n" + 
				"        \"descripcion\": \"Activa\"\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}"));
	}
	
	/**
	 * Prueba recuperar tarjetas de un token
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Sql("/bd/test-tarjetas-h2.sql")
	public void getTokenTest() throws Exception {
		tarjetasController.perform(get("/mimonte/v1/tarjetas/{token}", token))
		.andExpect(status().isOk())
		.andExpect(content().string("{\r\n" + 
				"  \"code\": \"200 OK\",\r\n" + 
				"  \"message\": \"Registros recuperados correctamente.\",\r\n" + 
				"  \"object\": {\r\n" + 
				"    \"token\": \"12346\",\r\n" + 
				"    \"digitos\": \"1234\",\r\n" + 
				"    \"alias\": \"prueba\",\r\n" + 
				"    \"fechaAlta\": \"2018-12-04T19:12:50.000+0000\",\r\n" + 
				"    \"fechaModificacion\": \"2018-12-17T22:42:24.000+0000\",\r\n" + 
				"    \"tipo\": {\r\n" + 
				"      \"id\": 1,\r\n" + 
				"      \"descripcionCorta\": \"visa\",\r\n" + 
				"      \"descripcion\": \"visa\"\r\n" + 
				"    },\r\n" + 
				"    \"estatus\": {\r\n" + 
				"      \"id\": 1,\r\n" + 
				"      \"descripcionCorta\": \"Activa\",\r\n" + 
				"      \"descripcion\": \"Activa\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}"));
	}
	
	/**
	 * Prueba recuperar tarjetas con token y idCliente
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Sql("/bd/test-tarjetas-h2.sql")
	public void getTokenAndClienteTest() throws Exception {
		tarjetasController.perform(get("/mimonte/v1/tarjetas/{token}/{idCliente}", token , idCliente))
		.andExpect(status().isOk())
		.andExpect(content().string("{\r\n" + 
				"  \"code\": \"200 OK\",\r\n" + 
				"  \"message\": \"Registros recuperados correctamente.\",\r\n" + 
				"  \"object\": {\r\n" + 
				"    \"token\": \"12346\",\r\n" + 
				"    \"digitos\": \"1234\",\r\n" + 
				"    \"alias\": \"prueba\",\r\n" + 
				"    \"fechaAlta\": \"2018-12-04T19:12:50.000+0000\",\r\n" + 
				"    \"fechaModificacion\": \"2018-12-17T22:42:24.000+0000\",\r\n" + 
				"    \"tipo\": {\r\n" + 
				"      \"id\": 1,\r\n" + 
				"      \"descripcionCorta\": \"visa\",\r\n" + 
				"      \"descripcion\": \"visa\"\r\n" + 
				"    },\r\n" + 
				"    \"estatus\": {\r\n" + 
				"      \"id\": 1,\r\n" + 
				"      \"descripcionCorta\": \"Activa\",\r\n" + 
				"      \"descripcion\": \"Activa\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}"));
	}

	/**
	 * Prueba guardar tajetas nuevas 
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Sql("/bd/test-tarjetas-h2.sql")
	public void postTarjeta() throws Exception {
		TarjetaDTO tarjeDto = new TarjetaDTO();
		EstatusTarjetaDTO estatusTarjeta = new EstatusTarjetaDTO();
		TipoTarjetaDTO tipoTarjeta = new TipoTarjetaDTO();
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setIdCliente(1);
		clienteDTO.setNombreTitular("cliente test");
		estatusTarjeta.setId(1);
		estatusTarjeta.setDescripcion("Descripcion estatus Test");
		estatusTarjeta.setDescripcionCorta("Descripcion corta estatus Test");
		tipoTarjeta.setId(1);
		tipoTarjeta.setDescripcion("Descripcion tipo Test");
		tipoTarjeta.setDescripcionCorta("Descripcion corta tipo Test");
		tarjeDto.setToken("pruebaTest123");
		tarjeDto.setDigitos("1234");
		tarjeDto.setAlias("prueba Testing");
		tarjeDto.setTipo(tipoTarjeta);
		tarjeDto.setEstatus(estatusTarjeta);
		tarjeDto.setCliente(clienteDTO);
		tarjetasController.perform(post("/mimonte/v1/tarjeta")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(tarjeDto)))
				
		.andExpect(status().isOk())
		.andExpect(content().string("{\r\n" + 
				"  \"code\": \"200 OK\",\r\n" + 
				"  \"message\": \"Registro agregado correctamente.\",\r\n" + 
				"  \"object\": {\r\n" + 
				"    \"token\": \"pruebaTest123\",\r\n" + 
				"    \"ultimosDigitos\": \"1234\",\r\n" + 
				"    \"alias\": \"prueba Testing\",\r\n" + 
				"    \"fechaAlta\": \"2018-12-20T01:12:59.827+0000\",\r\n" + 
				"    \"fechaModificacion\": null,\r\n" + 
				"    \"tipoTarjeta\": {\r\n" + 
				"      \"id\": 1,\r\n" + 
				"      \"descripcionCorta\": \"visa\",\r\n" + 
				"      \"descripcion\": \"visa\"\r\n" + 
				"    },\r\n" + 
				"    \"estatusTarjeta\": {\r\n" + 
				"      \"id\": 1,\r\n" + 
				"      \"descripcionCorta\": \"Activa\",\r\n" + 
				"      \"descripcion\": \"Activa\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}"));
	}


	/**
	 * Prueba modificar alias de  tajetas  
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Sql("/bd/test-tarjetas-h2.sql")
	public void putTarjeta() throws Exception {
		tarjetasController.perform(put("/mimonte/v1/tarjeta/{token}/{alias}", token , alias))
		.andExpect(status().isOk())
		.andExpect(content().string("{\r\n" + 
				"  \"code\": \"200 OK\",\r\n" + 
				"  \"message\": \"Registro actualizado correctamente.\",\r\n" + 
				"  \"object\": {\r\n" + 
				"    \"token\": \"12346\",\r\n" + 
				"    \"ultimosDigitos\": \"1234\",\r\n" + 
				"    \"alias\": \"Prueba Test\",\r\n" + 
				"    \"fechaAlta\": \"2018-12-04T19:12:50.000+0000\",\r\n" + 
				"    \"fechaModificacion\": \"2018-12-20T01:03:31.294+0000\",\r\n" + 
				"    \"tipoTarjeta\": {\r\n" + 
				"      \"id\": 1,\r\n" + 
				"      \"descripcionCorta\": \"visa\",\r\n" + 
				"      \"descripcion\": \"visa\"\r\n" + 
				"    },\r\n" + 
				"    \"estatusTarjeta\": {\r\n" + 
				"      \"id\": 1,\r\n" + 
				"      \"descripcionCorta\": \"Activa\",\r\n" + 
				"      \"descripcion\": \"Activa\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}"));
	}
	
	/**
	 * Prueba eliminar tajetas  
	 * @throws Exception
	 */
	@Test
	@Transactional
	@Sql("/bd/test-tarjetas-h2.sql")
	public void deleteTarjetas() throws Exception {
		tarjetasController.perform(put("/mimonte/v1/tarjeta/{token}", tokenDelete))
		.andExpect(status().isOk())
		.andExpect(content().string("{\r\n" + 
				"  \"code\": \"200 OK\",\r\n" + 
				"  \"message\": \"Registro borrado correctamente.\",\r\n" + 
				"  \"object\": {\r\n" + 
				"    \"token\": \"12347\",\r\n" + 
				"    \"ultimosDigitos\": \"1234\",\r\n" + 
				"    \"alias\": \"Prueba\",\r\n" + 
				"    \"fechaAlta\": \"2018-12-04T19:12:50.000+0000\",\r\n" + 
				"    \"fechaModificacion\": \"2018-12-06T04:21:38.000+0000\",\r\n" + 
				"    \"tipoTarjeta\": {\r\n" + 
				"      \"id\": 1,\r\n" + 
				"      \"descripcionCorta\": \"visa\",\r\n" + 
				"      \"descripcion\": \"visa\"\r\n" + 
				"    },\r\n" + 
				"    \"estatusTarjeta\": {\r\n" + 
				"      \"id\": 1,\r\n" + 
				"      \"descripcionCorta\": \"Activa\",\r\n" + 
				"      \"descripcion\": \"Activa\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}"));
	}

	


}
