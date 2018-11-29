package mx.com.nmp.pagos.mimonte.constans;

/**
 * Nombre: EstatusOperacion
 * Descripcion: Mapea los estatus de una operacion a
 * una estructura ENUM NOTA: Este enum debe mantenerse actualizado para que se
 * igual al catalogo en base de datos
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 29/11/2018 15:04 hrs.
 * @version 0.1
 */
public enum EstatusOperacion {

	SUCCESSFUL_STATUS_OPERATION(1, "Operacion Exitosa"), FAIL_STATUS_OPERATION(1, "Operacion Exitosa");

	/**
	 * Campo id de estatus operacion
	 */
	private Integer id;

	/**
	 * Campo descripcion de estatus operacion
	 */
	private String descripcion;

	private EstatusOperacion(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
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

}
