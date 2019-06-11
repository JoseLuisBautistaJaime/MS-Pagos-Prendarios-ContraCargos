/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @name SolicitarPagosMailDataDTO
 * @description Clase tipo DTO que se encarga de mapear la respuesta de la
 *              consulta de solicitud de pagos
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 04/06/2019 17:35 hrs.
 * @version 0.1
 */
public class SolicitarPagosMailDataDTO implements java.io.Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

	private Integer folio;
	private Integer sucursal;
	private Date fecha;
	private BigDecimal monto;
	private String tipoContratoDesc;
	private String cuenta;
	private String titular;

	public SolicitarPagosMailDataDTO() {
		super();
	}

	public SolicitarPagosMailDataDTO(Integer folio, Integer sucursal, Date fecha, BigDecimal monto,
			String tipoContratoDesc, String cuenta, String titular) {
		super();
		this.folio = folio;
		this.sucursal = sucursal;
		this.fecha = fecha;
		this.monto = monto;
		this.tipoContratoDesc = tipoContratoDesc;
		this.cuenta = cuenta;
		this.titular = titular;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getTipoContratoDesc() {
		return tipoContratoDesc;
	}

	public void setTipoContratoDesc(String tipoContratoDesc) {
		this.tipoContratoDesc = tipoContratoDesc;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	@Override
	public int hashCode() {
		return Objects.hash(folio, sucursal, fecha, monto, tipoContratoDesc, cuenta, titular);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof SolicitarPagosMailDataDTO))
			return false;

		final SolicitarPagosMailDataDTO other = (SolicitarPagosMailDataDTO) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public String toString() {
		return "SolicitarPagosMailDataDTO [folio=" + folio + ", sucursal=" + sucursal + ", fecha=" + fecha + ", monto="
				+ monto + ", tipoContratoDesc=" + tipoContratoDesc + ", cuenta=" + cuenta + ", titular=" + titular
				+ "]";
	}

}
