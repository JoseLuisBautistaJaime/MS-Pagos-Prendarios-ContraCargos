/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name BonificacionDTO
 * @description Clase que encapsula la informacion de un objeto bonificacion
 *
 * @author Jorge Galvez Quintero
 * @creationDate 03/04/2019 12:06 hrs.
 * @version 0.1
 */
public class BonificacionDTO implements Comparable<BonificacionDTO> {

	private Long id;
	private Date fecha;
	private EstatusBonificacionDTO estatus;
	private BigDecimal monto;
	private Long folio;
	private Date createdDate;
	private Date lastModifiedDate;
	private String createdBy;
	private String lastModifiedBy;


	public BonificacionDTO() {		
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public EstatusBonificacionDTO getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusBonificacionDTO estatus) {
		this.estatus = estatus;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}


	@Override
	public int compareTo(BonificacionDTO o) {
		return o.id.compareTo(this.id);
	}

	@Override
	public String toString() {
		return "BonificacionDTO [id=" + id + ", fecha=" + fecha + ", estatus=" + estatus + ", monto=" + monto
				+ ", folio=" + folio + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate
				+ ", createdBy=" + createdBy + ", lastModifiedBy=" + lastModifiedBy + "]";
	}

}
