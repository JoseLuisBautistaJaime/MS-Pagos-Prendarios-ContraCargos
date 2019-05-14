/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @name EstadoCuenta
 * 
 * @description encapsula informacion de un estado de cuenta
 * @author Quarksoft
 * @version 1.0
 * @created 09-May-2019 10:28:41 PM
 */
@Entity
@Table(name = "to_estado_cuenta")

public class EstadoCuenta implements Comparable<EstadoCuenta> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "reporte")
	private Long idReporte;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cabecera", referencedColumnName = "id")
	private EstadoCuentaCabecera cabecera;

	@ManyToOne
	@JoinColumn(name = "totales", referencedColumnName = "id")
	private EstadoCuentaTotales totales;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "totales_adicional", referencedColumnName = "id")
	private EstadoCuentaTotalesAdicional totalesAdicional;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_carga")
	private Date fechaCarga;

	@Column(name = "total_movimientos")
	private Integer totalMovimientos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	public EstadoCuentaCabecera getCabecera() {
		return cabecera;
	}

	public void setCabecera(EstadoCuentaCabecera cabecera) {
		this.cabecera = cabecera;
	}

	public EstadoCuentaTotales getTotales() {
		return totales;
	}

	public void setTotales(EstadoCuentaTotales totales) {
		this.totales = totales;
	}

	public EstadoCuentaTotalesAdicional getTotalesAdicional() {
		return totalesAdicional;
	}

	public void setTotalesAdicional(EstadoCuentaTotalesAdicional totalesAdicional) {
		this.totalesAdicional = totalesAdicional;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public Integer getTotalMovimientos() {
		return totalMovimientos;
	}

	public void setTotalMovimientos(Integer totalMovimientos) {
		this.totalMovimientos = totalMovimientos;
	}

	@Override
	public String toString() {
		return "EstadoCuenta [id=" + id + ", idReporte=" + idReporte + ", cabecera=" + cabecera + ", totales=" + totales
				+ ", totalesAdicional=" + totalesAdicional + ", fechaCarga=" + fechaCarga + ", totalMovimientos="
				+ totalMovimientos + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idReporte, cabecera, totales, totalesAdicional, fechaCarga, totalMovimientos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof EstadoCuenta))
			return false;

		final EstadoCuenta other = (EstadoCuenta) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(EstadoCuenta arg0) {
		return arg0.id.compareTo(this.id);
	}

}