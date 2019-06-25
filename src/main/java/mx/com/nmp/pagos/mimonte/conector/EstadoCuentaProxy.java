/**
 * Proyecto:        NMP - Microservicio Mi Monte Conciliacion
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
		LOGGER.info(">> consulta({}{})", ruta, archivo);

		// EstadoCuentaService estadoCuentaService = estadoCuentaConector.getReferenciaWsEstadoCuenta();

		// estadoCuentaService.xxxx();

		// Convertir a lineas
		List<String> lines = new ArrayList<String>();

		// Dummy
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("edocuenta/7002.txt").getInputStream()))) {
			while(reader.ready()) {
			     String line = reader.readLine();
			     lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

}
