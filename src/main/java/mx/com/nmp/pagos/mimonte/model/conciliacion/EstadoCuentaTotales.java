/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @name EstadoCuentaTotales
 * 
 * @description Encapsula la informacion de un total de estado de cuenta
 * @author Quarksoft
 * @version 1.0
 * @created 09-May-2019 10:28:45 PM
 */
@Entity
@Table(name = "to_estado_cuenta_totales")
public class EstadoCuentaTotales implements Comparable<EstadoCuentaTotales> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "clave_pais")
	private String clavePais;

	@Column(name = "subcodigo_registro")
	private String subcodigoRegistro;

	@Column(name = "informacion1")
	private String informacion1;

	@Column(name = "informacion2")
	private String informacion2;

	@OneToMany(mappedBy = "totales")
	private Set<EstadoCuenta> estadoCuentaSet;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClavePais() {
		return clavePais;
	}

	public void setClavePais(String clavePais) {
		this.clavePais = clavePais;
	}

	public String getSubcodigoRegistro() {
		return subcodigoRegistro;
	}

	public void setSubcodigoRegistro(String subcodigoRegistro) {
		this.subcodigoRegistro = subcodigoRegistro;
	}

	public String getInformacion1() {
		return informacion1;
	}

	public void setInformacion1(String informacion1) {
		this.informacion1 = informacion1;
	}

	public String getInformacion2() {
		return informacion2;
	}

	public void setInformacion2(String informacion2) {
		this.informacion2 = informacion2;
	}
	
	public Set<EstadoCuenta> getEstadoCuentaSet() {
		return estadoCuentaSet;
	}

	public void setEstadoCuentaSet(Set<EstadoCuenta> estadoCuentaSet) {
		this.estadoCuentaSet = estadoCuentaSet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, clavePais, subcodigoRegistro, informacion1, informacion2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof EstadoCuentaTotales))
			return false;

		final EstadoCuentaTotales other = (EstadoCuentaTotales) obj;
		return (this.hashCode() == other.hashCode());

	}

	@Override
	public int compareTo(EstadoCuentaTotales o) {
		return o.id.compareTo(this.id);
	}

}