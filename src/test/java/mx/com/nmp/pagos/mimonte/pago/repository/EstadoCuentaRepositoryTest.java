/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.pago.repository;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import mx.com.nmp.pagos.mimonte.MimonteApplication;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.EstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuenta;
import javax.inject.Inject;
import static org.junit.Assert.*;

import java.util.Date;

/**
 * Clase de prueba para el repositorio RangoPesoDiamanteRepository.
 *
 * @author jgalvez
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MimonteApplication.class)
public class EstadoCuentaRepositoryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(EstadoCuentaRepositoryTest.class);

	/**
	 * Referencia al repositorio de estadoCuentaRepository.
	 */
	@Inject
	private EstadoCuentaRepository estadoCuentaRepository;



	// METODOS

	/**
	 * Constructor.
	 */
	public EstadoCuentaRepositoryTest() {
		super();
	}

	/**
	 * Configuración inicial.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	/**
	 * Utilizado para consultar el rango de peso de un diamante cuyas características son:
	 * QUILATES CT - SI EXISTE (se encuentra dentro del rango)
	 */
	@Test
	@Transactional
	//@Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
	public void saveEstadoCuentaTest() {
		
		LOGGER.debug("saveEstadoCuentaTest");
		
		EstadoCuenta estadoCuenta = new EstadoCuenta();
		estadoCuenta.setFechaCarga(new Date());
		estadoCuenta.setTotalMovimientos(100);
		EstadoCuenta result = estadoCuentaRepository.save(estadoCuenta);

		assertNotNull(result);
	}

}
