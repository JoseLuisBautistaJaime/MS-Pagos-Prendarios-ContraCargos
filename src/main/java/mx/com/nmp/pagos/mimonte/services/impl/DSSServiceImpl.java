/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl;

import java.math.BigInteger;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.ReglaNegocioBuilder;
import mx.com.nmp.pagos.mimonte.builder.TipoAutorizacionBuilder;
import mx.com.nmp.pagos.mimonte.dao.DSSRepository;
import mx.com.nmp.pagos.mimonte.dao.DSSRepositoryCustom;
import mx.com.nmp.pagos.mimonte.dao.TipoAutorizacionRepository;
import mx.com.nmp.pagos.mimonte.dto.TipoAutorizacionDTO;
import mx.com.nmp.pagos.mimonte.model.ReglaNegocio;
import mx.com.nmp.pagos.mimonte.model.TipoAutorizacion;
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

	/**
	 * Repository de DSS
	 */
	@Autowired
	@Qualifier("dssRepository")
	private DSSRepository dssRepository;

	/**
	 * Repository de TipoAutorizacion
	 */
	@Autowired
	@Qualifier("tipoAutorizacionRepository")
	private TipoAutorizacionRepository tipoAutorizacionRepository;

	/**
	 * Repository personalizado de DSS
	 */
	@Autowired(required = true)
	@Qualifier(value = "dssRepositoryImpl")
	private DSSRepositoryCustom dssRepositoryCustom;

	/**
	 * Metodo que consulta todas las reglas de negocio
	 * 
	 * @return
	 * @throws DataIntegrityViolationException
	 * @throws SQLDataException
	 * @throws SQLException
	 */
	@Override
	public List<ReglaNegocio> getReglasNegocio() {
		return dssRepository.findAll();
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
	public Object execQuery(String query) throws DataIntegrityViolationException, SQLDataException, SQLException {
		LOG.debug("Iniciando metodo DSSServiceImpl.execQuery con query: {}", query);
		Boolean estatus = null;
		BigInteger obj = dssRepositoryCustom.execQuery(query);
		estatus = ReglaNegocioBuilder.buildReglaNegocioResumenDTOFromObjArr(obj);
		return estatus;
	}

	/**
	 * Obtiene un tipo de autorizacion por id
	 */
	@Override
	public TipoAutorizacionDTO getTipoAutorizacionById(final Integer id) {
		Optional<TipoAutorizacion> tipoAutorizacion = tipoAutorizacionRepository.findById(id);
		if (tipoAutorizacion.isPresent())
			return TipoAutorizacionBuilder.buildTipoAutorizacionDTOFromTipoAutorizacion(tipoAutorizacion.get());
		return null;
	}

}
