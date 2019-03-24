/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Nombre: EntradaCatalogo
 * Descripcion: Clase que mantiene la informacion generica sobre un registro perteneciente a un catalogo
 *
 * @author Javier Hernandez jhernandez@quarksoft.net
 * Fecha: 07/02/2018 07:31 PM
 * @version 0.1
 */
public class EntradaCatalogo implements Serializable {
    private int id;
    private String descripcionCorta;
    private String descripcion;

    public EntradaCatalogo() {
    }

    public EntradaCatalogo(int id, String descripcionCorta, String descripcion) {
        this.id = id;
        this.descripcionCorta = descripcionCorta;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntradaCatalogo)) return false;
        EntradaCatalogo that = (EntradaCatalogo) o;
        return getId() == that.getId() &&
                Objects.equals(getDescripcionCorta(), that.getDescripcionCorta()) &&
                Objects.equals(getDescripcion(), that.getDescripcion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescripcionCorta(), getDescripcion());
    }

    @Override
    public String toString() {
        return "EntradaCatalogo{" +
                "id=" + id +
                ", descripcionCorta='" + descripcionCorta + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
