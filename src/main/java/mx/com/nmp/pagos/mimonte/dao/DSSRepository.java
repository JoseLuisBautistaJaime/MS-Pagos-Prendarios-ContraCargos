/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.ReglaNegocio;

/**
 * Nombre: DSSRepository Descripcion: Interfaz que define las operaciones
 * encargadas de consultar informacion referente a DSS de la aplicacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate: 12/12/2018 17:21 hrs.
 * @version 0.1
 */
@Repository("dssRepository")
public interface DSSRepository extends JpaRepository<ReglaNegocio, Integer>, DSSRepositoryCustom {

	/**
	 * Metodo que obtiene el resultado de evaluar una regla de negocio con datos de
	 * variables especificas
	 */
	@SuppressWarnings("unchecked")
	@Query(value = "query", nativeQuery = true)
	public Object[] execQuery(String query) throws DataIntegrityViolationException;

}
