package mx.com.nmp.pagos.mimonte.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.EntidadBuilder;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadResponseDTO;
import mx.com.nmp.pagos.mimonte.services.CatalogoAdmService;

/**
 * Nombre: EntidadServiceImpl Descripcion: Clase de capa de servicio para el
 * catalogo de entidades que sirve para realizar operaciones de logica de
 * negocios para el catalogo de entidades
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/03/2019 12:33 hrs.
 * @version 0.1
 */
@Service("entidadServiceImpl")
public class EntidadServiceImpl implements CatalogoAdmService<EntidadDTO> {

	/**
	 * Repository del catalogo Entidad
	 */
	@Autowired
	@Qualifier("entidadRepository")
	private EntidadRepository entidadRepository;

	/**
	 * Guarda una entidad
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(EntidadDTO e, String createdBy) {
		if (null != e)
			e.setCreatedBy(createdBy);
		return (T) EntidadBuilder
				.buildEntidadDTOFromEntidad(entidadRepository.save(EntidadBuilder.buildEntidadFromEntidadDTO(e)));
	}

	/**
	 * Actualiza una entidad
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(EntidadDTO e, String lastModifiedBy) {
		if (null != e)
			e.setLastModifiedBy(lastModifiedBy);
		return (T) EntidadBuilder
				.buildEntidadDTOFromEntidad(entidadRepository.save(EntidadBuilder.buildEntidadFromEntidadDTO(e)));
	}

	/**
	 * Encuentra una entidad por id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) {
		return (T) EntidadBuilder.buildEntidadDTOFromEntidad(
				entidadRepository.findById(id).isPresent() ? entidadRepository.findById(id).get() : null);
	}

	/**
	 * Encuentra una entidad por su nombre y estatus
	 * 
	 * @param nombre
	 * @param estatus
	 * @return
	 */
	public EntidadResponseDTO findByNombreAndEstatus(final String nombre, final Boolean estatus) {
		return EntidadBuilder
				.buildEntidadResponseDTOFromEntidad(entidadRepository.findByNombreAndEstatus(nombre, estatus));
	}

}
