/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import mx.com.nmp.pagos.mimonte.model.AbstractCatalogo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @name CatalogoProceso
 * @description Clase que mapea el catalogo de procesos
 * @date: 19/11/2021 13:05 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_proceso")
public class CatalogoProceso extends AbstractCatalogo {

    public CatalogoProceso() {
        super();
    }

    public CatalogoProceso(Integer id, String descripcionCorta, String descripcion) {
        super(id, descripcionCorta, descripcion);
    }

    public CatalogoProceso(Integer id) {
        super(id);
    }


}
