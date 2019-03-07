package mx.com.nmp.pagos.mimonte.dto;

import java.util.Set;

/**
 * Nombre: CuentaEntDTO Descripcion: Clase que encapsula la informacion de una
 * cuenta a enviar como respuesta dentro del objeto Entidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 07/03/2019 10:37 hrs.
 * @version 0.1
 */
public class CuentaEntDTO implements Comparable<CuentaEntDTO> {

	private Long id;
	private Long numeroCuenta;
	private Boolean estatus;
	private Set<AfiliacionEntDTO> afiliaciones;

	public CuentaEntDTO() {
		super();
	}

	public CuentaEntDTO(Long id, Long numeroCuenta, Boolean estatus, Set<AfiliacionEntDTO> afiliaciones) {
		super();
		this.id = id;
		this.numeroCuenta = numeroCuenta;
		this.estatus = estatus;
		this.afiliaciones = afiliaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Set<AfiliacionEntDTO> getAfiliaciones() {
		return afiliaciones;
	}

	public void setAfiliaciones(Set<AfiliacionEntDTO> afiliaciones) {
		this.afiliaciones = afiliaciones;
	}

	@Override
	public int compareTo(CuentaEntDTO o) {
		return o.getId().compareTo(this.id);
	}

}
