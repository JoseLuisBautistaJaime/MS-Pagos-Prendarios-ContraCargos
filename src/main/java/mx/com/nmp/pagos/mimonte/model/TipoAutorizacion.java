package mx.com.nmp.pagos.mimonte.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Nombre: TipoAutorizacion Descripcion: Entidad que representa un tipo de
 * autorizacion dentro del sistema en el catalogo de afiliaciones.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 01/02/2019 11:57 hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tipo_afiliacion_c")
public class TipoAutorizacion extends AbstractCatalogo {

	public TipoAutorizacion() {
		super();
	}

	public TipoAutorizacion(Set<Afiliacion> afiliaciones) {
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
		return "TipoAutorizacion [afiliaciones=" + afiliaciones + "]";
	}

}
