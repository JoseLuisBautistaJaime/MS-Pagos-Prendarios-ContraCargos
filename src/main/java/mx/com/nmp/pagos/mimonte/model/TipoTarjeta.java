package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Nombre: TipoTarjeta
 * Descripcion: Entidad que representa tipo de tarjeta dentro del sistema.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * Fecha: 21/11/2018 17:19 Hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_tipo_tarjeta")
public class TipoTarjeta extends AbstractCatalogo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2483408655570238078L;

	public TipoTarjeta() {
		super();
	}

}
