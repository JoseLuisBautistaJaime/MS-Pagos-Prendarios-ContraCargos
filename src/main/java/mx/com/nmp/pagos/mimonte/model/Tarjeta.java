package mx.com.nmp.pagos.mimonte.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import mx.com.nmp.pagos.mimonte.config.Constants;

/**
 * Nombre: Tarjeta
 * Descripcion: Clase abstracta que representa las propiedades generales de una tarjeta.
 *
 * @author Javier Hernandez jhernandez@quarksoft.net
 * Fecha: 23/11/2018 10:43 Hrs.
 * @version 0.1
 */
@MappedSuperclass
public abstract class Tarjeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "token", unique = true, nullable = false, length = Constants.LONGITUD_TOKEN)
	@Size(max = Constants.LONGITUD_TOKEN)
	private String token;

	@Column(name = "ultimos_digitos", length = Constants.LONGITUD_ULTIMOS_DIGITOS)
	private String ultimosDigitos;

	@Column(name = "alias", length = Constants.LONGITUD_ALIAS)
	private String alias;

	@Column(name = "fecha_alta")
	private Date fechaAlta;

	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private List<TipoTarjeta> tipoTarjeta;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private List<EstatusTarjeta> estatusTarjeta;

	public Tarjeta() {
		super();
	}

	public Tarjeta(String token, String ultimosDigitos, String alias, Date fechaAlta, Date fechaModificacion,
			List<TipoTarjeta> tipoTarjeta, List<EstatusTarjeta> estatusTarjeta) {
		super();
		this.token = token;
		this.ultimosDigitos = ultimosDigitos;
		this.alias = alias;
		this.fechaAlta = fechaAlta;
		this.fechaModificacion = fechaModificacion;
		this.tipoTarjeta = tipoTarjeta;
		this.estatusTarjeta = estatusTarjeta;
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

	public List<TipoTarjeta> getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(List<TipoTarjeta> tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public List<EstatusTarjeta> getEstatusTarjeta() {
		return estatusTarjeta;
	}

	public void setEstatusTarjeta(List<EstatusTarjeta> estatusTarjeta) {
		this.estatusTarjeta = estatusTarjeta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias, estatusTarjeta, fechaAlta, fechaModificacion, tipoTarjeta, token, ultimosDigitos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjeta other = (Tarjeta) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "Tarjeta [token=" + token + ", ultimosDigitos=" + ultimosDigitos + ", alias=" + alias + ", fechaAlta="
				+ fechaAlta + ", fechaModificacion=" + fechaModificacion + ", tipoTarjeta=" + tipoTarjeta
				+ ", estatusTarjeta=" + estatusTarjeta + "]";
	}

}
