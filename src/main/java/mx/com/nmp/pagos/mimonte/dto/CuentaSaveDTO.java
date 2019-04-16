/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

/**
 * @name CuentaSaveDTO
 * @description Clase que encapsula la informacion de un objeto cuenta
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 16/04/2019 12:21 hrs.
 * @version 0.1
 */
public class CuentaSaveDTO {

	private String numero;
	private List<AfiliacionSaveDTO> afiliaciones;

	public CuentaSaveDTO() {
		super();

	}

	public CuentaSaveDTO(String numero, List<AfiliacionSaveDTO> afiliaciones) {
		super();
		this.numero = numero;
		this.afiliaciones = afiliaciones;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<AfiliacionSaveDTO> getAfiliaciones() {
		return afiliaciones;
	}

	public void setAfiliaciones(List<AfiliacionSaveDTO> afiliaciones) {
		this.afiliaciones = afiliaciones;
	}

	@Override
	public String toString() {
		return "CuentaSaveDTO [numero=" + numero + ", afiliaciones=" + afiliaciones + "]";
	}

}
