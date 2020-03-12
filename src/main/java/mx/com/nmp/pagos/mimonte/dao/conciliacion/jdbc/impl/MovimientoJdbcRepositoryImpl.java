/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.impl;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.MovimientoJdbcRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.ReporteJdbcBulkInsert;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoReporte;

/**
 * @name MovimientoJdbcRepositoryImpl
 * @description Clase que implementa la Interface con las operaciones de
 *              insercion/actualizacion de movimientos en batch
 *
 * @author Jorge Galvez jgalvez@quarksoft.net
 * @creationDate unknown .
 * @version 0.1
 */
@Component
@Repository
public class MovimientoJdbcRepositoryImpl implements MovimientoJdbcRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovimientoJdbcRepositoryImpl.class);


	/**
	 * Referencia a la plantilla de JDBC
	 */
	@Inject
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.MovimientoJdbcRepository#
	 * insertarLista(java.util.List)
	 */
	@Transactional
	public <T extends MovimientoReporte> void insertarLista(List<T> lista) throws Exception {
		LOGGER.info(">> procesarLista({})", lista.size());

		Assert.notEmpty(lista, "Lista a insertar esta vacia");

		long inicio = System.nanoTime();
		LOGGER.info(">> Insertando movimientos...");

		// Procesar en batch
		Iterator<T> it = lista.iterator();
		List<T> batchList = new ArrayList<T>();
		while (it.hasNext()) {
			batchList.add(it.next());
			if (batchList.size() >= ConciliacionConstants.COMMON_BATCH_SIZE || !it.hasNext()) { // Insertar registros en batch o Insertar registros restantes
				LOGGER.info(">> Insertando {} registros", batchList.size());
				ReporteJdbcBulkInsert<MovimientoReporte> bulkInsert = new ReporteJdbcBulkInsert<MovimientoReporte>(batchList, false);
				String query = bulkInsert.buildInsertQuery();
				jdbcTemplate.execute(query);
				batchList.clear();
			}
		}

		long fin = System.nanoTime();

		LOGGER.info(">> Tiempo total ejecucion: {}", TimeUnit.SECONDS.convert(fin - inicio, TimeUnit.NANOSECONDS));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.MovimientoJdbcRepository#
	 * insertarLista(java.util.List, java.lang.String)
	 */
	@Transactional
	public <T extends MovimientoConciliacion> void insertarLista(List<T> lista, String storedProcedure)	throws Exception {
		LOGGER.info(">> procesarLista({})", lista.size());

		Assert.notEmpty(lista, "Lista a insertar esta vacia");

		long inicio = System.nanoTime();

		// Procesar en batch
		Iterator<T> it = lista.iterator();
		List<T> batchList = new ArrayList<T>();
		while (it.hasNext()) {
			batchList.add(it.next());
			if (batchList.size() >= ConciliacionConstants.COMMON_BATCH_SIZE || !it.hasNext()) { // Insertar registros en batch o Insertar registros restantes
				LOGGER.info(">> Generando SQL batch...");
				ReporteJdbcBulkInsert<MovimientoConciliacion> bulkInsert = new ReporteJdbcBulkInsert<MovimientoConciliacion>(batchList, true);
				List<String> querySps = bulkInsert.buildCallSPStatements(storedProcedure, "");
				SqlParameterSource[] paramSourceList = bulkInsert.buildBatchValues();
				
				LOGGER.info(">> Agregando registros y valores al batch...");
				int[] res = null;
				try (CallableStatement callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall(querySps.get(0))) {
					for (SqlParameterSource paramSource : paramSourceList) {
						int index = 1;
						for (String paramName : paramSource.getParameterNames()) {
							callableStatement.setObject(index++, paramSource.getValue(paramName), paramSource.getSqlType(paramName));
						}
						callableStatement.addBatch();
					}
					LOGGER.info(">> Insertando {} registros", batchList.size());
					res = callableStatement.executeBatch();
				}
				LOGGER.info("Resultado: " + (res != null && res.length > 0 ? res[0] : ""));
				batchList.clear();
			}
		}

		long fin = System.nanoTime();

		LOGGER.info(">> Tiempo total ejecucion: {}", TimeUnit.SECONDS.convert(fin - inicio, TimeUnit.NANOSECONDS));
	}

}
