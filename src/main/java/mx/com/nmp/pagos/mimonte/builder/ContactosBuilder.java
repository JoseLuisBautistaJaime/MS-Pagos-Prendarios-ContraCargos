/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.ContactoBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoEntDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoReqDTO;
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

	/**
	 * Construye un objeto de tipo ContactoEntDTO a partir de una entity de tipo
	 * Contactos
	 * 
	 * @param contacto
	 * @return
	 */
	public static ContactoEntDTO buildContactoEntDTOFromContacto(Contactos contacto) {
		ContactoEntDTO contactoEntDTO = null;
		if (null != contacto) {
			contactoEntDTO = new ContactoEntDTO();
			contactoEntDTO.setEmail(contacto.getEmail());
			contactoEntDTO.setEstatus(contacto.getEstatus());
			contactoEntDTO.setId(contacto.getId());
			contactoEntDTO.setNombre(contacto.getNombre());
		}
		return contactoEntDTO;
	}

	/**
	 * Construye un set de objetos de tipo ContactoEntDTO a partir de un set de
	 * entities de tipo Contactos
	 * 
	 * @param contactoSet
	 * @return
	 */
	public static Set<ContactoEntDTO> buildContactoEntDTOSetFromContactoSet(Set<Contactos> contactoSet) {
		Set<ContactoEntDTO> contactoEntDTOSet = null;
		if (null != contactoSet && !contactoSet.isEmpty()) {
			contactoEntDTOSet = new TreeSet<>();
			for (Contactos contacto : contactoSet) {
				contactoEntDTOSet.add(buildContactoEntDTOFromContacto(contacto));
			}
		}
		return contactoEntDTOSet;
	}

	/**
	 * Construye un objeto de tipo ContactoEntDTO a partir de un objeto de tipo
	 * ContactoReqDTO
	 * 
	 * @param contactoReqDTO
	 * @return
	 */
	public static ContactoEntDTO buildContactoEntDTOFromContactoReqDTO(ContactoReqDTO contactoReqDTO) {
		ContactoEntDTO contactoEntDTO = null;
		if (null != contactoReqDTO) {
			contactoEntDTO = new ContactoEntDTO();
			contactoEntDTO.setEmail(contactoReqDTO.getEmail());
			contactoEntDTO.setEstatus(contactoReqDTO.getEstatus());
			contactoEntDTO.setId(contactoReqDTO.getId());
			contactoEntDTO.setNombre(contactoReqDTO.getNombre());
		}
		return contactoEntDTO;
	}

	/**
	 * Construye un Set de objetos de tipo ContactoEntDTO a partir de un Set de
	 * objetos de tipo ContactoReqDTO
	 * 
	 * @param contactoReqDTOSet
	 * @return
	 */
	public static Set<ContactoEntDTO> buildContactoEntDTOSetFromContactoReqDTOSet(
			Set<ContactoReqDTO> contactoReqDTOSet) {
		Set<ContactoEntDTO> contactoEntDTOSet = null;
		if (null != contactoReqDTOSet) {
			contactoEntDTOSet = new TreeSet<>();
			for (ContactoReqDTO contactoReqDTO : contactoReqDTOSet) {
				contactoEntDTOSet.add(buildContactoEntDTOFromContactoReqDTO(contactoReqDTO));
			}
		}
		return contactoEntDTOSet;
	}

	/**
	 * Construye un entity de tipo Contactos a partir de un objeto de tipo
	 * ContactoReqDTO
	 * 
	 * @param contactoReqDTO
	 * @return
	 */
	public static Contactos buildContactosFromContactoReqDTO(ContactoReqDTO contactoReqDTO, String lastModifiedBy,
			Date lastModifiedDate) {
		Contactos contacto = null;
		if (null != contactoReqDTO) {
			contacto = new Contactos();
			contacto.setEmail(contactoReqDTO.getEmail());
			contacto.setEstatus(contactoReqDTO.getEstatus());
			contacto.setNombre(contactoReqDTO.getNombre());
			contacto.setId(contactoReqDTO.getId());
			contacto.setLastModifiedBy(lastModifiedBy);
			contacto.setLastModifiedDate(lastModifiedDate);
		}
		return contacto;
	}

	/**
	 * Construye un Set de entities de tipo Contactos a partir de in Set de objetos
	 * de tipo ContactoReqDTO
	 * 
	 * @param contactoReqDTOSet
	 * @return
	 */
	public static Set<Contactos> buildContactosSetFromContactoReqDTOSet(Set<ContactoReqDTO> contactoReqDTOSet,
			String lastModifiedBy, Date lastModifiedDate) {
		Set<Contactos> contactosSet = null;
		if (null != contactoReqDTOSet) {
			contactosSet = new TreeSet<>();
			for (ContactoReqDTO contactoReqDTO : contactoReqDTOSet) {
				contactosSet.add(buildContactosFromContactoReqDTO(contactoReqDTO, lastModifiedBy, lastModifiedDate));
			}
		}
		return contactosSet;
	}

	/**
	 * Construye un objeto de tipo ContactoReqDTO a partir de un entity de tipo
	 * Contactos
	 * 
	 * @param contacto
	 * @return
	 */
	public static ContactoReqDTO buildContactoReqDTOFromContacto(Contactos contacto) {
		ContactoReqDTO contactoReqDTO = null;
		if (null != contacto) {
			contactoReqDTO = new ContactoReqDTO();
			contactoReqDTO.setEmail(contacto.getEmail());
			contactoReqDTO.setEstatus(contacto.getEstatus());
			contactoReqDTO.setId(contacto.getId());
			contactoReqDTO.setNombre(contacto.getNombre());
		}
		return contactoReqDTO;
	}

	/**
	 * Construye un Set de objetos de tipo ContactoReqDTO a partir de un Set de
	 * entities de tipo Contactos
	 * 
	 * @param contactoSet
	 * @return
	 */
	public static Set<ContactoReqDTO> buildContactoReqDTOSetFromContactoSet(Set<Contactos> contactoSet) {
		Set<ContactoReqDTO> contactoReqDTO = null;
		if (null != contactoSet) {
			contactoReqDTO = new TreeSet<>();
			for (Contactos contacto : contactoSet) {
				contactoReqDTO.add(buildContactoReqDTOFromContacto(contacto));
			}
		}
		return contactoReqDTO;
	}

	/**
	 * Construye un objeto de tipo ContactoBaseDTO a partir de un entity de tipo
	 * Contactos (solo setea el id)
	 * 
	 * @param contacto
	 * @return
	 */
	public static ContactoBaseDTO buildContactoBaseDTOFromContactosOnlyIds(Contactos contacto) {
		ContactoBaseDTO contactoBaseDTO = null;
		if (null != contacto) {
			contactoBaseDTO = new ContactoBaseDTO();
			contactoBaseDTO.setId(contacto.getId());
		}
		return contactoBaseDTO;
	}

	/**
	 * Construye una lista de objetos de tipo ContactoBaseDTO a partir de una lista
	 * de entities de tipo Contactos (solo setea los ids)
	 * 
	 * @param contactosList
	 * @return
	 */
	public static final List<ContactoBaseDTO> buildContactoBaseDTOListFromContactosListOnlyIds(
			List<Contactos> contactosList) {
		List<ContactoBaseDTO> contactoBaseDTOList = null;
		if (null != contactosList && !contactosList.isEmpty()) {
			contactoBaseDTOList = new ArrayList<>();
			for (Contactos contactos : contactosList) {
				contactoBaseDTOList.add(buildContactoBaseDTOFromContactosOnlyIds(contactos));
			}
		}
		return contactoBaseDTOList;
	}

}
