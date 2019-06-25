package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutLineaDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.FormatoReporteEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Layout;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeader;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeaderCatalog;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLinea;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLineaCatalog;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
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
	private MovimientoConciliacionRepository lovimientoConciliacionRepository;
	
	@Autowired
	private LayoutHeaderCatalogRepository layoutHeaderCatalogRepository;
	
	@Autowired
	LayoutLineaCatalogRepository layoutLineaCatalogRepository;
	
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
	    }	
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
	public void  eliminarUnLayout(Long idConciliacion, Long idLayout) {
		if(idConciliacion > 0L && idLayout > 0L) {
			Layout layout = layoutsRepository.getOne(idLayout);//siLayoutPerteneceAConciliacion
			List<Layout> layouts = layoutsRepository.findByTipo(layout.getTipo());//siLayoutEsElMismoTipo
			for(Layout layout2: layouts) {
				if(layout2.getIdConciliacion() == idConciliacion) {
				      layoutsRepository.eliminarUnLayoutLineas(layout2.getId());
				      layoutsRepository.eliminarUnLayoutHeader(layout2.getId());
				      layoutsRepository.deleteById(layout2.getId());
				}
			}
		}
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
		List<MovimientoPago> movimientoPagos = lovimientoConciliacionRepository.findMovimientoPagoByConciliacionId(idConciliacion.intValue());
		LayoutLineaCatalog layoutLineaCatalog = layoutLineaCatalogRepository.getOne(1L);//Obtener LayoutLinea
		for(MovimientoPago movimientoPago : movimientoPagos) {
			layoutLineaDTO1 = new LayoutLineaDTO();
			  layoutLineaDTO1.setCuenta(layoutLineaCatalog.getCuenta()); 
		      layoutLineaDTO1.setDepId(layoutLineaCatalog.getDepId());
			  layoutLineaDTO1.setId(0L); 
		      layoutLineaDTO1.setLinea(layoutLineaCatalog.getLinea());
			  layoutLineaDTO1.setMonto(movimientoPago.getMonto()); 
		      layoutLineaDTO1.setNegocio(layoutLineaCatalog.getNegocio());
			  layoutLineaDTO1.setProyectoNMP(layoutLineaCatalog.getProyectoNmp());
			  layoutLineaDTO1.setUnidadOperativa(layoutLineaCatalog.getUnidadOperativa());
			  layoutLineaDTOList.add(layoutLineaDTO1);
		}
		layoutDTO.setLineas(layoutLineaDTOList);
		obtenerLayoutC(layoutDTO);

		LayoutDTO layoutDTO1 = new LayoutDTO();
		LayoutLineaDTO layoutLineaDTO2 = null;
		List<LayoutLineaDTO> layoutLineaListDTO1 = new ArrayList<>();
		layoutDTO1.setFolio(idConciliacion);
		layoutDTO1.setTipoLayout(TipoLayoutEnum.DEVOLUCIONES);	
		List<MovimientoDevolucion> movimientoDevolucions = lovimientoConciliacionRepository.findMovimientoDevolucionByConciliacionId(idConciliacion.intValue());	
		LayoutLineaCatalog layoutLineaCatalog4 = layoutLineaCatalogRepository.getOne(4L);
		for(MovimientoDevolucion movimientoDevolucion : movimientoDevolucions) {
			layoutLineaDTO2 = new LayoutLineaDTO();
			  layoutLineaDTO2.setCuenta(layoutLineaCatalog4.getCuenta()); 
		      layoutLineaDTO2.setDepId(layoutLineaCatalog4.getDepId());
			  layoutLineaDTO2.setId(0L); 
		      layoutLineaDTO2.setLinea(layoutLineaCatalog4.getLinea());
			  layoutLineaDTO2.setMonto(movimientoDevolucion.getMonto()); 
		      layoutLineaDTO2.setNegocio(layoutLineaCatalog4.getNegocio());
			  layoutLineaDTO2.setProyectoNMP(layoutLineaCatalog4.getProyectoNmp());
			  layoutLineaDTO2.setUnidadOperativa(layoutLineaCatalog4.getUnidadOperativa());
			  layoutLineaListDTO1.add(layoutLineaDTO2);
		}
		layoutDTO1.setLineas(layoutLineaListDTO1);
		obtenerLayoutC(layoutDTO1);
		
		LayoutDTO layoutDTO2 = new LayoutDTO();
		LayoutLineaDTO layoutLineaDTO3 = null;
		List<LayoutLineaDTO> layoutLineaDTOList3 = new ArrayList<>();
		layoutDTO2.setFolio(idConciliacion);
		layoutDTO2.setTipoLayout(TipoLayoutEnum.COMISIONES_MOV);
		List<MovimientoComision> movimientoConciliacions = lovimientoConciliacionRepository.findMovimientoComisionByConciliacionId(idConciliacion.intValue());
		LayoutLineaCatalog layoutLineaCatalog2 = layoutLineaCatalogRepository.getOne(2L);
		for(MovimientoComision movimientoComision : movimientoConciliacions) {
			if(movimientoComision.getTipo().equals(TipoMovimientoEnum.OPENPAY.toString()) || movimientoComision.getTipo().equals(TipoMovimientoEnum.OPENPAY.toString())) {
			layoutLineaDTO3 = new LayoutLineaDTO();
			  layoutLineaDTO3.setCuenta(layoutLineaCatalog2.getCuenta()); 
		      layoutLineaDTO3.setDepId(layoutLineaCatalog2.getDepId());
			  layoutLineaDTO3.setId(0L); 
		      layoutLineaDTO3.setLinea(layoutLineaCatalog2.getLinea());
			  layoutLineaDTO3.setMonto(movimientoComision.getMonto()); 
		      layoutLineaDTO3.setNegocio(layoutLineaCatalog2.getNegocio());
			  layoutLineaDTO3.setProyectoNMP(layoutLineaCatalog2.getProyectoNmp());
			  layoutLineaDTO3.setUnidadOperativa(layoutLineaCatalog2.getUnidadOperativa());
			  layoutLineaDTOList3.add(layoutLineaDTO3);
			}
		}
		layoutDTO2.setLineas(layoutLineaDTOList3);
		obtenerLayoutC(layoutDTO2);
		
		LayoutDTO layoutDTO4 = new LayoutDTO();
		LayoutLineaDTO layoutLineaDTO4 = null;
		List<LayoutLineaDTO> layoutLineaDTOList4 = new ArrayList<>();
		layoutDTO4.setFolio(idConciliacion);
		layoutDTO4.setTipoLayout(TipoLayoutEnum.COMISIONES_GENERALES);
		List<MovimientoComision> movimientoConciliacions1 = lovimientoConciliacionRepository.findMovimientoComisionByConciliacionId(idConciliacion.intValue());
		LayoutLineaCatalog layoutLineaCatalog3 = layoutLineaCatalogRepository.getOne(3L);
		for(MovimientoComision movimientoComision : movimientoConciliacions1) {
			if(movimientoComision.getTipo().equals(TipoMovimientoEnum.COMISION.toString()) || movimientoComision.getTipo().equals(TipoMovimientoEnum.IVA_COMISION.toString())) {
			layoutLineaDTO4 = new LayoutLineaDTO();
			  layoutLineaDTO4.setCuenta(layoutLineaCatalog3.getCuenta()); 
		      layoutLineaDTO4.setDepId(layoutLineaCatalog3.getDepId());
			  layoutLineaDTO4.setId(0L); 
		      layoutLineaDTO4.setLinea(layoutLineaCatalog3.getLinea());
			  layoutLineaDTO4.setMonto(movimientoComision.getMonto()); 
		      layoutLineaDTO4.setNegocio(layoutLineaCatalog3.getNegocio());
			  layoutLineaDTO4.setProyectoNMP(layoutLineaCatalog3.getProyectoNmp());
			  layoutLineaDTO4.setUnidadOperativa(layoutLineaCatalog3.getUnidadOperativa());
			  layoutLineaDTOList4.add(layoutLineaDTO4);
			}
		}
		layoutDTO4.setLineas(layoutLineaDTOList4);
		obtenerLayoutC(layoutDTO4);
		respuesta = "Conciliación enviado con éxito.";
	 }	
	
		return respuesta;
	}//End enviarConciliacion
	public void obtenerLayout(LayoutDTO layoutDTO) {
		Layout layout = new Layout();
		if(layoutDTO != null && validar(layoutDTO)){
				LayoutHeaderCatalog layoutHeaderCatalog = null;
				LayoutHeader layoutHeader = null;
				layout.setIdConciliacion(layoutDTO.getFolio());
				layout.setTipo(layoutDTO.getTipoLayout().toString());
				layout.setLayoutLineas(LayoutsBuilder.buildLayoutLineaFromLayoutLineaDTO(layoutDTO.getLineas()));	
				layout = layoutsRepository.saveAndFlush(layout);
				if(layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.PAGOS.toString())){
					layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(1L);//buscarLayoutHeader
				}
				if(layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.COMISIONES_MOV.toString())){
					layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(2L);
				}
				if(layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.COMISIONES_GENERALES.toString())){
					layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(3L);
				}
				if(layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.DEVOLUCIONES.toString())){
					layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(4L);
				}
				layoutHeader = new LayoutHeader();
				layoutHeader.setId(layoutHeaderCatalog.getId());
				layoutHeader.setCabecera(layoutHeaderCatalog.getCabecera());
				layoutHeader.setCodigoOrigen(layoutHeaderCatalog.getCodigoOrigen());
				layoutHeader.setDescripcion(layoutHeaderCatalog.getDescripcion());
				layoutHeader.setFecha(layoutHeaderCatalog.getFecha());
				layoutHeader.setUnidadNegocio(layoutHeaderCatalog.getUnidadNegocio());
				
				layoutHeader.setLayout(layout);
				layoutHeadersRepository.saveAndFlush(layoutHeader);
			for(LayoutLinea layoutLinea : layout.getLayoutLineas())
			{
				layoutLinea.setLayout(layout);
			} 
			
			layoutLineasRepository.saveAll(layout.getLayoutLineas());
		}
	}
	
	public void obtenerLayoutC(LayoutDTO layoutDTO) {
		Layout layout = new Layout();
		if(layoutDTO != null && validar(layoutDTO)){
				LayoutHeaderCatalog layoutHeaderCatalog = null;
				LayoutHeader layoutHeader = null;
				layout.setIdConciliacion(layoutDTO.getFolio());
				layout.setTipo(layoutDTO.getTipoLayout().toString());
				layout.setLayoutLineas(LayoutsBuilder.buildLayoutLineaFromLayoutLineaDTOC(layoutDTO.getLineas()));	
				layout = layoutsRepository.saveAndFlush(layout);
				if(layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.PAGOS.toString())){
					layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(1L);//buscarLayoutHeader
				}
				if(layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.COMISIONES_MOV.toString())){
					layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(2L);
				}
				if(layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.COMISIONES_GENERALES.toString())){
					layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(3L);
				}
				if(layoutDTO.getTipoLayout().toString().equals(TipoLayoutEnum.DEVOLUCIONES.toString())){
					layoutHeaderCatalog = layoutHeaderCatalogRepository.getOne(4L);
				}
				layoutHeader = new LayoutHeader();
				layoutHeader.setId(layoutHeaderCatalog.getId());
				layoutHeader.setCabecera(layoutHeaderCatalog.getCabecera());
				layoutHeader.setCodigoOrigen(layoutHeaderCatalog.getCodigoOrigen());
				layoutHeader.setDescripcion(layoutHeaderCatalog.getDescripcion());
				layoutHeader.setFecha(layoutHeaderCatalog.getFecha());
				layoutHeader.setUnidadNegocio(layoutHeaderCatalog.getUnidadNegocio());
				
				layoutHeader.setLayout(layout);
				layoutHeadersRepository.saveAndFlush(layoutHeader);
			for(LayoutLinea layoutLinea : layout.getLayoutLineas())
			{
				layoutLinea.setLayout(layout);
			} 
			
			layoutLineasRepository.saveAll(layout.getLayoutLineas());
		}
	}
	
	public boolean validar(Long idConciliacion, String tipoLayout) {
		return idConciliacion > 0L && tipoLayout.equals(TipoLayoutEnum.PAGOS.toString()) || tipoLayout.equals(TipoLayoutEnum.COMISIONES_MOV.toString())
				|| tipoLayout.equals(TipoLayoutEnum.COMISIONES_GENERALES.toString()) || tipoLayout.equals(TipoLayoutEnum.DEVOLUCIONES.toString());
	}
	
	public boolean validar(LayoutDTO layoutDTO) {
		return layoutDTO.getFolio() > 0L && !layoutDTO.getTipoLayout().toString().equals("");
	}

}