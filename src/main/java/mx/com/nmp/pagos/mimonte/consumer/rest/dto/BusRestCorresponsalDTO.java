/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest.dto;


/**
 * @name BusRestCorresponsalDTO
 * @description Clase que encapsula el corresponsal como parametro de los servicios expuesto por BUS.
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 07/01/2022 12:46 hrs.
 */
public class BusRestCorresponsalDTO {

	private String idCorresponsal;

	public BusRestCorresponsalDTO() {}

	public BusRestCorresponsalDTO(String idCorresponsal) {
		this.idCorresponsal = idCorresponsal;
	}

	public String getIdCorresponsal() {
		return idCorresponsal;
	}

	public void setIdCorresponsal(String idCorresponsal) {
		this.idCorresponsal = idCorresponsal;
	}

	@Override
	public String toString() {
		return "BusRestCorresponsalDTO [idCorresponsal=" + idCorresponsal +"]";
	}

}
