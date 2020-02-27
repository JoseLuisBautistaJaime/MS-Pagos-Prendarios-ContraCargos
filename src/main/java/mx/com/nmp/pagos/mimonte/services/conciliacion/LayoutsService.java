/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.aspects.ActividadGenericMethod;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.LayoutsBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientoDevolucionBuilder;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ComisionTransaccionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutHeaderCatalogRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutHeadersRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutLineaCatalogRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutLineasRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutsRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.LayoutsJdbcRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutCabeceraDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutLineaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.OperacionesPorSucursalDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper;
import mx.com.nmp.pagos.mimonte.helper.EstadoCuentaHelper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuentaCabecera;
import mx.com.nmp.pagos.mimonte.model.conciliacion.GrupoLayoutEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.IMovTransaccion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Layout;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeader;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeaderCatalog;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLinea;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLineaCatalog;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoPago;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubTipoActividadEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoActividadEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoComisionEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoEnum;
import mx.com.nmp.pagos.mimonte.util.ConciliacionDataValidator;
import mx.com.nmp.pagos.mimonte.util.DateUtil;

/**
 * * @name LayoutsService
 * 
 * @description Realiza operaciones de logica de negocio sobre objetos
 *              relacionados con Layout
 * @author Quarksoft
 * @version 1.0
 * @creationDate 27-Jun-2019 6:33:47 PM
 */
@Service
public class LayoutsService {

	/**
	 * Repository de Layouts
	 */
	@Autowired
	private LayoutsRepository layoutsRepository;

	/**
	 * Repository de Layouts Lineas
	 */
	@Autowired
	private LayoutLineasRepository layoutLineasRepository;

	/**
	 * Repository de Layouts header
	 */
	@Autowired
	private LayoutHeadersRepository layoutHeadersRepository;

	/**
	 * Repository de Movimientos conciliacion
	 */
	@Autowired
	private MovimientoConciliacionRepository movimientoConciliacionRepository;

	/**
	 * Repository de Layouts header catalog
	 */
	@Autowired
	private LayoutHeaderCatalogRepository layoutHeaderCatalogRepository;

	/**
	 * Repository de Layouts linea catalog
	 */
	@Autowired
	private LayoutLineaCatalogRepository layoutLineaCatalogRepository;

	/**
	 * Repository de Layouts linea catalog
	 */
	@Autowired
	private ConciliacionHelper conciliacionHelper;

	/**
	 * Validador generico de conciliacion
	 */
	@Autowired
	private ConciliacionDataValidator conciliacionDataValidator;

	/**
	 * Registro de actividades
	 */
	@Autowired
	private ActividadGenericMethod actividadGenericMethod;

	@Autowired
	private LayoutsJdbcRepository layoutsJdbcRepository;

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private MovimientosMidasRepository movimientosMidasRepository;

	@Inject
	private EstadoCuentaHelper estadoCuentaHelper;

	@Inject
	private ComisionTransaccionRepository comisionTransaccionRepository;


	/**
	 * Logs de la clase
	 */
	private static final Logger LOG = LoggerFactory.getLogger(LayoutsService.class);

	public LayoutsService() {
		super();
	}

	/**
	 * Consulta un Layout de una Conciliación
	 * 
	 * @param idConciliacion
	 * @param tipo
	 * @return
	 */
	public LayoutDTO consultarUnLayout(Long idConciliacion, TipoLayoutEnum tipo) {
		// Objetos necesarios
		Layout layout = null;

		if (validar(idConciliacion, tipo)) {
			try {
				List<Layout> layouts = layoutsRepository.findByIdConciliacionAndTipo(idConciliacion, tipo);
				if (layouts != null && layouts.size() > 0) {
					layout = layouts.get(0);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ConciliacionException("Error al consultar el layout tipo " + tipo,
						CodigoError.NMP_PMIMONTE_BUSINESS_113);
			}
		}

		LayoutDTO layoutDTO = LayoutsBuilder.buildLayoutDTOFromLayout(layout);

		// Ordenar lineas de los layouts layout//
		// Primero positivos arriba y negativos abajo
		if (layoutDTO != null && layoutDTO.getLineas() != null && layoutDTO.getLineas().size() > 0) {

			List<LayoutLineaDTO> lineasPositivosDTO = new ArrayList<LayoutLineaDTO>();
			List<LayoutLineaDTO> lineasNegativosDTO = new ArrayList<LayoutLineaDTO>();

			// Se validan positivos y negativos
			for (LayoutLineaDTO lineaDTO : layoutDTO.getLineas()) {
				// Positivos
				if (lineaDTO.getMonto() != null && lineaDTO.getMonto().compareTo(new BigDecimal(0)) >= 0) {
					lineasPositivosDTO.add(lineaDTO);
				}
				else {
					lineasNegativosDTO.add(lineaDTO);
				}
			}

			// Se setean los montos positivos primero
			layoutDTO.setLineas(new ArrayList<LayoutLineaDTO>());
			layoutDTO.getLineas().addAll(lineasPositivosDTO);
			layoutDTO.getLineas().addAll(lineasNegativosDTO);
		}

		return layoutDTO;
	}

	/**
	 * Consulta los Layouts de una Conciliación
	 * 
	 * @param idConciliacion
	 * @return
	 */
	public List<LayoutDTO> consultarLayouts(Long idConciliacion) {
		// Objetos necesarios
		List<LayoutDTO> layoutDTOs = new ArrayList<>();

		try {
			// Valida que la conciliacion exista
			conciliacionDataValidator.validateFolioExists(idConciliacion);

			if (idConciliacion > 0L) {
				LayoutDTO layoutDTO = consultarUnLayout(idConciliacion, TipoLayoutEnum.PAGOS);
				if (layoutDTO != null) {
					layoutDTOs.add(layoutDTO);
				}
				layoutDTO = consultarUnLayout(idConciliacion, TipoLayoutEnum.COMISIONES_MOV);
				if (layoutDTO != null) {
					layoutDTOs.add(layoutDTO);
				}
				layoutDTO = consultarUnLayout(idConciliacion, TipoLayoutEnum.COMISIONES_GENERALES);
				if (layoutDTO != null) {
					layoutDTOs.add(layoutDTO);
				}
				layoutDTO = consultarUnLayout(idConciliacion, TipoLayoutEnum.DEVOLUCIONES);
				if (layoutDTO != null) {
					layoutDTOs.add(layoutDTO);
				}
			}
		} catch (ConciliacionException ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_113.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_113);
		}
		return layoutDTOs;
	}

