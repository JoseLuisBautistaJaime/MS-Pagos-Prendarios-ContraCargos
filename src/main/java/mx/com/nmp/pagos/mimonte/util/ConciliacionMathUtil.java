package mx.com.nmp.pagos.mimonte.util;

import java.math.BigDecimal;
import java.util.List;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
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
	 * @param codigosEdoCuenta
	 * @return
	 */
	public static BigDecimal getDevolucionesEstadoCuenta(List<MovimientoEstadoCuenta> movsEstadoCuenta, CodigosEdoCuentaMap codigosEdoCuenta) {
		BigDecimal total = new BigDecimal(0);
		if (movsEstadoCuenta != null) {
			for (MovimientoEstadoCuenta movEstadoCuenta : movsEstadoCuenta) {
				if (codigosEdoCuenta.containsClaveInCategoria(ConciliacionConstants.CATEGORIA_ESTADO_CUENTA_DEVOLUCIONES, movEstadoCuenta.getClaveLeyenda())) {
					total = total.add(movEstadoCuenta.getImporte());
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
				if (movMidas.getEstatus()) {
					importeMidas = importeMidas.add(movMidas.getMonto());
				}
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
				if (isValidTransaction(movProveedor)) {
					importeProveedor = importeProveedor.add(movProveedor.getAmount());
				}
			}
		}
		return importeProveedor;
	}


	public static boolean isValidTransaction(MovimientoProveedor movProveedor) {
		return movProveedor != null && movProveedor.getStatus() != null && movProveedor.getStatus().equalsIgnoreCase(ConciliacionConstants.ESTATUS_TRANSACCION_OPENPAY_COMPLETED) &&
				movProveedor.getTransactionType() != null && movProveedor.getTransactionType().equalsIgnoreCase(ConciliacionConstants.TIPO_TRANSACCION_OPENPAY_CARGO);
	}


	/**
	 * (Cantidad de liquidación reflejada en el estado de cuenta, para el proceso de preconciliación este campo obtendrá el moto 0.00 ya que aún no se importa el estado de cuenta.)
	 * @param movsEstadoCuenta
	 * @param codigosEstadoCuenta
	 * @return
	 */
	public static BigDecimal getImporteBanco(List<MovimientoEstadoCuenta> movsEstadoCuenta, CodigosEdoCuentaMap codigosEdoCuenta) {
		BigDecimal montoLiquidacion = new BigDecimal(0);
		if (movsEstadoCuenta != null) {
			for (MovimientoEstadoCuenta movEstadoCuenta : movsEstadoCuenta) {
				if (codigosEdoCuenta.containsClave(movEstadoCuenta.getClaveLeyenda())) {
					// Monto liquidacion (solo ventas/devoluciones)
					if (codigosEdoCuenta.containsClaveInCategoria(ConciliacionConstants.CATEGORIA_ESTADO_CUENTA_VENTAS, movEstadoCuenta.getClaveLeyenda()) ||
						(codigosEdoCuenta.containsClaveInCategoria(ConciliacionConstants.CATEGORIA_ESTADO_CUENTA_DEVOLUCIONES, movEstadoCuenta.getClaveLeyenda()))) {
						if (movEstadoCuenta.getTipoMovimiento() != null && movEstadoCuenta.getTipoMovimiento() == ConciliacionConstants.TipoMovimiento.TIPO_ABONO) {
							montoLiquidacion = montoLiquidacion.add(movEstadoCuenta.getImporte());
						}
						else {
							montoLiquidacion = montoLiquidacion.subtract(movEstadoCuenta.getImporte());
						}
					}
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
	 * @param codigosEdoCuenta
	 * @return
	 */
	public static BigDecimal getDiferenciaProveedorBanco(List<MovimientoProveedor> movsProveedor, List<MovimientoEstadoCuenta> movsEstadoCuenta, CodigosEdoCuentaMap codigosEdoCuenta) {
		BigDecimal montoBanco = getImporteBanco(movsEstadoCuenta, codigosEdoCuenta);
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
