/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccionReal;

/**
 * @name ComisionTransaccionRealRepository
 * @description Interface de tipo repsotorio de spring que realiza operaciones a
 *              nivel base de datos sobre la entidad ComsionTransaccionReal
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 30/05/2019 14:36 hrs.
 * @version 0.1
 */
@Repository("comisionTransaccionRealRepository")
public interface ComisionTransaccionRealRepository extends JpaRepository<ComisionTransaccionReal, Long> {

	/**
	 * Regresa un set de ComisionTransaccionReal en base al id de su comision
	 * 
	 * @param idComision
	 * @return
	 */
	public Set<ComisionTransaccionReal> findByComisionTransaccion_Id(Long idComision);

}
