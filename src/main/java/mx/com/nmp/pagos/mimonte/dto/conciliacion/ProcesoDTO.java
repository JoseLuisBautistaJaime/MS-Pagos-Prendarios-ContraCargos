/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Objects;

/**
 * @name ProcesoDTO
 * @description Clase que encapsula la información del proceso de automatización.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 19/11/2021 09:22 hrs.
 * @version 0.1
 */


public class ProcesoDTO implements Comparable<ProcesoDTO> {

	private Integer id;
	private String descripcionCorta;
	private String descripcion;


	public ProcesoDTO() {
		super();
	}

	public ProcesoDTO(Integer id) {
		super();
		this.id = id;
	}

	public ProcesoDTO(Integer id, String descripcionCorta, String descripcion) {
		super();
		this.id = id;
		this.descripcionCorta = descripcionCorta;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "ProcesoDTO [id=" + id + ", descripcionCorta=" + descripcionCorta + ", descripcion=" + descripcion  +"]";
	}

	@Override
	public int compareTo(ProcesoDTO o) {
		return 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, descripcionCorta, descripcion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ProcesoDTO)) {
			return false;
		}
		final ProcesoDTO other = (ProcesoDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

}
