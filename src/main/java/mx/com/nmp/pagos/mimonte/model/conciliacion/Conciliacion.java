package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.EstatusConciliacion;
import mx.com.nmp.pagos.mimonte.model.Updatable;

/**
 * Registro de conciliacion.
 * 
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:57:43 PM
 */
@Entity
@Table(name = "to_conciliacion")
public class Conciliacion extends Updatable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Transient
	private EstatusConciliacion estatus;

	@Transient
	private Entidad entidad;

	@Transient
	private Cuenta cuenta;

	@Transient
	private String peopleSoftId;

	@Transient
	private Global global;

	@OneToMany(mappedBy = "conciliacion")
	private Set<Reporte> reportes;

	public Conciliacion() {

	}

}