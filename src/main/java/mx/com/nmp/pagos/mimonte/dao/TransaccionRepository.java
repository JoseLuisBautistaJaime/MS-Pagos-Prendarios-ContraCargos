package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Transaccion;

/**
 * Nombre: TransaccionRepository
 * Descripcion: Repositorio que realiza consultas referentes a las transacciones realizadas
 *
 * @author: Ismael Flores iaguilar@quarksoft.net
 * @creationDate 23/11/2018 10:44 hrs.
 * @version: 0.1
 */
@Repository("transaccionRepository")
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer>{
}
