/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO;

/**
 * @name Actividad
 * @description Clase de tipo entidad persistete que encapsula la informacion
 *              realcionada con el registro de actividades
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 14/06/2019 11:48 hrs.
 * @version 0.1
 */
@SqlResultSetMapping(name = "ActividadResultmapping", classes = {
		@ConstructorResult(targetClass = ConsultaActividadDTO.class, columns = { @ColumnResult(name = "folio"),
				@ColumnResult(name = "fecha"), @ColumnResult(name = "descripcion") }) })
@NamedNativeQuery(name = "Actividad.nGetTopXActividades", query = "SELECT folio, fecha, descripcion FROM tb_actividad ORDER BY fecha DESC LIMIT :limitValue;", resultSetMapping = "ActividadResultmapping")
@Entity
@Table(name = "tb_actividad")
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "tipo", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoActividadEnum tipo;

	@Column(name = "sub_tipo", nullable = false)
	@Enumerated(EnumType.STRING)
	private SubTipoActividadEnum subTipo;

	@Size(max = 150, message = "La longitud de la descripcion debe ser de maximo 150 caracteres")
	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false)
	private Date fecha;

	@Column(name = "folio", nullable = false)
	private Integer folio;

	public Actividad() {
		super();
	}

	public Actividad(Long id, TipoActividadEnum tipo, SubTipoActividadEnum subTipo, String descripcion, Date fecha,
			Integer folio) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.subTipo = subTipo;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.folio = folio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoActividadEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoActividadEnum tipo) {
		this.tipo = tipo;
	}

	public SubTipoActividadEnum getSubTipo() {
		return subTipo;
	}

	public void setSubTipo(SubTipoActividadEnum subTipo) {
		this.subTipo = subTipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tipo, subTipo, descripcion, fecha, folio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Actividad))
			return false;

		final Actividad other = (Actividad) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public String toString() {
		return "Actividad [id=" + id + ", tipo=" + tipo + ", subTipo=" + subTipo + ", descripcion=" + descripcion
				+ ", fecha=" + fecha + ", folio=" + folio + "]";
	}

}
