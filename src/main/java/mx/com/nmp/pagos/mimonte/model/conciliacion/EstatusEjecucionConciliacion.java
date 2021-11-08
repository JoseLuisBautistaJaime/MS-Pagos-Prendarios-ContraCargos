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
 * @name EstatusEjecucionConciliacion
 * @description Clase que mapea el catalogo de EstatusEjecucionConciliacion
 * @date: 29/10/2021 13:05 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_estatus_ejecucion_conciliacion")
public class EstatusEjecucionConciliacion extends AbstractCatalogo {

    @Column(name = "order_number", nullable = true)
    private Integer orderNumber;

    public EstatusEjecucionConciliacion() {
        super();
    }

    public EstatusEjecucionConciliacion(Integer id, String descripcionCorta, String descripcion) {
        super(id, descripcionCorta, descripcion);
    }

    public EstatusEjecucionConciliacion(Integer id) {
        super(id);
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

}
