package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * The persistent class for the to_layout_linea database table.
 * 
 */
@Entity
@Table(name = "to_layout_linea")
public class LayoutLinea extends Updatable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "cuenta")
	private String cuenta;

	@Column(name = "dep_id")
	private String depId;

	@Column(name = "linea")
	private String linea;

	@Column(name = "monto")
	private BigDecimal monto;

	@Column(name = "negocio")
	private String negocio;

	@Column(name = "proyecto_nmp")
	private String proyectoNmp;

	@Column(name = "unidad_operativa")
	private String unidadOperativa;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_layout")
	private Layout layout;

	@Column(name = "nuevo")
	private Boolean nuevo;

	public LayoutLinea() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDepId() {
		return this.depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getLinea() {
		return this.linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public BigDecimal getMonto() {
		return this.monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getNegocio() {
		return this.negocio;
	}

	public void setNegocio(String negocio) {
		this.negocio = negocio;
	}

	public String getProyectoNmp() {
		return this.proyectoNmp;
	}

	public void setProyectoNmp(String proyectoNmp) {
		this.proyectoNmp = proyectoNmp;
	}

	public String getUnidadOperativa() {
		return this.unidadOperativa;
	}

	public void setUnidadOperativa(String unidadOperativa) {
		this.unidadOperativa = unidadOperativa;
	}

	public Layout getLayout() {
		return this.layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public Boolean getNuevo() {
		return nuevo;
	}

	public void setNuevo(Boolean nuevo) {
		this.nuevo = nuevo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
		result = prime * result + ((depId == null) ? 0 : depId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((layout == null) ? 0 : layout.hashCode());
		result = prime * result + ((linea == null) ? 0 : linea.hashCode());
		result = prime * result + ((monto == null) ? 0 : monto.hashCode());
		result = prime * result + ((negocio == null) ? 0 : negocio.hashCode());
		result = prime * result + ((proyectoNmp == null) ? 0 : proyectoNmp.hashCode());
		result = prime * result + ((unidadOperativa == null) ? 0 : unidadOperativa.hashCode());
		result = prime * result + ((nuevo == null) ? 0 : nuevo.hashCode());
		return result;
	}

}