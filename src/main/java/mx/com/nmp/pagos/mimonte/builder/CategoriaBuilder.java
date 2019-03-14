package mx.com.nmp.pagos.mimonte.builder;

import mx.com.nmp.pagos.mimonte.dto.CategoriaDTO;
import mx.com.nmp.pagos.mimonte.dto.CategoriaReqDTO;

/**
 * Nombre: CategoriaBuilder Descripcion: Clase de capa de builder que se encarga
 * de convertir difrentes tipos de objetos y entidades relacionadas con el
 * catalogo Categoria
 *
 * @author Ismael Flores iaguilar@qaurksoft.net
 * @creationDate 14/03/2019 15:55 hrs.
 * @version 0.1
 */
public abstract class CategoriaBuilder {

	private CategoriaBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo CategoriaDTO a partir de un objeto de tipo
	 * CategoriaReqDTO
	 * 
	 * @param categoriaReqDTO
	 * @return
	 */
	public static CategoriaDTO buildCategoriaDTOFromCategoriaReqDTO(CategoriaReqDTO categoriaReqDTO) {
		CategoriaDTO categoriaDTO = null;
		if (null != categoriaReqDTO) {
			categoriaDTO = new CategoriaDTO();
			categoriaDTO.setId(categoriaReqDTO.getId());
		}
		return categoriaDTO;
	}

}
