/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.ArrayList;
import java.util.List;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoProveedor;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoReporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

/**
 * Nombre: ReportesWrapper
 * Descripcion: Clase que encapsula los movimientos de los reportes de las diferentes fuentes
 *
 * @author JGALVEZ
 * Fecha: 04/06/2019 9:44 PM
 * @version 0.1
 */
public class ReportesWrapper {

	private List<MovimientoMidas> movimientosMidas;
	private List<MovimientoProveedor> movimientosProveedor;
	private List<MovimientoEstadoCuenta> movimientosEstadoCuenta;


	@SuppressWarnings("unchecked")
	public <T extends MovimientoReporte> List<T> getByTipoReporte(TipoReporteEnum tipoReporte) {
		List<T> movimientos = null;
		switch (tipoReporte) {
			case MIDAS:
				movimientos = (List<T>) this.movimientosMidas;
				break;
			case PROVEEDOR:
				movimientos = (List<T>) this.movimientosProveedor;
				break;
			case ESTADO_CUENTA:
				movimientos = (List<T>) this.movimientosEstadoCuenta;
				break;
		}
		return movimientos;
	}


	public List<MovimientoMidas> getMovimientosMidasByTransaccion(Long transaccion) {
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

	public boolean contains(TipoReporteEnum tipoReporte) {
		boolean contains = false;
		switch (tipoReporte) {
			case MIDAS:
				contains = this.movimientosMidas != null && this.movimientosMidas.size() > 0;
				break;
			case PROVEEDOR:
				contains = this.movimientosProveedor != null && this.movimientosProveedor.size() > 0;
				break;
			case ESTADO_CUENTA:
				contains = this.movimientosEstadoCuenta != null && this.movimientosEstadoCuenta.size() > 0;
				break;
		}
		return contains;
	}


	public void setMovimientosMidas(List<MovimientoMidas> movimientosMidas) {
		this.movimientosMidas = movimientosMidas;
	}

	public void setMovimientosProveedor(List<MovimientoProveedor> movimientosProveedor) {
		this.movimientosProveedor = movimientosProveedor;
	}

	public void setMovimientosEstadoCuenta(List<MovimientoEstadoCuenta> movimientosEstadoCuenta) {
		this.movimientosEstadoCuenta = movimientosEstadoCuenta;
	}

}
