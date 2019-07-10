package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.LayoutsBuilder;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutHeaderCatalogRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutHeadersRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutLineaCatalogRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutLineasRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.LayoutsRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosPagoRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutLineaDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.FormatoReporteEnum;
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
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoEnum;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:47 PM
 */
@Service
public class LayoutsService {

	private static final Logger LOG = LoggerFactory.getLogger(LayoutsService.class);
	
	@Autowired
	private LayoutsRepository layoutsRepository;
	
	@Autowired
	private LayoutLineasRepository layoutLineasRepository;
	
	@Autowired
	private LayoutHeadersRepository layoutHeadersRepository;
	
	@Autowired
	private MovimientoConciliacionRepository movimientoConciliacionRepository;
	
	@Autowired
	private LayoutHeaderCatalogRepository layoutHeaderCatalogRepository;
	
	@Autowired
	private LayoutLineaCatalogRepository layoutLineaCatalogRepository;
	
	@Autowired
	private MovimientosPagoRepository movimientosPagoRepository;
		
	public LayoutsService(){

	}
	
	/**
	 * Consultar Layout
	 * @param idConciliacion
	 * @param tipoLayout
	 */
	public LayoutDTO consultarUnLayout(Long idConciliacion, String tipoLayout){
		 List<Object[]> layouts = null;
		 Layout layoutNuevo = new Layout();
		 List<LayoutLinea> layoutLineas = new ArrayList<>();
		if(validar(idConciliacion, tipoLayout)){
			      layouts = layoutsRepository.findByIdConciliacionAndTipo(idConciliacion, tipoLayout);
		    	
			int x=1;
			for(Object[] arrayObject: layouts){
				if(x==1){
					Layout layout = (Layout)arrayObject[0];
					LayoutHeader layoutHeader = (LayoutHeader)arrayObject[1];
					layoutNuevo.setIdConciliacion(layout.getIdConciliacion());
					layoutNuevo.setTipo(layout.getTipo());
					layoutNuevo.setLayoutHeader(layoutHeader);
				}
				LayoutLinea layoutLinea = (LayoutLinea)arrayObject[2];
				layoutLineas.add(layoutLinea);
				
				x=0;
			}
			layoutNuevo.setLayoutLineas(layoutLineas);
		}	
		return LayoutsBuilder.buildLayoutDTOFromLayout(layoutNuevo);			
	}
	
	/**
	 * Consultar Layouts Conciliación
	 * @param idConciliacion
	 */
	public List<LayoutDTO> consultarLayouts(Long idConciliacion){
		List<LayoutDTO> layoutDTOs = new ArrayList<>();
		if(idConciliacion>0L) {
			LayoutDTO layoutDTO = consultarUnLayout(idConciliacion,TipoLayoutEnum.PAGOS.toString());
			if(layoutDTO != null) {layoutDTOs.add(layoutDTO);}
			layoutDTO = consultarUnLayout(idConciliacion,TipoLayoutEnum.COMISIONES_MOV.toString());
			if(layoutDTO != null) {layoutDTOs.add(layoutDTO);}
			layoutDTO = consultarUnLayout(idConciliacion,TipoLayoutEnum.COMISIONES_GENERALES.toString());
			if(layoutDTO != null) {layoutDTOs.add(layoutDTO);}
			layoutDTO = consultarUnLayout(idConciliacion,TipoLayoutEnum.DEVOLUCIONES.toString());
			if(layoutDTO != null) {layoutDTOs.add(layoutDTO);}
		}
		
		return layoutDTOs;
	}
	
	/**
	 * Agregar Layout
	 * @param layout
	 */
	@Transactional
	public void saveLayout(LayoutDTO layoutDTO) {
		obtenerLayout(layoutDTO);
	}
	
