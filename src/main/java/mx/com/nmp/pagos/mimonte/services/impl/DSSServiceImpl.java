package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.dao.DSSRepository;
import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioResumenDTO;
import mx.com.nmp.pagos.mimonte.model.ReglaNegocio;
import mx.com.nmp.pagos.mimonte.services.DSSService;

/**
 * Nombre: DSSServiceImpl
 * Descripcion: Clase que define las operacions
 * encargadas de realizar consultas relacionadas con el DSS de la aplicacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate: 12/12/2018 17:19 hrs.
 * @version 0.1
 */
@Service("dssServiceImpl")
public class DSSServiceImpl implements DSSService {

	@Autowired
	private DSSRepository dssRepository;

	/**
	 * 
	 * Metodo que consulta todas las reglas de negocio asociadas a un cliente
	 * especifico
	 * 
	 * @param Integer idCliente
	 * @return objeto List<ReglaNegocio>
	 */
	@Override
	public List<ReglaNegocio> getReglasNegocio(Integer idCliente) {
		return dssRepository.getReglasNegocio(idCliente);
	}

	/**
	 * 
	 * Metodo que obtiene el resultado de evaluar una regla de negocio con datos de
	 * un cliente en especifico
	 * 
	 * @param String query
	 * @return ReglaNegocioResumenDTO
	 */
	@Override
	public ReglaNegocioResumenDTO executeQuery(String query) {
		return dssRepository.executeQuery(query);
	}

}
