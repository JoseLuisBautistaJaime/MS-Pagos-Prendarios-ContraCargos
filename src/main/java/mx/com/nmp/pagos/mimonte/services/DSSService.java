package mx.com.nmp.pagos.mimonte.services;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioResumenDTO;
import mx.com.nmp.pagos.mimonte.model.ReglaNegocio;

/**
 * Nombre: DSSService
 * Descripcion: Interfaz que contiene las operaciones encargadas de
 * realizar consultas relacionadas con el DSS de la aplicacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate: 12/12/2018 17:06 hrs.
 * @version 0.1
 */
public interface DSSService {

	/**
	 * 
	 * Metodo que consulta todas las reglas de negocio asociadas a un cliente
	 * especifico
	 * 
	 * @param Integer idCliente
	 * @return objeto List<ReglaNegocio>
	 */
	public abstract List<ReglaNegocio> getReglasNegocio(Integer idCliente);

	/**
	 * 
	 * Metodo que obtiene el resultado de evaluar una regla de negocio con datos de
	 * un cliente en especifico
	 * 
	 * @param String query
	 * @return ReglaNegocioResumenDTO
	 */
	public abstract ReglaNegocioResumenDTO executeQuery(String query);

}
