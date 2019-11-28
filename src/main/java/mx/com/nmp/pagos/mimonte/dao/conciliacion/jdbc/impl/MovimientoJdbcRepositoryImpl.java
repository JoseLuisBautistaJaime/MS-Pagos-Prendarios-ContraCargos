/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

	private static final int BATCH_SIZE = 500;

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
			if (batchList.size() >= BATCH_SIZE || // Insertar registros en batch
					(!it.hasNext() && batchList.size() > 0)) { // Insertar registros restantes
				LOGGER.info(">> Insertando {} registros", batchList.size());
				ReporteJdbcBulkInsert<MovimientoReporte> bulkInsert = new ReporteJdbcBulkInsert<MovimientoReporte>(
						batchList, false);
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
	public <T extends MovimientoConciliacion> void insertarLista(List<T> lista, String storedProcedure)
			throws Exception {
		LOGGER.info(">> procesarLista({})", lista.size());

		Assert.notEmpty(lista, "Lista a insertar esta vacia");

		long inicio = System.nanoTime();
		LOGGER.info(">> Insertando movimientos...");

		// Procesar en batch
		Iterator<T> it = lista.iterator();
		List<T> batchList = new ArrayList<T>();
		while (it.hasNext()) {
			batchList.add(it.next());
			if (batchList.size() >= BATCH_SIZE || // Insertar registros en batch
					(!it.hasNext() && batchList.size() > 0)) { // Insertar registros restantes
				LOGGER.info(">> Insertando {} registros", batchList.size());
				ReporteJdbcBulkInsert<MovimientoConciliacion> bulkInsert = new ReporteJdbcBulkInsert<MovimientoConciliacion>(
						Arrays.asList(lista.get(0)), true);
				String query = bulkInsert.buildCallSP(storedProcedure);
				jdbcTemplate.execute(query);
				batchList.clear();
			}
		}

		long fin = System.nanoTime();

		LOGGER.info(">> Tiempo total ejecucion: {}", TimeUnit.SECONDS.convert(fin - inicio, TimeUnit.NANOSECONDS));
	}

}
