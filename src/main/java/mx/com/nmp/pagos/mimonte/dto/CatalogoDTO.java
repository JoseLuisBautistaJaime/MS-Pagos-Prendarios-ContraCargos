package mx.com.nmp.pagos.mimonte.dto;

import mx.com.nmp.pagos.mimonte.dto.EntradaCatalogo;

import java.util.List;

/**
 * Nombre: Catalogo
 * Descripcion: Clase que encapsula la informacion perteneciente a un catalogo.
 *
 * @author Javier Hernandez jhernandez@quarksoft.net
 * Fecha: 14/02/2018 07:34 PM
 * @version 0.1
 */
public class CatalogoDTO {
    private String nombre;
    private List<EntradaCatalogo> registros;

    public CatalogoDTO() {
        super();
    }

    public CatalogoDTO(String nombre, List<EntradaCatalogo> registros) {
        this.nombre = nombre;
        this.registros = registros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<EntradaCatalogo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<EntradaCatalogo> registros) {
        this.registros = registros;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "nombre='" + nombre + '\'' +
                ", registros=" + registros +
                '}';
    }
}
