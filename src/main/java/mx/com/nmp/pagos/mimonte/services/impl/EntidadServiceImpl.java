/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.ContactosBuilder;
import mx.com.nmp.pagos.mimonte.builder.EntidadBuilder;
import mx.com.nmp.pagos.mimonte.builder.EntidadCuentaAfiliacionBuilder;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.dao.AfiliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dao.CuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.EntidadCuentaAfiliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dao.TipoContactoRepository;
import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.AfiliacionReqDTO;
import mx.com.nmp.pagos.mimonte.dto.CuentaReqDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadCuentaAfiliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadDTO;
import mx.com.nmp.pagos.mimonte.dto.EntidadResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.CatalogoException;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Afiliacion;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.EntidadCuentaAfiliacion;
import mx.com.nmp.pagos.mimonte.model.TipoContacto;
import mx.com.nmp.pagos.mimonte.services.EntidadService;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorCatalogo;

/**
 * @name EntidadServiceImpl
 * @description Clase de capa de servicio para el catalogo de entidades que
 *              sirve para realizar operaciones de logica de negocios para el
 *              catalogo de entidades
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 06/03/2019 12:33 hrs.
 * @version 0.1
 */
@Service("entidadServiceImpl")
public class EntidadServiceImpl implements EntidadService {

	/**
	 * Repository del catalogo Entidad
	 */
	@Autowired
	@Qualifier("entidadRepository")
	private EntidadRepository entidadRepository;

	/**
	 * Repository del catalogo EntidadCuentaAfiliacion
	 */
	@Autowired
	@Qualifier("entidadCuentaAfiliacionRepository")
	private EntidadCuentaAfiliacionRepository entidadCuentaAfiliacionRepository;

	/**
	 * Repository del catalogo Cuenta
	 */
	@Autowired
	@Qualifier("cuentaRepository")
	private CuentaRepository cuentaRepository;

	/**
	 * Repository del catalogo de tipo de contactos
	 */
	@Autowired
	private TipoContactoRepository tipoContactoRepository;

	/**
	 * Repository del catalogo Contacto
	 */
	@Autowired
	@Qualifier("contactoRespository")
	private ContactoRespository contactoRespository;

	/**
	 * Repository de Afiliacion
	 */
	@Autowired
	@Qualifier("afiliacionRepository")
	private AfiliacionRepository afiliacionRepository;

