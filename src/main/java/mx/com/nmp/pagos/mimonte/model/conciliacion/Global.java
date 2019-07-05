package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:46 PM
 */
@Entity
@Table(name = "to_global")
public class Global implements Serializable{

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -5370143232645303205L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "movimientos")
	private Integer movmientos;
	
	@Column(name = "partidas")
	private Integer partidas;
	
	@Column(name = "monto")
	private BigDecimal monto;
	
	@Column(name = "importe_midas")
	private BigDecimal importeMidas;
	
	@Column(name = "importe_proveedor")
	private BigDecimal importeProveedor;
	
	@Column(name = "importe_banco")
	private BigDecimal importeBanco;
	
	@Column(name = "importe_devoluciones")
	private BigDecimal importeDevoluciones;
	
	@Column(name = "devoluciones")
	private Integer devoluciones;
	
	@Column(name = "diferencia_proveedor_midas")
	private BigDecimal diferenciaProveedorMidas;
	
	@Column(name = "diferencia_proveedor_banco")
	private BigDecimal diferenciaProveedorBanco;
	
	@ManyToOne(fetch=FetchType.EAGER/*, cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}*/)
	@JoinColumn(name = "id_conciliacion"/*,  insertable = false, updatable = false*/)
	private Conciliacion conciliacion;

	public Global() {
		super();
	}

	public Global(Long id, Date fecha, Integer movmientos, Integer partidas, BigDecimal monto, BigDecimal importeMidas,
			BigDecimal importeProveedor, BigDecimal importeBanco, BigDecimal importeDevoluciones, Integer devoluciones,
			BigDecimal diferenciaProveedorMidas, BigDecimal diferenciaProveedorBanco, Conciliacion conciliacion
			) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.movmientos = movmientos;
		this.partidas = partidas;
		this.monto = monto;
		this.importeMidas = importeMidas;
		this.importeProveedor = importeProveedor;
		this.importeBanco = importeBanco;
		this.importeDevoluciones = importeDevoluciones;
		this.devoluciones = devoluciones;
		this.diferenciaProveedorMidas = diferenciaProveedorMidas;
		this.diferenciaProveedorBanco = diferenciaProveedorBanco;
		this.conciliacion = conciliacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getMovmientos() {
		return movmientos;
	}

	public void setMovmientos(Integer movmientos) {
		this.movmientos = movmientos;
	}

	public Integer getPartidas() {
		return partidas;
	}

	public void setPartidas(Integer partidas) {
		this.partidas = partidas;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public BigDecimal getImporteMidas() {
		return importeMidas;
	}

	public void setImporteMidas(BigDecimal importeMidas) {
		this.importeMidas = importeMidas;
	}

	public BigDecimal getImporteProveedor() {
		return importeProveedor;
	}

	public void setImporteProveedor(BigDecimal importeProveedor) {
		this.importeProveedor = importeProveedor;
	}

	public BigDecimal getImporteBanco() {
		return importeBanco;
	}

	public void setImporteBanco(BigDecimal importeBanco) {
		this.importeBanco = importeBanco;
	}

	public BigDecimal getImporteDevoluciones() {
		return importeDevoluciones;
	}

	public void setImporteDevoluciones(BigDecimal importeDevoluciones) {
		this.importeDevoluciones = importeDevoluciones;
	}

	public Integer getDevoluciones() {
		return devoluciones;
	}

	public void setDevoluciones(Integer devoluciones) {
		this.devoluciones = devoluciones;
	}

	public BigDecimal getDiferenciaProveedorMidas() {
		return diferenciaProveedorMidas;
	}

	public void setDiferenciaProveedorMidas(BigDecimal diferenciaProveedorMidas) {
		this.diferenciaProveedorMidas = diferenciaProveedorMidas;
	}

	public BigDecimal getDiferenciaProveedorBanco() {
		return diferenciaProveedorBanco;
	}

	public void setDiferenciaProveedorBanco(BigDecimal diferenciaProveedorBanco) {
		this.diferenciaProveedorBanco = diferenciaProveedorBanco;
	}

	public Conciliacion getConciliacion() {
		return conciliacion;
	}

	public void setConciliacion(Conciliacion conciliacion) {
		this.conciliacion = conciliacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conciliacion == null) ? 0 : conciliacion.hashCode());
		result = prime * result + ((devoluciones == null) ? 0 : devoluciones.hashCode());
		result = prime * result + ((diferenciaProveedorBanco == null) ? 0 : diferenciaProveedorBanco.hashCode());
		result = prime * result + ((diferenciaProveedorMidas == null) ? 0 : diferenciaProveedorMidas.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((importeBanco == null) ? 0 : importeBanco.hashCode());
		result = prime * result + ((importeDevoluciones == null) ? 0 : importeDevoluciones.hashCode());
		result = prime * result + ((importeMidas == null) ? 0 : importeMidas.hashCode());
		result = prime * result + ((importeProveedor == null) ? 0 : importeProveedor.hashCode());
		result = prime * result + ((monto == null) ? 0 : monto.hashCode());
		result = prime * result + ((movmientos == null) ? 0 : movmientos.hashCode());
		result = prime * result + ((partidas == null) ? 0 : partidas.hashCode());
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
		Global other = (Global) obj;
		if (conciliacion == null) {
			if (other.conciliacion != null)
				return false;
		} else if (!conciliacion.equals(other.conciliacion))
			return false;
		if (devoluciones == null) {
			if (other.devoluciones != null)
				return false;
		} else if (!devoluciones.equals(other.devoluciones))
			return false;
		if (diferenciaProveedorBanco == null) {
			if (other.diferenciaProveedorBanco != null)
				return false;
		} else if (!diferenciaProveedorBanco.equals(other.diferenciaProveedorBanco))
			return false;
		if (diferenciaProveedorMidas == null) {
			if (other.diferenciaProveedorMidas != null)
				return false;
		} else if (!diferenciaProveedorMidas.equals(other.diferenciaProveedorMidas))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (importeBanco == null) {
			if (other.importeBanco != null)
				return false;
		} else if (!importeBanco.equals(other.importeBanco))
			return false;
		if (importeDevoluciones == null) {
			if (other.importeDevoluciones != null)
				return false;
		} else if (!importeDevoluciones.equals(other.importeDevoluciones))
			return false;
		if (importeMidas == null) {
			if (other.importeMidas != null)
				return false;
		} else if (!importeMidas.equals(other.importeMidas))
			return false;
		if (importeProveedor == null) {
			if (other.importeProveedor != null)
				return false;
		} else if (!importeProveedor.equals(other.importeProveedor))
			return false;
		if (monto == null) {
			if (other.monto != null)
				return false;
		} else if (!monto.equals(other.monto))
			return false;
		if (movmientos == null) {
			if (other.movmientos != null)
				return false;
		} else if (!movmientos.equals(other.movmientos))
			return false;
		if (partidas == null) {
			if (other.partidas != null)
				return false;
		} else if (!partidas.equals(other.partidas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Global [id=" + id + ", fecha=" + fecha + ", movmientos=" + movmientos + ", partidas=" + partidas
				+ ", monto=" + monto + ", importeMidas=" + importeMidas + ", importeProveedor=" + importeProveedor
				+ ", importeBanco=" + importeBanco + ", importeDevoluciones=" + importeDevoluciones + ", devoluciones="
				+ devoluciones + ", diferenciaProveedorMidas=" + diferenciaProveedorMidas
				+ ", diferenciaProveedorBanco=" + diferenciaProveedorBanco + ", conciliacion=" + conciliacion + "]";
	}

	
	
}