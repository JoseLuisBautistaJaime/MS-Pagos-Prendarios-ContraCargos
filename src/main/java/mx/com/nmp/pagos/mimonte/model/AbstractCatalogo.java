package mx.com.nmp.pagos.mimonte.model;

import mx.com.nmp.pagos.mimonte.config.Constants;
import javax.persistence.*;
import java.util.Objects;

/**
 * Nombre: AbstractCatalogo
 * Descripcion: Clase abstracta que representa las propiedades generales de un catalogo
 *
 * @author Javier Hernandez jhernandez@quarksoft.net
 * Fecha: 08/02/2018 11:05 AM
 * @version 0.1
 */
@MappedSuperclass
public abstract class AbstractCatalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected Integer id;

    @Column(name="descripcion_corta", length = Constants.LONGITUD_DESCRIPCION_CORTA, unique = true)
    protected String descripcionCorta;

    @Column(name="descripcion", length = Constants.LONGITUD_DESCRIPCION)
    protected String descripcion;

    public AbstractCatalogo() {
        super();
    }

    public AbstractCatalogo(Integer id) {
        super();

        this.id = id;
    }


    public AbstractCatalogo(Integer id, String descripcionCorta, String descripcion) {
        this.id = id;
        this.descripcionCorta = descripcionCorta;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer integer) {
        this.id = integer;
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
        if (!(o instanceof AbstractCatalogo)) return false;
        AbstractCatalogo that = (AbstractCatalogo) o;
        return getId().equals(that.getId()) &&
                Objects.equals(getDescripcionCorta(), that.getDescripcionCorta()) &&
                Objects.equals(getDescripcion(), that.getDescripcion());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDescripcionCorta(), getDescripcion());
    }

    @Override
    public String toString() {
        return "AbstractCatalogo{" +
                "id=" + id +
                ", descripcionCorta='" + descripcionCorta + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
