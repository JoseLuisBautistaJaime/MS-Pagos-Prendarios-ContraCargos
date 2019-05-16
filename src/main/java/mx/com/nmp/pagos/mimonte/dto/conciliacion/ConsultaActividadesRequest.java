/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @name ConsultaActividadesRequest
 * @description Clase que encapsula el request de ConsultaActividadesRequest para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 15/05/2019 22:12 hrs.
 * @version 0.1
 */
public class ConsultaActividadesRequest implements Comparable<ConsultaActividadesRequest>{

	    private Integer folio;
	    private Date fechaDesde;
	    private Date fechaHasta;
	    private Integer resultados;
	    private Integer pagina;

	public ConsultaActividadesRequest() {
			super();
			// TODO Auto-generated constructor stub
		}

	public ConsultaActividadesRequest(Integer folio, Date fechaDesde, Date fechaHasta, Integer resultados,
			Integer pagina) {
		super();
		this.folio = folio;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.resultados = resultados;
		this.pagina = pagina;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Integer getResultados() {
		return resultados;
	}

	public void setResultados(Integer resultados) {
		this.resultados = resultados;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	@Override
	public String toString() {
		return "ConsultaActividadesRequest [folio=" + folio + ", fechaDesde=" + fechaDesde + ", fechaHasta="
				+ fechaHasta + ", resultados=" + resultados + ", pagina=" + pagina + "]";
	}

	@Override
	public int compareTo(ConsultaActividadesRequest o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
