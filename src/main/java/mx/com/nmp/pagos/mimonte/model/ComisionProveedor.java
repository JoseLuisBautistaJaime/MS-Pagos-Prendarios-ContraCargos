/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

/**
 * @Nombre: Comision Proveedor 
 * @Descripcion: Entidad correspondiente a la configuracion de la parametrizacion del porcentaje de comisiones
 * @author Daniel Hernandez
 * @version 0.1
 */
@Entity
@Table(name = "tc_comision_proveedor")
public class ComisionProveedor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "corresponsal", nullable = false)
	private CorresponsalEnum corresponsal;
	
	@Column(name = "comision", nullable = false)
	private BigDecimal comision;
	
	@Column(name = "iva", nullable = false)
	private BigDecimal iva;

	public ComisionProveedor() {
		super();
	}

	public ComisionProveedor(Integer id, CorresponsalEnum corresponsal, BigDecimal comision, BigDecimal iva) {
		super();
		this.id = id;
		this.corresponsal = corresponsal;
		this.comision = comision;
		this.iva = iva;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CorresponsalEnum getCorresponsal() {
		return corresponsal;
	}

	public void setCorresponsal(CorresponsalEnum corresponsal) {
		this.corresponsal = corresponsal;
	}

	public BigDecimal getComision() {
		return comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}


	@Override
	public String toString() {
		return "ComiisionProveedor [id=" + id + ", corresponsal=" + corresponsal.getDescripcion() + ", comision=" + comision + ", iva=" + iva + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, corresponsal);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ComisionProveedor))
			return false;

		final ComisionProveedor other = (ComisionProveedor) obj;
		return (this.hashCode() == other.hashCode());

	}

}
