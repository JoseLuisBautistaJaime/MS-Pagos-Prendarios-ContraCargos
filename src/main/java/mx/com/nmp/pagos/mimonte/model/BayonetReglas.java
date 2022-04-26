package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @name BayonetReglas
 * @description Entidad que representa las respuestas posibles que llegan de bayonet
 *              y su relacion con  la entidad TipoAutorizacion
 *
 * @author Felix Manuel Galicia Paredes fmgalicia@quarksoft.net
 * @creationDate 21/04/2022 19:45 hrs.
 * @version 0.1
 */


@Entity
@Table(name = "tk_bayonet_reglas")
public class BayonetReglas implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "regla_autorizacion", nullable = false)
	private Integer reglaAutorizacion;
	

	public BayonetReglas() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getReglaAutorizacion() {
		return reglaAutorizacion;
	}

	public void setReglaAutorizacion(Integer reglaAutorizacion) {
		this.reglaAutorizacion = reglaAutorizacion;
	}

	public BayonetReglas(Long id, String status, Integer reglaAutorizacion) {
		super();
		this.id = id;
		this.status = status;
		this.reglaAutorizacion = reglaAutorizacion;
	}

	@Override
	public String toString() {
		return "BayonetReglas [id=" + id + ", status=" + status + ", reglaAutorizacion=" + reglaAutorizacion + "]";
	}
}
