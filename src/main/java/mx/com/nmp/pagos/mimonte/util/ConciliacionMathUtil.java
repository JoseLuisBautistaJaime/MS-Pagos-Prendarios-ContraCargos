package mx.com.nmp.pagos.mimonte.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.model.ComisionProveedor;
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
				//if (movMidas.getEstatus()) { // Se consideran todos los movimientos exitosos y erroneos
					importeMidas = importeMidas.add(movMidas.getMonto());
				//}
			}
		}
		return importeMidas;
	}


	/**
	 * (Suma total de montos del reporte del proveedor transaccional)
	 * @param movsProveedor
	 * @param comisionProveedor 
	 * @return
	 */
	public static BigDecimal getImporteProveedor(List<MovimientoProveedor> movsProveedor, ComisionProveedor comisionProveedor) {
		BigDecimal importeProveedor = new BigDecimal(0);
		if (movsProveedor != null) {
			for (MovimientoProveedor movProveedor : movsProveedor) {
				if (isValidTransaction(movProveedor)) {
					importeProveedor = importeProveedor.add(getMontoOperacionProveedor(movProveedor.getAmount(), comisionProveedor));
				}
			}
		}
		return importeProveedor;
	}


	/**
	 * Calcula el monto total de la operacion realizada a partir del monto depositado OXXO y considerando el % comision e iva de la comision (si existe)
	 * @param amount
	 * @param comisionProveedor
	 * @return
	 */
	public static BigDecimal getMontoOperacionProveedor(BigDecimal amount, ComisionProveedor comisionProveedor) {
		BigDecimal monto = amount;
		if (comisionProveedor != null && comisionProveedor.getComision() != null && comisionProveedor.getIva() != null) {
			BigDecimal comisionMasIva = comisionProveedor.getComision().add(comisionProveedor.getIva()); // Comision + iva
			BigDecimal porcentajeMonto = new BigDecimal(100).subtract(comisionMasIva); // Porcentaje correspondiente al monto actual
			BigDecimal comision = monto.multiply(comisionProveedor.getComision()).divide(porcentajeMonto, 4, RoundingMode.HALF_UP); // Porcentaje de comision adicional cobrado
			BigDecimal ivaComision = monto.multiply(comisionProveedor.getIva()).divide(porcentajeMonto, 4, RoundingMode.HALF_UP); // Porcentaje de comision iva cobrado
			monto = monto.add(comision).add(ivaComision);
		}
		return monto;
	}


	/**
	 * Calcula el monto proveedor considerando el % comision e iva de la comision (si existe)
	 * @param amount
	 * @param comisionProveedor
	 * @return
	 */
	public static BigDecimal getComisionCobradaProveedor(BigDecimal monto, ComisionProveedor comisionProveedor) {
		BigDecimal comision = new BigDecimal(0);
		if (comisionProveedor != null && comisionProveedor.getComision() != null && comisionProveedor.getIva() != null && monto != null) {
			BigDecimal comisionMasIva = comisionProveedor.getComision().add(comisionProveedor.getIva()); // Comision + iva
			BigDecimal porcentajeMonto = new BigDecimal(100).subtract(comisionMasIva); // Porcentaje correspondiente al monto actual
			comision = monto.multiply(comisionProveedor.getComision()).divide(porcentajeMonto, 4, RoundingMode.HALF_UP); // Porcentaje de comision adicional cobrado
		}
		return comision;
	}


	/**
	 * Calcula el monto proveedor considerando el % comision e iva de la comision (si existe)
	 * @param amount
	 * @param comisionProveedor
	 * @return
	 */
	public static BigDecimal getComisionIvaCobradaProveedor(BigDecimal monto, ComisionProveedor comisionProveedor) {
		BigDecimal ivaComision = new BigDecimal(0);
		if (comisionProveedor != null && comisionProveedor.getComision() != null && comisionProveedor.getIva() != null && monto != null) {
			BigDecimal comisionMasIva = comisionProveedor.getComision().add(comisionProveedor.getIva()); // Comision + iva
			BigDecimal porcentajeMonto = new BigDecimal(100).subtract(comisionMasIva); // Porcentaje correspondiente al monto actual
			ivaComision = monto.multiply(comisionProveedor.getIva()).divide(porcentajeMonto, 4, RoundingMode.HALF_UP); // Porcentaje de comision iva cobrado
		}
		return ivaComision;
	}


	/**
	 * Calcula el monto depositado por el proveedor restando la % comision e iva de la comision (si existe)
	 * @param monto
	 * @param comisionProveedor
	 * @return
	 */
	public static BigDecimal getMontoDepositadoPorProveedor(BigDecimal montoTotalOperacion, ComisionProveedor comisionProveedor) {
		BigDecimal montoDepositado = new BigDecimal(0);
		if (comisionProveedor != null && comisionProveedor.getComision() != null && comisionProveedor.getIva() != null && montoTotalOperacion != null) {
			BigDecimal comisionMasIva = comisionProveedor.getComision().add(comisionProveedor.getIva()); // Comision + iva
			BigDecimal porcentajeMonto = new BigDecimal(100).subtract(comisionMasIva); // Porcentaje correspondiente al monto actual
			montoDepositado = montoTotalOperacion.multiply(porcentajeMonto).divide(new BigDecimal(100), 4, RoundingMode.HALF_UP); // Porcentaje de comision iva cobrado
		}
		return montoDepositado;
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
					// Monto liquidacion (solo ventas)
					if (codigosEdoCuenta.containsClaveInCategoria(ConciliacionConstants.CATEGORIA_ESTADO_CUENTA_VENTAS, movEstadoCuenta.getClaveLeyenda())) {
						if (movEstadoCuenta.getTipoMovimiento() != null && movEstadoCuenta.getTipoMovimiento() == ConciliacionConstants.TipoMovimiento.TIPO_ABONO) {
							montoLiquidacion = montoLiquidacion.add(movEstadoCuenta.getImporte());
						}
						//else {
						//	montoLiquidacion = montoLiquidacion.subtract(movEstadoCuenta.getImporte());
						//}
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
	 * @param comisionProveedor
	 * @return
	 */
	public static BigDecimal getDiferenciaProveedorMidas(List<MovimientoProveedor> movsProveedor, List<MovimientoMidas> movsMidas, ComisionProveedor comisionProveedor) {
		BigDecimal montoMidas = getImporteMidas(movsMidas);
		BigDecimal montoProveedor = getImporteProveedor(movsProveedor, comisionProveedor);
		
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
	 * @param comisionProveedor
	 * @return
	 */
	public static BigDecimal getDiferenciaProveedorBanco(List<MovimientoProveedor> movsProveedor, List<MovimientoEstadoCuenta> movsEstadoCuenta,
			CodigosEdoCuentaMap codigosEdoCuenta, ComisionProveedor comisionProveedor) {
		BigDecimal montoBanco = getImporteBanco(movsEstadoCuenta, codigosEdoCuenta);
		BigDecimal montoProveedor = getImporteProveedor(movsProveedor, comisionProveedor);
		
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
