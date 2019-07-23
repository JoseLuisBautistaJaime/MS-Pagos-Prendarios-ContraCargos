/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.math.BigDecimal;
import java.util.List;

import mx.com.nmp.pagos.mimonte.model.Pago;

/**
 * @name PagoPartidasDTO
 * @description Clase que encapsula la informacion perteneciente a las partidas
 *              asociadas a un pago.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 17/07/2019 12:00 hrs.
 * @version 0.1
 */
public class PagoPartidasDTO {

	private Long id;
	private Long folioPartida;
	private Integer idOperacion;
	private String nombreOperacion;
	private BigDecimal monto;
	private List<Pago> pagos;

	public PagoPartidasDTO(Long id, Long folioPartida, Integer idOperacion, String nombreOperacion, BigDecimal monto,
			List<Pago> pagos) {
		super();
		this.id = id;
		this.folioPartida = folioPartida;
		this.idOperacion = idOperacion;
		this.nombreOperacion = nombreOperacion;
		this.monto = monto;
		this.pagos = pagos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFolioPartida() {
		return folioPartida;
	}

	public void setFolioPartida(Long folioPartida) {
		this.folioPartida = folioPartida;
	}

	public Integer getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public String getNombreOperacion() {
		return nombreOperacion;
	}

	public void setNombreOperacion(String nombreOperacion) {
		this.nombreOperacion = nombreOperacion;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	@Override
	public String toString() {
		return "PagoPartidasDTO [id=" + id + ", folioPartida=" + folioPartida + ", idOperacion=" + idOperacion
				+ ", nombreOperacion=" + nombreOperacion + ", monto=" + monto + ", pagos=" + pagos + "]";
	}

}
