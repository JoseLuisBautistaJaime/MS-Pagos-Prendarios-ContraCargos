package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_tarjeta_c")
public class TipoTarjeta extends AbstractCatalogo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2483408655570238078L;

}
