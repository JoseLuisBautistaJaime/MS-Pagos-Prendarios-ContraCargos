/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutCabeceraDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutLineaDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.LayoutRequestDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Layout;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeader;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeaderCatalog;
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

	/* Conversión de Entity a DTO */

	/**
	 * Construye un objeto de tipo LayoutDTO a partir de un entity de tipo Layout
	 * 
	 * @param layout
	 * @return
	 */
	public static List<LayoutDTO> buildLayoutsDTOFromLayouts(List<Layout> layouts) {
		List<LayoutDTO> layoutsDTO = null;
		if (layouts != null && layouts.size() > 0) {
			layoutsDTO = new ArrayList<LayoutDTO>();
			for (Layout layout : layouts) {
				layoutsDTO.add(buildLayoutDTOFromLayout(layout));
			}
		}
		return layoutsDTO;
	}

	/**
	 * Construye un objeto de tipo LayoutDTO a partir de un entity de tipo Layout
	 * 
	 * @param layout
	 * @return
	 */
	public static LayoutDTO buildLayoutDTOFromLayout(Layout layout) {
		LayoutDTO layoutDTO = null;
		if (null != layout) {
			layoutDTO = new LayoutDTO();
			layoutDTO.setFolio(layout.getIdConciliacion());
			layoutDTO.setTipoLayout(layout.getTipo());
			layoutDTO.setCabecera(buildLayoutCabeceraDTOFromLayoutHeader(layout.getLayoutHeader()));
			if (layout.getLayoutLineas() != null && layout.getLayoutLineas().size() > 0) {
				layoutDTO.setLineas(buildLayoutLineaDTOFromLayoutLinea(layout.getLayoutLineas()));
			}
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
	 * Construye una lista de objetos de tipo LayoutLineaDTO a partir de una lista
	 * de objetos de tipo LayoutLinea
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

	/* Conversión de DTO a Entity */
	/**
	 * Construye un entity de tipo Layout apartir de un objeto de tipo LayoutDTO
	 * 
	 * @param layoutDTO
	 * @param requestUser
	 * @return
	 */
	public static Layout buildLayoutFromLayoutDTO(LayoutDTO layoutDTO, String requestUser) {
		Layout layout = null;

		if (layoutDTO != null) {
			layout = new Layout();
			layout.setTipo(layoutDTO.getTipoLayout());
		}

		return layout;
	}

	/**
	 * Construye un entity de tipo LayoutHeader apartir de un objeto de tipo
	 * LayoutDTO
	 * 
	 * @param layoutDTO
	 * @param layout
	 * @param requestUser
	 * @return
	 */
	public static LayoutHeader buildLayoutHeaderFromLayoutCabeceraDTO(LayoutDTO layoutDTO, Layout layout,
			String requestUser) {
		LayoutHeader layoutHeader = null;

		if (layoutDTO != null) {
			layoutHeader = buildLayoutHeaderFromLayoutCabeceraDTO(layoutDTO.getCabecera(), layout, requestUser);
		}

		return layoutHeader;
	}

	/**
	 * Construye un entity de tipo LayoutHeader apartir de un objeto de tipo
	 * LayoutDTO
	 * 
	 * @param layoutCabeceraDTO
	 * @param layout
	 * @param requestUser
	 * @return
	 */
	public static LayoutHeader buildLayoutHeaderFromLayoutCabeceraDTO(LayoutCabeceraDTO layoutCabeceraDTO,
			Layout layout, String requestUser) {
		LayoutHeader layoutHeader = null;

		if (layoutCabeceraDTO != null) {
			layoutHeader = new LayoutHeader();
			layoutHeader.setCabecera(layoutCabeceraDTO.getCabecera());
			layoutHeader.setUnidadNegocio(layoutCabeceraDTO.getUnidadNegocio());
			layoutHeader.setDescripcion(layoutCabeceraDTO.getDescripcion());
			layoutHeader.setCodigoOrigen(layoutCabeceraDTO.getCodigoOrigen());
			layoutHeader.setFecha(layoutCabeceraDTO.getFecha());
			layoutHeader.setLayout(layout);
			layoutHeader.setCreatedBy(requestUser);
			layoutHeader.setCreatedDate(new Date());
		}

		return layoutHeader;
	}

	/**
	 * Construye un objeto de tipo LayoutLineaDTO a partir de un entity de tipo
	 * LayoutLineaCatalog
	 * 
	 * @param layoutLineaCatalog
	 * @param monto
	 * @param unidadOperativa
	 * @return
	 */
	public static LayoutLineaDTO buildLayoutLineaDTOFromLayoutLineaCatalog(LayoutLineaCatalog layoutLineaCatalog,
			BigDecimal monto, String unidadOperativa) {
		LayoutLineaDTO layoutLineaDTO = new LayoutLineaDTO();
		layoutLineaDTO.setCuenta(layoutLineaCatalog.getCuenta());
		layoutLineaDTO.setDepId(layoutLineaCatalog.getDepId());
		layoutLineaDTO.setId(0L);
		layoutLineaDTO.setLinea(layoutLineaCatalog.getLinea());
		layoutLineaDTO.setMonto(monto);
		layoutLineaDTO.setNegocio(layoutLineaCatalog.getNegocio());
		layoutLineaDTO.setProyectoNMP(layoutLineaCatalog.getProyectoNmp());
		layoutLineaDTO.setUnidadOperativa(unidadOperativa);
		return layoutLineaDTO;
	}

	/**
	 * Construye una lista de entities de tipo LayoutLinea a partir de una lista de
	 * objetos de tipo LayoutLineaDTO
	 * 
	 * @param layoutLineaDTOs
	 * @param layout
	 * @param requestUser
	 * @return
	 */
	public static List<LayoutLinea> buildLayoutLineaFromLayoutLineaDTO(List<LayoutLineaDTO> layoutLineaDTOs,
			Layout layout, String requestUser) {
		List<LayoutLinea> layoutLineas = new ArrayList<>();
		LayoutLinea layoutLinea = null;
		
		for(LayoutLineaDTO l : layoutLineaDTOs){
			layoutLinea = new LayoutLinea();
			layoutLinea.setId(l.getId() != null && l.getId() > 0 ? l.getId() : null);
			layoutLinea.setLinea(l.getLinea());
			layoutLinea.setCuenta(l.getCuenta());
			layoutLinea.setDepId(l.getDepId());
			layoutLinea.setUnidadOperativa(l.getUnidadOperativa());
			layoutLinea.setNegocio(l.getNegocio());
			layoutLinea.setProyectoNmp(l.getProyectoNMP());
			
			// Si el layout es tipo es PAGOS el monto debe ser negativo, si es de tipo DEVOLCUONES DEBE SER POSITIVO
			if(null != layout && null != layout.getTipo()) {
				layoutLinea.setMonto(l.getMonto());
			}
			
			layoutLinea.setLayout(layout);
			layoutLinea.setNuevo(true);
			layoutLinea.setCreatedBy(requestUser);
			layoutLinea.setCreatedDate(new Date());
			layoutLineas.add(layoutLinea);
		};

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
		for (LayoutLineaDTO layoutLineaDTO : layoutLineaDTOs) {
			if (validar(layoutLineaDTO)) {
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
			} else {
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
		return layoutLineaDTO.getId() >= 0L && !layoutLineaDTO.getLinea().equals("")
				&& !layoutLineaDTO.getCuenta().equals("") && layoutLineaDTO.getMonto().compareTo(BigDecimal.ZERO) != 0;
	}

	/**
	 * Construye la cabecera para el layout usando los valores configurados por
	 * default en la bd
	 * 
	 * @param layoutHeaderCatalog
	 * @return
	 */
	public static LayoutCabeceraDTO buildLayoutCabeceraDTOFromLayoutHeaderCatalog(LayoutHeaderCatalog layoutHeaderCatalog, Date fechaOperacion) {
		
		if (fechaOperacion == null) { // No existen operaciones del estado cuenta ?
			fechaOperacion = new Date();
		}

		LocalDate localFecha = getFechaHeader(layoutHeaderCatalog.getTipo(), fechaOperacion);
		String fechaDescripcion = getFechaDescripcionHeader(layoutHeaderCatalog.getTipo(), fechaOperacion);

		LayoutCabeceraDTO layoutHeaderDTO = new LayoutCabeceraDTO();
		layoutHeaderDTO.setCabecera(layoutHeaderCatalog.getCabecera());
		layoutHeaderDTO.setCodigoOrigen(layoutHeaderCatalog.getCodigoOrigen());
		layoutHeaderDTO.setDescripcion(MessageFormat.format(layoutHeaderCatalog.getDescripcion(), fechaDescripcion));
		layoutHeaderDTO.setFecha(localFecha);
		layoutHeaderDTO.setUnidadNegocio(layoutHeaderCatalog.getUnidadNegocio());

		return layoutHeaderDTO;
	}


	/**
	 * Obtiene la fecha para la description header para el tipo de layout
	 * @param tipo
	 * @param fechaConciliacion
	 * @return
	 */
	private static String getFechaDescripcionHeader(TipoLayoutEnum tipo, Date fecha) {
		Instant instant = Instant.ofEpochMilli(fecha.getTime());
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		LocalDate localDate = localDateTime.toLocalDate();
		String fechaDescripcion = localDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
		return fechaDescripcion;
	}


	/**
	 * Obtiene la fecha del header para el tipo de layout
	 * @param tipoLayout
	 * @param fecha
	 * @return
	 */
	private static LocalDate getFechaHeader(TipoLayoutEnum tipoLayout, Date fecha) {
		Instant instant = Instant.ofEpochMilli(fecha.getTime());
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		LocalDate localDate = localDateTime.toLocalDate();

		if (tipoLayout == TipoLayoutEnum.PAGOS) {
			localDate = localDate.plusDays(1); // Dia posterior a la operacion
			if (localDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
				localDate =  localDate.plusDays(3); // Fecha de pago (BANCO) correspondiente al día posterior de la operación. (Cuando es viernes el pago seria el lunes)
			}
		}
		return localDate;
	}


	public static void mergeLinea(LayoutLinea lineaBD, LayoutLinea linea, String requestUser) {
		lineaBD.setLinea(linea.getLinea());
		lineaBD.setCuenta(linea.getCuenta());
		lineaBD.setDepId(linea.getDepId());
		lineaBD.setUnidadOperativa(linea.getUnidadOperativa());
		lineaBD.setNegocio(linea.getNegocio());
		lineaBD.setProyectoNmp(linea.getProyectoNmp());
		lineaBD.setMonto(linea.getMonto());
		lineaBD.setLastModifiedBy(requestUser);
		lineaBD.setLastModifiedDate(new Date());
	}


	/**
	 * Construye un objeto de tipo LayoutDTO a partir de un objeto de tipo
	 * LayoutRequestDTO
	 * 
	 * @param layoutRequestDTO
	 * @return
	 */
	public static LayoutDTO buildLayoutDTOFromLayoutRequestDTO(LayoutRequestDTO layoutRequestDTO) {
		LayoutDTO layoutDTO = null;
		if (null != layoutRequestDTO) {
			layoutDTO = new LayoutDTO();
			layoutDTO.setCabecera(null);
			layoutDTO.setFolio(layoutRequestDTO.getFolio());
			layoutDTO.setLineas(layoutRequestDTO.getLineas());
			layoutDTO.setTipoLayout(layoutRequestDTO.getTipoLayout());
		}
		return layoutDTO;
	}

	/**
	 * Construye una lista de objetos de tipo Long con los ids de una lista de
	 * objetos de tipo LayoutLineaDTO NOTA: No se toman en cuenta los ids 0
	 * 
	 * @param layoutLineaDTOList
	 * @return
	 */
	public static List<Long> buildLongListFromLayoutLineaDTONO0List(List<LayoutLineaDTO> layoutLineaDTOList) {
		List<Long> list = null;
		if (null != layoutLineaDTOList && !layoutLineaDTOList.isEmpty()) {
			list = new ArrayList<>();
			for (LayoutLineaDTO layoutLineaDTO : layoutLineaDTOList)
				if (layoutLineaDTO.getId().compareTo(0L) != 0)
					list.add(layoutLineaDTO.getId());
		}
		return list;
	}

	/**
	 * Construye una lista de objetos de tipo Long a partir de una lista de objetos
	 * de tipo LayoutDTO, especificamente con los atributos id de dicho objeto
	 * 
	 * @param layoutDTO
	 * @return
	 */
	public static List<Long> getLineasIds(LayoutDTO layoutDTO) {
		List<Long> lst = null;
		if (null != layoutDTO && null != layoutDTO.getLineas() && !layoutDTO.getLineas().isEmpty()) {
			lst = new ArrayList<>();
			for (LayoutLineaDTO layoutLineaDTO : layoutDTO.getLineas()) {
				if (layoutLineaDTO.getId() > 0)
					lst.add(layoutLineaDTO.getId());
			}
		}
		return lst;
	}
	
}
