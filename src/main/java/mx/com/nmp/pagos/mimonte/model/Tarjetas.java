package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mx.com.nmp.pagos.mimonte.config.Constants;
import mx.com.nmp.pagos.mimonte.constans.TarjetaConstants;

/**
 * Nombre: Tarjetas
 * Descripcion: Entidad que representa la tarjeta dentro del sistema.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * Fecha: 21/11/2018 17:19 Hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tarjetas")
public class Tarjetas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4139940644170406428L;

	@Id
	@Size(max = Constants.LONGITUD_ID_OPENPAY, message = TarjetaConstants.MSG_ID_OPENPAY_VALUE_TOO_LONG)
	@Column(name = "id_openpay", unique = true, nullable = false, length = Constants.LONGITUD_TOKEN)
	private String id_openpay;

	@Column(name = "ultimos_digitos", length = Constants.LONGITUD_ULTIMOS_DIGITOS)
	private String ultimosDigitos;
	
	@Column(name = "alias", length = Constants.LONGITUD_ALIAS)
	private String alias;

	@Column(name = "fecha_alta")
	private Date fechaAlta;

	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="id_cliente", nullable = false)
	private Cliente clientes;
	
	@ManyToOne
	@JoinColumn(name = "tipo_tarjeta_c_id")
	private TipoTarjeta tipoTarjeta;

	@ManyToOne
	@JoinColumn(name = "estatus_tarjeta_c")
	private EstatusTarjeta estatusTarjeta;
	
	@Size(max = Constants.LONGITUD_TOKEN, message = TarjetaConstants.MSG_TOKEN_VALUE_TOO_LONG)
	@Column(name = "token")
	private String token;

	public Tarjetas() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUltimosDigitos() {
		return ultimosDigitos;
	}

	public void setUltimosDigitos(String ultimosDigitos) {
		this.ultimosDigitos = ultimosDigitos;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public TipoTarjeta getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}



	public EstatusTarjeta getEstatusTarjeta() {
		return estatusTarjeta;
	}

	public void setEstatusTarjeta(EstatusTarjeta estatusTarjeta) {
		this.estatusTarjeta = estatusTarjeta;
	}
	
	public String getId_openpay() {
		return id_openpay;
	}

	public void setId_openpay(String id_openpay) {
		this.id_openpay = id_openpay;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias, estatusTarjeta, fechaAlta, fechaModificacion, tipoTarjeta, token,
				ultimosDigitos, id_openpay);
	}

	public Cliente getClientes() {
		return clientes;
	}

	public void setClientes(Cliente clientes) {
		this.clientes = clientes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjetas other = (Tarjetas) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (estatusTarjeta == null) {
			if (other.estatusTarjeta != null)
				return false;
		} else if (!estatusTarjeta.equals(other.estatusTarjeta))
			return false;
		if (fechaAlta == null) {
			if (other.fechaAlta != null)
				return false;
		} else if (!fechaAlta.equals(other.fechaAlta))
			return false;
		if (fechaModificacion == null) {
			if (other.fechaModificacion != null)
				return false;
		} else if (!fechaModificacion.equals(other.fechaModificacion))
			return false;
		if (tipoTarjeta == null) {
			if (other.tipoTarjeta != null)
				return false;
		} else if (!tipoTarjeta.equals(other.tipoTarjeta))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (ultimosDigitos == null) {
			if (other.ultimosDigitos != null)
				return false;
		} else if (!ultimosDigitos.equals(other.ultimosDigitos))
			return false;
		if (id_openpay == null) {
			if (other.id_openpay != null)
				return false;
		} else if (!id_openpay.equals(other.id_openpay))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tarjetas [id_openpay=" + id_openpay + ", ultimosDigitos=" + ultimosDigitos + ", alias=" + alias + ", fechaAlta="
				+ fechaAlta + ", fechaModificacion=" + fechaModificacion + ", tipoTarjeta="
				+ tipoTarjeta + ", estatusTarjeta=" + estatusTarjeta + ", token=" + token + "]";
	}

}