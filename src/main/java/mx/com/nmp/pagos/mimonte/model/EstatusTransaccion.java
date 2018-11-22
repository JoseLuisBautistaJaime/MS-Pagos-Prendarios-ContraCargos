package mx.com.nmp.pagos.mimonte.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Nombre: EstatusTransaccion
 * Descripcion: Entidad que representa un estatus de transaccion en el sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * Fecha: 21/11/2018 16:41 AM
 * @version 0.1
 */
@Entity
@Table(name = "estatus_transaccion_c")
public class EstatusTransaccion extends AbstractCatalogo{
	
	public EstatusTransaccion() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "estatusTransaccion")
    private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
