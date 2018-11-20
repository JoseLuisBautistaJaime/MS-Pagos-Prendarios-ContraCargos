package mx.com.nmp.pagos.mimonte.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.dto.PagoDTO;
import mx.com.nmp.pagos.mimonte.services.PagoService;

/**
 * Nombre: PagoServiceImpl
 * Descripcion: Clase que implementa la operacion que realiza pagos de partidas
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 20/11/2018 16:22 hrs.
 * @version 0.1
 */
@Service("pagoServiceImpl")
public class PagoServiceImpl implements PagoService {

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(PagoServiceImpl.class);

	@Override
	public PagoDTO savePago(PagoDTO pagoDTO) {
		log.info("Ingreso al servicio de pago: POST");
		// Enviar peticion a BUS
		return null;
	}

}
