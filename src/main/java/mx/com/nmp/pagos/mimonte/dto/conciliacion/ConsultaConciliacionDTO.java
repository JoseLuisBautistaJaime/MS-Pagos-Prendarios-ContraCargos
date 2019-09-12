/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:55 PM
 */
public class ConsultaConciliacionDTO implements Comparable<ConsultaConciliacionDTO> {

	private Long folio;
	private EstatusConciliacionDTO estatus;
	private SubEstatusConciliacionDTO subEstatus;
	private String subEstatusDescripcion;
	private Long idPolizaTesoreria;
	private Long idAsientoContable;
	private Date createdDate;
	private String createdBy;
	private Date lastModifiedDate;
	private String lastModifiedBy;
	private EntidadDTO entidad;
	private CuentaDTO cuenta;
	private Integer numeroMovimientos;

	public ConsultaConciliacionDTO() {
		super();
	}
	
	public ConsultaConciliacionDTO(Long folio, EstatusConciliacionDTO estatus, SubEstatusConciliacionDTO subEstatus,
			String subEstatusDescripcion, Long idPolizaTesoreria, Long idAsientoContable, Date createdDate,
			String createdBy, Date lastModifiedDate, String lastModifiedBy, EntidadDTO entidad, CuentaDTO cuenta,
			Integer numeroMovimientos) {
		super();
		this.folio = folio;
		this.estatus = estatus;
		this.subEstatus = subEstatus;
		this.subEstatusDescripcion = subEstatusDescripcion;
		this.idPolizaTesoreria = idPolizaTesoreria;
		this.idAsientoContable = idAsientoContable;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;
		this.entidad = entidad;
		this.cuenta = cuenta;
		this.numeroMovimientos = numeroMovimientos;
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public EstatusConciliacionDTO getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusConciliacionDTO estatus) {
		this.estatus = estatus;
	}

	public SubEstatusConciliacionDTO getSubEstatus() {
		return subEstatus;
	}

	public void setSubEstatus(SubEstatusConciliacionDTO subEstatus) {
		this.subEstatus = subEstatus;
	}

	public String getSubEstatusDescripcion() {
		return subEstatusDescripcion;
	}

	public void setSubEstatusDescripcion(String subEstatusDescripcion) {
		this.subEstatusDescripcion = subEstatusDescripcion;
	}

	public Long getIdPolizaTesoreria() {
		return idPolizaTesoreria;
	}

	public void setIdPolizaTesoreria(Long idPolizaTesoreria) {
		this.idPolizaTesoreria = idPolizaTesoreria;
	}

	public Long getIdAsientoContable() {
		return idAsientoContable;
	}

	public void setIdAsientoContable(Long idAsientoContable) {
		this.idAsientoContable = idAsientoContable;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public EntidadDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadDTO entidad) {
		this.entidad = entidad;
	}

	public CuentaDTO getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaDTO cuenta) {
		this.cuenta = cuenta;
	}

	public Integer getNumeroMovimientos() {
		return numeroMovimientos;
	}

	public void setNumeroMovimientos(Integer numeroMovimientos) {
		this.numeroMovimientos = numeroMovimientos;
	}

	@Override
	public String toString() {
		return "ConsultaConciliacionDTO [folio=" + folio + ", estatus=" + estatus + ", subEstatus=" + subEstatus
				+ ", subEstatusDescripcion=" + subEstatusDescripcion + ", idPolizaTesoreria=" + idPolizaTesoreria
				+ ", idAsientoContable=" + idAsientoContable + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy
				+ ", entidad=" + entidad + ", cuenta=" + cuenta + ", numeroMovimientos=" + numeroMovimientos + "]";
	}

	@Override
	public int compareTo(ConsultaConciliacionDTO o) {
		return 0;
	}

}