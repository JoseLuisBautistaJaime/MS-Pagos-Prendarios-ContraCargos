package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;
import java.util.Set;

/**
 * Nombre: CuentaBaseDTO Descripcion: Clase que encapsula la informacion de un
 * objeto cuenta base
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 13/03/2019 20:22 hrs.
 * @version 0.1
 */
public class CuentaBaseDTO extends AbstractCatalogoDTO implements Comparable<CuentaBaseDTO>, java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -1128729505717430633L;

	private String numeroCuenta;
	private Set<AfiliacionDTO> afiliaciones;

	public CuentaBaseDTO() {
		super();
	}

	public CuentaBaseDTO(String numeroCuenta, Set<AfiliacionDTO> afiliaciones) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.afiliaciones = afiliaciones;
	}

	public CuentaBaseDTO(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription, String numeroCuenta,
			Set<AfiliacionDTO> afiliaciones) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
		this.numeroCuenta = numeroCuenta;
		this.afiliaciones = afiliaciones;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Set<AfiliacionDTO> getAfiliaciones() {
		return afiliaciones;
	}

	public void setAfiliaciones(Set<AfiliacionDTO> afiliaciones) {
		this.afiliaciones = afiliaciones;
	}

	@Override
	public String toString() {
		return "CuentaBaseDTO [numeroCuenta=" + numeroCuenta + ", afiliaciones=" + afiliaciones + "]";
	}

	@Override
	public int compareTo(CuentaBaseDTO o) {
		return o.numeroCuenta.compareTo(this.numeroCuenta);
	}

}
