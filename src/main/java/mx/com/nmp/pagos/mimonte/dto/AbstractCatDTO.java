package mx.com.nmp.pagos.mimonte.dto;

public abstract class AbstractCatDTO {
	
	protected Long id;
	protected Boolean estatus;
	
	public AbstractCatDTO() {
		super();
	}

	public AbstractCatDTO(Long id, Boolean estatus) {
		super();
		this.id = id;
		this.estatus = estatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "AbstractCatDTO [id=" + id + ", estatus=" + estatus + "]";
	}

}
