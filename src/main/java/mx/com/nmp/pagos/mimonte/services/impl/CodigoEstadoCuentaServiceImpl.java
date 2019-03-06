package mx.com.nmp.pagos.mimonte.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import mx.com.nmp.pagos.mimonte.dao.CodigoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.services.CatalogoAdmService;

public class CodigoEstadoCuentaServiceImpl implements CatalogoAdmService<CodigoEstadoCuentaDTO> {

	@Autowired
	@Qualifier("codigoEstadoCuentaRepository")
	private CodigoEstadoCuentaRepository codigoEstadoCuentaRepository;

	@Override
	public <T extends AbstractCatalogoDTO> T save(CodigoEstadoCuentaDTO e) {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
//		codigoEstadoCuentaRepository.save(e);
		return null;
	}

	@Override
	public <T extends AbstractCatalogoDTO> T update(CodigoEstadoCuentaDTO e) {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
//		codigoEstadoCuentaRepository.save(e);
		return null;
	}

	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) {
		CodigoEstadoCuentaDTO codigoEstadoCuentaDTO = null;
		codigoEstadoCuentaRepository.findById(id);
		return null;
	}

}
