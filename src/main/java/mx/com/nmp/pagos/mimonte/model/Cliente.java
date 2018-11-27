package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.com.nmp.pagos.mimonte.config.Constants;

/**
 * Nombre: Cliente
 * Descripcion: Entidad que representa al cliente dentro del sistema.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * Fecha: 21/11/2018 17:15 Hrs.
 * @version 0.1
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7528374533024645790L;
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente", unique = true, nullable = false)
	private Integer idCliente;
	
	@Column(name="nombreTitular", length = Constants.LONGITUD_NOMBRE_TITULAR)
	private String nombreTitular;
	
	@Column(name="fechaAlta")
	private Date fechaAlta;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="cliente", targetEntity = Pago.class)
	private Set<Pago> transacciones;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "Cliente_Tarjetas", joinColumns = { @JoinColumn(name = "idCliente")}, inverseJoinColumns = {@JoinColumn(name = "token")})
	private List<Tarjetas> tarjetas;
	
	public Cliente() {
		super();
	}

	public Cliente(Integer idCliente, String nombreTitular, Date fechaAlta) {
		super();
		this.idCliente = idCliente;
		this.nombreTitular = nombreTitular;
		this.fechaAlta = fechaAlta;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCliente, nombreTitular, fechaAlta, fechaAlta);
	}

	public Set<Pago> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Set<Pago> transacciones) {
		this.transacciones = transacciones;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public List<Tarjetas> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<Tarjetas> tarjetas) {
		this.tarjetas = tarjetas;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (fechaAlta == null) {
			if (other.fechaAlta != null)
				return false;
		} else if (!fechaAlta.equals(other.fechaAlta))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		if (nombreTitular == null) {
			if (other.nombreTitular != null)
				return false;
		} else if (!nombreTitular.equals(other.nombreTitular))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [idcliente=" + idCliente + ", nombreTitular=" + nombreTitular + ", fechaAlta=" + fechaAlta
				+ "]";
	}

}
