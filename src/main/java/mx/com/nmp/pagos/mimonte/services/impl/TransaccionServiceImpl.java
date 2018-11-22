package mx.com.nmp.pagos.mimonte.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.TransaccionBuilder;
import mx.com.nmp.pagos.mimonte.dao.TransaccionRepository;
import mx.com.nmp.pagos.mimonte.dto.TransaccionDTO;
import mx.com.nmp.pagos.mimonte.services.TransaccionService;

/**
 * Nombre: TransaccionServiceImpl Descripcion: Clase que implementa la operacion
 * que realiza registro de transacciones
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @Date: 20/11/2018 16:22 hrs.
 * @version 0.1
 */
@Service("transaccionServiceImpl")
public class TransaccionServiceImpl implements TransaccionService {

	@Autowired
	@Qualifier("transaccionRepository")
	TransaccionRepository transaccionRepository;

	@Override
	public TransaccionDTO saveTransaccion(TransaccionDTO transaccionDTO) {
		return TransaccionBuilder.buildTransaccionDTO(
				(transaccionRepository.save(TransaccionBuilder.buildTransaccionEntity(transaccionDTO))));
	}

}
