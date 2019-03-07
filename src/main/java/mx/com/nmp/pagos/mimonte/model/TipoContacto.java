package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Nombre: TipoContacto
 * Descripcion: Entidad que representa el tipo de contacto dentro del sistema.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net Fecha: 05/03/2019 12:16 Hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_tipo_contactos")
public class TipoContacto extends AbstractCatalogoAdm implements Serializable{

	private static final long serialVersionUID = 7033742691736781185L;

}
