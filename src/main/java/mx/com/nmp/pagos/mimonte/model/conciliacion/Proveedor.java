/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Proveedor implements java.io.Serializable{

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	public Proveedor() {
		super();
	}
	
	public Proveedor(Long id) {
		super();
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "nombre", nullable = true)
	private String nombre;

	@Column(name = "descripcion", nullable = true)
	private String descripcion;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
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
		return "Proveedor [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
