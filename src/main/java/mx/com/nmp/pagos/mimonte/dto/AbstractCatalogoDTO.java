package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

public abstract class AbstractCatalogoDTO {

	protected Integer id;
	protected Boolean estatus;
	protected Date fechaCreacion;
	protected Date fechaModificacion;
	protected Long usuarioCreador;

	public AbstractCatalogoDTO() {
		super();
	}

	public AbstractCatalogoDTO(Integer id, Boolean estatus, Date fechaCreacion, Date fechaModificacion,
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
		return "AbstractCatalogoDTO [id=" + id + ", estatus=" + estatus + ", fechaCreacion=" + fechaCreacion
				+ ", fechaModificacion=" + fechaModificacion + ", usuarioCreador=" + usuarioCreador + "]";
	}

}
