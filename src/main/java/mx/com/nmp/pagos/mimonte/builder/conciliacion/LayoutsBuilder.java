/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutCabeceraDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutLineaDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Layout;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeader;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLinea;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLineaCatalog;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;

/**
 * @name LayoutsBuilder
 * @description Clase que construye objetos en base a otros relacionados con
 *              Layouts en general
 *
 * @author Quarksoft
 * @creationDate 27/05/2019 05:07:09 PM
 * @version 0.1
 */
public abstract class LayoutsBuilder {

	/*Conversión de Entity a DTO*/
	/**
	 * Construye un objeto de tipo LayoutDTO a partir de un entity de tipo
	 * Layout
	 * 
	 * @param layout
	 * @return
	 */
	public static LayoutDTO buildLayoutDTOFromLayout(Layout layout) {
		LayoutDTO layoutDTO = null;
		
		if (layout.getLayoutLineas() != null && layout.getLayoutLineas().size() > 0){
			layoutDTO = new LayoutDTO();
			layoutDTO.setFolio(layout.getIdConciliacion());
			layoutDTO.setTipoLayout(TipoLayoutEnum.valueOf(layout.getTipo()));
			layoutDTO.setCabecera(buildLayoutCabeceraDTOFromLayoutHeader(layout.getLayoutHeader()));
			layoutDTO.setLineas(buildLayoutLineaDTOFromLayoutLinea(layout.getLayoutLineas()));
		}
		
		return layoutDTO;
	}
	/**
	 * Construye un objeto de tipo LayoutCabeceraDTO a partir de un entity de tipo
	 * LayoutHeader
	 * 
	 * @param layoutHeader
	 * @return
	 */
	public static LayoutCabeceraDTO buildLayoutCabeceraDTOFromLayoutHeader(LayoutHeader layoutHeader) {
		LayoutCabeceraDTO layoutCabeceraDTO = null;
		
		if (layoutHeader != null) {
			layoutCabeceraDTO = new LayoutCabeceraDTO();
			layoutCabeceraDTO.setId(layoutHeader.getId());
			layoutCabeceraDTO.setCabecera(layoutHeader.getCabecera());
			layoutCabeceraDTO.setUnidadNegocio(layoutHeader.getUnidadNegocio());
			layoutCabeceraDTO.setDescripcion(layoutHeader.getDescripcion());
			layoutCabeceraDTO.setCodigoOrigen(layoutHeader.getCodigoOrigen());
			layoutCabeceraDTO.setFecha(layoutHeader.getFecha());			
		}
		
		return layoutCabeceraDTO;
	}
	/**
	 * Construye una lista de objetos de tipo LayoutLineaDTO a partir de una
	 * lista de objetos de tipo LayoutLinea
	 * 
	 * @param layoutLineas
	 * @return
	 */
	public static List<LayoutLineaDTO> buildLayoutLineaDTOFromLayoutLinea(List<LayoutLinea> layoutLineas) {
		List<LayoutLineaDTO> layoutLineaDTOs = new ArrayList<>();
		
				layoutLineas.forEach(l -> {
					LayoutLineaDTO layoutLineaDTO = new LayoutLineaDTO();
					layoutLineaDTO.setId(l.getId());
					layoutLineaDTO.setLinea(l.getLinea());
					layoutLineaDTO.setCuenta(l.getCuenta());
					layoutLineaDTO.setDepId(l.getDepId());
					layoutLineaDTO.setUnidadOperativa(l.getUnidadOperativa());
					layoutLineaDTO.setNegocio(l.getNegocio());
					layoutLineaDTO.setProyectoNMP(l.getProyectoNmp());
					layoutLineaDTO.setMonto(l.getMonto());			
					layoutLineaDTOs.add(layoutLineaDTO);
					
				});
		
		return layoutLineaDTOs;
	}
	
	/*Conversión de DTO a Entity*/
	/**
	 * Construye un entity de tipo Layout apartir de un objeto de tipo
	 * LayoutDTO
	 * 
	 * @param layoutDTO
	 * @return
	 */
	public static Layout buildLayoutDTOFromLayout(LayoutDTO layoutDTO) {
		Layout layout = null;
		
		if (layoutDTO != null) {
			layout = new Layout();
			layout.setTipo(layoutDTO.getTipoLayout().toString());
			layout.setLayoutHeader(buildLayoutHeaderFromLayoutCabeceraDTO(layoutDTO));
			layout.setLayoutLineas(buildLayoutLineaFromLayoutLineaDTO(layoutDTO.getLineas()));
		}
		
		return layout;
	}
	
