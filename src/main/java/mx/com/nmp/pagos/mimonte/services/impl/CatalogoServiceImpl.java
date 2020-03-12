/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dao.CatalogoRepository;
import mx.com.nmp.pagos.mimonte.dto.CatalogoDTO;
import mx.com.nmp.pagos.mimonte.dto.EntradaCatalogo;
import mx.com.nmp.pagos.mimonte.exception.CatalogoNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Catalogo;
import mx.com.nmp.pagos.mimonte.model.extrafilter.Filtro;
import mx.com.nmp.pagos.mimonte.services.CatalogoService;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorCadena;

/**
 * @name CatalogoServiceImpl
 * @description Clase que implementa la operacion que obtiene los registros de
 *              un catalogo definido
 *
 * @author Javier Hernandez jhernandez@quarksoft.net
 * @date 07/02/2018 08:02 PM
 * @version 0.1
 */
@Service
@Transactional(readOnly = true, value = "transactionManager")
public class CatalogoServiceImpl implements CatalogoService {
	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(CatalogoServiceImpl.class);

	/**
	 * Repositorio para la entidad {@link mx.com.nmp.pagos.mimonte.model.Catalogo}
	 */
	@Autowired
	private CatalogoRepository catalogoRepository;

	/**
	 * Dependencia para ejecucion de Query SQL sobre JDBC
	 */
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * Metodo que obtiene la lista de extrafilter del sistema y devuelve la lista
	 * con los nombres de los mismos
	 * 
	 * @return List<String> Lista con los nombres de los extrafilter registrados en
	 *         el sistema para poder consultarlos
	 */
	@Override
	public List<String> getCatalogosSistema() {

		log.info("Consultando registros de extrafilter del sistema...");
		List<Catalogo> results = catalogoRepository.findAllByActivoIsTrue();

		log.info("Generando lista de nombres de extrafilter encontrados...");
		List<String> registrosCatalogos = new ArrayList<>();
		results.forEach(item -> registrosCatalogos.add(item.getDescripcionCorta()));

		log.debug("Validando la lista de registros...");
		if (registrosCatalogos.isEmpty()) {
			log.error("No se encontraron registros de extrafilter del sistema...");
			throw new CatalogoNotFoundException("No se encontraron registros de extrafilter del sistema...",
					CodigoError.NMP_PMIMONTE_0005);
		}

		log.info("Regresando lista de extrafilter: {}", registrosCatalogos);
		return registrosCatalogos;
	}

	/**
	 * Obtiene un catalogo por nombre
	 */
	@Override
	public CatalogoDTO getRegistrosCatalogo(String nombreCatalogo) {
		Catalogo catalogo = getCatalogo(nombreCatalogo);
		if (null == catalogo)
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOMBRE_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_BUSINESS_077);
		else if (null == catalogo.getNombreTabla())
			throw new CatalogoNotFoundException(CatalogConstants.CATALOG_NOMBRE_TABLA_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_BUSINESS_002);
		log.info("Consultando la base de datos para la tabla: {}", catalogo.getNombreTabla());
		List<EntradaCatalogo> resultados = null;
		try {
			resultados = new ArrayList<>();
			List<Map<String, Object>> rows = jdbcTemplate
					.queryForList(CatalogConstants.Consulta.QUERY_VALUE + catalogo.getNombreTabla());
			for (Map<String, Object> row : rows) {
				EntradaCatalogo catalogoResult = new EntradaCatalogo();
				catalogoResult.setId(Integer.parseInt(((row.get(CatalogConstants.Consulta.ID_VALUE))).toString()));
				catalogoResult.setDescripcionCorta((String) row.get(CatalogConstants.Consulta.DESCRIPCION_CORTA_VALUE));
				catalogoResult.setDescripcion((String) row.get(CatalogConstants.Consulta.DESCRIPCION_VALUE));
				resultados.add(catalogoResult);
			}
		} catch (DataAccessException dae) {
			log.error("Ocurrio un error al intentar obtener los registros del catalogo: {}. Mensaje de error: {}",
					nombreCatalogo, dae.getMessage());
			throw new CatalogoNotFoundException("Ocurrio un error al intentar obtener los registros del catalogo: "
					+ nombreCatalogo + ". Mensaje de error: " + dae.getMessage(), CodigoError.NMP_PMIMONTE_0005);
		}

		log.debug("Validando la lista de registros...");
		if (resultados.isEmpty()) {
			throw new CatalogoNotFoundException("No se encontraron registros para el catalogo especificado",
					CodigoError.NMP_PMIMONTE_0005);
		}

		log.info("Generando objeto de tipo CatalogoDTO...");
		return new CatalogoDTO(catalogo.getDescripcion(), resultados);
	}

	/**
	 * Obtiene un catalogo por nombre, un campo filtro y una lista de parametros
	 * extra
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public CatalogoDTO getRegistrosCatalogo(String nombreCatalogo, Filtro filtro, List<?> parametros) {
		Catalogo catalogo = getCatalogo(nombreCatalogo);

		log.info("Consultando la base de datos para la tabla: {}", catalogo.getNombreTabla());
		List<EntradaCatalogo> resultados;

		try {
			String query = String.format("SELECT id, descripcion_corta, descripcion FROM %s cat %s",
					catalogo.getNombreTabla(), filtro.getQuery());
			Object[] args = ObjectUtils.isEmpty(parametros) ? null : parametros.toArray();

			resultados = jdbcTemplate.query(query, args, new BeanPropertyRowMapper<>(EntradaCatalogo.class));
		} catch (Exception dae) {
			log.error("Ocurrio un error al intentar obtener los registros del catalogo: {}. Mensaje de error: {}",
					nombreCatalogo, dae.getMessage(), dae);
			throw new RuntimeException("Ocurrio un error al intentar obtener los registros del catalogo: "
					+ nombreCatalogo + ". Mensaje de error: " + dae.getMessage());
		}

		return new CatalogoDTO(catalogo.getDescripcion(), resultados);
	}

	/**
	 * Recupera un elemento de {@link Catalogo}
	 *
	 * @param nombreCatalogo Nombre del catalogo a recuperar
	 *
	 * @return Catalogo especificado
	 */
	public Catalogo getCatalogo(String nombreCatalogo) {
		log.info("Validando que el nombre del catalogo {} sea un parametro correcto...", nombreCatalogo);
		ValidadorCadena.notNullNorEmpty(nombreCatalogo);

		log.info("Obteniendo el tipo de Catalogo de acuerdo al nombre: {}", nombreCatalogo);
		Catalogo catalogo = catalogoRepository.findByDescripcionCorta(nombreCatalogo);

		log.info("Validando si el catalogo existe...");
		if (catalogo == null) {
			log.error("El catalogo especificado no existe dentro del sistema...");
			throw new CatalogoNotFoundException("El catalogo especificado no existe dentro del sistema...",
					CodigoError.NMP_PMIMONTE_0005);
		} else if (!catalogo.isActivo()) {
			log.error("El catalogo especificado se encuentra inactivo dentro del sistema...");
			throw new CatalogoNotFoundException("El catalogo especificado se encuentra inactivo dentro del sistema...",
					CodigoError.NMP_PMIMONTE_0005);
		}
		return catalogo;
	}

}
