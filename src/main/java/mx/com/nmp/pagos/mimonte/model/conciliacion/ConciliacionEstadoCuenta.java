/*
 * Proyecto:        NMP - MI MONTE - OXXO.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @name tr_conciliacion_estado_cuenta
 * @description Asocia un estado de cuenta a varias conciliaciones
 * 
 * @author Quarksoft
 * @version 1.0
 * @created 19-Oct-2020
 */
@Entity
@Table(name = "tr_conciliacion_estado_cuenta")
public class ConciliacionEstadoCuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "id_conciliacion", nullable = false)
	private Long idConciliacion;

	@Column(name = "id_reporte", nullable = false)
	private Long idReporte;


	public ConciliacionEstadoCuenta() {
		super();
	}
	
	public ConciliacionEstadoCuenta(Long idConciliacion, Long idReporte) {
		super();
		this.idConciliacion = idConciliacion;
		this.idReporte = idReporte;
	}

	public Long getIdConciliacion() {
		return idConciliacion;
	}

	public void setIdConciliacion(Long idConciliacion) {
		this.idConciliacion = idConciliacion;
	}

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}


	@Override
	public int hashCode() {
		return Objects.hash(idConciliacion, idReporte);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ConciliacionEstadoCuenta))
			return false;

		final ConciliacionEstadoCuenta other = (ConciliacionEstadoCuenta) obj;
		return (this.hashCode() == other.hashCode());
	}

	@Override
	public String toString() {
		return "ConciliacionEstadoCuenta [idConciliacion=" + idConciliacion + ", idReporte=" + idReporte + "]";
	}

}