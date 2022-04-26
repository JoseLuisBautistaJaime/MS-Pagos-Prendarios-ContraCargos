package mx.com.nmp.pagos.mimonte.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.BayonetReglas;

/**
 * Nombre: BayonetReglasRepository Descripcion: Repositorio que realiza consultas
 * referentes a las reglas regresadas por bayonet
 *
 * @author: Felix Manuel Galicia fmgalicia@quarksoft.net
 * @creationDate 21/04/2022 20:02 hrs.
 * @version: 0.1
 */

@Repository
public interface BayonetReglasRepository extends JpaRepository<BayonetReglas, Long> {

	Optional<BayonetReglas> findByStatus(String status);
}
