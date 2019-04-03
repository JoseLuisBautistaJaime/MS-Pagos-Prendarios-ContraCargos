/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name LayoutLineaDTO
 * @description Clase que encapsula las lineas de un layout
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 05/04/2019 10:41 hrs.
 * @version 0.1
 */
public class LayoutLineaDTO implements Comparable<LayoutLineaDTO> {

	private Long id;
	private String linea;
	private String cuenta;
	private String depId;
	private String unidadOperativa;
	private String negocio;
	private String proyectoNMP;
	private Double monto;

	public LayoutLineaDTO() {
		super();
	}

	public LayoutLineaDTO(Long id, String linea, String cuenta, String depId, String unidadOperativa, String negocio,
			String proyectoNMP, Double monto) {
		super();
		this.id = id;
		this.linea = linea;
		this.cuenta = cuenta;
		this.depId = depId;
		this.unidadOperativa = unidadOperativa;
		this.negocio = negocio;
		this.proyectoNMP = proyectoNMP;
		this.monto = monto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getUnidadOperativa() {
		return unidadOperativa;
	}

	public void setUnidadOperativa(String unidadOperativa) {
		this.unidadOperativa = unidadOperativa;
	}

	public String getNegocio() {
		return negocio;
	}

	public void setNegocio(String negocio) {
		this.negocio = negocio;
	}

	public String getProyectoNMP() {
		return proyectoNMP;
	}

	public void setProyectoNMP(String proyectoNMP) {
		this.proyectoNMP = proyectoNMP;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "LayoutLineaDTO [id=" + id + ", linea=" + linea + ", cuenta=" + cuenta + ", depId=" + depId
				+ ", unidadOperativa=" + unidadOperativa + ", negocio=" + negocio + ", proyectoNMP=" + proyectoNMP
				+ ", monto=" + monto + "]";
	}

	@Override
	public int compareTo(LayoutLineaDTO o) {
		return o.id.compareTo(this.id);
	}

}
