package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * The persistent class for the tc_layout_header database table.
 * 
 */
@Entity
@Table(name = "tc_layout_header")
public class LayoutHeaderCatalog extends Updatable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cabecera")
	private String cabecera;

	@Column(name = "campo1")
	private String campo1;

	@Column(name = "campo2")
	private String campo2;

	@Column(name = "codigo_origen")
	private String codigoOrigen;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "unidad_negocio")
	private String unidadNegocio;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoLayoutEnum tipo;

	@Enumerated(EnumType.STRING)
	@Column(name = "corresponsal")
	private CorresponsalEnum corresponsal;

	public LayoutHeaderCatalog() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCabecera() {
		return this.cabecera;
	}

	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	public String getCampo1() {
		return this.campo1;
	}

	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	public String getCampo2() {
		return this.campo2;
	}

	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	public String getCodigoOrigen() {
		return this.codigoOrigen;
	}

	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUnidadNegocio() {
		return this.unidadNegocio;
	}

	public void setUnidadNegocio(String unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}

	public TipoLayoutEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoLayoutEnum tipo) {
		this.tipo = tipo;
	}

	public CorresponsalEnum getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(CorresponsalEnum corresponsal) {
		this.corresponsal = corresponsal;
	}

}