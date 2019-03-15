package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.CodigoEstadoCuentaBuilder;
import mx.com.nmp.pagos.mimonte.dao.CodigoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaGenDTO;
import mx.com.nmp.pagos.mimonte.services.CatalogoAdmService;

/**
 * Nombre: CodigoEstadoCuentaServiceImpl Descripcion: Clase implementa la clase
 * CatalgoAdmService y realiza operaciones de logica de negocios relacionadas
 * con el catalogo CodigoEstadoCuenta
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 06/03/2019 12:39 hrs.
 * @version 0.1
 */
@Service("codigoEstadoCuentaServiceImpl")
public class CodigoEstadoCuentaServiceImpl implements CatalogoAdmService<CodigoEstadoCuentaDTO> {

	/**
	 * Service de el catalogo CodigoEstadoCuenta
	 */
	@Autowired
	@Qualifier("codigoEstadoCuentaRepository")
	private CodigoEstadoCuentaRepository codigoEstadoCuentaRepository;

	/**
	 * Guarda un nuevo catalogo CodigoEstadoCuenta
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(CodigoEstadoCuentaDTO e, String createdBy) {
		if (null != e)
			e.setCreatedBy(createdBy);
		CodigoEstadoCuentaGenDTO codigoEstadoCuentaDTO = null;
		codigoEstadoCuentaRepository
				.save(CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTO(e));
		return (T) codigoEstadoCuentaDTO;

	}

	/**
	 * Actualiza un catalogo CodigoEstadoCuenta
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(CodigoEstadoCuentaDTO e, String lastModifiedBy) {
		if (null != e)
			e.setLastModifiedBy(lastModifiedBy);
		CodigoEstadoCuentaGenDTO codigoEstadoCuentaDTO = null;
		codigoEstadoCuentaRepository
				.save(CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTO(e));
		return (T) codigoEstadoCuentaDTO;
	}

	/**
	 * Regresa un catalogo CodigoEstadoCuenta por su id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) {
		CodigoEstadoCuentaGenDTO codigoEstadoCuentaDTO = null;
		codigoEstadoCuentaRepository.findById(id);
		return (T) codigoEstadoCuentaDTO;
	}

	/**
	 * Regresa una lista de catalogos CodigoEstadoCuenta por el id de una de sus
	 * entidades relacionadas
	 * 
	 * @param idEntidad
	 * @return
	 */
	public List<CodigoEstadoCuentaDTO> findByEntidades_Id(Long idEntidad) {
		return CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaDTOListFromCodigoEstadoCuentaList(
				codigoEstadoCuentaRepository.findByEntidades_Id(idEntidad));
	}

}
