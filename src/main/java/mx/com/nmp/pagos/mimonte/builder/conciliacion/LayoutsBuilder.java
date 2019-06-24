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
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;

public abstract class LayoutsBuilder {

	/*Conversión de Entity a DTO*/
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
	
	public static List<LayoutLineaDTO> buildLayoutLineaDTOFromLayoutLinea(List<LayoutLinea> layoutLineas) {
		LayoutLineaDTO layoutLineaDTO = null;
		List<LayoutLineaDTO> layoutLineaDTOs = new ArrayList<>();
		
		if (layoutLineas != null) {
			if(layoutLineas != null) {
				for(LayoutLinea layoutLinea : layoutLineas) {
					layoutLineaDTO = new LayoutLineaDTO();
					layoutLineaDTO.setId(layoutLinea.getId());
					layoutLineaDTO.setLinea(layoutLinea.getLinea());
					layoutLineaDTO.setCuenta(layoutLinea.getCuenta());
					layoutLineaDTO.setDepId(layoutLinea.getDepId());
					layoutLineaDTO.setUnidadOperativa(layoutLinea.getUnidadOperativa());
					layoutLineaDTO.setNegocio(layoutLinea.getNegocio());
					layoutLineaDTO.setProyectoNMP(layoutLinea.getProyectoNmp());
					layoutLineaDTO.setMonto(layoutLinea.getMonto());			
					layoutLineaDTOs.add(layoutLineaDTO);
					
				}
			}
		}
		
		return layoutLineaDTOs;
	}
	
	/*Conversión de DTO a Entity*/
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
	
	public static List<LayoutLinea> buildLayoutLineaFromLayoutLineaDTO(List<LayoutLineaDTO> layoutLineaDTOs) {
		LayoutLinea layoutLinea = null;
		List<LayoutLinea> layoutLineas = new ArrayList<>();
		
		if (layoutLineaDTOs != null) {
				for(LayoutLineaDTO layoutLineaDTO : layoutLineaDTOs) {
					if(validar(layoutLineaDTO)){
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
				}
		}
		
		return layoutLineas;
	}
	public static List<LayoutLinea> buildLayoutLineaFromLayoutLineaDTOC(List<LayoutLineaDTO> layoutLineaDTOs) {
		LayoutLinea layoutLinea = null;
		List<LayoutLinea> layoutLineas = new ArrayList<>();
		
		if (layoutLineaDTOs != null) {
				for(LayoutLineaDTO layoutLineaDTO : layoutLineaDTOs) {
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
		}
		
		return layoutLineas;
	}
	//Layout layout
	public static boolean validar(LayoutLineaDTO layoutLineaDTO) {
		return layoutLineaDTO.getId()>=0L && 
				!layoutLineaDTO.getLinea().equals("") && 
				!layoutLineaDTO.getCuenta().equals("")&& 
				!layoutLineaDTO.getDepId().equals("") && 
			   !layoutLineaDTO.getUnidadOperativa().equals("") &&
			   !layoutLineaDTO.getNegocio().equals("") && 
			   !layoutLineaDTO.getProyectoNMP().equals("") &&
			   layoutLineaDTO.getMonto().compareTo(BigDecimal.ZERO) > 0;
	}
	
}
