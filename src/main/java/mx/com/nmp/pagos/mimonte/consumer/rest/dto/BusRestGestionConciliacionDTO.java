/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest.dto;


/**
 * @name BusRestGestionConciliacionDTO
 * @description Clase que encapsula la información de la peticion del servicio que expone BUS
 *              para crear el proceso de conciliación.
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 24/01/2022 13:46 hrs.
 */
public class BusRestGestionConciliacionDTO implements BusRestBodyDTO {


	private Long idCuenta;
	private Long idEntidad;
	private BusRestCorresponsalDTO corresponsal;

	public BusRestGestionConciliacionDTO() {
		super();
	}

	public BusRestGestionConciliacionDTO(Long idCuenta, Long idEntidad, String idCorresponsal) {
		super();
		this.idCuenta = idCuenta;
		this.idEntidad = idEntidad;
		this.corresponsal = new BusRestCorresponsalDTO(idCorresponsal);
	}

	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public BusRestCorresponsalDTO getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(BusRestCorresponsalDTO corresponsal) {
		this.corresponsal = corresponsal;
	}

	@Override
	public String toString() {
		return "BusRestGestionConciliacionDTO [idCuenta=" + idCuenta  +", idEntidad=" + idEntidad +", corresponsal=" + corresponsal +"]";
	}

}
