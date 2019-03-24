/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.ContactosBuilder;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.ContactoBaseDTO;
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

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T save(ContactoBaseDTO e, String createdBy) {
		if (null != e)
			e.setCreatedBy(createdBy);
		return (T) ContactosBuilder.buildContactosDTOFromContactos(
				contactoRespository.save(ContactosBuilder.buildContactosFromContactosDTO(e)));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T update(ContactoBaseDTO e, String lastModifiedBy) {
		if (null != e)
			e.setCreatedBy(lastModifiedBy);
		return (T) ContactosBuilder.buildContactosDTOFromContactos(
				contactoRespository.save(ContactosBuilder.buildContactosFromContactosDTO(e)));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) {
		return contactoRespository.findById(id).isPresent()
				? (T) ContactosBuilder.buildContactosDTOFromContactos(contactoRespository.findById(id).get())
				: null;
	}

}
