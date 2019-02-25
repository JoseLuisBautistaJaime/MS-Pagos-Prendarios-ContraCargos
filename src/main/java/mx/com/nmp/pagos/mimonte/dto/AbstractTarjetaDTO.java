package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import mx.com.nmp.pagos.mimonte.config.Constants;
import mx.com.nmp.pagos.mimonte.constans.TarjetaConstants;

/**
 * Nombre: AbstractTarjetaDTO Descripcion: Clase abstracta que representa las
 * propiedades generales de una tarjeta
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net Fecha: 22/11/2018 20:27 Hrs.
 * @version 0.1
 */
@MappedSuperclass
public abstract class AbstractTarjetaDTO {

	@Size(max = Constants.LONGITUD_ID_OPENPAY, message = TarjetaConstants.MSG_ID_OPENPAY_VALUE_TOO_LONG)
	protected  String id_openpay;

	protected  String digitos;

	protected  String alias;

	protected  Date fechaAlta;

	protected  Date fechaModificacion;

	protected  TipoTarjetaDTO tipo;

	protected  EstatusTarjetaDTO estatus;
	
	@Size(max = Constants.LONGITUD_TOKEN, message = TarjetaConstants.MSG_TOKEN_VALUE_TOO_LONG)
	protected  String token;

	public AbstractTarjetaDTO() {
		super();
	}

	public AbstractTarjetaDTO(String id_openpay, String digitos, String alias, Date fechaAlta, Date fechaModificacion,
			TipoTarjetaDTO tipo, EstatusTarjetaDTO estatus, String token) {
		super();
		this.id_openpay = id_openpay;
		this.digitos = digitos;
		this.alias = alias;
		this.fechaAlta = fechaAlta;
		this.fechaModificacion = fechaModificacion;
		this.tipo = tipo;
		this.estatus = estatus;
		this.token = token;
	}

	public TipoTarjetaDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoTarjetaDTO tipo) {
		this.tipo = tipo;
	}

	public EstatusTarjetaDTO getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusTarjetaDTO estatus) {
		this.estatus = estatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getDigitos() {
		return digitos;
	}

	public void setDigitos(String digitos) {
		this.digitos = digitos;
	}
	
	
	public String getId_openpay() {
		return id_openpay;
	}

	public void setId_openpay(String id_openpay) {
		this.id_openpay = id_openpay;
	}

	@Override
	public String toString() {
		return "TarjetaDTO [id_openpay=" + id_openpay + ", digitos=" + digitos + ", alias=" + alias + ", fechaAlta=" + fechaAlta
				+ ", fechaModificacion=" + fechaModificacion + ", tipo=" + tipo + ", estatus=" + estatus + ", token=" + token + "]";
	}

}
