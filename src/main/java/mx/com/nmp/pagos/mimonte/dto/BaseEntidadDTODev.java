/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * @name BaseEntidadDTODev
 * @description Encapsula la informacion de una entidad base con un campo
 *              adicional con el id de movimiento devolucion relacionado
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @creattionDate 02/08/2019 16:16:00 hrs.
 */
public class BaseEntidadDTODev {

	private Integer idMovimiento;
	private Long id;
	private String nombre;
	private String descripcion;

	public BaseEntidadDTODev() {
		super();
	}

	public BaseEntidadDTODev(Integer idMovimiento, Long id, String nombre, String descripcion) {
		super();
		this.idMovimiento = idMovimiento;
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Integer getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

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
		return "BaseEntidadDTODev [idMovimiento=" + idMovimiento + ", id=" + id + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + "]";
	}

}
