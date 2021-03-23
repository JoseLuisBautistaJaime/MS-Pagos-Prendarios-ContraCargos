/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.consumer.rest.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @name BusRestConsultaPagosResponseDTO
 * @version 1.0
 * @createdDate 04/06/2019 22:55 hrs.
 */
public class BusRestConsultaPagosResponseDTO {

	private List<Pagos> pagos;
	
	public static class Pagos {
		private List<Partidas> partidas;

		public List<Partidas> getPartidas() {
			return partidas;
		}

		public void setPartidas(List<Partidas> partidas) {
			this.partidas = partidas;
		}
	};
	
	public static class Partidas {
		private long idPartida;
		private String operacion;
		private String concepto;
		private BigDecimal importeMinimo;
		private BigDecimal importeTope;
		private String tipoContrato;
		private Contrato contrato;
		private BigDecimal importeTransaccion;
		private Integer sucursal;
		
		public static class Contrato {
			private String idTipoContrato;
			private String tipoContratoDesc;
			public String getIdTipoContrato() {
				return idTipoContrato;
			}
			public void setIdTipoContrato(String idTipoContrato) {
				this.idTipoContrato = idTipoContrato;
			}
			public String getTipoContratoDesc() {
				return tipoContratoDesc;
			}
			public void setTipoContratoDesc(String tipoContratoDesc) {
				this.tipoContratoDesc = tipoContratoDesc;
			}
		}

		public long getIdPartida() {
			return idPartida;
		}

		public void setIdPartida(long idPartida) {
			this.idPartida = idPartida;
		}

		public String getOperacion() {
			return operacion;
		}

		public void setOperacion(String operacion) {
			this.operacion = operacion;
		}

		public String getConcepto() {
			return concepto;
		}

		public void setConcepto(String concepto) {
			this.concepto = concepto;
		}

		public BigDecimal getImporteMinimo() {
			return importeMinimo;
		}

		public void setImporteMinimo(BigDecimal importeMinimo) {
			this.importeMinimo = importeMinimo;
		}

		public BigDecimal getImporteTope() {
			return importeTope;
		}

		public void setImporteTope(BigDecimal importeTope) {
			this.importeTope = importeTope;
		}

		public String getTipoContrato() {
			return tipoContrato;
		}

		public void setTipoContrato(String tipoContrato) {
			this.tipoContrato = tipoContrato;
		}

		public Contrato getContrato() {
			return contrato;
		}

		public void setContrato(Contrato contrato) {
			this.contrato = contrato;
		}

		public Integer getSucursal() {
			return sucursal;
		}

		public void setSucursal(Integer sucursal) {
			this.sucursal = sucursal;
		}

		public BigDecimal getImporteTransaccion() {
			return importeTransaccion;
		}

		public void setImporteTransaccion(BigDecimal importeTransaccion) {
			this.importeTransaccion = importeTransaccion;
		}
	}

	public List<Pagos> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pagos> pagos) {
		this.pagos = pagos;
	}

	public BusRestConsultaPagosResponseDTO() {
		super();
	}

}
