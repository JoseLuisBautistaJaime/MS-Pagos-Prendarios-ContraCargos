package mx.com.nmp.pagos.mimonte.util;

import java.math.BigDecimal;
import java.util.List;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;

/**
 * @name MergeMathUtil
 * @description Clase que realiza las operaciones aritmeticas sobre los movimientos conciliacion
 * @author JGALVEZ
 * @creationDate 06/10/2019 20:47 hrs.
 * @version 0.1
 */
public class ConciliacionMathUtil {


	// Sumatorias

	/**
	 * (Cantidad de las devoluciones autorizadas por la entidad bancaria reflejada en el estado de cuenta, para el proceso de preconciliación este campo obtendrá
	 * el monto 0.00 ya que un no se importa el estado de cuenta.)
	 * @param movsEstadoCuenta
	 * @return
	 */
	public static int getDevolucionesEstadoCuenta(List<MovimientoEstadoCuenta> movsEstadoCuenta) {
		int total = 0;
		if (movsEstadoCuenta != null) {
			for (MovimientoEstadoCuenta movEstadoCuenta : movsEstadoCuenta) {
				if (movEstadoCuenta.getConcepto() != null && movEstadoCuenta.getConcepto().equals(CatalogConstants.CONCEPTO_DEVOLUCION)) {
					total ++;
				}
			}
		}
		return total;
	}


	/**
	 * (Suma total de montos pagados en las partidas MIDAS, pagos exitosos y pagos erróneos)
	 * @param movimientosMidas
	 * @return
	 */
	public static BigDecimal getImporteMidas(List<MovimientoMidas> movsMidas) {
		BigDecimal importeMidas = new BigDecimal(0);
		if (movsMidas != null) {
			for (MovimientoMidas movMidas : movsMidas) {
				importeMidas = importeMidas.add(movMidas.getMonto());
			}
		}
		return importeMidas;
	}


	/**
	 * (Suma total de montos del reporte del proveedor transaccional)
	 * @param movsProveedor
	 * @return
	 */
	public static BigDecimal getImporteProveedor(List<MovimientoProveedor> movsProveedor) {
		BigDecimal importeProveedor = new BigDecimal(0);
		if (movsProveedor != null) {
			for (MovimientoProveedor movProveedor : movsProveedor) {
				importeProveedor = importeProveedor.add(movProveedor.getAmount());
			}
		}
		return importeProveedor;
	}


	/**
	 * (Cantidad de liquidación reflejada en el estado de cuenta, para el proceso de preconciliación este campo obtendrá el moto 0.00 ya que aún no se importa el estado de cuenta.)
	 * @param movsEstadoCuenta
	 * @return
	 */
	public static BigDecimal getImporteBanco(List<MovimientoEstadoCuenta> movsEstadoCuenta) {
		BigDecimal montoLiquidacion = new BigDecimal(0);
		if (movsEstadoCuenta != null) {
			for (MovimientoEstadoCuenta movEstadoCuenta : movsEstadoCuenta) {
				if (movEstadoCuenta.getConcepto() != null && movEstadoCuenta.getConcepto().equals(CatalogConstants.CONCEPTO_DEVOLUCION)) {
					montoLiquidacion = montoLiquidacion.add(movEstadoCuenta.getImporte());
				}
			}
		}
		return montoLiquidacion;
	}


	/**
	 * (Este campo realizará una resta entre las cantidades de los montos totales ($) del reporte transaccional y del reporte de procesos nocturnos, si el resultado es 0.00,
	 * quiere decir que los montos de las transacciones y las afectaciones en MIDAS son exitosas; en caso contrario, se tendrá que validar si existen registros en la sección
	 * “Movimientos en tránsito”.)
	 * @param movsProveedor
	 * @param movsMidas
	 * @return
	 */
	public static BigDecimal getDiferenciaProveedorMidas(List<MovimientoProveedor> movsProveedor, List<MovimientoMidas> movsMidas) {
		BigDecimal montoMidas = getImporteMidas(movsMidas);
		BigDecimal montoProveedor = getImporteProveedor(movsProveedor);
		
		BigDecimal diff = new BigDecimal(0);
		if (montoMidas != null && montoProveedor != null) {
			diff = montoProveedor.subtract(montoMidas);
		}
		else if (montoMidas != null) {
			diff = montoMidas;
		}
		else if (montoProveedor != null){
			diff = montoProveedor;
		}

		return diff;
	}


	/**
	 * (Este campo realizará una resta entre las cantidades de los montos totales ($) del reporte transaccional y del monto reflejado en el estado de cuenta,
	 * si el resultado es 0.00, quiere decir que los montos de las transacciones y de la liquidación del estado de cuenta son exitosas.)
	 * @param movsProveedor
	 * @param movsEstadoCuenta
	 * @return
	 */
	public static BigDecimal getDiferenciaProveedorBanco(List<MovimientoProveedor> movsProveedor, List<MovimientoEstadoCuenta> movsEstadoCuenta) {
		BigDecimal montoBanco = getImporteBanco(movsEstadoCuenta);
		BigDecimal montoProveedor = getImporteProveedor(movsProveedor);
		
		BigDecimal diff = new BigDecimal(0);
		if (montoBanco != null && montoProveedor != null) {
			diff = montoProveedor.subtract(montoBanco);
		}
		else if (montoBanco != null) {
			diff = montoBanco;
		}
		else if (montoProveedor != null){
			diff = montoProveedor;
		}

		return diff;
	}

}
