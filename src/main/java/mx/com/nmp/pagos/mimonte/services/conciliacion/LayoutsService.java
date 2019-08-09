/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.LayoutsBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutHeaderCatalogRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutHeadersRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutLineaCatalogRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutLineasRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutsRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutCabeceraDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutEditionValidationDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutLineaDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.HeaderCatalogEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Layout;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeader;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeaderCatalog;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLinea;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLineaCatalog;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoPago;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;
import mx.com.nmp.pagos.mimonte.util.ConciliacionDataValidator;

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
			}
			catch (Exception ex) {
				ex.printStackTrace();
				throw new ConciliacionException("Error al consultar el layout tipo " + tipo, CodigoError.NMP_PMIMONTE_BUSINESS_113);
			}
		}
		
		// TODO: Borrar esta sentencia if dado que es valido que sea nulo en algunas ocaciones (no siempre existe un tipo X de layout para una conciliacion)
//		if (layout == null) {
//			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
//					CodigoError.NMP_PMIMONTE_0009);
//		}
		
		// TODO: Obtener el header para incrustar en el objeto de retorno
		
		return LayoutsBuilder.buildLayoutDTOFromLayout(layout);
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
		}
		catch(ConciliacionException ex) {
			LOG.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
			throw ex;
		}
		catch(Exception ex) {
			LOG.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_113.getDescripcion(), CodigoError.NMP_PMIMONTE_BUSINESS_113);
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

		// Se valida que la conciliacion se encuentre en proceso
		Conciliacion conciliacion = this.conciliacionHelper.getConciliacionByFolio(layoutDTO.getFolio(),
				ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);

		// Se encarga de persistir o actualizar el layout, header y lineas
		List<Layout> layouts = mergeLayouts(conciliacion.getId(), Arrays.asList(layoutDTO), requestUser);

		// Se persisten los layouts
		if (layouts != null && layouts.size() > 0) {
			try {
				this.layoutsRepository.saveAll(layouts);
			}
			catch (Exception ex) {
				ex.printStackTrace();
				throw new ConciliacionException("Ocurrio un error al persistir los layouts", CodigoError.NMP_PMIMONTE_0011);
			}
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
		
		// LOGICA DE ELIMINACION
		try {
			// Valida que la conciliacion exista
			conciliacionDataValidator.validateFolioExists(idConciliacion);
			
			// Valida que el id de layout exista
			flag = ((BigInteger)layoutsRepository.checkIfIdExist(idLayout)).compareTo(BigInteger.ONE) == 0;
			if(!flag)
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_085.getDescripcion(), CodigoError.NMP_PMIMONTE_BUSINESS_085);
			
			// Valida que la conciliacion este ligada al id de layout especificado
			flag = ((BigInteger)layoutsRepository.checkIfFolioIdRelationshipExist(idConciliacion.intValue(), idLayout)).compareTo(BigInteger.ONE) == 0;
			if(!flag)
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_115.getDescripcion(), CodigoError.NMP_PMIMONTE_BUSINESS_115);
			
			// Valida que el layout no tenga lineas dadas de alta desde el sistema para poder eliminar (nuevo = 1)
			flag = ((BigInteger)layoutsRepository.checkIfLineasAreNew(idConciliacion, idLayout)).compareTo(BigInteger.ONE) == 0;
			if(!flag)
				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_116.getDescripcion(), CodigoError.NMP_PMIMONTE_BUSINESS_116);
			
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
						if (layout2.getIdConciliacion() == idConciliacion) {
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
		}
		catch(ConciliacionException ex) {
			LOG.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
			throw ex;
		}
		catch(Exception ex) {
			LOG.debug(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE.concat("{}"), ex.getMessage());
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_114.getDescripcion(), CodigoError.NMP_PMIMONTE_BUSINESS_114);
		}
		return respuesta;
	}


	/**
	 * Se encarga de eliminar una linea del layout
	 * @param idLinea
	 */
	public void eliminarLinea(Long idLinea) throws ConciliacionException {
		// Objetos necesarios
		Optional<LayoutLinea> linea = null;
		Boolean flag = null;
		
		// Se valida que la linea exista
		try {
			linea = this.layoutLineasRepository.findById(idLinea);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al obtener la linea del layout asignada al id " + idLinea, CodigoError.NMP_PMIMONTE_BUSINESS_085);
		}
		if (linea == null || !linea.isPresent()) {
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_085.getDescripcion(), CodigoError.NMP_PMIMONTE_BUSINESS_085);
		}

		// Se valida que la linea se pueda elimina, o sea que fue dada de alta desde la aplicacion (nuevo = 1)
		flag = ((BigInteger)layoutLineasRepository.checkIfLineIsNew(idLinea)).compareTo(BigInteger.ONE) == 0;
		if(!flag)
			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_116.getDescripcion(), CodigoError.NMP_PMIMONTE_BUSINESS_116);
		
		// Se valida que la conciliacion asignada se encuentre en proceso
		Long idConciliacion = linea.get().getLayout().getIdConciliacion();
		this.conciliacionHelper.getConciliacionByFolio(idConciliacion, ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);

		// Se elimina la linea del layout
		try {
			this.layoutLineasRepository.deleteById(linea.get().getId());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al eliminar la linea del layout asignada al id " + idLinea, CodigoError.NMP_PMIMONTE_BUSINESS_085);
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

		// Obtiene, valida la conciliacion (en proceso)
		Conciliacion conciliacion = this.conciliacionHelper.getConciliacionByFolio(idConciliacion, ConciliacionConstants.ESTATUS_CONCILIACION_EN_PROCESO);

		// Se obtienen todos los layouts en base a los movimientos generados durante la conciliacion
		List<LayoutDTO> layoutsDTO = buildLayoutsDTO(conciliacion.getId());

		// Se persisten los layouts
		if (layoutsDTO != null && layoutsDTO.size() > 0) {

			// Se verifican duplicados o cargados con anterioridad (nuevo = false)
			limpiarLayoutsGenerados(conciliacion.getId());

			// Se generan todos los layouts, incluyendo cabeceras
			List<Layout> layouts = mergeLayouts(conciliacion.getId(), layoutsDTO, user);

			// Se persisten
			try {
				this.layoutsRepository.saveAll(layouts);
			}
			catch (Exception ex) {
				ex.printStackTrace();
				throw new ConciliacionException("Ocurrio un error al persistir los layouts", CodigoError.NMP_PMIMONTE_0011);
			}
		}

	}// End enviarConciliacion




	// PRIVATES /////////////////////////////////////////////


	/**
	 * Guarda y edita un layout como PAGOS, COMISIONES_MOV, COMISIONES_GENERALES y
	 * DEVOLUCIONES
	 * 
	 * @param layoutDTO
	 */
	private void persistirLayout(LayoutDTO layoutDTO, final String requestUser) {
		Layout layout = new Layout();
		List<LayoutEditionValidationDTO> layoutEditionValidationDTOList = null;
		List<Long> ids = null;
		Map<Long, LayoutEditionValidationDTO> map = null;
		LocalDate localDate = LocalDate.now();
		if (validar(layoutDTO)) {
			LayoutHeaderCatalog layoutHeaderCatalog = null;
			LayoutHeader layoutHeader = null;
			layout.setIdConciliacion(layoutDTO.getFolio());
			layout.setTipo(layoutDTO.getTipoLayout());
			if (LayoutsBuilder.validaLineas(layoutDTO.getLineas())) {
				ids = new ArrayList<>();
				layout.setLayoutLineas(LayoutsBuilder.buildLayoutLineaFromLayoutLineaDTO(layoutDTO.getLineas(), layout, requestUser));
				layout = layoutsRepository.saveAndFlush(layout);
				if (layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.PAGOS.toString())) {
					layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(HeaderCatalogEnum.PAGOS.id());// buscarLayoutHeader
					if (localDate.getDayOfWeek().getValue() == 5) {
						layoutHeaderCatalog.setFecha(localDate.plusDays(3));
					} else {
						layoutHeaderCatalog.setFecha(localDate);
					}
				}
				if (layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.COMISIONES_MOV.toString())) {
					layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(HeaderCatalogEnum.COMISIONES_MOV.id());
					layoutHeaderCatalog.setFecha(localDate);
				}
				if (layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.COMISIONES_GENERALES.toString())) {
					layoutHeaderCatalog = layoutHeaderCatalogRepository
							.getOne(HeaderCatalogEnum.COMISIONES_GENERALES.id());
					layoutHeaderCatalog.setFecha(localDate);
				}
				if (layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.DEVOLUCIONES.toString())) {
					layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(HeaderCatalogEnum.DEVOLUCIONES.id());
					layoutHeaderCatalog.setFecha(localDate);
				}
				layoutHeader = new LayoutHeader();
				layoutHeader.setId(layoutHeaderCatalog.getId());
				layoutHeader.setCabecera(layoutHeaderCatalog.getCabecera());
				layoutHeader.setCodigoOrigen(layoutHeaderCatalog.getCodigoOrigen());
				layoutHeader.setDescripcion(layoutHeaderCatalog.getDescripcion() + " " + LocalDate.now());
				layoutHeader.setFecha(layoutHeaderCatalog.getFecha());
				layoutHeader.setUnidadNegocio(layoutHeaderCatalog.getUnidadNegocio());

				layoutHeader.setLayout(layout);
				layoutHeadersRepository.saveAndFlush(layoutHeader);
				
				
				
				for (LayoutLinea layoutLinea : layout.getLayoutLineas()) {
					layoutLinea.setLayout(layout);
					if (null != layoutLinea.getId())
						ids.add(layoutLinea.getId());
				}
				if (!ids.isEmpty()) {
					layoutEditionValidationDTOList = layoutLineasRepository.getIdsDataValidateCreateOrUpdate(ids);
					if (null != layoutEditionValidationDTOList && !layoutEditionValidationDTOList.isEmpty()) {
						map = getMapFromDTOList(layoutEditionValidationDTOList);
					}
					for (LayoutLinea layoutLinea : layout.getLayoutLineas()) {
						if (null != map && !map.isEmpty() && map.containsKey(layoutLinea.getId())) {
							if (null == map.get(layoutLinea.getId()).getCreatedBy()) {
								layoutLinea.setCreatedBy(requestUser);
								layoutLinea.setCreatedDate(new Date());
							} else {
								layoutLinea.setLastModifiedBy(requestUser);
								layoutLinea.setLastModifiedDate(new Date());
							}
						} else {
							layoutLinea.setCreatedBy(requestUser);
							layoutLinea.setCreatedDate(new Date());
						}
					}

				}
				layoutLineasRepository.saveAll(layout.getLayoutLineas());
			} // End if
		} // End if
	}


	/**
	 * Cobstruye un mapa de tipo Long, LayoutEditionValidationDTO a partir de una
	 * lista de tipo LayoutEditionValidationDTO
	 * 
	 * @param list
	 * @return
	 */
	private Map<Long, LayoutEditionValidationDTO> getMapFromDTOList(List<LayoutEditionValidationDTO> list) {
		Map<Long, LayoutEditionValidationDTO> map = null;
		if (null != list && !list.isEmpty()) {
			map = new HashMap<>();
			for (LayoutEditionValidationDTO elem : list) {
				map.put(elem.getId(), elem);
			}
		}
		return map;
	}


	/**
	 * Se encarga de generar los layouts para persistencia, se verifica si ya existen, en caso contrario se construye.
	 * Adicionalmente se verifica si los layouts ya cuentan con cabecera, si no tiene cabecera se genera una nueva
	 * @param idConciliacion
	 * @param layoutsDTO
	 * @param requestUser 
	 */
	private List<Layout> mergeLayouts(Long idConciliacion, List<LayoutDTO> layoutsDTO, String requestUser) throws ConciliacionException {
		List<Layout> layouts = null;
		if (layoutsDTO != null && layoutsDTO.size() > 0) {
			
			layouts = new ArrayList<Layout>();
			for (LayoutDTO layoutDTO : layoutsDTO) {

				// Se obtiene el layout de la BD, si no existe se crea
				Layout layoutDB = getLayoutByTipoLayout(idConciliacion, layoutDTO.getTipoLayout());
				if (layoutDB == null) {
					layoutDB = buildLayout(idConciliacion, layoutDTO, requestUser);
				}
				else {

					// Se verifica el tipo de layout y si ya tiene cabecera
					LayoutHeader layoutHeaderBD = getLayoutHeaderByIdLayout(idConciliacion, layoutDB.getId());
					if (layoutHeaderBD == null) {
						layoutHeaderBD = LayoutsBuilder.buildLayoutHeaderFromLayoutCabeceraDTO(layoutDTO.getCabecera(), layoutDB, requestUser);
						layoutDB.setLayoutHeader(layoutHeaderBD);
					}
					else {
						// Se actualiza (No se requiere)
						/*LayoutHeader layoutHeader = buildLayoutHeader(idConciliacion, layoutDTO.getCabecera());
						layoutHeader.setLayout(layoutDB);
						layoutHeader.setId(layoutHeaderBD.getId());
						layoutHeaderBD = layoutHeader;
						layoutHeaderBD.setLastModifiedBy(requestUser);
						layoutHeaderBD.setLastModifiedDate(new Date());
						layoutDB.setLayoutHeader(layoutHeaderBD);*/
					}

					// Se actualizan las lineas
					mergeLayoutLineas(layoutDTO.getLineas(), layoutDB, idConciliacion, requestUser);
				}
				layouts.add(layoutDB);
			}
		}
		return layouts;
	}


	/**
	 * Se encarga de actualizar o insertar las lineas recibidas vs las existentes en la bd
	 * @param lineas
	 * @param layout
	 * @param requestUser 
	 */
	private void mergeLayoutLineas(List<LayoutLineaDTO> lineasDTO, Layout layout, Long idConciliacion, String requestUser) throws ConciliacionException {

		if (lineasDTO != null && lineasDTO.size() > 0) {

			List<LayoutLinea> lineas = LayoutsBuilder.buildLayoutLineaFromLayoutLineaDTO(lineasDTO, layout, requestUser);

			// Existentes
			if (layout.getLayoutLineas() != null && layout.getLayoutLineas().size() > 0) {
				for (LayoutLinea linea : lineas) {
					if (linea.getId() != null && linea.getId() > 0) {
						boolean found = false;
						if (layout.getLayoutLineas() != null && layout.getLayoutLineas().size() > 0) {
							for (LayoutLinea lineaBD : layout.getLayoutLineas()) {
								if (linea.getId() == lineaBD.getId()) {
									LayoutsBuilder.mergeLinea(lineaBD, linea, requestUser);
									found = true;
									break;
								}
							}
							if (!found) {
								throw new ConciliacionException("No existe la linea layout con id " + linea.getId(), CodigoError.NMP_PMIMONTE_0011);
							}
						}
					}
				}
			}

			// Nuevas
			for (LayoutLinea linea : lineas) {
				// Valida si existe el layout
				if (linea.getId() == null || linea.getId() == 0) {
					linea.setId(null);
					linea.setNuevo(false);
					linea.setCreatedBy(requestUser);
					linea.setCreatedDate(new Date());
					layout.addLayoutLinea(linea);
				}
			}
		}
	}


	/**
	 * Se encarga de construir un layout en base a los parametros del DTO recibidos
	 * @param layoutDTO
	 * @param requestUser 
	 * @return
	 */
	private Layout buildLayout(Long idConciliacion, LayoutDTO layoutDTO, String requestUser) throws ConciliacionException {
		Layout layout = LayoutsBuilder.buildLayoutFromLayoutDTO(layoutDTO, requestUser);
		layout.setIdConciliacion(idConciliacion);
		layout.setTipo(layoutDTO.getTipoLayout());
		return layout;
	}


	/**
	 * Genera los layouts en base a la conciliacion
	 * @param idConciliacion
	 */
	private List<LayoutDTO> buildLayoutsDTO(Long idConciliacion) {

		List<LayoutDTO> layoutsDTO = new ArrayList<LayoutDTO>();
		
		// Se construyen todos los layouts en base a los movimientos de pagos, devoluciones, comisiones
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

		// COMISIONES OPEN PAY ??
		layoutDTO = buildLayoutDTO(idConciliacion, TipoLayoutEnum.COMISIONES_MOV);
		if (layoutDTO != null) {
			layoutsDTO.add(layoutDTO);
		}

		// COMISIONES GENERALES
		layoutDTO = buildLayoutDTO(idConciliacion, TipoLayoutEnum.COMISIONES_GENERALES);
		if (layoutDTO != null) {
			layoutsDTO.add(layoutDTO);
		}

		return layoutsDTO;
	}


	/**
	 * Genera los layouts para el movimiento solo si existen movimientos
	 * @param idConciliacion
	 * @param movimientoPagos
	 * @param layoutLineaCatalog
	 * @return
	 */
	private LayoutDTO buildLayoutDTO(Long idConciliacion, TipoLayoutEnum tipoLayout) throws ConciliacionException {

		LayoutDTO layoutDTO = null;

		// Se obtienen los movimientos de acuerdo al tipo de layout
		List<MovimientoConciliacion> movimientos = obtenerMovimientosConciliacion(idConciliacion, tipoLayout);
		if (movimientos != null && movimientos.size() > 0) {
	
			// Se construyen las lineas
			List<LayoutLineaDTO> lineasDTO = buildLineasDTO(movimientos, tipoLayout);

			// Se genera la cabecera correspondiente
			LayoutCabeceraDTO cabeceraDTO = buildCabeceraDTO(idConciliacion, tipoLayout);

			// Se crea el layout correspondiente
			layoutDTO = new LayoutDTO();
			layoutDTO.setFolio(idConciliacion);
			layoutDTO.setTipoLayout(tipoLayout);
			layoutDTO.setLineas(lineasDTO);
			layoutDTO.setCabecera(cabeceraDTO);	
		}

		return layoutDTO;
	}


	/**
	 * Construye la cabecera para el tipo de layout especificado usando los valores por default configurados en la bd
	 * @param idConciliacion
	 * @param tipoLayout
	 * @return
	 */
	private LayoutCabeceraDTO buildCabeceraDTO(Long idConciliacion, TipoLayoutEnum tipoLayout) {
		
		// Obtiene la cabecera del catalogo correspondiente al tipo layout
		LayoutHeaderCatalog layoutHeaderCatalog = getCabeceraCatalog(tipoLayout);

		// Se construye la cabecera
		LayoutCabeceraDTO cabecera = LayoutsBuilder.buildLayoutCabeceraDTOFromLayoutHeaderCatalog(layoutHeaderCatalog);
		return cabecera;
	}


	/**
	 * Se construyen las lineas de acuerdo al tipo de layout
	 * @param movimientos
	 * @param tipoLayout
	 * @return
	 */
	private List<LayoutLineaDTO> buildLineasDTO(List<MovimientoConciliacion> movimientos, TipoLayoutEnum tipoLayout) throws ConciliacionException {

		LayoutLineaCatalog lineaCatalog = getLayoutLineaCatalog(tipoLayout);

		// Genera las lineas
		List<LayoutLineaDTO> lineasDTO = new ArrayList<LayoutLineaDTO>();
		for (MovimientoConciliacion movimiento : movimientos) {
			BigDecimal monto = getMontoMovimiento(movimiento);
			LayoutLineaDTO lineaDTO = LayoutsBuilder.buildLayoutLineaDTOFromLayoutLineaCatalog(lineaCatalog, monto);
			lineasDTO.add(lineaDTO);
		}
		return lineasDTO;
	}


	/**
	 * Obtiene el monto del movimiento de la conciliacion de acuerdo al tipo de movimiento
	 * @param movimiento
	 * @return
	 */
	private BigDecimal getMontoMovimiento(MovimientoConciliacion movimiento) {
		BigDecimal monto = new BigDecimal(0);
		switch (movimiento.getTipoMovimiento()) {
			case COMISIONES:
				monto = ((MovimientoComision)movimiento).getMonto();
				break;
			case PAGOS:
				monto = ((MovimientoPago)movimiento).getMonto();
				break;
			case DEVOLUCIONES:
				monto = ((MovimientoDevolucion)movimiento).getMonto();
				break;
			default:
				break;
		}
		return monto;
	}


	/**
	 * Obtiene el listado de movimientos conciliacion de acuerdo al tipo de layout
	 * @param idConciliacion
	 * @param tipoLayout
	 * @return
	 */
	private List<MovimientoConciliacion> obtenerMovimientosConciliacion(Long idConciliacion, TipoLayoutEnum tipoLayout) {
		List<MovimientoConciliacion> movimientos = new ArrayList<MovimientoConciliacion>();
		switch (tipoLayout) {
			case PAGOS:
				movimientos.addAll(movimientoConciliacionRepository.findMovimientoPagoByConciliacionId(idConciliacion));
				break;
			case DEVOLUCIONES:
				movimientos.addAll(movimientoConciliacionRepository.findMovimientoDevolucionByConciliacionId(idConciliacion));
				break;
			case COMISIONES_MOV: // TipoMovimientoComisionEnum.OPENPAY
				//movimientos.addAll(movimientoConciliacionRepository.findMovimientoComisionByConciliacionId(idConciliacion)); // TODO: Verificar tipos
				break;
			case COMISIONES_GENERALES: // TipoMovimientoComisionEnum.IVA_COMISION
				movimientos.addAll(movimientoConciliacionRepository.findMovimientoComisionByConciliacionId(idConciliacion));
				break;
		}
		return movimientos;
	}


	/**
	 * Se encarga de obtener el layout por tipo de layout
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
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Ocurrio un error consultar el layout", CodigoError.NMP_PMIMONTE_0011);
		}
		return layout;
	}


	/**
	 * Obtiene la cabecera en base al id del layout
	 * @param idConciliacion
	 * @param idLayout
	 * @return
	 */
	private LayoutHeader getLayoutHeaderByIdLayout(Long idConciliacion, Long idLayout) {
		LayoutHeader layoutHeader = null;
		try {
			layoutHeader = this.layoutHeadersRepository.findByLayoutId(idLayout);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Ocurrio un error consultar el header para el layout " + idLayout, CodigoError.NMP_PMIMONTE_0011);
		}
		return layoutHeader;
	}


	/**
	 * Obtiene la linea del catalogo de acuerdo tipo de layout
	 * @param tipoLayout
	 * @return
	 * @throws ConciliacionException
	 */
	private LayoutLineaCatalog getLayoutLineaCatalog(TipoLayoutEnum tipoLayout) throws ConciliacionException {
		Optional<LayoutLineaCatalog> lineaCatalog = null;

		// Se obtienen los valores por default de la linea de acuerdo al tipo de layout
		try {
			lineaCatalog = layoutLineaCatalogRepository.findById(tipoLayout.getLineaCatalog().id());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Ocurrio un error consultar la linea del catalogo para el tipo layout " + tipoLayout, CodigoError.NMP_PMIMONTE_0011);
		}
		
		if (lineaCatalog == null || !lineaCatalog.isPresent()) {
			throw new ConciliacionException("No existe la linea del catalogo para el tipo de layout " + tipoLayout, CodigoError.NMP_PMIMONTE_0011);
		}

		return lineaCatalog.get();
	}


	/**
	 * Obtiene una linea por su id
	 * @param idLinea
	 * @return
	 * @throws ConciliacionException
	 */
	private LayoutLinea getLayoutLineaById(Long idLinea) throws ConciliacionException {
		Optional<LayoutLinea> linea = null;
		try {
			linea = this.layoutLineasRepository.findById(idLinea);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Ocurrio un error al consultar la linea para el layout " + idLinea, CodigoError.NMP_PMIMONTE_0011);
		}
		return linea != null && linea.isPresent() ? linea.get() : null;
	}


	/**
	 * Obtiene la cabecera del catalogo correspondiente al tipo de layout
	 * @param tipoLayout
	 * @return
	 */
	private LayoutHeaderCatalog getCabeceraCatalog(TipoLayoutEnum tipoLayout) {
		// Se obtiene la configuracion de la cabecera en la bd
		Optional<LayoutHeaderCatalog> layoutHeaderCatalog = null;
		try {
			layoutHeaderCatalog = layoutHeaderCatalogRepository.findById(tipoLayout.getHeaderCatalog().id());// buscarLayoutHeader
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("No existe la configuracion de la cabecera del layout de tipo " + tipoLayout, CodigoError.NMP_PMIMONTE_0011);
		}

		if (layoutHeaderCatalog == null || !layoutHeaderCatalog.isPresent()) {
			throw new ConciliacionException("No existe la configuracion de la cabecera del layout de tipo " + tipoLayout, CodigoError.NMP_PMIMONTE_0011);
		}

		return layoutHeaderCatalog.get();
	}


	/**
	 * Elimina los layouts que han sido generados con anterioridad (todos layouts con la bandera nuevo = false)
	 * @param idConciliacion
	 */
	private void limpiarLayoutsGenerados(Long idConciliacion) throws ConciliacionException {
		try {
			this.layoutsRepository.deleteByIdConciliacionAndNuevo(idConciliacion, false); // Solo layouts generados
		}
		catch (Exception ex) {
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
		return idConciliacion > 0L && tipoLayout == TipoLayoutEnum.PAGOS
				|| tipoLayout == TipoLayoutEnum.COMISIONES_MOV
				|| tipoLayout == TipoLayoutEnum.COMISIONES_GENERALES
				|| tipoLayout == TipoLayoutEnum.DEVOLUCIONES;
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

}