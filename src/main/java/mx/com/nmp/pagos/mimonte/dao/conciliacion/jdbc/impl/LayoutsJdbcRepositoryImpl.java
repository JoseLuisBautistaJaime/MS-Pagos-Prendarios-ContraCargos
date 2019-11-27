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
import mx.com.nmp.pagos.mimonte.model.conciliacion.Layout;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutHeader;
import mx.com.nmp.pagos.mimonte.model.conciliacion.LayoutLinea;

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

	@Override
	public void insertarListaLayout(List<Layout> layoutList, String functionName) throws Exception {
		LOGGER.info(">> procesarLista({})", layoutList.size());

		Assert.notEmpty(layoutList, "Lista a insertar esta vacia");

		long inicio = System.nanoTime();
		LOGGER.info(">> Insertando layout...");

		// Procesar en batch
		Iterator<Layout> it = layoutList.iterator();
		List<Layout> batchList = new ArrayList<>();
		while (it.hasNext()) {
			batchList.add(it.next());
			if (batchList.size() >= ConciliacionConstants.COMMON_BATCH_SIZE || // Insertar registros en batch
					(!it.hasNext() && !batchList.isEmpty())) { // Insertar registros restantes
				LOGGER.info(">> Insertando {} registros", batchList.size());
				ReporteJdbcBulkInsert<Layout> bulkInsert = new ReporteJdbcBulkInsert<>(layoutList, true);
				String query = bulkInsert.buildCallSP(functionName);
				jdbcTemplate.execute(query);
				batchList.clear();
			}
		}

		long fin = System.nanoTime();

		LOGGER.info(">> Tiempo total ejecucion: {}", TimeUnit.SECONDS.convert(fin - inicio, TimeUnit.NANOSECONDS));
	}

	@Override
	public void insertarListaLayoutHeader(List<LayoutHeader> layoutHeaderList, String functionName) throws Exception {
		LOGGER.info(">> procesarLista({})", layoutHeaderList.size());

		Assert.notEmpty(layoutHeaderList, "Lista a insertar esta vacia");

		long inicio = System.nanoTime();
		LOGGER.info(">> Insertando header de layout...");

		// Procesar en batch
		Iterator<LayoutHeader> it = layoutHeaderList.iterator();
		List<LayoutHeader> batchList = new ArrayList<>();
		while (it.hasNext()) {
			batchList.add(it.next());
			if (batchList.size() >= ConciliacionConstants.COMMON_BATCH_SIZE || // Insertar registros en batch
					(!it.hasNext() && !batchList.isEmpty())) { // Insertar registros restantes
				LOGGER.info(">> Insertando {} registros", batchList.size());
				ReporteJdbcBulkInsert<LayoutHeader> bulkInsert = new ReporteJdbcBulkInsert<>(layoutHeaderList, true);
				String query = bulkInsert.buildCallSP(functionName);
				jdbcTemplate.execute(query);
				batchList.clear();
			}
		}

		long fin = System.nanoTime();

		LOGGER.info(">> Tiempo total ejecucion: {}", TimeUnit.SECONDS.convert(fin - inicio, TimeUnit.NANOSECONDS));
	}

	@Override
	public void insertarListaLayoutLinea(List<LayoutLinea> layoutLineaList, String functionName) throws Exception {
		LOGGER.info(">> procesarLista({})", layoutLineaList.size());

		Assert.notEmpty(layoutLineaList, "Lista a insertar esta vacia");

		long inicio = System.nanoTime();
		LOGGER.info(">> Insertando linea de layout...");

		// Procesar en batch
		Iterator<LayoutLinea> it = layoutLineaList.iterator();
		List<LayoutLinea> batchList = new ArrayList<>();
		while (it.hasNext()) {
			batchList.add(it.next());
			if (batchList.size() >= ConciliacionConstants.COMMON_BATCH_SIZE || // Insertar registros en batch
					(!it.hasNext() && !batchList.isEmpty())) { // Insertar registros restantes
				LOGGER.info(">> Insertando {} registros", batchList.size());
				ReporteJdbcBulkInsert<LayoutLinea> bulkInsert = new ReporteJdbcBulkInsert<>(layoutLineaList, true);
				String query = bulkInsert.buildCallSP(functionName);
				jdbcTemplate.execute(query);
				batchList.clear();
			}
		}

		long fin = System.nanoTime();

		LOGGER.info(">> Tiempo total ejecucion: {}", TimeUnit.SECONDS.convert(fin - inicio, TimeUnit.NANOSECONDS));
	}

}
