/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.Categoria;

/**
 * @name CategoriaRepository
 * @description interface que implementa los metodos para ejecutar querys de
 *              base de datos sobre la entidad Categoria
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 26/03/2019 13:25 hrs.
 * @version 0.1
 */
@Repository("categoriaRepository")
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
