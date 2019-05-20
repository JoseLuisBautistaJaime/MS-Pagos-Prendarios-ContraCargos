/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @name CatalogoOperacion
 * @description Clase que encapsula la informacion de un catalogo de tipos de
 *              operacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 20/05/2019 13:06 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_operacion")
public class CatalogoOperacion {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "tipo", nullable = false)
	private Integer tipo;

	@Column(name = "abreviatura", nullable = false)
	private String abreviatura;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "inddep", nullable = false)
	private Integer inddep;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getInddep() {
		return inddep;
	}

	public void setInddep(Integer inddep) {
		this.inddep = inddep;
	}

	@Override
	public String toString() {
		return "CatalogoOperacion [id=" + id + ", tipo=" + tipo + ", abreviatura=" + abreviatura + ", descripcion="
				+ descripcion + ", inddep=" + inddep + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tipo, abreviatura, descripcion, inddep);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof CatalogoOperacion))
			return false;

		final CatalogoOperacion other = (CatalogoOperacion) obj;
		return (this.hashCode() == other.hashCode());

	}

}
