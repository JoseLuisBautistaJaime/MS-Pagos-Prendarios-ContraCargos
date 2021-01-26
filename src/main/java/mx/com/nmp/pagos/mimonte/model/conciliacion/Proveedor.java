/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Proveedor o Corresponsal de quien se realiza la conciliacion.
 * 
 * @author Ismael Flores Aguilar
 * @version 1.0
 * @created 14-Oct-2020
 */
@Entity
@Table(name = "tk_proveedor")
public class Proveedor implements java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	public Proveedor() {
		super();
	}

	public Proveedor(CorresponsalEnum nombre) {
		super();
		this.nombre = nombre;
	}

	@Id
	@Enumerated(EnumType.STRING)
	@Column(name = "nombre", nullable = true)
	private CorresponsalEnum nombre;

	@Column(name = "descripcion", nullable = true)
	private String descripcion;

	public CorresponsalEnum getNombre() {
		return nombre;
	}

	public void setNombre(CorresponsalEnum nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Proveedor [nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
