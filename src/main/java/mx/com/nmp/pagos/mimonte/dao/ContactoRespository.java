package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Catalogo;
import mx.com.nmp.pagos.mimonte.model.Contactos;

/**
 * Nombre: ContactoRespository
 * Descripcion: Repositorio que realiza las consultas sobre la entidad {@link Contacto}
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net
 * Fecha: 05/03/2019 15:33 hrs.
 * @version 0.1
 */
@Repository
public interface ContactoRespository extends JpaRepository<Contactos, Long>{

}
