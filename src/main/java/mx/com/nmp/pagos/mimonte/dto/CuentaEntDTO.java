/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

/**
 * @name CuentaEntDTO
 * @description Clase que encapsula la informacion de una cuenta a enviar como
 *              respuesta dentro del objeto Entidad
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 07/03/2019 10:37 hrs.
 * @version 0.1
 */
public class CuentaEntDTO implements Comparable<CuentaEntDTO> {

	private Long id;
	private String numero;
	private Boolean estatus;
	private List<AfiliacionEntDTO> afiliaciones;

	public CuentaEntDTO() {
		super();
	}

	public CuentaEntDTO(Long id, String numero, Boolean estatus, List<AfiliacionEntDTO> afiliaciones) {
		super();
		this.id = id;
		this.numero = numero;
		this.estatus = estatus;
		this.afiliaciones = afiliaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<AfiliacionEntDTO> getAfiliaciones() {
		return afiliaciones;
	}

	public void setAfiliaciones(List<AfiliacionEntDTO> afiliaciones) {
		this.afiliaciones = afiliaciones;
	}

	@Override
	public int compareTo(CuentaEntDTO o) {
		return o.getId().compareTo(this.id);
	}

}
