package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import mx.com.nmp.pagos.mimonte.config.Constants;

import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "token", unique = true, nullable = false, length = Constants.LONGITUD_TOKEN)
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
	@JoinColumn(name = "idCliente")
	private List<Cliente> cliente;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private List<TipoTarjeta> tipoTarjeta;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private List<EstatusTarjeta> estatusTarjeta;

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

	public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
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
		return Objects.hash(alias, estatusTarjeta, fechaAlta, fechaModificacion, cliente, tipoTarjeta, token,
				ultimosDigitos);
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
		return true;
	}

	@Override
	public String toString() {
		return "Tarjetas [token=" + token + ", ultimosDigitos=" + ultimosDigitos + ", alias=" + alias + ", fechaAlta="
				+ fechaAlta + ", fechaModificacion=" + fechaModificacion + ", idcliente=" + cliente + ", tipoTarjeta="
				+ tipoTarjeta + ", estatusTarjeta=" + estatusTarjeta + "]";
	}

}