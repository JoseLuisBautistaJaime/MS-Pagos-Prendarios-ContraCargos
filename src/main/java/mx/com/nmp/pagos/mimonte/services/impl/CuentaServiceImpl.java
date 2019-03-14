package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.CuentaBuilder;
import mx.com.nmp.pagos.mimonte.dao.CuentaRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaDTO;
import mx.com.nmp.pagos.mimonte.services.CatalogoAdmService;

/**
 * Nombre: CuentaServiceImpl Descripcion: Clase que implementa los metodos de
 * operaciones basicas sobre el catalogo de cuentas
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 13/03/2019 20:18 hrs.
 * @version 0.1
 */
@Service("cuentaServiceImpl")
public class CuentaServiceImpl implements CatalogoAdmService<CuentaBaseDTO> {

	/**
	 * Repository de Cuenta
	 */
	@Autowired
	@Qualifier("cuentaRepository")
	private CuentaRepository cuentaRepository;

	/**
	 * Guarda una cuenta
	 * 
	 * @param e
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(CuentaBaseDTO e) {
		return (T) CuentaBuilder
				.buildCuentaBaseDTOFromCuenta(cuentaRepository.save(CuentaBuilder.buildCuentaFromCuentaBaseDTO(e)));
	}

	/**
	 * Actualiza una cuenta
	 * 
	 * @param e
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(CuentaBaseDTO e) {
		return (T) CuentaBuilder
				.buildCuentaBaseDTOFromCuenta(cuentaRepository.save(CuentaBuilder.buildCuentaFromCuentaBaseDTO(e)));
	}

	/**
	 * Regresa una cuenta por id
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) {
		return (T) CuentaBuilder.buildCuentaBaseDTOFromCuenta(
				cuentaRepository.findById(id).isPresent() ? cuentaRepository.findById(id).get() : null);
	}

	/**
	 * Regresa una lista de objetos de tipo CuentaDTO en base a un id de entidad
	 * asociada
	 * 
	 * @param idEntidad
	 * @return
	 */
	public List<CuentaDTO> findByEntidadId(final Long idEntidad) {
		return CuentaBuilder.buildCuentaDTOListFromCuentaDTOList(cuentaRepository.findByEntidades_Id(idEntidad));
	}

}
