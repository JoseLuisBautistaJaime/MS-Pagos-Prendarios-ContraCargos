/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.LayoutsBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutHeaderCatalogRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutHeadersRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutLineaCatalogRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutLineasRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutsRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutEditionValidationDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutLineaDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.HeaderCatalogEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Layout;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeader;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeaderCatalog;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLinea;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLineaCatalog;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LineaCatalogEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoPago;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoEnum;

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
	 * Repository de Conciliacion
	 */
	@Autowired
	private ConciliacionRepository conciliacionRepository;




	/**
	 * Consulta un Layout de una Conciliación
	 * 
	 * @param idConciliacion
	 * @param tipoLayout
	 * @return
	 */
	public LayoutDTO consultarUnLayout(Long idConciliacion, String tipoLayout) {
		List<Object[]> layouts = null;
		Layout layoutNuevo = new Layout();
		List<LayoutLinea> layoutLineas = new ArrayList<>();
		if (validar(idConciliacion, tipoLayout)) {
			layouts = layoutsRepository.findByIdConciliacionAndTipo(idConciliacion, tipoLayout);

			int x = 1;
			for (Object[] arrayObject : layouts) {
				if (x == 1) {
					Layout layout = (Layout) arrayObject[0];
					LayoutHeader layoutHeader = (LayoutHeader) arrayObject[1];
					layoutNuevo.setIdConciliacion(layout.getIdConciliacion());
					layoutNuevo.setTipo(layout.getTipo());
					layoutNuevo.setLayoutHeader(layoutHeader);
				}
				LayoutLinea layoutLinea = (LayoutLinea) arrayObject[2];
				layoutLineas.add(layoutLinea);

				x = 0;
			}
			layoutNuevo.setLayoutLineas(layoutLineas);
		}
		return LayoutsBuilder.buildLayoutDTOFromLayout(layoutNuevo);
	}

	/**
	 * Consulta los Layouts de una Conciliación
	 * 
	 * @param idConciliacion
	 * @return
	 */
	public List<LayoutDTO> consultarLayouts(Long idConciliacion) {
		List<LayoutDTO> layoutDTOs = new ArrayList<>();
		if (idConciliacion > 0L) {
			LayoutDTO layoutDTO = consultarUnLayout(idConciliacion, TipoLayoutEnum.PAGOS.toString());
			if (layoutDTO != null) {
				layoutDTOs.add(layoutDTO);
			}
			layoutDTO = consultarUnLayout(idConciliacion, TipoLayoutEnum.COMISIONES_MOV.toString());
			if (layoutDTO != null) {
				layoutDTOs.add(layoutDTO);
			}
			layoutDTO = consultarUnLayout(idConciliacion, TipoLayoutEnum.COMISIONES_GENERALES.toString());
			if (layoutDTO != null) {
				layoutDTOs.add(layoutDTO);
			}
			layoutDTO = consultarUnLayout(idConciliacion, TipoLayoutEnum.DEVOLUCIONES.toString());
			if (layoutDTO != null) {
				layoutDTOs.add(layoutDTO);
			}
		}

		return layoutDTOs;
	}

	/**
	 * Agrega un Layout
	 * 
	 * @param layoutDTO
	 */
	@Transactional
	public void saveLayout(LayoutDTO layoutDTO, final String requestUser) {
		if (conciliacionRepository.findById(layoutDTO.getFolio()).isPresent()) {
			obtenerLayout(layoutDTO, requestUser);
		} else {
			throw new ConciliacionException(ConciliacionConstants.CONCILIACION_ID_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0008);
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
	public boolean eliminarUnLayout(Long idConciliacion, Long idLayout) {
		boolean respuesta = true;
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
				throw new ConciliacionException(ConciliacionConstants.LAYOUT_ID_DOESNT_EXIST,
						CodigoError.NMP_PMIMONTE_BUSINESS_085);
		}
		return respuesta;
	}

	/**
	 * Genera layouts a partir de una conciliación
	 * 
	 * @param idConciliacion
	 * @return
	 */
	@Transactional
	public void enviarConciliacion(Long idConciliacion) {

		// Obtiene, valida la conciliacion (en proceso)
		Conciliacion conciliacion = this.conciliacionHelper.getConciliacionByFolio(idConciliacion, null);


		// Se obtienen todos los movimientos de pagos, devoluciones, comisiones
		// PAGOS /////
		List<MovimientoPago> movimientoPagos = movimientoConciliacionRepository.findMovimientoPagoByConciliacionId(idConciliacion);
		LayoutLineaCatalog layoutLineaCatalog = layoutLineaCatalogRepository.getOne(LineaCatalogEnum.PAGOS.id());// Obtener // LayoutLinea

		List<LayoutLineaDTO> layoutLineaDTOList = new ArrayList<>();
		for (MovimientoPago movimientoPago : movimientoPagos) {
			LayoutLineaDTO layoutLineaDTO1 = LayoutsBuilder.buildLayoutLineaDTOFromLayoutLineaCatalog(layoutLineaCatalog,
					movimientoPago.getMonto());
			layoutLineaDTOList.add(layoutLineaDTO1);
		}

		LayoutDTO layoutDTO = new LayoutDTO();
		layoutDTO.setFolio(idConciliacion);
		layoutDTO.setTipoLayout(TipoLayoutEnum.PAGOS);
		layoutDTO.setLineas(layoutLineaDTOList);

		obtenerLayout(layoutDTO, null);

		LayoutDTO layoutDTO1 = new LayoutDTO();
		LayoutLineaDTO layoutLineaDTO2 = null;
		List<LayoutLineaDTO> layoutLineaListDTO1 = new ArrayList<>();
		layoutDTO1.setFolio(idConciliacion);
		layoutDTO1.setTipoLayout(TipoLayoutEnum.DEVOLUCIONES);
		List<MovimientoDevolucion> movimientoDevolucions = movimientoConciliacionRepository
				.findMovimientoDevolucionByConciliacionId(idConciliacion);
		LayoutLineaCatalog layoutLineaCatalog4 = layoutLineaCatalogRepository
				.getOne(LineaCatalogEnum.DEVOLUCIONES.id());
		for (MovimientoDevolucion movimientoDevolucion : movimientoDevolucions) {
			layoutLineaDTO2 = LayoutsBuilder.buildLayoutLineaDTOFromLayoutLineaCatalog(layoutLineaCatalog4,
					movimientoDevolucion.getMonto());
			layoutLineaListDTO1.add(layoutLineaDTO2);
		}
		layoutDTO1.setLineas(layoutLineaListDTO1);
		obtenerLayout(layoutDTO1, null);

		LayoutDTO layoutDTO2 = new LayoutDTO();
		LayoutLineaDTO layoutLineaDTO3 = null;
		List<LayoutLineaDTO> layoutLineaDTOList3 = new ArrayList<>();
		layoutDTO2.setFolio(idConciliacion);
		layoutDTO2.setTipoLayout(TipoLayoutEnum.COMISIONES_MOV);
		List<MovimientoComision> movimientoConciliacions = movimientoConciliacionRepository
				.findMovimientoComisionByConciliacionId(idConciliacion);
		LayoutLineaCatalog layoutLineaCatalog2 = layoutLineaCatalogRepository
				.getOne(LineaCatalogEnum.COMISIONES_MOV.id());
		for (MovimientoComision movimientoComision : movimientoConciliacions) {
			if (movimientoComision.getTipo().equals(TipoMovimientoEnum.OPENPAY)) {
				layoutLineaDTO3 = LayoutsBuilder.buildLayoutLineaDTOFromLayoutLineaCatalog(layoutLineaCatalog2,
						movimientoComision.getMonto());
				layoutLineaDTOList3.add(layoutLineaDTO3);
			}
		}
		layoutDTO2.setLineas(layoutLineaDTOList3);
		obtenerLayout(layoutDTO2, null);

		LayoutDTO layoutDTO4 = new LayoutDTO();
		LayoutLineaDTO layoutLineaDTO4 = null;
		List<LayoutLineaDTO> layoutLineaDTOList4 = new ArrayList<>();
		layoutDTO4.setFolio(idConciliacion);
		layoutDTO4.setTipoLayout(TipoLayoutEnum.COMISIONES_GENERALES);
		List<MovimientoComision> movimientoConciliacions1 = movimientoConciliacionRepository
				.findMovimientoComisionByConciliacionId(idConciliacion);
		LayoutLineaCatalog layoutLineaCatalog3 = layoutLineaCatalogRepository
				.getOne(LineaCatalogEnum.COMISIONES_GENERALES.id());
		for (MovimientoComision movimientoComision : movimientoConciliacions1) {
			if (movimientoComision.getTipo().equals(TipoMovimientoEnum.COMISION)
					|| movimientoComision.getTipo().equals(TipoMovimientoEnum.IVA_COMISION)) {
				layoutLineaDTO4 = LayoutsBuilder.buildLayoutLineaDTOFromLayoutLineaCatalog(layoutLineaCatalog3,
						movimientoComision.getMonto());
				layoutLineaDTOList4.add(layoutLineaDTO4);
			}
		}
		layoutDTO4.setLineas(layoutLineaDTOList4);
		obtenerLayout(layoutDTO4, null);
	

	}// End enviarConciliacion

	/**
	 * Guarda y edita un layout como PAGOS, COMISIONES_MOV, COMISIONES_GENERALES y
	 * DEVOLUCIONES
	 * 
	 * @param layoutDTO
	 */
	public void obtenerLayout(LayoutDTO layoutDTO, final String requestUser) {
		Layout layout = new Layout();
		List<LayoutEditionValidationDTO> layoutEditionValidationDTOList = null;
		List<Long> ids = null;
		Map<Long, LayoutEditionValidationDTO> map = null;
		LocalDate localDate = LocalDate.now();
		if (validar(layoutDTO)) {
			LayoutHeaderCatalog layoutHeaderCatalog = null;
			LayoutHeader layoutHeader = null;
			layout.setIdConciliacion(layoutDTO.getFolio());
			layout.setTipo(layoutDTO.getTipoLayout().toString());
			if (LayoutsBuilder.validaLineas(layoutDTO.getLineas())) {
				ids = new ArrayList<>();
				layout.setLayoutLineas(LayoutsBuilder.buildLayoutLineaFromLayoutLineaDTO(layoutDTO.getLineas()));
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
	public Map<Long, LayoutEditionValidationDTO> getMapFromDTOList(List<LayoutEditionValidationDTO> list) {
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
	 * Valida los campos idConciliacion y tipoLayout
	 * 
	 * @param idConciliacion
	 * @param tipoLayout
	 * @return
	 */
	public boolean validar(Long idConciliacion, String tipoLayout) {
		return idConciliacion > 0L && tipoLayout.equals(TipoLayoutEnum.PAGOS.toString())
				|| tipoLayout.equals(TipoLayoutEnum.COMISIONES_MOV.toString())
				|| tipoLayout.equals(TipoLayoutEnum.COMISIONES_GENERALES.toString())
				|| tipoLayout.equals(TipoLayoutEnum.DEVOLUCIONES.toString());
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