package mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.MovimientoJdbcRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.ReporteJdbcBulkInsert;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoReporte;

@Component
@Repository
public class MovimientoJdbcRepositoryImpl implements MovimientoJdbcRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovimientoJdbcRepositoryImpl.class);

	@Inject
    private DataSource dataSource;

	/**
	 * Referencia a la plantilla de JDBC
	 */
	@Inject
    private JdbcTemplate jdbcTemplate;

	/**
	 * Referencia a la plantilla de JDBC
	 */
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @PostConstruct
    private void postConstruct() {
    	namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.MovimientoJdbcRepository#insertarLista(java.util.List)
	 */
	@Transactional
	public void insertarLista(List<? extends MovimientoReporte> lista) throws Exception {
		LOGGER.info(">> procesarLista({})", lista.size());

		Assert.notEmpty(lista, "Lista a insertar esta vacia");

		ReporteJdbcBulkInsert<MovimientoReporte> bulkInsert = new ReporteJdbcBulkInsert<MovimientoReporte>(lista);
		String query = bulkInsert.buildInsertQuery();

		//String queryInsertStatment = bulkInsert.buildInsertStatement();
		//SqlParameterSource[] values = bulkInsert.buildBatchValues();

		long inicio = System.nanoTime();
		LOGGER.info(">> Insertando movimientos...");
		jdbcTemplate.execute(query);
		//namedJdbcTemplate.batchUpdate(queryInsertStatment,  values);
		long fin = System.nanoTime();

		LOGGER.info(">> Tiempo total ejecucion: {}", TimeUnit.SECONDS.convert(fin-inicio, TimeUnit.NANOSECONDS));
	}

}
