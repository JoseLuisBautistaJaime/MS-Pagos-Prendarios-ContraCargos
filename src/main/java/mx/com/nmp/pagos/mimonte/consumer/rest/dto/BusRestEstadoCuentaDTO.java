/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest.dto;

import org.apache.commons.lang.StringUtils;

/**
 * @name BusEstadoCuentaDTO
 * @description Clase que encapsula la informacion del servicio que expone BUS
 *              para consulta estado cuenta
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @version 1.0
 * @createdDate 04/06/2019 22:55 hrs.
 */
public class BusRestEstadoCuentaDTO implements BusRestParamDTO {

	private String ruta;
	private String nombre;

	public BusRestEstadoCuentaDTO() {
		super();
	}

	public BusRestEstadoCuentaDTO(String ruta, String nombre) {
		super();
		this.ruta = ruta;
		this.nombre = nombre;
	}

	public String getUri() {
		String url = "?ruta=" + (StringUtils.isNotBlank(ruta) ? ruta : "") + "&nombre=" + (StringUtils.isNotBlank(nombre) ? nombre : "");
		return url;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "BusEstadoCuentaDTO [ruta=" + ruta + ", nombre=" + nombre + "]";
	}

}