	/**
	 * Eliminar Layout
	 * @param idConciliacion
	 * @param idLayout
	 */
	@Transactional
	public String  eliminarUnLayout(Long idConciliacion, Long idLayout) {
		String respuesta = "No hay registro del layout.";
		if(idConciliacion > 0L && idLayout > 0L) {
			if(layoutsRepository.findById(idLayout).isPresent()) {
				Layout layout = layoutsRepository.getOne(idLayout);//siLayoutPerteneceAConciliacion
				List<Layout> layouts = layoutsRepository.findByTipo(layout.getTipo());//siLayoutEsElMismoTipo
				   for(Layout layout2: layouts) {
						if(layout2.getIdConciliacion() == idConciliacion) {
						      layoutsRepository.eliminarUnLayoutLineas(layout2.getId());
						      layoutsRepository.eliminarUnLayoutHeader(layout2.getId());
						      layoutsRepository.deleteById(layout2.getId());
						      respuesta = "Layout eliminado con éxito.";
						}
					}
			}
		}
		return respuesta;
	}
	@Transactional
	public String enviarConciliacion(Long idConciliacion) {
		String respuesta = "Conciliación ya ha sido enviado.";
		
    	if(idConciliacion > 0 && layoutsRepository.findByIdConciliacion(idConciliacion).size() == 0){//buscarLayouts
					
		    List<LayoutLineaDTO> layoutLineaDTOList = null;
			LayoutDTO layoutDTO = null;
			LayoutLineaDTO layoutLineaDTO1 = null;
			layoutLineaDTOList = new ArrayList<>();
			layoutDTO = new LayoutDTO();
			layoutDTO.setFolio(idConciliacion);
			layoutDTO.setTipoLayout(TipoLayoutEnum.PAGOS);
			List<MovimientoPago> movimientoPagos = movimientoConciliacionRepository.findMovimientoPagoByConciliacionId(idConciliacion.intValue());
			LayoutLineaCatalog layoutLineaCatalog = layoutLineaCatalogRepository.getOne(1L);//Obtener LayoutLinea
			for(MovimientoPago movimientoPago : movimientoPagos) {		
				layoutLineaDTO1 = LayoutsBuilder.buildLayoutLineaDTOFromLayoutLineaCatalog(layoutLineaCatalog, movimientoPago.getMonto());
				layoutLineaDTOList.add(layoutLineaDTO1);
			}
			
			layoutDTO.setLineas(layoutLineaDTOList);
			obtenerLayout(layoutDTO);
	
			LayoutDTO layoutDTO1 = new LayoutDTO();
			LayoutLineaDTO layoutLineaDTO2 = null;
			List<LayoutLineaDTO> layoutLineaListDTO1 = new ArrayList<>();
			layoutDTO1.setFolio(idConciliacion);
			layoutDTO1.setTipoLayout(TipoLayoutEnum.DEVOLUCIONES);	
			List<MovimientoDevolucion> movimientoDevolucions = movimientoConciliacionRepository.findMovimientoDevolucionByConciliacionId(idConciliacion.intValue());	
			LayoutLineaCatalog layoutLineaCatalog4 = layoutLineaCatalogRepository.getOne(4L);
			for(MovimientoDevolucion movimientoDevolucion : movimientoDevolucions) {
				layoutLineaDTO2 = LayoutsBuilder.buildLayoutLineaDTOFromLayoutLineaCatalog(layoutLineaCatalog4, movimientoDevolucion.getMonto());
				layoutLineaListDTO1.add(layoutLineaDTO2);
			}
			layoutDTO1.setLineas(layoutLineaListDTO1);
			obtenerLayout(layoutDTO1);
			
			LayoutDTO layoutDTO2 = new LayoutDTO();
			LayoutLineaDTO layoutLineaDTO3 = null;
			List<LayoutLineaDTO> layoutLineaDTOList3 = new ArrayList<>();
			layoutDTO2.setFolio(idConciliacion);
			layoutDTO2.setTipoLayout(TipoLayoutEnum.COMISIONES_MOV);
			List<MovimientoComision> movimientoConciliacions = movimientoConciliacionRepository.findMovimientoComisionByConciliacionId(idConciliacion.intValue());
			LayoutLineaCatalog layoutLineaCatalog2 = layoutLineaCatalogRepository.getOne(2L);
			for(MovimientoComision movimientoComision : movimientoConciliacions) {
				if(movimientoComision.getTipo().equals(TipoMovimientoEnum.OPENPAY)) {
					  layoutLineaDTO3 = LayoutsBuilder.buildLayoutLineaDTOFromLayoutLineaCatalog(layoutLineaCatalog2, movimientoComision.getMonto());
					  layoutLineaDTOList3.add(layoutLineaDTO3);
				}
			}
			layoutDTO2.setLineas(layoutLineaDTOList3);
			obtenerLayout(layoutDTO2);
			
			LayoutDTO layoutDTO4 = new LayoutDTO();
			LayoutLineaDTO layoutLineaDTO4 = null;
			List<LayoutLineaDTO> layoutLineaDTOList4 = new ArrayList<>();
			layoutDTO4.setFolio(idConciliacion);
			layoutDTO4.setTipoLayout(TipoLayoutEnum.COMISIONES_GENERALES);
			List<MovimientoComision> movimientoConciliacions1 = movimientoConciliacionRepository.findMovimientoComisionByConciliacionId(idConciliacion.intValue());
			LayoutLineaCatalog layoutLineaCatalog3 = layoutLineaCatalogRepository.getOne(3L);
			for(MovimientoComision movimientoComision : movimientoConciliacions1) {
				if(movimientoComision.getTipo().equals(TipoMovimientoEnum.COMISION) || movimientoComision.getTipo().equals(TipoMovimientoEnum.IVA_COMISION)) {
					layoutLineaDTO4 = LayoutsBuilder.buildLayoutLineaDTOFromLayoutLineaCatalog(layoutLineaCatalog3, movimientoComision.getMonto());
					layoutLineaDTOList4.add(layoutLineaDTO4);
				}
			}
			layoutDTO4.setLineas(layoutLineaDTOList4);
			obtenerLayout(layoutDTO4);
			respuesta = "Conciliación enviado con éxito.";
	   }	
	
		return respuesta;
	}//End enviarConciliacion
	public void obtenerLayout(LayoutDTO layoutDTO) {
		Layout layout = new Layout();
		LocalDate localDate = LocalDate.now();
		if(validar(layoutDTO)){
				LayoutHeaderCatalog layoutHeaderCatalog = null;
				LayoutHeader layoutHeader = null;
				layout.setIdConciliacion(layoutDTO.getFolio());
				layout.setTipo(layoutDTO.getTipoLayout().toString());
				if(LayoutsBuilder.validaLineas(layoutDTO.getLineas())){
					layout.setLayoutLineas(LayoutsBuilder.buildLayoutLineaFromLayoutLineaDTO(layoutDTO.getLineas()));	
					layout = layoutsRepository.saveAndFlush(layout);
					if(layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.PAGOS.toString())){
						layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(1L);//buscarLayoutHeader
						if(localDate.getDayOfWeek().getValue() == 5) {
							layoutHeaderCatalog.setFecha(localDate.plusDays(3));
						}
						else {
							layoutHeaderCatalog.setFecha(localDate);
						}
					}
					if(layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.COMISIONES_MOV.toString())){
						layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(2L);layoutHeaderCatalog.setFecha(localDate);
					}
					if(layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.COMISIONES_GENERALES.toString())){
						layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(3L);layoutHeaderCatalog.setFecha(localDate);
					}
					if(layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.DEVOLUCIONES.toString())){
						layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(4L);layoutHeaderCatalog.setFecha(localDate);
					}
					layoutHeader = new LayoutHeader();
					layoutHeader.setId(layoutHeaderCatalog.getId());
					layoutHeader.setCabecera(layoutHeaderCatalog.getCabecera());
					layoutHeader.setCodigoOrigen(layoutHeaderCatalog.getCodigoOrigen());
					layoutHeader.setDescripcion(layoutHeaderCatalog.getDescripcion() +" "+ LocalDate.now());
					layoutHeader.setFecha(layoutHeaderCatalog.getFecha());
					layoutHeader.setUnidadNegocio(layoutHeaderCatalog.getUnidadNegocio());
					
					layoutHeader.setLayout(layout);
					layoutHeadersRepository.saveAndFlush(layoutHeader);
					for(LayoutLinea layoutLinea : layout.getLayoutLineas())
					{   
						layoutLinea.setLayout(layout);
					} 
				
				   layoutLineasRepository.saveAll(layout.getLayoutLineas());
		       }//End if
		}//End if
	}
	
	public boolean validar(Long idConciliacion, String tipoLayout) {
		return idConciliacion > 0L && tipoLayout.equals(TipoLayoutEnum.PAGOS.toString()) || tipoLayout.equals(TipoLayoutEnum.COMISIONES_MOV.toString())
				|| tipoLayout.equals(TipoLayoutEnum.COMISIONES_GENERALES.toString()) || tipoLayout.equals(TipoLayoutEnum.DEVOLUCIONES.toString());
	}
	
	public boolean validar(LayoutDTO layoutDTO) {
		return layoutDTO.getFolio() > 0L && !layoutDTO.getTipoLayout().toString().equals("") && layoutDTO.getLineas().size() > 0;
	}

}