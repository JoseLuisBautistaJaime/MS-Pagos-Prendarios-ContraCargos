/**
 * Proyecto:        NMP - Microservicio Mi Monte Conciliacion
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.conector.ws.EstadoCuentaService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Referencia al servicio web Estado Cuenta
 *
 * @author jgalvez
 */
@Component
@Service("estadoCuentaAPI")
public class EstadoCuentaProxy implements EstadoCuentaAPI {

	private Logger LOGGER = LoggerFactory.getLogger(EstadoCuentaProxy.class);

	/**
	 * Referencia al conector hacia el Servicio Web OpenPay
	 */
	//@Inject
	private EstadoCuentaConector estadoCuentaConector;


	// METODOS

	/**
	 * {@inheritDoc}
	 */
	@Cacheable("EstadoCuentaProxy.consulta")
	@Override
	public List<String> consulta(String ruta, String archivo) {
		LOGGER.info(">> consulta({}, {})", ruta, archivo);

		// EstadoCuentaService estadoCuentaService = estadoCuentaConector.getReferenciaWsEstadoCuenta();

		// estadoCuentaService.xxxx();

		// Convertir a lineas
		List<String> lines = null;

		// Dummy
		try {
			ClassLoader classLoader = new EstadoCuentaProxy().getClass().getClassLoader();
			File file = new File(classLoader.getResource("edocuenta/7002.txt").getFile());
			lines = FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

}
