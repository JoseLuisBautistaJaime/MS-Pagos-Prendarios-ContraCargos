/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest.dto;


/**
 * @name BusRestMergeConciliacionDTO
 * @description Clase que encapsula la información de la peticion del servicio que expone BUS
 *              para conciliar los movimientos cargados.
 * 
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @createdDate 20/01/2022 13:46 hrs.
 */
public class BusRestMergeConciliacionDTO implements BusRestBodyDTO {


	private Long folio;

	public BusRestMergeConciliacionDTO() {
		super();
	}

	public BusRestMergeConciliacionDTO(Long folio) {
		super();
		this.folio = folio;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}


	@Override
	public String toString() {
		return "BusRestMergeConciliacionDTO [folio=" + folio  +"]";
	}

}
