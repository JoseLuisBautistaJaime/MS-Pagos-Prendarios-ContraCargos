/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.Date;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:55 PM
 */
public class ConsultaConciliacionDTO implements Comparable<ConsultaConciliacionDTO> {

	private Long folio;
	private Long folioConciliacion;
	private EstatusConciliacionDTO estatus;
	private SubEstatusConciliacionDTO subEstatus;
	private String subEstatusDescripcion;
	private String idPolizaTesoreria;
	private String idAsientoContable;
	private Date createdDate;
	private String createdBy;
	private Date lastModifiedDate;
	private String lastModifiedBy;
	private EntidadDTO entidad;
	private CuentaDTO cuenta;
	private Integer numeroMovimientos;
	private CorresponsalEnum idCorresponsal;

	public ConsultaConciliacionDTO() {
		super();
	}
	
	public ConsultaConciliacionDTO(Long folio, Long folioConciliacion, EstatusConciliacionDTO estatus, SubEstatusConciliacionDTO subEstatus,
			String subEstatusDescripcion, String idPolizaTesoreria, String idAsientoContable, Date createdDate,
			String createdBy, Date lastModifiedDate, String lastModifiedBy, EntidadDTO entidad, CuentaDTO cuenta,
			Integer numeroMovimientos, CorresponsalEnum idCorresponsal) {
		super();
		this.folio = folio;
		this.folioConciliacion = folioConciliacion;
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
		this.idCorresponsal = idCorresponsal;
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

	public String getIdPolizaTesoreria() {
		return idPolizaTesoreria;
	}

	public void setIdPolizaTesoreria(String idPolizaTesoreria) {
		this.idPolizaTesoreria = idPolizaTesoreria;
	}

	public String getIdAsientoContable() {
		return idAsientoContable;
	}

	public void setIdAsientoContable(String idAsientoContable) {
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

	public Long getFolioConciliacion() {
		return folioConciliacion;
	}

	public void setFolioConciliacion(Long folioConciliacion) {
		this.folioConciliacion = folioConciliacion;
	}

	public CorresponsalEnum getIdCorresponsal() {
		return idCorresponsal;
	}

	public void setIdCorresponsal(CorresponsalEnum idCorresponsal) {
		this.idCorresponsal = idCorresponsal;
	}

}