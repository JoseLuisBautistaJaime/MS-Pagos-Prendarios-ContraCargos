/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Nombre: TipoContacto Descripcion: Entidad que representa el tipo de contacto
 * dentro del sistema.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net Fecha: 05/03/2019 12:16 Hrs.
 * @version 0.1
 */
@Entity
@Table(name = "tk_tipo_contacto")
public class TipoContacto extends AbstractCatalogoAdm implements Serializable, Comparable<TipoContacto> {

	private static final long serialVersionUID = 7033742691736781185L;
	
	@OneToMany(mappedBy = "tipoContacto", fetch = FetchType.LAZY)
	private Set<Contactos> contactos;

	public TipoContacto() {
		super();
	}

	public Set<Contactos> getContactos() {
		return contactos;
	}

	public void setContactos(Set<Contactos> contactos) {
		this.contactos = contactos;
	}

	public TipoContacto(Long id) {
		super.id = id;
	}

	public TipoContacto(Long id, Boolean estatus, Date createdDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy, String description, String shortDescription) {
		super(id, estatus, createdDate, lastModifiedDate, createdBy, lastModifiedBy, description, shortDescription);
	}

	@Override
	public int compareTo(TipoContacto o) {
		return o.id.compareTo(this.id);
	}

}
