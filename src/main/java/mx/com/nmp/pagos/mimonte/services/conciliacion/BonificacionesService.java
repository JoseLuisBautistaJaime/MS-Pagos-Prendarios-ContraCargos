package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.BonificacionDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 11-11-2020 07:24:00PM
 */
public interface BonificacionesService {

	/**
	 * Se encarga de guardar/actualizar una nueva bonificacion
	 * @param bonificacion
	 * @param registerBy 
	 * @return
	 * @throws ConciliacionException
	 */
	public BonificacionDTO save(BonificacionDTO bonificacion, String registerBy) throws ConciliacionException;

	/**
	 * Se encarga de agregar una nueva bonificacion
	 * @param id
	 * @param deletedBy 
	 * @return
	 * @throws ConciliacionException
	 */
	public void delete(Long id, String deletedBy) throws ConciliacionException;

	/**
	 * Se encarga de listar las bonificaciones para la conciliacion
	 * @param folio
	 * @return
	 * @throws ConciliacionException
	 */
	public List<BonificacionDTO> consulta(Long folio) throws ConciliacionException;

}