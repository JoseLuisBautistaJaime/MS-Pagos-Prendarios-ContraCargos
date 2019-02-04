package mx.com.nmp.pagos.mimonte.services.impl;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.ReglaNegocioBuilder;
import mx.com.nmp.pagos.mimonte.dao.DSSRepository;
import mx.com.nmp.pagos.mimonte.dao.DSSRepositoryCustom;
import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioResumenDTO;
import mx.com.nmp.pagos.mimonte.model.ReglaNegocio;
import mx.com.nmp.pagos.mimonte.services.DSSService;

/**
 * Nombre: DSSServiceImpl Descripcion: Clase que define las operacions
 * encargadas de realizar consultas relacionadas con el DSS de la aplicacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate: 12/12/2018 17:19 hrs.
 * @version 0.1
 */
@Service("dssServiceImpl")
public class DSSServiceImpl implements DSSService {

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DSSServiceImpl.class);

	@Autowired
	@Qualifier("dssRepository")
	private DSSRepository dssRepository;

	@Autowired(required = true)
	@Qualifier(value = "dssRepositoryImpl")
	private DSSRepositoryCustom dssRepositoryCustom;

	/**
	 * 
	 * Metodo que consulta todas las reglas de negocio asociadas a un cliente
	 * especifico
	 * 
	 * @param Integer idCliente
	 * @return objeto List<ReglaNegocio>
	 * @throws SQLDataException
	 * @throws SQLException
	 * @throws                  @throws DataIntegrityViolationException
	 */
	@Override
	public List<ReglaNegocio> getReglasNegocio(Long idCliente)
			throws DataIntegrityViolationException, SQLDataException, SQLException {
		return dssRepository.getReglasNegocio(idCliente);
	}

	/**
	 * 
	 * Metodo que obtiene el resultado de evaluar una regla de negocio con datos de
	 * un cliente en especifico
	 * 
	 * @param String query
	 * @return ReglaNegocioResumenDTO
	 * @throws SQLException
	 * @throws SQLDataException
	 * @throws DataIntegrityViolationException
	 */
	@Override
	public ReglaNegocioResumenDTO execQuery(String query)
			throws DataIntegrityViolationException, SQLDataException, SQLException {
		LOG.debug("Iniciando metodo DSSServiceImpl.execQuery con query: " + query);
		ReglaNegocioResumenDTO reglaNegocioResumenDTO = null;
		Object[] obj = dssRepositoryCustom.execQuery(query);
		reglaNegocioResumenDTO = ReglaNegocioBuilder.buildReglaNegocioResumenDTOFromObjArr(obj);
		return reglaNegocioResumenDTO;
	}

}
