package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.dao.BayonetReglasRepository;
import mx.com.nmp.pagos.mimonte.dto.BayonetReglasDTO;
import mx.com.nmp.pagos.mimonte.model.BayonetReglas;
import mx.com.nmp.pagos.mimonte.services.BayonetReglasService;

/**
 * Nombre: ReglasBayonetServiceImpl Descripcion: Clase que implementa las operaciones relacionadas
 * a la respuyesta de bayonet
 *
 * @author Felix Mauel Galicia fmgalicia@quarksoft.net
 * @creationDate: 21/04/2022 20:15 hrs.
 * @version 0.1
 */

@Service
public class BayonetReglasServiceImpl implements BayonetReglasService{

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BayonetReglasServiceImpl.class);

	/**
	 * Repository de BayonetReglasRepository
	 */
	@Autowired
	private BayonetReglasRepository bayonetReglasRepository;
	
	
	@Override
	public BayonetReglasDTO getReglasBayonetByStatus(String status) {
		LOG.debug("Llega la respuesta de bayonet a BayonetReglasServiceImpl: " + status);
		
		Optional<BayonetReglas>  bayonetReglas = bayonetReglasRepository.findByStatus(status);
		if (!bayonetReglas.isPresent())
			return null;
		else {
			BayonetReglasDTO  bayonetReglasDTO = new BayonetReglasDTO();
			bayonetReglasDTO.setReglaAutorizacion(bayonetReglas.get().getReglaAutorizacion());
			return bayonetReglasDTO;
		}
	}
}
