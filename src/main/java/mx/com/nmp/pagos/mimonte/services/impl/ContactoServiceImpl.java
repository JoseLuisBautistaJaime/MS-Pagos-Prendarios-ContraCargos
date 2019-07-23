/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.ContactosBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dao.TipoContactoRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoRespDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.TipoContacto;
import mx.com.nmp.pagos.mimonte.services.CatalogoAdmService;

/**
 * Nombre: ContactoServiceImpl Descripcion: Capa de servicios en la cual esta la
 * logica del manejo de los contactos.
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net Fecha: 05/03/2019 15:41 hrs.
 * @version 0.1
 */
@Service("contactoServiceImpl")
public class ContactoServiceImpl implements CatalogoAdmService<ContactoBaseDTO> {

	/**
	 * Repository de contactos
	 */
	@Autowired
	private ContactoRespository contactoRespository;

	/**
	 * Repository de catalogo Entidad
	 */
	@Autowired
	@Qualifier("entidadRepository")
	private EntidadRepository entidadRepository;

	/**
	 * Repository de tipo de contactos
	 */
	@Autowired
	private TipoContactoRepository tipoContactoRepository;

	/**
	 * Guarda un contacto
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public <T extends AbstractCatalogoDTO> T save(ContactoBaseDTO e, String createdBy) {
		// Valida que el contacto no exista
		if (!validaEmailExistente(e))
			throw new CatalogoException(CatalogConstants.CATALOG_THE_EMAIL_THAT_WANTS_TO_ADD_ALREADY_EXISTS,
					CodigoError.NMP_PMIMONTE_BUSINESS_015);
		if (null != e)
			e.setCreatedBy(createdBy);
		// Valida que el tipo de contacto exista
		if (validaTipoContacto(e))
			throw new CatalogoException(CatalogConstants.CATALOG_THE_CONTACT_TYPE_ID_DOES_NOT_EXIST,
					CodigoError.NMP_PMIMONTE_BUSINESS_011);
		// Construye y regresa la respuesta
		return (T) ContactosBuilder.buildContactosDTOFromContactos(
				contactoRespository.save(ContactosBuilder.buildContactosFromContactosDTO(e)));
	}

	/**
	 * Actualiza un contacto
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(ContactoBaseDTO e, String lastModifiedBy) {
		// Valida que el email no exista
		if (!validaEmailExistente(e))
			throw new CatalogoException(CatalogConstants.CATALOG_THE_EMAIL_THAT_WANTS_TO_UPDT_ALREADY_EXISTS,
					CodigoError.NMP_PMIMONTE_BUSINESS_015);
		if (null != e)
			e.setLastModifiedBy(lastModifiedBy);
		// Valida que el contacto exista
		if (validaContacto(e))
			throw new InformationNotFoundException(CatalogConstants.CATALOG_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0005);
		// Valida que el tipo de contacto exista
		if (validaTipoContacto(e))
			throw new CatalogoException(CatalogConstants.CATALOG_THE_CONTACT_TYPE_ID_DOES_NOT_EXIST,
					CodigoError.NMP_PMIMONTE_BUSINESS_011);
		// Construye y regresa el objeto de respuesta
		return (T) ContactosBuilder.buildContactosDTOFromContactos(
				contactoRespository.save(ContactosBuilder.buildContactosFromContactosDTOupdt(e)));
	}

	/**
	 * Encuentra un contacto por id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) {
		Optional<Contactos> contacto = null;
		ContactoRespDTO contactoRespDTO = null;
		contacto = contactoRespository.findById(id);
		if (contacto.isPresent())
			contactoRespDTO = ContactosBuilder.buildContactoRespDTOFromContactos(contacto.get());
		return (T) contactoRespDTO;
	}

	/**
	 * Encuentra todos los contactos
	 */
	@Override
	public List<? extends AbstractCatalogoDTO> findAll() {
		List<ContactoRespDTO> lst = null;
		lst = ContactosBuilder.buildContactoRespDTOListFromContactosList(contactoRespository.findAll());
		return null != lst ? lst : new ArrayList<>();
	}

	/**
	 * Elimina un contacto por id
	 */
	@Override
	public void deleteById(Long id) throws EmptyResultDataAccessException {
		Optional<Contactos> contacto = null;
		// Valida por id que el contacto exista
		contacto = contactoRespository.findById(id);
		if (!contacto.isPresent())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND, CodigoError.NMP_PMIMONTE_0005);
		// Valida sie l contacto tiene entidades asociadas
		List<Entidad> entidades = entidadRepository.findByContactos_Id(id);
		if (null != entidades && !entidades.isEmpty())
			throw new CatalogoException(CatalogConstants.CONTACTO_HAS_ENTIDADES_ASSOCIATED,
					CodigoError.NMP_PMIMONTE_BUSINESS_016);
		contactoRespository.deleteById(id);
	}

	/**
	 * Metodo que obtiene los registros en base al parametro idTipoContacto.
	 * 
	 * @param idTipoContacto
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<ContactoRespDTO> findByIdTipoContacto(Long idTipoContacto) throws EmptyResultDataAccessException {
		List<ContactoRespDTO> lst = null;
		lst = ContactosBuilder
				.buildContactoRespDTOListFromContactosList(contactoRespository.findByIdTipoContacto(idTipoContacto));
		return null != lst ? lst : new ArrayList<>();
	}

	/**
	 * Metodo que obtiene registros en base a los parametros idTipoContacto, nombre
	 * y email.
	 * 
	 * @param idTipoContacto
	 * @param nombre
	 * @param email
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<ContactoRespDTO> findByIdTipoContactoAndNombreAndEmail(Long idTipoContacto, String nombre, String email)
			throws EmptyResultDataAccessException {
		List<ContactoRespDTO> lst = null;
		lst = ContactosBuilder.buildContactoRespDTOListFromContactosList(
				contactoRespository.findByIdTipoContactoOrNombreOrEmail(idTipoContacto, nombre, email));
		return null != lst ? lst : new ArrayList<>();
	}

	/**
	 * Metodo que valida el email existe o no para agregar o actualizar los
	 * registros de los contactos.
	 * 
	 * @param e
	 * @return
	 */
	public boolean validaEmailExistente(ContactoBaseDTO e) {
		Contactos validaEmail = contactoRespository.findByEmail(e.getEmail());
		if (e.getId() == null && null != validaEmail) {
			return (!e.getEmail().equals(validaEmail.getEmail()));
		} else {
			if (null != validaEmail && e.getId().equals(validaEmail.getId()))
				return true;
			else if (null != validaEmail && !e.getId().equals(validaEmail.getId())
					&& !e.getEmail().equals(validaEmail.getEmail()))
				return true;
			else if (null != validaEmail && !e.getId().equals(validaEmail.getId())
					&& e.getEmail().equals(validaEmail.getEmail()))
				return false;
			else
				return true;
		}

	}

	/**
	 * Metodo que valida si existe un idTipoContacto.
	 * 
	 * @param e
	 * @return
	 */
	public boolean validaTipoContacto(ContactoBaseDTO e) {
		Optional<TipoContacto> tipoContacto = null;
		tipoContacto = tipoContactoRepository.findById(e.getTipoContacto().getId());
		return (!tipoContacto.isPresent());
	}

	/**
	 * Metodo que valida si existe un idContacto.
	 * 
	 * @param e
	 * @return
	 */
	public boolean validaContacto(ContactoBaseDTO e) {
		Optional<Contactos> contacto = null;
		contacto = contactoRespository.findById(e.getId());
		return (!contacto.isPresent());
	}
}
