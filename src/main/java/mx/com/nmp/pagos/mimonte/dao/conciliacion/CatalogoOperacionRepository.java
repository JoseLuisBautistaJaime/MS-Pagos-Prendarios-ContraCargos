/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CatalogoOperacion;

/**
 * @name CatalogoOperacionRepository
 * @description Interface que contiene metodos para realizar operaciones a nivel
 *              base de datos con el catalogo de operaciones
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 20/05/2019 13:18 hrs.
 * @version 0.1
 */
@Repository("catalogoOperacionRepository")
public interface CatalogoOperacionRepository extends JpaRepository<CatalogoOperacion, Integer> {
}
