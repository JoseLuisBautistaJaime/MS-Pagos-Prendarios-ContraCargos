package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.ArrayList;
import java.util.List;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;


public class MovimientosConciliacionWrapper {

	private List<MovimientoMidas> movimientosMidas;
	private List<MovimientoProveedor> movimientosProveedor;
	private List<MovimientoEstadoCuenta> movimientosEstadoCuenta;


	public void setMovimientosMidas(List<MovimientoMidas> movimientosMidas) {
		this.movimientosMidas = movimientosMidas;
	}

	public void setMovimientosProveedor(List<MovimientoProveedor> movimientosProveedor) {
		this.movimientosProveedor = movimientosProveedor;
	}

	public void setMovimientosEstadoCuenta(List<MovimientoEstadoCuenta> movimientosEstadoCuenta) {
		this.movimientosEstadoCuenta = movimientosEstadoCuenta;
	}


	public List<MovimientoMidas> getByTransaccion(Long transaccion) {
		List<MovimientoMidas> movimientos = null;
		if (this.movimientosMidas != null && transaccion != null) {
			movimientos = new ArrayList<MovimientoMidas>();
			for (MovimientoMidas movimientoMidas : this.movimientosMidas) {
				if (movimientoMidas.getTransaccion() != null && movimientoMidas.getTransaccion() == transaccion.longValue()) {
					movimientos.add(movimientoMidas);
				}
			}
		}
		return movimientos;
	}


	public List<MovimientoProveedor> getByTransaccion(String transaccion) {
		List<MovimientoProveedor> movimientos = null;
		if (this.movimientosProveedor != null && transaccion != null) {
			movimientos = new ArrayList<MovimientoProveedor>();
			for (MovimientoProveedor movimientoProveedor : this.movimientosProveedor) {
				if (movimientoProveedor.getOrderId() != null && movimientoProveedor.getOrderId().equals(transaccion)) {
					movimientos.add(movimientoProveedor);
				}
			}
		}
		return movimientos;
	}

}
