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
 * @name CatalogoTipoContrato
 * @description Clase que encapsula la informacion de un catalogo de tipo de
 *              contrato
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 20/05/2019 13:07 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_tipo_contrato")
public class CatalogoTipoContrato {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "baja_Logica", nullable = false)
	private String bajaLogica;

	@Column(name = "abreviatura", nullable = false)
	private String abreviatura;

	@Column(name = "inddep", nullable = false)
	private Integer inddep;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getBajaLogica() {
		return bajaLogica;
	}

	public void setBajaLogica(String bajaLogica) {
		this.bajaLogica = bajaLogica;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public Integer getInddep() {
		return inddep;
	}

	public void setInddep(Integer inddep) {
		this.inddep = inddep;
	}

	@Override
	public String toString() {
		return "CatalogoTipoContrato [id=" + id + ", descripcion=" + descripcion + ", bajaLogica=" + bajaLogica
				+ ", abreviatura=" + abreviatura + ", inddep=" + inddep + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, descripcion, bajaLogica, abreviatura, inddep);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof CatalogoTipoContrato))
			return false;

		final CatalogoTipoContrato other = (CatalogoTipoContrato) obj;
		return (this.hashCode() == other.hashCode());

	}
}
