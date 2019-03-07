package mx.com.nmp.pagos.mimonte.dto;

public abstract class AbstractCatDTO {
	
	protected Integer id;
	protected Boolean estatus;
	
	public AbstractCatDTO() {
		super();
	}

	public AbstractCatDTO(Integer id, Boolean estatus) {
		super();
		this.id = id;
		this.estatus = estatus;
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

	@Override
	public String toString() {
		return "AbstractCatDTO [id=" + id + ", estatus=" + estatus + "]";
	}

}
