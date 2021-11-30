/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import mx.com.nmp.pagos.mimonte.model.AbstractCatalogo;

import javax.persistence.*;
import java.util.Date;

/**
 * @name CatalogoDiaInhabil
 * @description Clase que mapea el catalogo de días inhábiles
 * @date: 24/11/2021 18:05 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_dia_inhabil")
public class CatalogoDiaInhabil extends AbstractCatalogo {

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    public CatalogoDiaInhabil() {
        super();
    }

    public CatalogoDiaInhabil(Integer id, String descripcionCorta, String descripcion) {
        super(id, descripcionCorta, descripcion);
    }

    public CatalogoDiaInhabil(Integer id) {
        super(id);
    }

    public CatalogoDiaInhabil(Integer id, String descripcionCorta, String descripcion, Date fecha) {
        super(id, descripcionCorta, descripcion);
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
