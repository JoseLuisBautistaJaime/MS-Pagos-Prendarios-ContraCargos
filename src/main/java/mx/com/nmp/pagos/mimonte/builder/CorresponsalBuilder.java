/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.CorresponsalDTO;
import mx.com.nmp.pagos.mimonte.dto.CorresponsalesRespPostDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Proveedor;

/**
 * @name CorresponsalBuilder
 * @description Builder que se encaraga de fabricar objetos a partir de entities
 *              y viceversa
 *
 * @author Daniel Hernandez danhernandez@quarksoft.net
 * @creationDate 06/11/2020 19:28 hrs.
 * @version 0.1
 */
public class CorresponsalBuilder {

	private CorresponsalBuilder() {
		/**
		 * hidden constructor
		 */
	}

	
	/**
	 * Construye un objeto de tipo CorresponsalesRespPostDTO a partir de un objeto de
	 * tipo CorresponsalDTO
	 * 
	 * @param corresponsalDTO
	 * @return corresponsalRespPostDTO
	 */
	public static CorresponsalesRespPostDTO buildCorresponsalRespPostDTOfromCorresponsalDTO(CorresponsalDTO corresponsalDTO) {
		CorresponsalesRespPostDTO corresponsalRespPostDTO = null;
		if (null != corresponsalDTO) {
			corresponsalRespPostDTO = new CorresponsalesRespPostDTO();
			corresponsalRespPostDTO.setNombre(corresponsalDTO.getNombre());
			corresponsalRespPostDTO.setDescripcion(corresponsalDTO.getDescripcion());
		}
		return corresponsalRespPostDTO;
	}
	
	
	/**
	 * Construye una lista de objetos de tipo CorresponsalesRespPostDTO a partir de una
	 * lista de objetos de tipo CorresponsalDTO
	 * 
	 * @param corresponsalDTOList
	 * @return corresponsalRespPostDTOList
	 */
	public static List<CorresponsalesRespPostDTO> buildCorresponsalRespPostDTOListfromCorresponsalDTOList(
			List<CorresponsalDTO> corresponsalDTOList) {
		
		List<CorresponsalesRespPostDTO> corresponsalRespPostDTOList = null;
		
		if (null != corresponsalDTOList && !corresponsalDTOList.isEmpty()) {
			
			corresponsalRespPostDTOList = new ArrayList<CorresponsalesRespPostDTO>();
			
			for (CorresponsalDTO corresponsalDTO : corresponsalDTOList) {
				corresponsalRespPostDTOList.add(buildCorresponsalRespPostDTOfromCorresponsalDTO(corresponsalDTO));
			}
		}
		return corresponsalRespPostDTOList;
	}

	/**
	 * Construye una lista de objetos de tipo CorresponsalDTO a partir de una lista de
	 * objetos de tipo Proveedor (Corresponsal-Proveedor)
	 * 
	 * @param proveedorList
	 * @return List CorresponsalDTO
	 */
	public static List<CorresponsalDTO> buildCorresponsalDTOListFromProveedorList(List<Proveedor> proveedorList) {
		List<CorresponsalDTO> corresponsalDTOList = null;
		if (null != proveedorList) {
			corresponsalDTOList = new ArrayList<>();
			for (Proveedor proveedor : proveedorList) {
				corresponsalDTOList.add(buildCorresponsalDTOFromProveedor(proveedor));
			}
		}
		return corresponsalDTOList;
	}
	
	/**
	 * Metodo que convierte de un entity tipo Proveedor a un objeto de tipo
	 * CorresponsalDTO
	 * 
	 * @param proveedor
	 * @return corresponsalDTO
	 */
	public static CorresponsalDTO buildCorresponsalDTOFromProveedor(Proveedor proveedor) {
		CorresponsalDTO corresponsalDTO = null;
		if (null != proveedor) {
			corresponsalDTO = new CorresponsalDTO();
			corresponsalDTO.setNombre(proveedor.getNombre());
			corresponsalDTO.setDescripcion(proveedor.getDescripcion());
		}
		return corresponsalDTO;
	}
	
}
