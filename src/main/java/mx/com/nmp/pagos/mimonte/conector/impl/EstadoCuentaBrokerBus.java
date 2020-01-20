/**
 * Proyecto:        NMP - Microservicio Mi Monte Conciliacion
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.conector.EstadoCuentaBroker;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusEstadoCuentaRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;

/**
 * Referencia al servicio web Estado Cuenta
 *
 * @author jgalvez
 */
@Component
@Service("estadoCuentaBrokerBus")
public class EstadoCuentaBrokerBus implements EstadoCuentaBroker {

	private Logger LOGGER = LoggerFactory.getLogger(EstadoCuentaBrokerBus.class);

	@Autowired
	private BusEstadoCuentaRestService busEstadoCuentaRestService;



	/**
	 * {@inheritDoc}
	 */
	//@Cacheable("EstadoCuentaBroker.consulta")
	@Override
	public List<String> consulta(String ruta, String archivo) throws ConciliacionException {
		LOGGER.info(">> consulta({}/{})", ruta, archivo);

		BusRestEstadoCuentaDTO body = new BusRestEstadoCuentaDTO(ruta, archivo);
		String edoCuentaStr = busEstadoCuentaRestService.consultaEstadoCuenta(body);

//		// Convertir a lineas
		List<String> lines = new ArrayList<String>();
		byte[] base64EstadoCuenta = Base64.decodeBase64(edoCuentaStr);
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new ByteArrayInputStream(base64EstadoCuenta)))) {
			while (reader.ready()) {
				String line = reader.readLine();
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ConciliacionException("Error al recibir el archivo del estado de cuenta",
					CodigoError.NMP_PMIMONTE_BUSINESS_063);
		}

		// Dummy
		/*try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new ClassPathResource("edocuenta/7002.txt").getInputStream()));
			while(reader.ready()) {
				String line = reader.readLine(); lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		return lines;
	}

}
