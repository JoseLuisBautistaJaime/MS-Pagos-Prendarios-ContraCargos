package mx.com.nmp.pagos.mimonte.conciliacion;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.ReporteJdbcBulkInsert;
import mx.com.nmp.pagos.mimonte.model.EstatusTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MetodoPagoMovimientosProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoReporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TarjetaMovimientosProveedor;

public class JdbcBulkInsertTest {

	public static void main(String[] args) {

		/*MovimientoMidas movMidas = new MovimientoMidas();
		movMidas.setId(1L);
		movMidas.setEstatus(true);
		movMidas.setFecha(new Date());
		movMidas.setFolio(12345L);
		movMidas.setIdTipoContrato(11);
		movMidas.setTipoTarjeta("Banamex");
		
		ReporteJdbcBulkInsert<MovimientoReporte> bulk = new ReporteJdbcBulkInsert<MovimientoReporte>(Arrays.asList(movMidas));
		String insertStatement = bulk.buildInsertStatement();
		String query = bulk.buildInsertQuery();
		System.out.println(insertStatement);
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
		insertStatement = bulk.buildInsertStatement();
		query = bulk.buildInsertQuery();
		System.out.println(insertStatement);
		System.out.println(query);*/


		MovimientoTransito movTransito = new MovimientoTransito();
		movTransito.setId(1);
		movTransito.setCreatedBy("JG");
		movTransito.setCreatedDate(new Date());
		movTransito.setCuenta("Cuenta");
		movTransito.setEsquemaTarjeta("Esquema Tarjeta");
		movTransito.setEstatus(new EstatusTransito());
		movTransito.getEstatus().setId(2);
		movTransito.setFecha(new Date());
		movTransito.setFolio(1234);
		movTransito.setIdConciliacion(3L);
		movTransito.setLastModifiedBy("JG Mod");
		movTransito.setLastModifiedDate(new Date());
		movTransito.setMonto(new BigDecimal(5000));
		movTransito.setMovimientoMidas(new MovimientoMidas(4L));
		movTransito.setNuevo(true);
		movTransito.setNumAutorizacion("Num Autorizacion");
		movTransito.setOperacionDesc("Desempenio");
		movTransito.setSucursal(5);
		movTransito.setTipoContratoDesc("56 - Pagos Libres");
		movTransito.setTitular("Titular");
		
		ReporteJdbcBulkInsert<MovimientoTransito> bulkTransito = new ReporteJdbcBulkInsert<MovimientoTransito>(Arrays.asList(movTransito), true);
		String callSP = bulkTransito.buildCallSP("save_movimiento_transito");
		String insertStatement = bulkTransito.buildInsertStatement();
		System.out.println(callSP);
		System.out.println(insertStatement);

	}

}
