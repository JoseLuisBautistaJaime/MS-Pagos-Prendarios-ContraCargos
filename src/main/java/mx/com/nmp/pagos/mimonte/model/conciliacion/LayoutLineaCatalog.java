package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.util.Objects;

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
 * The persistent class for the tc_layout_linea database table.
 * 
 */
@Entity
@Table(name = "tc_layout_linea")
public class LayoutLineaCatalog extends Updatable implements Serializable {
	private static final long serialVersionUID = -5776981826554339715L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cuenta")
	private String cuenta;

	@Column(name = "dep_id")
	private String depId;

	@Column(name = "linea")
	private String linea;

	@Column(name = "negocio")
	private String negocio;

	@Column(name = "proyecto_nmp")
	private String proyectoNmp;

	@Column(name = "unidad_operativa")
	private String unidadOperativa;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoLayoutEnum tipo;

	@Enumerated(EnumType.STRING)
	@Column(name = "grupo")
	private GrupoLayoutEnum grupo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "corresponsal")
	private CorresponsalEnum corresponsal;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "operacion")
	private OperacionLayoutEnum operacion;


	public LayoutLineaCatalog() {
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

	public TipoLayoutEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoLayoutEnum tipo) {
		this.tipo = tipo;
	}

	public GrupoLayoutEnum getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoLayoutEnum grupo) {
		this.grupo = grupo;
	}
	
	public CorresponsalEnum getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(CorresponsalEnum corresponsal) {
		this.corresponsal = corresponsal;
	}

	public OperacionLayoutEnum getOperacion() {
		return operacion;
	}

	public void setOperacion(OperacionLayoutEnum operacion) {
		this.operacion = operacion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cuenta, depId, linea, negocio, proyectoNmp, unidadOperativa, tipo, grupo, operacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LayoutLineaCatalog other = (LayoutLineaCatalog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}