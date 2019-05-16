/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;

/**
 * @name LayoutCabeceraDTOs
 * @description Clase que encapsula la informacion de un objeto
 *              LayoutCabeceraDTOs.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 15/05/2019 23:28 hrs.
 * @version 0.1
 */
public class LayoutLineaDTOs implements Comparable<LayoutLineaDTOs>{
	
	private Integer	id;
	private	String linea;
	private	String cuenta;
	private	String depID;
	private	String unidadOperativa;
	private	String negocio;
	private	String proyectoNMP;
	private	BigDecimal monto;

	public LayoutLineaDTOs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LayoutLineaDTOs(Integer id, String linea, String cuenta, String depID, String unidadOperativa,
			String negocio, String proyectoNMP, BigDecimal monto) {
		super();
		this.id = id;
		this.linea = linea;
		this.cuenta = cuenta;
		this.depID = depID;
		this.unidadOperativa = unidadOperativa;
		this.negocio = negocio;
		this.proyectoNMP = proyectoNMP;
		this.monto = monto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getDepID() {
		return depID;
	}

	public void setDepID(String depID) {
		this.depID = depID;
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "LayoutLineaDTOs [id=" + id + ", linea=" + linea + ", cuenta=" + cuenta + ", depID=" + depID
				+ ", unidadOperativa=" + unidadOperativa + ", negocio=" + negocio + ", proyectoNMP=" + proyectoNMP
				+ ", monto=" + monto + "]";
	}

	@Override
	public int compareTo(LayoutLineaDTOs o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
