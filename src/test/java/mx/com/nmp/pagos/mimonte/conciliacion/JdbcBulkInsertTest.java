package mx.com.nmp.pagos.mimonte.conciliacion;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.jdbc.ReporteJdbcBulkInsert;
import mx.com.nmp.pagos.mimonte.model.EstatusDevolucion;
import mx.com.nmp.pagos.mimonte.model.EstatusPago;
import mx.com.nmp.pagos.mimonte.model.EstatusTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MetodoPagoMovimientosProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoDevolucion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoPago;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoReporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TarjetaMovimientosProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoComisionEnum;

public class JdbcBulkInsertTest {

	public static void main(String[] args) {
		testMovPago();
	}


	public static void testMovTransito() {
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


	public static void testMidas() {
		MovimientoMidas movMidas = new MovimientoMidas();
		movMidas.setId(1L);
		movMidas.setEstatus(true);
		movMidas.setFecha(new Date());
		movMidas.setFolio(12345L);
		movMidas.setIdTipoContrato(11);
		movMidas.setTipoTarjeta("Banamex");
		ReporteJdbcBulkInsert<MovimientoReporte> bulk = new ReporteJdbcBulkInsert<MovimientoReporte>(Arrays.asList(movMidas), true);
		String insertStatement = bulk.buildInsertStatement();
		String query = bulk.buildInsertQuery();
		System.out.println(insertStatement);
		System.out.println(query);
	}


	public static void testMovProveedor() {
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
		ReporteJdbcBulkInsert<MovimientoReporte> bulk = new ReporteJdbcBulkInsert<MovimientoReporte>(Arrays.asList(movProveedor), true);
		String insertStatement = bulk.buildInsertStatement();
		String query = bulk.buildInsertQuery();
		System.out.println(insertStatement);
		System.out.println(query);
	}


	public static void testMovComision() {
		MovimientoComision movComision = new MovimientoComision();
		movComision.setId(1);
		movComision.setEstatus(true);
		movComision.setFechaCargo(new Date());
		movComision.setFechaOperacion(new Date());
		movComision.setIdConciliacion(2L);
		movComision.setIdMovimientoEstadoCuenta(3L);
		movComision.setMonto(new BigDecimal(600));
		movComision.setTipoComision(TipoMovimientoComisionEnum.COMISION);
		movComision.setMovimientoMidas(new MovimientoMidas(4L));
		movComision.setNuevo(false);
		movComision.setCreatedBy("JG");
		movComision.setCreatedDate(new Date());
		movComision.setLastModifiedBy("JG Mod");
		movComision.setLastModifiedDate(new Date());
		
		ReporteJdbcBulkInsert<MovimientoComision> bulk = new ReporteJdbcBulkInsert<MovimientoComision>(Arrays.asList(movComision), true);
		String insertStatement = bulk.buildInsertStatement();
		String query = bulk.buildInsertQuery();
		String sp = bulk.buildCallSP("save_movimiento_comision");
		System.out.println(insertStatement);
		System.out.println(query);
		System.out.println(sp);
	}


	public static void testMovDevolucion() {
		MovimientoDevolucion movDev = new MovimientoDevolucion();
		movDev.setId(1);
		movDev.setCodigoAutorizacion("Codigo Auth");
		movDev.setEsquemaTarjeta("Esquema Tarjeta");
		movDev.setEstatus(new EstatusDevolucion(2));
		movDev.setFecha(new Date());
		movDev.setFechaLiquidacion(new Date());
		movDev.setIdentificadorCuenta("Identificador Cuenta");
		movDev.setSucursal(100);
		movDev.setTitular("Titutlar");
		movDev.setMonto(new BigDecimal(600));

		movDev.setIdConciliacion(2L);
		movDev.setMovimientoMidas(new MovimientoMidas(4L));
		movDev.setNuevo(false);
		movDev.setCreatedBy("JG");
		movDev.setCreatedDate(new Date());
		movDev.setLastModifiedBy("JG Mod");
		movDev.setLastModifiedDate(new Date());
		
		ReporteJdbcBulkInsert<MovimientoDevolucion> bulk = new ReporteJdbcBulkInsert<MovimientoDevolucion>(Arrays.asList(movDev), true);
		String insertStatement = bulk.buildInsertStatement();
		String query = bulk.buildInsertQuery();
		String sp = bulk.buildCallSP("save_movimiento_devolucion");
		System.out.println(insertStatement);
		System.out.println(query);
		System.out.println(sp);
	}


	public static void testMovPago() {
		MovimientoPago movPago = new MovimientoPago();
		movPago.setId(1);
		movPago.setEstatus(new EstatusPago(1));
		movPago.setMonto(new BigDecimal(600));

		movPago.setIdConciliacion(2L);
		movPago.setMovimientoMidas(new MovimientoMidas(4L));
		movPago.setNuevo(false);
		movPago.setCreatedBy("JG");
		movPago.setCreatedDate(new Date());
		movPago.setLastModifiedBy("JG Mod");
		movPago.setLastModifiedDate(new Date());
		
		ReporteJdbcBulkInsert<MovimientoPago> bulk = new ReporteJdbcBulkInsert<MovimientoPago>(Arrays.asList(movPago), true);
		String insertStatement = bulk.buildInsertStatement();
		String query = bulk.buildInsertQuery();
		String sp = bulk.buildCallSP("save_movimiento_pago");
		System.out.println(insertStatement);
		System.out.println(query);
		System.out.println(sp);
	}

}
