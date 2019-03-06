package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;

/**
 * Nombre: CodigoEstadoCuentaRepository Descripcion: Interface en donde se
 * realizan operaciones de base de datos relacionadas con el catalogo
 * CodigoEstadoCuenta
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 06/03/2019 12:42 hrs.
 * @version 0.1
 */
@Repository("codigoEstadoCuentaRepository")
public interface CodigoEstadoCuentaRepository extends JpaRepository<CodigoEstadoCuenta, Long> {

	/**
	 * Regresa un alista de catalogos CodigoEstadoCuenta en base a el id de una de
	 * sus entidades asociadas
	 * 
	 * @param idEntidad
	 * @return
	 */
	public List<CodigoEstadoCuenta> findByBaseEntidad_Id(Long idEntidad);

}
