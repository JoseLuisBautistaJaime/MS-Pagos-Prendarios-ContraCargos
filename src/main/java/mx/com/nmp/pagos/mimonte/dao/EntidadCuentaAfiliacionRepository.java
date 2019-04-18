/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.EntidadCuentaAfiliacion;

/**
 * @name EntidadCuentaAfiliacionRepository
 * @description Interface de capa DAO que sirve para realizar operaciones de
 *              base de datos relacionadas con el objeto EntidadCuentaAfiliacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 18/04/2019 00:28 hrs.
 * @version 0.1
 */
@Repository("entidadCuentaAfiliacionRepository")
public interface EntidadCuentaAfiliacionRepository extends JpaRepository<EntidadCuentaAfiliacion, Long> {

	@Modifying
	public void dropEntidadCuentaAfiliacioneRelationship(@Param("idEntidad") Long idEntidad,
			@Param("idCuenta") Long idCuenta, @Param("idAfiliacion") Long idAfiliacion);

	/**
	 * Regresa una lista de EntidadCuentaAfiliacion por id de Entidad
	 * 
	 * @param idEntidad
	 * @return
	 */
	public List<EntidadCuentaAfiliacion> findByEntidad_Id(final Long idEntidad);

}
