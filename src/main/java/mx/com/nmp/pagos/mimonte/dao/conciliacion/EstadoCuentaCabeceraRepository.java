/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuentaCabecera;

/**
 * @name EstadoCuentaCabeceraRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con estado cuenta cabecera
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 08/05/2019 21:28 hrs.
 * @version 0.1
 */
@Repository("estadoCuentaCabeceraRepository")
public interface EstadoCuentaCabeceraRepository extends PagingAndSortingRepository<EstadoCuentaCabecera, Long> {

	@Query("SELECT ecc FROM EstadoCuenta ec INNER JOIN ec.cabecera ecc WHERE ec.id = :idEstadoCuenta")
	public EstadoCuentaCabecera findCabeceraByEstadoCuenta(@Param("idEstadoCuenta") Long idEstadoCuenta);

}