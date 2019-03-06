package mx.com.nmp.pagos.mimonte.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadDTO;
import mx.com.nmp.pagos.mimonte.services.CatalogoAdmService;

@Service("entidadServiceImpl")
public class EntidadServiceImpl implements CatalogoAdmService<EntidadDTO> {

	@Autowired
	@Qualifier("entidadRepository")
	private EntidadRepository entidadRepository;

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(EntidadDTO e) {
		EntidadDTO entidadDTO = null;
//		entidadRepository.save(e);
		return (T) entidadDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(EntidadDTO e) {
		EntidadDTO entidadDTO = null;
//		entidadRepository.save(e);
		return (T) entidadDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) {
		EntidadDTO entidadDTO = null;
		entidadRepository.findById(id);
		return (T) entidadDTO;
	}

	public EntidadDTO findByNombreAndEstatus(String nombre, Boolean status) {
		EntidadDTO entidadDTO = null;
		entidadRepository.findByNombreAndStatus(nombre, status);
		return entidadDTO;
	}

}
