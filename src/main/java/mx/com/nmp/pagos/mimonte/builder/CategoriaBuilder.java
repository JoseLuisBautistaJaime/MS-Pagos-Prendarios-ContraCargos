/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import mx.com.nmp.pagos.mimonte.dto.CategoriaDTO;
import mx.com.nmp.pagos.mimonte.dto.CategoriaReqDTO;
import mx.com.nmp.pagos.mimonte.model.Categoria;

/**
 * @name CategoriaBuilder
 * @description Clase de capa de builder que se encarga de convertir difrentes
 *              tipos de objetos y entidades relacionadas con el catalogo
 *              Categoria
 *
 * @author Ismael Flores iaguilar@quarksoft.net
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

	/**
	 * Construye un objeto de tipo CategoriaDTO a partir de un objeto de tipo
	 * Categoria
	 * 
	 * @param categoria
	 * @return
	 */
	public static CategoriaDTO buildCategoriaDTOFromCategoria(Categoria categoria) {
		CategoriaDTO categoriaDTO = null;
		if (null != categoria) {
			categoriaDTO = new CategoriaDTO();
			categoriaDTO.setDescripcion(categoria.getDescripcion());
			categoriaDTO.setId(categoria.getId());
		}
		return categoriaDTO;
	}

	/**
	 * Construye un entity de tipo Categoria a partir de un objeto de tipo
	 * CategoriaDTO
	 * 
	 * @param categoriaDTO
	 * @return
	 */
	public static Categoria buildCategoriaFromCategoriaDTO(CategoriaDTO categoriaDTO) {
		Categoria categoria = null;
		if (null != categoriaDTO) {
			categoria = new Categoria();
			categoria.setDescripcion(categoriaDTO.getDescripcion());
			categoria.setId(categoriaDTO.getId());
		}
		return categoria;
	}

}
