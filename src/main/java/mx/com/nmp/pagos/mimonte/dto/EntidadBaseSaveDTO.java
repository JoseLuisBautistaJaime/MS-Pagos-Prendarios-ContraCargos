/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.Set;

/**
 * @name EntidadBaseSaveDTO
 * @description Clase que encapsula la unformacion de un catalogo de entidad
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 16/04/2019 12:19 hrs.
 * @version 0.1
 */
public class EntidadBaseSaveDTO implements Comparable<EntidadBaseSaveDTO> {

	private String descripcion;
	private String nombre;
	private Set<CuentaSaveReqDTO> cuentas;
	private Set<ContactoReqSaveNewDTO> contactos;

	public EntidadBaseSaveDTO() {
		super();
	}

	public EntidadBaseSaveDTO(String descripcion, String nombre, Set<CuentaSaveReqDTO> cuentas,
			Set<ContactoReqSaveNewDTO> contactos) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.cuentas = cuentas;
		this.contactos = contactos;
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

	public Set<CuentaSaveReqDTO> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<CuentaSaveReqDTO> cuentas) {
		this.cuentas = cuentas;
	}

	public Set<ContactoReqSaveNewDTO> getContactos() {
		return contactos;
	}

	public void setContactos(Set<ContactoReqSaveNewDTO> contactos) {
		this.contactos = contactos;
	}

	@Override
	public String toString() {
		return "EntidadBaseSaveDTO [descripcion=" + descripcion + ", nombre=" + nombre + ", cuentas=" + cuentas
				+ ", contactos=" + contactos + "]";
	}

	@Override
	public int compareTo(EntidadBaseSaveDTO o) {
		return o.nombre.compareTo(this.nombre);
	}

}
