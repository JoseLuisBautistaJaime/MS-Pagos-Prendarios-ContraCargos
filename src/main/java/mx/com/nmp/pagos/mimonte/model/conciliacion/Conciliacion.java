package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.EstatusConciliacion;
import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * Registro de conciliacion.
 * 
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:43 PM
 */
@Entity
@Table(name = "to_conciliacion")
public class Conciliacion extends Updatable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Transient
	private EstatusConciliacion estatus;

	@Transient
	private Entidad entidad;

	@Transient
	private Cuenta cuenta;

	@Transient
	private String peopleSoftId;

	@Transient
	private Global global;

	@OneToMany(mappedBy = "conciliacion")
	private Set<Reporte> reportes;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
		result = prime * result + ((entidad == null) ? 0 : entidad.hashCode());
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result + ((global == null) ? 0 : global.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((peopleSoftId == null) ? 0 : peopleSoftId.hashCode());
		result = prime * result + ((reportes == null) ? 0 : reportes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conciliacion other = (Conciliacion) obj;
		if (cuenta == null) {
			if (other.cuenta != null)
				return false;
		} else if (!cuenta.equals(other.cuenta))
			return false;
		if (entidad == null) {
			if (other.entidad != null)
				return false;
		} else if (!entidad.equals(other.entidad))
			return false;
		if (estatus == null) {
			if (other.estatus != null)
				return false;
		} else if (!estatus.equals(other.estatus))
			return false;
		if (global == null) {
			if (other.global != null)
				return false;
		} else if (!global.equals(other.global))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (peopleSoftId == null) {
			if (other.peopleSoftId != null)
				return false;
		} else if (!peopleSoftId.equals(other.peopleSoftId))
			return false;
		if (reportes == null) {
			if (other.reportes != null)
				return false;
		} else if (!reportes.equals(other.reportes))
			return false;
		return true;
	}

}