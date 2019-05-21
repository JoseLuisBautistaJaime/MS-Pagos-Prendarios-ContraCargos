/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CatalogoTipoContrato;

/**
 * @name CatalogoTipoContratoRepository
 * @description Interface que contiene metodos para realizar operaciones a nivel
 *              base de datos con el catalogo de tipo de contrato
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 20/05/2019 13:19 hrs.
 * @version 0.1
 */
@Repository("catalogoTipoContratoRepository")
public interface CatalogoTipoContratoRepository extends JpaRepository<CatalogoTipoContrato, Integer> {
}
