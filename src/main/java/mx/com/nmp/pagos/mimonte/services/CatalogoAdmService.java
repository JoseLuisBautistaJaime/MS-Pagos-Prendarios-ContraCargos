package mx.com.nmp.pagos.mimonte.services;

import mx.com.nmp.pagos.mimonte.dto.AbstractCatalogoDTO;

/**
 * Nombre: CatalogoAdmService Descripcion: Interface que define las operaciones
 * para la interaccion con objetos de tipo Catalogos administrables
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
	 * @param t
	 * @return
	 */
	public <T extends AbstractCatalogoDTO> T save(E e);

	/**
	 * Actualiza un objeto de tipo catalogo
	 * 
	 * @param t
	 * @return
	 */
	public <T extends AbstractCatalogoDTO> T update(E e);

	/**
	 * Regresa un objeto de tipo catalogo por id
	 * 
	 * @param id
	 * @return
	 */
	public <T extends AbstractCatalogoDTO> T findById(Long id);

}
