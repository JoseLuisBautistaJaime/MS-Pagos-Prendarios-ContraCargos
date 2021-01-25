/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest.dto;


/**
 * @name BusRestConsultaPagosDTO
 * @version 1.0
 * @createdDate 04/06/2019 22:55 hrs.
 */
public class BusRestConsultaPagosDTO implements BusRestBodyDTO {

	private String corresponsalFolio;
	private String idPagoCorresponsal;
	private String desde;
	private String hasta;
	private String nombreCliente;
	private String referencia;
	private String idTransaccionMidas;
	private String estadoPago;

	public BusRestConsultaPagosDTO() {
		super();
	}

	public BusRestConsultaPagosDTO(String corresponsalFolio, String idPagoCorresponsal) {
		super();
		this.corresponsalFolio = corresponsalFolio;
		this.idPagoCorresponsal = idPagoCorresponsal;
	}

	public String getCorresponsalFolio() {
		return corresponsalFolio;
	}

	public void setCorresponsalFolio(String corresponsalFolio) {
		this.corresponsalFolio = corresponsalFolio;
	}

	public String getIdPagoCorresponsal() {
		return idPagoCorresponsal;
	}

	public void setIdPagoCorresponsal(String idPagoCorresponsal) {
		this.idPagoCorresponsal = idPagoCorresponsal;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getIdTransaccionMidas() {
		return idTransaccionMidas;
	}

	public void setIdTransaccionMidas(String idTransaccionMidas) {
		this.idTransaccionMidas = idTransaccionMidas;
	}

	public String getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}


	@Override
	public String toString() {
		return "BusEstadoCuentaDTO [corresponsalFolio=" + corresponsalFolio + ", idPagoCorresponsal=" + idPagoCorresponsal +"]";
	}

	public String getUri() {
		return null;
	}

}
