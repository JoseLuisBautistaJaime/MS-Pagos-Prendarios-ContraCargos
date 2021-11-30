/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CatalogoDiaInhabil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @name DiaInhabilRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con los días inhábiles
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 24/11/2021 18:56 hrs.
 * @version 0.1
 */
@Repository("diaInhabilRepository")
public interface DiaInhabilRepository extends JpaRepository<CatalogoDiaInhabil, Integer>{

	/**
	 * Búsqueda del día inhábil a partir de la fecha.
	 * @param fecha
	 * @return
	 */
	@Query("FROM CatalogoDiaInhabil cd WHERE cd.fecha = :fecha")
	public CatalogoDiaInhabil findByFecha(@Param("fecha") final Date fecha);

}