	/**
	 * Guarda una entidad
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends AbstractCatalogoDTO> T save(EntidadDTO e, String createdBy) {
		List<Entidad> entidades = null;
		// Se valida que el nombre de la entidad no exista
		try {
			entidades = entidadRepository.findByNombreAndDescription(e.getNombre(), e.getDescription());
		} catch (EmptyResultDataAccessException erdaex) {
			// No action required here, application continues normally
		}
		// Validar que las relaciones cuenta-afiliacion sean correctas
		List<Cuenta> cuentasComp = cuentaRepository.findAll();
		int c = 0;
		int c2 = 0;
		for (Cuenta cuentaI : cuentasComp) {
			for (Afiliacion afiliacionI : cuentaI.getAfiliaciones()) {
				for (CuentaReqDTO cuentaReqDTO : e.getCuentas()) {
					for (AfiliacionReqDTO afiliacionReqDTO : cuentaReqDTO.getAfiliaciones()) {
						if (cuentaI.getId().equals(cuentaReqDTO.getId())
								&& afiliacionI.getId().equals(afiliacionReqDTO.getId())) {
							c++;
						}
					}
				}
			}
		}
		for (CuentaReqDTO cuentaReqDTO : e.getCuentas()) {
			c2 += cuentaReqDTO.getAfiliaciones().size();
		}
		if (c != c2) {
			throw new CatalogoException(CatalogConstants.CUENTA_AFILIACION_RELATIONSHIP_DOESNT_EXISTS);
		}
		if (null != entidades && !entidades.isEmpty())
			throw new CatalogoException(CatalogConstants.ENTIDAD_ALREADY_EXISTS);
		if (null != e)
			e.setCreatedBy(createdBy);
		Entidad entidad = null;
		Entidad entidadResp = null;
		EntidadDTO entidadDTO = null;
		// Poner el tipo de contacto a los contactos
		TipoContacto tipoContacto = tipoContactoRepository
				.findByDescription(CatalogConstants.ENTIDAD_TIPO_CONTACTO_NAME);
		entidad = EntidadBuilder.buildEntidadFromEntidadDTONEW(e, tipoContacto.getId());
		entidadResp = entidadRepository.save(entidad);
		e.setId(entidadResp.getId());
		List<EntidadCuentaAfiliacion> lst = null;
		List<EntidadCuentaAfiliacionDTO> lst2 = null;
		int cnt = 0;
		List<Cuenta> cuentas = null;
		List<Afiliacion> afiliaciones = null;
		Entidad entidadval = null;
		EntidadCuentaAfiliacion entidadCuentaAfiliacion = null;
		if (null != e && null != e.getCuentas() && !e.getCuentas().isEmpty()) {
			lst = new ArrayList<>();
			lst2 = new ArrayList<>();
			cuentas = new ArrayList<>();
			afiliaciones = new ArrayList<>();
			for (CuentaReqDTO cuentaReqDTO : e.getCuentas()) {
				if (null != cuentaReqDTO.getAfiliaciones() && !cuentaReqDTO.getAfiliaciones().isEmpty()) {
					for (AfiliacionReqDTO afiliacionReqDTO : cuentaReqDTO.getAfiliaciones()) {
						entidadCuentaAfiliacion = new EntidadCuentaAfiliacion();
						entidadCuentaAfiliacion.setAfiliacion(new Afiliacion(afiliacionReqDTO.getId()));
						entidadCuentaAfiliacion.setCuenta(new Cuenta(cuentaReqDTO.getId()));
						entidadCuentaAfiliacion.setEntidad(new Entidad(e.getId()));
						entidadCuentaAfiliacionRepository.save(entidadCuentaAfiliacion);
						lst.add(entidadCuentaAfiliacion);
						entidadval = entidadRepository.findById(entidadCuentaAfiliacion.getEntidad().getId())
								.isPresent()
										? entidadRepository.findById(entidadCuentaAfiliacion.getEntidad().getId()).get()
										: null;
						cuentas.add(cuentaRepository.findById(cuentaReqDTO.getId()).isPresent()
								? cuentaRepository.findById(cuentaReqDTO.getId()).get()
								: null);
						afiliaciones.add(afiliacionRepository.findById(afiliacionReqDTO.getId()).isPresent()
								? afiliacionRepository.findById(afiliacionReqDTO.getId()).get()
								: null);
						lst2.add(new EntidadCuentaAfiliacionDTO(entidadval, cuentas.get(cnt), afiliaciones.get(cnt)));
						cnt++;
					}
				}
			}
		}
		entidadDTO = EntidadBuilder.buildEntidadDTOFromEntidad(entidadResp);
		Set<CuentaReqDTO> cuentaReqDTOSet = null;
		cuentaReqDTOSet = EntidadCuentaAfiliacionBuilder.buildCuentaReqDTOSetFromEntidadCuentaAfiliacionDTOList(lst2);
		entidadDTO.setCuentas(cuentaReqDTOSet);
		return (T) entidadDTO;
	}

	/**
	 * Actualiza una entidad
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends AbstractCatalogoDTO> T update(EntidadDTO e, String lastModifiedBy) throws CatalogoException {
		if (null != e)
			e.setLastModifiedBy(lastModifiedBy);
		Entidad entidad = null;
		Entidad entidadResp = null;
		EntidadDTO entidadDTO = null;
		// Poner el tipo de contacto a los contactos
		TipoContacto tipoContacto = tipoContactoRepository
				.findByDescription(CatalogConstants.ENTIDAD_TIPO_CONTACTO_NAME);
		entidad = EntidadBuilder.buildEntidadFromEntidadDTO(e, tipoContacto.getId());
		Entidad entidadTest = entidadRepository.findById(e.getId()).isPresent()
				? entidadRepository.findById(e.getId()).get()
				: null;
		if (null == entidadTest)
			throw new CatalogoException(CatalogConstants.CATALOG_NOT_FOUND);
		// Se valida que los id's de las cuentas existan
		// Se valida que los id's de los contactos existan
		List<Contactos> contactosList = contactoRespository.findAll();
		List<Long> newContacts = ValidadorCatalogo.validateContactosExists2(e.getContactos(),
				ContactosBuilder.buildContactoBaseDTOListFromContactosListOnlyIds(contactosList));
		List<Entidad> entidades = null;
		// Se valida que el nombre de la entidad no exista
		try {
			entidades = entidadRepository.findByNombreAndDescription(e.getNombre(), e.getDescription());
		} catch (EmptyResultDataAccessException erdaex) {
			// No action required here, application continues normally
		}
		if (null != entidades && !entidades.isEmpty() && entidades.get(0).getId() != e.getId())
			throw new CatalogoException(CatalogConstants.ENTIDAD_ALREADY_EXISTS);
		List<EntidadCuentaAfiliacion> lst = null;
		List<EntidadCuentaAfiliacionDTO> lst2 = null;
		try {
			for (Contactos contactos : entidad.getContactos()) {
				if (newContacts.contains(contactos.getId())) {
					contactos.setCreatedDate(new Date());
					contactos.setCreatedBy(lastModifiedBy);
					contactos.setEstatus(true);
				}
			}
			List<Cuenta> cuentasComp = cuentaRepository.findAll();
			int c = 0;
			int c2 = 0;
			for (Cuenta cuentaI : cuentasComp) {
				for (Afiliacion afiliacionI : cuentaI.getAfiliaciones()) {
					for (CuentaReqDTO cuentaReqDTO : e.getCuentas()) {
						for (AfiliacionReqDTO afiliacionReqDTO : cuentaReqDTO.getAfiliaciones()) {
							if (cuentaI.getId().equals(cuentaReqDTO.getId())
									&& afiliacionI.getId().equals(afiliacionReqDTO.getId())) {
								c++;
							}
						}
					}
				}
			}
			for (CuentaReqDTO cuentaReqDTO : e.getCuentas()) {
				c2 += cuentaReqDTO.getAfiliaciones().size();
			}
			if (c != c2) {
				throw new CatalogoException(CatalogConstants.CUENTA_AFILIACION_RELATIONSHIP_DOESNT_EXISTS);
			}
			entidadResp = entidadRepository.save(entidad);
			e.setId(entidadResp.getId());
			int cnt = 0;
			List<Cuenta> cuentas = null;
			List<Afiliacion> afiliaciones = null;
			Entidad entidadval = null;
			EntidadCuentaAfiliacion entidadCuentaAfiliacion = null;
			if (null != e && null != e.getCuentas() && !e.getCuentas().isEmpty()) {
				lst = new ArrayList<>();
				lst2 = new ArrayList<>();
				cuentas = new ArrayList<>();
				afiliaciones = new ArrayList<>();
				for (CuentaReqDTO cuentaReqDTO : e.getCuentas()) {
					if (null != cuentaReqDTO.getAfiliaciones() && !cuentaReqDTO.getAfiliaciones().isEmpty()) {
						for (AfiliacionReqDTO afiliacionReqDTO : cuentaReqDTO.getAfiliaciones()) {
							entidadCuentaAfiliacion = new EntidadCuentaAfiliacion();
							entidadCuentaAfiliacion.setAfiliacion(new Afiliacion(afiliacionReqDTO.getId()));
							entidadCuentaAfiliacion.setCuenta(new Cuenta(cuentaReqDTO.getId()));
							entidadCuentaAfiliacion.setEntidad(new Entidad(e.getId()));
							entidadCuentaAfiliacionRepository.save(entidadCuentaAfiliacion);
							lst.add(entidadCuentaAfiliacion);
							entidadval = entidadRepository.findById(entidadCuentaAfiliacion.getEntidad().getId())
									.isPresent()
											? entidadRepository.findById(entidadCuentaAfiliacion.getEntidad().getId())
													.get()
											: null;
							cuentas.add(cuentaRepository.findById(cuentaReqDTO.getId()).isPresent()
									? cuentaRepository.findById(cuentaReqDTO.getId()).get()
									: null);
							afiliaciones.add(afiliacionRepository.findById(afiliacionReqDTO.getId()).isPresent()
									? afiliacionRepository.findById(afiliacionReqDTO.getId()).get()
									: null);
							lst2.add(new EntidadCuentaAfiliacionDTO(entidadval, cuentas.get(cnt),
									afiliaciones.get(cnt)));
							cnt++;
						}
					}
				}
			}

		} catch (ConstraintViolationException cve) {
			throw new CatalogoException(CatalogConstants.CONSTRAINT_ERROR_MESSAGE);
		}

		// INICIA COMPROBACION DE CUENTAS A ELIMINAR
		List<EntidadCuentaAfiliacion> entidadCuentaAfiliacionExt = entidadCuentaAfiliacionRepository
				.findByEntidad_Id(entidadResp.getId());
		long[][] ids = new long[entidadCuentaAfiliacionExt.size()][3];
		int pos = 0;
		for (EntidadCuentaAfiliacion entidadCuentaAfiliacion : entidadCuentaAfiliacionExt) {
			ids[pos][0] = entidadCuentaAfiliacion.getCuenta().getId();
			ids[pos][1] = entidadCuentaAfiliacion.getAfiliacion().getId();
			ids[pos][2] = 0;
			pos++;
		}
		pos = 0;
		if (null != entidadCuentaAfiliacionExt && !entidadCuentaAfiliacionExt.isEmpty()) {
			if (null != e.getCuentas()) {
				for (CuentaReqDTO cuentaReqDTO : e.getCuentas()) {
					if (null != cuentaReqDTO.getAfiliaciones()) {
						for (AfiliacionReqDTO afiliacionReqDTO : cuentaReqDTO.getAfiliaciones()) {
							pos = 0;
							for (EntidadCuentaAfiliacion entidadCuentaAfiliacion : entidadCuentaAfiliacionExt) {
								if (entidadCuentaAfiliacion.getCuenta().getId().equals(cuentaReqDTO.getId())
										&& entidadCuentaAfiliacion.getAfiliacion().getId()
												.equals(afiliacionReqDTO.getId())) {
									ids[pos][2] = 1;
								}
								pos++;
							}
						}
					}
				}
			}
			for (int i = 0; i < ids.length; i++) {
				if (ids[i][2] == 0) {
					entidadCuentaAfiliacionRepository.dropEntidadCuentaAfiliacioneRelationship(e.getId(), ids[i][0],
							ids[i][1]);
				}
			}
		}
		// FINALIZA COMPROBACION DE CUENTAS A ELIMINAR

		entidadDTO = EntidadBuilder.buildEntidadDTOFromEntidad(entidadResp);
		Set<CuentaReqDTO> cuentaReqDTOSet = null;
		cuentaReqDTOSet = EntidadCuentaAfiliacionBuilder.buildCuentaReqDTOSetFromEntidadCuentaAfiliacionDTOList(lst2);
		entidadDTO.setCuentas(cuentaReqDTOSet);
		return (T) entidadDTO;
	}

	/**
	 * Encuentra una entidad por id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractCatalogoDTO> T findById(Long id) throws EmptyResultDataAccessException {
		Entidad entidadResp = null;
		EntidadDTO entidadDTO = null;
		entidadResp = entidadRepository.findById(id).isPresent() ? entidadRepository.findById(id).get() : null;
		if (null == entidadResp)
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND);
		entidadDTO = EntidadBuilder.buildEntidadDTOFromEntidad(entidadResp);

		if (null != entidadDTO) {
			entidadDTO.setCuentas(EntidadCuentaAfiliacionBuilder.buildCuentaReqDTOSetFromEntidadCuentaAfiliacionDTOList(
					EntidadCuentaAfiliacionBuilder.buildEntidadCuentaAfiliacionDTOListFromEntidadCuentaAfiliacionList(
							entidadCuentaAfiliacionRepository.findByEntidad_Id(entidadDTO.getId()))));
		}

		return (T) entidadDTO;
	}

	/**
	 * Encuentra una entidad por su nombre y estatus
	 * 
	 * @param nombre
	 * @param estatus
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	@Override
	public List<EntidadResponseDTO> findByNombreAndEstatus(final String nombre, final Boolean estatus)
			throws EmptyResultDataAccessException {
		List<Entidad> entidadList = null;
		List<EntidadResponseDTO> entidadResponseDTOList = null;
		entidadList = entidadRepository.findByNombreAndEstatus(nombre, estatus);
		if (null == entidadList || entidadList.isEmpty())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND);
		entidadResponseDTOList = EntidadBuilder.buildEntidadResponseDTOListFromEntidadList(entidadList);

		if (null != entidadResponseDTOList && !entidadResponseDTOList.isEmpty()) {
			for (EntidadResponseDTO entidadResponseDTO : entidadResponseDTOList) {
				entidadResponseDTO
						.setCuentas(EntidadCuentaAfiliacionBuilder.buildCuentaEntDTOSetFromEntidadCuentaAfiliacionList(
								entidadCuentaAfiliacionRepository.findByEntidad_Id(entidadResponseDTO.getId())));
			}
		}
		return entidadResponseDTOList;
	}

	/**
	 * Encuentra todas las entidades
	 */
	@Override
	public List<? extends AbstractCatalogoDTO> findAll() {
		List<Entidad> entidadList = null;
		List<EntidadDTO> entidadDTOList = null;
		entidadList = (List<Entidad>) entidadRepository.findAll();
		entidadDTOList = EntidadBuilder.buildEntidadDTOListFromEntidadList(entidadList);
		return (List<EntidadDTO>) entidadDTOList;
	}

