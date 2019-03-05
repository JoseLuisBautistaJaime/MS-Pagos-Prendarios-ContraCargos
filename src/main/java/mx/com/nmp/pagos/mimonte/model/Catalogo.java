package mx.com.nmp.pagos.mimonte.model;

import mx.com.nmp.pagos.mimonte.model.AbstractCatalogo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Nombre: Catalogo
 * Descripcion: Entidad que representa un catalogo dentro del sistema.
 *
 * @author Javier Hernandez jhernandez@quarksoft.net
 * Fecha: 22/02/2018 11:03 AM
 * @version 0.1
 */
@Entity
@Table(name = "tk_catalogo")
public class Catalogo extends AbstractCatalogo {
    /**
     * Nombre de la tabla a la que corresponde el catalogo
     */
    @Column(name = "nombre_tabla")
    private String nombreTabla;

    /**
     * Bandera que especifica si un catalogo se encuentra activo dentro del sistema o no.
     */
    @NotNull
    @Column(name = "activo", nullable = false)
    private Boolean activo;

    public Catalogo() {
    }

    public Catalogo(Integer id, String descripcionCorta, String descripcion, String nombreTabla, Boolean activo) {
        super(id, descripcionCorta, descripcion);
        this.nombreTabla = nombreTabla;
        this.activo = activo;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
