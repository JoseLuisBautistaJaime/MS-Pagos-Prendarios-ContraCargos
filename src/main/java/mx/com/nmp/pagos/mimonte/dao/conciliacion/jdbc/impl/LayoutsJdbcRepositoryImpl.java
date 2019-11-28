/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.LayoutsJdbcRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.ReporteJdbcBulkInsert;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutReporte;

/**
 * @name LayoutsJdbcRepositoryImpl
 * @description Clase que implementa la interfaz de las operaciones de
 *              inserciones en base de datos relacionadas con layouts mediante
 *              JDBC
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 28/11/2019 17:28 hrs.
 * @version 0.1
 */
@Component
@Repository
public class LayoutsJdbcRepositoryImpl implements LayoutsJdbcRepository {

	/**
	 * Log de clase
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LayoutsJdbcRepositoryImpl.class);

	/**
	 * Referencia a la plantilla de JDBC
	 */
	@Inject
	private JdbcTemplate jdbcTemplate;

	/**
	 * Inserta una lista de sentencias insert SQL de objetos de algun tipo que
	 * implemente LayoutReporte
	 */
	@Override
	public <T extends LayoutReporte> void insertarLista(List<T> lista) throws Exception {

		LOGGER.info(">> procesarLista({})", lista.size());
		Assert.notEmpty(lista, "Lista a insertar esta vacia");

		long inicio = System.nanoTime();
		LOGGER.info(">> Insertando movimientos...");

		// Procesar en batch
		Iterator<T> it = lista.iterator();
		List<T> batchList = new ArrayList<T>();
		while (it.hasNext()) {
			batchList.add(it.next());
			if (batchList.size() >= ConciliacionConstants.COMMON_BATCH_SIZE || // Insertar registros en batch
					(!it.hasNext() && batchList.size() > 0)) { // Insertar registros restantes
				LOGGER.info(">> Insertando {} registros", batchList.size());
				ReporteJdbcBulkInsert<LayoutReporte> bulkInsert = new ReporteJdbcBulkInsert<LayoutReporte>(batchList,
						false);
				String query = bulkInsert.buildInsertQuery();
				jdbcTemplate.execute(query);
				batchList.clear();
			}
		}

		long fin = System.nanoTime();
		LOGGER.info(">> Tiempo total ejecucion: {}", TimeUnit.SECONDS.convert(fin - inicio, TimeUnit.NANOSECONDS));

	}

}
