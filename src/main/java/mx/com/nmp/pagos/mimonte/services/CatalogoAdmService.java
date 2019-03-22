package mx.com.nmp.pagos.mimonte.services;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;

/**
 * @name CatalogoAdmService
 * @description Interface que define las operaciones para la interaccion con
 *              objetos de tipo Catalogos administrables
 *
 * @author Ismael Flores Aguilar iaguilar@quarksoft.net
 * @creationDate 04/03/2019 11:00 hrs.
 * @version 0.1
 * @param <E>
 */
public interface CatalogoAdmService<E> {

	/**
	 * Guarda un objeto de tipo catalogo
	 * 
	 * @param e
	 * @param createdBy
	 * @return
	 */
	public <T extends AbstractCatalogoDTO> T save(E e, String createdBy);

	/**
	 * Actualiza un objeto de tipo catalogo
	 * 
	 * @param e
	 * @param lastModifiedBy
	 * @return
	 */
	public <T extends AbstractCatalogoDTO> T update(E e, String lastModifiedBy);

	/**
	 * Regresa un objeto de tipo catalogo por id
	 * 
	 * @param id
	 * @return
	 */
	public <T extends AbstractCatalogoDTO> T findById(Long id);

	/**
	 * Regresa todos los catalogos de un tipo especifico
	 * 
	 * @return
	 */
	public List<? extends AbstractCatalogoDTO> findAll();

	/**
	 * Elimina un catalogo por id
	 * 
	 * @param id
	 * @return
	 */
	public <T extends AbstractCatalogoDTO> T deleteById(Long id);

}
