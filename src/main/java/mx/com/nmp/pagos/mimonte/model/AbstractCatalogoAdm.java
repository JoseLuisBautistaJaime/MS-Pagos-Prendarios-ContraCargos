package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractCatalogoAdm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Integer id;

	@Column(name = "estatus")
	protected Boolean estatus;

	@Column(name = "fecha_creacion")
	protected Date fechaCreacion;

	@Column(name = "fecha_modificacion")
	protected Date fechaModificacion;

	@Column(name = "usuario_creador")
	protected Long usuarioCreador;

	public AbstractCatalogoAdm() {
		super();
	}

	public AbstractCatalogoAdm(Integer id, Boolean estatus, Date fechaCreacion, Date fechaModificacion,
			Long usuarioCreador) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioCreador = usuarioCreador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(Long usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	@Override
	public String toString() {
		return "AbstractCatalogoAdm [id=" + id + ", estatus=" + estatus + ", fechaCreacion=" + fechaCreacion
				+ ", fechaModificacion=" + fechaModificacion + ", usuarioCreador=" + usuarioCreador + "]";
	}

}
