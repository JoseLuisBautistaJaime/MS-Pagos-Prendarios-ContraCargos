package mx.com.nmp.pagos.mimonte.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Nombre: Catalogo
 * Descripcion: Entidad que representa un catalogo dentro del sistema.
 *
 * @author Javier Hernandez jhernandez@quarksoft.net
 * Fecha: 22/02/2018 11:03 AM
 * @version 0.1
 */
@Entity
@Table(name = "estatus_transaccion_c")
public class EstatusTransaccion extends AbstractCatalogo{

	public EstatusTransaccion() {
		super();
	}
	
}
