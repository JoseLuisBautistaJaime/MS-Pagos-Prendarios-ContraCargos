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

	@Column(name = "abreviatura", nullable = false)
	private String abreviatura;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "CatalogoOperacion{" +
				"id=" + id +
				", abreviatura='" + abreviatura + '\'' +
				", descripcion='" + descripcion + '\'' +
				'}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, abreviatura, descripcion);
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
