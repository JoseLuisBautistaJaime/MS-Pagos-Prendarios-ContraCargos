package mx.com.nmp.pagos.mimonte.conciliacion;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.ReporteJdbcBulkInsert;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MetodoPagoMovimientosProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoReporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TarjetaMovimientosProveedor;

public class JdbcBulkInsertTest {

	public static void main(String[] args) {

		MovimientoMidas movMidas = new MovimientoMidas();
		movMidas.setId(1L);
		movMidas.setEstatus(true);
		movMidas.setFecha(new Date());
		movMidas.setFolio(12345L);
		movMidas.setIdTipoContrato(11);
		movMidas.setTipoTarjeta("Banamex");
		
		ReporteJdbcBulkInsert<MovimientoReporte> bulk = new ReporteJdbcBulkInsert<MovimientoReporte>(Arrays.asList(movMidas));
		String query = bulk.buildInsertStatement();
		System.out.println(query);

		MovimientoProveedor movProveedor = new MovimientoProveedor();
		movProveedor.setId(1);
		movProveedor.setAmount(new BigDecimal(100));
		movProveedor.setReporte(1);
		movProveedor.setOrderId("1234");
		movProveedor.setCreationDate(new Date());
		movProveedor.setMetodoPagoMovimientosProveedor(new MetodoPagoMovimientosProveedor());
		movProveedor.getMetodoPagoMovimientosProveedor().setType("abc");
		movProveedor.setTarjetaMovimientosProveedor(new TarjetaMovimientosProveedor());
		movProveedor.getTarjetaMovimientosProveedor().setAddress("Address");
		
		bulk = new ReporteJdbcBulkInsert<MovimientoReporte>(Arrays.asList(movProveedor));
		String queryProv = bulk.buildInsertStatement();
		System.out.println(queryProv);

	}

}