	/**
	 * Elimina una entidad por id
	 */
	@Override
	public void deleteById(Long id) throws EmptyResultDataAccessException {
		entidadRepository.deleteById(id);
	}

	/**
	 * Actualiza el estatus de un catalogo Entidad por id
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateEstatusById(final Boolean estatus, final Long id, final String lastModifiedBy,
			final Date lastModifiedDate) throws EmptyResultDataAccessException {
		Entidad entidad = entidadRepository.findById(id).isPresent() ? entidadRepository.findById(id).get() : null;
		if (null == entidad)
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOT_FOUND);
		entidadRepository.setEstatusById(estatus, id, lastModifiedBy, lastModifiedDate);
	}

	/**
	 * Regresa una lista de ids de afiliaicion pertencientes a varias cuentas
	 * recibidad como parametro
	 * 
	 * @param cuentaDTOSet
	 * @return
	 */
	public static List<Long> getAfiliacionesCompleteFromManyCuentas(Set<CuentaReqDTO> cuentaReqDTOSet) {
		List<Long> lst = null;
		if (null != cuentaReqDTOSet && !cuentaReqDTOSet.isEmpty()) {
			lst = new ArrayList<>();
			for (CuentaReqDTO cuentaReqDTO : cuentaReqDTOSet)
				if (null != cuentaReqDTO.getAfiliaciones())
					for (AfiliacionReqDTO afiliacionReqDTO : cuentaReqDTO.getAfiliaciones())
						if (null != afiliacionReqDTO.getId())
							lst.add(afiliacionReqDTO.getId());
		}
		return lst;
	}

}
