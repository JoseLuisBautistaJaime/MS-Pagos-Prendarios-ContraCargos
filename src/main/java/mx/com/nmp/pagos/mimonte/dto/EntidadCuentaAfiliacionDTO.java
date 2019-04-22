/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import mx.com.nmp.pagos.mimonte.model.Afiliacion;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;

/**
 * @name Entidad
 * @description Clase que encapsula la informacion de un objeto de catalogo de
 *              tipo EntidadCuentaAfiliacionDTO
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 18/04/2019 00:29 hrs.
 * @version 0.1
 */
public class EntidadCuentaAfiliacionDTO implements Comparable<EntidadCuentaAfiliacionDTO> {

	private Entidad entidad;
	private Cuenta cuenta;
	private Afiliacion afiliacion;

	public EntidadCuentaAfiliacionDTO() {
		super();
	}

	public EntidadCuentaAfiliacionDTO(Entidad entidad, Cuenta cuenta, Afiliacion afiliacion) {
		super();
		this.entidad = entidad;
		this.cuenta = cuenta;
		this.afiliacion = afiliacion;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Afiliacion getAfiliacion() {
		return afiliacion;
	}

	public void setAfiliacion(Afiliacion afiliacion) {
		this.afiliacion = afiliacion;
	}

	@Override
	public String toString() {
		return "EntidadCuentaAfiliacionDTO [entidad=" + entidad + ", cuenta=" + cuenta + ", afiliacion=" + afiliacion
				+ "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public int compareTo(EntidadCuentaAfiliacionDTO arg0) {
		return arg0.getEntidad().getId().compareTo(this.entidad.getId());
	}

}
