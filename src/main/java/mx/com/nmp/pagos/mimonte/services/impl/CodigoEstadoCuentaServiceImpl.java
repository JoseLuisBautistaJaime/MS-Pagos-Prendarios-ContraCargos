package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dao.CodigoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.CodigoEstadoCuentaDTO;
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
//@Service("codigoEstadoCuentaServiceImpl")
public class CodigoEstadoCuentaServiceImpl implements CatalogoAdmService<CodigoEstadoCuentaDTO> {

	/**
	 * Service de el catalogo CodigoEstadoCuenta
	 */
	@SuppressWarnings("unused")
//	@Autowired
//	@Qualifier("codigoEstadoCuentaRepository")
	private CodigoEstadoCuentaRepository codigoEstadoCuentaRepository;

	/**
	 * Guarda un nuevo catalogo CodigoEstadoCuenta
	 */
//	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(CodigoEstadoCuentaDTO e) {
//		CodigoEstadoCuentaGenDTO codigoEstadoCuentaDTO = null;
//		codigoEstadoCuentaRepository
//				.save(CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTO(e));
//		return (T) codigoEstadoCuentaDTO;
		return null;
	}

	/**
	 * Actualiza un catalogo CodigoEstadoCuenta
	 */
//	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(CodigoEstadoCuentaDTO e) {
//		CodigoEstadoCuentaGenDTO codigoEstadoCuentaDTO = null;
//		codigoEstadoCuentaRepository
//				.save(CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaFromCodigoEstadoCuentaDTO(e));
//		return (T) codigoEstadoCuentaDTO;
		return null;
	}

	/**
	 * Regresa un catalogo CodigoEstadoCuenta por su id
	 */
//	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) {
//		CodigoEstadoCuentaGenDTO codigoEstadoCuentaDTO = null;
//		codigoEstadoCuentaRepository.findById(id);
//		return (T) codigoEstadoCuentaDTO;
		return null;
	}

	/**
	 * Regresa una lista de catalogos CodigoEstadoCuenta por el id de una de sus
	 * entidades relacionadas
	 * 
	 * @param idEntidad
	 * @return
	 */
	public List<CodigoEstadoCuentaDTO> findByEntidad_Id(Long idEntidad) {
//		return CodigoEstadoCuentaBuilder.buildCodigoEstadoCuentaDTOListFromCodigoEstadoCuentaList(
//				codigoEstadoCuentaRepository.findByBaseEntidad_Id(idEntidad));
		return null;
	}

}