	/**
	 * Agrega o actualiza un Layout
	 * 
	 * @param layoutDTO
	 */
	@Transactional
	public void saveLayout(LayoutDTO layoutDTO, final String requestUser) {
		// Objetos a utilizar
		List<Layout> layouts = null;
		Conciliacion conciliacion = null;
		List<Long> layoutIdsList;
		Boolean flag = false;
		List<Long> lineasIds;

		try {
			// Se valida que la conciliacion se encuentre en proceso
			conciliacion = this.conciliacionHelper.getConciliacionByFolio(layoutDTO.getFolio(),
					ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);

			// Se valida si en las lineas de la peticion existen lineas a editar
			lineasIds = LayoutsBuilder.getLineasIds(layoutDTO);
			if (null != lineasIds && !lineasIds.isEmpty()) {
				// Valida que las lineas a editar pertenezcan al tipo especificado
				flag = ((BigInteger) layoutsRepository.validateLineasVSTipo(lineasIds, lineasIds.size(),
						layoutDTO.getTipoLayout().toString())).compareTo(BigInteger.ONE) == 0;
				if (!flag) {
					throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_129.getDescripcion(),
							CodigoError.NMP_PMIMONTE_BUSINESS_129);
				}
			}

			// Se valida que las lineas a editar existan para ese layout y que sean lineas
			// eliminables (dadas de alta desde APP)
			layoutIdsList = LayoutsBuilder.buildLongListFromLayoutLineaDTONO0List(layoutDTO.getLineas());
			if (null != layoutIdsList && !layoutIdsList.isEmpty()) {
				flag = ((BigInteger) layoutsRepository.checkLineasIdsAndFolioRelationship(layoutDTO.getFolio(),
						layoutIdsList, layoutIdsList.size())).compareTo(BigInteger.ONE) == 0;
				if (!flag)
					throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_123.getDescripcion(),
							CodigoError.NMP_PMIMONTE_BUSINESS_123);
			}

		} catch (ConciliacionException ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_0014.getDescripcion(),
					CodigoError.NMP_PMIMONTE_0014);
		}

		// Se encarga de persistir o actualizar el layout, header y lineas
		try {
			layouts = mergeLayouts(conciliacion.getId(), Arrays.asList(layoutDTO), requestUser, true);
		} catch (ConciliacionException ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		} catch (Exception ex) {
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_120.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_120);
		}

		// Se persisten los layouts
		if (layouts != null && !layouts.isEmpty()) {
			try {
				this.layoutsRepository.saveAll(layouts);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ConciliacionException("Ocurrio un error al persistir los layouts",
						CodigoError.NMP_PMIMONTE_0011);
			}
		}

		if (null != layoutDTO.getFolio() && null != layoutDTO.getTipoLayout() && null != layoutDTO.getLineas()) {
			// Registro de actividad
			String desc = "Se crea el layout del tipo: ".concat(layoutDTO.getTipoLayout().toString())
					.concat(", con un total de ").concat(String.valueOf(layoutDTO.getLineas().size()))
					.concat(" lineas, pertenecirente a la conciliacion: ").concat(String.valueOf(layoutDTO.getFolio()));
			actividadGenericMethod.registroActividad(layoutDTO.getFolio(), desc, TipoActividadEnum.ACTIVIDAD,
					SubTipoActividadEnum.LAYOUTS);
		}
	}

	/**
	 * Elimina un Layout
	 * 
	 * @param idConciliacion
	 * @param idLayout
	 * @return
	 */
	@Transactional
	public boolean eliminarUnLayout(Long idConciliacion, Long idLayout) throws ConciliacionException {
		// Objetos necesarios
		Boolean flag = null;
		boolean respuesta = false;
		boolean flagAct = true;

		// LOGICA DE ELIMINACION
		try {
			// Valida que la conciliacion exista
			conciliacionDataValidator.validateFolioExists(idConciliacion);

			// Valida que el id de layout exista
			flag = ((BigInteger) layoutsRepository.checkIfIdExist(idLayout)).compareTo(BigInteger.ONE) == 0;
			if (!flag)
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_085.getDescripcion(),
						CodigoError.NMP_PMIMONTE_BUSINESS_085);

			// Valida que la conciliacion este ligada al id de layout especificado
			flag = ((BigInteger) layoutsRepository.checkIfFolioIdRelationshipExist(idConciliacion.intValue(), idLayout))
					.compareTo(BigInteger.ONE) == 0;
			if (!flag)
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_115.getDescripcion(),
						CodigoError.NMP_PMIMONTE_BUSINESS_115);

			// Valida que el layout no tenga lineas dadas de alta desde el sistema para
			// poder eliminar (nuevo = 1)
			flag = ((BigInteger) layoutsRepository.checkIfLineasAreNew(idConciliacion, idLayout))
					.compareTo(BigInteger.ONE) == 0;
			if (!flag)
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_116.getDescripcion(),
						CodigoError.NMP_PMIMONTE_BUSINESS_116);

			// Logica de eliminacion
			respuesta = true;
			List<Layout> layoutList = layoutsRepository.checkFolioAndLayoutsRelationship(idConciliacion);
			if (null == layoutList || layoutList.isEmpty())
				throw new ConciliacionException(ConciliacionConstants.THERE_IS_NO_CONCILIACION_LAYOUT_RELATIONSHIP,
						CodigoError.NMP_PMIMONTE_BUSINESS_084);
			if (idConciliacion > 0L && idLayout > 0L) {
				if (layoutsRepository.findById(idLayout).isPresent()) {
					Layout layout = layoutsRepository.getOne(idLayout);// siLayoutPerteneceAConciliacion
					List<Layout> layouts = layoutsRepository.findByTipo(layout.getTipo());// siLayoutEsElMismoTipo
					for (Layout layout2 : layouts) {
						if (layout2.getIdConciliacion().equals(idConciliacion)) {
							layoutsRepository.eliminarUnLayoutLineas(layout2.getId());
							layoutsRepository.eliminarUnLayoutHeader(layout2.getId());
							layoutsRepository.deleteById(layout2.getId());
							respuesta = false;
						}
					}
				} else
					throw new InformationNotFoundException(ConciliacionConstants.Validation.NO_INFORMATION_FOUND,
							CodigoError.NMP_PMIMONTE_0009);
			}
		} catch (ConciliacionException ex) {
			flagAct = false;
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw ex;
		} catch (Exception ex) {
			flagAct = false;
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_114.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_114);
		} finally {
			if (flagAct && null != idConciliacion && null != idLayout) {
				String desc = "Se elimino el layout con id: ".concat(String.valueOf(idLayout))
						.concat(", perteneciente a la conciliacion: ").concat(String.valueOf(idConciliacion));
				actividadGenericMethod.registroActividad(idConciliacion, desc, TipoActividadEnum.ACTIVIDAD,
						SubTipoActividadEnum.LAYOUTS);
			}
		}
		return respuesta;
	}

	/**
	 * Se encarga de eliminar una linea del layout
	 * 
	 * @param idLinea
	 */
	public void eliminarLinea(Long idLinea) throws ConciliacionException {
		// Objetos necesarios
		Optional<LayoutLinea> linea = null;
		Boolean flag = null;
		boolean flagAct = true;

		// Se valida que la linea exista
		try {
			linea = this.layoutLineasRepository.findById(idLinea);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al obtener la linea del layout asignada al id " + idLinea,
					CodigoError.NMP_PMIMONTE_BUSINESS_085);
		}
		if (linea == null || !linea.isPresent()) {
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_117.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_117);
		}

		// Se valida que la linea se pueda eliminar, o sea que fue dada de alta por el
		// usuario (nuevo = 1)
		flag = ((BigInteger) layoutLineasRepository.checkIfLineIsNew(idLinea,
				ConciliacionConstants.ELEMENT_ADDED_BY_USER)).compareTo(BigInteger.ONE) == 0;
		if (!flag)
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_134.getDescripcion(),
					CodigoError.NMP_PMIMONTE_BUSINESS_134);

		// Se valida que la conciliacion asignada se encuentre en proceso
		Long idConciliacion = linea.get().getLayout().getIdConciliacion();
		this.conciliacionHelper.getConciliacionByFolio(idConciliacion,
				ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);

		// Se elimina la linea del layout
		try {
			this.layoutLineasRepository.deleteById(linea.get().getId());
		} catch (Exception ex) {
			flagAct = false;
			ex.printStackTrace();
			throw new ConciliacionException("Error al eliminar la linea del layout asignada al id " + idLinea,
					CodigoError.NMP_PMIMONTE_BUSINESS_085);
		} finally {
			if (flagAct && null != idLinea && null != idConciliacion) {
				String desc = "Se elimino la linea con id: ".concat(String.valueOf(idLinea))
						.concat(", perteneciente a la conciliacion: ").concat(String.valueOf(idConciliacion));
				actividadGenericMethod.registroActividad(idConciliacion, desc, TipoActividadEnum.ACTIVIDAD,
						SubTipoActividadEnum.LAYOUTS);
			}
		}
	}

	/**
	 * Genera layouts a partir de una conciliación
	 * 
	 * @param idConciliacion
	 * @return
	 */
	@Transactional
	public void enviarConciliacion(Long idConciliacion, String user) {

		long start = 0;
		long finish = 0;

		start = System.currentTimeMillis();
		LOG.debug("T >>> INCIA PROCESO DE GENERACION DE LAYOUTS: {}", start);

		// Obtiene, valida la conciliacion (en proceso)
		Conciliacion conciliacion = this.conciliacionHelper.getConciliacionByFolio(idConciliacion,
				ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);

		// Se verifican duplicados o cargados con anterioridad (nuevo = false)
		limpiarLayoutsGenerados(conciliacion.getId());

		// Se obtienen todos los layouts en base a los movimientos generados durante la
		// conciliacion
		List<LayoutDTO> layoutsDTO = buildLayoutsDTO(conciliacion.getId());

		// Se persisten los layouts
		if (layoutsDTO != null && layoutsDTO.size() > 0) {

			// Se generan todos los layouts, incluyendo cabeceras
			List<Layout> layouts = mergeLayouts(conciliacion.getId(), layoutsDTO, user, false); // Genera layouts a
																								// partir de movimientos

			// Se persisten
			try {
				// INSERTA EL LAYOUT, HEADER Y LINEAS
				for (Layout layout : layouts) {
					/*KeyHolder keyHolder = new GeneratedKeyHolder();
					long layoutId;

					// Inserta el layout si es nuevo
					if (layout.getId() == null || layout.getId() <= 0) {
						String insertQuery = ConciliacionConstants.SQLSentences.INSERT_WHITIN_TO_LAYOUT;
						jdbcTemplate.update(connection -> {
							PreparedStatement ps = connection.prepareStatement(insertQuery,
									Statement.RETURN_GENERATED_KEYS);
							ps.setLong(1, layout.getIdConciliacion());
							ps.setString(2, layout.getTipo().toString());
							return ps;
						}, keyHolder);
						// Obtiene el id de layout
	//					    layoutId = ((BigInteger) keyHolder.getKey()).longValue();
						// Se castea a String para evitar errores de compatbili
						layoutId = (new BigInteger(keyHolder.getKey().toString())).longValue();
					}
					else {
						layoutId = layout.getId();
					}*/

					// Setea el id de layout a el header
					layout.getLayoutHeader().setLayout(layout);


					// Setea el id de layout a las lineas en una nueva lista
					/*List<LayoutLinea> layoutLineasNuevasList = new ArrayList<>();
					List<LayoutLinea> layoutLineasExistentesList = new ArrayList<>();
					for (LayoutLinea layoutLineaInner : layout.getLayoutLineas()) {
						layoutLineaInner.setLayout(new Layout(layoutId));
						if (layoutLineaInner.getId() == null  || layoutLineaInner.getId() <= 0) {
							layoutLineasNuevasList.add(layoutLineaInner);
						}
						else {
							layoutLineasExistentesList.add(layoutLineaInner);
						}
					}
					// Inserta las lineas
					if (layoutLineasNuevasList.size() > 0) {
						layoutsJdbcRepository.insertarLista(layoutLineasNuevasList);
					}
					// Actualiza lineas
					if (layoutLineasExistentesList.size() > 0) {
						layoutLineasRepository.saveAll(layoutLineasExistentesList);
					}*/
					layoutsRepository.save(layout); // Persiste


					// Inserta/Actualiza el header
					//layoutsJdbcRepository.insertarLista(Arrays.asList(layout.getLayoutHeader()));
					layoutHeadersRepository.save(layout.getLayoutHeader());
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ConciliacionException("Ocurrio un error al persistir los layouts",
						CodigoError.NMP_PMIMONTE_0011);
			}
		}

		finish = System.currentTimeMillis();
		LOG.debug("T >>> INCIA PROCESO DE GENERACION DE LAYOUTS: {}", finish);

	}// End enviarConciliacion

	// PRIVATES /////////////////////////////////////////////

	/**
	 * Se encarga de generar los layouts para persistencia, se verifica si ya
	 * existen, en caso contrario se construye. Adicionalmente se verifica si los
	 * layouts ya cuentan con cabecera, si no tiene cabecera se genera una nueva
	 * 
	 * @param idConciliacion
	 * @param layoutsDTO
	 * @param requestUser
	 * @param nuevo
	 */
	private List<Layout> mergeLayouts(Long idConciliacion, List<LayoutDTO> layoutsDTO, String requestUser,
			boolean nuevo) throws ConciliacionException {
		List<Layout> layouts = null;
		if (layoutsDTO != null && layoutsDTO.size() > 0) {

			layouts = new ArrayList<Layout>();
			for (LayoutDTO layoutDTO : layoutsDTO) {

				// LAYOUT //////////////////////////////
				// Se obtiene el layout de la BD, si no existe se crea
				Layout layoutDB = getLayoutByTipoLayout(idConciliacion, layoutDTO.getTipoLayout());
				if (layoutDB == null) {
					layoutDB = buildLayout(idConciliacion, layoutDTO, requestUser);
				}

				// HEADER ////////////////////////////
				// Se verifica si se especifico header en caso contrario se crea por default
				LayoutCabeceraDTO headerDTO = layoutDTO.getCabecera();
				LayoutHeader layoutHeaderBD = null;
				if (layoutDB.getId() != null) {
					layoutHeaderBD = getLayoutHeaderByIdLayout(idConciliacion, layoutDB.getId());
				}

				// No existe el header se crea uno nuevo
				if (layoutHeaderBD == null) {
					if (headerDTO == null) {
						LayoutHeaderCatalog headerCatalog = getCabeceraCatalog(layoutDTO.getTipoLayout());
						Date fechaOperacion = this.estadoCuentaHelper.getFechaOperacionEstadoCuenta(idConciliacion, layoutDTO.getTipoLayout());
						headerDTO = LayoutsBuilder.buildLayoutCabeceraDTOFromLayoutHeaderCatalog(headerCatalog, fechaOperacion);
					}
					layoutHeaderBD = LayoutsBuilder.buildLayoutHeaderFromLayoutCabeceraDTO(headerDTO, layoutDB,
							requestUser);
					layoutDB.setLayoutHeader(layoutHeaderBD);
				} else {
					// Se actualiza el header (No se requiere)
					/*
					 * LayoutHeader layoutHeader = buildLayoutHeader(idConciliacion,
					 * layoutDTO.getCabecera()); layoutHeader.setLayout(layoutDB);
					 * layoutHeader.setId(layoutHeaderBD.getId()); layoutHeaderBD = layoutHeader;
					 * layoutHeaderBD.setLastModifiedBy(requestUser);
					 * layoutHeaderBD.setLastModifiedDate(new Date());
					 * layoutDB.setLayoutHeader(layoutHeaderBD);
					 */
				}

				// LINEAS /////////////////////
				// Se actualizan las lineas
				mergeLayoutLineas(layoutDTO.getLineas(), layoutDB, idConciliacion, requestUser, nuevo);

				layouts.add(layoutDB);
			}
		}
		return layouts;
	}

	/**
	 * Se encarga de actualizar o insertar las lineas recibidas vs las existentes en
	 * la bd
	 * 
	 * @param lineas
	 * @param layout
	 * @param requestUser
	 * @param nuevo
	 */
	private void mergeLayoutLineas(List<LayoutLineaDTO> lineasDTO, Layout layout, Long idConciliacion,
			String requestUser, boolean nuevo) throws ConciliacionException {

		if (lineasDTO != null && lineasDTO.size() > 0) {

			List<LayoutLinea> lineas = LayoutsBuilder.buildLayoutLineaFromLayoutLineaDTO(lineasDTO, layout, requestUser);

			// Existentes
			for (LayoutLinea linea : lineas) {
				if (linea.getId() != null && linea.getId() > 0) {
					boolean found = false;
					if (layout.getLayoutLineas() != null && layout.getLayoutLineas().size() > 0) {
						for (LayoutLinea lineaBD : layout.getLayoutLineas()) {
							if (linea.getId().equals(lineaBD.getId())) {
								LayoutsBuilder.mergeLinea(lineaBD, linea, requestUser);
								found = true;
								break;
							}
						}
						if (!found) {
							throw new ConciliacionException("No existe la linea layout con id " + linea.getId(),
									CodigoError.NMP_PMIMONTE_0011);
						}
					} else {
						throw new ConciliacionException("No existe la linea layout con id " + linea.getId(),
								CodigoError.NMP_PMIMONTE_0011);
					}
				}
			}

			// Nuevas
			for (LayoutLinea linea : lineas) {
				// Valida si existe el layout
				if (linea.getId() == null || linea.getId() == 0) {
					linea.setId(null);
					linea.setNuevo(nuevo);
					linea.setCreatedBy(requestUser);
					linea.setCreatedDate(new Date());
					layout.addLayoutLinea(linea);
				}
			}
		}
	}

	/**
	 * Se encarga de construir un layout en base a los parametros del DTO recibidos
	 * 
	 * @param layoutDTO
	 * @param requestUser
	 * @return
	 */
	private Layout buildLayout(Long idConciliacion, LayoutDTO layoutDTO, String requestUser)
			throws ConciliacionException {
		Layout layout = LayoutsBuilder.buildLayoutFromLayoutDTO(layoutDTO, requestUser);
		layout.setIdConciliacion(idConciliacion);
		layout.setTipo(layoutDTO.getTipoLayout());
		return layout;
	}

	/**
	 * Genera los layouts en base a la conciliacion
	 * 
	 * @param idConciliacion
	 */
	private List<LayoutDTO> buildLayoutsDTO(Long idConciliacion) {

		List<LayoutDTO> layoutsDTO = new ArrayList<LayoutDTO>();

		// Se construyen todos los layouts en base a los movimientos de pagos,
		// devoluciones, comisiones
		// PAGOS /////
		LayoutDTO layoutDTO = buildLayoutDTO(idConciliacion, TipoLayoutEnum.PAGOS);
		if (layoutDTO != null) {
			layoutsDTO.add(layoutDTO);
		}

		// DEVOLUCIONES ////
		layoutDTO = buildLayoutDTO(idConciliacion, TipoLayoutEnum.DEVOLUCIONES);
		if (layoutDTO != null) {
			layoutsDTO.add(layoutDTO);
		}

		// COMISIONES MOVIMIENTOS SE OBTIENEN DEL ESTADO DE CUENTA
		layoutDTO = buildLayoutDTO(idConciliacion, TipoLayoutEnum.COMISIONES_MOV);
		if (layoutDTO != null) {
			layoutsDTO.add(layoutDTO);
		}

		// COMISIONES GENERALES: // TODO: NO SE TIENE IDENTIFICADO AUN
		//layoutDTO = buildLayoutDTO(idConciliacion, TipoLayoutEnum.COMISIONES_GENERALES);
		//if (layoutDTO != null) {
		//	layoutsDTO.add(layoutDTO);
		//}

		return layoutsDTO;
	}

	/**
	 * Genera los layouts para el movimiento solo si existen movimientos
	 * 
	 * @param idConciliacion
	 * @param tipo
	 * @return
	 */
	private LayoutDTO buildLayoutDTO(Long idConciliacion, TipoLayoutEnum tipo) throws ConciliacionException {

		LayoutDTO layoutDTO = null;

		// Se obtienen los movimientos de acuerdo al tipo de layout
		List<IMovTransaccion> movimientos = obtenerMovimientosConciliacion(idConciliacion, tipo);
		if (movimientos != null && movimientos.size() > 0) {

			// Se construyen las lineas
			List<LayoutLineaDTO> lineasDTO = new ArrayList<LayoutLineaDTO>();
			List<LayoutLineaDTO> lineasIVADTO = new ArrayList<LayoutLineaDTO>();
			
			// Extraer operaciones de Estado cuenta
			lineasDTO.addAll(buildLineasDTO(movimientos, tipo, GrupoLayoutEnum.BANCOS));

			// Layout especial : Extraer tambien comisiones IVA al extraer comisiones del estado de cuenta
			if (tipo == TipoLayoutEnum.COMISIONES_MOV) {
				List<IMovTransaccion> movimientosIVA = obtenerMovimientosConciliacion(idConciliacion, TipoLayoutEnum.COMISIONES_IVA);
				lineasIVADTO.addAll(buildLineasDTO(movimientosIVA, TipoLayoutEnum.COMISIONES_IVA, GrupoLayoutEnum.BANCOS));
			}

			// Extraer operaciones de sucursales
			if (tipo != TipoLayoutEnum.COMISIONES_MOV && tipo != TipoLayoutEnum.COMISIONES_IVA) {
				lineasDTO.addAll(buildLineasDTO(movimientos, tipo, GrupoLayoutEnum.SUCURSALES));
			}
			else {
				// Prorrateo comisiones por sucursal, iva 
				lineasDTO.addAll(buildLineasComisionesSucursal(lineasDTO, lineasIVADTO, idConciliacion));
			}

			// Se genera la cabecera correspondiente
			LayoutCabeceraDTO cabeceraDTO = buildCabeceraDTO(idConciliacion, tipo);

			// Suma las lineas IVA (en caso de existir)
			lineasDTO.addAll(lineasIVADTO);

			// Se crea el layout correspondiente
			layoutDTO = new LayoutDTO();
			layoutDTO.setFolio(idConciliacion);
			layoutDTO.setTipoLayout(tipo);
			layoutDTO.setLineas(lineasDTO);
			layoutDTO.setCabecera(cabeceraDTO);
		}

		return layoutDTO;
	}


	/**
	 * Comisión - Obtengo el número de operaciones por sucursal que será prorrateado por la comisión cobrada (cantidad acordada con la entidad bancaria por operación).
	 * IVA - se saca el IVA (%16) por operación
	 * @param lineasDTO
	 * @param lineasIVADTO 
	 * @param idConciliacion
	 * @return
	 */
	private Collection<? extends LayoutLineaDTO> buildLineasComisionesSucursal(List<LayoutLineaDTO> lineasDTO,
			List<LayoutLineaDTO> lineasIVADTO, long idConciliacion) {

		List<LayoutLineaDTO> lineasComisionIVA = new ArrayList<LayoutLineaDTO>();

		if (CollectionUtils.isNotEmpty(lineasDTO)) {

			// Obtiene el total de operaciones por sucursal
			List<OperacionesPorSucursalDTO> totalOpPorSuc = obtenerTotalOperacionesPorSucursal(idConciliacion, TipoLayoutEnum.COMISIONES_MOV);

			if (totalOpPorSuc != null && totalOpPorSuc.size() > 0) {
				LOG.debug("Existen " + totalOpPorSuc.size() + " sucursales con operaciones para el rango de fechas");

				// Inicia el proceso de prorrateo
				LOG.debug("Inicia el proceso de prorrateo");

				// Obtiene el monto total de comisiones
				BigDecimal montoTotalComisiones = LayoutsBuilder.getMontoLineas(lineasDTO);
				BigDecimal montoTotalIVA = LayoutsBuilder.getMontoLineas(lineasIVADTO);
				LOG.debug("Total monto Comisiones: " + montoTotalComisiones);
				LOG.debug("Total monto IVA: " + montoTotalIVA);

				// Total de operaciones realizadas
				int totalOperaciones = LayoutsBuilder.getTotalOperaciones(totalOpPorSuc);
				LOG.debug("Total operaciones realizadas en todas las sucursales: " + totalOperaciones);

				// Se prorratean layouts para comisiones y se generan las lineas
				if (montoTotalComisiones.compareTo(new BigDecimal(0)) > 0) {
					lineasComisionIVA.addAll(buildLineasComisionesProrrateado(totalOpPorSuc, montoTotalComisiones, totalOperaciones, TipoLayoutEnum.COMISIONES_MOV));
				}

				// Se prorratean layouts para IVA y se generan las lineas
				if (montoTotalIVA.compareTo(new BigDecimal(0)) > 0) {
					lineasComisionIVA.addAll(buildLineasComisionesProrrateado(totalOpPorSuc, montoTotalIVA, totalOperaciones, TipoLayoutEnum.COMISIONES_IVA));
				}

			}
			else {
				LOG.debug("No existen operaciones para el rango de fechas");
			}
		}

		return lineasComisionIVA;
	}


	/**
	 * Genera las lineas de comisiones sucursal acuerdo al monto de comisiones a distribuir 
	 * @param totalOpPorSuc
	 * @param montoTotalComisiones
	 * @param totalOperaciones
	 * @param tipoLayout
	 */
	private List<LayoutLineaDTO> buildLineasComisionesProrrateado(List<OperacionesPorSucursalDTO> totalOpPorSuc,
			BigDecimal montoTotal, int totalOperaciones, TipoLayoutEnum tipoLayout) {

		List<LayoutLineaDTO> lineasComision = new ArrayList<LayoutLineaDTO>();
		for (OperacionesPorSucursalDTO opPorSuc : totalOpPorSuc) {
			if (opPorSuc.getTotal() > 0) {

				// Peso
				BigDecimal pesoSucursal = LayoutsBuilder.getPesoOperacionesSuc(opPorSuc.getTotal(), totalOperaciones);
				LOG.debug("Sucursal {} tiene {} operaciones con un peso de {}%", opPorSuc.getSucursal(), opPorSuc.getTotal(), pesoSucursal);
				
				// Se prorratea el monto de acuerdo al peso de la sucursal
				BigDecimal montoSucursal = LayoutsBuilder.getPorcentajeSucursal(montoTotal, pesoSucursal);
				LOG.debug("Sucursal tiene un monto asignado de {}/{} para {}", montoSucursal, montoTotal, tipoLayout);

				// Se genera la linea correspondiente
				MovimientoComision movimientoComision = new MovimientoComision();
				movimientoComision.setMovimientoMidas(new MovimientoMidas());
				movimientoComision.getMovimientoMidas().setSucursal(opPorSuc.getSucursal());
				LayoutLineaCatalog lineaCatalog = getLayoutLineaCatalog(tipoLayout, GrupoLayoutEnum.SUCURSALES);
				String unidadOperativa = getUnidadOperativa(movimientoComision, lineaCatalog);
				LayoutLineaDTO lineaDTO = LayoutsBuilder.buildLayoutLineaDTOFromLayoutLineaCatalog(lineaCatalog, montoSucursal, unidadOperativa);

				lineasComision.add(lineaDTO);
			}
		}
		return lineasComision;
	}


	/**
	 * Obtiene el total de operaciones por sucursal para el rango capturado en el modulo de comisiones transaccion
	 * @param idConciliacion
	 * @param tipoLayout
	 * @return
	 */
	private List<OperacionesPorSucursalDTO> obtenerTotalOperacionesPorSucursal(long idConciliacion, TipoLayoutEnum tipoLayout) {
		// Se obtienen el rango de fechas de la proyeccion para obtener el total de movimientos realizados
		LOG.debug("obtenerTotalOperacionesPorSucursal {}", idConciliacion);

		Date fechaDesde = DateUtil.getDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd");
		Date fechaHasta = DateUtil.getDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd");

		ComisionTransaccion comisionTransaccion  = comisionTransaccionRepository.findByConciliacionId(idConciliacion);
		if (comisionTransaccion != null) {
			LOG.debug("Usando fechas del rango de comision transaccion");
			fechaDesde = comisionTransaccion.getFechaDesde();
			fechaHasta = comisionTransaccion.getFechaHasta();
		}
		else {
			Date fechaOperacion = this.estadoCuentaHelper.getFechaOperacionEstadoCuenta(idConciliacion, tipoLayout);
			fechaDesde = fechaOperacion;
			fechaHasta = fechaOperacion;
		}

		LOG.debug("Fecha inicial: " + fechaDesde);
		LOG.debug("Fecha final: " + fechaHasta);
		
		// Se obtienen el total de partidas con pagos por sucursal en el rango de fechas
		List<OperacionesPorSucursalDTO> totalOpPorSuc = new ArrayList<OperacionesPorSucursalDTO>();
		List<Object[]> listObject = this.movimientosMidasRepository.getTotalOperacionesRealesPorSucursal(fechaDesde, fechaHasta);
		if (listObject != null && listObject.size() > 0) {
			for (Object[] object : listObject) {
				if (object != null && object.length > 0 && object[0] != null) {
					totalOpPorSuc.add(new OperacionesPorSucursalDTO(
						Integer.valueOf(object[0].toString()),
						object.length > 1 && object[1] != null ? Integer.valueOf(object[1].toString()) : 0
					));
				}
			}
		}

		return totalOpPorSuc;
	}


	/**
	 * Construye la cabecera para el tipo de layout especificado usando los valores
	 * por default configurados en la bd
	 * 
	 * @param idConciliacion
	 * @param tipoLayout
	 * @return
	 */
	private LayoutCabeceraDTO buildCabeceraDTO(Long idConciliacion, TipoLayoutEnum tipoLayout) {

		// Obtiene la cabecera del catalogo correspondiente al tipo layout
		LayoutHeaderCatalog layoutHeaderCatalog = getCabeceraCatalog(tipoLayout);

		// Se construye la cabecera
		Date fechaOperacion = this.estadoCuentaHelper.getFechaOperacionEstadoCuenta(idConciliacion, tipoLayout);
		LayoutCabeceraDTO cabecera = LayoutsBuilder.buildLayoutCabeceraDTOFromLayoutHeaderCatalog(layoutHeaderCatalog, fechaOperacion);
		return cabecera;
	}


	/**
	 * Se construyen las lineas de acuerdo al tipo de layout y grupo
	 * 
	 * @param movimientos
	 * @param tipo
	 * @param grupo
	 * @return
	 */
	private List<LayoutLineaDTO> buildLineasDTO(List<IMovTransaccion> movimientos, TipoLayoutEnum tipo,
			GrupoLayoutEnum grupo) throws ConciliacionException {

		// Genera las lineas
		List<LayoutLineaDTO> lineasDTO = new ArrayList<LayoutLineaDTO>();

		// Agrupar movimientos por tipo y grupo
		List<IMovTransaccion> movimientosByGrupo = agruparMovimientos(movimientos, tipo, grupo);
		LayoutLineaCatalog lineaCatalog = getLayoutLineaCatalog(tipo, grupo);
		if (lineaCatalog == null) {
			throw new ConciliacionException(
					"No existe configuracion de la linea para el layout " + tipo + " y grupo " + grupo);
		}

		// Genera las lineas de acuerdo al tipo y grupo
		for (IMovTransaccion movimiento : movimientosByGrupo) {
			BigDecimal monto = getMontoMovimiento(movimiento, tipo, grupo);
			String unidadOperativa = getUnidadOperativa(movimiento, lineaCatalog);
			LayoutLineaDTO lineaDTO = LayoutsBuilder.buildLayoutLineaDTOFromLayoutLineaCatalog(lineaCatalog, monto,
					unidadOperativa);
			lineasDTO.add(lineaDTO);
		}

		return lineasDTO;
	}

	/**
	 * Agrupa los movimientos de acuerdo al tipo de movimiento y grupo SUCURSAL o
	 * BANCO
	 * 
	 * @param movimientos
	 * @param tipo
	 * @param grupo
	 * @return
	 */
	private List<IMovTransaccion> agruparMovimientos(List<IMovTransaccion> movimientos, TipoLayoutEnum tipo, GrupoLayoutEnum grupo) {
		List<IMovTransaccion> movimientosGrupo = new ArrayList<IMovTransaccion>();
		IMovTransaccion movimiento = null;

		if (movimientos != null && movimientos.size() > 0) {
			for (int i = 0; i < movimientos.size(); i++) {
				movimiento = movimientos.get(i);
				switch (grupo) {
					case BANCOS:
						if (movimiento.getMovimientoMidas() == null) {
							movimientosGrupo.add(movimiento);
						}
						break;
					case SUCURSALES:
						if (movimiento.getMovimientoMidas() != null) {
							movimientosGrupo.add(movimiento);
						}
						break;
				}
			}
		}
		return movimientosGrupo;
	}

	/**
	 * Obtiene el monto del movimiento de la conciliacion de acuerdo al tipo de
	 * movimiento
	 * 
	 * @param movimiento
	 * @param tipo
	 * @param grupo
	 * @return
	 */
	private BigDecimal getMontoMovimiento(IMovTransaccion movimiento, TipoLayoutEnum tipo, GrupoLayoutEnum grupo) {
		BigDecimal monto = new BigDecimal(0);
		switch (tipo) {
			case PAGOS:
				monto = ((MovimientoPago) movimiento).getMonto();
				//Monto: el monto de las sucursales deberán ser negativos y el monto del banco deberá ser positivo
				if (monto != null) {
					monto = (grupo == GrupoLayoutEnum.SUCURSALES ? monto.negate() : monto);
				}
				break;
			case DEVOLUCIONES:
				monto = ((MovimientoDevolucion) movimiento).getMonto();
				// cantidad total por sucursal positivo
				// cantidad extraída del estado de cuenta cuando la leyenda sea devoluciones. Valores serán colocaos en negativo.
				if (monto != null) {
					monto = (grupo == GrupoLayoutEnum.BANCOS ? monto.negate() : monto);
				}
				break;
			case COMISIONES_GENERALES:
			case COMISIONES_MOV:
			case COMISIONES_IVA:
				monto = ((MovimientoComision) movimiento).getMonto();
				//cantidad total por sucursal positivo
				//cantidad extraída del estado de cuenta cuando la leyenda sea comisiones. Valores serán colocaos en negativo
				if (monto != null) {
					monto = (grupo == GrupoLayoutEnum.BANCOS ? monto.negate() : monto);
				}
				break;
			default:
				break;
		}
		return monto;
	}

	/**
	 * Obtiene la unidad operativa de acuerdo al tipo de layout y grupo SUCURSAL o
	 * BANCO
	 * 
	 * @param movimiento
	 * @param lineaCatalog
	 * @return
	 */
	private String getUnidadOperativa(IMovTransaccion movimiento, LayoutLineaCatalog lineaCatalog) {
		String unidadOperativa = "";
		if (StringUtils.isNotBlank(lineaCatalog.getUnidadOperativa())) {
			switch (lineaCatalog.getTipo()) {
			case PAGOS:
				MovimientoPago movimientoPago = (MovimientoPago) movimiento;
				if (movimientoPago.getMovimientoMidas() != null
						&& movimientoPago.getMovimientoMidas().getSucursal() != null) {
					unidadOperativa = String.format(lineaCatalog.getUnidadOperativa(),
							movimientoPago.getMovimientoMidas().getSucursal());
				}
				break;
			case DEVOLUCIONES:
				MovimientoDevolucion movimientoDevolucion = (MovimientoDevolucion) movimiento;
				if (movimientoDevolucion.getMovimientoMidas() != null
						&& movimientoDevolucion.getMovimientoMidas().getSucursal() != null) {
					unidadOperativa = String.format(lineaCatalog.getUnidadOperativa(),
							movimientoDevolucion.getMovimientoMidas().getSucursal());
				}
				break;
			case COMISIONES_MOV:
			case COMISIONES_GENERALES:
			case COMISIONES_IVA:
				MovimientoComision movimientoComision = (MovimientoComision) movimiento;
				if (movimientoComision.getMovimientoMidas() != null
						&& movimientoComision.getMovimientoMidas().getSucursal() != null) {
					unidadOperativa = String.format(lineaCatalog.getUnidadOperativa(),
							movimientoComision.getMovimientoMidas().getSucursal());
				}
				break;
			}
		}
		return unidadOperativa;
	}

	/**
	 * Obtiene el listado de movimientos conciliacion de acuerdo al tipo de layout
	 * 
	 * @param idConciliacion
	 * @param tipo
	 * @return
	 */
	private List<IMovTransaccion> obtenerMovimientosConciliacion(Long idConciliacion, TipoLayoutEnum tipo) {

		List<IMovTransaccion> movimientos = new ArrayList<IMovTransaccion>();

		switch (tipo) {
			case PAGOS:
				// Se obtienen los movimientos de pagos // Remover esta consulta
				List<MovimientoPago> movimientoPagoList = movimientoConciliacionRepository.findMovimientoPagoByConciliacionId(idConciliacion);
				// Lo siguiente es para setear de manera manual los ids de sucursal a cada
				// movimiento ya que la consulta no trae esa informacion
				if(null != movimientoPagoList && !movimientoPagoList.isEmpty()) {
					List<Integer> idsList = new ArrayList<>();
					for (MovimientoPago movimientoPago : movimientoPagoList) {
						idsList.add(movimientoPago.getId());
					}
					List<Object[]> result = movimientoConciliacionRepository.getMapSucursalesByMovimientoConciliacionIds(idsList);
					Map<Integer, Integer> movimientosPagosSucursalesMap = getMapValues(result);
					for (MovimientoPago movimientoPago : movimientoPagoList) {
						movimientoPago.getMovimientoMidas()
								.setSucursal((movimientosPagosSucursalesMap.get(movimientoPago.getId())));
					}
					movimientos.addAll(movimientoPagoList);
				}
				// Se obtienen los movimientos de pagos midas // MOVIMIENTOS SUCURSAL
				movimientos.addAll(obtenerMovimientosMidasPagos(idConciliacion, tipo));
	
				// Se obtienen los movimientos de pagos estado de cuenta // MOVIMIENTOS BANCO
				movimientos.addAll(obtenerMovimientosEstadoCuentaPagos(idConciliacion, tipo));
	
				break;
			case DEVOLUCIONES:

				// Movimientos devoluciones Estado Cuenta.
				// Si no existen movimientos de estado de cuenta no se extraen los movimientos liquidados
				List<MovimientoEstadoCuenta> movsEstadoCuentaDevolucion = estadoCuentaHelper
						.getMovimientosEstadoCuentaByCategoria(idConciliacion, ConciliacionConstants.CATEGORIA_ESTADO_CUENTA_DEVOLUCIONES);
				if (CollectionUtils.isNotEmpty(movsEstadoCuentaDevolucion)) {
					
					Set<Date> fechasDevoluciones = new LinkedHashSet<Date>();
					for (MovimientoEstadoCuenta movEstadoCuenta : movsEstadoCuentaDevolucion) {
						
						// Se obtienen todas las fechas de devoluciones
						fechasDevoluciones.add(movEstadoCuenta.getFechaOperacion());

						// Se mapean los movs estado de cuenta en movs devoluciones
						MovimientoDevolucion movDevolucion = MovimientoDevolucionBuilder
								.buildMovimientoFromMovEstadoCuenta(movEstadoCuenta, idConciliacion, null, null);
						movimientos.add(movDevolucion);
					}

					// Movimientos devoluciones Sucursal ///////

					// Movimientos sucursal liquidados consultando por fechas de operacion
					List<Date> fechasLiquidacion = Arrays.asList(fechasDevoluciones.toArray(new Date[0]));
					List<MovimientoDevolucion> movimientosDev = movimientoConciliacionRepository
						.findMovimientoDevolucionByFechaLiquidacionInAndStatus(fechasLiquidacion, ConciliacionConstants.ESTATUS_DEVOLUCION_LIQUIDADA);
					// Movimientos 

					// Agrupar por sucursal
					movimientosDev = MovimientosBuilder.groupMovimientosBySucursal(movimientosDev, TipoMovimientoEnum.DEVOLUCION);
					movimientos.addAll(movimientosDev);
				}
				break;
			case COMISIONES_MOV:
				movimientos.addAll(movimientoConciliacionRepository.findMovimientoComisionByConciliacionIdAndTipo(idConciliacion, TipoMovimientoComisionEnum.COMISION));
				break;
			case COMISIONES_IVA:
				movimientos.addAll(movimientoConciliacionRepository.findMovimientoComisionByConciliacionIdAndTipo(idConciliacion, TipoMovimientoComisionEnum.IVA_COMISION));
				break;
			//TODO: No identificados aun
			//case COMISIONES_GENERALES: // TipoMovimientoComisionEnum.GENERALES
				//movimientos.addAll(movimientoConciliacionRepository.findMovimientoComisionByConciliacionIdAndTipo(idConciliacion, TipoMovimientoComisionEnum.GENERAL));
				//break;
		}
		return movimientos;
	}


	/**
	 * Obtiene los movimientos midas DS, RF, APL correspondientes
	 * 
	 * @param idConciliacion
	 * @param tipo
	 * @return
	 */
	private List<MovimientoPago> obtenerMovimientosMidasPagos(Long idConciliacion, TipoLayoutEnum tipo)
			throws ConciliacionException {
		List<MovimientoPago> movimientosMidas = new ArrayList<MovimientoPago>();

		try {
			List<MovimientoMidasDTO> movMidasDTO = this.movimientosMidasRepository
					.getMovimientosMidasBySucursal(idConciliacion);
			if (CollectionUtils.isNotEmpty(movMidasDTO)) {
				for (MovimientoMidasDTO movDTO : movMidasDTO) {
					// Se crea un movimiento pago incluyendo los datos del movimiento midas
					MovimientoPago movPago = new MovimientoPago();
					// Se crea un movimiento midas incluyendo monto, sucursal y operacion
					MovimientoMidas movMidas = new MovimientoMidas();
					movMidas.setMonto(movDTO.getMontoOperacion());
					movMidas.setSucursal(movDTO.getSucursal());
					movMidas.setOperacionAbr(movDTO.getOperacionAbr());
					movPago.setMovimientoMidas(movMidas);
					movPago.setMonto(movDTO.getMontoOperacion());
					movPago.setNuevo(false);
					movimientosMidas.add(movPago);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ex.getMessage(), CodigoError.NMP_PMIMONTE_0011);
		}

		return movimientosMidas;
	}


	/**
	 * Obtiene los movimientos del tipo especificado del estado de cuenta
	 * @param idConciliacion
	 * @param tipo
	 * @return
	 * @throws ConciliacionException
	 */
	private List<MovimientoPago> obtenerMovimientosEstadoCuentaPagos(Long idConciliacion, TipoLayoutEnum tipo)
			throws ConciliacionException {

		List<MovimientoPago> movimientosEstadoCuenta = new ArrayList<MovimientoPago>();
		try {
			List<MovimientoEstadoCuenta> movimientos = null;
			switch (tipo) {
				case PAGOS:
					movimientos = estadoCuentaHelper.getMovimientosEstadoCuentaByCategoria(idConciliacion, ConciliacionConstants.CATEGORIA_ESTADO_CUENTA_VENTAS);
					break;
				default:
					break;
			}
			if (CollectionUtils.isNotEmpty(movimientos)) {
				for (MovimientoEstadoCuenta mov : movimientos) {
					// Se crea un movimiento pago incluyendo los datos del movimiento estado cuenta
					MovimientoPago movPago = new MovimientoPago();
					movPago.setMovimientoMidas(null);
					movPago.setMonto(mov.getImporte());
					movPago.setNuevo(false);
					movimientosEstadoCuenta.add(movPago);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ex.getMessage(), CodigoError.NMP_PMIMONTE_0011);
		}

		return movimientosEstadoCuenta;
	}


	/**
	 * Se encarga de obtener el layout por tipo de layout
	 * 
	 * @param idConciliacion
	 * @param tipoLayout
	 * @return
	 * @throws ConciliacionException
	 */
	private Layout getLayoutByTipoLayout(Long idConciliacion, TipoLayoutEnum tipoLayout) throws ConciliacionException {
		Layout layout = null;
		try {
			List<Layout> layouts = this.layoutsRepository.findByIdConciliacionAndTipo(idConciliacion, tipoLayout);
			if (layouts != null && layouts.size() > 0) {
				layout = layouts.get(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Ocurrio un error consultar el layout", CodigoError.NMP_PMIMONTE_0011);
		}
		return layout;
	}

	/**
	 * Obtiene la cabecera en base al id del layout
	 * 
	 * @param idConciliacion
	 * @param idLayout
	 * @return
	 */
	private LayoutHeader getLayoutHeaderByIdLayout(Long idConciliacion, Long idLayout) {
		LayoutHeader layoutHeader = null;
		try {
			layoutHeader = this.layoutHeadersRepository.findByLayoutId(idLayout);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Ocurrio un error consultar el header para el layout " + idLayout,
					CodigoError.NMP_PMIMONTE_0011);
		}
		return layoutHeader;
	}

	/**
	 * Obtiene la linea del catalogo de acuerdo tipo de layout
	 * 
	 * @param tipoLayout
	 * @return
	 * @throws ConciliacionException
	 */
	private LayoutLineaCatalog getLayoutLineaCatalog(TipoLayoutEnum tipoLayout, GrupoLayoutEnum grupo)
			throws ConciliacionException {
		LayoutLineaCatalog lineaCatalog = null;

		// Se obtienen los valores por default de la linea de acuerdo al tipo de layout
		try {
			lineaCatalog = layoutLineaCatalogRepository.findByTipoAndGrupo(tipoLayout, grupo);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Ocurrio un error consultar la linea del catalogo para el tipo layout "
					+ tipoLayout + " y grupo " + grupo, CodigoError.NMP_PMIMONTE_0011);
		}

		if (lineaCatalog == null) {
			throw new ConciliacionException(
					"No existe la linea del catalogo para el tipo de layout " + tipoLayout + " y grupo " + grupo,
					CodigoError.NMP_PMIMONTE_0011);
		}

		return lineaCatalog;
	}

	/**
	 * Obtiene la cabecera del catalogo correspondiente al tipo de layout
	 * 
	 * @param tipoLayout
	 * @return
	 */
	private LayoutHeaderCatalog getCabeceraCatalog(TipoLayoutEnum tipoLayout) {
		// Se obtiene la configuracion de la cabecera en la bd
		LayoutHeaderCatalog layoutHeaderCatalog = null;
		try {
			layoutHeaderCatalog = layoutHeaderCatalogRepository.findByTipo(tipoLayout);// buscarLayoutHeader
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(
					"No existe la configuracion de la cabecera del layout de tipo " + tipoLayout,
					CodigoError.NMP_PMIMONTE_0011);
		}

		if (layoutHeaderCatalog == null) {
			throw new ConciliacionException(
					"No existe la configuracion de la cabecera del layout de tipo " + tipoLayout,
					CodigoError.NMP_PMIMONTE_0011);
		}

		return layoutHeaderCatalog;
	}

	/**
	 * Elimina los layouts que han sido generados con anterioridad (todos layouts
	 * con la bandera nuevo = false)
	 * 
	 * @param idConciliacion
	 */
	private void limpiarLayoutsGenerados(Long idConciliacion) throws ConciliacionException {
		try {
			this.layoutsRepository.deleteByIdConciliacionAndNuevo(idConciliacion, false); // Solo layouts generados
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al regenerar los layouts", CodigoError.NMP_PMIMONTE_0011);
		}
	}

	/**
	 * Valida los campos idConciliacion y tipoLayout
	 * 
	 * @param idConciliacion
	 * @param tipoLayout
	 * @return
	 */
	public boolean validar(Long idConciliacion, TipoLayoutEnum tipoLayout) {
		return idConciliacion > 0L && tipoLayout == TipoLayoutEnum.PAGOS || tipoLayout == TipoLayoutEnum.COMISIONES_MOV
				|| tipoLayout == TipoLayoutEnum.COMISIONES_GENERALES || tipoLayout == TipoLayoutEnum.DEVOLUCIONES;
	}

	/**
	 * Valida layoutDTO
	 * 
	 * @param layoutDTO
	 * @return
	 */
	public boolean validar(LayoutDTO layoutDTO) {
		return layoutDTO.getFolio() > 0L && !layoutDTO.getTipoLayout().toString().equals("")
				&& layoutDTO.getLineas().size() > 0;
	}

	/**
	 * Obtiene el id de mov. conciliacion y sucursal de una lista de arreglo de
	 * objetos y los pone en un mapa para su mas rapido acceso
	 * 
	 * @param values
	 * @return
	 */
	private static Map<Integer, Integer> getMapValues(List<Object[]> values) {
		Map<Integer, Integer> map = new HashMap<>();
		;
		if (null != values) {
			for (Object[] obj : values) {
				map.put(Integer.parseInt(obj[0].toString()), Integer.parseInt(obj[1].toString()));
			}
		}
		return map;
	}

}