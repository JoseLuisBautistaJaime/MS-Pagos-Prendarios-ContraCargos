/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.model.AbstractCatalogoAdm;

/**
 * @name SubEstatusConciliacion
 * @description Clase que encapsula la informacion de una entidad de catalogo de
 *              sub estatus conciliacion
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 18/05/2019 13:08 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_sub_estatus_conciliacion")
public class SubEstatusConciliacion extends AbstractCatalogoAdm {
	
	public SubEstatusConciliacion() {
		super();
	}

	public SubEstatusConciliacion(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
	}

}
