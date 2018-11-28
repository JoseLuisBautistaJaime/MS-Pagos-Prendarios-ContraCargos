package mx.com.nmp.pagos.mimonte.constans;

/**
 * Nombre: EstatusPago
 * Descripcion: Mapea los estatus de pago en estructura ENUM
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 27/11/2018 12:49 hrs.
 * @version 0.1
 */
public enum EstatusPago {

	REGISTERED_PAYMENT_STATUS(1, "Registrado", "Pago Registrado");

	/**
	 * Id de el estatus de pago
	 */
	private Integer id;

	/**
	 * Descripcion del estatus de pago
	 */
	private String descripcion;

	/**
	 * Descripcion corta del estatus de pago
	 */
	private String descripcionCorta;

	EstatusPago(Integer id, String descripcion, String descripcionCorta) {
		this.id = id;
		this.descripcion = descripcion;
		this.descripcionCorta = descripcionCorta;
	}

	/**
	 * Recupera el valor de {@code id}
	 *
	 * @return Valor de {@code id}
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Recupera el valor de {@code descripcion}
	 *
	 * @return Valor de {@code descripcion}
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Recupera el valor de {@code descripcionCorta}
	 *
	 * @return Valor de {@code descripcionCorta}
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}

}
