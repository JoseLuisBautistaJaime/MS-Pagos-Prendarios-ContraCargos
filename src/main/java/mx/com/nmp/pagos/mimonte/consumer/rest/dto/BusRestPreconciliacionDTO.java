/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest.dto;

/**
 * @name BusRestPreconciliacionDTO
 * @description Clase que encapsula la información de la peticion del servicio que expone BUS
 *              para invocar la ejecución del proceso de pre-conciliación.
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 06/12/2021 09:46 hrs.
 */
public class BusRestPreconciliacionDTO implements BusRestBodyDTO {

	private BusRestRangoFechasDTO rangoFecha;
	private BusRestCorresponsalDTO corresponsal;

	public BusRestPreconciliacionDTO() {
		super();
	}

	public BusRestPreconciliacionDTO( BusRestRangoFechasDTO rangoFecha,BusRestCorresponsalDTO corresponsal) {
		super();
		this.rangoFecha = rangoFecha;
		this.corresponsal = corresponsal;
	}

	public BusRestRangoFechasDTO getRangoFecha() {
		return rangoFecha;
	}

	public void setRangoFecha(BusRestRangoFechasDTO rangoFecha) {
		this.rangoFecha = rangoFecha;
	}

	public BusRestCorresponsalDTO getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(BusRestCorresponsalDTO corresponsal) {
		this.corresponsal = corresponsal;
	}

	@Override
	public String toString() {
		return "BusRestPreconciliacionDTO [rangoFecha=" + rangoFecha + ", corresponsal=" + corresponsal  +"]";
	}

}
