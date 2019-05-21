package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.model.EstatusTransito;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:53 PM
 */
@Entity
@Table(name = "to_movimiento_transito")
public class MovimientoTransito extends MovimientoConciliacion implements Serializable{

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -145696842279859203L;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "estatus")
	private EstatusTransito estatus;
	
	@Column(name = "folio")
	private Integer folio;
	
	@Column(name = "sucursal")
	private String sucursal;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "operacion_desc")
	private String operacionDesc;
	
	@Column(name = "monto")
	private BigDecimal monto;
	
	@Column(name = "tipo_contrato_desc")
	private String tipoContratoDesc;
	
	@Column(name = "esquema_tarjeta")
	private String esquemaTarjeta;
	
	@Column(name = "cuenta")
	private String cuenta;
	
	@Column(name = "titular")
	private String titular;
	
	@OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name = "id_conciliacion")
	private Set<MovimientoConciliacion> movimientoConciliacionSet;

	public MovimientoTransito() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovimientoTransito(EstatusTransito estatus, Integer folio, String sucursal, Date fecha,
			String operacionDesc, BigDecimal monto, String tipoContratoDesc, String esquemaTarjeta, String cuenta,
			String titular) {
		super();
		this.estatus = estatus;
		this.folio = folio;
		this.sucursal = sucursal;
		this.fecha = fecha;
		this.operacionDesc = operacionDesc;
		this.monto = monto;
		this.tipoContratoDesc = tipoContratoDesc;
		this.esquemaTarjeta = esquemaTarjeta;
		this.cuenta = cuenta;
		this.titular = titular;
	}

	public EstatusTransito getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusTransito estatus) {
		this.estatus = estatus;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getOperacionDesc() {
		return operacionDesc;
	}

	public void setOperacionDesc(String operacionDesc) {
		this.operacionDesc = operacionDesc;
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

	public String getEsquemaTarjeta() {
		return esquemaTarjeta;
	}

	public void setEsquemaTarjeta(String esquemaTarjeta) {
		this.esquemaTarjeta = esquemaTarjeta;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
		result = prime * result + ((esquemaTarjeta == null) ? 0 : esquemaTarjeta.hashCode());
		result = prime * result + ((estatus == null) ? 0 : estatus.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((folio == null) ? 0 : folio.hashCode());
		result = prime * result + ((monto == null) ? 0 : monto.hashCode());
		result = prime * result + ((operacionDesc == null) ? 0 : operacionDesc.hashCode());
		result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
		result = prime * result + ((tipoContratoDesc == null) ? 0 : tipoContratoDesc.hashCode());
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimientoTransito other = (MovimientoTransito) obj;
		if (cuenta == null) {
			if (other.cuenta != null)
				return false;
		} else if (!cuenta.equals(other.cuenta))
			return false;
		if (esquemaTarjeta == null) {
			if (other.esquemaTarjeta != null)
				return false;
		} else if (!esquemaTarjeta.equals(other.esquemaTarjeta))
			return false;
		if (estatus == null) {
			if (other.estatus != null)
				return false;
		} else if (!estatus.equals(other.estatus))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (folio == null) {
			if (other.folio != null)
				return false;
		} else if (!folio.equals(other.folio))
			return false;
		if (monto == null) {
			if (other.monto != null)
				return false;
		} else if (!monto.equals(other.monto))
			return false;
		if (operacionDesc == null) {
			if (other.operacionDesc != null)
				return false;
		} else if (!operacionDesc.equals(other.operacionDesc))
			return false;
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		if (tipoContratoDesc == null) {
			if (other.tipoContratoDesc != null)
				return false;
		} else if (!tipoContratoDesc.equals(other.tipoContratoDesc))
			return false;
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MovimientoTransito [estatus=" + estatus + ", folio=" + folio + ", sucursal=" + sucursal
				+ ", fecha=" + fecha + ", operacionDesc=" + operacionDesc + ", monto=" + monto + ", tipoContratoDesc="
				+ tipoContratoDesc + ", esquemaTarjeta=" + esquemaTarjeta + ", cuenta=" + cuenta + ", titular="
				+ titular + "]";
	}

}