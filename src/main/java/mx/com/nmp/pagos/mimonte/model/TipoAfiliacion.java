package mx.com.nmp.pagos.mimonte.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_afiliacion_c")
public class TipoAfiliacion extends AbstractCatalogo {

	public TipoAfiliacion() {
		super();
	}

	public TipoAfiliacion(Set<Afiliacion> afiliaciones) {
		super();
		this.afiliaciones = afiliaciones;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipo")
	private Set<Afiliacion> afiliaciones;

	public Set<Afiliacion> getAfiliaciones() {
		return afiliaciones;
	}

	public void setAfiliaciones(Set<Afiliacion> afiliaciones) {
		this.afiliaciones = afiliaciones;
	}

	@Override
	public String toString() {
		return "TipoAfiliacion [afiliaciones=" + afiliaciones + "]";
	}

}
