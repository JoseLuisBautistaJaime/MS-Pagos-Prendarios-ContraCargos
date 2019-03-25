/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.Set;

/**
 * @name EntidadBaseDTO
 * @description Clase que encapsula la unformacion de un catalogo de entidad
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 14/03/2019 14:53 hrs.
 * @version 0.1
 */
public class EntidadBaseDTO implements Comparable<EntidadBaseDTO> {

	private Long id;
	private String descripcion;
	private String nombre;
	private Set<CuentaReqDTO> cuentas;
	private Set<ContactoReqDTO> contactos;

	public EntidadBaseDTO() {
		super();
	}

	public EntidadBaseDTO(Long id, String descripcion, String nombre, Set<CuentaReqDTO> cuentas,
			Set<ContactoReqDTO> contactos) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<CuentaReqDTO> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<CuentaReqDTO> cuentas) {
		this.cuentas = cuentas;
	}

	public Set<ContactoReqDTO> getContactos() {
		return contactos;
	}

	public void setContactos(Set<ContactoReqDTO> contactos) {
		this.contactos = contactos;
	}

	@Override
	public String toString() {
		return "EntidadBaseDTO [id=" + id + ", descripcion=" + descripcion + ", nombre=" + nombre + ", cuentas="
				+ cuentas + ", contactos=" + contactos + "]";
	}

	@Override
	public int compareTo(EntidadBaseDTO o) {
		return o.nombre.compareTo(this.nombre);
	}

}
