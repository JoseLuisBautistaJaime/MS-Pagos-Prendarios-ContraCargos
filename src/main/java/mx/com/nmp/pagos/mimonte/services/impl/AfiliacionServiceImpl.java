package mx.com.nmp.pagos.mimonte.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.AfiliacionBuilder;
import mx.com.nmp.pagos.mimonte.dao.AfiliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionDTO;
import mx.com.nmp.pagos.mimonte.services.CatalogoAdmService;

/**
 * Nombre: AfiliacionServiceImpl Descripcion: Clase de capa de servicio para el
 * catalogo de afiliaciones que sirve para realizar operaciones de logica de
 * negocios para el catalogo de afiliaciones
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 13/03/2019 20:58 hrs.
 * @version 0.1
 */
@Service("afiliacionServiceImpl")
public class AfiliacionServiceImpl implements CatalogoAdmService<AfiliacionDTO> {

	/**
	 * Repository para Afiliacion
	 */
	@Autowired
	@Qualifier("afiliacionRepository")
	private AfiliacionRepository afiliacionRepository;

	/**
	 * Guarda una Afiliacion
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(AfiliacionDTO e) {
		return (T) AfiliacionBuilder.buildAfiliacionDTOFromAfiliacion(
				afiliacionRepository.save(AfiliacionBuilder.buildAfiliacionFromAfiliacionDTO(e)));
	}

	/**
	 * Actualiza una afilicacion
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(AfiliacionDTO e) {
		return (T) AfiliacionBuilder.buildAfiliacionDTOFromAfiliacion(
				afiliacionRepository.save(AfiliacionBuilder.buildAfiliacionFromAfiliacionDTO(e)));
	}

	/**
	 * Regresa una afiliacion por id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) {
		return afiliacionRepository.findById(id).isPresent()
				? (T) AfiliacionBuilder.buildAfiliacionDTOFromAfiliacion(afiliacionRepository.findById(id).get())
				: null;
	}

	/**
	 * Regresa un objeto de tipo AfiliacionDTO a partir de un id de Cuenta asociada
	 * 
	 * @param idCuenta
	 * @return
	 */
	public AfiliacionDTO findByCuentasId(final Long idCuenta) {
		return AfiliacionBuilder.buildAfiliacionDTOFromAfiliacion(afiliacionRepository.findByCuentas_Id(idCuenta));
	}

}
