package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

/**
 * Nombre: CuentaReqDTO Descripcion: Clase que encapsula la informacion
 * informacion sobre una cuenta en el requets de alta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 14/03/2019 14:30 hrs.
 * @version 0.1
 */
public class CuentaReqDTO implements Comparable<CuentaReqDTO> {

	private Long id;
	private String numero;
	private Boolean estatus;
	private List<AfiliacionReqDTO> afiliaicones;

	public CuentaReqDTO() {
		super();
	}

	public CuentaReqDTO(Long id, String numero, Boolean estatus, List<AfiliacionReqDTO> afiliaicones) {
		super();
		this.id = id;
		this.numero = numero;
		this.estatus = estatus;
		this.afiliaicones = afiliaicones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<AfiliacionReqDTO> getAfiliaicones() {
		return afiliaicones;
	}

	public void setAfiliaicones(List<AfiliacionReqDTO> afiliaicones) {
		this.afiliaicones = afiliaicones;
	}

	@Override
	public String toString() {
		return "CuentaReqDTO [id=" + id + ", numero=" + numero + ", estatus=" + estatus + ", afiliaicones="
				+ afiliaicones + "]";
	}

	@Override
	public int compareTo(CuentaReqDTO o) {
		return o.numero.compareTo(this.numero);
	}

}
