package mx.com.nmp.pagos.mimonte.dto;

import java.util.Set;

/**
 * Nombre: ReglaNegocioResumenDTO Descripcion: Clase que encapsula la
 * informacion perteneciente a una Regla de Negocio Resumen.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 16:44 hrs.
 * @version 0.1
 */
public class ReglaNegocioResumenDTO implements Comparable<ReglaNegocioResumenDTO> {

	private Integer id;
	private Boolean valido;
	private Set<TipoAutorizacionDTO> tipoAutorizacionSet;

	public ReglaNegocioResumenDTO() {
		super();
	}

	public ReglaNegocioResumenDTO(Integer id, Boolean valido, Set<TipoAutorizacionDTO> tipoAutorizacionSet) {
		super();
		this.id = id;
		this.valido = valido;
		this.tipoAutorizacionSet = tipoAutorizacionSet;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	public Set<TipoAutorizacionDTO> getTipoAutorizacionSet() {
		return tipoAutorizacionSet;
	}

	public void setTipoAutorizacionSet(Set<TipoAutorizacionDTO> tipoAutorizacionSet) {
		this.tipoAutorizacionSet = tipoAutorizacionSet;
	}

	@Override
	public String toString() {
		return "ReglaNegocioResumenDTO [id=" + id + ", valido=" + valido + ", tipoAutorizacionSet="
				+ tipoAutorizacionSet + "]";
	}

	@Override
	public int compareTo(ReglaNegocioResumenDTO o) {
		int val = 0;
		if (this.id > o.id)
			val = 1;
		else
			val = -1;
		return val;
	}

}
