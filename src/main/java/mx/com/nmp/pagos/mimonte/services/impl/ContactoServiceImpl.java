/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.ContactosBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dao.TipoContactoRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoBaseDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoRespDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
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

	
	@Autowired
	private ContactoRespository contactoRespository;
	
	/**
	 * Repository de catalogo Entidad
	 */
	@Autowired
	@Qualifier("entidadRepository")
	private EntidadRepository entidadRepository;

	@Autowired
	private TipoContactoRepository tipoContactoRepository;

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public <T extends AbstractCatalogoDTO> T save(ContactoBaseDTO e, String createdBy) {
		if(!validaEmailExistente(e))
			throw new CatalogoException(CatalogConstants.CATALOG_THE_EMAIL_THAT_WANTS_TO_ADD_ALREADY_EXISTS);
		if (null != e)
			e.setCreatedBy(createdBy);
		
		if(validaTipoContacto(e))
			throw new CatalogoException(CatalogConstants.CATALOG_THE_CONTACT_TYPE_ID_DOES_NOT_EXIST);
		
		return (T) ContactosBuilder.buildContactosDTOFromContactos(
				contactoRespository.save(ContactosBuilder.buildContactosFromContactosDTO(e)));
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(ContactoBaseDTO e, String lastModifiedBy) {
		if(!validaEmailExistente(e))
			throw new CatalogoException(CatalogConstants.CATALOG_THE_EMAIL_THAT_WANTS_TO_ADD_ALREADY_NOT_EXISTS);
		if (null != e)
			e.setLastModifiedBy(lastModifiedBy);
	
		if(validaContacto(e)) 
			throw new CatalogoException(CatalogConstants.CATALOG_THE_ID_TO_UPDATE_DOES_NOT_EXIST);
		
		if(validaTipoContacto(e))
			throw new CatalogoException(CatalogConstants.CATALOG_THE_CONTACT_TYPE_ID_DOES_NOT_EXIST);
		
		return (T) ContactosBuilder.buildContactosDTOFromContactos(
					contactoRespository.save(ContactosBuilder.buildContactosFromContactosDTOupdt(e)));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) {
		return contactoRespository.findById(id).isPresent()
				? (T) ContactosBuilder.buildContactoRespDTOFromContactos(contactoRespository.findById(id).get())
				: null;
	}

	@Override
	public List<? extends AbstractCatalogoDTO> findAll() {
		List<ContactoRespDTO> lst = null;
		lst = ContactosBuilder.buildContactoRespDTOListFromContactosList(contactoRespository.findAll());
		return null != lst ? lst : new ArrayList<>();
	}

	@Override
	public void deleteById(Long id) throws EmptyResultDataAccessException {
		Contactos contactos = contactoRespository.findById(id).isPresent() ? contactoRespository.findById(id).get()	: null;
		if(contactos == null)
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND);
		List<Entidad> entidades = entidadRepository.findByContactos_Id(id);
		if(null != entidades && !entidades.isEmpty())
			throw new CatalogoException(CatalogConstants.CONTCATO_HAS_ENTIDADES_ASSOCIATED);
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
	 * Metodo que obtiene registros en base a los parametros idTipoContacto, nombre y email.
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
	 * Metodo que valida el email existe o no para agregar o actualizar los registros de los contactos.
	 * 
	 * @param e
	 * @return
	 */
	public boolean validaEmailExistente(ContactoBaseDTO e) {
		Contactos validaEmail = contactoRespository.findByEmail(e.getEmail());
		if (e.getId() == null && null != validaEmail) {
			if (!e.getEmail().equals(validaEmail.getEmail()))
				return true;
			else
				return false;
		} else {
			if (null != validaEmail && e.getId() == validaEmail.getId())
				return true;
			else if (null != validaEmail && e.getId() != validaEmail.getId()
					&& !e.getEmail().equals(validaEmail.getEmail()))
				return true;
			else if (null != validaEmail && e.getId() != validaEmail.getId()
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
	public boolean validaTipoContacto(ContactoBaseDTO e){
		TipoContacto tipoContacto = tipoContactoRepository.findById(e.getTipoContacto().getId()).isPresent() ? tipoContactoRepository.findById(e.getTipoContacto().getId()).get() : null ;
		if(tipoContacto == null)
			return true;
		return false;
	}
	
	/**
	 * Metodo que valida si existe un idContacto.
	 * 
	 * @param e
	 * @return
	 */
	public boolean validaContacto(ContactoBaseDTO e) {
		Contactos contacto = contactoRespository.findById(e.getId()).isPresent()?contactoRespository.findById(e.getId()).get():null;
		if(contacto == null)
			return true;
		return false;
		
	}
}
