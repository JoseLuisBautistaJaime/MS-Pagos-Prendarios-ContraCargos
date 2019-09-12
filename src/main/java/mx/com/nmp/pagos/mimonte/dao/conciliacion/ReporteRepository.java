/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

/**
 * @name ReporteRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con los reportes.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 08/05/2019 21:55 hrs.
 * @version 0.1
 */
@Repository("reporteRepository")
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

	/**
	 * Búsqueda de los reportes a partir del id de conciliación.
	 * 
	 * @param idConciliacion
	 * @return
	 */
	@Query("FROM Reporte r WHERE r.conciliacion.id = :idConciliacion")
	public List<Reporte> findByIdConciliacion(@Param("idConciliacion") final Long idConciliacion);


	/**
	 * Búsqueda de los reportes a partir del id de conciliación y del tipo de reporte
	 * @param idConciliacion
	 * @return
	 */
	@Query(
		"FROM Reporte r " +
		"WHERE " +
			"r.conciliacion.id = :idConciliacion " +
			"AND r.tipo = :tipo " +
			"AND r.createdDate = (" +
				"SELECT " +
					"MAX(r1.createdDate) " +
				"FROM Reporte r1 " +
				"WHERE " +
					"r1.conciliacion.id = :idConciliacion " +
					"AND r1.tipo = :tipo" +
			")"
	)
	public Reporte findLastByIdConciliacionAndTipo(@Param("idConciliacion") final Long idConciliacion, @Param("tipo") TipoReporteEnum tipo);

}
