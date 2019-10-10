/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name ContactoReqSearchDTO
 * @description Clase que encapsula la información de un objeto "Contacto" utilizado en la búsqueda por parámetros.
 *
 * @author Néstor González ngonzalez@quarksoft.net
 * @creationDate 09/10/2019 18:11:53 hrs.
 * @version 0.1
 */
public class ContactoReqSearchDTO {

    private Long idTipoContacto = 1L;
    private String nombre;
    private String email;



    // METODOS

    /**
     * Constructor de la clase.
     */
    public ContactoReqSearchDTO() {
        super();
    }

    /**
     * Constructor de la clase.
     */
    public ContactoReqSearchDTO(Long idTipoContacto, String nombre, String email) {
        super();
        this.idTipoContacto = idTipoContacto;
        this.nombre = nombre;
        this.email = email;
    }



    // GETTERS Y SETTERS

    public Long getIdTipoContacto() {
        return idTipoContacto;
    }

    public void setIdTipoContacto(Long idTipoContacto) {
        this.idTipoContacto = idTipoContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ContactoReqSearchDTO{" +
                "idTipoContacto=" + idTipoContacto +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