	/**
	 * Construye un entity de tipo LayoutHeader apartir de un objeto de tipo
	 * LayoutDTO
	 * 
	 * @param layoutDTO
	 * @return
	 */
	public static LayoutHeader buildLayoutHeaderFromLayoutCabeceraDTO(LayoutDTO layoutDTO) {
		LayoutHeader layoutHeader = null;
		
		if (layoutDTO != null) {
			layoutHeader = new LayoutHeader();			
			layoutHeader.setCabecera(layoutDTO.getCabecera().getCabecera());
			layoutHeader.setUnidadNegocio(layoutDTO.getCabecera().getUnidadNegocio());
			layoutHeader.setDescripcion(layoutDTO.getCabecera().getDescripcion());
			layoutHeader.setCodigoOrigen(layoutDTO.getCabecera().getCodigoOrigen());
			layoutHeader.setFecha(layoutDTO.getCabecera().getFecha());			
		}
		
		return layoutHeader;
	}
	
	/**
	 * Construye un objeto de tipo LayoutLineaDTO a partir de un entity de tipo
	 * LayoutLineaCatalog
	 * 
	 * @param layoutLineaCatalog
	 * @param monto
	 * @return
	 */
	public static LayoutLineaDTO buildLayoutLineaDTOFromLayoutLineaCatalog(LayoutLineaCatalog layoutLineaCatalog, BigDecimal monto) {
		
		LayoutLineaDTO layoutLineaDTO = new LayoutLineaDTO();
		  layoutLineaDTO.setCuenta(layoutLineaCatalog.getCuenta()); 
	      layoutLineaDTO.setDepId(layoutLineaCatalog.getDepId());
		  layoutLineaDTO.setId(0L); 
	      layoutLineaDTO.setLinea(layoutLineaCatalog.getLinea());
		  layoutLineaDTO.setMonto(monto); 
	      layoutLineaDTO.setNegocio(layoutLineaCatalog.getNegocio());
		  layoutLineaDTO.setProyectoNMP(layoutLineaCatalog.getProyectoNmp());
		  layoutLineaDTO.setUnidadOperativa(layoutLineaCatalog.getUnidadOperativa());			
		
		return layoutLineaDTO;
	}
	
	/**
	 * Construye una lista de entities de tipo LayoutLinea a partir de una lista
	 * de objetos de tipo LayoutLineaDTO
	 * 
	 * @param layoutLineaDTOs
	 * @return
	 */
	public static List<LayoutLinea> buildLayoutLineaFromLayoutLineaDTO(List<LayoutLineaDTO> layoutLineaDTOs) {
		List<LayoutLinea> layoutLineas = new ArrayList<>();
				layoutLineaDTOs.forEach(l -> {
					    LayoutLinea layoutLinea = new LayoutLinea();
						layoutLinea.setId(l.getId());
						layoutLinea.setLinea(l.getLinea());
						layoutLinea.setCuenta(l.getCuenta());
						layoutLinea.setDepId(l.getDepId());
						layoutLinea.setUnidadOperativa(l.getUnidadOperativa());
						layoutLinea.setNegocio(l.getNegocio());
						layoutLinea.setProyectoNmp(l.getProyectoNMP());
						layoutLinea.setMonto(l.getMonto());		
						layoutLineas.add(layoutLinea);
				});
		
		return layoutLineas;
	}
	
	/**
	 * Valida las líneas de un Layout
	 * 
	 * @param layoutLineaDTOs
	 * @return
	 */
	public static boolean validaLineas(List<LayoutLineaDTO> layoutLineaDTOs) {
		LayoutLinea layoutLinea = null;
		boolean valor = true;
		List<LayoutLinea> layoutLineas = new ArrayList<>();
				for(LayoutLineaDTO layoutLineaDTO : layoutLineaDTOs) {
					if(validar(layoutLineaDTO)) {
						layoutLinea = new LayoutLinea();
						layoutLinea.setId(layoutLineaDTO.getId());
						layoutLinea.setLinea(layoutLineaDTO.getLinea());
						layoutLinea.setCuenta(layoutLineaDTO.getCuenta());
						layoutLinea.setDepId(layoutLineaDTO.getDepId());
						layoutLinea.setUnidadOperativa(layoutLineaDTO.getUnidadOperativa());
						layoutLinea.setNegocio(layoutLineaDTO.getNegocio());
						layoutLinea.setProyectoNmp(layoutLineaDTO.getProyectoNMP());
						layoutLinea.setMonto(layoutLineaDTO.getMonto());			
						layoutLineas.add(layoutLinea);
					}
					else {
						valor = false;
						break;
					}
				}
		
		return valor;
	}
	/**
	 * Valida los campos requeridos de LayoutLineaDTO
	 *  
	 * @param layoutLineaDTO
	 * @return
	 */
	public static boolean validar(LayoutLineaDTO layoutLineaDTO) {
		return layoutLineaDTO.getId()>=0L && 
				!layoutLineaDTO.getLinea().equals("") && 
				!layoutLineaDTO.getCuenta().equals("")&& 
			   layoutLineaDTO.getMonto().compareTo(BigDecimal.ZERO) != 0;
	}
	
}
