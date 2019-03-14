package mx.com.nmp.pagos.mimonte.builder;

import java.util.Date;

import mx.com.nmp.pagos.mimonte.dto.ContactoBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoRespDTO;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.TipoContacto;

/**
 * Nombre: ContactosBuilder Descripcion: Clase de capa de builder que se encarga
 * de convertir difrentes tipos de objetos y entidades relacionadas con el
 * catalogo Contacto
 *
 * @author José Rodríguez jgrodriguez@qaurksoft.net
 * @creationDate 12/03/2019 10:51 hrs.
 * @version 0.1
 */
public abstract class ContactosBuilder {

	private ContactosBuilder() {
		super();
	}

	/**
	 * Construye un a entidad de tipo Contacto a partir de un objeto de tipo
	 * ContactosDTO
	 * 
	 * @param contactosDTO
	 * @return
	 */
	public static Contactos buildContactosFromContactosDTO(ContactoBaseDTO contactoBaseDTO) {

		Contactos contactos = null;

		if (contactoBaseDTO != null) {

			contactos = new Contactos();

			contactos.setId(contactoBaseDTO.getId());
			contactos.setNombre(contactoBaseDTO.getNombre());
			contactos.setDescription(contactoBaseDTO.getDescription());
			contactos.setEmail(contactoBaseDTO.getEmail());
			contactos.setTipoContacto(
					TipoContactoBuilder.builTipoContactoFromContactoBaseDTO(contactoBaseDTO.getTipoContacto()));
			contactos.setCreatedBy(contactoBaseDTO.getCreatedBy());
			contactos.setCreatedDate(contactoBaseDTO.getCreatedDate());
			contactos.setLastModifiedBy(contactoBaseDTO.getLastModifiedBy());
			contactos.setLastModifiedDate(contactoBaseDTO.getLastModifiedDate());

		}

		return contactos;

	}

	/**
	 * Construye un a entidad de tipo Contacto a partir de un objeto de tipo
	 * ContactosDTO
	 * 
	 * @param contactosDTO
	 * @return
	 */
	public static ContactoBaseDTO buildContactosDTOFromContactos(Contactos contactos) {

		ContactoBaseDTO contactosDTO = null;

		if (contactos != null) {

			contactosDTO = new ContactoBaseDTO();

			contactosDTO.setId(contactos.getId());
			contactosDTO.setNombre(contactos.getNombre());
			contactosDTO.setDescription(contactos.getDescription());
			contactosDTO.setEmail(contactos.getEmail());
			contactosDTO.setTipoContacto(
					TipoContactoBuilder.builTipoContactoBaseDTOFromContacto(contactos.getTipoContacto()));
			contactosDTO.setCreatedBy(contactos.getCreatedBy());
			contactosDTO.setCreatedDate(contactos.getCreatedDate());
			contactosDTO.setLastModifiedBy(contactos.getLastModifiedBy());
			contactosDTO.setLastModifiedDate(contactos.getLastModifiedDate());

		}

		return contactosDTO;

	}

	//

	/**
	 * Construye un a entidad de tipo Contacto a partir de un objeto de tipo
	 * ContactosDTO
	 * 
	 * @param contactosDTO
	 * @return
	 */
	public static ContactoBaseDTO buildContactoBaseDTOFromContactoRequestDTO(ContactoRequestDTO contactoRequestDTO) {

		ContactoBaseDTO contactosDTO = null;

		if (contactoRequestDTO != null) {

			contactosDTO = new ContactoBaseDTO();

			contactosDTO.setNombre(contactoRequestDTO.getNombre());
			contactosDTO.setEmail(contactoRequestDTO.getEmail());
			contactosDTO.setTipoContacto(TipoContactoBuilder
					.builTipoContactoBaseDTOFromContacto(new TipoContacto(contactoRequestDTO.getTipoContacto())));
			contactosDTO.setCreatedBy(contactoRequestDTO.getCreatedBy());
			contactosDTO.setCreatedDate(new Date());

		}

		return contactosDTO;

	}

	// ContactoRespDTO

	/**
	 * Construye un a entidad de tipo Contacto a partir de un objeto de tipo
	 * ContactosDTO
	 * 
	 * @param contactosDTO
	 * @return
	 */
	public static ContactoRespDTO buildContactoRespDTOFromContactoBaseDTO(ContactoBaseDTO contactoBaseDTO) {

		ContactoRespDTO contactosDTO = null;

		if (contactoBaseDTO != null) {

			contactosDTO = new ContactoRespDTO();

			contactosDTO.setEstatus(contactoBaseDTO.getEstatus());

			contactosDTO.setNombre(contactoBaseDTO.getNombre());
			contactosDTO.setEmail(contactoBaseDTO.getEmail());
			contactosDTO.setDescripcion(contactoBaseDTO.getDescription());
			contactosDTO.setId(contactoBaseDTO.getId());

			contactosDTO.setTipoContactoResDTO(TipoContactoBuilder
					.builTipoTipoContactoRespDTOFromTipoContactoDTO(contactoBaseDTO.getTipoContacto()));

		}

		return contactosDTO;

	}

}
