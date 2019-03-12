package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Nombre: TipoContacto
 * Descripcion: Entidad que representa el tipo de contacto dentro del sistema.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net Fecha: 05/03/2019 12:16 Hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_tipo_contacto")
public class TipoContacto extends AbstractCatalogoAdm implements Serializable{

	private static final long serialVersionUID = 7033742691736781185L;
	
	public TipoContacto() {
		super();
	}

	public TipoContacto(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy);
	}

	public TipoContacto(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	@Column(name = "descripcion")
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoContacto other = (TipoContacto) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoContacto [descripcion=" + descripcion + "]";
	}

}
